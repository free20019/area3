<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="sdtlclcx">重点监控区域时段停留车辆查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
						<section class="col col-5" style="width: 200px;float:left;" >
										<label class="select">
											<select  id="timevehiquyu" class="select" >
												<option value="0" >所有区域</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 200px;float:left;" >
										<label class="select">
											<select  id="timevehicompname" class="select" >
										<option value="0" >--请选择公司--</option>
											</select> <i></i> </label>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="timevehistime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="timevehietime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="findtimevehi();">查    询</a>
								<a href="javascript:void(1);"   class="btn btn-primary"  onclick="findeimevehiexcle();">导   出</a><span id="timevehidaochu"></span>
								<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="timevehicis"></span>
										</label>
									</section>
						</form>
						<table id="timevehitable"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>公司</th>
									<th>区域</th>
									<th>车号</th>
									<th>SIM卡号</th>
									<th>联系人</th>
									<th>联系电话</th>
								</tr>
							</thead>
							<tbody id="timevehitbody">
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
	findtimequyu();
	findba();
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
		$("#timevehistime").val(setformatnewlc(smydate));
		$("#timevehietime").val(setformatnewlc(mydate));
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
	//loadDataTableScripts_timevehitable();
	// PAGE RELATED SCRIPTS
	function findtimequyu(){
	$.ajax({
			url : 'area.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				// $("#timevehiquyu").empty();
					var area=json.list;
					for(var i=0;i<area.length;i++){
						$("#timevehiquyu").append("<option value='"+area[i].area_name+"' >"+area[i].area_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
		function findeimevehiexcle(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
		url : 'findtimevehiexcle.action',
		type : 'post',
		data:{
			"stime" : $("#timevehistime").val(),
				"etime" : $("#timevehietime").val(),
				"quyu" : $("#timevehiquyu").val(),
				"compname" : $("#timevehicompname").val()
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
	function findba(){
		$.ajax({
		url:"getInfo.action",
	dataType:"JSON",
	type:"POST",
	async:true,
	success:function(data){
	$("#timevehicompname").empty();	
		$("#timevehicompname").append("<option value='0' >--选择公司--</option>");
		var list = data.compList;
		if(list.length == 1){
			$("#timevehicompname").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
		}else{
			for(var i=0;i<list.length;i++){
				$('#timevehicompname').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
			}
		}
		
	}
	});
	}
function findtimevehi(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findtimevehi.action',
			type : 'post',
			data:{
				"stime" : $("#timevehistime").val(),
				"etime" : $("#timevehietime").val(),
				"quyu" : $("#timevehiquyu").val(),
				"compname" : $("#timevehicompname").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var offline = json.list2;
				var area = json.area;
				var vehicle = json.vehicle;
				var tab="";
				var i=0;
				//$('#timevehitable').dataTable().fnDestroy(); 
				if(offline!=null){
					for(i=0;i<offline.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].ba_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+offline[i].own_tel+"</td></tr>";
					}
				}
				var polygonArr=new Array();
				var zbs = area[0].areazbs.split(";");
				for(var i=0;i<zbs.length;i++){
					var zb = zbs[i].split(",");
					polygonArr.push(new AMap.LngLat(zb[0],zb[1]));   
				}
				var polygon=new AMap.Polygon({     
					path:polygonArr,//设置多边形边界路径
					strokeColor:"black", //线颜色  
					// strokeOpacity:0.2, //线透明度   
					strokeWeight:3,    //线宽   
					fillColor: "#f5deb3", //填充色  
					fillOpacity: 0//填充透明度  
				}); 
				var test=0;
				for(j=0;j<vehicle.length;j++){
					var po = new AMap.LngLat(vehicle[j].longi,vehicle[j].lati);
					if(polygon.contains(po)){
						test++;
						tab=tab+"<tr><td nowrap='nowrap'>"+(offline.length+test)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicle[j].compname+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+$("#timevehiquyu").val()+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicle[j].vehino+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicle[j].simka+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicle[j].ownname+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicle[j].owntel+"</td></tr>";
					}
				}
					$("#timevehicis").html("总条数:"+(offline.length+test)+"条");
						$("#timevehitbody").html(tab);
				$.unblockUI();
				
			},
			error:function(){
				
			}		
	});
}

$("#sdtlclcx").html("重点监控区域时段停留车辆查询");
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

	var timevehitables="";
	function loadDataTableScripts_timevehitable() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_timevehitable);
		}

	}

	function runDataTables_timevehitable() {

		/*
		 * BASIC
		 */
		timevehitables=$('#timevehitable').dataTable({
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
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
