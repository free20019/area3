﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						
						<table id="vhicinfotable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th><input type="hidden" id="time" value="${time}"/><input type="hidden" id="id" value="${id}"/>序号</th>
									<th>公司</th>
									<th>车号</th>
									<th>SIM卡号</th>
									<th>司机姓名</th>
									<th>司机电话</th>
								</tr>
							</thead>
							<tbody id="vhicinfotbody">
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
	findvhicinfo();
	loadDataTableScripts_vhicinfo();
	
//查询数据
function findvhicinfo(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findareavhic.action',
			type : 'post',
			data:{
			"time" : $("#time").val(),
			"id" : $("#id").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var vhicinfo = json.list3;
				var tab="";
				$('#vhicinfotable').dataTable().fnDestroy(); 
				if(vhicinfo!=null){
					for(var i=0;i<vhicinfo.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vhicinfo[i].comp_id+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vhicinfo[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vhicinfo[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vhicinfo[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vhicinfo[i].own_tel+"</td></tr>";
					}
						$("#vhicinfotbody").html(tab);
	$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}


var sendvhicinfo="";
function loadDataTableScripts_vhicinfo() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_vhicinfo);
		}

	}

	function runDataTables_vhicinfo() {

		/*
		 * BASIC
		 */
		 sendvhicinfo=$('#vhicinfotable').dataTable({
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
<script src="js/jquery.blockUI.js"></script>