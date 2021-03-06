<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/map.css">
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="actionidoper" value="${actionid }"/>
<input type="hidden" id="dataoper" value="${data }"/>
	<!-- row -->
<table border="0" style="width: 102%;">
	<tr>
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="clztj">车辆组统计</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:85px;float:left;">
										<label class="font" >
											<span style="font-size: 18px">开始时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="startTime4" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endTime4" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 140px;float:left;" >
								<label class="select">
									<select  id="group" class="select">
										<option value="0" >--资格证号--</option>
									</select> <i></i> 
								</label>
								</section>								
								<a href="javascript:void(0);" id ="search4" class="btn btn-primary" data-url="group.action" data-msg="group">查   询</a>
								<a href="gexcel.action" class="btn btn-primary" id="gro">导出</a>
						</form>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>																	
									<td nowrap="nowrap">群组</td>	
									<td nowrap="nowrap">车辆数</td>
									<td nowrap="nowrap">营运数</td>
									<td nowrap="nowrap">出车率</td>	
									<td nowrap="nowrap">金额(元)</td>
									<td nowrap="nowrap">次数(次)</td>	
									<td nowrap="nowrap">计程(公里)</td>
									<td nowrap="nowrap">空驶(公里)</td>
									<td nowrap="nowrap">总里程(公里)</td>
									<td nowrap="nowrap">实载率</td>
									<td nowrap="nowrap">载客时间(小时)</td>
									<td nowrap="nowrap">载客等候时间(小时)</td>			
								</tr>
							</thead>
							<tbody id="tb4">
								
							</tbody>
							
						</table>
							
					</div>
				 
					<div class="pagination">
							<span class="getData4" data-url="getGroup.action" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num4">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage4">0</a></strong> / <strong><a id="pageCount4">0</a></strong> 页							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage4(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage4(this.page-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage4(this.page+1);">下一页</a>
							<a class="STYLE19" href="javascript:getEndPage4(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage4" >页 <a class="STYLE19" href="javascript:getOnePage4();">go</a>							
    						
					</div>
				  </div>  
				  </div>
			
		</td>
	</tr>
</table>
</div>
<script type="text/javascript" src="/area3/js/util/util.js"></script>
<script type="text/javascript" src="/area3/js/map/map.js"></script>
<script type="text/javascript">
$("#clztj").html("车辆组统计");
</script>