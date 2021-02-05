<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" >
<input type="hidden" id="stationadmin" value="${stationadmin}">
	<!-- row -->
<table border="1" style="width: 102%;height: 100%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="yhglym">用户管理</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<a href="javascript:void(0);" id="uck" style="display: none;" class="btn btn-primary" onclick="finduser();">查   看</a>
								<a href="javascript:void(0);" id="uxz" style="display: none;" class="btn btn-primary" onclick="setuser();">新   增</a>
								<a href="javascript:void(0);" id="uxg" style="display: none;" class="btn btn-primary" onclick="edituser();">修   改</a>
								<a href="javascript:void(0);" id="usc" style="display: none;" class="btn btn-primary" onclick="deluser();">删   除</a>
						</form>
						<div class="widget-body-toolbar">
						</div>
						
						<table id="fuel_driver" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>姓名</th>
									<th>用户名</th>
									<th>岗位</th>
									<th>数据权限</th>
								</tr>
							</thead>
							<tbody id="drivertbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
<div id="finduser" title="查看">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
			<table>
					<tr><td width="100">
				<label>姓名:</label></td><td>
				<input class="form-control" id="compnamefind" disabled   type="text"></td></tr><tr><td>
				<label>用户名:</label></td><td>
				<input class="form-control" id="usernamefind" disabled  type="text"></td></tr><tr><td>
				<label>密码:</label></td><td>
				<input class="form-control" id="passwordfind"  disabled type="text"></td></tr><tr><td>
					<tr><td width="100">
				<label>岗位:</label>
					</td>
						<td ><input class="form-control" id="stationfind" disabled  type="text">
						</td>
					</tr>
				</table>
				</div>
						<div class="form-group">
							<label>数据权限:</label>
							<table>
								<tr>
									<td width="100">公司:</td>
									<td><input class="form-control" id="findba" disabled  type="text">
									</td>
								</tr>
									<tr>
									<td width="100" >分公司:</td>
									<td><input class="form-control" id="findcomp" disabled  type="text">
									</td>
								</tr><tr>
									<td width="100">车号:</td>
									<td><input class="form-control" id="findvehi" disabled  type="text">
									</td>
								</tr>
							</table>
						</div>
		</fieldset>
	</form>
</div>
<div id="adddriver" title="新增">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>姓名:</label>
				<input class="form-control" id="compnameadd"  placeholder="--输入姓名--" type="text">
			</div>
			<div class="form-group">
				<label>用户名:</label>
				<input class="form-control" id="usernameadd"  placeholder="--输入用户名--" type="text">
			</div>
			<div class="form-group">
				<label>密码:</label>
				<input class="form-control" id="passwordadd"  placeholder="--输入密码--" type="text">
			</div>
			<div class="form-group">
				<table>
					<tr><td width="100">
				<label>请选择岗位:</label>
					</td>
						<td ><select id="stationadd">
							</select> 
						</td>
					</tr>
				</table>
				</div>
						<div class="form-group">
							<label>数据权限:</label>
							<table>
								<tr>
									<td width="100">公司:</td>
									<td><select id="baadd" onchange="findcomp();">
										</select> 
									</td>
								</tr>
									<tr>
									<td width="100" >分公司:</td>
									<td><select id="compadd" onchange="findvehi();">
										<option value="所有分公司">--所有分公司--</option>
										</select> 
									</td>
								</tr><tr>
									<td width="100">车号:</td>
									<td><select id="vehiadd">
										<option value="所有车辆">--所有车辆--</option>
										</select> 
									</td>
								</tr>
							</table>
						</div>
		</fieldset>
	</form>
</div>
<div id="edituser" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<input type="hidden" id="useridedit"/>
			<div class="form-group">
				<label>姓名:</label>
				<input class="form-control" id="compnameedit"  placeholder="--输入姓名--" type="text">
			</div>
			<div class="form-group">
				<label>用户名:</label>
				<input class="form-control" id="usernameedit"  placeholder="--输入用户名--" type="text">
			</div>
			<div class="form-group">
				<label>密码:</label>
				<input class="form-control" id="passwordedit"  placeholder="--输入密码--" type="text">
			</div>
			<div class="form-group">
				<table>
					<tr><td width="100">
				<label>请选择岗位:</label>
					</td>
						<td ><select id="stationedit">
							</select> 
						</td>
					</tr>
				</table>
				</div>
						<div class="form-group">
							<label>数据权限:</label>
							<table>
								<tr>
									<td width="100">公司:</td>
									<td><select id="baedit" onchange="findcomp();">
										</select> 
									</td>
								</tr>
									<tr>
									<td width="100" >分公司:</td>
									<td><select id="compedit" onchange="findvehi();">
										<option value="所有分公司">--所有分公司--</option>
										</select> 
									</td>
								</tr><tr>
									<td width="100">车号:</td>
									<td><select id="vehiedit">
										<option value="所有车辆">--所有车辆--</option>
										</select> 
									</td>
								</tr>
							</table>
						</div>
		</fieldset>
	</form>
</div>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	//loadDataTableScripts_driver();
	finddriver();
	findstation();
	findba();
	// PAGE RELATED SCRIPTS
	var guanli=$("#stationadmin").val().split(',');
			for(var i=0;i<guanli.length;i++){
				if(guanli[i]=="用户管理增加"){
					$("#uxz").css('display','');
				}
				if(guanli[i]=="用户管理查看"){
					$("#uck").css('display','');
				}
				if(guanli[i]=="用户管理修改"){
					$("#uxg").css('display','');
				}
				if(guanli[i]=="用户管理删除"){
					$("#usc").css('display','');
				}
			}
	function finddriver(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'finduser.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			//if(drivers!=""){
			//	drivers.fnDestroy();
			//	} 
					var quanxian=json.list;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td><input type='checkbox' name='userid' value='"+quanxian[i].id+"' /></td>";
						tab=tab+"<td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].real_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].username+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_id+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].date_view_type+"</td>";
					}
					$("#drivertbody").html(tab);
					loadDataTableScripts_driver();
					}
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	
 
	//增加
	 function adduser(){
            var comp="";
            var vehi="";
           		comp=$("#compadd").val();
           		vehi=$("#vehiadd").val();
            var shuju= $("#baadd").val()+','+comp+','+vehi;
				$.ajax({
             type: "POST",
             url:"adduser.action",
             data:{
             	"username" : $("#usernameadd").val(),
             	"password" : $("#passwordadd").val(),
             	"date_view_type" : shuju,
             	"station_id" : $("#stationadd").val(),
             	"realname" : $("#compnameadd").val()
             },
            dataType: 'json',
			timeout : 180000,
             error: function(json) {
                 alert(json.info);
             },
             success: function(json) {
                alert(json.info);
                finddriver();
             }
         });		
     }
     //增加弹出窗口
	function setuser(){
			$("#compnameadd").val("");
            $("#usernameadd").val("");
            $("#passwordadd").val("");
   				var select = document.getElementById("baadd").options;
      			for(var i=0; i<select.length; i++) {
           		 	if(select[i].value == "所有公司") {
                    	select[i].selected = true;
                    	break;
            		}
      			}
            	var select1 = document.getElementById("compadd").options;
      			for(var i=0; i<select1.length; i++) {
           		 	if(select1[i].value == "所有分公司") {
                    	select1[i].selected = true;
                    	break;
            		}
      			}
            	var select2 = document.getElementById("vehiadd").options;
      			for(var i=0; i<select2.length; i++) {
           		 	if(select2[i].value == "所有车辆") {
                    	select2[i].selected = true;
                    	break;
            		}
      			}
      			var select3 = document.getElementById("stationadd").options;
      			for(var i=0; i<select.length; i++) {
           		 	if(select3[i].value == "0") {
                    	select3[i].selected = true;
                    	break;
            		}
      			}
			$('#adddriver').dialog('open');
		}
		$('#adddriver').dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 增加",
				"class" : "btn btn-danger",
				click : function() {
					adduser();
					$(this).dialog("close");
				}
			}]
	});
	//删除
	function deluser() {
		var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行删除!");
	}else{
    	var r = window.confirm("您确定要删除该条数据吗？");
    	if(r) {
    	 $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
     	$.ajax({
			url : 'deluser.action',
			type : 'post',
			data:{
				"id":s
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			alert(json.info);
			  finddriver();
			},
			error:function(){
				alert(json.info);
			}		
	});
     	}
     	$.unblockUI();
	 }
	 }
	 //修改弹出窗口
	function edituser(){
	var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行修改!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条数据!");
	}else{
     	$.ajax({
             type: "POST",
             url:"iduser.action",
             data:{
             	"id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
                $("#useridedit").val(json.user.id);
                $("#compnameedit").val(json.user.real_name);
                $("#usernameedit").val(json.user.username);
                $("#passwordedit").val(json.user.password);
                var shuju=json.user.date_view_type.split(',');
            	var select = document.getElementById("baedit").options;
            	var select1 = document.getElementById("compedit").options;
            	var select2 = document.getElementById("vehiedit").options;
      			for(var i=0; i<select.length; i++) {
           		 	if(select[i].value == shuju[0]) {
                    	select[i].selected = true;
                    	break;
            		}
      			}
      			for(var i=0; i<select1.length; i++) {
           		 	if(select1[i].value == shuju[1]) {
                    	select1[i].selected = true;
                    	break;
            		}
      			}
      			for(var i=0; i<select2.length; i++) {
           		 	if(select2[i].value == shuju[2]) {
                    	select2[i].selected = true;
                    	break;
            		}
      			}
             }
         });
		$('#edituser').dialog('open');
	}
	}
	$('#edituser').dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 修改",
				"class" : "btn btn-danger",
				click : function() {
					edituser1();
					$(this).dialog("close");
				}
			}]
	});
	//修改
	 function edituser1(){
     $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
      		 var comp="";
            var vehi="";
           if($("#compedit").val()==null){
           		comp="所有分公司";
           }else{
           		comp=$("#compedit").val();
           }
           if($("#vehiedit").val()==null){
           		vehi="所有车辆";
           }else{
           	vehi=$("#vehiedit").val();
           }
           var shuju= $("#baedit").val()+','+comp+','+vehi;
     	$.ajax({
             type: "POST",
             url:"edituser.action",
             data:{
             	"id" : $("#useridedit").val(),
             	"realname" : $("#compnameedit").val(),
             	"username" : $("#usernameedit").val(),
             	"password" : $("#passwordedit").val(),
             	"stationid"  : $("#stationedit").val(),
             	"date_view_type" : shuju,
             },
            dataType: 'json',
			timeout : 180000,
             error: function(json) {
                 alert(json.info);
             },
             success: function(json) {
                alert(json.info);
                finddriver();
             }
         });
     	$.unblockUI();
     }
     //查看
     function finduser(){
	var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行才查看!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条查看!");
	}else{
     	$.ajax({
             type: "POST",
             url:"iduser.action",
             data:{
             	"id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
                $("#compnamefind").val(json.user.real_name);
                $("#usernamefind").val(json.user.username);
                $("#passwordfind").val(json.user.password);
                $("#stationfind").val(json.user.station_id)
                var shuju=json.user.date_view_type.split(',');
            		$("#findba").val(shuju[0]);
            		$("#findcomp").val(shuju[1]);
            		$("#findvehi").val(shuju[2]);
             }
         });
		$('#finduser').dialog('open');
	}
	}
	$('#finduser').dialog({
		autoOpen : false,
		width : 350,
		resizable : false,
		modal : true,
	});
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
					var area=json.list1;
					$("#baadd").append("<option value = '所有公司' >--所有公司--</option>");
					$("#baedit").append("<option value = '所有公司' >--所有公司--</option>");
					for(var i=0;i<area.length;i++){
						$("#baadd").append("<option value='"+area[i].ba_name+"'>"+area[i].ba_name+"</option>");
						$("#baedit").append("<option value='"+area[i].ba_name+"'>"+area[i].ba_name+"</option>");
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
				"ba_idadd":$("#baadd").val()+','+$("#baedit").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#compadd").empty();
			$("#compedit").empty();
					var area=json.list2;
					$("#compadd").append("<option value = '所有分公司' >--所有分公司--</option>");
					$("#compedit").append("<option value = '所有分公司' >--所有分公司--</option>");
					for(var i=0;i<area.length;i++){
						$("#compadd").append("<option value='"+area[i].comp_name+"'>"+area[i].comp_name+"</option>");
						$("#compedit").append("<option value='"+area[i].comp_name+"'>"+area[i].comp_name+"</option>");
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
				"comp_idadd":$("#compadd").val()+','+$("#compedit").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#vehiadd").empty();
			$("#vehiedit").empty();
					var area=json.list3;
					$("#vehiadd").append("<option value = '所有车辆'>--所有车辆--</option>");
					$("#vehiedit").append("<option value = '所有车辆'>--所有车辆--</option>");
					for(var i=0;i<area.length;i++){
						$("#vehiadd").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
						$("#vehiedit").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findstation(){
		$.ajax({
			url : 'findstation.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#stationadd").empty();
			$("#stationedit").empty();
					var area=json.station;
					$("#stationadd").append("<option value = '0'>--请选择岗位--</option>");
					$("#stationedit").append("<option value = '0'>--请选择岗位--</option>");
					for(var i=0;i<area.length;i++){
						$("#stationadd").append("<option value='"+area[i].station_name+"'>"+area[i].station_name+"</option>");
						$("#stationedit").append("<option value='"+area[i].station_name+"'>"+area[i].station_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	$("#yhglym").html("用户管理");
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

	var drivers="";
	function loadDataTableScripts_driver() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_driver);
		}

	}

	function runDataTables_driver() {

		/*
		 * BASIC
		 */
		drivers=$('#fuel_driver').dataTable({
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

<script src="js/jquery.blockUI.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
