<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
$(document).ready(function(){
	var mydate = new Date();
	var y=(mydate.getFullYear()).toString();
	var m=(mydate.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=mydate.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var time=y+"-"+m+"-"+d; 
	var time1=time+" 00:00:00"; 
	var time2=time+" 23:59:59"; 
	$("#endtimec7").val(time2);
	$("#starttimec7").val(time1);
	
});
</script>
	
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="sdzxjl">时段车辆最新交易时间记录查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:85px;float:left;">
										<label class="font">
											<span style="font-size: 18px">开始时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="starttimec7" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font">
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endtimec7" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style="width:200px;float:left;">
										<select  id="area2" class="select2" onchange="findcomp(this.value)">
												<option value="0" selected=""  disabled="">--选择公司--</option>
											</select> 
									</section>
									<section style="width:200px;float:left;">
											<select id="comp2" class="select2" onchange="findvehi(this.value)">
												<option value="0" selected=""  disabled="">--选择分公司--</option>
											</select>
								</section>
								<section style="width:120px;float:left;">
											<select id="vhic2" class="select2">
												<option value="0" selected=""  disabled="">--选择车辆--</option>
											</select>
								</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="cash_searchc7();">查   询</a>
						</form>
						<table id="dt_basicc7" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<td nowrap="nowrap">序号</td>
									<td nowrap="nowrap">资格证号</td>
									<td nowrap="nowrap">车号</td>
									<td nowrap="nowrap">上车时间</td>
									<td nowrap="nowrap">下车时间</td>
									<td nowrap="nowrap">计程(km)</td>
									<td nowrap="nowrap">等候时间(秒)</td>
									<td nowrap="nowrap">交易金额</td>
									<td nowrap="nowrap">空驶</td>
									<td nowrap="nowrap">交易类型</td>
								</tr>
							</thead>
							<tbody id="vehidatac7">
							</tbody>
						</table>
							
					</div>
					<div>
							<span class="STYLE22">
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="resultc7">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPagec7">0</a></strong> / <strong><a id="yec7">0</a></strong> 页
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:business_select(1);">首页</a>
							<a class="STYLE19" href="javascript:business_select(this.currentPage-1);">上一页</a> | <a class="STYLE19" href="javascript:business_select(this.currentPage+1);">下一页</a>
							<a class="STYLE19" href="javascript:business_selectend();">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input onchange="trim3(this.id)" style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="currentPage1c7" >页 <a class="STYLE19" href="javascript:business_select(currentPage1.value)">go</a>
							<input type="button" value="导出EXCLE" onclick="outExcelc7()"/><span id="daochuc7">
    						</a>
						</span>
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
	findba();
	// PAGE RELATED SCRIPTS



var businesslist=null;
var result=0;
var currentPage=0;
var ye=0;
var maxye="";
var CUSTOMERS="";
var IN_OUT="";
var starttime="";
var endtime="";
var state="";
var vhic="";
function cash_searchc7(){
	starttime=$("#starttimec7").val();
	endtime=$("#endtimec7").val();
	vhic=$("#vhic2").val();
	ba_name=$("#area2").val();
	comp_name=$("#comp2").val();
	business_select(1);
}
function business_select(currentPage1){
if(currentPage1==0){
	alert("已经到达首页！");
	}else if(currentPage1>maxye&&maxye!=""){
	alert("已经到达末页！");
	}else if(currentPage1!=''&&currentPage1>=0){
	
	var a=eval("({'starttime':'"+starttime+"','endtime':'"+endtime+"','currentPage':'"+currentPage1+"','vhic':'"+vhic+"','ba_name':'"+ba_name+"','comp_name':'"+comp_name+"'})");
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='/citizencard/images/busy.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url : 'findyy.action',
		type : 'post',
		data:a,
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			businesslist=json.list;
			result=json.result;
			currentPage=json.currentPage;
			ye=json.ye;
			maxye=parseInt((result-1)/15+1);
			setbusiness_select(businesslist);
		},
		error:function(){
			
		}		
	});
}
}
function business_selectend(){
	business_select(maxye);
}
function setbusiness_select(businesslist){
$("#vehidatac7").html();
	var tab="";
	if(businesslist!=null){
	
	for(var i=0;i<businesslist.length;i++){
		tab=tab+"<tr><td nowrap='nowrap'>"+businesslist[i].r+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].cbid+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].shangche+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].xiache+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].jicheng+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].denghou+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].jine+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].kongshi+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].jiaoyitype+"</td>";
			tab=tab+"</tr>";
	}
	
	$("#vehidatac7").html(tab);
	$("#resultc7").html(result);
	$("#currentPagec7").html(currentPage);
	$("#yec7").html(ye);
	if(businesslist.length!=0){
		$("#excel").removeAttr("disabled");
	}
	}else{
		$('#vehidatac7').html(tab);
		$("#resultc7").html("0");
		$("#currentPagec7").html("0");
		$("#yec7").html("0");
	}
	businesslist=null;
	$.unblockUI();
}

	function outExcelc7(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='/citizencard/images/busy.gif' border='0'/><br/><br/></div>"});
		$.ajax({
		url : 'yy.action',
		type : 'post',
		data:{
			"starttime" : $("#starttimec7").val(),
			"endtime" : $("#endtimec7").val(),
			"state" :  $("#statec7").val(),
			"vhic" : $("#vhicc7").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			if(json.fanhuei=='成功导成Excel!'){
				var tab=json.fanhuei+" 文件:"+json.xlsfilename+"&nbsp;<a href='"+json.action+"'>下载</a>";
				$("#daochuc7").html(tab);
			}else{
				var tab=json.fanhuei;
				$("#daochuc7").html(tab);
			}
		},
		error:function(){
			
		}
		});
		$.unblockUI();
	}
}
//下拉框
	function findba(){
		$.ajax({
			url : 'findba.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#area2").empty();
					var area=json.list1;
					$("#area2").append("<option value='0' >--选择公司--</option>");
					for(var i=0;i<area.length;i++){
						$("#area2").append("<option value='"+area[i].ba_name+"'>"+area[i].ba_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findcomp(){
		$.ajax({
			url : 'findcomp.action',
			type : 'post',
			data:{
				"ba_id":$("#area2").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#comp2").empty();
					var area=json.list2;
					$("#comp2").append("<option value='0' >--选择分公司--</option>");
					for(var i=0;i<area.length;i++){
						$("#comp2").append("<option value='"+area[i].comp_name+"'>"+area[i].comp_name+"</option>");
					}
					$("#vhic2").empty();
					var area=json.list3;
					$("#vhic2").append("<option value='0' >--选择车辆--</option>");
					for(var i=0;i<area.length;i++){
						$("#vhic2").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findvehi(){
		$.ajax({
			url : 'findvehi.action',
			type : 'post',
			data:{
				"comp_id":$("#comp2").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#vhic2").empty();
					var area=json.list3;
					$("#vhic2").append("<option value='0' >--选择车辆--</option>");
					for(var i=0;i<area.length;i++){
						$("#vhic2").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	$("#sdzxjl").html("时段车辆最新交易时间记录查询");
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

	
	function loadDataTableScripts() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables);
		}

	}

	function runDataTables() {

		/*
		 * BASIC
		 */
		$('#dt_basicc7').dataTable({
			"sPaginationType" : "bootstrap_full"
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
.markerContentStyle{position:relative;}
.markerContentStyle span{width:70px;background-color:#3399FF;color:#ffffff;border:1px solidred;font-size:14px;white-space:nowrap}
</style>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
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