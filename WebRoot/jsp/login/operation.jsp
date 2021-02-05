<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height:100%;overflow: scroll">
<div  style="width: 70%;  position: relative;left: 30%;;">
<span style="font-size: 40px;font-weight: bold;">出租汽车营运态势</span></div>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false" style="width:1550px;">
			<header>
				<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
				<h2>上线率</h2>				
			</header>
		<div>
		<div class="jarviswidget-editbox">
			</div>
				<div class="widget-body no-padding">
					<table id="dtonline" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">上线率</th>
									<th>0:00</th>
									<th>1:00</th>
									<th>2:00</th>
									<th>3:00</th>
									<th>4:00</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="dtonlinetbody">
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</article>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false" style="width:1250px;">
			<header>
				<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
				<h2>在线营运率</h2>				
			</header>
		<div>
		<div class="jarviswidget-editbox">
			</div>
				<div class="widget-body no-padding">
					<table id="table_yingyun" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">在线营运率</th>
									<th>0:00</th>
									<th>1:00</th>
									<th>2:00</th>
									<th>3:00</th>
									<th>4:00</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="tbody_yingyuntable">
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</article>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false" style="width:1500px;">
			<header>
				<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
				<h2>一小时以上未营运车辆数</h2>				
			</header>
		<div>
		<div class="jarviswidget-editbox">
			</div>
				<div class="widget-body no-padding">
					<table id="table_wyingyun" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">未营运数</th>
									<th>0:00</th>
									<th>1:00</th>
									<th>2:00</th>
									<th>3:00</th>
									<th>4:00</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="tbody_wyingyun">
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</article>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false" style="width:1200px;">
			<header>
				<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
				<h2>重点监控区域车辆数</h2>				
			</header>
		<div>
		<div class="jarviswidget-editbox">
			</div>
				<div class="widget-body no-padding">
					<table id="table_zhongdian" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">车辆数</th>
									<th>0:00</th>
									<th>1:00</th>
									<th>2:00</th>
									<th>3:00</th>
									<th>4:00</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="tbody_zhongdian">
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</article>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
			<header>
				<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
				<h2>疑似停运车辆分析(自4点开始未营运车辆数量统计)</h2>				
			</header>
		<div>
		<div class="jarviswidget-editbox">
			</div>
				<div class="widget-body no-padding">
					<table id="table_tingyun" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap="nowrap">车辆数</th>
									<th>5:00</th>
									<th>6:00</th>
									<th>7:00</th>
									<th>8:00</th>
									<th>9:00</th>
									<th>10:00</th>
									<th>11:00</th>
									<th>12:00</th>
									<th>13:00</th>
									<th>14:00</th>
									<th>15:00</th>
									<th>16:00</th>
									<th>17:00</th>
									<th>18:00</th>
									<th>19:00</th>
									<th>20:00</th>
									<th>21:00</th>
									<th>22:00</th>
									<th>23:00</th>
								</tr>
							</thead>
							<tbody id="tbody_tingyun">
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</article>
</div>



<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	finddcshangxian();
	finddcyingyun();
	findareashul();
	findweiyingyun();
	findystingyun();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	//上线率
	function finddcshangxian(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'fingyingyun24.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.yy1[0];
					var bb = json.loadonline.yy1[1];
					var cc = json.loadonline.yy1[2];
					var dd = json.loadonline.yy1[3];
					var ee = json.list[0].pjyingyun;
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<dd.length;i++){
						if(dd[i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
						}else{
						tab+="<td style='background:#aac8ea'>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<cc.length;i++){
						if(cc[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+cc[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<bb.length;i++){
					if(bb[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+bb[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<aa.length;i++){
					if(aa[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+aa[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					$("#dtonlinetbody").html(tab);
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
		setTimeout(function(){$($('header')[1]).css('width',$('#dtonlinetbody').css('width'));},2000)
		
	}
	
	
	//在线营运率
	function finddcyingyun(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findyingyun.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var cc = json.loadonline.yy1[2];
					var dd = json.loadonline.yy1[3];
					var ee = json.loadonline.yy1[4];
					var ff = json.loadonline.yy1[5];
					var gg = json.list1[0].pjyingyun;
					var date = new Date();
					var hour = date.getHours();
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
						for(var i=0;i<ff.length;i++){
						if(ff[i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
						}else{
							tab+="<td style='background:#aac8ea'>"+ff[i]+"</td>";
							}
						}
						tab+="</tr>";
					
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd.length;i++){
					if(dd[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc.length;i++){
					if(cc[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+cc[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<gg.length;i++){
						if(gg[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+gg[i]+"</td>";
						} 
					}
					tab+="</tr>";
					$("#tbody_yingyuntable").html(tab);
					//loadDataTableScripts_yingyun();
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}

//重点监控区域车辆数量
function findareashul(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var q=$("#quyu").val();
		$("#chaxun").html(q);
			$.ajax({
			url : 'findareacishu.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var cc = json.area.jg[0][0].all;
					var dd = json.area.jg[1][0].all;
					var ee = json.area.jg[2][0].all;
					var ff = json.area.jg[3][0].all;
					var gg = json.cs[0];
					//var gg = json.cs[0];
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<ff.length;i++){
						if(ff[i]=="0"){
						tab+="<td style='background:#aac8ea'>&nbsp;</td>";
						}else{
						tab+="<td style='background:#aac8ea'>"+ff[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<dd.length;i++){
					if(dd[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<cc.length;i++){
					if(cc[i]=="0"){
						tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+cc[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<gg.sj.length;i++){
				 		if(gg.sj[i]=="0"){
							tab+="<td>&nbsp;</td>";
						}else{
						tab+="<td>"+gg.sj[i]+"</td>";
						} 
					}
					tab+="</tr>";
					$("#tbody_zhongdian").html(tab);
		$.unblockUI();
			},
			error:function(){
				
			}		
		});
	}
	
	//一小时未营运车辆数
function findweiyingyun(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'fingweiyingyun24.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.yy1[0];
					var bb = json.loadonline.yy1[1];
					var cc = json.loadonline.yy1[2];
					var dd = json.loadonline.yy1[3];
					var ee = json.list[0].pjyingyun;
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<dd.length;i++){
					if(dd[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td style='background:#aac8ea'>"+dd[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>昨天</td>";
					for(var i=0;i<cc.length;i++){
					if(cc[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+cc[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前天</td>";
					for(var i=0;i<bb.length;i++){
					if(bb[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+bb[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周同比</td>";
					for(var i=0;i<aa.length;i++){
					if(aa[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+aa[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>上周平均</td>";
					for(var i=0;i<ee.length;i++){
					if(ee[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+ee[i]+"</td>";
						}
					}
					tab+="</tr>";
					$("#tbody_wyingyun").html(tab);
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	
	//疑似停运车辆
function findystingyun(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'fingystingyun.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var aa = json.loadonline.yy1[0];
					var bb = json.loadonline.yy1[1];
					var tab="<tr><td nowrap='nowrap' style='background:#aac8ea'>今天</td>";
					for(var i=0;i<bb.length;i++){
					if(bb[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td style='background:#aac8ea'>"+bb[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>前三日平均</td>";
					for(var i=0;i<aa.length;i++){
					if(aa[i]=="0"){
					tab+="<td>&nbsp;</td>";
					}else{
						tab+="<td>"+aa[i]+"</td>";
						}
					}
					tab+="</tr>";
					tab+="<tr><td nowrap='nowrap'>疑似停运数量</td>";
					for(var i=0;i<aa.length;i++){
						//console.log(i+','+bb[i]+','+aa[i]+','+isNaN(bb[i])+','+(!isNaN(parseInt(bb[i]))?bb[i]:bb[i].replace(/<.*>(.)/,'$1').replace(/(.)<.*/,'$1')));
						if(bb[i]==0){
							tab+="<td>&nbsp;</td>";
						}else if(((!isNaN(parseInt(bb[i]))?bb[i]:bb[i].replace(/<.*>(.)/,'$1').replace(/(.)<.*/,'$1'))-aa[i])<0){
							tab+="<td>0</td>";
						}else{
							tab+="<td>"+((!isNaN(parseInt(bb[i]))?bb[i]:bb[i].replace(/<.*>(.)/,'$1').replace(/(.)<.*/,'$1'))-aa[i])+"</td>";
						}
					}
					tab+="</tr>";
					$("#tbody_tingyun").html(tab);
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}

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

	var shizais="";
	function loadDataTableScripts_shizai() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_shizai);
		}

	}

	function runDataTables_shizai() {

		/*
		 * BASIC
		 */
		shizais=$('#shizai').dataTable({
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
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
