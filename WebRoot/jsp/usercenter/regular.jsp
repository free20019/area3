  <script type="text/javascript" src="js/map/manage.js"></script>
<script type="text/javascript" src="js/util/regular.js"></script>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
 <script type="text/javascript" src="js/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
 <style>
input{
height: 30px;

}
</style>
<div class="row"  style=" height:600px;overflow: scroll">
 <table border="0"  style="width: 102%;">
	<tr>
		<td height="50px;">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="cgxxqk">常规下线情况</h2>
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
											<input id="startTimer" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endTimer" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<a href="javascript:void(0);" id ="regular-search" class="btn btn-primary" data-url="regularSearch.action" data-msg="regularSearch">查   询</a>
								<a href="regular.action" class="btn btn-primary" id ="regular" >导出</a>
								<a href="javascript:void(0);"  id="add-group" class="btn btn-primary "><strong>添加记录</strong></a>
								<a href="javascript:void(0);"  id="update-group" class="btn btn-primary "><strong>修改记录</strong></a>
								<a href="javascript:void(0);"  id="del-one" class="btn btn-primary "><strong>删除记录</strong></a>
						</form>		
		<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap"></td>
									<td nowrap="nowrap">编号</td>
									<td nowrap="nowrap">企业报停数量(辆)</td>								
									<td nowrap="nowrap">设备故障数量(辆)</td>	
									<td nowrap="nowrap">SIM卡故障数量(辆)</td>
									<td nowrap="nowrap">其他数量(辆)</td>		
									<td nowrap="nowrap">合计数量</td>	
									<td nowrap="nowrap">登记时间</td>	
									<td nowrap="nowrap">登记人</td>	
								</tr>
							</thead>
							<tbody id="tbs">
								
							</tbody>
							
						</table>
	</div>
</div>
<div id="addgroup" title="新增">
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>企业报停数量:</label>
				<input class="form-control" id="cpNum"  placeholder="--输入企业报停数量--" type="text">
			</div>
			<div class="form-group">
				<label>设备故障数量:</label>
				<input class="form-control" id="efNum"  placeholder="--输入设备故障数量--" type="text">
			</div>
			<div class="form-group">
				<label>SIM卡故障数量:</label>
				<input class="form-control" id="sim"   placeholder="--输入SIM卡故障数量--" type="text">
			</div>
			<div class="form-group">
				<label>其他数量:</label>
				<input class="form-control" id="other"  placeholder="--输入其他数量--" type="text">
			</div>
		</fieldset>
</div>
<div id="editgroup" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>企业报停数量:</label>
				<input class="form-control" id="cpNums"  placeholder="--输入企业报停数量--" type="text">
			</div>
			<div class="form-group">
				<label>设备故障数量:</label>
				<input class="form-control" id="efNums"  placeholder="--输入设备故障数量--" type="text">
			</div>
			<div class="form-group">
				<label>SIM卡故障数量:</label>
				<input class="form-control" id="sims"  placeholder="--输入SIM卡故障数量--" type="text">
			</div>
			<div class="form-group">
				<label>其他数量:</label>
				<input class="form-control" id="others"  placeholder="--输入其他数量--" type="text">
			</div>
		</fieldset>
	</form>
</div>
							
</div></td></tr></table></div>
<script type="text/javascript">
	$("#cgxxqk").html("常规下线情况");
</script>