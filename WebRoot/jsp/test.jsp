<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>

<div class="row" style="overflow: auto;height: 100%" >
<table border="1" style="width: 101%;height: 100%">
	<tr style="width: 98%;">
		<td>
		<font size="4" color="red">总数：<strong id="total"></strong>&nbsp;上线：<strong id="onnum"></strong></font>&nbsp;<img src="img/lan_2.png"></img><font size="4" color="#929292" >下线：</font><font size="4" color="red"><strong id="offnum"></strong> </font>&nbsp;<img src="img/lan_1.png"></img><font size="4" color="#ff1313" >重车：</font><font size="4" color="red"><strong id="hnum"></strong></font>&nbsp;<img src="img/lan_0.png"></img><font size="4" color="#0fff21" >空车：</font><font size="4" color="red"><strong id="nnum"></strong></font>
		&nbsp;<font size="1"  style="float: right;padding-right: 20px;"><strong id="ntime"></strong></font>
		</td>
	</tr>
	<tr style="width: 98%;height: 350px;">
		<td style="width: 100%;height: 100%"><div id="data-div-history" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
	</tr>
</table>
</div>

<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	initializehistory();
	var i=0;
	var j=0;
	getarea();
	// PAGE RELATED SCRIPTS
	
	var mapObjhistory;
	var markerhistory="";
	function initializehistory() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		mapObjhistory=new AMap.Map("data-div-history",{center:position,level:14});//创建地图实例
		mapObjhistory.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
			  //加载工具条
			  tool=new AMap.ToolBar({
			    direction:false,//隐藏方向导航
			    ruler:false,//隐藏视野级别控制尺
			    autoPosition:false//禁止自动定位
			  });
			  mapObjhistory.addControl(tool);
			  //加载鹰眼
			  view=new AMap.OverView();
			  mapObjhistory.addControl(view);
			  //加载比例尺
			  scale=new AMap.Scale();
			  mapObjhistory.addControl(scale);
			});
	}
//var sms=null;
	
	function getarea(){
		if(i==0&&j==0){
			if(sms!=null){
				clearTimeout(sms);
			}
			$.ajax({
				url : 'getarea.action',
				type : 'post',
				data:{
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					markers= [];
					var arealist = json.arealist;
					var num =json.num;
					var vehilist = json.vehilist;
					$("#total").html(num.total);
					$("#onnum").html(num.onnum+"("+parseInt(parseInt(num.onnum)/parseInt(num.total)*100)+'%'+")");
					$("#offnum").html(num.offnum);
					$("#hnum").html(num.hnum+"("+parseInt(parseInt(num.hnum)/parseInt(num.onnum)*100)+'%'+")");
					$("#nnum").html(num.nnum);
					mapObjhistory.clearMap();
					var ntime=setformat(new Date());
					$("#ntime").html(ntime);
					//	addPolygon1();
					
					for(j=0;j<vehilist.length;j++){
						addmks(vehilist[j]);
					}
					if(j==vehilist.length){
						addCluster(0);
						for(i=0;i<arealist.length;i++){
							addPolygon(ntime,arealist[i].id,arealist[i].name,arealist[i].cars,arealist[i].bjcars,arealist[i].lati1,arealist[i].longi1,arealist[i].lati2,arealist[i].longi2,arealist[i].lati3,arealist[i].longi3,arealist[i].lati4,arealist[i].longi4);
						}
					}
					if(i==arealist.length&&j==vehilist.length){
						i=0;j=0;
						sms=setTimeout("getarea()",40000);
					}
				},
				error:function(){
				}		
			});
		}else{
			setTimeout('getarea()',40000); 
		}
	}

	function setformat(obj){
		var y=(obj.getFullYear()).toString();
		var m=(obj.getMonth()+1).toString();
		if(m.length==1){
			m="0"+m;
		}
		var d=obj.getDate().toString();
		if(d.length==1){
			d="0"+d;
		}
		var h = obj.getHours().toString();
		if(h.length==1){
			h="0"+h;
		}
		var mi = obj.getMinutes().toString();
		if(mi.length==1){
			mi="0"+mi;
		}
		var s = obj.getSeconds().toString();
		if(s.length==1){
			s="0"+s;
		}
		var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s; 
		return time;
	}
	//添加多边形覆盖物  
	function addPolygon(ntime,id,name,cars,bjcars,a1,b1,a2,b2,a3,b3,a4,b4){  
		ntime=ntime.replace(/[ ]/g,"")
		//alert(a1+" "+b1+" "+a2+" "+b2+" "+a3+" "+b3+" "+a4+" "+b4);
	   var polygonArr=new Array();//多边形覆盖物节点坐标数组   
	   polygonArr.push(new AMap.LngLat(b1,a1));   
	   polygonArr.push(new AMap.LngLat(b2,a2));   
	   polygonArr.push(new AMap.LngLat(b4,a4));   
	   polygonArr.push(new AMap.LngLat(b3,a3));   
	   polygon=new AMap.Polygon({     
	   path:polygonArr,//设置多边形边界路径  
	   strokeColor:"black", //线颜色  
	  // strokeOpacity:0.2, //线透明度   
	   strokeWeight:3,    //线宽   
	   fillColor: "#f5deb3", //填充色  
	   fillOpacity: 0//填充透明度  
	  });   
	   polygon.setMap(mapObjhistory);  
	   
	    var markerContent = document.createElement("div");
	    markerContent.className = "markerContentStyle";
		var markerSpan = document.createElement("span");
		markerContent.onclick=function() { addTab(100,name,"find.action?time="+ntime+"&id="+id);};
		if(parseInt(cars)>=parseInt(bjcars)){
			markerSpan.innerHTML = "<font color='red'>"+name+": "+cars+"</font>/"+bjcars;
		}else{
			markerSpan.innerHTML = name+": "+cars+"/"+bjcars;
		}
		
		//markerSpan.innerHTML = name+": "+cars+"/"+bjcars+"(当前区域车辆数/区域预警数)";
		markerContent.appendChild(markerSpan);
	    var marker1 = new AMap.Marker({
		    map:mapObjhistory,
		     zIndex:10001, 
		    position:new AMap.LngLat(b3,a3),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
		
	 }  
var markers= [];
	function addmks(obj){
		var markerContent = document.createElement("div");
	    markerContent.className = "markerContentStyle";
	    //点标记中的图标
	    var markerImg= document.createElement("img");
		markerImg.className="markerlnglat";
		if(obj.state=="0"){
			markerImg.src="img/lan_0.png";   
		}else if(obj.state=="1"){
			markerImg.src="img/lan_1.png";   
		}else{
			markerImg.src="img/lan_2.png";   
		}
		markerImg.id=obj.messageid
		markerContent.appendChild(markerImg);
		var marker1 = new AMap.Marker({
		 //   map:mapObjhistory,
		    position:new AMap.LngLat(obj.longi,obj.lati),     
		    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent   //自定义点标记覆盖物内容
		});
		//marker1.setMap(mapObjhistory);  //在地图上添加点
		markers.push(marker1);
		var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[SIM卡]</b>："+obj.simka+"<br/><b>[车辆所属人]</b>："+obj.ownname
						  +"<br/><b>[联系电话]</b>："+obj.owntel;
		var info = [];                 
		info.push(txt);                 
		               
		var inforWindow = new AMap.InfoWindow({                 
		  offset:new AMap.Pixel(0,0),                 
		  content:info.join("<br>")                 
		});                 
		  AMap.event.addListener(marker1,"click",function(e){                 
			  inforWindow.open(mapObjhistory,marker1.getPosition());                 
			});
		  AMap.event.addListener(mapObjhistory,"click",function(e){                 
			  inforWindow.close();              
			});
		
	}
	var cluster;
	

	function addCluster(tag)
	{
		if(cluster) {	
			cluster.setMap(null);
		}
		if(tag==1) {
			var sts=[{url:"http://developer.amap.com/wp-content/uploads/2014/06/1.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
				{url:"http://developer.amap.com/wp-content/uploads/2014/06/2.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
				{url:"http://developer.amap.com/wp-content/uploads/2014/06/3.png", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
			mapObjhistory.plugin(["AMap.MarkerClusterer"],function(){
				cluster = new AMap.MarkerClusterer(mapObjhistory,markers,{styles:sts});
			});
		}
		else {
			mapObjhistory.plugin(["AMap.MarkerClusterer"],function(){
				cluster = new AMap.MarkerClusterer(mapObjhistory,markers);
			});
		}
	}
	
</script>
<link rel="stylesheet" type="text/css" href="css/gddiv.css" />
<!-- 
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
 -->