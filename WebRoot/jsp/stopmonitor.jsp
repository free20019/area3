
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%" >
<div style="height: 100%;float: left;width: 100%" id="lefttdstop">
		<div style="height: 65%;width: 100%" id="mapdivstop"> 
			<table border="0" style="height: 100%;width: 100%">
			<tr style="width: 98%;">
				<td style="width: 100%;height: 100%"><div id="stop-data-div" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
			</tr>
		</table>
		
		</div>
		<!-- 
		<div id="data-div" class="google_maps" style="height: 65%;width: 100%">&nbsp;</div>
		 -->
			<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar1stop() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utodstop"><input type="hidden" id="switchPoint1stop" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
			<!-- Widget ID (each widget will need unique ID)-->
			<div id="monitortabsstop"  style="height: 45%;width: 100%;">
			<a href="javascript:void(0);" class="btn btn-primary" onclick="freshstop();">刷   新</a>
				<div id="tabs-a" style="width: 100%;overflow: auto;height: 80%">
					<table id="dw_tablestop" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
							<thead>
								<tr>
									<th nowrap='nowrap' >序号</th>
									<th nowrap='nowrap'>车牌</th>
									<th nowrap='nowrap'>分公司</th>
									<th nowrap='nowrap'>Sim卡号</th>
									<th nowrap='nowrap'>车速(km/h)</th>
									<th nowrap='nowrap'>汇报时间</th>
									<!-- 
									<th nowrap='nowrap'>当前定位</th>
									 -->
								</tr>
							</thead>
							<tbody id="dw_datastop">
							</tbody> 
						</table>
				</div>
			</div>
</div>
</div>

<script type="text/javascript">
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	initializestop();
	querystop();
	// PAGE RELATED SCRIPTS

	
	/*
	 *  Maps Initialize
	 */
	var typestop=0;
	var mapObjstop;
	var markerstop="";
	var markerliststop = new Array();
	function initializestop() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		 mapObjstop=new AMap.Map("stop-data-div",{center:position,level:15,resizeEnable:true});//创建地图实例
		 mapObjstop.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
			  //加载工具条
			  tool=new AMap.ToolBar({
			    direction:false,//隐藏方向导航
			    ruler:false,//隐藏视野级别控制尺
			    autoPosition:false//禁止自动定位
			  });
			  mapObjstop.addControl(tool);
			  //加载鹰眼
			  view=new AMap.OverView();
			  mapObjstop.addControl(view);
			  //加载比例尺
			  scale=new AMap.Scale();
			  mapObjstop.addControl(scale);
			  mapObjstop.plugin(["AMap.MapType"], function() {
					var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
					mapObjstop.addControl(type);
				});
			});
	}

function freshstop(){
	querystop();
}

var vehiliststop;
function querystop(){
	$("#dw_datastop").html("<tr><td align='center' colspan='7'><img src='img/select2-spinner.gif' /></td></tr>");
	$.ajax({
		url : 'queryvhic30.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			vehiliststop = json.vehilist;
			mapObjstop.clearMap();
			markersstop= [];
			var trdata="";
			for(j=0;j<vehiliststop.length;j++){
				addmksstop(vehiliststop[j]);
				trdata+="<tr id='"+vehiliststop[j].vehino+"' ondblclick='monitorstop(this.id)'><td>"+(j+1)+"</td><td>"+vehiliststop[j].vehino+"</td><td>"+vehiliststop[j].compname+"</td><td>"+vehiliststop[j].simka+"</td><td>"+vehiliststop[j].speed+"</td><td>"+vehiliststop[j].dateTime+"</td></tr>";
				//<td>"+vehiliststop[j].address+"</td>
			}
			$("#dw_datastop").html(trdata);
			addClusterstop(1);
		},
		error:function(){
		}		
	});
}

var markersstop= [];
function addmksstop(obj){

	 var icon = '';
			if(obj.onoffstate=="1"){
				icon="img/car/c.png";   
			}else{
				icon="img/car/d.png";   
			}
	var marker1 = new AMap.Marker({
	    //map:mapObjstop,
	    position:new AMap.LngLat(obj.longi,obj.lati),     
	    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    icon: icon   //自定义点标记覆盖物内容
	});
	//marker1.setMap(mapObjhistory);  //在地图上添加点
	markersstop.push(marker1);
	 var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><br/><b>[GPS时间]</b>："+obj.dateTime
	  +"<br/><b>[所属公司]</b>："+obj.compname
		  +"<br/><b>[SIM卡号]</b>："+obj.simka
	  +"<br/><b>[GPS速度]</b>："+obj.speed
	  +"<br/><b>[经纬度]</b>："+obj.longi+","+obj.lati
	var info = [];                 
	info.push(txt);                 
	               
	var inforWindowstop = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(0,0),                 
	  content:info.join("<br>")                 
	});                 
	  AMap.event.addListener(marker1,"click",function(e){                 
		  inforWindowstop.open(mapObjstop,marker1.getPosition());                 
		});
}
var clusterstop;
function addClusterstop(tag)
{
	if(clusterstop) {	
		clusterstop.setMap(null);
	}
	if(tag==1) {
		var sts=[{url:"img/car/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
		mapObjstop.plugin(["AMap.MarkerClusterer"],function(){
			clusterstop = new AMap.MarkerClusterer(mapObjstop,markersstop,{minClusterSize:5,styles:sts});
		});
	}
	else {
		mapObjstop.plugin(["AMap.MarkerClusterer"],function(){
			clusterstop = new AMap.MarkerClusterer(mapObjstop,markersstop);
		});
	}
}

function monitorstop(obj){
	$.ajax({
		url : 'monitor.action',
		type : 'post',
		data:{
			"vehiid" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				var mdtstop = json.mdt;
				addMarkerstop(mdtstop);
		},
		error:function(){
			
		}		
	});
}

function addMarkerstop(obj){
	if(markerstop!=""){
		markerstop.setMap(null);
	}
//	ngeocoder(mdt.longi,mdt.lati);

	 var icon = ''
			if(obj.onoffstate=="1"){
				if(obj.carStatus=="0"){
					icon="img/car/c.png";   
				}else{
					icon="img/car/h.png";   
				}
			}else{
				icon="img/car/d.png";   
			}
	
	markerstop = new AMap.Marker({
	    map:mapObjstop,
	    position:new AMap.LngLat(obj.longi,obj.lati),     
	    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    icon:icon   //自定义点标记覆盖物内容
	});
	mapObjstop.setCenter(new AMap.LngLat(obj.longi,obj.lati));
	var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[SIM卡]</b>："+obj.vehisim+"<br/><b>[车辆所属人]</b>："+obj.ownname
					  +"<br/><b>[联系电话]</b>："+obj.owntel+"<br/><b>[经度]</b>："+obj.longi+"<br/><b>[纬度]</b>："+obj.lati+"<br/><b>[所在位置]</b>："+obj.address;
	var info = [];                 
	info.push(txt);                 
	               
	var inforWindowonestop = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(0,0),                 
	  content:info.join("<br>")                 
	});           
	  inforWindowonestop.open(mapObjstop,markerstop.getPosition());                 
	  AMap.event.addListener(markerstop,"click",function(e){                 
		  inforWindowonestop.open(mapObjstop,markerstop.getPosition());                 
		});
}
</script>
<script type="text/javascript" language="javascript">  
	function switchSysBar1stop(){
		if ($("#switchPoint1stop").val()=="3"){
			$("#switchPoint1stop").val("4");
			$("#monitortabsstop").css('display','none'); 
			 $("#mapdivstop").height("100%"); 
			$("#utodstop").attr("class", "fa fa-chevron-up");
		}
		else{
			$("#switchPoint1stop").val("3");
			$("#monitortabsstop").css('display','block');
			 $("#mapdivstop").height("65%"); 
			$("#utodstop").attr("class", "fa fa-chevron-down");
		}
	}

	function switchSysBarstop(){
		if ($("#switchPointstop").val()=="3"){
			$("#switchPointstop").val("4");
			$("#monirightstop").css('display','none'); 
			$("#lefttdstop").width("98%"); 
			$("#ltorstop").attr("class", "fa fa-chevron-left");
		}
		else{
			$("#switchPointstop").val("3");
			$("#monirightstop").css('display','block');
			$("#lefttdstop").width("74%"); 
			$("#ltorstop").attr("class", "fa fa-chevron-right");
		}
	}
</script>  

<style>
.txtstyle{position:relative;background: rgba(255,204,51, 0.5);}
.txtstyle span{
white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #FFCC66;
	color:black;
	width:auto;
	border:1px solid #ffffff;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;
}
td,th{ nowrap:nowrap;}
</style>
<script language="javascript" type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>  
	<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script> 
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">