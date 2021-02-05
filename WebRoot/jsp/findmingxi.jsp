﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<style>
.mxsj a:link {color: #ff0012}
.mxsj a:visited {color: #1800ff}
.mxsj a:hover {color: #00ff30}
.mxsj a:active {color: #fff000}
</style>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" style="height:600px;overflow:scroll" >
	<!-- row -->
<table border="1" style="width: 102%;height: 95px;" >
<input type="hidden" id="zhi"/>
	<tr style="height: 130px;overflow: auto;" valign="top">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<!-- widget div-->
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="clmicx">出租汽车重点监控区域车辆明细查询</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:160px;float:left;">
										<label class="select">
											<select  id="mingxiquyu">
											<option value="所有区域">所有区域</option>
											</select> <i></i> </label>
									</section>
									<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="day" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"/>
										</label>
								</section>
								<section style="width:120px;float:left;">
										<label class="input"> 
											 <input id="speed" type="text"  placeholder="--请输入车辆速度--"></input>
										</label>
									</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="findmingxi();">查   询</a>
								<a href="javascript:void(1);"   class="btn btn-primary"  onclick="findmingxiexcle();">导   出</a><span id="mingxidaochu"></span>
						</form>
						
						<table id="mingxi" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
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
							<tbody id="mingxitbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
<section  class="">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
<table id="jtnr" class="table table-striped table-bordered table-hover" >
							<thead>
								<tr>
									<th>序号</th>
									<th>公司</th>
									<th>所在区域</th>
									<th>车号</th>
									<th>SIM卡号</th>
									<th>联系人</th>
									<th>联系电话</th>
								</tr>
							</thead>
							<tbody id="jtnrtbody">
							</tbody>
						</table>
						</article>
</section>
</div><br>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	findquyu();
	shijian();
	findmingxi();
	function shijian(){
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
		$("#day").val(time);
	};
	function findquyu(){
	$.ajax({
			url : 'area.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list;
					for(var i=0;i<area.length;i++){
						$("#mingxiquyu").append("<option value='"+area[i].area_name+"' >"+area[i].area_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	
	function findmingxi(){
	var q=$("#mingxiquyu").val();
	var d=$("#day").val();
	if(q=="0"){
		alert("必须选择一个区域才能查询");
	}else{
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findbreakdown.action',
			type : 'post',
			data:{
				"quyu" : $("#mingxiquyu").val(),
				"day" : $("#day").val(),
				"speed" : $("#speed").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.list1;
					var tab="<tr>";
					for(var i=0;i<aa.length;i++){
						if(aa[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td ><div class='mxsj'><a style='cursor:pointer;'  onclick='cllb("+i+")'>"+aa[i]+"</a><div></td>";
					}}
					tab+="</tr>";
					$.unblockUI();
					$("#mingxitbody").html(tab);
					var myDate = new Date();
					var hour =myDate.getHours();
					var minutes=myDate.getMinutes();
					var obj=0;
					if((hour==0&&minutes>=0&&minutes<=33)||(hour==23&&minutes>=57&&minutes<=59)){
						i=0;
					}else if((hour==0&&minutes>33&&minutes<=59)||(hour==1&&minutes>=0&&minutes<=3)){
						i=1;
					}else if((hour==1&&minutes>=1&&minutes<=33)||(hour==0&&minutes>=57&&minutes<=59)){
						i=2;
					}else if((hour==1&&minutes>33&&minutes<=59)||(hour==2&&minutes>=0&&minutes<=3)){
						i=3;
					}else if((hour==2&&minutes>=1&&minutes<=33)||(hour==1&&minutes>=57&&minutes<=59)){
						i=4;
					}else if((hour==2&&minutes>33&&minutes<=59)||(hour==3&&minutes>=0&&minutes<=3)){
						i=5;
					}else if((hour==3&&minutes>=1&&minutes<=33)||(hour==2&&minutes>=57&&minutes<=59)){
						i=6;
					}else if((hour==3&&minutes>33&&minutes<=59)||(hour==4&&minutes>=0&&minutes<=3)){
						i=7;
					}else if((hour==4&&minutes>=1&&minutes<=33)||(hour==3&&minutes>=57&&minutes<=59)){
						i=8;
					}else if((hour==4&&minutes>33&&minutes<=59)||(hour==5&&minutes>=0&&minutes<=3)){
						i=9;
					}else if((hour==5&&minutes>=1&&minutes<=33)||(hour==4&&minutes>=57&&minutes<=59)){
						i=10;
					}else if((hour==5&&minutes>33&&minutes<=59)||(hour==6&&minutes>=0&&minutes<=3)){
						i=11;
					}else if((hour==6&&minutes>=1&&minutes<=33)||(hour==5&&minutes>=57&&minutes<=59)){
						i=12;
					}else if((hour==6&&minutes>33&&minutes<=59)||(hour==7&&minutes>=0&&minutes<=3)){
						i=13;
					}else if((hour==7&&minutes>=1&&minutes<=33)||(hour==6&&minutes>=57&&minutes<=59)){
						i=14;
					}else if((hour==7&&minutes>33&&minutes<=59)||(hour==8&&minutes>=0&&minutes<=3)){
						i=15;
					}else if((hour==8&&minutes>=1&&minutes<=33)||(hour==7&&minutes>=57&&minutes<=59)){
						i=16;
					}else if((hour==8&&minutes>33&&minutes<=59)||(hour==9&&minutes>=0&&minutes<=3)){
						i=17;
					}else if((hour==9&&minutes>=1&&minutes<=33)||(hour==8&&minutes>=57&&minutes<=59)){
						i=18;
					}else if((hour==9&&minutes>33&&minutes<=59)||(hour==10&&minutes>=0&&minutes<=3)){
						i=19;
					}else if((hour==10&&minutes>=1&&minutes<=33)||(hour==9&&minutes>=57&&minutes<=59)){
						i=20;
					}else if((hour==10&&minutes>33&&minutes<=59)||(hour==11&&minutes>=0&&minutes<=3)){
						i=21;
					}else if((hour==11&&minutes>=1&&minutes<=33)||(hour==10&&minutes>=57&&minutes<=59)){
						i=22;
					}else if((hour==11&&minutes>33&&minutes<=59)||(hour==12&&minutes>=0&&minutes<=3)){
						i=23;
					}else if((hour==12&&minutes>=1&&minutes<=33)||(hour==11&&minutes>=57&&minutes<=59)){
						i=24;
					}else if((hour==12&&minutes>33&&minutes<=59)||(hour==13&&minutes>=0&&minutes<=3)){
						i=25;
					}else if((hour==13&&minutes>=1&&minutes<=33)||(hour==12&&minutes>=57&&minutes<=59)){
						i=26;
					}else if((hour==13&&minutes>33&&minutes<=59)||(hour==14&&minutes>=0&&minutes<=3)){
						i=27;
					}else if((hour==14&&minutes>=1&&minutes<=33)||(hour==13&&minutes>=57&&minutes<=59)){
						i=28;
					}else if((hour==14&&minutes>33&&minutes<=59)||(hour==15&&minutes>=0&&minutes<=3)){
						i=29;
					}else if((hour==15&&minutes>=1&&minutes<=33)||(hour==14&&minutes>=57&&minutes<=59)){
						i=30;
					}else if((hour==15&&minutes>33&&minutes<=59)||(hour==16&&minutes>=0&&minutes<=3)){
						i=31;
					}else if((hour==16&&minutes>=1&&minutes<=33)||(hour==15&&minutes>=57&&minutes<=59)){
						i=32;
					}else if((hour==16&&minutes>33&&minutes<=59)||(hour==17&&minutes>=0&&minutes<=3)){
						i=33;
					}else if((hour==17&&minutes>=1&&minutes<=33)||(hour==16&&minutes>=57&&minutes<=59)){
						i=34;
					}else if((hour==17&&minutes>33&&minutes<=59)||(hour==18&&minutes>=0&&minutes<=3)){
						i=35;
					}else if((hour==18&&minutes>=1&&minutes<=33)||(hour==17&&minutes>=57&&minutes<=59)){
						i=36;
					}else if((hour==18&&minutes>33&&minutes<=59)||(hour==19&&minutes>=0&&minutes<=3)){
						i=37;
					}else if((hour==19&&minutes>=1&&minutes<=33)||(hour==18&&minutes>=57&&minutes<=59)){
						i=38;
					}else if((hour==19&&minutes>33&&minutes<=59)||(hour==20&&minutes>=0&&minutes<=3)){
						i=39;
					}else if((hour==20&&minutes>=1&&minutes<=33)||(hour==19&&minutes>=57&&minutes<=59)){
						i=40;
					}else if((hour==20&&minutes>33&&minutes<=59)||(hour==21&&minutes>=0&&minutes<=3)){
						i=41;
					}else if((hour==21&&minutes>=1&&minutes<=33)||(hour==20&&minutes>=57&&minutes<=59)){
						i=42;
					}else if((hour==21&&minutes>33&&minutes<=59)||(hour==22&&minutes>=0&&minutes<=3)){
						i=43;
					}else if((hour==22&&minutes>=1&&minutes<=33)||(hour==21&&minutes>=57&&minutes<=59)){
						i=44;
					}else if((hour==22&&minutes>33&&minutes<=59)||(hour==23&&minutes>=0&&minutes<=3)){
						i=45;
					}else if((hour==23&&minutes>=1&&minutes<=33)||(hour==22&&minutes>=57&&minutes<=59)){
						i=46;
					}else if((hour==23&&minutes>33&&minutes<=59)){
						i=47;
					}
					$("#zhi").val(i);
					cllb(i);
			},
			error:function(){
				
			}		
		});
		}
	}
	
	function cllb(i){
		$('#mingxitbody a').css('color','blue')
		$($('#mingxitbody a')[i]).css('color','red')
		$. blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findspecinfo.action',
			type : 'post',
			data:{
				"quyu" : $("#mingxiquyu").val(),
				"time" : $("#day").val(),
				"i"    :  i,
				"speed" : $("#speed").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var jt = json.list2;
				var tab="";
				if(jt!=null){
					for(var i=0;i<jt.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].comp_id+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].ba_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+jt[i].own_tel+"</td></tr>";
					}
						$("#jtnrtbody").html(tab);
	$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
	}
	
		function findmingxiexcle(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findmingxiexcle.action',
		type : 'post',
		data:{
			"quyu" : $("#mingxiquyu").val(),
				"time" : $("#day").val(),
				"i"    :  $("#zhi").val(),
				"speed" : $("#speed").val()
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
$("#clmicx").html("出租汽车重点监控区域车辆明细查询");
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

	var mingxis="";
	function loadDataTableScripts_mingxi() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_mingxi);
		}

	}

	function runDataTables_mingxi() {

		/*
		 * BASIC
		 */
		mingxis=$('#mingxi').dataTable({
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
<style type="text/css">

</style>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
