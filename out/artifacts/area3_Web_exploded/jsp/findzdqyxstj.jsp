<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/echarts/echarts.js"></script>
<script src="js/echarts/bar.js"></script>
<script src="js/echarts/line.js"></script>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="zdqyxdtj">杭州出租车重点区域小时服务车次统计(每小时统计)</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
					<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="zdqyxstjtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择时间--"/>
										</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findzdqyxstj();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findzdqyxstjexcle();">导   出</a><span id="zdqyxstjdaochu"></span>
						</form>
						<table id="zdqyxstjtable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap='nowrap'>序号</th>
									<th>区域</th>
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
							<tbody id="zdqyxstjtbody">
							</tbody>
						</table>
					</div>
				</div>
				<div id="zdqyxstjtb" class="chart has-legend"></div>
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
	var day = myDate.getDate();
	if(day.length<2){
		day="0"+day;
	}
	$("#zdqyxstjtime").val(a+"-"+b+"-"+day);
	findzdqyxstj();
	//loadDataTableScripts_zdqyxstjtable();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	
	
function findzdqyxstj(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findzdqyxstj.action',
			type : 'post',
			data:{
				"time" : $("#zdqyxstjtime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var fx = json.list;
				var tab="";
				var i=0;
				var beizhan=[];
				var xizhan=[];
				var jichang=[];
				var dongzhan=[];
				var chengzhan=[];
				var keyun=[];
				var nanzhan=[];
				if(fx!=null){
					for(i=0;i<fx.length;i++){
						if(fx[i].area=='杭州汽车北站'){
							beizhan.push(fx[i].a00);
							beizhan.push(fx[i].a01);
							beizhan.push(fx[i].a02);
							beizhan.push(fx[i].a03);
							beizhan.push(fx[i].a04);
							beizhan.push(fx[i].a05);
							beizhan.push(fx[i].a06);
							beizhan.push(fx[i].a07);
							beizhan.push(fx[i].a08);
							beizhan.push(fx[i].a09);
							beizhan.push(fx[i].a10);
							beizhan.push(fx[i].a11);
							beizhan.push(fx[i].a12);
							beizhan.push(fx[i].a13);
							beizhan.push(fx[i].a14);
							beizhan.push(fx[i].a15);
							beizhan.push(fx[i].a16);
							beizhan.push(fx[i].a17);
							beizhan.push(fx[i].a18);
							beizhan.push(fx[i].a19);
							beizhan.push(fx[i].a20);
							beizhan.push(fx[i].a21);
							beizhan.push(fx[i].a22);
							beizhan.push(fx[i].a23);
						}else if(fx[i].area=='杭州汽车西站'){
							xizhan.push(fx[i].a00);
							xizhan.push(fx[i].a01);
							xizhan.push(fx[i].a02);
							xizhan.push(fx[i].a03);
							xizhan.push(fx[i].a04);
							xizhan.push(fx[i].a05);
							xizhan.push(fx[i].a06);
							xizhan.push(fx[i].a07);
							xizhan.push(fx[i].a08);
							xizhan.push(fx[i].a09);
							xizhan.push(fx[i].a10);
							xizhan.push(fx[i].a11);
							xizhan.push(fx[i].a12);
							xizhan.push(fx[i].a13);
							xizhan.push(fx[i].a14);
							xizhan.push(fx[i].a15);
							xizhan.push(fx[i].a16);
							xizhan.push(fx[i].a17);
							xizhan.push(fx[i].a18);
							xizhan.push(fx[i].a19);
							xizhan.push(fx[i].a20);
							xizhan.push(fx[i].a21);
							xizhan.push(fx[i].a22);
							xizhan.push(fx[i].a23);
						}else if(fx[i].area=='机场'){
							jichang.push(fx[i].a00);
							jichang.push(fx[i].a01);
							jichang.push(fx[i].a02);
							jichang.push(fx[i].a03);
							jichang.push(fx[i].a04);
							jichang.push(fx[i].a05);
							jichang.push(fx[i].a06);
							jichang.push(fx[i].a07);
							jichang.push(fx[i].a08);
							jichang.push(fx[i].a09);
							jichang.push(fx[i].a10);
							jichang.push(fx[i].a11);
							jichang.push(fx[i].a12);
							jichang.push(fx[i].a13);
							jichang.push(fx[i].a14);
							jichang.push(fx[i].a15);
							jichang.push(fx[i].a16);
							jichang.push(fx[i].a17);
							jichang.push(fx[i].a18);
							jichang.push(fx[i].a19);
							jichang.push(fx[i].a20);
							jichang.push(fx[i].a21);
							jichang.push(fx[i].a22);
							jichang.push(fx[i].a23);
						}else if(fx[i].area=='火车东站'){
							dongzhan.push(fx[i].a00);
							dongzhan.push(fx[i].a01);
							dongzhan.push(fx[i].a02);
							dongzhan.push(fx[i].a03);
							dongzhan.push(fx[i].a04);
							dongzhan.push(fx[i].a05);
							dongzhan.push(fx[i].a06);
							dongzhan.push(fx[i].a07);
							dongzhan.push(fx[i].a08);
							dongzhan.push(fx[i].a09);
							dongzhan.push(fx[i].a10);
							dongzhan.push(fx[i].a11);
							dongzhan.push(fx[i].a12);
							dongzhan.push(fx[i].a13);
							dongzhan.push(fx[i].a14);
							dongzhan.push(fx[i].a15);
							dongzhan.push(fx[i].a16);
							dongzhan.push(fx[i].a17);
							dongzhan.push(fx[i].a18);
							dongzhan.push(fx[i].a19);
							dongzhan.push(fx[i].a20);
							dongzhan.push(fx[i].a21);
							dongzhan.push(fx[i].a22);
							dongzhan.push(fx[i].a23);
						}else if(fx[i].area=='杭州城站'){
							chengzhan.push(fx[i].a00);
							chengzhan.push(fx[i].a01);
							chengzhan.push(fx[i].a02);
							chengzhan.push(fx[i].a03);
							chengzhan.push(fx[i].a04);
							chengzhan.push(fx[i].a05);
							chengzhan.push(fx[i].a06);
							chengzhan.push(fx[i].a07);
							chengzhan.push(fx[i].a08);
							chengzhan.push(fx[i].a09);
							chengzhan.push(fx[i].a10);
							chengzhan.push(fx[i].a11);
							chengzhan.push(fx[i].a12);
							chengzhan.push(fx[i].a13);
							chengzhan.push(fx[i].a14);
							chengzhan.push(fx[i].a15);
							chengzhan.push(fx[i].a16);
							chengzhan.push(fx[i].a17);
							chengzhan.push(fx[i].a18);
							chengzhan.push(fx[i].a19);
							chengzhan.push(fx[i].a20);
							chengzhan.push(fx[i].a21);
							chengzhan.push(fx[i].a22);
							chengzhan.push(fx[i].a23);
						}else if(fx[i].area=='杭州汽车客运中心站'){
							keyun.push(fx[i].a00);
							keyun.push(fx[i].a01);
							keyun.push(fx[i].a02);
							keyun.push(fx[i].a03);
							keyun.push(fx[i].a04);
							keyun.push(fx[i].a05);
							keyun.push(fx[i].a06);
							keyun.push(fx[i].a07);
							keyun.push(fx[i].a08);
							keyun.push(fx[i].a09);
							keyun.push(fx[i].a10);
							keyun.push(fx[i].a11);
							keyun.push(fx[i].a12);
							keyun.push(fx[i].a13);
							keyun.push(fx[i].a14);
							keyun.push(fx[i].a15);
							keyun.push(fx[i].a16);
							keyun.push(fx[i].a17);
							keyun.push(fx[i].a18);
							keyun.push(fx[i].a19);
							keyun.push(fx[i].a20);
							keyun.push(fx[i].a21);
							keyun.push(fx[i].a22);
							keyun.push(fx[i].a23);
						}else if(fx[i].area=='杭州汽车南站'){
							nanzhan.push(fx[i].a00);
							nanzhan.push(fx[i].a01);
							nanzhan.push(fx[i].a02);
							nanzhan.push(fx[i].a03);
							nanzhan.push(fx[i].a04);
							nanzhan.push(fx[i].a05);
							nanzhan.push(fx[i].a06);
							nanzhan.push(fx[i].a07);
							nanzhan.push(fx[i].a08);
							nanzhan.push(fx[i].a09);
							nanzhan.push(fx[i].a10);
							nanzhan.push(fx[i].a11);
							nanzhan.push(fx[i].a12);
							nanzhan.push(fx[i].a13);
							nanzhan.push(fx[i].a14);
							nanzhan.push(fx[i].a15);
							nanzhan.push(fx[i].a16);
							nanzhan.push(fx[i].a17);
							nanzhan.push(fx[i].a18);
							nanzhan.push(fx[i].a19);
							nanzhan.push(fx[i].a20);
							nanzhan.push(fx[i].a21);
							nanzhan.push(fx[i].a22);
							nanzhan.push(fx[i].a23);
						}
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].area+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a00+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a01+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a02+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a03+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a04+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a05+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a06+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a07+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a08+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a09+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a10+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a11+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a12+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a13+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a14+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a15+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a16+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a17+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a18+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a19+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a20+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a21+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a22+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].a23+"</td></tr>";
					}
						zdqyxstjtb(beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan);
						$("#zdqyxstjtbody").html(tab);
	$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}

function zdqyxstjtb(beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan){
		  // 使用
         require(
            [
                'echarts',
                'echarts/chart/line', // 
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('zdqyxstjtb')); 
			
              option = {
              grid:{
					x:40,y:10,x2:10,y2:30
				},
			    title : {
			        text: '',
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['汽车北站','汽车西站','机场','火车东站','城站','客运中心','汽车南站']
			    },
			    toolbox: {
			        show : false,
			       
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			        {
			            name:'汽车北站',
			            type:'line',
			            data:beizhan,
			        },
			        {
			            name:'汽车西站',
			            type:'line',
			            data:xizhan,
			        },
			        {
			            name:'机场',
			            type:'line',
			            data:jichang,
			        },
			        {
			            name:'火车东站',
			            type:'line',
			            data:dongzhan,
			        },
			        {
			            name:'城站',
			            type:'line',
			            data:chengzhan,
			        },
			        {
			            name:'客运中心',
			            type:'line',
			            data:keyun,
			        },
			        {
			            name:'汽车南站',
			            type:'line',
			            data:nanzhan,
			        }
			    ]
			};
      
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
	}


//导出excle
	function findzdqyxstjexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findzdqyxstjexcle.action',
		type : 'post',
		data:{
			"time" : $("#zdqyxstjtime").val()
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
$("#zdqyxstj").html("杭州出租车重点区域小时服务车次统计(每小时统计)");
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

	var zdqyxstjtables="";
	function loadDataTableScripts_zdqyxstjtable() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_zdqyxstjtable);
		}

	}

	function runDataTables_zdqyxstjtable() {

		/*
		 * BASIC
		 */
		zdqyxstjtables=$('#zdqyxstjtable').dataTable({
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
