<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/map.css">
<script type="text/javascript" src="/area3/js/util/util.js"></script>
<script type="text/javascript" src="/area3/js/map/map.js"></script>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="actionidoper" value="${actionid }"/>
<input type="hidden" id="dataoper" value="${data }"/>
	<!-- row -->
<table border="0" style="width: 102%;" >
	<tr>
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="cyrytj">从业人员营运数据统计</h2>
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
											<input id="startTime2" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endTime2" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 130px;float:left;" >
									<label class="select">
										<select  id="company2" class="select">
											<option value="0" >--选择公司--</option>
										</select> <i></i> 
									</label>
									</section>
								<section class="col col-5" style="width: 130px;float:left;" >
								<label class="select">
									<select  id="branch2" class="select">
										<option value="0" >--选择分公司--</option>
									</select> <i></i> 
								</label>
								</section>
								<section class="col col-5" style="width: 110px;float:left;" >
								<label class="select">
									<select  id="certNo" class="select">
										<option value="0" >--资格证号--</option>
									</select> <i></i> 
								</label>
								</section>
								<section class="col col-5" style="width: 110px;float:left;" >
									<input type="text" style="width: 80px;height: 28px ;color:gray" id="test2" placeholder="查找资格证号"/>
									</section>
								<a href="javascript:void(0);" id ="search2" class="btn btn-primary" data-url="certNo.action" data-msg="certno">查   询</a>
								<a href="cexcel.action;" class="btn btn-primary" id="cert">导 出</a>
						</form>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>								
									<td nowrap="nowrap">所属公司</td>
									<td nowrap="nowrap">所属分公司</td>
									<td nowrap="nowrap">资格证号</td>	
									<td nowrap="nowrap">营运金额(元)</td>
									<td nowrap="nowrap">次数(次)</td>	
									<td nowrap="nowrap">计程(公里)</td>
									<td nowrap="nowrap">空驶(公里)</td>
									<td nowrap="nowrap">总里程(公里)</td>
									<td nowrap="nowrap">实载率</td>
									<td nowrap="nowrap">载客时间(小时)</td>
									<td nowrap="nowrap">载客等候时间(小时)</td>			
								</tr>
							</thead>
							<tbody id="tb2">
								
							</tbody>
							
						</table>
							
					</div>
				 
					<div class="pagination">
							<span class="getData2" data-url="getByCertNo.action" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num2">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage2">0</a></strong> / <strong><a id="pageCount2">0</a></strong> 页
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage2(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage2(this.page-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage2(this.page+1);">下一页</a>
							<a class="STYLE19" href="javascript:getEndPage2(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage2" >页 <a class="STYLE19" href="javascript:getOnePage2();">go</a>
							
					</div>
				  </div>  
				  </div>
			
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
$("#cyrytj").html("从业人员营运数据统计");
</script>