
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%" >
<div style="height: 100%;float: left;width: 74%" id="lefttd">
		<input type="hidden" id="compid" value="${date}"> 
		<input type="hidden" id="userid" value="${userid}">
		<div style="height: 65%;width: 100%" id="mapdiv">
			<table border="0" style="height: 100%;width: 100%">
			<tr style="width: 98%;">
				<td>
				<font size="4" color="red">总数：<strong id="total"></strong>&nbsp;上线：<strong id="onnum"></strong></font>&nbsp;<font size="4" color="#333333" ><img src="img/car/d.png"></img>下线：</font><font size="4" color="#333333"><strong id="offnum"></strong> </font>&nbsp;<font size="4" color="#ff1313" ><img src="img/car/h.png"></img>重车：</font><font size="4" color="red"><strong id="hnum"></strong></font>&nbsp;<font size="4" color="#009933" ><img src="img/car/c.png"></img>空车：</font><font size="4" color="#009933"><strong id="nnum"></strong></font>
				&nbsp;<font size="1"  style="float: right;padding-right: 20px;"><strong id="ntime"></strong></font>
				</td>
			</tr>
			<tr style="width: 98%;">
				<td style="width: 100%;height: 100%"><div id="data-div" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
			</tr>
		</table>
		
		</div>
		<!-- 
		<div id="data-div" class="google_maps" style="height: 65%;width: 100%">&nbsp;</div>
		 -->
			<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar1() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod"><input type="hidden" id="switchPoint1" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
			<!-- Widget ID (each widget will need unique ID)-->
			<div id="monitortabs"  style="height: 35%;width: 100%;">
				<ul>
					<li>
						<a href="#tabs-a" onclick="dwtab()" >定位信息(<font id="carnumid">0</font>)</a>
					</li>
				</ul>
				<div id="tabs-a" style="width: 100%;overflow: auto;height: 80%">
					<table id="dw_table" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
							<thead>
								<tr>
									<th nowrap='nowrap'>序号</th>
									<th nowrap='nowrap'>车牌</th>
									<th nowrap='nowrap'>车辆颜色</th>
									<th nowrap='nowrap'>分公司</th>
									<th nowrap='nowrap'>Sim卡号</th>
									<th nowrap='nowrap'>车速(km/h)</th>
									<th nowrap='nowrap'>状态</th>
									<th nowrap='nowrap'>汇报时间</th>
									<th nowrap='nowrap'>当前定位</th>
									<th nowrap='nowrap'>终端类型</th>
									<th nowrap='nowrap'>车辆类型</th>
								</tr>
							</thead>
							<tbody id="dw_data">
							</tbody>
						</table>
				</div>
			</div>
</div>
<TABLE border=0 cellPadding=0 cellSpacing=0 height="102%" style="background-color: #D0DBD7;float: left">
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar() style="HEIGHT: 100%;cursor:pointer"><i class="fa fa-chevron-right" id="ltor"><input type="hidden" id="switchPoint" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
	<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false"  style="overflow: auto;width: 24%;float: left;height: 102%;">
				<!-- 
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>车辆列表</h2>				
					
				</header>
				 -->

				<!-- widget div-->
					
					<!-- widget content -->
					<div class="widget-body" style="overflow: auto;height: 100%" id="moniright">
						<div class="widget-body-toolbar" align="left" >
							
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate">
								<section style="width:100%;float:left">
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<input type="text" name="fname" placeholder="关键字" id="keyword"  onkeyup="chaxun();">
										</label>
								</section>
								<!-- 
								onkeyup="chaxun();"
								<a href="javascript:void(0);" class="btn btn-primary" onclick="chaxun();" style="float:left;" >查   询</a>
								 -->
								</form>

						</div>
<ul id="treeDemo" class="ztree"></ul> 
						<!-- gps数据 
						<table id="vehi_table" class="table table-striped table-bordered table-hover" style="overflow: auto;">
							<thead>
								<tr>
									<th nowrap='nowrap'><input type="checkbox" id="quanxuan"  onclick="qxqbx()"> &nbsp;&nbsp;车牌</th>
								</tr>
							</thead>
							<tbody id="vehi_data">
							</tbody>
						</table>
						-->
					

					</div>
				</div>

</div>

<script type="text/javascript">
	function qxqbx(){
		if($("#quanxuan").prop("checked")){
			allcheck();
		}else{
			allnocheck();
		}
	}
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	initialize();
	query();
	$('#monitortabs').tabs();
	// PAGE RELATED SCRIPTS

	
	/*
	 *  Maps Initialize
	 */
	var type=0;
	var mapObj;
	var marker="";
	var markerlist = new Array();
	function initialize() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		 mapObj=new AMap.Map("data-div",{center:position,level:15,resizeEnable:true});//创建地图实例
		 mapObj.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
			  //加载工具条
			  tool=new AMap.ToolBar({
			    direction:false,//隐藏方向导航
			    ruler:false,//隐藏视野级别控制尺
			    autoPosition:false//禁止自动定位
			  });
			  mapObj.addControl(tool);
			  //加载鹰眼
			  view=new AMap.OverView();
			  mapObj.addControl(view);
			  //加载比例尺
			  scale=new AMap.Scale();
			  mapObj.addControl(scale);
			  mapObj.plugin(["AMap.MapType"], function() {
					var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
					mapObj.addControl(type);
				});
			});
		 AMap.event.addListener(mapObj,'click',function(e) {
				if(inforWindowone!=""){
					inforWindowone.close();
				}
			});
	}



//查询按钮
function chaxun(){
	if($("#keyword").val()!=""){
		var nodes = treeObj.getNodesByParamFuzzy("name", $("#keyword").val(), null);
		 treeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);  
	}else{
		$.ajax({
			url : 'cartree.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				fuzhi(json.data);  
			},
			error:function(){
			}		
		});
	}
	/*$.ajax({
		url : 'chaxunvhic.action',
		type : 'post',
		data:{
			"keyword" : $("#keyword").val(),
			"compid" : $("#compid").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var vehilist1 = json.vehilist;
			var lidata="";
			for(var i=0;i<vehilist1.length;i++){
				if(vehilist1[i].onoffstate=="1"){
					if(vehilist1[i].CarStatus=="0"){
						lidata +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist1[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/3.png'>"+vehilist1[i].vehino+"</td></tr>";
					}else{
						lidata +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist1[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/2.png'>"+vehilist1[i].vehino+"</td></tr>";
					}
				}else{
					lidata +="<tr><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist1[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/1.png'>"+vehilist1[i].vehino+"</td></tr>";
				}
			}
			$("#vehi_data").html(lidata);
		},
		error:function(){
			
		}		
	});
	*/
}
var vehilist;
function query(){
//	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url : 'queryvhic.action',
		type : 'post',
		data:{
			"keyword" : "",
			"compid" : $("#compid").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			vehilist = json.vehilist;
			mapObj.clearMap();
			markers= [];
			var num =json.num;
			$("#total").html(num.total);
			$("#onnum").html(num.onnum+"("+parseInt(parseInt(num.onnum)/parseInt(num.total)*100)+'%'+")");
			$("#offnum").html(num.offnum);
			if(num.onnum=="0"){
				$("#hnum").html("0(0%)")
			}else{
				$("#hnum").html(num.hnum+"("+parseInt(parseInt(num.hnum)/parseInt(num.onnum)*100)+'%'+")");
			}
			$("#nnum").html(num.nnum);
			/*
			var lidata="";
			for(var i=0;i<vehilist.length;i++){
				if(vehilist[i].onoffstate=="1"){
					if(vehilist[i].CarStatus=="0"){
						lidata +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/3.png'>"+vehilist[i].vehino+"</td></tr>";
					}else{
						lidata +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/2.png'>"+vehilist[i].vehino+"</td></tr>";
					}
				}else{
					lidata +="<tr><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;<img src='img/car/1.png'>"+vehilist[i].vehino+"</td></tr>";
				}
				
				//lidata +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' id='"+vehilist[i].vehino+"' onclick='changevehi()'>&nbsp;&nbsp;&nbsp;"+vehilist[i].vehino+"</td></tr>";
			}
			$("#vehi_data").html(lidata);
*/
			
			var ntime=setformat(new Date());
			$("#ntime").html(ntime);
			var arealist = json.arealist;
			for(j=0;j<vehilist.length;j++){
				addmks(vehilist[j]);
			}
			if(j==vehilist.length){
				addCluster(1);
				for(var i=0;i<arealist.length;i++){
					addPolygon(arealist[i].areazbs,arealist[i].areaname,arealist[i].areams,arealist[i].areabjs,arealist[i].areasize);
					var zbs = arealist[i].areazbs.split(";");
					var lo = zbs[0].split(",")[0];
					var la = zbs[0].split(",")[1];
				}
			}
		//	$.unblockUI();
		if(smsbz==0){
			sms=setTimeout("query()",20000);
		}else if(smsbz==1){
			if(sms!=null){
				clearTimeout(sms);
			}
		}
		},
		error:function(){
		//	$.unblockUI();
		}		
	});
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
function addPolygon(obj,name,ms,bjs,sz){  
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
	var cars=0;  
	for(j=0;j<vehilist.length;j++){
		var po = new AMap.LngLat(vehilist[j].longi,vehilist[j].lati);
		if(polygon.contains(po)){
			cars++;
		}
	}
	polygon.setMap(mapObj);  

	if(parseInt(cars)>=parseInt(bjs)){
		name = "<font color='red'>"+name+": "+cars+"</font>/"+bjs;
	}else{
		name = name+": "+cars+"/"+bjs;
	}
	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = zpp(ms);
		markerContent.onclick=function() { addTab(100,name,"find.action?time="+ntime+"&id="+id);};
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+"<br/><br/>区域面积:"+sz+"<br/>区域描述:"+ms;};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:mapObj,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
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
	if(obj.onoffstate=="1"){
		if(obj.carStatus=="0"){
			markerImg.src="img/car/c.png";   
		}else{
			markerImg.src="img/car/h.png";   
		}
	}else{
		markerImg.src="img/car/d.png";   
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
	var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[SIM卡]</b>："+obj.vehisim+"<br/><b>[车辆所属人]</b>："+obj.ownname
					  +"<br/><b>[联系电话]</b>："+obj.owntel+"<br/><b>[经度]</b>："+obj.longi+"<br/><b>[纬度]</b>："+obj.lati;
	var info = [];                 
	info.push(txt);                 
	               
	var inforWindow = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(0,0),                 
	  content:info.join("<br>")                 
	});                 
	  AMap.event.addListener(marker1,"click",function(e){                 
		  inforWindow.open(mapObj,marker1.getPosition());                 
		});
	  AMap.event.addListener(mapObj,"click",function(e){                 
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
		var sts=[{url:"img/car/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
		mapObj.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObj,markers,{minClusterSize:10,styles:sts});
		});
	}
	else {
		mapObj.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObj,markers);
		});
	}
}


function allcheck(){
	$("[name='vehicheckbox']").prop("checked",true);
	changevehi();
}
function allnocheck(){
	$("[name='vehicheckbox']").prop("checked",false);
	changevehi();
}

function changevehi(){
	getdwmsg();
}
var carids;
function getdwmsg(){
	carids="";
	var carnum=0;
	$('input[type="checkbox"][name="vehicheckbox"]:checked').each(function(){ 
		 if($(this).prop("checked")){
			 carids+=this.id+",";
			 carnum++;
		} 
	});
	 getvehidwmsg(carids);
	$("#carnumid").html(carnum);
}

function getvehidwmsg(carids){
	$.ajax({
		url : 'finddwvhic.action',
		type : 'post',
		data:{
			"vehiid" : carids,
			"iscomp" : vehilist.length
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var vehidwgps = json.vehilist;
			var trdata="";
			for(var i=0;i<vehidwgps.length;i++){
				var kzc ="";
				if(vehidwgps[i].onoffstate=="1"){
					kzc ="上线";
				}else{
					kzc ="下线";
				}
				trdata +="<tr id='"+vehidwgps[i].vehino+"' ondblclick='monitor(this.id)'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+vehidwgps[i].vehino+"</td><td nowrap='nowrap'>"+vehidwgps[i].color+"</td><td nowrap='nowrap'>"+vehidwgps[i].compname+"</td><td nowrap='nowrap'>"+vehidwgps[i].vehisim+"</td><td nowrap='nowrap'>"+vehidwgps[i].speed+"</td><td nowrap='nowrap'>"+kzc+"</td><td nowrap='nowrap'>"+vehidwgps[i].dateTime+"</td><td nowrap='nowrap'>"+vehidwgps[i].address+"</td><td nowrap='nowrap'>"+vehidwgps[i].mdtno+"</td><td nowrap='nowrap'>"+vehidwgps[i].cartype+"</td></tr>";
			}
			$("#dw_data").html(trdata);
		},
		error:function(){
			
		}		
	});
}
var mdt;
function monitor(obj){
		carid = obj;
		$.ajax({
			url : 'monitor.action',
			type : 'post',
			data:{
				"vehiid" : carid
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					mdt = json.mdt;
					addMarker(mdt);
			},
			error:function(){
				
			}		
		});
}
function addMarker(mdt){
	if(marker!=""){
	    marker.setMap(null);
	}
//	ngeocoder(mdt.longi,mdt.lati);
	var markerContent = document.createElement("div");
    markerContent.className = "txtstyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(mdt.onoffstate=="1"){
		if(mdt.carStatus=="0"){
			markerImg.src="img/car/c.png";   
		}else{
			markerImg.src="img/car/h.png";   
		}
	}else{
		markerImg.src="img/car/d.png";   
	}
	markerImg.id=mdt.messageid
	markerContent.appendChild(markerImg);
	marker = new AMap.Marker({
	    map:mapObj,
	    position:new AMap.LngLat(mdt.longi,mdt.lati),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	mapObj.setCenter(new AMap.LngLat(mdt.longi,mdt.lati));
	var txt = "<b style='color:#3399FF'>"+mdt.vehino+"</b><br/><b>[所属公司]</b>："+mdt.compname+"<br/><b>[车辆类型]</b>："+mdt.cartype+"<br/><b>[车辆颜色]</b>："+mdt.color+"<br/><b>[SIM卡]</b>："+mdt.vehisim+"<br/><b>[车辆所属人]</b>："+mdt.ownname
					  +"<br/><b>[联系电话]</b>："+mdt.owntel+"<br/><b>[经度]</b>："+mdt.longi+"<br/><b>[纬度]</b>："+mdt.lati+"<br/><b>[所在位置]</b>："+mdt.address;
	var info = [];                 
	info.push(txt);                 
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(0,0),                 
	  content:info.join("<br>")                 
	});           
	  inforWindowone.open(mapObj,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(mapObj,marker.getPosition());                 
		});
}
function zpp(obj){
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
</script>
<script type="text/javascript" language="javascript">  
	$(document).ready(function(){  
		$.ajax({
			url : 'cartree.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				
				fuzhi(json.data);  
			},
			error:function(){
			}		
		});
      });  

	var treeObj;
	var setting = { 
			async: {
				enable: true, //启用异步加载
				url:"findcars.action", //调用的后台的方法
				autoParam:["id"],  //向后台传递的参数
				dataFilter: filter
		//		,
		//		otherParam: ["keyword", function(){
		//			return $("#keyword").val(); }]
			},
            check: {  
                enable: true  
            },  
            data: {  
                simpleData: {  
                    enable: true  
                }  
            },  
            callback: {     /**回调函数的设置，随便写了两个**/  
            	onCheck: zTreeOnCheck,
            	onClick: carmoni
            	 }  
        };  
	function filter(treeId, parentNode, childNodes) {
		var array =  eval("("+childNodes.zdata+")");  //获取后台传递的数据
		return array;
		}
	function fuzhi(data){  
        zNodes=eval("("+data+")");  
        treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);  
    } 
	function zTreeOnCheck(event, treeId, treeNode) {
	    var nodes = treeObj.getCheckedNodes(true);
    	carids="";
    	var carnum=0;
    	for(var i=0;i<nodes.length;i++){
        	if(nodes[i].name.indexOf("浙A") >= 0){
    			 carids+=nodes[i].name+",";
    			 carnum++;
            }	
        }
    	$("#carnumid").html(carnum);
    	 getvehidwmsg(carids);
	};

	function carmoni(event, treeId, treeNode) {
		monitor(treeNode.name);
	};
	function switchSysBar1(){
		if ($("#switchPoint1").val()=="3"){
			$("#switchPoint1").val("4");
			$("#monitortabs").css('display','none'); 
			 $("#mapdiv").height("100%"); 
			$("#utod").attr("class", "fa fa-chevron-up");
		}
		else{
			$("#switchPoint1").val("3");
			$("#monitortabs").css('display','block');
			 $("#mapdiv").height("65%"); 
			$("#utod").attr("class", "fa fa-chevron-down");
		}
	}

	function switchSysBar(){
		if ($("#switchPoint").val()=="3"){
			$("#switchPoint").val("4");
			$("#moniright").css('display','none'); 
			$("#lefttd").width("98%"); 
			$("#ltor").attr("class", "fa fa-chevron-left");
		}
		else{
			$("#switchPoint").val("3");
			$("#moniright").css('display','block');
			$("#lefttd").width("74%"); 
			$("#ltor").attr("class", "fa fa-chevron-right");
		}
	}
</script>  
<style>
.txtstyle{position:relative;background: rgba(255,204,51, 0.5);}
.txtstyle span{
white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #FFCC66;
	color:black;
	width:auto;
	border:1px solid #ffffff;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;
}
td,th{ nowrap:nowrap;}
</style>
<script language="javascript" type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>  
	<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script> 
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">