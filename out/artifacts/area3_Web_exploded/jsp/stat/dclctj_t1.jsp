<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<style>
.lccs a:link {color: #FF0000}
.lccs a:visited {color: #00FF00}
.lccs a:hover {color: #FF00FF}
.lccs a:active {color: #0000FF}
</style>
<!-- widget grid -->
<div class="row" style="height:98%;overflow: scroll" >
<input type="hidden" id="divcs"> 
	<!-- row -->
<table border="1" style="width: 102%;height: 95px;" >
	<tr style="height: 130px;overflow: auto;" valign="top">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<!-- widget div-->
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2>统计管理→单车里程统计</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
						<section class="col col-5" style="width: 200px;float:left;" >
										<label class="select">
											<select  id="dcareaselect" class="select">
												<option value="0" >--选择公司--</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 200px;float:left;" >
										<label class="select">
											<select id="dccompselect" class="select" >
												<option value="0" selected=""  disabled="">--选择分公司--</option>
											</select> <i></i> </label>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="stime3" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()"></input>
										</label>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="etime3" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()"/>
										</label>
								</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="finddclc();">查   询</a>
								<!-- 
								<a href="javascript:void(0);" class="btn btn-primary" onclick="exportdclc();">导   出</a>
								 -->
								</form>
								<br>
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<table>
								<tr>
								<td>车牌：</td>
								<td><section>
									<label class="select select-multiple">
									<select multiple="" class="custom-scroll" style="height: 100px;width: 200px" id="cdvehiselect">
									</select> </label>
									</section></td>
								<td>里程：</td>
								<td><div class="divcss5" id="dclc"></div> </td>
								</tr>
								</table>
								
								</form>
						
						<table id="dclctable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>车牌</th>
									<th>经度</th>
									<th>纬度</th>
									<th>速度(km/h)</th>
									<th>时间</th>
									<th>所在位置</th>
								</tr>
							</thead>
							<tbody id="dclcdata">
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
	findba();
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
		$("#stime3").val(setformatnewlc(smydate));
		$("#etime3").val(setformatnewlc(mydate));
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


var resultdclcdata="";

function loadDataTableScripts_dclc() {

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
		 resultdclcdata=$('#dclctable').dataTable({
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

//添加车牌
function resetdclc(){
	$("#dccp").html("");
}
function adddclc(){
	if($("#cdvehiselect").val()!=0&&$("#cdvehiselect").val()!=null){
		var htm = $("#dccp").html();
		htm += $("#dccp").val()+"<p name='lccps' id='"+$("#cdvehiselect").val()+"'>"+$("#cdvehiselect").val()+"</p>";
		$("#dccp").html(htm);
	}else{
		alert("请选择车辆之后添加");
	}
}
function finddclc(){
	var cp="";
	$('p[name="lccps"]').each(function(){
		cp+=this.id+';';
	});
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url : 'dclcquery.action',
		type : 'post',
		data:{
			"vehi_no" : $('#cdvehiselect option:selected') .val(),
			"stime" : $("#stime3").val(),
			"etime" : $("#etime3").val(),
			"ba_name":$("#dcareaselect").val(),
			"comp_name":$("#dccompselect").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var businesslist=json.list1;
					var tab="";
					for(var i=0;i<businesslist.length;i++){
							tab +="<div class='lccs'><a id='"+businesslist[i].vehinum+"' style='cursor:pointer;' onclick='finddclcxx(this.id);'>"+businesslist[i].vehinum+" : "+businesslist[i].jicheng+" km</a><div>";
					}
					var vehino=businesslist[0].vehinum;
					$("#dclc").html(tab);
					finddclcxx();
			$.unblockUI();
		},
		error:function(){
			$.unblockUI();
		}
		});
}
function finddclcxx(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url : 'clquery.action',
		type : 'post',
		data:{
			"vehi_no" : $('#cdvehiselect option:selected') .val(),
			"stime" : $("#stime3").val(),
			"etime" : $("#etime3").val(),
			"ba_name":$("#dcareaselect").val(),
			"comp_name":$("#dccompselect").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var xx=json.list3;
			var cl="";
			for(var i=0;i<xx.length;i++){
				cl +="<tr><td>"+(i+1)+"</td><td nowrap='nowrap'>"+xx[i].vehinum+"</td><td nowrap='nowrap'>"+xx[i].lati+"</td><td nowrap='nowrap'>"+xx[i].longi+"</td><td nowrap='nowrap'>"+xx[i].speed+"</td><td nowrap='nowrap'>"+xx[i].speedtime+"</td><td  nowrap='nowrap'>"+xx[i].address+"</td></tr>";
			}
			$("#dclcdata").html(cl);
			$.unblockUI();
		},
		error:function(){
			$.unblockUI();
		}
		});
}

function findba(){
		$.ajax({
			url : 'findba.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list1;
					for(var i=0;i<area.length;i++){
						$("#dcareaselect").append("<option value='"+area[i].ba_id+"'onclick='findcomp(this.value);'>"+area[i].ba_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findcomp(obj){
		$.ajax({
			url : 'findcompgroup.action',
			type : 'post',
			data:{
				'ba_id': obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#dccompselect").empty();
			$("#dcvehiselect").empty();
					var area=json.list;
					var comp=json.list1;
					for(var i=0;i<area.length;i++){
						$("#dccompselect").append("<option value='"+area[i].comp_id+"' onclick='findvehi(this.value)';>"+area[i].comp_name+"</option>");
					}
					for(var i=0;i<comp.length;i++){
						$("#cdvehiselect").append("<option value='"+comp[i].vehi_no+"' onclick='finddclc(this.value);'>"+comp[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findvehi(obj){
		$.ajax({
			url : 'findvehigroup.action',
			type : 'post',
			data:{
				"comp_id": obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#cdvehiselect").empty();
					var area=json.list;
					for(var i=0;i<area.length;i++){
						$("#cdvehiselect").append("<option value='"+area[i].vehi_no+"' onclick='finddclc();'>"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
</script>
<style>
.divcss5{width:200px;height:100px;border:1px solid #006699;overflow: auto;}
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">