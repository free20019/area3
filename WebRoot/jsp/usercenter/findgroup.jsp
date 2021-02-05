<script type="text/javascript" src="js/map/manage.js"></script>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
 <script type="text/javascript" src="js/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
 <div class="row"  style=" height:600px;overflow: scroll">
 <table border="0"  style="width: 102%;">
	<tr>
		<td height="50px;">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="clzgl">车辆组管理</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select  id="groupf" class="select">
											<option value="0" >--选择群组--</option>
										</select> <i></i> 
									</label>
									</section>
								<section class="col col-5" style="width: 200px;float:left;" >
								<label class="select">
									<select  id="branchf" class="select">
										<option value="0" >--选择公司--</option>
									</select> <i></i> 
								</label>
								</section>
								<section class="col col-5" style="width: 150px;float:left;" >
								<label class="select">
									<select  id="cardNof" class="select">
										<option value="0" >--选择车号--</option>
									</select> <i></i> 
								</label>
								</section>
								<section class="col col-5" style="width: 110px;float:left;" >
									<input type="text" style="width: 110px;height: 28px;color:gray" id="testf" placeholder="查找车号"/>
									</section>
								<a href="javascript:void(0);" id ="searchf" class="btn btn-primary" data-url="searchf.action" data-msg="searchf">查   询</a>
								<a href="findg.action" class="btn btn-primary" id ="find-group" >导出</a>
								<a href="javascript:void(0);"  id="add-groupf" class="btn btn-primary "><strong>添加车辆组</strong></a>
								<a href="javascript:void(0);"  id="update-groupf" class="btn btn-primary "><strong>修改车辆组</strong></a>
								<a href="javascript:void(0);"  id="delete-groupf" class="btn btn-primary "><strong>删除车辆组</strong></a>
						</form>		
		<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">编号</td>	
									<td nowrap="nowrap">群组</td>	
									<td nowrap="nowrap">分公司</td>
									<td nowrap="nowrap">车号</td>								
									<td nowrap="nowrap">车辆颜色</td>	
									<td nowrap="nowrap">终端类型</td>	
									<td nowrap="nowrap">车辆类型</td>	
								</tr>
							</thead>
							<tbody id="tbf">
								
							</tbody>
							
						</table>
	</div>
</div>
<div id="addgroupf" title="新增">
		<fieldset>
			<form action="upload.action">
		<table>
			<tr><td style="width: 260px;">
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>车辆组名:</label>
				<input class="form-control" id="vhicgroupadd"  placeholder="--输入车辆组名--" type="text">
			</div>
			
			</td>
			<td style="width:10px;">			</td>
			<td style="width: 200px;">
			<img src="js/AjaxFileUploaderV2.1/loading.gif" id="loading" style="display: none;">
			<input type="file" id="file" name="file" />
			</td> 
			<td style="width: 100px;">
			<input type="button" value="上传" onclick="ajaxFileUpload();">
			</td>
			<td style="width: 160px;">
			<input type="button" onclick="javascript:window.location='download.action';" value="导入车辆excle格式查看"/>
			<span id="daochu"></span></td></tr>
		</table>
			</form>
			
			<table style="float:left;">
				<tr><td><label>公司名:</label></td><td>分公司名</td><td>车号</td><td>按钮</td><td>车辆组车号</td></tr>
				<tr><td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-add">
						</select> </label>
				</section>
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="comp-add">
						</select> </label>
				</section>
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="vehicle-add">
						</select> </label>
				</section>
				</td>
				<td>
					&nbsp;&nbsp;<input name="" type="button" id="addall" style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="addone" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delone" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delall" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-add">
						</select> </label>
				</section>
				</td>
				</tr>
			</table>
		</fieldset>
</div>
<div id="editgroupf" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>车辆组名:</label>
				<input type="hidden" id="vhicgroupidedit">
				<input class="form-control" id="vhicgroupedit"  placeholder="--输入车辆组名--" type="text">
			</div>
			<table style="float:left;">
				<tr><td><label>公司名:</label></td><td>分公司名</td><td>车号</td><td>按钮</td><td>车辆组车号</td></tr>
				<tr><td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-edit">
						</select> </label>
				</section>
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="comp-edit">
						</select> </label>
				</section>
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="vehicle-edit">
						</select> </label>
				</section>
				</td>
				<td>
					&nbsp;&nbsp;<input id="addalls" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="addones" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delones" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delalls" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-edit">
						</select> </label>
				</section>
				</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
							
</div></td></tr></table></div>
<script type="text/javascript">
	$("#clzgl").html("车辆组管理");
</script>