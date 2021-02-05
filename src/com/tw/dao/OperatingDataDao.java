package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.tw.entity.Vehicle;
import com.ze.util.DataBese;
import com.ze.util.PageHelper;

/**
 * @category：营运数据统计，营运交易查询，群组营运交易查询等相关查询
 * @author： sven.zhang
 * @since：2015.3.25
 * */
public class OperatingDataDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DecimalFormat df =new DecimalFormat("0.00");
	//营运交易查询
	public List<OperatingData> getBusiness(Condition condition, PageHelper page){
		List<OperatingData> list = new ArrayList<OperatingData>();
		String sj=condition.getStartTime().substring(0,4)+condition.getStartTime().substring(5,7);
		StringBuffer sql = new StringBuffer("select * from(select p.* ,rownum rn from(select t1.*,v1.*from");
		sql.append("(select * from  jjq.JJQ"+sj+"_1@taxilink89 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss') and flag = '1000000000')t1,");
		sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
		if (condition.getCompany() != null)
			sql.append(" and ba_name='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
			sql.append(" and comp_name='" + condition.getBranch() + "' ");
		if (condition.getCardNo() != null)
			sql.append(" and vehi_no='" + condition.getCardNo() + "'");		
		sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no )p where rownum<=");
		sql.append(page.getPageSize()* page.getCurrentPage()+" ) where rn> " );
		sql.append( page.getPageSize()* (page.getCurrentPage() - 1) );
		System.out.println(sql);
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				OperatingData b = new OperatingData();								
				b.setNumber(rs.getInt("rn"));
				b.setVhic(rs.getString("vehi_no"));
				b.setCertNo(rs.getString("yingyun"));
				b.setUpTaxi(rs.getString("shangche"));
				b.setDownTaxi(rs.getString("xiache"));
				b.setMoney(rs.getFloat("jine") / 100);				
				b.setDistance(rs.getFloat("jicheng")/10);
				b.setEmpty(rs.getFloat("kongshi")/10);
				b.setWaitTime(rs.getInt("denghou"));
				b.setType(rs.getString("jiaoyitype"));
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		return list;
	}
	//营运交易数量查询
	public Integer getNumbyBusiness(Condition condition){
		Integer t = 0;
		String sj=condition.getStartTime().substring(0,4)+condition.getStartTime().substring(5,7);
		StringBuffer sql = new StringBuffer("select count(*) c from");
		sql.append("(select * from  jjq.JJQ"+sj+"_1@taxilink89 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')  and flag = '1000000000'");
		sql.append("and  vhic in (select ltrim(vehi_no, '浙') from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
		if (condition.getCompany() != null)
			sql.append(" and ba_name='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
			sql.append(" and comp_name='" + condition.getBranch() + "' ");
		if (condition.getCardNo() != null)
			sql.append(" and vehi_no='" + condition.getCardNo() + "'");		
		sql.append(")) ");
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				t=rs.getInt("C");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		return t;
	}
	// 按照车号统计查询
	public List<OperatingData> getAll(Condition condition, PageHelper page) {
			List<OperatingData> list = new ArrayList<OperatingData>();
			StringBuffer sql = new StringBuffer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String tt = sdf.format(new Date());
//			if(Integer.parseInt(condition.getStartTime().substring(0,4))<2017||condition.getStartTime().equals(tt)){
//				String sj=condition.getStartTime().substring(0,4);
//				sql = new StringBuffer("select * from(select p.*,rownum rn from(select t1.*,v1.*from (select vhic," +
//						" sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0))," +
//						"upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(" +
//						"translate(jine, '$%', ' '),' ',''),0),0))) st,count(vhic) ct,sum(TO_NUMBER(" +
//						"decode(decode(lower(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0))" +
//						",upper(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0)),1,0),1," +
//						"nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0),0))+TO_NUMBER" +
//						"(decode(decode(lower(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0))" +
//						",upper(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0)),1,0),1," +
//						"nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0),0))*60+ " +
//						"TO_NUMBER(decode(decode(lower(nvl(replace(translate(substr(denghou,-6,2), " +
//						"'$%', ' '),' ',''),0)),upper(nvl(replace(translate(substr(denghou,-6,2), '$%', ' ')" +
//						",' ',''),0)),1,0),1,nvl(replace(translate(substr(denghou,-6,2), '$%', ' '),' ',''),0),0)" +
//						")*3600) waitTime, sum(TO_NUMBER(decode(decode(lower(nvl(replace" +
//						"(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' ')" +
//						",' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance, " +
//						"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' ')" +
//						",' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1," +
//						"nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
//						"sum((xiache-shangche)*60*24*60)time1 from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
//				sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
//				sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
//				sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
//				if (condition.getCompany() != null)
//					sql.append(" and ba_name='" + condition.getCompany() + "' ");
//				if (condition.getBranch() != null)
//					sql.append(" and comp_name='" + condition.getBranch() + "' ");
//				if (condition.getCardNo() != null)
//					sql.append(" and vehi_no='" + condition.getCardNo() + "'");		
//				sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no order by ba_name asc,comp_name asc,vehi_no asc )p where rownum<=");
//				sql.append(page.getPageSize()* page.getCurrentPage()+") where rn> " );
//				sql.append( page.getPageSize()* (page.getCurrentPage() - 1));
//			}else{
				String st = condition.getStartTime().replaceAll("-", "");
				String et = condition.getEndTime().replaceAll("-", "");
				String tab = "jjq.JJQ_TJ_"+st.substring(0, 6)+"_DAY@taxilink89";
				sql.append("select t1.*,rownum rn from (select cphm vehi_no,zgs ba_name,fgs comp_name,sum(tjcs) ct,sum(jine) * 100 st"
						+ ",sum(szlc) * 10 distance,sum(kslc)*10 empty1,sum(yssc) * 60 time1,sum(dhsj) * 60 waitTime"
						+ " from "+tab+" where type='5'");
				sql.append(" and day >='"+st+"'");
				sql.append(" and day <='"+et+"'");
				if (condition.getCompany() != null)
					sql.append(" and zgs='" + condition.getCompany() + "' ");
				if (condition.getBranch() != null)
					sql.append(" and fgs='" + condition.getBranch() + "' ");
				if (condition.getCardNo() != null)
					sql.append(" and '浙'||cphm='" + condition.getCardNo() + "'");	
				sql.append("group by cphm, zgs, fgs order by comp_name,cphm) t1 where"
						+ " rownum <="+page.getPageSize()* page.getCurrentPage()+" and rownum >"+page.getPageSize()* (page.getCurrentPage() - 1));
//			}
			System.out.println(sql);
			con = DataBese.getConnectionOfOracle();
			try {
				ps = con.prepareStatement(sql.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					OperatingData b = new OperatingData();				
					b.setCompany(rs.getString("ba_name"));
					b.setBranch(rs.getString("comp_name"));				
					b.setNumber(rs.getInt("rn"));
					b.setVhic(rs.getString("vehi_no"));
					b.setMoney(rs.getFloat("st") / 100);
					b.setTimes(rs.getInt("ct"));				
					b.setDistance(rs.getFloat("distance")/10);
					b.setTimeOut(rs.getInt("time1"));
					b.setEmpty(rs.getFloat("empty1")/10);
					b.setWaitTime(rs.getInt("waitTime"));
					list.add(b);
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return list;
		}

	
	// 按车号获取数据总数量查询
	public Integer geData(Condition condition) {
		Integer t = 0;
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tt = sdf.format(new Date());
//		if(Integer.parseInt(condition.getStartTime().substring(0,4))<2017||condition.getStartTime().equals(tt)){
//			String sj=condition.getStartTime().substring(0,4);
//			sql = new StringBuffer("select * from (select distinct(vhic)from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where shangche>=to_date(");
//			sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
//			sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss'))t1,");
//			sql.append("(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where 1=1");
//			if (condition.getCompany() != null)			
//				sql = sql.append(" and ba_name='" + condition.getCompany() + "' ");
//			if (condition.getBranch() != null)
//				sql = sql.append(" and comp_name='" + condition.getBranch() + "' ");		
//			if (condition.getCardNo() != null)
//				sql = sql.append(" and vehi_no='" + condition.getCardNo() + "'");
//			sql.append(")v1 where  '浙'||t1.vhic=v1.vehi_no");
//		}else{
			String st = condition.getStartTime().replaceAll("-", "");
			String et = condition.getEndTime().replaceAll("-", "");
			String tab = "jjq.JJQ_TJ_"+st.substring(0, 6)+"_DAY@taxilink89";
			sql.append("select cphm vehi_no,zgs ba_name,fgs comp_name,sum(tjcs) ct,sum(jine) * 100 st"
					+ ",sum(szlc) * 10 distance,sum(kslc) empty1,sum(yssc) * 60 time1,sum(dhsj) * 60 waitTime"
					+ " from "+tab+" where type='5'");
			sql.append(" and day >='"+st+"'");
			sql.append(" and day <='"+et+"'");
			if (condition.getCompany() != null)
				sql.append(" and zgs='" + condition.getCompany() + "' ");
			if (condition.getBranch() != null)
				sql.append(" and fgs='" + condition.getBranch() + "' ");
			if (condition.getCardNo() != null)
				sql.append(" and '浙'||cphm='" + condition.getCardNo() + "'");	
			sql.append("group by cphm, zgs, fgs order by comp_name,cphm");
//		}
		System.out.println(sql);
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				++t;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}
	
	
	// 按照资格证号统计查询
	public List<OperatingData> getDataByCetNo(Condition condition, PageHelper page) {
		List<OperatingData> list = new ArrayList<OperatingData>();
		String sj=condition.getStartTime().substring(0,4);
		StringBuffer sql = new StringBuffer("select * from(select p.*,rownum rn from(select t1.*,v1.* from ");
		sql.append("(select yingyun," +
				" sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st," +
				"count(yingyun) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime," +
				" sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance," +
				"  sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
				"sum((xiache-shangche)*60*24*60)time1");
		sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')");
		if(condition.getCertNo() != null)
			sql.append(" and yingyun='" + condition.getCertNo() + "'");
		sql.append(" group by yingyun)t1,");
		sql.append(" (select vhic ,max( distinct yingyun)yy from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)p,");
		sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
		if (condition.getCompany() != null)
			sql.append(" and ba_name='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
			sql.append(" and comp_name='" + condition.getBranch() + "' ");	
		sql.append(")v1 where '浙'||p.vhic =v1.vehi_no and p.yy = t1.yingyun order by ba_name asc,comp_name asc,yingyun asc )p where rownum<=");
		sql.append(page.getPageSize()* page.getCurrentPage()+" )where rn> " );
		sql.append( page.getPageSize()* (page.getCurrentPage() - 1) );
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				OperatingData b = new OperatingData();				
				b.setCompany(rs.getString("ba_name"));
				b.setBranch(rs.getString("comp_name"));				
				b.setNumber(rs.getInt("rn"));
				b.setCertNo(rs.getString("yingyun"));
				b.setMoney(rs.getFloat("st") / 100);
				b.setTimes(rs.getInt("ct"));				
				b.setDistance(rs.getFloat("distance")/10);
				b.setTimeOut(rs.getInt("time1"));
				b.setEmpty(rs.getFloat("empty1")/10);
				b.setWaitTime(rs.getInt("waitTime"));
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}
	//按照公司查询数据统计
		public List<OperatingData> getDataByCompany(Condition condition, PageHelper page) {
			List<OperatingData> list = new ArrayList<OperatingData>();
			String sj=condition.getStartTime().substring(0,4);
			StringBuffer sql = new StringBuffer();
//			StringBuffer sql = new StringBuffer("select * from (select q.*,rownum rn from(select p.*,s.ba_name ,f.total from(");
//			sql.append("select sum(st) st,sum(ct) ct,count(vhic)qt,sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1, sum(time1)time1,comp_name from( ");
//			sql.append("select t1.*,v1.* from ");			
//			sql.append("(select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st" +
//					",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime," +
//					"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance," +
//					"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
//					"sum((xiache-shangche)*60*24*60)time1");			
//			sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
//			sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
//			sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
//			sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
//			if (condition.getCompany() != null)				
//				sql.append(" and ba_name='" + condition.getCompany() + "' ");
//			if (condition.getBranch() != null)
//				sql.append(" and comp_name='" + condition.getBranch() + "' ");					
//			sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no )group by comp_name )p,");
//			sql.append("(select comp_name,max(distinct ba_name)ba_name from HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v where v.ba_id =t.ba_id group by comp_name )s,");
//			sql.append("(select count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v group by comp_name)f");
//			sql.append(" where p.comp_name =s.comp_name and f.comp_name=s.comp_name order by total desc)q where rownum<=");
//			sql.append(page.getPageSize()* page.getCurrentPage()+" )where rn> " );
//			sql.append( page.getPageSize()* (page.getCurrentPage() - 1) );
			String st = condition.getStartTime().replaceAll("-", "");
			String et = condition.getEndTime().replaceAll("-", "");
			String tab = "jjq.JJQ_TJ_"+st.substring(0, 6)+"_DAY@taxilink89";
			sql.append("select x.* from (select t1.*,f.total, rownum rn  from (select zgs ba_name,fgs comp_name,count(distinct cphm) qt,sum(tjcs) ct,sum(jine) * 100 st"
					+ ",sum(szlc) * 10 distance,sum(kslc)*10 empty1,sum(yssc) * 60 time1,sum(dhsj) * 60 waitTime"
					+ " from "+tab+" s,(select count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v group by comp_name)f where  s.fgs=f.comp_name and type='5'");
			sql.append(" and day >='"+st+"'");
			sql.append(" and day <='"+et+"'");
			if (condition.getCompany() != null)
				sql.append(" and zgs='" + condition.getCompany() + "' ");
			if (condition.getBranch() != null)
				sql.append(" and fgs='" + condition.getBranch() + "' ");
//			if (condition.getCardNo() != null)
//				sql.append(" and '浙'||cphm='" + condition.getCardNo() + "'");	
			sql.append("group by fgs,zgs order by comp_name) t1 left join (select count(vehi_no) total, comp_name"
					+ " from HZGPS_TAXI.VW_VEHICLE@TAXILINK v group by comp_name) f on t1.comp_name = f.comp_name) x where"
					+ " rn <="+page.getPageSize()* page.getCurrentPage()+" and rn >"+page.getPageSize()* (page.getCurrentPage() - 1));
System.out.println(sql);
			con = DataBese.getConnectionOfOracle();
			try {
				ps = con.prepareStatement(sql.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					OperatingData b = new OperatingData();
					float total = (rs.getFloat("distance")+rs.getFloat("empty1"))/10;
					float per = rs.getFloat("distance")/total*10;
					b.setDriving(rs.getInt("qt"));
					b.setTotal(rs.getInt("total"));
					b.setCompany(rs.getString("ba_name"));
					b.setBranch(rs.getString("comp_name"));
					b.setNumber(rs.getInt("rn"));
					b.setMoney(rs.getFloat("st") / 100);
					b.setTimes(rs.getInt("ct"));
					b.setTotalDis(total+"");
					b.setYpercent(df.format(per)+"%");
					b.setDistance(rs.getFloat("distance")/10);
					b.setTimeOut(rs.getInt("time1"));
					b.setEmpty(rs.getFloat("empty1")/10);
					b.setWaitTime(rs.getInt("waitTime"));
					list.add(b);
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return list;
		}	
	//按照公司获取总数量查询	
	public Integer getNumByCompany(Condition condition){
		String sj=condition.getStartTime().substring(0,4);
		StringBuffer sql = new StringBuffer();
		Integer t = 0;
//		StringBuffer sql = new StringBuffer("select  count(comp_name) from (select distinct vhic from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where shangche>=to_date(");
//		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
//		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')");
//		sql.append(")t1,(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where 1=1");
//		if (condition.getCompany() != null)		
//			sql = sql.append(" and ba_name='" + condition.getCompany() + "' ");
//		if (condition.getBranch() != null)
//			sql = sql.append(" and comp_name='" + condition.getBranch() + "' ");					
//		sql.append(")v1 where  '浙'||t1.vhic=v1.vehi_no group by comp_name");
		String st = condition.getStartTime().replaceAll("-", "");
		String et = condition.getEndTime().replaceAll("-", "");
		String tab = "jjq.JJQ_TJ_"+st.substring(0, 6)+"_DAY@taxilink89";
		sql.append("select zgs ba_name,fgs comp_name,sum(tjcs) ct,sum(jine) * 100 st"
				+ ",sum(szlc) * 10 distance,sum(kslc) empty1,sum(yssc) * 60 time1,sum(dhsj) * 60 waitTime"
				+ " from "+tab+" where type='5'");
		sql.append(" and day >='"+st+"'");
		sql.append(" and day <='"+et+"'");
		if (condition.getCompany() != null)
			sql.append(" and zgs='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
			sql.append(" and fgs='" + condition.getBranch() + "' ");
//		if (condition.getCardNo() != null)
//			sql.append(" and '浙'||cphm='" + condition.getCardNo() + "'");	
		sql.append("group by fgs,zgs order by comp_name");
		System.out.println(sql);
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				++t;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}
	//按照资格证号获取总数量查询	
	public Integer getNumByCertNo(Condition condition){
		String sj=condition.getStartTime().substring(0,4);
		Integer t = 0;
		StringBuffer sql = new StringBuffer("select count(yingyun)from (select yingyun, max(distinct vhic) vhic from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') ");
		if(condition.getCertNo() != null)
			sql = sql.append(" and yingyun='" + condition.getCertNo() + "' ");
		sql.append("and shangche<=to_date('"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')  group by yingyun)t1,");
		sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where 1=1");
		if (condition.getCompany() != null)	
			sql = sql.append(" and ba_name='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
		sql = sql.append(" and comp_name='" + condition.getBranch() + "' ");
		sql.append(" )v1 where '浙'||t1.vhic=v1.vehi_no group by yingyun");
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				++t;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return t;
	}
	
	//查询群组数据
	public List<OperatingData> getDataByGroup(Condition condition,PageHelper page){
		List<OperatingData> list = new ArrayList<OperatingData>();
		String sj=condition.getStartTime().substring(0,4);
		StringBuffer sql = new StringBuffer("select * from(select q.*,rownum rn from(select p.*,f.total from(");
		sql.append("select sum(st) st,sum(ct) ct,count(vhic)qt,sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1, sum(time1)time1,group_name from( ");
		sql.append("select t1.*,v1.* from ");			
		sql.append("(select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st" +
				",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime," +
				"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance," +
				"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
				"sum((xiache-shangche)*60*24*60)time1");			
		sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
		sql.append("(select tb.group_vhic,ts.* from tb_taxi_vehicle_groupname ts,tb_taxi_vehicle_group tb  where  tb.group_id = ts.group_id  ");	
		if (condition.getGroup() != null)				
			sql.append(" and ts.group_id='" + condition.getGroup() + "' ");
		sql.append(")v1 where '浙'||t1.vhic =v1.group_vhic)group by group_name)p, ");
		sql.append("(select count(group_vhic)total ,group_name from tb_taxi_vehicle_group tb ,tb_taxi_vehicle_groupname tn where tb.group_id=tn.group_id group by group_name )f where f.group_name = p.group_name");
		sql.append(" order by total desc) q where rownum<=");
		sql.append(page.getPageSize()* page.getCurrentPage()+")where rn> " );
		sql.append( page.getPageSize()* (page.getCurrentPage() - 1) );
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				OperatingData b = new OperatingData();
				float total = (rs.getFloat("distance")+rs.getFloat("empty1"))/10;
				float per = rs.getFloat("distance")/total*10;
				b.setDriving(rs.getInt("qt"));
				b.setTotal(rs.getInt("total"));
				b.setVpercent(df.format(100*rs.getInt("qt")/rs.getInt("total"))+"%");
				b.setGroup(rs.getString("group_name"));
				b.setNumber(rs.getInt("rn"));
				b.setMoney(rs.getFloat("st") / 100);
				b.setTimes(rs.getInt("ct"));
				b.setTotalDis(total+"");
				b.setYpercent(df.format(per)+"%");
				b.setDistance(rs.getFloat("distance")/10);
				b.setTimeOut(rs.getInt("time1"));
				b.setEmpty(rs.getFloat("empty1")/10);
				b.setWaitTime(rs.getInt("waitTime"));
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

				
		
		return list;
		
	}
	//查询群组获取总数量
	public Integer getNumByGroup(Condition condition){
		String sj=condition.getStartTime().substring(0,4);
		Integer t = 0;
		StringBuffer sql = new StringBuffer("select  count(group_id) from (select distinct vhic from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')");
		sql.append(")t1,(select *from tb_taxi_vehicle_group tb where 1=1");
		if(condition.getGroup() != null)
			sql.append(" and group_id='"+condition.getGroup()+"'");
		sql.append(")v1 where  '浙'||t1.vhic=v1.group_vhic group by group_id");	
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				++t;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}
	//群组交易查询
	public List<OperatingData> getGroupsel(Condition condition,PageHelper page){
		List<OperatingData> list = new ArrayList<OperatingData>();
		String sj=condition.getStartTime().substring(0,4);
		StringBuffer sql = new StringBuffer("");
		sql.append("select * from(select p.*, rownum rn from(select * from(select * from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss'))t1,");
		sql.append("(select * from tb_taxi_vehicle_group tb where 1= 1");
		if(condition.getGroup() != null)
			sql.append(" and group_id='"+condition.getGroup()+"'");
		if(condition.getCardNo() != null)
			sql.append("and group_vhic='"+condition.getCardNo()+"'");
		sql.append(")v where '浙'||t1.vhic = v.group_vhic order by shangche asc ,vhic asc )p where rownum<=");
		sql.append(page.getPageSize()* page.getCurrentPage()+")where rn> " );
		sql.append( page.getPageSize()* (page.getCurrentPage() - 1) );
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				OperatingData b = new OperatingData();								
				b.setNumber(rs.getInt("rn"));
				b.setVhic(rs.getString("group_vhic"));
				b.setCertNo(rs.getString("yingyun"));
				b.setUpTaxi(rs.getString("shangche"));
				b.setDownTaxi(rs.getString("xiache"));
				b.setMoney(rs.getFloat("jine") / 100);				
				b.setDistance(rs.getFloat("jicheng")/10);
				b.setEmpty(rs.getFloat("kongshi")/10);
				b.setWaitTime(rs.getInt("denghou"));
				b.setType(rs.getString("jiaoyitype"));
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		return list;
	}
	//查询终端编号
	public List<Condition>findmdtno(){
		List<Condition>list=new ArrayList<Condition>();
		String sql="select distinct mdt_sub_type from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where mdt_sub_type is not null";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Condition c=new Condition();
				c.setMdtno(rs.getString("mdt_sub_type"));
				list.add(c);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据终端编号查询车辆
	public List<Vehicle>findVehicle(String mdtno){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where 1=1 ";
		if (mdtno!=null&&mdtno.length()>0) {
			sql+=" and mdt_sub_type ='"+mdtno+"'";
		}
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehino(rs.getString("vehi_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据终端类型查询车辆营运统计信息
	public List<OperatingData> getmdtno(Condition condition, PageHelper page,String data) {
		List<OperatingData> list = new ArrayList<OperatingData>();
		String sj=condition.getStartTime().substring(0,4);
		String[] sz=data.split(",");
		String area=sz[0];
		String comp=sz[1];
		StringBuffer sql = new StringBuffer("select * from(select p.*,rownum rn from(select t1.*,v1.*from ");
		sql.append("(select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st" +
				",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime," +
				"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance," +
				"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
				"sum((xiache-shangche)*60*24*60)time1");
		sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  where shangche>=to_date(");
		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
		sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
		if (condition.getMdtno() != null&&!condition.getMdtno().equals("0")){
			sql.append(" and v.mdt_sub_type='" + condition.getMdtno() + "' ");
		}
		if(!area.equals("所有公司")){
			sql.append(" and ba_name ='"+area+"'");
		}
		if (!comp.equals("所有分公司")) {
			sql.append(" and comp_name ='"+comp+"'");
		}
		if (condition.getCertNo()!=null&&condition.getCertNo().length()>0) {
			sql.append(" and v.vehi_no ='"+condition.getCertNo()+"'");
		}				
		sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no order by ba_name asc,comp_name asc,vehi_no asc )p where rownum<=");
		sql.append(page.getPageSize()* page.getCurrentPage()+") where rn> " );
		sql.append( page.getPageSize()* (page.getCurrentPage() - 1));
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				OperatingData b = new OperatingData();				
				b.setCompany(rs.getString("ba_name"));
				b.setBranch(rs.getString("comp_name"));				
				b.setNumber(rs.getInt("rn"));
				b.setVhic(rs.getString("vehi_no"));
				b.setMoney(rs.getFloat("st") / 100);
				b.setTimes(rs.getInt("ct"));				
				b.setDistance(rs.getFloat("distance")/10);
				b.setTimeOut(rs.getInt("time1"));
				b.setEmpty(rs.getFloat("empty1")/10);
				b.setWaitTime(rs.getInt("waitTime"));
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}


// 按终端获取数据总数量查询
public Integer mdtnoData(Condition condition,String data) {
	Integer t = 0;
	String[] sz=data.split(",");
	String area=sz[0];
	String sj=condition.getStartTime().substring(0,4);
	String comp=sz[1];
	StringBuffer sql = new StringBuffer("select * from (select distinct(vhic)from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where shangche>=to_date(");
	sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
	sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss'))t1,");
	sql.append("(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where 1=1");
	if (condition.getMdtno() != null&&!condition.getMdtno().equals("0")){
		sql.append(" and v.mdt_sub_type='" + condition.getMdtno() + "' ");
	}
	if(!area.equals("所有公司")){
		sql.append(" and ba_name ='"+area+"'");
	}
	if (!comp.equals("所有分公司")) {
		sql.append(" and comp_name ='"+comp+"'");
	}
	if (condition.getCertNo()!=null&&condition.getCertNo().length()>0) {
		sql.append(" and v.vehi_no ='"+condition.getCertNo()+"'");
	}			
	sql.append(")v1 where  '浙'||t1.vhic=v1.vehi_no");
	con = DataBese.getConnectionOfOracle();
	try {
		ps = con.prepareStatement(sql.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			
			++t;
		}
		rs.close();
		ps.close();
		con.close();
	} catch (SQLException e) {

		e.printStackTrace();
	}
	return t;
}
}