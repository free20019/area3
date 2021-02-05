<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<!-- widget grid -->
<input type="hidden" id="compid" value="${date}">
<input type="hidden" id="userid" value="${userid}">
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv0"><div id="data-div-history" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar0() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod0"><input type="hidden" id="switchPoint0" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
	
	<div style="width: 100%;height: 40%;overflow: auto;" id="downdiv0">
			<div style="overflow: auto;" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>明细数据</h2>

				</header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body">
						<div class="widget-body-toolbar">
							
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<label class="select" style="width:180px;float:left;">
											<select  id="bahistory" class="select" onchange="findcomp(this.value)">
												<option value="0" selected=""  disabled="">--选择公司--</option>
											</select><i></i> 
								</label>
								<label class="select" style="width:180px;float:left;">
											<select id="comphistory" class="select" onchange="findgjcl(this.value)">
												<option value="0" selected=""  disabled="">--选择分公司--</option>
											</select><i></i>
								</label>
								<label class="select" style="width:180px;float:left;">
											<select id="carhistory" class="select">
												<option value="0" selected=""  disabled="">--选择车辆--</option>
											</select><i></i>
								</label>
								<section style="width:80px;float:left;">
										<label class="input"> 
											 <input id="hiscar" type="text"  placeholder="车辆过滤"   onkeyup="queryhiscar(this.value)"></input>
										</label>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="stime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--开始时间--"></input>
										</label>
									</section>
									<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="etime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--结束时间--"></input>
										</label>
									</section>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="queryhis();">查   询</a>
									</form>
									</div>
							<div class="widget-body-toolbar">
							<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
							<div style="float:left;text-align: center;height:32px;line-height:32px;">总里程：</div>
							<section style="width:80px;float:left;">
										<label class="input"> 
											 <input id="zlc" type="text"  placeholder="0.00km"></input>
										</label>
									</section>&nbsp;&nbsp;
									<a href="javascript:void(0);" class="btn btn-primary" onclick="startAnimation();">从头开始</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="pauseAnimation();">暂   停</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="continueAnimation();">继   续</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="quick();">加   快</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="slow();">减   慢</a>
									&nbsp;&nbsp;
									<a href="javascript:void(0);" class="btn btn-primary" onclick="exportgj();">导   出</a><span id="gjdaochu"></span>
								</form>

						</div>

						<!-- gps数据 -->
						<form id="gjbbform">
						<table id="dt_history_basic" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>上报时间</th>
									<th>车辆状态</th>
									<th>经度</th>
									<th>纬度</th>
									<th>方向</th>
									<th>GPS速度(km/h)</th>
									<th>总里程(km)</th>
									<th>位置描述</th>
									<!-- 
									<th>客户兴趣点</th>
									 -->
								</tr>
							</thead>
							<tbody id="vehigpsdata">
							</tbody>
						</table>
					</form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			</div>
</div>
<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
		$("#stime").val(setformatnewlc(smydate));
		$("#etime").val(setformatnewlc(mydate));
//		$("#START_TIME2").val(y+"-01-01");
	});
	function setformatnewlc(obj){
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
	initializehistory();
	findvhichistory();
	// PAGE RELATED SCRIPTS

	var vhicshistory;
	function findvhichistory(){
		$.ajax({
			url : 'chaxunvhichis.action',
			type : 'post',
			data:{
				"compid" : $("#compid").val(),
				"keyword" : ""
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				$("#carhistory").empty();   
				$("#carhistory").append("<option value='0' >--选择车辆--</option>");
				vhicshistory = json.vehilist;
				//alert(vhicshistory.length);
				for(var i=0;i<vhicshistory.length;i++){
					$("#carhistory").append("<option value='"+vhicshistory[i].vehino+"' >"+vhicshistory[i].vehino+"</option>");
				}
				getallvalues();
				var baname = json.baname;
				$("#bahistory").empty();   
				$("#bahistory").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<baname.length;i++){
					$("#bahistory").append("<option value='"+baname[i].id+"' >"+baname[i].name+"</option>");
				}
			},
			error:function(){
				
			}		
		});
	}
	
	var mapObjhistory;
	var markerhistory="";
	function initializehistory() {
		var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
		mapObjhistory=new AMap.Map("data-div-history",{center:position,level:14,resizeEnable:true});//创建地图实例
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

			  mapObjhistory.plugin(["AMap.MapType"], function() {
					var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
					mapObjhistory.addControl(type);
				});
			});

	}
	
	var vehigps;
	function queryhis(){
		if($("#carhistory").val()=="0"){
			alert("请选择一辆车辆");
		}else if($("#stime").val()==""){
			alert("开始时间不能为空");
		}else if($("#etime").val()==""){
			alert("结束时间不能为空");
		}else{
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
			$.ajax({
				url : 'history.action',
				type : 'post',
				data:{
					"vehiid" : $("#carhistory").val(),
					"stime" : $("#stime").val(),
					"etime" : $("#etime").val()
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					$.unblockUI();
						vehigps = json.vehigps;
						mapObjhistory.clearMap();
						if(vehigps.length==0){
							alert("该车辆在该时段 没有GPS数据");
							$("#vehigpsdata").html("");
						}else{
							for(var i=0;i<vehigps.length;i++){
								if(i==0){
									addmkshisstart(vehigps[i]);
								}else if(i==vehigps.length-1){
									addmkshisend(vehigps[i]);
								}else{
									addmkshis(vehigps[i]);
								}
							}
							completeEventHandler(vehigps);
							addtable(vehigps);
					markerMovingControl = new MarkerMovingControl(mapObjhistory, markerhistory, lineArr);
						}
					gjstime=null;
					gjetime=null;
					gjcl=null;
				},
				error:function(){
					$.unblockUI();
				}		
			});
		}
	}
	var polyline="";
	var markerhm="";
	var lineArr=null;
	var subArr=null;
var markerhistory;
	function completeEventHandler(vehigps){
		if(vehigps[0].carstate=="空车"){
			markerhistory = new AMap.Marker({
		        map:mapObjhistory,
		        //draggable:true, //是否可拖动
		        position:new AMap.LngLat(vehigps[0].longi,vehigps[0].lati),//基点位置
		        icon:"img/car2.png", //marker图标，直接传递地址url
		        zIndex:101,
		        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
		        angle:vehigps[0].direction-90,
		        autoRotation:true
		    });
		}else if(vehigps[0].carstate=="重车"){
			markerhistory = new AMap.Marker({
		        map:mapObjhistory,
		        //draggable:true, //是否可拖动
		        position:new AMap.LngLat(vehigps[0].longi,vehigps[0].lati),//基点位置
		        icon:"img/car.png", //marker图标，直接传递地址url
		        zIndex:101,
		        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
		        angle:vehigps[0].direction-90,
		        autoRotation:true
		    });
		}
	    lineArr = new Array();
	    for (var i = 0; i <vehigps.length; i++){
	    	var lngX=vehigps[i].longi;
	    	var lngY=vehigps[i].lati;
		    lineArr.push(new AMap.LngLat(lngX,lngY));
	    }
	    //绘制轨迹
	    polyline = new AMap.Polyline({
	        map:mapObjhistory,
	        path:lineArr,
	        strokeColor:"#00A",//线颜色
	        strokeOpacity:1,//线透明度
	        strokeWeight:3,//线宽
	        strokeStyle:"dashed"//线样式
	    });
	    mapObjhistory.setFitView();
	    //alert(lineArr.length+"   "+vehigps.length);
	   // var linea=0;
	    AMap.event.addListener(markerhistory,'moving',function(e) {
			    for(var i=0;i<lineArr.length;i++){
			        var l = lineArr[i];
			        if(markerhistory.getPosition().distance(l)<=2){
				        
			        	$('#vehigpsdata tr').each(function() {
			       	 		$(this).css('color','black');
			    		});
			           $("#his"+vehigps[i].messageid).css('color','red');
			           if(vehigps[i].carstate=="空车"){
			        	   markerhistory.setIcon("img/car2.png");
					    }else if(vehigps[i].carstate=="重车"){
			        	   markerhistory.setIcon("img/car.png");
					    }
					   // linea = i+1;
						break;
			        }else{
			        	       
				    }
			    }
			});
	  }


	function addmkshis(obj){
		var markerContent = document.createElement("div");
	    markerContent.className = "markerContentStyle";
	    //点标记中的图标
	    var markerImg= document.createElement("img");
		markerImg.className="markerlnglat";
		markerImg.src="img/fx.jpg";   
		markerImg.id="img"+obj.messageid
		markerContent.appendChild(markerImg);
	  var txt = "<b style='color:#3399FF'>"+obj.vehinum+"</b><br/><br/><b>[GPS时间]</b>："+obj.speedtime
	  				  +"<br/><b>[车辆状态]</b>："+obj.carstate
					  +"<br/><b>[GPS速度]</b>："+obj.speed
					  +"<br/><b>[行驶方向]</b>："+dlwz(obj.direction)
					  +"<br/><b>[经纬度]</b>："+obj.longi+","+obj.lati
					  +"<br/><b>[位置描述]</b>："+obj.address;
					  var marker1 =null;
	if(obj.carstate=="空车"){
		var marker1 = new AMap.Marker({
		    //map:mapObjhistory,
		    position:new AMap.LngLat(obj.longi,obj.lati),     
		    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    icon:"img/fx.jpg",
		  //  content:markerContent,   //自定义点标记覆盖物内容
		    angle:obj.direction-90
		});
		}else if(obj.carstate=="重车"){
			var marker1 = new AMap.Marker({
			    //map:mapObjhistory,
			    position:new AMap.LngLat(obj.longi,obj.lati),
			    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    icon:"img/fx2.png",
			  //  content:markerContent,   //自定义点标记覆盖物内容
			    angle:obj.direction-90
			});
			}
		//$("#img"+obj.messageid).rotate(obj.direction-90);
		marker1.setMap(mapObjhistory);  //在地图上添加点

		//添加文本标记
		var info = [];                 
		info.push(txt);                 
		               
		var inforWindow = new AMap.InfoWindow({                 
		  offset:new AMap.Pixel(0,0),                 
		  content:info.join("<br>")                 
		});                 
		  AMap.event.addListener(marker1,"click",function(e){                 
			  inforWindow.open(mapObjhistory,marker1.getPosition());                 
			});

		//添加角度		
		//markerlist.push(marker1);
	}

	function addmkshisstart(obj){
		var markerContent = document.createElement("div");
	    markerContent.className = "markerContentStyle";
	    //点标记中的图标
	    var markerImg= document.createElement("img");
		markerImg.className="markerlnglat";
		markerImg.src="img/start.png";   
		markerImg.id="img"+obj.messageid
		markerContent.appendChild(markerImg);
	  var txt = "<b style='color:#3399FF'>"+obj.vehinum+"(起点)</b><br/><br/><b>[GPS时间]</b>："+obj.speedtime
	  				  +"<br/><b>[车辆状态]</b>："+obj.carstate
					  +"<br/><b>[GPS速度]</b>："+obj.speed
					  +"<br/><b>[行驶方向]</b>："+dlwz(obj.direction)
					  +"<br/><b>[经纬度]</b>："+obj.longi+","+obj.lati
					  +"<br/><b>[位置描述]</b>："+obj.address;
		var marker1 = new AMap.Marker({
		    map:mapObjhistory,
		    position:new AMap.LngLat(obj.longi,obj.lati),
		    zIndex:102,
		    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent   //自定义点标记覆盖物内容
		});
		//$("#img"+obj.messageid).rotate(obj.direction-90);
		marker1.setMap(mapObjhistory);  //在地图上添加点

		//添加文本标记
		var info = [];
		info.push(txt);
		               
		var inforWindow = new AMap.InfoWindow({                 
		  offset:new AMap.Pixel(0,0),                 
		  content:info.join("<br>")                 
		});                 
		  AMap.event.addListener(marker1,"click",function(e){                 
			  inforWindow.open(mapObjhistory,marker1.getPosition());                 
			});

		//添加角度		
		//markerlist.push(marker1);
	}
	function addmkshisend(obj){
		var markerContent = document.createElement("div");
	    markerContent.className = "markerContentStyle";
	    //点标记中的图标
	    var markerImg= document.createElement("img");
		markerImg.className="markerlnglat";
		markerImg.src="img/end.png";   
		markerImg.id="img"+obj.messageid
		markerContent.appendChild(markerImg);
	  var txt = "<b style='color:#3399FF'>"+obj.vehinum+"(终点)</b><br/><br/><b>[GPS时间]</b>："+obj.speedtime
	  				  +"<br/><b>[车辆状态]</b>："+obj.carstate
					  +"<br/><b>[GPS速度]</b>："+obj.speed
					  +"<br/><b>[行驶方向]</b>："+dlwz(obj.direction)
					  +"<br/><b>[经纬度]</b>："+obj.longi+","+obj.lati
					  +"<br/><b>[位置描述]</b>："+obj.address;
		var marker1 = new AMap.Marker({
		    map:mapObjhistory,
		    position:new AMap.LngLat(obj.longi,obj.lati),     
		    zIndex:102,  
		    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent   //自定义点标记覆盖物内容
		});
		//$("#img"+obj.messageid).rotate(obj.direction-90);
		marker1.setMap(mapObjhistory);  //在地图上添加点

		//添加文本标记
		var info = [];                 
		info.push(txt);                 
		               
		var inforWindow = new AMap.InfoWindow({                 
		  offset:new AMap.Pixel(0,0),                 
		  content:info.join("<br>")                 
		});                 
		  AMap.event.addListener(marker1,"click",function(e){                 
			  inforWindow.open(mapObjhistory,marker1.getPosition());                 
			});

		//添加角度		
		//markerlist.push(marker1);
	}
//  function startAnimation() {  
//	$('#vehigpsdata tr').each(function() {
 //    	 		$(this).css('color','black');
  	//	});
       //  $("#his"+vehigps[0].messageid).css('color','red');
		  //markerhistory.moveAlong(lineArr,20); 
	  //}
	  //function stopAnimation() {  
		//  markerhistory.stopMove();
	 // }
	  //轨迹回放
	  
var MarkerMovingControl = function(map, marker, path) {
          this._map = map;
          this._marker = marker;
          this._path = path;
          this._currentIndex = 0;
          marker.setMap(map);
          marker.setPosition(path[0]);
      }
/**
 * 移动marker，会从当前位置开始向前移动
 */
MarkerMovingControl.prototype.move = function() {
    if (!this._listenToStepend) {
        this._listenToStepend = AMap.event.addListener(this, 'stepend', function() {
            this.step();
        }, this);
    }
    this.step();
};

/**
 * 停止移动marker，由于控件会记录当前位置，所以相当于暂停
 */
MarkerMovingControl.prototype.stop = function() {
    this._marker.stopMove();
};

/**
 * 重新开始，会把marker移动到路径的起点然后开始移动
 */
MarkerMovingControl.prototype.restart = function() {
    this.stop();
    this._marker.setPosition(this._path[0]);
    this._currentIndex = 0;
    this.move();
};

/**
 * 向前移动一步
 */
 var spc=500;
function quick(){
	spc=spc+200;
	 shezspc();
}
function slow(){
	if(spc-200<=0){
		alert("已到最小速度");
	}else{
		spc=spc-200;
	}
	shezspc();
}
function shezspc(){
	MarkerMovingControl.prototype.step = function(){
	    var nextIndex = this._currentIndex + 1;
	    if (nextIndex < this._path.length) {
	        if (!this._listenToMoveend) {
	            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
	                this._currentIndex++;
	                AMap.event.trigger(this, 'stepend');
	            }, this);
	        }
	        // console.log(nextIndex);
	       // this._marker.moveTo(this._path[nextIndex], spc);
	        if(this._path[nextIndex].lng == this._path[this._currentIndex].lng && this._path[nextIndex].lat == this._path[this._currentIndex].lat ) {
                this._currentIndex++;
                this.step();
                return;
            }else{
                this._marker.moveTo(this._path[nextIndex], spc);
            }
	    }
	};
}
 
MarkerMovingControl.prototype.step = function(){
    var nextIndex = this._currentIndex + 1;
    if (nextIndex < this._path.length) {
        if (!this._listenToMoveend) {
            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
                this._currentIndex++;
                AMap.event.trigger(this, 'stepend');
            }, this);
        }
        // console.log(nextIndex);
        this._marker.moveTo(this._path[nextIndex], 500);
    }
};
var markerMovingControl = null;

// 编写事件响应函数
function startAnimation() {
	spc=500;
	shezspc();
    markerMovingControl.restart();
//	markerhistory.moveAlong(lineArr,500);
}

function pauseAnimation() {
    markerMovingControl.stop();
}

function continueAnimation() {
    markerMovingControl.move();
}


		 
function addtable(vehigps){
	var trdata="";
	var lc=0;
	for(var i=0;i<vehigps.length;i++){
		if(i==0){
			trdata +="<tr  style='color: red' id='his"+vehigps[i].messageid+"'><td><input type='hidden' value='"+vehigps[i].vehinum+"' name='exportvs["+i+"].vehinum'><input type='hidden' value='"+(i+1)+"' name='exportvs["+i+"].messageid'>"+(i+1)+"</td><td><input type='hidden' value='"+vehigps[i].speedtime+"' name='exportvs["+i+"].speedtime'>"+vehigps[i].speedtime+"</td><td><input type='hidden' value='"+vehigps[i].carstate+"' name='exportvs["+i+"].carstate'>"+vehigps[i].carstate+"</td><td><input type='hidden' value='"+vehigps[i].longi+"' name='exportvs["+i+"].longi'>"+vehigps[i].longi+"</td><td><input type='hidden' value='"+vehigps[i].lati+"' name='exportvs["+i+"].lati'>"+vehigps[i].lati+"</td><td><input type='hidden' value='"+dlwz(vehigps[i].direction)+"' name='exportvs["+i+"].mdttype'>"+dlwz(vehigps[i].direction)+"</td><td><input type='hidden' value='"+vehigps[i].speed+"' name='exportvs["+i+"].speed'>"+vehigps[i].speed+"</td><td><input type='hidden' value='0' name='exportvs["+i+"].color'>0</td><td><input type='hidden' value='"+vehigps[i].address+"' name='exportvs["+i+"].address'>"+vehigps[i].address+"</td></tr>";
		}else{
			var l1 = new AMap.LngLat(vehigps[i].longi,vehigps[i].lati)
			var l2 = new AMap.LngLat(vehigps[i-1].longi,vehigps[i-1].lati)
			lc += l1.distance(l2);
			trdata +="<tr id='his"+vehigps[i].messageid+"'><td><input type='hidden' value='"+vehigps[i].vehinum+"' name='exportvs["+i+"].vehinum'><input type='hidden' value='"+(i+1)+"' name='exportvs["+i+"].messageid'>"+(i+1)+"</td><td><input type='hidden' value='"+vehigps[i].speedtime+"' name='exportvs["+i+"].speedtime'>"+vehigps[i].speedtime+"</td><td><input type='hidden' value='"+vehigps[i].carstate+"' name='exportvs["+i+"].carstate'>"+vehigps[i].carstate+"</td><td><input type='hidden' value='"+vehigps[i].longi+"' name='exportvs["+i+"].longi'>"+vehigps[i].longi+"</td><td><input type='hidden' value='"+vehigps[i].lati+"' name='exportvs["+i+"].lati'>"+vehigps[i].lati+"</td><td><input type='hidden' value='"+dlwz(vehigps[i].direction)+"' name='exportvs["+i+"].mdttype'>"+dlwz(vehigps[i].direction)+"</td><td><input type='hidden' value='"+vehigps[i].speed+"' name='exportvs["+i+"].speed'>"+vehigps[i].speed+"</td><td><input type='hidden' value='"+parseFloat((lc/1000).toFixed(2))+"' name='exportvs["+i+"].color'>"+parseFloat((lc/1000).toFixed(2))+"</td><td><input type='hidden' value='"+vehigps[i].address+"' name='exportvs["+i+"].address'>"+vehigps[i].address+"</td></tr>";
		}
	}
	$("#zlc").val(parseFloat((lc/1000).toFixed(2))+"km");
	$("#vehigpsdata").html(trdata);
}

function dlwz(obj){
	if(obj==0||obj==360){
		return "正北";
	}else if(obj==90){
		return "正东";
	}else if(obj==180){
		return "正南";
	}else if(obj==270){
		return "正西";
	}else if(obj>0&&obj<90){
		return "东北";
	}else if(obj>90&&obj<180){
		return "东南";
	}else if(obj>180&&obj<270){
		return "西南";
	}else if(obj>270&&obj<360){
		return "西北";
	}
	
}
function findcomp(obj){
	$.ajax({
		url : 'findfgs.action',
		type : 'post',
		data:{
			"baid" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var compname = json.compname;
			$("#comphistory").empty();   
			$("#comphistory").append("<option value='0' >--选择公司--</option>");
			for(var i=0;i<compname.length;i++){
				$("#comphistory").append("<option value='"+compname[i].id+"' >"+compname[i].name+"</option>");
			}
			$("#carhistory").empty();   
			$("#carhistory").append("<option value='0' >--选择车辆--</option>");
			vhicshistory = json.vehilist;
			for(var i=0;i<vhicshistory.length;i++){
				$("#carhistory").append("<option value='"+vhicshistory[i].vehino+"' >"+vhicshistory[i].vehino+"</option>");
			}
			getallvalues();
		},
		error:function(){
		}		
	});
}
function findgjcl(obj){
	$.ajax({
		url : 'findgjcl.action',
		type : 'post',
		data:{
			"keyword" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			$("#carhistory").empty();   
			$("#carhistory").append("<option value='0' >--选择车辆--</option>");
			vhicshistory = json.vehilist;
			for(var i=0;i<vhicshistory.length;i++){
				$("#carhistory").append("<option value='"+vhicshistory[i].vehino+"' >"+vhicshistory[i].vehino+"</option>");
			}
			getallvalues();
		},
		error:function(){
			
		}		
	});
}

var arr = new Array(); //数组定义标准形式，不要写成Array arr = new Array();
function getallvalues(){
	arr = new Array();
     $("#carhistory option").each(function () {
         var val = $(this).val(); //获取单个value
         arr.push(val);
     });
}
function queryhiscar(obj){
	if(obj.length>2||obj==""){
		$("#carhistory").empty();   
		for(var i=0;i<arr.length;i++){
			if(arr[i].indexOf($("#hiscar").val())>=0){
				if(arr[i]=="0"){
					$("#carhistory").append("<option value='0' >--选择车辆--</option>");
				}else{
					$("#carhistory").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
				}
			}
		}
	}
}


function exportgj(){
	if(vehigps.length==0){
		alert("没有车辆轨迹数据，无法导出！");
	}else{
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据导出中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'exporthistory.action',
			type : 'post',
			data:$('#gjbbform').serialize(),
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				$.unblockUI();
				var tab=json.message+" 文件:"+json.xlsfilename+"&nbsp;<a href='"+json.action+"'>下载</a>";
				$("#gjdaochu").html(tab);
			},
			error:function(){
				$.unblockUI();
			}		
		});
	}
}
function switchSysBar0(){
	if ($("#switchPoint0").val()=="3"){
		$("#switchPoint0").val("4");
		$("#downdiv0").css('display','none'); 
		 $("#updiv0").height("100%"); 
		$("#utod0").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint0").val("3");
		$("#downdiv0").css('display','block');
		 $("#updiv0").height("60%"); 
		$("#utod0").attr("class", "fa fa-chevron-down");
	}
}
</script>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">