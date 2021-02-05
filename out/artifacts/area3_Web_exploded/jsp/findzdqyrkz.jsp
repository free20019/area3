<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<script src="js/echarts/echarts.js"></script>
<script src="js/echarts/bar.js"></script>
<script src="js/echarts/line.js"></script>
<div class="row" style="height:600px;overflow: scroll">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="zdqyrkz">杭州出租车重点区域服务车次(每日统计)</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:130px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="zdqyrkztime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})()"></input>
										</label>
									</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findzdqyrkzexcle();">导   出</a>
						</form>
						<table id="zdqyrkztable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th rowspan="2">序号</th>
									<th rowspan="2" style="text-align: center;">时间</th>
									<th colspan="3" style="text-align: center;">火车东站</th>
									<th colspan="3" style="text-align: center;">机场</th>
									<th colspan="3" style="text-align: center;">城站</th>
									<th colspan="3" style="text-align: center;">汽车客运中心站</th>
									<th colspan="3" style="text-align: center;">汽车北站</th>
									<th colspan="3" style="text-align: center;">汽车南站</th>
									<th colspan="3" style="text-align: center;">汽车西站</th>
								</tr>
								<tr>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
									<th style="text-align: center;">总数</th>
									<th style="text-align: center;">下客</th>
									<th style="text-align: center;">上客</th>
								</tr>
							</thead>
							<tbody id="zdqyrkztbody">
							</tbody>
						</table>

					</div>
				</div>
				<div id="zdqyrkztb" class="chart has-legend"></div>
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
	$("#zdqyrkztime").val(a+"-"+b);
	findzdqyrkz();
	//loadDataTableScripts_zdqyrkztable();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	
	
function findzdqyrkz(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findzdqyrkz.action',
			type : 'post',
			data:{
				"time" : $("#zdqyrkztime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var fx = json.list;
				var tab="";
				var i=0;var beizhan=[];
				var xizhan=[];
				var jichang=[];
				var dongzhan=[];
				var chengzhan=[];
				var keyun=[];
				var nanzhan=[];
				var time1 =[];
				//$('#nolinetable').dataTable().fnDestroy(); 
				if(fx!=null){
					for(i=0;i<fx.length;i++){
						if(fx[i].dongzhan!=null&&fx[i].dongzhanz!=null){
							time1.push(fx[i].time.substr(5,5));
							dongzhan.push(parseInt(fx[i].dongzhan)+parseInt(fx[i].dongzhanz));
							jichang.push(parseInt(fx[i].jichang)+parseInt(fx[i].jichangz));
							chengzhan.push(parseInt(fx[i].chengzhan)+parseInt(fx[i].chengzhanz));
							keyun.push(parseInt(fx[i].keyun)+parseInt(fx[i].keyunz));
							beizhan.push(parseInt(fx[i].beizhan)+parseInt(fx[i].beizhanz));
							nanzhan.push(parseInt(fx[i].nanzhan)+parseInt(fx[i].nanzhanz));
							xizhan.push(parseInt(fx[i].xizhan)+parseInt(fx[i].xizhanz));
							tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].time+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].dongzhan)+parseInt(fx[i].dongzhanz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].dongzhan+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].dongzhanz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].jichang)+parseInt(fx[i].jichangz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].jichang+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].jichangz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].chengzhan)+parseInt(fx[i].chengzhanz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].chengzhan+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].chengzhanz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].keyun)+parseInt(fx[i].keyunz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].keyun+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].keyunz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].beizhan)+parseInt(fx[i].beizhanz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].beizhan+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].beizhanz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].nanzhan)+parseInt(fx[i].nanzhanz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].nanzhan+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].nanzhanz+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+(parseInt(fx[i].xizhan)+parseInt(fx[i].xizhanz))+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].xizhan+"</td>";
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+fx[i].xizhanz+"</td></tr>";
						}
					}
						$("#zdqyrkztbody").html(tab);
						zdqyrkztb(time1,beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan)
	$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}

function zdqyrkztb(time1,beizhan,xizhan,jichang,dongzhan,chengzhan,keyun,nanzhan){
	  // 使用
   require(
      [
          'echarts',
          'echarts/chart/line', // 
			'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
      ],
      function (ec) {
          // 基于准备好的dom，初始化echarts图表
          var myChart = ec.init(document.getElementById('zdqyrkztb')); 
		
        option = {
        grid:{
				x:40,y:10,x2:20,y2:30
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
		            data : time1
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
	function findzdqyrkzexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findzdqyrkzexcle.action',
		type : 'post',
		data:{
			"time" : $("#zdqyrkztime").val()
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
$("#zdqyrkz").html("杭州出租车重点区域服务车次(每日统计)");
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

	var zdqyrkztables="";
	function loadDataTableScripts_zdqyrkztable() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_zdqyrkztable);
		}

	}

	function runDataTables_zdqyrkztable() {

		/*
		 * BASIC
		 */
		zdqyrkztables=$('#zdqyrkztable').dataTable({
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
