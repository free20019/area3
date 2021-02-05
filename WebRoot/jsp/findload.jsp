﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="zdjkquzclfx">出租汽车重点监控区域重车率分析</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="loadtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
									</section>
									<section class="col col-5" style="width: 130px;float:left;" >
									<label class="select">
										<select  id="loadcomp" class="select">
											<option value="0" >--选择公司--</option>
										</select> <i></i> 
									</label>
									</section>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="findload();">查   询   </a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="loaddaochu();">导   出</a><span id="loaddaochu"></span>
						</form>
						
						<table id="load" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">重车率</th>
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
							<tbody id="loadtbody">
							</tbody>
						</table>

<section id="widget-grid" class="">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					</br><input type="checkbox" onclick="findload();" name="loadtime" value="0">上年同比
													<input type="checkbox" onclick="findload();" name="loadtime" value="1">上月同比
													<input type="checkbox" onclick="findload();" name="loadtime" value="2" checked >前半月最小
													<input type="checkbox" onclick="findload();" name="loadtime" value="3" checked >前半月最大
													<input type="checkbox" onclick="findload();" name="loadtime" value="4">上周平均
													<input type="checkbox" onclick="findload();" name="loadtime" value="5">上周同比
													<input type="checkbox" onclick="findload();" name="loadtime" value="6">前天
													<input type="checkbox" onclick="findload();" name="loadtime" value="7">昨天
													<input type="checkbox" onclick="findload();" name="loadtime" value="8" checked >今天
													<input type="checkbox" onclick="findload();" name="loadtimeq" value="0">全部
						<div id="loadtu" class="chart has-legend"></div>
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
		$("#loadtime").val(setformatnewlc(smydate));
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
	$(document).ready(function(){
		$.ajax({
			url:"findqxba.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#loadcomp').empty();
			var list = data.list;
			if(list.length<=1){
				for(var i=0;i<list.length;i++){
					$('#loadcomp').append("<option value='"+list[i].baid+"'>"+list[i].name+"</option>");
				}
			}else{
				$("#loadcomp").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$('#loadcomp').append("<option value='"+list[i].baid+"'>"+list[i].name+"</option>");
				}
			}
	findload();
		}
		});
});
	
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	var $chrt_border_color = "#21ede6";
	var $chrt_grid_color = "#1fda88"
	var $chrt_main = "#1f89da";			/* red       */
	var $chrt_second = "#CCFF99";
	
	
	function findload(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findonline.action',
			type : 'post',
			data:{
				"time" : $("#loadtime").val(),
				"baid" : "0"
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.ol[0];
					var bb = json.loadonline.ol[1];
					var cc = json.loadonline.ol[2];
					var dd = json.loadonline.ol[3];
					var ee = json.loadonline.ol[4];
					var ff = json.loadonline.ol[5];
					var gg = json.list1[0].pjload;
					var hh = json.halfMonth.arealoadmax;
					var ii = json.halfMonth.arealoadmin;
					var ltu="";
					var day=[];
					var daynj=[];
					var dayyj=[];
					var daysj=[];
					var dayqj=[];
					var dayzj=[];
					var dayjj=[];
					var daypj=[];
					var daymax=[];
					var daymin=[];
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<ff[1].length;i++){
					if(ff[1][i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
					}else{
						tab+="<td style='background:#aac8ea'>"+ff[1][i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee[1].length;i++){
					if(ee[1][i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+ee[1][i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd[1].length;i++){
					if(dd[1][i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+dd[1][i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc[1].length;i++){
					if(cc[1][i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+cc[1][i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<gg.length;i++){
						if(gg[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+gg[i]+"%</td>";
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
					if($("#loadtime").val().substring(5,10)=='03-29'||$("#loadtime").val().substring(5,10)=='03-30'||$("#loadtime").val().substring(5,10)=='03-31'){
						for(var i=0;i<48;i++){
								tab+="<td>--</td>";
						}
					}else{
						for(var i=0;i<bb[1].length;i++){
						if(bb[1][i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
							tab+="<td>"+bb[1][i]+"</td>";
							}
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上年同比</td>";
					for(var i=0;i<aa[1].length;i++){
					if(aa[1][i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+aa[1][i]+"</td>";
						}
					}
					tab+="</tr>";
					for(var i=0;i<aa[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(aa[1][i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(aa[1][i].substring(0,aa[1][i].length-1)+"]" );
							daynj.push(day1);
						}
					}
					for(var i=0;i<bb[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						var day1=[];
								day1.push("["+j);
								day1.push(bb[1][i].substring(0,bb[1][i].length-1)+"]" );
							dayyj.push(day1);
					}
					for(var i=0;i<cc[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(cc[1][i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(cc[1][i].substring(0,cc[1][i].length-1)+"]" );
							daysj.push(day1);
						}
					}
					for(var i=0;i<dd[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(dd[1][i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(dd[1][i].substring(0,dd[1][i].length-1)+"]" );
							dayqj.push(day1);
						}
					}
					for(var i=0;i<ee[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ee[1][i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(ee[1][i].substring(0,ee[1][i].length-1)+"]" );
							dayzj.push(day1);
						}
					}
					for(var i=0;i<ff[1].length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ff[1][i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(ff[1][i].substring(0,ff[1][i].length-1)+"]" );
							dayjj.push(day1);
						}
					}
					for(var i=0;i<gg.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(gg[i].length>2){
						var day1=[];
								day1.push("["+j);
								day1.push(gg[i].substring(0,gg[i].length-1)+"]" );
							daypj.push(day1);
						}
					}
					if(hh!=null){
					for(var i=0;i<hh.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(hh[i].length>2){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(hh[i].substring(0,hh[i].length-1)+"]" );
							daymax.push(gps1);
						}
					}
					}
					if(ii!=null){
					for(var i=0;i<ii.length;i++){
					var j=0;
					if(i==0){
						j=0;
					}else if(i%2==0){
							j=i/2;
						}else{
							j=i/2-0.20;
						}
						if(ii[i].length>1){
						var gps1=[];
								gps1.push("["+j);
								gps1.push(ii[i].substring(0,ii[i].length-1)+"]" );
							daymin.push(gps1);
						}
					}
					}
					$("#loadtbody").html(tab);
					var str=document.getElementsByName("loadtime");
					var objarray=str.length;
					var chestr="";
					for (i=0;i<objarray;i++){
					  if(str[i].checked == false)
					  {
					   chestr+=str[i].value+",";
					  }
					}
					var str1=document.getElementsByName("loadtimeq");
					var chestr1="";
					  if(str1[0].checked == true){
					   chestr1=str1[0].value;
					  }
					  if(chestr1=='0'){
					  	chestr='';
					  	 $("[name=loadtime]:checkbox").prop('checked', 'checked');
					  }
					  if(chestr.split(',').length>0){
					  	$("[name=loadtimeq]:checkbox").attr('checked', false);
					  }
					var loadtime=chestr.split(',');
					for(var i=0;i<loadtime.length;i++){
						if(loadtime[i]=="0"){
							daynj.length=0;
						}
						if(loadtime[i]=="1"){
							dayyj.length=0;
						}
						if(loadtime[i]=="2"){
							daymin.length=0;
						}
						if(loadtime[i]=="3"){
							daymax.length=0;
						}
						if(loadtime[i]=="4"){
							daypj.length=0;
						}
						if(loadtime[i]=="5"){
							daysj.length=0;
						}
						if(loadtime[i]=="6"){
							dayqj.length=0;
						}
						if(loadtime[i]=="7"){
							dayzj.length=0;
						}
						if(loadtime[i]=="8"){
							dayjj.length=0;
						}
					}
					generateAllFlotChartsload(daynj,dayyj,daysj,dayqj,dayzj,dayjj,daypj,daymax,daymin);
					//loadDataTableScripts_load();
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	
	function generateAllFlotChartsload(daynj,dayyj,daysj,dayqj,dayzj,dayjj,daypj,daymax,daymin){
		if ($("#loadtu").length) {
			var nianj = eval( "["+ daynj+"]" );
			var yuej  = eval( "["+ dayyj+"]" );
			var zhouj =  eval( "["+daysj+"]" );
			var qianj =  eval( "["+dayqj+"]" );
			var zuoj  = eval( "["+ dayzj+"]" );
			var jinj  = eval( "["+ dayjj+"]" );
			var pingj = eval( "["+ daypj+"]" );
			var max  = eval( "["+ daymax+"]" );
			var min = 	eval( "["+ daymin+"]" );
			//console.log(pageviews)
			var plot = $.plot($("#loadtu"), [{
				data : nianj,
				label : "上年同比"
			},{
				data : yuej,
				label : "上月同比"
			},{
				data : zhouj,
				label : "上周同比"
			},{
				data : qianj,
				label : "前天"
			},{
				data : zuoj,
				label : "昨天"
			},{
				data : jinj,
				label : "今天"
			},{
				data : pingj,
				label : "上周平均"
			},{
				data : max,
				label : "前半月最大"
			},{
				data : min,
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
					tickLength : 0.3
				},
	
				yaxes : [{
					min : 15,
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
					content : "%s  <b>%x </b> 重车率 %y %",
					defaultTheme : false
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
	function loaddaochu(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findloadexcle.action',
		type : 'post',
		data:{
			"time" : $("#loadtime").val()
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
$("#zdjkquzclfx").html("出租汽车重点监控区域重车率分析");
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

	var loads="";
	function loadDataTableScripts_load() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_load);
		}

	}

	function runDataTables_load() {

		/*
		 * BASIC
		 */
		loads=$('#load').dataTable({
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
