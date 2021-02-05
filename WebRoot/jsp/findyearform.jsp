﻿ <%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" style="overflow: auto;">
	<!-- row -->
<table border="1" style="width: 102%;height: 100%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"><i class="fa fa-table"></i></span>
					<h2 id="yynbby">年报表(月)</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate">
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="yearformtime" type="text" onClick="WdatePicker({dateFmt:'yyyy'})()"></input>
										</label>
									</section>
								<a href="javascript:void(0);"  class="btn btn-primary" onclick="findyearform1();">查   询</a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="findyearformexcle();">导   出</a><span id="yearformdaochu"></span>
						</form>
						
						<table id="fuel_yearform1" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap='nowrap'>序号</th>
									<th nowrap='nowrap'>月份</th>
									<th nowrap='nowrap'>总车辆数</th>
									<th nowrap='nowrap'>日参与营运平均车辆数</th>
									<th nowrap='nowrap'>总周转次数</th>
									<th nowrap='nowrap'>月平均单车周转次数</th>
									<th nowrap='nowrap'>日平均单车周转次数</th>
									<th nowrap='nowrap'>平均营运率</th>
									<th nowrap='nowrap'>总出车时间(天)</th>
									<th nowrap='nowrap'>平均出车时间(天)</th>
									<th nowrap='nowrap'>营收金额</th>
									<th nowrap='nowrap'>月平均单车营收金额</th>
									<th nowrap='nowrap'>日平均单车营收金额</th>
									<th nowrap='nowrap'>总里程(公里)</th>
									<th nowrap='nowrap'>月平均单车总里程(公里)</th>
									<th nowrap='nowrap'>日平均单车总里程(公里)</th>
									<th nowrap='nowrap'>实载里程(公里)</th>
									<th nowrap='nowrap'>月平均单车实载里程(公里)</th>
									<th nowrap='nowrap'>日平均单车实载里程(公里)</th>
									<th nowrap='nowrap'>空驶里程(公里)</th>
									<th nowrap='nowrap'>月平均单车空驶里程(公里)</th>
									<th nowrap='nowrap'>日平均单车空驶里程(公里)</th>
									<th nowrap='nowrap'>平均实载率</th>
								</tr>
							</thead>
							<tbody id="yearformtbody">
							</tbody>
						</table>

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
	var myDate = new Date();
	var a=myDate.getFullYear();
	$("#yearformtime").val(a);
	//loadDataTableScripts_yearform1();
	findyearform1();
	// PAGE RELATED SCRIPTS

	function findyearform1(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findyearform.action',
			type : 'post',
			data:{
				'time' : $("#yearformtime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var quanxian=json.list;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].report_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;9612</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjvhic+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_ypjno+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjno+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_rade+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_car_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjcar_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_amount_revenue+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_ypjamount_revenue+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjamount_revenue+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_ypjmileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjmileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_actual_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_ypjactual_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjactual_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_empty_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_ypjempty_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_pjempty_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_actual_loading+"</td></tr>";
					}
					$("#yearformtbody").html(tab);
					}
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}

//导出excle
	function findyearformexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findyearformexcle.action',
		type : 'post',
		data:{
				'time' : $("#yearformtime").val()
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
$("#yynbby").html("年报表(月)");
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

	var fuel_yearform1="";
	function loadDataTableScripts_yearform1() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_yearform1);
		}

	}

	function runDataTables_yearform1() {

		/*
		 * BASIC
		 */
		fuel_yearform1=$('#fuel_yearform1').dataTable({
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
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
