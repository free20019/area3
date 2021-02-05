<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
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
					<h2 id="zxyylfx">出租汽车在线营运率分析</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="yingyuntime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
									</section>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="findyingyun();">查   询   </a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="yingyundaochu();">导   出</a><span id="yingyundaochu"></span>
						</form>
						
						<table id="yingyun" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">营运率</th>
									<th>0-1</th>
									<th>1-2</th>
									<th>2-3</th>
									<th>3-4</th>
									<th>4-5</th>
									<th>5-6</th>
									<th>6-7</th>
									<th>7-8</th>
									<th>8-9</th>
									<th>9-10</th>
									<th>10-11</th>
									<th>11-12</th>
									<th>12-13</th>
									<th>13-14</th>
									<th>14-15</th>
									<th>15-16</th>
									<th>16-17</th>
									<th>17-18</th>
									<th>18-19</th>
									<th>19-20</th>
									<th>20-21</th>
									<th>21-22</th>
									<th>22-23</th>
									<th>23-0</th>
								</tr>
							</thead>
							<tbody id="yingyuntbody">
							</tbody>
						</table>

<section id="widget-grid" class="">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					</br><input type="checkbox" onclick="findyingyun();" name="yytime" value="0">上年同比
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="1">上月同比
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="2" checked >前半月最小
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="3" checked >前半月最大
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="4">上周平均
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="5">上周同比
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="6">前天
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="7">昨天
													<input type="checkbox" onclick="findyingyun();" name="yytime" value="8" checked >今天
													<input type="checkbox" onclick="findyingyun();" name="yytimeq" value="0">全部
						<div id="yingyuntu" class="chart has-legend"></div>
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
		$("#yingyuntime").val(setformatnewlc(smydate));
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
	findyingyun();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	var $chrt_border_color = "#21ede6";
	var $chrt_grid_color = "#1fda88"
	var $chrt_main = "#1f89da";			/* red       */
	var $chrt_second = "#CCFF99";
	function findyingyun(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findyingyun.action',
			type : 'post',
			data:{
				"time" : $("#yingyuntime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.yy1[0];
					var bb = json.loadonline.yy1[1];
					var cc = json.loadonline.yy1[2];
					var dd = json.loadonline.yy1[3];
					var ee = json.loadonline.yy1[4];
					var ff = json.loadonline.yy1[5];
					var gg = json.list1[0].pjyingyun;
					var hh = json.halfMonth.yingyunmax;
					var ii = json.halfMonth.yingyunmin;
					var day=[];
					var yingyunn=[];
					var yingyuny=[];
					var yingyuns=[];
					var yingyunq=[];
					var yingyunz=[];
					var yingyunj=[];
					var yingyunp=[];
					var yingyunmax=[];
					var yingyunmin=[];
					var date = new Date();
					var hour = date.getHours();
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
						for(var i=0;i<ff.length;i++){
						if(ff[i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
						}else{
							tab+="<td style='background:#aac8ea'>"+ff[i]+"</td>";
							}
						}
						tab+="</tr>";
					
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd.length;i++){
					if(dd[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc.length;i++){
					if(cc[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+cc[i]+"</td>";
						}
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
					if(hh!=null){
					for(var i=0;i<hh.length;i++){
						if(hh[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+hh[i]+"</td>";
						} 
					}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前半月最小</td>";
					if(ii!=null){
					for(var i=0;i<ii.length;i++){
						if(ii[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+ii[i]+"</td>";
						} 
					}
					}
					tab+="<tr><td nowrap='nowrap'>上月同比</td>";
					if($("#yingyuntime").val().substring(5,10)=='03-29'||$("#yingyuntime").val().substring(5,10)=='03-30'||$("#yingyuntime").val().substring(5,10)=='03-31'){
						for(var i=0;i<24;i++){
								tab+="<td>--</td>";
						}
					}else{
						for(var i=0;i<bb.length;i++){
						if(bb[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
							tab+="<td>"+bb[i]+"</td>";
							}
						}
					}
					tab+="</tr>";
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
					var j=i+1;
						if(aa[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(aa[i].substr(0,2)+"]");
							yingyunn.push(yy1);
						}
					}
					for(var i=0;i<bb.length;i++){
					var j=i+1;
						if(bb[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(bb[i].substr(0,2)+"]");
							yingyuny.push(yy1);
						}
					}
					for(var i=0;i<cc.length;i++){
					var j=i+1;
						if(cc[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(cc[i].substr(0,2)+"]");
							yingyuns.push(yy1);
						}
					}
					for(var i=0;i<dd.length;i++){
					var j=i+1;
						if(dd[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(dd[i].substr(0,2)+"]");
							yingyunq.push(yy1);
						}
					}
					for(var i=0;i<ee.length;i++){
					var j=i+1;
						if(ee[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ee[i].substr(0,2)+"]");
							yingyunz.push(yy1);
						}
					}
					for(var i=0;i<ff.length-7;i++){
					var j=i+1;
					if(ff[i].length>5){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ff[i].substring(24,26)+"]");
							yingyunj.push(yy1);
					}else if(ff[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(ff[i].substr(0,2)+"]");
							yingyunj.push(yy1);
						}
					}
					for(var i=0;i<gg.length;i++){
					var j=i+1;
						if(gg[i].length>2){
						var yy1=[];
								yy1.push("["+j);
								yy1.push(gg[i].substr(0,2)+"]");
							yingyunp.push(yy1);
						}
					}
					for(var i=0;i<hh.length;i++){
					var j=i+1;
						if(hh[i].length>2){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(hh[i].substr(0,2)+"]" );
							yingyunmax.push(gps1);
						}
					}
					for(var i=0;i<ii.length;i++){
					var j=i+1;
						if(ii[i].length>1){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(ii[i].substr(0,2)+"]" );
							yingyunmin.push(gps1);
						}
					}
					var str=document.getElementsByName("yytime");
					var objarray=str.length;
					var chestr="";
					for (i=0;i<objarray;i++){
					  if(str[i].checked == false)
					  {
					   chestr+=str[i].value+",";
					  }
					}
					var str1=document.getElementsByName("yytimeq");
					var chestr1="";
					  if(str1[0].checked == true){
					   chestr1=str1[0].value;
					  }
					  if(chestr1=='0'){
					  	chestr='';
					  	 $("[name=yytime]:checkbox").prop('checked', 'checked');
					  }
					  if(chestr.split(',').length>0){
					  	$("[name=yytimeq]:checkbox").attr('checked', false);
					  }
					var loadtime=chestr.split(',');
					for(var i=0;i<loadtime.length;i++){
						if(loadtime[i]=="0"){
							yingyunn.length=0;
						}
						if(loadtime[i]=="1"){
							yingyuny.length=0;
						}
						if(loadtime[i]=="2"){
							yingyunmin.length=0;
						}
						if(loadtime[i]=="3"){
							yingyunmax.length=0;
						}
						if(loadtime[i]=="4"){
							yingyunp.length=0;
						}
						if(loadtime[i]=="5"){
							yingyuns.length=0;
						}
						if(loadtime[i]=="6"){
							yingyunq.length=0;
						}
						if(loadtime[i]=="7"){
							yingyunz.length=0;
						}
						if(loadtime[i]=="8"){
							yingyunj.length=0;
						}
					}
					$("#yingyuntbody").html(tab);
					generateAllFlotChartsyingyun(yingyunn,yingyuny,yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyunmax,yingyunmin);
					//loadDataTableScripts_yingyun();
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	
	function generateAllFlotChartsyingyun(yingyunn,yingyuny,yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyunmax,yingyunmin){
		if ($("#yingyuntu").length) {
			var yyn =eval( "["+yingyunn+"]" );
			var yyy=eval( "["+yingyuny+"]" );
			var yys=eval( "["+yingyuns+"]" );
			var yyq=eval( "["+yingyunq+"]" );
			var yyz=eval( "["+yingyunz+"]" );
			var yyj=eval( "["+yingyunj+"]" );
			var yyp=eval( "["+yingyunp+"]" );
			var yymax=eval( "["+yingyunmax+"]" );
			var yymin=eval( "["+yingyunmin+"]" );
			var plot = $.plot($("#yingyuntu"), [{
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
				data : yymax,
				label : "前半月最大"
			},{
				data : yymin,
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
					min : 10,
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
					content : "%s  <b>%x</b> 营运率 %y %",
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
	function yingyundaochu(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findyingyunexcle.action',
		type : 'post',
		data:{
			"time" : $("#yingyuntime").val()
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
$("#zxyylfx").html("出租汽车在线营运率分析");

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

	var yingyuns="";
	function loadDataTableScripts_yingyun() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_yingyun);
		}

	}

	function runDataTables_yingyun() {

		/*
		 * BASIC
		 */
		yingyuns=$('#yingyun').dataTable({
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
