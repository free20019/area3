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
					<h2 id="dcpjyycsfx">出租汽车单车平均营运次数分析</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="yycishutime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
									</section>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="findyycishu();">查   询   </a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="yycishudaochu();">导   出</a><span id="yycishudaochu"></span>
						</form>
						
						<table id="yycishu" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">营运次数(时/次)</th>
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
							<tbody id="yycishutbody">
							</tbody>
						</table>

<section id="widget-grid" class="">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						</br><input type="checkbox" onclick="findyycishu();" name="yycishu" value="0" >上年同比
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="1" >上月同比
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="2" checked >前半月最小
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="3" checked >前半月最大
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="4" />上周平均
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="5">上周同比
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="6">前天
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="7">昨天
													<input type="checkbox" onclick="findyycishu();" name="yycishu" value="8" checked >今天
													<input type="checkbox" onclick="findyycishu();" name="yycishuq" value="0">全部
							<div id="yycishutu" class="chart has-legend"></div>
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
		$("#yycishutime").val(setformatnewlc(smydate));
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
	findyycishu();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	var $chrt_border_color = "#21ede6";
	var $chrt_grid_color = "#1fda88"
	var $chrt_main = "#1f89da";			/* red       */
	var $chrt_second = "#CCFF99";
	function findyycishu(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findyycishu.action',
			type : 'post',
			data:{
				"time" : $("#yycishutime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.yy[0];
					var bb = json.loadonline.yy[1];
					var cc = json.loadonline.yy[2];
					var dd = json.loadonline.yy[3];
					var ee = json.loadonline.yy[4];
					var ff = json.loadonline.yy[5];
					var gg = json.list1[0].pjyingyun;
					var hh = json.loadonline.my
					var ii = json.halfMonth.timesmax;
					var jj = json.halfMonth.timesmin;
					var day=[];
					var yingyunn=[];
					var yingyuny=[];
					var yingyuns=[];
					var yingyunq=[];
					var yingyunz=[];
					var yingyunj=[];
					var yingyunp=[];
					var yymax=[];
					var yymin=[];
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					if(ff.length>20){
					for(var i=0;i<ff.length;i++){
						tab+="<td style='background:#aac8ea'>"+ff[i].run_times+"</td>";
					}
					tab+="</tr>";
					}else{
					for(var i=0;i<ff.length;i++){
						tab+="<td style='background:#aac8ea'>"+ff[i].run_times+"</td>";
					}
					for(var i=0;i<hh.length;i++){
						tab+="<td style='background:#aac8ea'><span style='color:Red'>"+hh[i]+"</span></td>";
					}
					tab+="</tr>";
					}
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee.length;i++){
						tab+="<td>"+ee[i].run_times+"</td>";
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd.length;i++){
						tab+="<td>"+dd[i].run_times+"</td>";
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc.length;i++){
						tab+="<td>"+cc[i].run_times+"</td>";
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<gg.length;i++){
						if(gg[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+gg[i]+"</td>";
						} 
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前半月最大</td>";
					if(ii!=null){
					for(var i=0;i<ii.length;i++){
						if(ii[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+ii[i]+"</td>";
						} 
					}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前半月最小</td>";
					if(jj!=null){
					for(var i=0;i<jj.length;i++){
						if(jj[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+jj[i]+"</td>";
						} 
					}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上月同比</td>";
					if($("#yycishutime").val().substring(5,10)=='03-29'||$("#yycishutime").val().substring(5,10)=='03-30'||$("#yycishutime").val().substring(5,10)=='03-31'){
						for(var i=0;i<24;i++){
								tab+="<td>--</td>";
						}
					}else{
						for(var i=0;i<bb.length;i++){
						if(bb[i].run_times==null){
							tab+="<td></td>";
						}else{
							tab+="<td>"+bb[i].run_times+"</td>";
						}}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上年同比</td>";
					for(var i=0;i<aa.length;i++){
						tab+="<td>"+aa[i].run_times+"</td>";
					}
					tab+="</tr>";
					for(var i=0;i<aa.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(aa[i].run_times+"]");
							yingyunn.push(yy1);
					}
					for(var i=0;i<bb.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(bb[i].run_times+"]");
							yingyuny.push(yy1);
					}
					for(var i=0;i<cc.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(cc[i].run_times+"]");
							yingyuns.push(yy1);
					}
					for(var i=0;i<dd.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(dd[i].run_times+"]");
							yingyunq.push(yy1);
					}
					for(var i=0;i<ee.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ee[i].run_times+"]");
							yingyunz.push(yy1);
					}
					for(var i=0;i<ff.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ff[i].run_times+"]");
							yingyunj.push(yy1);
					}
					for(var i=0;i<gg.length;i++){
					var j=i+1;
						var yy1=[];
								yy1.push("["+j);
								yy1.push(gg[i]+"]");
							yingyunp.push(yy1);
					}
					for(var i=0;i<ii.length;i++){
					var j=i+1;
						if(ii[i].length>1){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(ii[i]+"]" );
							yymax.push(gps1);
						}
					}
					for(var i=0;i<jj.length;i++){
					var j=i+1;
						if(jj[i].length>1){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(jj[i]+"]" );
							yymin.push(gps1);
						}
					}
					$("#yycishutbody").html(tab);
		$.unblockUI();
		var str=document.getElementsByName("yycishu");
					var objarray=str.length;
					var chestr="";
					for (i=0;i<objarray;i++){
					  if(str[i].checked == false)
					  {
					   chestr+=str[i].value+",";
					  }
					}
					var str1=document.getElementsByName("yycishuq");
					var chestr1="";
					  if(str1[0].checked == true){
					   chestr1=str1[0].value;
					  }
					  if(chestr1=='0'){
					  	chestr='';
					  	 $("[name=yycishu]:checkbox").prop('checked', 'checked');
					  }
					  if(chestr.split(',').length>0){
					  	$("[name=yycishuq]:checkbox").attr('checked', false);
					  }
					var onlinetime=chestr.split(',');
					for(var i=0;i<onlinetime.length;i++){
						if(onlinetime[i]=="0"){
							yingyunn.length=0;
						}
						if(onlinetime[i]=="1"){
							yingyuny.length=0;
						}
						if(onlinetime[i]=="2"){
							yymin.length=0;
						}
						if(onlinetime[i]=="3"){
							yymax.length=0;
						}
						if(onlinetime[i]=="4"){
							yingyunp.length=0;
						}
						if(onlinetime[i]=="5"){
							yingyuns.length=0;
						}
						if(onlinetime[i]=="6"){
							yingyunq.length=0;
						}
						if(onlinetime[i]=="7"){
							yingyunz.length=0;
						}
						if(onlinetime[i]=="8"){
							yingyunj.length=0;
						}
					}
					generateAllFlotChartsyycishu(yingyunn,yingyuny,yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yymax,yymin);
					loadDataTableScripts_yycishu();
			},
			error:function(){
				$.unblockUI();
			}		
		});
	}
	
	function generateAllFlotChartsyycishu(yingyunn,yingyuny,yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yymax,yymin){
		if ($("#yycishutu").length) {
			var yyn =eval( "["+yingyunn+"]" );
			var yyy=eval( "["+yingyuny+"]" );
			var yys=eval( "["+yingyuns+"]" );
			var yyq=eval( "["+yingyunq+"]" );
			var yyz=eval( "["+yingyunz+"]" );
			var yyj=eval( "["+yingyunj+"]" );
			var yyp=eval( "["+yingyunp+"]" );
			var plot = $.plot($("#yycishutu"), [{
				data : yyn,
				label : "上年同比"
			}, {
				data : yyy,
				label : "上月同比"
			},{
				data : yys,
				label : "上周同比"
			},{
				data : yyq,
				label : "前天"
			},{
				data : yyz,
				label : "昨天"
			},{
				data : yyj,
				label : "今天"
			},{
				data : yyp,
				label : "上周平均"
			},{
				data : eval( "["+yymax+"]" ),
				label : "前半月最大"
			},{
				data : eval( "["+yymin+"]" ),
				label : "前半月最小"
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
					tickLength : 1
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
					content : "%s  <b>%x</b>时 营运次数 %y ",
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
	
	//导出excle
	function yycishudaochu(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findyycishuexcle.action',
		type : 'post',
		data:{
		"time" : $("#yycishutime").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			window.location.href=json.action;
		},
		error:function(){
			
		}
		});
	}
}

$("#dcpjyycsfx").html("出租汽车单车平均营运次数分析");
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

	var yycishus="";
	function loadDataTableScripts_yycishu() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_yycishu);
		}

	}

	function runDataTables_yycishu() {

		/*
		 * BASIC
		 */
		yycishus=$('#yycishu').dataTable({
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
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
