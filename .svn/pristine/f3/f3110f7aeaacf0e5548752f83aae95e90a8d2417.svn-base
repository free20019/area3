<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/echarts/echarts.js"></script>
<script src="js/echarts/bar.js"></script>
<script src="js/echarts/line.js"></script>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="zdqyxdkz">杭州出租车重点区域服务车次(每小时统计)</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
					<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="zdqyxskztime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择时间--"/>
										</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findzdqyxskz();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findzdqyxskzexcle();">导   出</a><span id="zdqyxskzdaochu"></span>
						</form>
						<table id="zdqyxskztable"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap='nowrap'>序号</th>
									<th style="text-align: center;">区域</th>
									<th>类别</th>
									<th>0:00</th>
									<th>1:00</th>
									<th>2:00</th>
									<th>3:00</th>
									<th>4:00</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="zdqyxskztbody">
							</tbody>
						</table>
					</div>
				</div>
				<div id="zdqyxskztb" class="chart has-legend"></div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	var myDate = new Date();
	var a=myDate.getFullYear();
	var b=myDate.getMonth()+1+"";
	if(b.length<2){
		b="0"+b;
	}
	var day = myDate.getDate();
	if(day.length<2){
		day="0"+day;
	}
	$("#zdqyxskztime").val(a+"-"+b+"-"+day);
	findzdqyxskz();
	//loadDataTableScripts_zdqyxskztable();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	
	
function findzdqyxskz(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findzdqyxskz.action',
			type : 'post',
			data:{
				"time" : $("#zdqyxskztime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var fx = json.list;
				var tab="";
				var i=0;
				var beizhan=[];
				var xizhan=[];
				var jichang=[];
				var dongzhan=[];
				var chengzhan=[];
				var keyun=[];
				var nanzhan=[];
				if(fx!=null){
					for(i=0;i<fx.length;i++){
						if(fx[i].area=='杭州汽车北站'){
							beizhan.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							beizhan.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							beizhan.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							beizhan.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							beizhan.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							beizhan.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							beizhan.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							beizhan.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							beizhan.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							beizhan.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							beizhan.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							beizhan.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							beizhan.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							beizhan.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							beizhan.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							beizhan.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							beizhan.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							beizhan.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							beizhan.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							beizhan.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							beizhan.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							beizhan.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							beizhan.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							beizhan.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='杭州汽车西站'){
							xizhan.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							xizhan.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							xizhan.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							xizhan.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							xizhan.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							xizhan.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							xizhan.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							xizhan.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							xizhan.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							xizhan.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							xizhan.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							xizhan.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							xizhan.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							xizhan.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							xizhan.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							xizhan.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							xizhan.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							xizhan.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							xizhan.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							xizhan.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							xizhan.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							xizhan.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							xizhan.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							xizhan.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='机场'){
							jichang.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							jichang.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							jichang.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							jichang.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							jichang.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							jichang.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							jichang.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							jichang.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							jichang.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							jichang.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							jichang.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							jichang.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							jichang.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							jichang.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							jichang.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							jichang.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							jichang.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							jichang.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							jichang.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							jichang.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							jichang.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							jichang.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							jichang.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							jichang.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='火车东站'){
							dongzhan.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							dongzhan.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							dongzhan.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							dongzhan.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							dongzhan.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							dongzhan.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							dongzhan.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							dongzhan.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							dongzhan.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							dongzhan.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							dongzhan.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							dongzhan.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							dongzhan.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							dongzhan.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							dongzhan.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							dongzhan.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							dongzhan.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							dongzhan.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							dongzhan.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							dongzhan.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							dongzhan.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							dongzhan.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							dongzhan.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							dongzhan.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='杭州城站'){
							chengzhan.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							chengzhan.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							chengzhan.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							chengzhan.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							chengzhan.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							chengzhan.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							chengzhan.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							chengzhan.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							chengzhan.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							chengzhan.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							chengzhan.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							chengzhan.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							chengzhan.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							chengzhan.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							chengzhan.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							chengzhan.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							chengzhan.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							chengzhan.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							chengzhan.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							chengzhan.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							chengzhan.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							chengzhan.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							chengzhan.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							chengzhan.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='杭州汽车客运中心站'){
							keyun.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							keyun.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							keyun.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							keyun.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							keyun.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							keyun.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							keyun.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							keyun.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							keyun.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							keyun.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							keyun.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							keyun.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							keyun.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							keyun.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							keyun.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							keyun.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							keyun.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							keyun.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							keyun.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							keyun.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							keyun.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							keyun.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							keyun.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							keyun.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}else if(fx[i].area=='杭州汽车南站'){
							nanzhan.push((parseInt((fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00))));
							nanzhan.push(((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01))));
							nanzhan.push(((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02))));
							nanzhan.push(((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03))));
							nanzhan.push(((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04))));
							nanzhan.push(((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05))));
							nanzhan.push(((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06))));
							nanzhan.push(((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07))));
							nanzhan.push(((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08))));
							nanzhan.push(((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09))));
							nanzhan.push(((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10))));
							nanzhan.push(((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11))));
							nanzhan.push(((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12))));
							nanzhan.push(((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13))));
							nanzhan.push(((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14))));
							nanzhan.push(((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15))));
							nanzhan.push(((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16))));
							nanzhan.push(((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17))));
							nanzhan.push(((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18))));
							nanzhan.push(((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19))));
							nanzhan.push(((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20))));
							nanzhan.push(((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21))));
							nanzhan.push(((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22))));
							nanzhan.push(((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23))));
						}
						tab=tab+"<tr><td nowrap='nowrap' rowspan='3' style='vertical-align:middle;text-align: center;'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap' rowspan='3'style='vertical-align:middle;text-align: center;'>&nbsp;"+fx[i].area+"</td>";
						tab=tab+"<td nowrap='nowrap'>总数</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a00)+parseInt(fx[i].b00))==0?"":(parseInt(fx[i].a00)+parseInt(fx[i].b00)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a01)+parseInt(fx[i].b01))==0?"":(parseInt(fx[i].a01)+parseInt(fx[i].b01)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a02)+parseInt(fx[i].b02))==0?"":(parseInt(fx[i].a02)+parseInt(fx[i].b02)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a03)+parseInt(fx[i].b03))==0?"":(parseInt(fx[i].a03)+parseInt(fx[i].b03)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a04)+parseInt(fx[i].b04))==0?"":(parseInt(fx[i].a04)+parseInt(fx[i].b04)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a05)+parseInt(fx[i].b05))==0?"":(parseInt(fx[i].a05)+parseInt(fx[i].b05)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a06)+parseInt(fx[i].b06))==0?"":(parseInt(fx[i].a06)+parseInt(fx[i].b06)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a07)+parseInt(fx[i].b07))==0?"":(parseInt(fx[i].a07)+parseInt(fx[i].b07)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a08)+parseInt(fx[i].b08))==0?"":(parseInt(fx[i].a08)+parseInt(fx[i].b08)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a09)+parseInt(fx[i].b09))==0?"":(parseInt(fx[i].a09)+parseInt(fx[i].b09)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a10)+parseInt(fx[i].b10))==0?"":(parseInt(fx[i].a10)+parseInt(fx[i].b10)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a11)+parseInt(fx[i].b11))==0?"":(parseInt(fx[i].a11)+parseInt(fx[i].b11)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a12)+parseInt(fx[i].b12))==0?"":(parseInt(fx[i].a12)+parseInt(fx[i].b12)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a13)+parseInt(fx[i].b13))==0?"":(parseInt(fx[i].a13)+parseInt(fx[i].b13)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a14)+parseInt(fx[i].b14))==0?"":(parseInt(fx[i].a14)+parseInt(fx[i].b14)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a15)+parseInt(fx[i].b15))==0?"":(parseInt(fx[i].a15)+parseInt(fx[i].b15)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a16)+parseInt(fx[i].b16))==0?"":(parseInt(fx[i].a16)+parseInt(fx[i].b16)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a17)+parseInt(fx[i].b17))==0?"":(parseInt(fx[i].a17)+parseInt(fx[i].b17)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a18)+parseInt(fx[i].b18))==0?"":(parseInt(fx[i].a18)+parseInt(fx[i].b18)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a19)+parseInt(fx[i].b19))==0?"":(parseInt(fx[i].a19)+parseInt(fx[i].b19)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a20)+parseInt(fx[i].b20))==0?"":(parseInt(fx[i].a20)+parseInt(fx[i].b20)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a21)+parseInt(fx[i].b21))==0?"":(parseInt(fx[i].a21)+parseInt(fx[i].b21)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a22)+parseInt(fx[i].b22))==0?"":(parseInt(fx[i].a22)+parseInt(fx[i].b22)))+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+((parseInt(fx[i].a23)+parseInt(fx[i].b23))==0?"":(parseInt(fx[i].a23)+parseInt(fx[i].b23)))+"</td></tr>";
						tab=tab+"<tr><td nowrap='nowrap'>下客</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a00==0.01?"":fx[i].a00)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a01==0.01?"":fx[i].a01)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a02==0.01?"":fx[i].a02)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a03==0.01?"":fx[i].a03)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a04==0.01?"":fx[i].a04)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a05==0.01?"":fx[i].a05)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a06==0.01?"":fx[i].a06)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a07==0.01?"":fx[i].a07)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a08==0.01?"":fx[i].a08)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a09==0.01?"":fx[i].a09)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a10==0.01?"":fx[i].a10)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a11==0.01?"":fx[i].a11)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a12==0.01?"":fx[i].a12)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a13==0.01?"":fx[i].a13)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a14==0.01?"":fx[i].a14)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a15==0.01?"":fx[i].a15)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a16==0.01?"":fx[i].a16)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a17==0.01?"":fx[i].a17)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a18==0.01?"":fx[i].a18)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a19==0.01?"":fx[i].a19)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a20==0.01?"":fx[i].a20)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a21==0.01?"":fx[i].a21)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a22==0.01?"":fx[i].a22)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].a23==0.01?"":fx[i].a23)+"</td></tr>";
						tab=tab+"<tr><td nowrap='nowrap'>上客</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b00==0.01?"":fx[i].b00)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b01==0.01?"":fx[i].b01)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b02==0.01?"":fx[i].b02)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b03==0.01?"":fx[i].b03)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b04==0.01?"":fx[i].b04)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b05==0.01?"":fx[i].b05)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b06==0.01?"":fx[i].b06)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b07==0.01?"":fx[i].b07)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b08==0.01?"":fx[i].b08)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b09==0.01?"":fx[i].b09)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b10==0.01?"":fx[i].b10)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b11==0.01?"":fx[i].b11)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b12==0.01?"":fx[i].b12)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b13==0.01?"":fx[i].b13)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b14==0.01?"":fx[i].b14)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b15==0.01?"":fx[i].b15)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b16==0.01?"":fx[i].b16)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b17==0.01?"":fx[i].b17)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b18==0.01?"":fx[i].b18)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b19==0.01?"":fx[i].b19)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b20==0.01?"":fx[i].b20)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b21==0.01?"":fx[i].b21)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b22==0.01?"":fx[i].b22)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+(fx[i].b23==0.01?"":fx[i].b23)+"</td></tr>";
					}
						zdqyxskztb(beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan);
						$("#zdqyxskztbody").html(tab);
	$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}

function zdqyxskztb(beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan){
		  // 使用
         require(
            [
                'echarts',
                'echarts/chart/line', // 
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('zdqyxskztb')); 
			
              option = {
              grid:{
					x:40,y:10,x2:10,y2:30
				},
			    title : {
			        text: '',
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['汽车北站','汽车西站','机场','火车东站','城站','客运中心','汽车南站']
			    },
			    toolbox: {
			        show : false,
			       
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			        {
			            name:'汽车北站',
			            type:'line',
			            data:beizhan,
			        },
			        {
			            name:'汽车西站',
			            type:'line',
			            data:xizhan,
			        },
			        {
			            name:'机场',
			            type:'line',
			            data:jichang,
			        },
			        {
			            name:'火车东站',
			            type:'line',
			            data:dongzhan,
			        },
			        {
			            name:'城站',
			            type:'line',
			            data:chengzhan,
			        },
			        {
			            name:'客运中心',
			            type:'line',
			            data:keyun,
			        },
			        {
			            name:'汽车南站',
			            type:'line',
			            data:nanzhan,
			        }
			    ]
			};
      
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
	}


//导出excle
	function findzdqyxskzexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findzdqyxskzexcle.action',
		type : 'post',
		data:{
			"time" : $("#zdqyxskztime").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			window.location.href=json.action;
			$.unblockUI();
		},
		error:function(){
			
		}
		});
	}
}
$("#zdqyxskz").html("杭州出租车重点区域服务车次(每小时统计)");
//树
	$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
	$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(':visible')) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
		}
		e.stopPropagation();
	});
		
	if($('.DTTT_dropdown.dropdown-menu').length){
		$('.DTTT_dropdown.dropdown-menu').remove();
	}

	var zdqyxskztables="";
	function loadDataTableScripts_zdqyxskztable() {

		loadScript("js/plugin/datatables/jquery.dataTables-cust.min.js", dt_2);

		function dt_2() {
			loadScript("js/plugin/datatables/ColReorder.min.js", dt_3);
		}

		function dt_3() {
			loadScript("js/plugin/datatables/FixedColumns.min.js", dt_4);
		}

		function dt_4() {
			loadScript("js/plugin/datatables/ColVis.min.js", dt_5);
		}

		function dt_5() {
			loadScript("js/plugin/datatables/ZeroClipboard.js", dt_6);
		}

		function dt_6() {
			loadScript("js/plugin/datatables/media/js/TableTools.min.js", dt_7);
		}

		function dt_7() {
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_zdqyxskztable);
		}

	}

	function runDataTables_zdqyxskztable() {

		/*
		 * BASIC
		 */
		zdqyxskztables=$('#zdqyxskztable').dataTable({
			"sPaginationType" : "bootstrap_full",
			"bRetrieve": true
		});

		/* END BASIC */

		/* Add the events etc before DataTables hides a column */
		$("#datatable_fixed_column thead input").keyup(function() {
			oTable.fnFilter(this.value, oTable.oApi._fnVisibleToColumnIndex(oTable.fnSettings(), $("thead input").index(this)));
		});

		$("#datatable_fixed_column thead input").each(function(i) {
			this.initVal = this.value;
		});
		$("#datatable_fixed_column thead input").focus(function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		});
		$("#datatable_fixed_column thead input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = this.initVal;
			}
		});		
		

		var oTable = $('#datatable_fixed_column').dataTable({
			"sDom" : "<'dt-top-row'><'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			//"sDom" : "t<'row dt-wrapper'<'col-sm-6'i><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'>>",
			"oLanguage" : {
				"sSearch" : "Search all columns:"
			},
			"bSortCellsTop" : true
		});		
		


		/*
		 * COL ORDER
		 */
		$('#datatable_col_reorder').dataTable({
			"sPaginationType" : "bootstrap",
			"sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"fnInitComplete" : function(oSettings, json) {
				$('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
			}
		});
		
		/* END COL ORDER */

		/* TABLE TOOLS */
		$('#datatable_tabletools').dataTable({
			"sDom" : "<'dt-top-row'Tlf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"oTableTools" : {
				"aButtons" : ["copy", "print", {
					"sExtends" : "collection",
					"sButtonText" : 'Save <span class="caret" />',
					"aButtons" : ["csv", "xls", "pdf"]
				}],
				"sSwfPath" : "js/plugin/datatables/media/swf/copy_csv_xls_pdf.swf"
			},
			"fnInitComplete" : function(oSettings, json) {
				$(this).closest('#dt_table_tools_wrapper').find('.DTTT.btn-group').addClass('table_tools_group').children('a.btn').each(function() {
					$(this).addClass('btn-sm btn-default');
				});
			}
		});
		
		/* END TABLE TOOLS */
	}
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
