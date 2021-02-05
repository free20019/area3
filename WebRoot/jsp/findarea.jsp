﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<!-- widget div-->
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="zdjkclsl">出租汽车重点监控区域车辆数量分析</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:160px;float:left;">
										<label class="select">
											<select name="quyu" id="quyu">
												<option value="0">所有区域</option>
											</select> <i></i> </label>
									</section>
									<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="time" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
									</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="findarea();">查   询</a>
								<a href="javascript:void(1);" style="float:center;" class="btn btn-primary"  onclick="areadaochu();">导   出</a><span id="areadaochu"></span>
						</form>
						
						<table id="area" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th id="chaxun" style='background:#aac8ea' nowrap="nowrap"></th>
									<th>0:00</th>
									<th>0:30</th>
									<th>1:00</th>
									<th>1:30</th>
									<th>2:00</th>
									<th>2:30</th>
									<th>3:00</th>
									<th>3:30</th>
									<th>4:00</th>
									<th>4:30</th>
									<th>5:00</th>
									<th>5:30</th>
									<th>6:00</th>
									<th>6:30</th>
									<th>7:00</th>
									<th>7:30</th>
									<th>8:00</th>
									<th>8:30</th>
									<th>9:00</th>
									<th>9:30</th>
									<th>10:00</th>
									<th>10:30</th>
									<th>11:00</th>
									<th>11:30</th>
									<th>12:00</th>
									<th>12:30</th>
									<th>13:00</th>
									<th>13:30</th>
									<th>14:00</th>
									<th>14:30</th>
									<th>15:00</th>
									<th>15:30</th>
									<th>16:00</th>
									<th>16:30</th>
									<th>17:00</th>
									<th>17:30</th>
									<th>18:00</th>
									<th>18:30</th>
									<th>19:00</th>
									<th>19:30</th>
									<th>20:00</th>
									<th>20:30</th>
									<th>21:00</th>
									<th>21:30</th>
									<th>22:00</th>
									<th>22:30</th>
									<th>23:00</th>
									<th>23:30</th>
								</tr>
							</thead>
							<tbody id="areatbody">
							</tbody>
						</table>

							<section id="widget-grid" class="">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div>
												<div class="jarviswidget-editbox">
												</div>
												<div class="widget-body no-padding">
												</br><input type="checkbox" onclick="findarea();" name="areatime" value="0">上年同比
													<input type="checkbox" onclick="findarea();" name="areatime" value="1">上月同比
													<input type="checkbox" onclick="findarea();" name="areatime" value="2" checked >前半月最小
													<input type="checkbox" onclick="findarea();" name="areatime" value="3" checked >前半月最大
													<input type="checkbox" onclick="findarea();" name="areatime" value="4">上周平均
													<input type="checkbox" onclick="findarea();" name="areatime" value="5">上周同比
													<input type="checkbox" onclick="findarea();" name="areatime" value="6">前天
													<input type="checkbox" onclick="findarea();" name="areatime" value="7">昨天
													<input type="checkbox" onclick="findarea();" name="areatime" value="8" checked >今天
													<input type="checkbox" onclick="findarea();" name="areatimeq" value="0">全部
													<div id="areatu" class="chart has-legend"></div>
											</div>
										</div>
									</article>
							</section>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
		$("#time").val(setformatnewlc(smydate));
//		$("#START_TIME2").val(y+"-01-01");
	});
	function setformatnewlc(obj){
		var y=(obj.getFullYear()).toString();
		var m=(obj.getMonth()+1).toString();
		if(m.length==1){
			m="0"+m;
		}
		var d=obj.getDate().toString();
		if(d.length==1){
			d="0"+d;
		}
		var h = obj.getHours().toString();
		if(h.length==1){
			h="0"+h;
		}
		var mi = obj.getMinutes().toString();
		if(mi.length==1){
			mi="0"+mi;
		}
		var s = obj.getSeconds().toString();
		if(s.length==1){
			s="0"+s;
		}
		var time=y+"-"+m+"-"+d; 
		return time;
	}
	findquyu();
	findarea();
	function findquyu(){
		$.ajax({
			url : 'area.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list;
					for(var i=0;i<area.length;i++){
						$("#quyu").append("<option value='"+area[i].area_id+"' >"+area[i].area_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	var $chrt_border_color = "#21ede6";
	var $chrt_grid_color = "#1fda88"
	var $chrt_main = "#1f89da";			/* red       */
	var $chrt_second = "#CCFF99";
	function findarea(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		var q=$("#quyu").find("option:selected").text();
		$("#chaxun").html(q);
			$.ajax({
			url : 'findarea.action',
			type : 'post',
			data:{
				"quyu" : $("#quyu").val(),
				"time" : $("#time").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.area.jg[0][0].all;
					var bb = json.area.jg[1][0].all;
					var cc = json.area.jg[2][0].all;
					var dd = json.area.jg[3][0].all;
					var ee = json.area.jg[4][0].all;
					var ff = json.area.jg[5][0].all;
					var gg = json.cs[0];
					var hh = json.halfMonth.areavhicmax;
					var ii = json.halfMonth.areavhicmin;
					var day=[];
					var arean=[];
					var areay=[];
					var areas=[];
					var areaq=[];
					var areaz=[];
					var areaj=[];
					var areap=[];
					var areamax=[];
					var areamin=[];
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<ff.length;i++){
						if(ff[i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
						}else{
						tab+="<td style='background:#aac8ea'>"+ff[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<ff.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ff[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ff[i]+"]");
							areaj.push(yy1);
							}
					}
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<ee.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ee[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ee[i]+"]");
							areaz.push(yy1);
							}
					}
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd.length;i++){
					if(dd[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<dd.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(dd[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(dd[i]+"]");
							areaq.push(yy1);
							}
					}
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc.length;i++){
					if(cc[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+cc[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<cc.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(cc[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(cc[i]+"]");
							areas.push(yy1);
							}
					}
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<gg.sj.length;i++){
				 		if(gg.sj[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+gg.sj[i]+"</td>";
						} 
					}
					tab+="</tr>";
					for(var i=0;i<gg.sj.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(gg.sj[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(gg.sj[i]+"]");
							areap.push(yy1);
							}
					}
					tab+="<tr><td nowrap='nowrap'>前半月最大</td>";
					if(hh!=null&&hh.length>0){
					for(var i=0;i<hh.length;i++){
					if(hh[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+hh[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<hh.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(hh[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(hh[i]);
							areamax.push(yy1+"]");
							}
					}
					}
					tab+="<tr><td nowrap='nowrap'>前半月最小</td>";
					if(ii!=null&&ii.length>0){
					for(var i=0;i<ii.length;i++){
					if(ii[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+ii[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<ii.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ii[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ii[i]);
							areamin.push(yy1+"]");
							}
					}
					}
					tab+="<tr><td nowrap='nowrap'>上月同比</td>";
					for(var i=0;i<bb.length;i++){
					if(bb[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+bb[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<bb.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(bb[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(bb[i]);
							areay.push(yy1+"]");
							}
					}
					tab+="<tr><td nowrap='nowrap'>上年同比</td>";
					for(var i=0;i<aa.length;i++){
					if(aa[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+aa[i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<aa.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(aa[i]!="0"){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(aa[i]+"]");
							arean.push(yy1);
							}
					}
					$("#areatbody").html(tab);
					var str=document.getElementsByName("areatime");
					var objarray=str.length;
					var chestr="";
					for (i=0;i<objarray;i++){
					  if(str[i].checked == false)
					  {
					   chestr+=str[i].value+",";
					  }
					}
					var str1=document.getElementsByName("areatimeq");
					var chestr1="";
					  if(str1[0].checked == true){
					   chestr1=str1[0].value;
					  }
					  if(chestr1=='0'){
					  	chestr='';
					  	 $("[name=areatime]:checkbox").prop('checked', 'checked');
					  }
					  if(chestr.split(',').length>0){
					  	$("[name=areatimeq]:checkbox").attr('checked', false);
					  }
					var areatime=chestr.split(',');
					for(var i=0;i<areatime.length;i++){
						if(areatime[i]=="0"){
							arean.length=0;
						}
						if(areatime[i]=="1"){
							areay.length=0;
						}
						if(areatime[i]=="2"){
							areamin.length=0;
						}
						if(areatime[i]=="3"){
							areamax.length=0;
						}
						if(areatime[i]=="4"){
							areap.length=0;
						}
						if(areatime[i]=="5"){
							areas.length=0;
						}
						if(areatime[i]=="6"){
							areaq.length=0;
						}
						if(areatime[i]=="7"){
							areaz.length=0;
						}
						if(areatime[i]=="8"){
							areaj.length=0;
						}
					}
					
		generateAllFlotChartsarea(arean,areay,areas,areaq,areaz,areaj,areap,areamax,areamin);
			},
			error:function(){
		$.unblockUI();
				
			}		
		});
	}
	
	function generateAllFlotChartsarea(arean,areay,areas,areaq,areaz,areaj,areap,areamax,areamin) {
		if ($("#areatu").length) {
		$.unblockUI();
			//console.log(pageviews)
			var plot = $.plot($("#areatu"), [{
				data : eval( "["+arean+"]" ),
				label : "上年同比"
			}, {
				data : eval( "["+areay+"]" ),
				label : "上月同比"
			},{
				data : eval( "["+areamin+"]" ),
				label : "前半月最小"
			},{
				data : eval( "["+areamax+"]" ),
				label : "前半月最大"
			},{
				data : eval( "["+areas+"]" ),
				label : "上周同比"
			},{
				data : eval( "["+areaq+"]" ),
				label : "前天"
			},{
				data : eval( "["+areaz+"]" ),
				label : "昨天"
			},{
				data : eval( "["+areaj+"]" ),
				label : "今天"
			},{
				data : eval( "["+areap+"]" ),
				label : "上周平均"
			}], {
				series : {
					lines : {
						show : true,
						lineWidth : 1,
						fill : true,
						fillColor : {
							colors : [{
								opacity : 0
							}, {
								opacity : 0
							}]
						}
					},
					points : {
						show : true
					},
					shadowSize : 0
				},
				xaxis : {
					mode : "time",
					tickLength : 10
				},
	
				yaxes : [{
					min : 0,
					tickLength : 5
				}],
				grid : {
					hoverable : true,
					clickable : true,
					tickColor : $chrt_border_color,
					borderWidth : 0,
					borderColor : $chrt_border_color,
				},
				tooltip : true,
				tooltipOpts : {
					content : "%s  <b>%x</b>时 监控点车辆对比分析 %y ",
				},
				colors : [$chrt_main, $chrt_second],
				xaxis : {
					ticks : 15,
					tickDecimals : 2
				},
				yaxis : {
					ticks : 15,
					tickDecimals : 0
				},
			});
		}
				
	}
	function aa(){
		alert(1);
	}
	
	//导出excle
	function areadaochu(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findareaexcle.action',
		type : 'post',
		data:{
		"quyu" : $("#quyu").val(),
		"time" :$("#time").val()
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
$("#zdjkclsl").html("出租汽车重点监控区域车辆数量分析");
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

	var areas="";
	function loadDataTableScripts_area() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_area);
		}

	}

	function runDataTables_area() {

		/*
		 * BASIC
		 */
		areas=$('#area').dataTable({
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
<script src="js/plugin/flot/jquery.flot.cust.js"></script>
<script src="js/plugin/flot/jquery.flot.resize.js"></script>
<script src="js/plugin/flot/jquery.flot.fillbetween.js"></script>
<script src="js/plugin/flot/jquery.flot.orderBar.js"></script>
<script src="js/plugin/flot/jquery.flot.pie.js"></script>
<script src="js/plugin/flot/jquery.flot.tooltip.js"></script>
<style type="text/css">

.button{
width: 50px;
line-height: 20px;
text-align: center;
font-weight: bold;
color: #fff;
text-shadow:1px 1px 1px #333;
border-radius: 5px;
margin:0 2px 2px 0;
position: relative;
overflow: hidden;
}

.button.gray{
color: #8c96a0;
text-shadow:1px 1px 1px #fff;
border:1px solid #dce1e6;
box-shadow: 0 1px 2px #fff inset,0 -1px 0 #a8abae inset;
background: -webkit-linear-gradient(top,#f2f3f7,#e4e8ec);
background: -moz-linear-gradient(top,#f2f3f7,#e4e8ec);
background: linear-gradient(top,#f2f3f7,#e4e8ec);
}

</style>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
