<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<!-- widget grid -->
<input type="hidden" id="compid" value="${date}">
<input type="hidden" id="userid" value="${userid}">
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv2"><div id="data-div-area" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
		<!-- 
		<div id="tip"> 
		<a href="javascript:void(0);" class="btn btn-primary" onclick="addarea();">添加区域</a>&nbsp;&nbsp;
		</div>
		<div id="tip2" style="display: none"> 
		<a href="javascript:void(0);" class="btn btn-primary" onclick="addarea();">完成编辑区域</a>&nbsp;&nbsp;
		</div>
		 -->
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar2() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod2"><input type="hidden" id="switchPoint2" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>	
		
	<div style="width: 100%;height: 35%;overflow: auto;" id="downdiv2">
			<div style="overflow: auto;" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2 id="qyxq">区域详细 &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="btn btn-primary" onclick="addarea();">添加区域</a></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body">
						<form id="areaform">
						<table id="areatable" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th nowrap="nowrap">序号</th>
									<th nowrap="nowrap">区域名称</th>
									<th nowrap="nowrap">区域描述</th>
									<th nowrap="nowrap">区域面积</th>
									<th nowrap="nowrap">区域预报警数</th>
									<th nowrap="nowrap">操作</th>
								</tr>
								
							</thead>
							<tbody id="areadata">
							</tbody>
						</table>
					</form>
					</div>
				</div>
			</div>
			</div>
</div>

<div id="dialog_area" title="区域详情">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areaname">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">预报警数:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areabjs" value="10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="areams"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areasize" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="areazbs" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>

<div id="area_edit" title="区域修改">
	<p>
	<input type="hidden" id="areaidedit" value="">
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td >
		<label class="input"  > 
			<input type="text" name=""  id="areanameedit">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">预报警数:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areabjsedit">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="areamsedit"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areasizeedit" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="areazbsedit" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	initializearea();
	
	var areamap;
	var polygonOption = {
		    strokeColor:"#000033",	
		    strokeOpacity:1,
		    strokeWeight:2
		};
	function initializearea() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		areamap=new AMap.Map("data-div-area",{center:position,level:14,resizeEnable:true});//创建地图实例
		areamap.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
			  //加载工具条
			  tool=new AMap.ToolBar({
			    direction:false,//隐藏方向导航
			    ruler:false,//隐藏视野级别控制尺
			    autoPosition:false//禁止自动定位
			  });
			  areamap.addControl(tool);
			  //加载鹰眼
			  view=new AMap.OverView();
			  areamap.addControl(view);
			  //加载比例尺
			  scale=new AMap.Scale();
			  areamap.addControl(scale);
			});

		areamap.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				areamap.addControl(type);
			});

	}
</script>
<script type="text/javascript">
var h=0;
queryarea();
function addarea(){
	if(mouseTool!=null){
		alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
	}else{
		areamap.plugin(["AMap.MouseTool"],function(){ 
		mouseTool = new AMap.MouseTool(areamap); 
		mouseTool.polygon(polygonOption);   //使用鼠标工具绘制多边形
		AMap.event.addListener(mouseTool, "draw", function(e){
			var drawObj = e.obj;  
			var pointsCount = e.obj.getPath().length; 
			var a =  e.obj.getPath();
			var zbs = "";
			for(var i=0;i<pointsCount;i++){
				if(i==pointsCount-1){
					zbs+=a[i]
					}else{
				zbs+=a[i]+";";}
			}
			$("#areazbs").val(zbs);
			$("#areasize").val(drawObj.getArea()+"平方米");
			$('#dialog_area').dialog('open');
			//alert("多边形节点数：" + pointsCount + "<br>节点坐标："+e.obj.getPath());
		});
		});
	}
}
var mouseTool=null;
$('#dialog_area').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "保存区域",
		"class" : "btn btn-danger",
		click : function() {
			if(mouseTool!=null){
				mouseTool.close(true);
			}
			mouseTool=null;
			savearea();
			$(this).dialog("close");
			resettip();
		}
	}, {
		html : "取消添加",
		"class" : "btn btn-default",
		click : function() {
			if(mouseTool!=null){
				mouseTool.close(true);
			}
			mouseTool=null;
			$(this).dialog("close");
			resettip();
		}
	}]
});

$('#area_edit').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "修改",
		"class" : "btn btn-danger",
		click : function() {
			updatearea();
			$(this).dialog("close");
		}
	}, {
		html : "取消",
		"class" : "btn btn-default",
		click : function() {
			$(this).dialog("close");
		}
	}]
});
function savearea(){
	if($("#areaname").val()==""){
		alert("请输入区域名称");
	}else{
		if($("#areabjs").val()==""){
			alert("请输入区域预报警数量");
		}else{
			if($("#areams").val()==""){
				alert("请输入区域描述");
			}else{
				$.ajax({
					url : 'addarea.action',
					type : 'post',
					data:{
						"areaname" : $("#areaname").val(),
						"areabjs" : $("#areabjs").val(),
						"areams" : $("#areams").val(),
						"areazbs" : $("#areazbs").val(),
						"areasize" : $("#areasize").val()
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
						if(json.message==""){
							alert("添加成功");
							areamap.clearMap();
							queryarea();
						}else{
							alert(json.message);
						}
					},
					error:function(){
					}		
				});
			}
		}
	}
}
var arealist=null;
function queryarea(){
	$.ajax({
		url : 'queryarea.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			arealist = json.arealist;
			var trdata="";
			for(var i=0;i<arealist.length;i++){
				addPolygon1(arealist[i].areazbs,arealist[i].areaname,arealist[i].areams,arealist[i].areasize);
				var zbs = arealist[i].areazbs.split(";");
				var lo = zbs[0].split(",")[0];
				var la = zbs[0].split(",")[1];
				trdata+="<tr ondblclick='gotoarea("+lo+","+la+")'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+arealist[i].areaname+"</td><td nowrap='nowrap'>"+arealist[i].areams+"</td><td nowrap='nowrap'>"+arealist[i].areasize+"</td><td nowrap='nowrap'>"+arealist[i].areabjs+"</td><td><input type='button' value='上' onclick='up(this,"+i+")' />&nbsp;<input type='button' value='下' onclick='down(this,"+i+")' />&nbsp;<input type='button' value='改' onclick='editarea("+arealist[i].id+")'>&nbsp;<input type='button' value='删' onclick='deletearea("+arealist[i].id+")'></td></tr>";
			}
			$("#areadata").html(trdata);
		},
		error:function(){
		}		
	});
}
function addPolygon1(obj,name,ms,sz){  
	var polygonArr=new Array();//多边形覆盖物节点坐标数组   
	var zbs = obj.split(";");
	for(var i=0;i<zbs.length;i++){
		var zb = zbs[i].split(",");
		polygonArr.push(new AMap.LngLat(zb[0],zb[1]));   
	}
	var polygon=new AMap.Polygon({     
		path:polygonArr,//设置多边形边界路径  
		strokeColor:"black", //线颜色  
		// strokeOpacity:0.2, //线透明度   
		strokeWeight:3,    //线宽   
		fillColor: "#f5deb3", //填充色  
		fillOpacity: 0//填充透明度  
	});   
	polygon.setMap(areamap);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = pp(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:areamap,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}
function deletearea(obj){
	if(window.confirm("确定删除该数据？")){
	$.ajax({
		url : 'deletearea.action', 
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			if(json.message==""){
				alert("删除成功");
				areamap.clearMap();
				queryarea();
			}else{
				alert(json.message);
			}
		},
		error:function(){
			alert("请求失败");
		}		
	});
	}
}

function resettip(){
	$("#areaname").val("");
	$("#areams").val("");
	$("#areasize").val("");
	$("#areazbs").val("");
}

function editarea(obj){
	$.ajax({
		url : 'findonearea.action',
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var area = json.areaone;
			$("#areaidedit").val(area.id);
			$("#areanameedit").val(area.areaname);
			$("#areabjsedit").val(area.areabjs);
			$("#areamsedit").val(area.areams);
			$("#areasizeedit").val(area.areasize);
			$("#areazbsedit").val(area.areazbs);
			$('#area_edit').dialog('open');
		},
		error:function(){
		}		
	});
}

function updatearea(){
	$.ajax({
		url : 'updatearea.action',
		type : 'post',
		data:{
			"id" : $("#areaidedit").val(),
			"areaname" : $("#areanameedit").val(),
			"areabjs" : $("#areabjsedit").val(),
			"areams" : $("#areamsedit").val(),
			"areazbs" : $("#areazbsedit").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			if(json.message==""){
				alert("修改成功");
				areamap.clearMap();
				queryarea();
			}else{
				alert(json.message);
			}
		},
		error:function(){
		}		
	});
}

function pp(obj){
	var num = parseInt(obj.length/10);
	if(num<1){
		return obj;
	}else{
		var rs="";
		for(var i=0;i<num;i++){
			rs+=obj.substr(i*10,10)+"<br/>";
		}
		rs+=obj.substr(num*10,obj.length);
		return rs;
	}
}

function gotoarea(lo,la){
	var po = new AMap.LngLat(lo,la);
	areamap.setZoomAndCenter(14,po);
}

function switchSysBar2(){
	if ($("#switchPoint2").val()=="3"){
		$("#switchPoint2").val("4");
		$("#downdiv2").css('display','none'); 
		 $("#updiv2").height("95%"); 
		$("#utod2").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint2").val("3");
		$("#downdiv2").css('display','block');
		 $("#updiv2").height("60%"); 
		$("#utod2").attr("class", "fa fa-chevron-down");
	}
}


//上移
function up(t,obj){
 var i=$(t).parent().parent().index();//当前行的id
 if(i>0){//不是表头，也不是第一行，则可以上移
//	alert(obj+"   "+(obj-1)+"  "+arealist[obj].id+"   "+arealist[obj-1].id);
	orderarea(arealist[obj].id,arealist[obj-1].id,arealist[obj].orderid,arealist[obj-1].orderid);
//   var tem0=$(t).parent().parent().html();
//   var tem1=$(t).parent().parent().prev().html();
//   $(t).parent().parent().prev().html(tem0);
//   $(t).parent().parent().html(tem1);
 }
}
//下移
function down(t,obj){
 var l=$("#areadata tr").length;//总行数
 var i=$(t).parent().parent().index();//当前行的id
 if(i<l-1){//不是最后一行，则可以下移
//	 alert(obj+"   "+(obj+1)+"  "+arealist[obj].id+"   "+arealist[obj+1].id);
	 orderarea(arealist[obj].id,arealist[obj+1].id,arealist[obj].orderid,arealist[obj+1].orderid);
//   var tem0=$(t).parent().parent().html();
//   var tem1=$(t).parent().parent().next().html();
//   $(t).parent().parent().next().html(tem0);
//   $(t).parent().parent().html(tem1);
 }
}   

function orderarea(id1,id2,oid1,oid2){
	$.ajax({
		url : 'orderareaid.action',
		type : 'post',
		data:{
			"id1" : id1,
			"id2" : id2,
			"oid1" : oid1,
			"oid2" : oid2
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			queryarea();
		},
		error:function(){
		}		
	});
}
</script>


<style>
.txtstyle{position:relative;}
.txtstyle span{
white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #3399CC;
	color:#FFFFFF;
	width:auto;
	border:1px solid #3399CC;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;
}
#tip{
			position:absolute;
			right:30px;
			top:40px;
			border-radius:3px;
			line-height:30px;
		}
</style>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">