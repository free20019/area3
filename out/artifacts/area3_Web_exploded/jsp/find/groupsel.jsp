<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/map.css">
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
					<h2 id="qzyyjy">群组营运交易查询</h2>
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
											<input id="startTime5" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endTime5" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select  id="group5" class="select">
											<option value="0" >--选择群组--</option>
										</select> <i></i> 
									</label>
									</section>						
								<section class="col col-5" style="width: 140px;float:left;" >
								<label class="select">
									<select  id="cardNo5" class="select">
										<option value="0" >--选择车号--</option>
									</select> <i></i> 
								</label>
								</section>
								<section class="col col-5" style="width: 110px;float:left;" >
									<input type="text" style="width: 80px;height: 28px;color:gray" id="test5" placeholder="查找车号"/>
									</section>
								<a href="javascript:void(0);" id ="search5" class="btn btn-primary" data-url="groupsel.action" data-msg="groupsel">查   询</a>
								<a href="gsexcel.action;" class="btn btn-primary" id="gpsel">导 出</a>
						</form>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>	
									<td nowrap="nowrap">车号</td>								
									<td nowrap="nowrap">资格证号</td>	
									<td nowrap="nowrap">上车时间</td>
									<td nowrap="nowrap">下车时间</td>	
									<td nowrap="nowrap">营运金额(元)</td>
									<td nowrap="nowrap">计程(公里)</td>
									<td nowrap="nowrap">空驶(公里)</td>									
									<td nowrap="nowrap">等候(秒)</td>
									<td nowrap="nowrap">交易类型</td>		
								</tr>
							</thead>
							<tbody id="tb5">
								
							</tbody>
							
						</table>
							
					</div>			
					<div class="pagination">
							<span class="getData5" data-url="getGroupsel.action" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num5">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage5">0</a></strong> / <strong><a id="pageCount5">0</a></strong> 页							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage5(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage5(this.page-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage5(this.page+1);">下一页</a>
							<a class="STYLE19" href="javascript:getEndPage5(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage5" >页 <a class="STYLE19" href="javascript:getOnePage5();">go</a>						
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
$("#qzyyjy").html("群组营运交易查询");
</script>