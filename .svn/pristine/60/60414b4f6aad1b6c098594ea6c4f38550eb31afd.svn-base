<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<!-- widget grid -->
<input type="hidden" id="compid" value="${date}">
<input type="hidden" id="userid" value="${userid}">
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv2ud"><div id="data-div-udarea" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
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
              <TD onclick=switchSysBar2ud() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod2ud"><input type="hidden" id="switchPoint2ud" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>	
		
	<div style="width: 100%;height: 35%;overflow: auto;" id="downdiv2ud">
			<div style="overflow: auto;" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>区域详细 &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="btn btn-primary" onclick="addudarea();">添加区域</a></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body">
						<form id="udareaform">
						<table id="udareatable" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th nowrap="nowrap">序号</th>
									<th nowrap="nowrap">区域名称</th>
									<th nowrap="nowrap">区域描述</th>
									<th nowrap="nowrap">区域面积</th>
									<!-- 
									<th nowrap="nowrap">区域预报警数</th>
									 -->
									<th nowrap="nowrap">操作</th>
								</tr>
								
							</thead>
							<tbody id="udareadata">
							</tbody>
						</table>
					</form>
					</div>
				</div>
			</div>
			</div>
</div>

<div id="dialog_udarea" title="区域详情">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareaname">
		</label>
		</td>
	</tr>
	<!-- 
	<tr>
		<td valign="top">预报警数:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areabjs" value="10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;">
		</label>
		</td>
	</tr>
	 -->
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareams"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareasize" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareazbs" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>

<div id="udarea_edit" title="区域修改">
	<p>
	<input type="hidden" id="udareaidedit" value="">
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td >
		<label class="input"  > 
			<input type="text" name=""  id="udareanameedit">
		</label>
		</td>
	</tr>
	<!-- 
	<tr>
		<td valign="top">预报警数:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areabjsedit">
		</label>
		</td>
	</tr>
	 -->
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareamsedit"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareasizeedit" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareazbsedit" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	initializeudarea();
	
	var udareamap;
	var udpolygonOption = {
		    strokeColor:"#000033",	
		    strokeOpacity:1,
		    strokeWeight:2
		};
	function initializeudarea() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		udareamap=new AMap.Map("data-div-udarea",{center:position,level:14,resizeEnable:true});//创建地图实例
		udareamap.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
			  //加载工具条
			  tool=new AMap.ToolBar({
			    direction:false,//隐藏方向导航
			    ruler:false,//隐藏视野级别控制尺
			    autoPosition:false//禁止自动定位
			  });
			  udareamap.addControl(tool);
			  //加载鹰眼
			  view=new AMap.OverView();
			  udareamap.addControl(view);
			  //加载比例尺
			  scale=new AMap.Scale();
			  udareamap.addControl(scale);
			});

		udareamap.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				udareamap.addControl(type);
			});

	}
</script>
<script type="text/javascript">
var h=0;
queryudarea();
function addudarea(){
	if(udmouseTool!=null){
		alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
	}else{
		udareamap.plugin(["AMap.MouseTool"],function(){ 
		udmouseTool = new AMap.MouseTool(udareamap); 
		udmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
		AMap.event.addListener(udmouseTool, "draw", function(e){
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
			$("#udareazbs").val(zbs);
			$("#udareasize").val(drawObj.getArea()+"平方米");
			$('#dialog_udarea').dialog('open');
			//alert("多边形节点数：" + pointsCount + "<br>节点坐标："+e.obj.getPath());
		});
		});
	}
}
var udmouseTool=null;
$('#dialog_udarea').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "保存区域",
		"class" : "btn btn-danger",
		click : function() {
			if(udmouseTool!=null){
				udmouseTool.close(true);
			}
			udmouseTool=null;
			saveudarea();
			$(this).dialog("close");
			resetudtip();
		}
	}, {
		html : "取消添加",
		"class" : "btn btn-default",
		click : function() {
			if(udmouseTool!=null){
				udmouseTool.close(true);
			}
			udmouseTool=null;
			$(this).dialog("close");
			resetudtip();
		}
	}]
});

$('#udarea_edit').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "修改",
		"class" : "btn btn-danger",
		click : function() {
			updateudarea();
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
function saveudarea(){
	if($("#udareaname").val()==""){
		alert("请输入区域名称");
	}else{
		//if($("#udareabjs").val()==""){
		//	alert("请输入区域预报警数量");
		//}else{
			if($("#udareams").val()==""){
				alert("请输入区域描述");
			}else{
				$.ajax({
					url : 'addudarea.action',
					type : 'post',
					data:{
						"areaname" : $("#udareaname").val(),
						//"areabjs" : $("#udareabjs").val(),
						"areams" : $("#udareams").val(),
						"areazbs" : $("#udareazbs").val(),
						"areasize" : $("#udareasize").val()
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
						if(json.message==""){
							alert("添加成功");
							udareamap.clearMap();
							queryudarea();
						}else{
							alert(json.message);
						}
					},
					error:function(){
					}		
				});
			}
		//}
	}
}
var udarealist=null;
function queryudarea(){
	$.ajax({
		url : 'queryudarea.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			udarealist = json.arealist;
			var trdata="";
			for(var i=0;i<udarealist.length;i++){
				udaddPolygon1(udarealist[i].areazbs,udarealist[i].areaname,udarealist[i].areams,udarealist[i].areasize);
				var zbs = udarealist[i].areazbs.split(";");
				var lo = zbs[0].split(",")[0];
				var la = zbs[0].split(",")[1];
				trdata+="<tr ondblclick='gotoudarea("+lo+","+la+")'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+udarealist[i].areaname+"</td><td nowrap='nowrap'>"+udarealist[i].areams+"</td><td nowrap='nowrap'>"+udarealist[i].areasize+"</td><TD><input type='button' value='改' onclick='editudarea("+udarealist[i].id+")'>&nbsp;<input type='button' value='删' onclick='deleteudarea("+udarealist[i].id+")'></td></tr>";
			}
			$("#udareadata").html(trdata);
		},
		error:function(){
		}		
	});
}
function udaddPolygon1(obj,name,ms,sz){  
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
	polygon.setMap(udareamap);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = pp(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:udareamap,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}
function deleteudarea(obj){
	if(window.confirm("确定删除该数据？")){
	$.ajax({
		url : 'deleteudarea.action', 
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			if(json.message==""){
				alert("删除成功");
				udareamap.clearMap();
				queryudarea();
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

function resetudtip(){
	$("#udareaname").val("");
	$("#udareams").val("");
	$("#udareasize").val("");
	$("#udareazbs").val("");
}

function editudarea(obj){
	$.ajax({
		url : 'findoneudarea.action',
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var area = json.areaone;
			$("#udareaidedit").val(area.id);
			$("#udareanameedit").val(area.areaname);
			//$("#udareabjsedit").val(area.areabjs);
			$("#udareamsedit").val(area.areams);
			$("#udareasizeedit").val(area.areasize);
			$("#udareazbsedit").val(area.areazbs);
			$('#udarea_edit').dialog('open');
		},
		error:function(){
		}		
	});
}

function updateudarea(){
	$.ajax({
		url : 'updateudarea.action',
		type : 'post',
		data:{
			"id" : $("#udareaidedit").val(),
			"areaname" : $("#udareanameedit").val(),
		//	"areabjs" : $("#udareabjsedit").val(),
			"areams" : $("#udareamsedit").val(),
			"areazbs" : $("#udareazbsedit").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			if(json.message==""){
				alert("修改成功");
				udareamap.clearMap();
				queryudarea();
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

function gotoudarea(lo,la){
	var po = new AMap.LngLat(lo,la);
	areamap.setZoomAndCenter(14,po);
}

function switchSysBar2ud(){
	if ($("#switchPoint2ud").val()=="3"){
		$("#switchPoint2ud").val("4");
		$("#downdiv2ud").css('display','none'); 
		 $("#updiv2ud").height("95%"); 
		$("#utod2ud").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint2ud").val("3");
		$("#downdiv2ud").css('display','block');
		 $("#updiv2ud").height("60%"); 
		$("#utod2ud").attr("class", "fa fa-chevron-down");
	}
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