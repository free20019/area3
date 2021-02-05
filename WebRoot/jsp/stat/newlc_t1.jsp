<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row">
<table border="1" style="width: 101%;height: 100%">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>统计管理→最新行驶里程统计</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<section style="width:120px;float:left;">
										<label class=select> 
											 <select id="area4">
											 <option>选择公司</option>
										</select> 
										</label>
									</section>
									<section style="width:120px;float:left;">
										<label class=select> 
											 <select id="comp4">
											 <option>选择车队</option>
										</select> 
										
										</label>
									</section>
									<section style="width:120px;float:left;">
										<label class=select> 
											 <select id="vhic4">
											 <option>选择车辆</option>
										</select> 
										
										</label>
									</section>
									<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="newlc_stime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()"></input>
										</label>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="newlc_etime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()"/>
										</label>
								</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="findnewlc();">查   询</a>
								<!-- 
								<a href="javascript:void(0);" class="btn btn-primary" onclick="exportnewlc();">导   出</a><span id="newlcdaochu"></span>
								 -->
								</form>
						<div class="widget-body-toolbar">
							
						</div>
						
						<table id="newlctable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>车牌</th>
									<th>里程(公里)</th>
								</tr>
							</thead>
							<tbody id="newlcdata">
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
	loadDataTableScripts_newlc();
	findbaedit();
	function findbaedit(){
		$.ajax({
			url : 'findba.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list1;
					$("#area4").append("<option value = '所有公司' onclick='findcomp()'>--所有公司--</option>");
					for(var i=0;i<area.length;i++){
						$("#area4").append("<option value='"+area[i].ba_name+"'onclick='findcomp()' >"+area[i].ba_name+"</option>");
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
				"ba_id":$("#area4").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#comp4").empty();
					var area=json.list2;
					$("#comp4").append("<option value = '所有车队' onclick='findvehi()'>--所有车队--</option>");
					for(var i=0;i<area.length;i++){
						$("#comp4").append("<option value='"+area[i].comp_name+"'onclick='findvehi()' >"+area[i].comp_name+"</option>");
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
				"comp_id":$("#comp4").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#vhic4").empty();
					var area=json.list3;
					$("#vhic4").append("<option value = '所有车辆'>--所有车辆--</option>");
					for(var i=0;i<area.length;i++){
						$("#vhic4").append("<option value='"+area[i].vehi_no+"'onclick='' >"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
		$("#newlc_stime").val(setformatnewlc(smydate));
		$("#newlc_etime").val(setformatnewlc(mydate));
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
		var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s; 
		return time;
	}


function findnewlc(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url : 'findlctj.action',
		type : 'post',
		data:{
			"vehi_no" : $("#vhic4").val(),
			"stime" : $("#newlc_stime").val(),
			"etime" : $("#newlc_etime").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			$.unblockUI();
				if(resultnewlcdata!=""){
					resultnewlcdata.fnDestroy();  
				}
				var businesslist=json.list;
					var tab="";
					for(var i=0;i<businesslist.length;i++){
							tab +="<tr><td>"+(i+1)+"</td><td>"+businesslist[i].vehinum+"</td><td>"+businesslist[i].jicheng+"</td></tr>";
					}
					$("#newlcdata").html(tab);
					loadDataTableScripts_newlc();
		},
		error:function(){
			$.unblockUI();
		}		
	});
}








var resultnewlcdata="";

function loadDataTableScripts_newlc() {

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
		 resultnewlcdata=$('#newlctable').dataTable({
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
	function exportnewlc(){
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'newlcexcle.action',
			type : 'post',
			data:{
				"compid" : $("#compid").val(),
				"vehiid" : $("#newlccar").val(),
				"stime" : $("#newlc_stime").val(),
				"etime" : $("#newlc_etime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				if(json.message=='成功导成Excel!'){
					var tab=json.message+" 文件:"+json.xlsfilename+"&nbsp;<a href='"+json.action+"'>下载</a>";
					$("#newlcdaochu").html(tab);
				}else{
					var tab=json.message;
					$("#newlcdaochu").html(tab);
				}
			},
			error:function(){
				
			}
			});
		}
	}

</script>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">