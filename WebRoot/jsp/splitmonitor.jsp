<style type="text/css">
.btn {
	display: inline-block;
	height: 32px;
	line-height: 32px;
	padding: 0 1em;
	border: 1px solid #2c699d;
	border-radius: .2em;
	text-align: center;
	color: #fff;
	background-color: #0f89f5;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.btn:hover {
	border-color: #1f496d;
	color: #fff;
	background-color: #00abff;
}

.dcfpjk_toolBox {
	position: absolute;
	top: 10px;
	left: 10px;
	width: 100%;
	height: 45px;
}

#dcfpjk_carNum {
	height: 27px;
}

#sdqx_cx {
	position: relative;
	top: 1px;
}

.dcfpjk_centerPanel {
	position: absolute;
	top: 40px;
	left: 0;
	bottom: 0;
	width: 100%;
}

.dcfpjk_centerPanel>div {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	position: relative;
	float: left;
	width: 50%;
	height: 50%;
	border: 6px solid #ffffff;
}

#dcfpjk_mapPanel1,#dcfpjk_mapPanel2,#dcfpjk_mapPanel3,#dcfpjk_mapPanel4
	{
	width: 100%;
	height: 100%;
	/* 	top : 10% */
}

#dcfpjk_carNum1,#dcfpjk_carNum2,#dcfpjk_carNum3,#dcfpjk_carNum4 {
	height: 26px;
}

.amap-info-content {
	width: 200px;
	height: 150px;
	font-size: 1px;
}

.panel-inputBox,.panel-itemBox {
	display: inline-block;
	overflow: hidden;
}

.panel-itemBox {
	position: absolute;
	margin-top: 4px;
	padding-left: 6px;
}

.panel-inputBox,.panel-inputIBox {
	position: relative;
	margin: 5px 0;
	border: 1px solid #0f89f5;
	border-radius: .2em;
	background: #ffffff;
}

.panel-inputBox input[type=text] {
	margin: 0;
	padding: .12em .2em;
	border: none;
	border-radius: .2em;
}

.panel-inputBox label,.panel-inputIBox label {
	padding-left: .5em;
	color: #0f89f5;
}

#sou_rect1 {
	pointer-events: auto;
	max-height: 350px;
	min-height: 0px;
	background: #ffffff;
	position: relative;
	top: 5px;
	box-shadow: 1px 2px 1px rgba(0, 0, 0, 0.15);
	width: 350px;
	overflow: hidden;
}

#sou_rect1:hover {
	overflow: auto;
}

#sou_rect1 ul {
	margin: 0px;
	padding: 0px;
}

#sou_rect1 ul li {
	width: 100%;
	height: 35px;
	display: block;
	border-bottom: 1px solid rgba(105, 105, 105, 0.1);
	line-height: 35px;
	float: left;
	cursor: pointer;
}

#sou_rect1 ul li i {
	font-family: '微软雅黑';
	font-size: 12px;
	color: rgba(105, 105, 105, 0.56);
	font-style: normal;
}

#sou_rect1 ul li span {
	display: block;
	float: left;
	height: 20px;
	width: 15px;
	vertical-align: middle;
	margin-left: 10px;
	margin-top: 11px;
}

#sou_rect1 ul li a {
	float: right;
	height: 80%;
	width: 100px;
	border-left: 1px solid rgba(105, 105, 105, 0.08);
	padding-left: 10px;
	position: relative;
	top: 4px;
	line-height: 28px;
	font-family: '微软雅黑';
	font-size: 12px;
	color: rgba(105, 105, 105, 0.56);
	font-style: normal;
}

#sou_rect1 li:hover {
	background: rgba(105, 105, 105, 0.25);
}
</style>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=UTF-8"%>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%">
	<input type="hidden" id="compid" value="${date}"> <input
		type="hidden" id="userid" value="${userid}">
	<div class="dcfpjk_centerPanel">
		<div>
			<div id="dcfpjk_mapPanel1"></div>
			<div class="dcfpjk_toolBox">
				<div class="panel-inputBox">
					<input type="text" class="cph3" info="1" placeholder="输入3位以上车牌号" style="width: 120px; height: 31px;"/>
				</div>
				<div class="panel-inputBox">
					<select name="carNum" id="dcfpjk_carNum1" style="width: 120px; height: 31px;"></select>
				</div>
				<div class="panel-itemBox">
					<a class="btn" id="sdqx_cx1">查询</a> <a class="btn" id="sdqx_qk1">清空</a>
				</div>
			</div>
		</div>
		<div>
			<div id="dcfpjk_mapPanel2"></div>
			<div class="dcfpjk_toolBox">
				<div class="panel-inputBox">
					<input type="text" class="cph3" info="2" placeholder="输入3位以上车牌号" style="width: 120px; height: 31px;"/>
				</div>
				<div class="panel-inputBox">
					<select name="carNum" id="dcfpjk_carNum2" style="width: 120px; height: 31px;"></select>
				</div>
				<div class="panel-itemBox">
					<a class="btn" id="sdqx_cx2">查询</a> <a class="btn" id="sdqx_qk2">清空</a>
				</div>
			</div>
		</div>
		<div>
			<div id="dcfpjk_mapPanel3"></div>
			<div class="dcfpjk_toolBox">
				<div class="panel-inputBox">
					<input type="text" class="cph3" info="3" placeholder="输入3位以上车牌号" style="width: 120px; height: 31px;"/>
				</div>
				<div class="panel-inputBox">
					<select name="carNum" id="dcfpjk_carNum3" style="width: 120px; height: 31px;"></select>
				</div>
				<div class="panel-itemBox">
					<a class="btn" id="sdqx_cx3">查询</a> <a class="btn" id="sdqx_qk3">清空</a>
				</div>
			</div>
		</div>
		<div>
			<div id="dcfpjk_mapPanel4"></div>
			<div class="dcfpjk_toolBox">
				<div class="panel-inputBox">
					<input type="text" class="cph3" info="4" placeholder="输入3位以上车牌号" style="width: 120px; height: 31px;"/>
				</div>
				<div class="panel-inputBox">
					<select name="carNum" id="dcfpjk_carNum4" style="width: 120px; height: 31px;"></select>
				</div>
				<div class="panel-itemBox">
					<a class="btn" id="sdqx_cx4">查询</a> <a class="btn" id="sdqx_qk4">清空</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="sou_rect1"
	style="height: 350px;
		    width: 100px;
		    background: #ffffff;
		    position: absolute;
		    top: 75px;
		    left: 364px;
		    box-shadow: 0px 2px 1px rgba(0, 0, 0, 0.15);
		    display: none;
		    border: 1px solid #000000;
		    overflow: inherit;
		    ">
	<ul></ul>
</div>
<script type="text/javascript">
	pageSetUp();
	var geocoder = new AMap.Geocoder({
		radius : 1000,
		extensions : "all"
	});
	var map1 = new AMap.Map('dcfpjk_mapPanel1', {
		animateEnable : false,
		jogEnable : false,
		view : new AMap.View2D({
			center : new AMap.LngLat(120.153576, 30.287459),
			zoom : 11
		})

	});
	var map2 = new AMap.Map('dcfpjk_mapPanel2', {
		animateEnable : false,
		jogEnable : false,
		view : new AMap.View2D({
			center : new AMap.LngLat(120.153576, 30.287459),
			zoom : 11
		})

	});
	var map3 = new AMap.Map('dcfpjk_mapPanel3', {
		animateEnable : false,
		jogEnable : false,
		view : new AMap.View2D({
			center : new AMap.LngLat(120.153576, 30.287459),
			zoom : 11
		})

	});
	var map4 = new AMap.Map('dcfpjk_mapPanel4', {
		animateEnable : false,
		jogEnable : false,
		view : new AMap.View2D({
			center : new AMap.LngLat(120.153576, 30.287459),
			zoom : 11
		})

	});
		$(".cph3").on('keyup', function(){
			var info = $(this).attr("info");
			var vhic = $(this).val();
			console.log(info,vhic)
			if(vhic.length>2){
				$.ajax({
					url : 'findcl3.action',
					type : 'post',
					data:{
						"quyu" : vhic
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
						var list = json.list2;
						if(info == '1'){
							if(list.length>0){
								for(var i=0; i<list.length; i++){
									$("#dcfpjk_carNum1").append("<option value="+list[i].vehi_id+">"+list[i].vehi_id+"</option>");
								}
							}else{
								$("#dcfpjk_carNum1").empty();
							}
						}else if(info == '2'){
							if(list.length>0){
								for(var i=0; i<list.length; i++){
									$("#dcfpjk_carNum2").append("<option value="+list[i].vehi_id+">"+list[i].vehi_id+"</option>");
								}
							}else{
								$("#dcfpjk_carNum2").empty();
							}
						}else if(info == '3'){
							if(list.length>0){
								for(var i=0; i<list.length; i++){
									$("#dcfpjk_carNum3").append("<option value="+list[i].vehi_id+">"+list[i].vehi_id+"</option>");
								}
							}else{
								$("#dcfpjk_carNum3").empty();
							}
						}else if(info == '4'){
							if(list.length>0){
								for(var i=0; i<list.length; i++){
									$("#dcfpjk_carNum4").append("<option value="+list[i].vehi_id+">"+list[i].vehi_id+"</option>");
								}
							}else{
								$("#dcfpjk_carNum4").empty();
							}
						}
					},
					error:function(){
					}		
				});
			}
		});
	var st1,arrline1=[],marker1=null,polyline1=null;

	$("#sdqx_qk1").click(function(){
		if(st1){
			map1.clearMap();
			clearInterval(st1);
			arrline1=[];
			marker1=null;
			polyline1=null;
		}
	});
	$("#sdqx_cx1").click(function(){
		var car1=$("#dcfpjk_carNum1").val();
		if(car1==""){
			alert("请选择车牌号码");
			return;
		}
		if(st1){
			map1.clearMap();
			clearInterval(st1);
			arrline1=[];
			marker1=null;
			polyline1=null;
		}
		cxdata1(car1);
		st1 = setInterval(function() {
			cxdata1(car1);
		}, 30000);
	}); 

	function cxdata1(obj){
		$.ajax({
			url : 'finddpjk.action',
			type : 'post',
			data:{
				"quyu" : obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				console.log(json.vehicle);
				if(json.vehicle.length==0){
					alert("该车暂无轨迹");
					map1.clearMap();
					clearInterval(st1);
					arrline1=[];
					marker1=null;
					polyline1=null;
					return;
				}
				var data = json.vehicle[0];
	        	var amapxy = new AMap.LngLat(data.px,data.py);
	        	arrline1.push(amapxy);
	        	if(marker1!=null){
	        		var xiaomarker1= new AMap.Marker({
	            	    map:map1,
	            	    position:marker1.getPosition(),
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-6,-6), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:marker1.getAngle(),
	            	    icon:"img/car/fx2.png"
	            	});
	        		marker1.setAngle(data.heading-90);
	        		marker1.setPosition(amapxy);
	        		polyline1.setPath(arrline1);
	        	}else{
	        		marker1 = new AMap.Marker({
	            	    map:map1,
	            	    position:amapxy,
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-12,-12), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:data.heading-90,
	            	    icon:"img/car/fpcar.png"
	            	});
	        		var txt = "<b style='color:#3399FF'>"+data.vehino+"</b><br/><br/><b>[GPS时间]</b>："+data.dateTime
	        		+"<br/><b>[GPS速度]</b>："+data.speed
	        		+"<br/><b>[行驶方向]</b>："+data.heading
	        		+"<br/><b>[当前位置]</b>：<font id='mkadd1'></font>";
	        		var info = [];                 
	            	info.push(txt);                 
	            	var inforWindow = new AMap.InfoWindow({                 
	            	  offset:new AMap.Pixel(0,0),                 
	            	  content:info.join("<br>")                 
	            	});                 
	            	  AMap.event.addListener(marker1,"click",function(e){
	            		  getaddress(new AMap.LngLat(data.px,data.py),1);
	            		  inforWindow.open(map1,marker1.getPosition());              
	            		});
	        		polyline1 = new AMap.Polyline({
	                    map:map1,
	                    path:arrline1,
	                    strokeColor:"#000",//线颜色
	                    strokeOpacity:1,//线透明度
	                    strokeWeight:3,//线宽
	                    strokeStyle:"solid"//线样式
	                });
	        	}
	        	
	        	map1.setZoomAndCenter(14,amapxy);
	        },
			error:function(){
			}		
		});
	}

	//2窗口
	var st2,arrline2=[],marker2=null,polyline2=null;

	$("#sdqx_qk2").click(function(){
		if(st2){
			map2.clearMap();
			clearInterval(st2);
			arrline2=[];
			marker2=null;
			polyline2=null;
		}
	});
	$("#sdqx_cx2").click(function(){
		var car2=$("#dcfpjk_carNum2").val();
		if(car2==""){
			alert("请选择车牌号码");
			return;
		}
		if(st2){
			map2.clearMap();
			clearInterval(st2);
			arrline2=[];
			marker2=null;
			polyline2=null;
		}
		cxdata2(car2);
		st2 = setInterval(function() {
			cxdata2(car2);
		}, 30000);
	}); 

	function cxdata2(obj){
		console.log(obj)
		$.ajax({
			url : 'finddpjk.action',
			type : 'post',
			data:{
				"quyu" : obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
	        	console.log(json.vehicle);
				if(json.vehicle.length==0){
					alert("该车暂无轨迹");
					map1.clearMap();
					clearInterval(st1);
					arrline1=[];
					marker1=null;
					polyline1=null;
					return;
				}
				var data = json.vehicle[0];
	        	var amapxy = new AMap.LngLat(data.px,data.py);
	        	arrline2.push(amapxy);
	        	if(marker2!=null){
	        		var xiaomarker2= new AMap.Marker({
	            	    map:map2,
	            	    position:marker2.getPosition(),
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-6,-6), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:marker2.getAngle(),
	            	    icon:"img/car/fx2.png"
	            	});
	        		marker2.setAngle(data.heading-90);
	        		marker2.setPosition(amapxy);
	        		polyline2.setPath(arrline2);
	        	}else{
	        		marker2 = new AMap.Marker({
	            	    map:map2,
	            	    position:amapxy,
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-12,-12), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:data.heading-90,
	            	    icon:"img/car/fpcar.png"
	            	});
	        		var txt = "<b style='color:#3399FF'>"+data.vehino+"</b><br/><br/><b>[GPS时间]</b>："+data.dateTime
        		+"<br/><b>[GPS速度]</b>："+data.speed
        		+"<br/><b>[行驶方向]</b>："+data.heading
        		+"<br/><b>[当前位置]</b>：<font id='mkadd2'></font>";
	        		var info = [];                 
	            	info.push(txt);                 
	            	var inforWindow = new AMap.InfoWindow({                 
	            	  offset:new AMap.Pixel(0,0),                 
	            	  content:info.join("<br>")                 
	            	});                 
	            	  AMap.event.addListener(marker2,"click",function(e){
	            		  getaddress(new AMap.LngLat(data.px,data.py),2);
	            		  inforWindow.open(map2,marker2.getPosition());                 
	            		});
	        		polyline2 = new AMap.Polyline({
	                    map:map2,
	                    path:arrline2,
	                    strokeColor:"#000",//线颜色
	                    strokeOpacity:1,//线透明度
	                    strokeWeight:3,//线宽
	                    strokeStyle:"solid"//线样式
	                });
	        	}
	        	
	        	map2.setZoomAndCenter(14,amapxy);
	        },
	        error : function(error) {
	            console.log(error)
	        }
	})
	}

	//3窗口
	var st3,arrline3=[],marker3=null,polyline3=null;

	$("#sdqx_qk3").click(function(){
		if(st3){
			map3.clearMap();
			clearInterval(st3);
			arrline3=[];
			marker3=null;
			polyline3=null;
		}
	});
	$("#sdqx_cx3").click(function(){
		var car3=$("#dcfpjk_carNum3").val();
		if(car3==""){
			alert("请选择车牌号码");
			return;
		}
		if(st3){
			map3.clearMap();
			clearInterval(st3);
			arrline3=[];
			marker3=null;
			polyline3=null;
		}
		cxdata3(car3);
		st3 = setInterval(function() {
			cxdata3(car3);
		}, 30000);
	}); 

	function cxdata3(obj){
		$.ajax({
			url : 'finddpjk.action',
			type : 'post',
			data:{
				"quyu" : obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
	        	console.log(json.vehicle);
				if(json.vehicle.length==0){
					alert("该车暂无轨迹");
					map1.clearMap();
					clearInterval(st1);
					arrline1=[];
					marker1=null;
					polyline1=null;
					return;
				}
				var data = json.vehicle[0];
	        	var amapxy = new AMap.LngLat(data.px,data.py);
	        	arrline3.push(amapxy);
	        	if(marker3!=null){
	        		var xiaomarker3= new AMap.Marker({
	            	    map:map3,
	            	    position:marker3.getPosition(),
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-6,-6), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:marker3.getAngle(),
	            	    icon:"img/car/fx2.png"
	            	});
	        		marker3.setAngle(data.heading-90);
	        		marker3.setPosition(amapxy);
	        		polyline3.setPath(arrline3);
	        	}else{
	        		marker3 = new AMap.Marker({
	            	    map:map3,
	            	    position:amapxy,
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-12,-12), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:data.heading-90,
	            	    icon:"img/car/fpcar.png"
	            	});
	        		var txt = "<b style='color:#3399FF'>"+data.vehino+"</b><br/><br/><b>[GPS时间]</b>："+data.dateTime
	        		+"<br/><b>[GPS速度]</b>："+data.speed
	        		+"<br/><b>[行驶方向]</b>："+data.heading
	        		+"<br/><b>[当前位置]</b>：<font id='mkadd3'></font>";
	        		var info = [];                 
	            	info.push(txt);                 
	            	var inforWindow = new AMap.InfoWindow({                 
	            	  offset:new AMap.Pixel(0,0),                 
	            	  content:info.join("<br>")                 
	            	});                 
	            	  AMap.event.addListener(marker3,"click",function(e){
	            		  getaddress(new AMap.LngLat(data.px,data.py),3);
	            		  inforWindow.open(map3,marker3.getPosition());                 
	            		});
	        		polyline3 = new AMap.Polyline({
	                    map:map3,
	                    path:arrline3,
	                    strokeColor:"#000",//线颜色
	                    strokeOpacity:1,//线透明度
	                    strokeWeight:3,//线宽
	                    strokeStyle:"solid"//线样式
	                });
	        	}
	        	
	        	map3.setZoomAndCenter(14,amapxy);
	        },
	        error : function(error) {
	            console.log(error)
	        }
	})
	}

	//4窗口
	var st4,arrline4=[],marker4=null,polyline4=null;

	$("#sdqx_qk4").click(function(){
		if(st4){
			map4.clearMap();
			clearInterval(st4);
			arrline4=[];
			marker4=null;
			polyline4=null;
		}
	});
	$("#sdqx_cx4").click(function(){
		var car4=$("#dcfpjk_carNum4").val();
		if(car4==""){
			alert("请选择车牌号码");
			return;
		}
		if(st4){
			map4.clearMap();
			clearInterval(st4);
			arrline4=[];
			marker4=null;
			polyline4=null;
		}
		cxdata4(car4);
		st4 = setInterval(function() {
			cxdata4(car4);
		}, 30000);
	}); 

	function cxdata4(obj){
		$.ajax({
			url : 'finddpjk.action',
			type : 'post',
			data:{
				"quyu" : obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
	        	console.log(json.vehicle);
				if(json.vehicle.length==0){
					alert("该车暂无轨迹");
					map1.clearMap();
					clearInterval(st1);
					arrline1=[];
					marker1=null;
					polyline1=null;
					return;
				}
				var data = json.vehicle[0];
	        	var amapxy = new AMap.LngLat(data.px,data.py);
	        	arrline4.push(amapxy);
	        	if(marker4!=null){
	        		var xiaomarker4= new AMap.Marker({
	            	    map:map4,
	            	    position:marker4.getPosition(),
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-6,-6), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:marker4.getAngle(),
	            	    icon:"img/car/fx2.png"
	            	});
	        		marker4.setAngle(data.heading-90);
	        		marker4.setPosition(amapxy);
	        		polyline4.setPath(arrline4);
	        	}else{
	        		marker4 = new AMap.Marker({
	            	    map:map4,
	            	    position:amapxy,
	            	    zIndex:102,  
	            	    offset:new AMap.Pixel(-12,-12), //相对于基点的偏移位置
	            	    draggable:false,
	            	    angle:data.heading-90,
	            	    icon:"img/car/fpcar.png"
	            	});
	        		var txt = "<b style='color:#3399FF'>"+data.vehino+"</b><br/><br/><b>[GPS时间]</b>："+data.dateTime
	        		+"<br/><b>[GPS速度]</b>："+data.speed
	        		+"<br/><b>[行驶方向]</b>："+data.heading
	        		+"<br/><b>[当前位置]</b>：<font id='mkadd4'></font>";
	        		var info = [];                 
	            	info.push(txt);                 
	            	var inforWindow = new AMap.InfoWindow({                 
	            	  offset:new AMap.Pixel(0,0),                 
	            	  content:info.join("<br>")                 
	            	});                 
	            	  AMap.event.addListener(marker4,"click",function(e){
	            		  getaddress(new AMap.LngLat(data.px,data.py),4);
	            		  inforWindow.open(map4,marker4.getPosition());                 
	            		});
	        		polyline4 = new AMap.Polyline({
	                    map:map4,
	                    path:arrline4,
	                    strokeColor:"#000",//线颜色
	                    strokeOpacity:1,//线透明度
	                    strokeWeight:3,//线宽
	                    strokeStyle:"solid"//线样式
	                });
	        	}
	        	
	        	map4.setZoomAndCenter(14,amapxy);
	        },
	        error : function(error) {
	            console.log(error)
	        }
	})
	}

	function getaddress(obj,obj1){
	    	geocoder.getAddress(obj, function(status, result){
	            //如果服务请求状态为“error”
	                if(status=='error') {
	              	  console.log("服务请求出错啦！ ");
	                 }else{
	             //如果服务请求状态为“no_data”， “no_data”是指服务请求正常，但根据检索条件无结果返回，建议更换检索条件
	                if(status=='no_data') {
	              	  console.log("无数据返回，请换个关键字试试～～");
	                }else {
	              	  //console.log(result);
	              	  $("#mkadd"+obj1).html(result.regeocode.formattedAddress);
	                	}
	                 }
	       });
	    }
</script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">
