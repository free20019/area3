 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
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
					<h2 id="ysmzcz">疑似模子出租信息统计</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:45px;float:left;">
										<label class="font" >
											<span style="font-size: 18px">时间:</span>
										</label>
								</section>
								<section style="width:130px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="taxiinfotime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
									</section>
									<section style="width:45px;float:left;">
										<label class="font" >
											<span style="font-size: 18px">车号:</span>
										</label>
								</section>
									<section style="width:130px;float:left;">
										<label class="input"> <i class=""></i>
											 <input id="taxiinfovhic" type="text"></input>
										</label>
									</section>
								<a href="javascript:void(0);"  class="btn btn-primary" onclick="findtaxiinfo1();">查   询</a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="findtaxiinfoexcle();">导   出</a><span id="taxiinfodaochu"></span>
						</form>
						
						<table id="" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>公司</th>
									<th>车号</th>
									<th>开始时间</th>
									<th>结束之间</th>
									<th>营运次数</th>
									<th>地址</th>
								</tr>
							</thead>
							<tbody id="taxiinfotbody">
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
	var b=myDate.getMonth()+1+"";
	if(b.length<2){
		b="0"+b;
	}
	var day = myDate.getDate()-1;
	if(day.length<2){
		day="0"+day;
	}
	$("#taxiinfotime").val(a+"-"+b+"-"+day);
	//loadDataTableScripts_taxiinfo1();
	findtaxiinfo1();
	// PAGE RELATED SCRIPTS

	function findtaxiinfo1(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findtaxiinfo.action',
			type : 'post',
			data:{
				'time' : $("#taxiinfotime").val(),
				'vehi_no' : $("#taxiinfovhic").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				//$('#fuel_taxiinfo1').dataTable().fnDestroy(); 
					var quanxian=json.taxiinfo;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].begintime+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].endtime+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].operation_num+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].address+"</td></tr>";
					}
					$("#taxiinfotbody").html(tab);
					//loadDataTableScripts_taxiinfo1();
					}
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	//导出excle
		function findtaxiinfoexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findtaxiinfoexcle.action',
		type : 'post',
		data:{
			'time' : $("#taxiinfotime").val(),
			'vehi_no' : $("#taxiinfovhic").val()
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
$("#ysmzcz").html("疑似模子出租信息统计");
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

	var fuel_taxiinfo1="";
	function loadDataTableScripts_taxiinfo1() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_taxiinfo1);
		}

	}

	function runDataTables_taxiinfo1() {

		/*
		 * BASIC
		 */
		fuel_taxiinfo1=$('#fuel_taxiinfo1').dataTable({
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
