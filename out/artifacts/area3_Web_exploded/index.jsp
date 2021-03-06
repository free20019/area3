﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<title>  </title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=edge; chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production_unminified.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">
		<link rel="stylesheet" type="text/css" href="css/gddiv.css" />
		<link rel="stylesheet" type="text/css" href="css/image.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/demo.css">
		<!-- FAVICONS -->
		
		<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<!-- Startup image for web apps -->
		<link rel="apple-touch-startup-image" href="img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
		<link rel="apple-touch-startup-image" href="img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="stylesheet" href="js/stylesheets/jquery.tooltip/jquery.tooltip.css" type="text/css" />
    <script type="text/javascript" src="js/javascripts/jquery.min.js"></script>
    <script type="text/javascript" src="js/javascripts/jquery.tooltip.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript">
      $j = jQuery.noConflict();
      $j(document).ready(function(){
        $j("div.item").tooltip();
      });
    </script>
	      <script src="js/html5shiv.min.js"></script>
	      <script src="js/respond.min.js"></script>
		<script src="js/libs/jquery-2.1.1.min.js"></script>
		<script src="js/libs/jquery-ui.min.js"></script>
		<script src="js/jQueryRotate.2.2.js"></script>
		<script src="js/map.js"></script>
		<!-- 
		<script src="js/jquery.blockUI.js"></script>
		 -->
		 <!-- 
		  <script src="http://webapi.amap.com/maps?v=1.2&key=0a54a59bdc431189d9405b3f2937921a" type="text/javascript"></script>
		  -->
		<script src="http://webapi.amap.com/maps?v=1.3&key=20be63c6a78db94a95f7393077b90dca&plugin=AMap.Geocoder" type="text/javascript"></script>
		<style type="text/css">
  			#tabs li .ui-icon-close {	float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
		</style>

		<script type="text/javascript">
		$(document).ready(function(){
			 $(".menu li").hover(function() {
		 	 $(this).find("em").animate({opacity: "show", top: "-75"}, "slow");
		 	}, function() {
		  	$(this).find("em").animate({opacity: "hide", top: "-85"}, "fast");
		 	});
		 	});
		var mapObj;
		var view;
		var scale;
		var smsbz=0;
		var manysmsbz=0;
		var sms0=null;
		var sms3=null;
			$(function() {
				var tabs=$( "#tabs" ).tabs();
			    $("#tabs-5").load("jsp/login/operation.jsp");
			    tabs.delegate( "span.ui-icon-close", "click", function() {
			      var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
			      $( "#" + panelId ).remove();
			      tabs.tabs( "refresh" );
			    });
			    tabs.bind( "keyup", function( event ) {
			      if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
			        var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
			        $( "#" + panelId ).remove();
			        tabs.tabs( "refresh" );
			      }
			    });
			
			});
				var tabid;
				var title;
				var url;
				        var Li="";
				function addTab(tabid,title,url) {
					if(tabid=="0"){
						smsbz=0;
					}else{
						smsbz=1;
					}
					if(tabid=="3011"){
						manysmsbz=0;
					}else{
						manysmsbz=1;
					}
					var tabs=$( "#tabs" ).tabs();
					var existing = tabs.find("[id='tabs-" + tabid + "']");
					if(existing.length==0){
					    var TabName = title /* TabName 和 TabCont 可从其他地方动态加载 */
				        TabId = "tabs-" + tabid,
				       	Li = "<li><a href='#" + TabId + "' onclick='stop("+tabid+")'>" + TabName + "</a><span class='ui-icon ui-icon-close' role='presentation' id='tabs"+tabid+"' onclick='tabclose(this.id)'>删除标签页</span></li>";
					    tabs.find( "ul:eq(0)" ).append(Li);
					    tabs.append( "<div id='" + TabId + "' style='height: 100%; display: none;'></div>" ); 
					    tabs.tabs( "refresh" );    
					    $("#"+TabId).load(url);
					   var index = $("#"+TabId).parent().find("#"+TabId).index()-1;
					    $("#tabs").tabs({ active: index });       
					}else{
						if(tabid=="0"){
							query();
						}else{
							smsbz=1;
						}
						if(tabid=="3011"){
							monitorallmany();
						}else{
							manysmsbz=1;
						}
						var index = $("#tabs-"+tabid).parent().find("#tabs-"+tabid).index()-1;
						$("#tabs").tabs({ active: index });       
					}
				}

			
			  
		  </script>
	</head>
	<body class="smart-style-3">
		<!-- POSSIBLE CLASSES: minified, fixed-ribbon, fixed-header, fixed-width
			 You can also add different skin classes such as "smart-skin-1", "smart-skin-2" etc...-->
		
		<!-- HEADER -->
		<header id="header">
			<div id="logo-group" style="width: 70%;" >
				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"><img src="img/logo1.png" alt="SmartAdmin"> </span> 
				<!-- END LOGO PLACEHOLDER -->

			</div>


			<!-- pulled right: nav area -->
			<div class="pull-right">

				<!-- 提交问题 -->
				<!-- 提交问题 -->	

				<!-- collapse menu button -->
				<div id="" class="btn-header pull-right">
					<span> <a>${realname }<i class=""></i></a> </span>
				</div>
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="收缩菜单栏"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="login1.html" title="退出" data-logout-msg="确定退出？"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- end logout button -->



				<!-- fullscreen button -->
				<div id="fullscreen" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0);" onclick="launchFullscreen(document.documentElement);" title="全屏"><i class="fa fa-fullscreen"></i></a> </span>
				</div>
				<!-- end fullscreen button -->


			</div>
			<!-- end pulled right: nav area -->

		</header>
		<!-- END HEADER -->

		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel">

			<!-- User info -->
			<div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
					
						<a href="javascript:void(0);" id="show-shortcut">
						<span>
							<p>导航菜单</p>
							<input type="hidden" id="stationgw" value="${station}">
							<input type="hidden" id="stationadmin" value="${stationadmin}">
							<input type="hidden" id="usernameye" value="${realname}">
							<input type="hidden" id="station_id" value="${station_id}">
						</span>
					</a> 
						
				</span>
			</div>
			<!-- end user info -->

			<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
			<nav>
				<!-- NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->

				<ul>
					<li id="yytstl"><a href="javascript:addTab('6','营运态势(图例)','jsp/login/home.jsp');"><i class="fa fa-lg fa-fw fa-home"></i><span class="menu-item-parent">营运态势(图例)</span></a></li>
					<li id="yyts"><a href="javascript:addTab('5','营运态势','jsp/login/operation.jsp');"><i class="fa fa-lg fa-fw fa-home"></i><span class="menu-item-parent">营运态势</span></a></li>
					<li id="ssjk"><a href="javascript:addTab('0','实时监控','jsp/monitor.jsp');"><i class="fa fa-lg fa-fw fa-map-marker"></i><span class="menu-item-parent">实时监控</span></a>
					</li>
<!-- 					<li id="dcjk" style="display: none;"><a href="javascript:addTab('3','多车监控','jsp/manymonitor.jsp');"><i class="fa fa-lg fa-fw fa-random"></i><span class="menu-item-parent">多车监控</span></a></li> -->
					<li id="dcjk">
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">车辆监控</span></a>
						<ul>
							<li id="dcjks">
								<a href="javascript:addTab('3011','多车监控','jsp/manymonitor.jsp');">多车监控</a>
							</li>
							<li id="fpjk">
								<a href="javascript:addTab('3021','分屏监控','jsp/splitmonitor.jsp');">分屏监控</a>
							</li>
						</ul>
					</li>
					<li id="tlcl" style="display: none;"><a href="javascript:addTab('2','停留车辆监控','jsp/stopmonitor.jsp');"><i class="fa fa-lg fa-fw fa-rub"></i><span class="menu-item-parent">停留车辆监控</span></a></li>
					<li id="lsgj" style="display: none;"><a href="javascript:addTab('1','历史轨迹','jsp/history.jsp');"><i class="fa fa-lg fa-fw fa-picture-o"></i><span class="menu-item-parent">历史轨迹</span></a></li>
					<li id="sjxw" style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i><span class="menu-item-parent">营运行为分析</span></a>
						<ul  class="menu">
						<div id="" class="item">
        					<li id="z1" style="display: none;">
								<a href="javascript:addTab('100','重点监控区域车辆分析','jsp/findarea.jsp');">重点监控区域车辆分析</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/28.jpg" style="height: 40px;width:140px;"></img>
					        </div>
					      </div>
					      <div id="" class="item">
					       <li id="z2" style="display: none;">
								<a href="javascript:addTab('103','重点监控区域车辆明细查询','jsp/findmingxi.jsp');">重点监控区域车辆明细</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/29.jpg" style="height: 40px;width:140px;"></img>
					        </div>
					      </div>
					       <div id="" class="item">
							 <li id="z3" style="display: none;">
								<a href="javascript:addTab('104','重点监控区域时段停留车辆查询','jsp/findtimevehi.jsp');">重点监控区域时段停留车辆</a>
							</li>
							<div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/31.jpg" style="height: 40px;width:140px;"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="z4" style="display: none;">
								<a href="javascript:addTab('101','重点监控区域上线率分析','jsp/findonline.jsp');">重点监控区域上线率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/1.jpg"></img>
					        </div>
					      </div>
					      
							<div id="" class="item">
        					<li id="z5" style="display: none;">
								<a href="javascript:addTab('102','重点监控区域重车率分析','jsp/findload.jsp');">重点监控区域重车率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/2.jpg"></img>
					        </div>
					      </div>
							
							<div id="" class="item">
        					<li id="z6" style="display: none;">
								<a href="javascript:addTab('107','在线营运率分析','jsp/findyingyun.jsp');">在线营运率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					      <img id ="image" src="img/Formula/3.jpg"></img>
					        </div>
					      </div>
							
							<div id="" class="item">
        					<li id="z7" style="display: none;">
								<a href="javascript:addTab('108','实载率分析','jsp/findshizai.jsp');">实载率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					       	<img  id ="image"  src="img/Formula/4.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="z8" style="display: none;">
								<a href="javascript:addTab('105','上线率分析','jsp/findallonline.jsp');">上线率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					<img  id ="image" src="img/Formula/5.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="z9" style="display: none;">
								<a href="javascript:addTab('106','重车率分析','jsp/findallload.jsp');">重车率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/6.jpg"></img>
					        </div>
					      </div>
							
						</ul>
					</li>
					<li id="yyyw">
						<a href="#"><i class="fa fa-lg fa-fw fa-table"></i><span class="menu-item-parent">营运业务分析</span></a>
						<ul>
							<div id="" class="item">
        					<li id="y1" style="display: none;">
								<a href="javascript:addTab('200','单车平均营运收益分析','jsp/findyyshouyi.jsp');">单车平均营运收益分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/7.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="y2" style="display: none;">
								<a href="javascript:addTab('201','单车平均载客里程分析','jsp/findsslicheng.jsp');">单车平均载客里程分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/8.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="y3" style="display: none;">
								<a href="javascript:addTab('202','单车平均空驶里程分析','jsp/findkslicheng.jsp');">单车平均空驶里程分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					         <img id ="image" src="img/Formula/9.jpg"></img>
					        </div>
					      </div>
					      <div id="" class="item">
        					<li id="y4" style="display: none;">
								<a href="javascript:addTab('203','单车平均行驶总里程分析','jsp/findzlicheng.jsp');">单车平均行驶总里程分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/10.jpg"></img>
					        </div>
					      </div>
					      <div id="" class="item">
        					<li id="y5" style="display: none;">
								<a href="javascript:addTab('204','单车平均营运次数分析','jsp/findyycishu.jsp');">单车平均营运次数分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/11.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="y6" style="display: none;">
								<a href="javascript:addTab('205','单车平均载客时间分析','jsp/findyytime.jsp');">单车平均载客时间分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					 			<img  id ="image" src="img/Formula/12.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="y7" style="display: none;">
								<a href="javascript:addTab('206','单车平均载客等候时间分析','jsp/finddhtime.jsp');">单车平均载客等候时间分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/13.jpg"></img>
					        </div>
					      </div>
						 	
						</ul>
					</li>
					<li id="ttbb" style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-list-alt"></i><span class="menu-item-parent">统计报表</span></a>
						<ul>
					     
					      <div id="" class="item">
							<li id="t1" style="display: none;">
								<a href="javascript:addTab('300','营运记录查询','jsp/find/bus.jsp');">营运记录查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/15.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							 <li id="t2" style="display: none;">
								<a href="javascript:addTab('301','群组营运记录查询','jsp/find/groupsel.jsp');">群组营运记录查询</a>
							</li>	
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/16.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							 <li id="t3" style="display: none;">
								<a href="javascript:addTab('302','车辆营运数据统计','jsp/find/vehicle.jsp');">车辆营运数据统计</a>
							</li>		
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/17.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t4" style="display: none;">
								<a href="javascript:addTab('303','业户运营数据统计','jsp/find/company.jsp');">业户营运数据统计</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/18.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t5" style="display: none;">
								<a href="javascript:addTab('304','群组营运数据统计','jsp/find/group.jsp');">群组营运数据统计</a>
							</li>	
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/19.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t6" style="display: none;">
								<a href="javascript:addTab('305','从业人员营运数据统计','jsp/find/certno.jsp');">从业人员营运数据统计</a>
							</li>	
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/20.jpg"></img>
					        </div>
					        </div>
					         <li id="t101" style="display: none;">
								<a href="javascript:addTab('6085','终端类型营运数据统计','jsp/find/mdtno.jsp');">终端类型营运数据统计</a>
							</li>
					        <div id="" class="item">
							 <li id="t7" style="display: none;">
								<a href="javascript:addTab('306','无营运数据车辆查询','jsp/findoffline.jsp');">无营运数据车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/21.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t8" style="display: none;">
								<a href="javascript:addTab('307','未上线车辆查询','jsp/findnoline.jsp');">未上线车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/22.jpg"></img>
					        </div>
					        </div>
					        <li id="t16" style="display: none;">
								<a href="javascript:addTab('314','上下客监测点车辆查询','jsp/updown.jsp');">上下客监测点车辆查询</a>
							</li>
					        <div id="" class="item">
							<li id="t9" style="display: none;">
								<a href="javascript:addTab('309','单车速度曲线及里程统计','jsp/stat/speed_t1.jsp');">单车速度曲线及里程统计</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/23.jpg"></img>
					        </div>
					        </div>
							 <li id="t10" style="display: none;">
								<a href="javascript:addTab('550','营运日报','jsp/finddayform.jsp');">营运日报</a>
							</li>
					        <div id="" class="item">
							<li id="t11" style="display: none;">
								<a href="javascript:addTab('310','营运月报','jsp/findmonthform.jsp');">营运月报</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/24.jpg"></img>
					        </div>
							</div>
							 <div id="" class="item">
        					<li id="t12" style="display: none;">
								<a href="javascript:addTab('207','业户车辆营运情况分析','jsp/findareaname.jsp');">业户车辆营运情况分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/14.jpg"></img>
					        </div>
					      </div>
					        <div id="" class="item">
							<li id="t13" style="display: none;">
								<a href="javascript:addTab('312','营运年报(月)','jsp/findyearform.jsp');">营运年报(月)</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/25.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t14" style="display: none;">
								<a href="javascript:addTab('311','营运年报(季)','jsp/findseasonsform.jsp');">营运年报(季)</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/26.jpg"></img>
					        </div>
					        </div>
					        <div id="" class="item">
							<li id="t15" style="display: none;">
								<a href="javascript:addTab('313','营运逐年报','jsp/findyearsform.jsp');">营运逐年报</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/27.jpg"></img>
					        </div>
					        </div>
					        <li id="" >
								<a href="javascript:addTab('356','疑似模子出租信息统计','jsp/findtaxiinfo.jsp');">疑似模子出租信息统计</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">春运专题</span></a>
						<ul>
							<li>
								<a href="javascript:addTab('600','出租车分布(小时)','jsp/findhourfx.jsp');">出租车分布(小时)</a>
							</li>
							<li>
								<a href="javascript:addTab('601','出租车分布(日)','jsp/finddayfx.jsp');">出租车分布(日)</a>
							</li>
<%--							<li>--%>
<%--								<a href="javascript:addTab('602','重点区域服务车次(小时)','jsp/findzdqyxstj.jsp');">重点区域服务车次(小时)</a>--%>
<%--							</li>--%>
							<li>
								<a href="javascript:addTab('603','重点区域服务车次(小时)','jsp/findzdqyxskz.jsp');">重点区域服务车次(小时)</a>
							</li>
<%--							<li>--%>
<%--								<a href="javascript:addTab('603','重点区域服务车次(日)','jsp/findzdqyrtj.jsp');">重点区域服务车次(日)</a>--%>
<%--							</li>--%>
							<li>
								<a href="javascript:addTab('604','重点区域服务车次(日)','jsp/findzdqyrkz.jsp');">重点区域服务车次(日)</a>
							</li>
							<li>
								<a href="javascript:addTab('605','重点区域服务车次(日总计)','jsp/findzddqyzs.jsp');">重点区域服务车次(日总计)</a>
							</li>
						</ul>
					</li>
					<li id="yhgl" style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">用户中心</span></a>
						<ul>
							<li id="yonghugl" style="display: none;">
								<a href="javascript:addTab('400','用户管理','jsp/usercenter/finduser.jsp');">用户管理</a>
							</li>
							<li id="gangweigl" style="display: none;">
								<a href="javascript:addTab('401','岗位管理','jsp/usercenter/findstation.jsp');">岗位管理</a>
							</li>
						</ul>
					</li>
					<li id="jcsj" style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">基础数据</span></a>
						<ul>
							<li id="areaguanli" style="display: none;">
								<a href="javascript:addTab('500','区域管理','jsp/area.jsp');">区域管理</a>
							</li>
							<li id="dxsxk" >
								<a href="javascript:addTab('503','典型上下客监测点','jsp/updownarea.jsp');">典型上下客监测点</a>
							</li>
							<li id="clfbqy" >
								<a href="javascript:addTab('504','车辆分布区域','jsp/vehiarea.jsp');">车辆分布区域</a>
							</li>
							<li id="vhicgroup" style="display: none;">
								<a href="javascript:addTab('501','车辆组管理','jsp/usercenter/findgroup.jsp');">车辆组管理</a>
							</li>
							<li id="regular" >
								<a href="javascript:addTab('502','常规下线情况表','jsp/usercenter/regular.jsp');">常规下线情况表</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			
<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>
		</aside>
		<!-- END NAVIGATION -->

		<!-- MAIN PANEL -->
		<div id="main" role="main" style="height: 90%;">


			<!-- MAIN CONTENT -->
			<div id="tabs" style="height: 100%;border: 0 none">
					<ul id="tabul">
						<li><a href="#tabs-5"  onclick="stop('5')">营运态势</a><span class='ui-icon ui-icon-close' role='presentation' id='tabs0' onclick='tabclose(this.id)'>删除标签页</span></li>
					</ul>
					<div id="tabs-5" style="height: 100%;">
					</div>
			</div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->

		<!--================================================== -->



		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->
		<script src="js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
		<!-- CUSTOM NOTIFICATION -->
		<script src="js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices: you can disable this in app.js -->
		<script src="js/plugin/fastclick/fastclick.js"></script>

		<!--[if IE 7]>
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
		<![endif]-->

		<!-- Demo purpose only -->
		<script src="js/demo.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="js/appsosuo.js"></script>
		<script type="text/javascript">
			if($("#usernameye").val()!='admin'){
				$("#dxsxk").css('display','none');
				$("#clfbqy").css('display','none');
				$("#regular").css('display','none');
			}
			var yemian=$("#stationgw").val().split(',');
			var guanli=$("#stationadmin").val().split(',');
			for(var i=0;i<guanli.length;i++){
				if(guanli[i]=="用户管理增加"||guanli[i]=="用户管理删除"||guanli[i]=="用户管理修改"||guanli[i]=="用户管理查看"||guanli[i]=="岗位管理增加"||guanli[i]=="岗位管理删除"||guanli[i]=="岗位管理修改"||guanli[i]=="岗位管理查看"){
					$("#yhgl").css('display','');
				}
				if(guanli[i]=="用户管理增加"||guanli[i]=="用户管理删除"||guanli[i]=="用户管理修改"||guanli[i]=="用户管理查看"){
					$("#yonghugl").css('display','');
				}
				if(guanli[i]=="岗位管理增加"||guanli[i]=="岗位管理删除"||guanli[i]=="岗位管理修改"||guanli[i]=="岗位管理查看"){
					$("#gangweigl").css('display','');
				}
				if(guanli[i]=="车辆组管理增加"||guanli[i]=="车辆组管理删除"||guanli[i]=="车辆组管理修改"||guanli[i]=="车辆组管理查看"||guanli[i]=="区域管理增加"||guanli[i]=="区域管理删除"||guanli[i]=="区域管理修改"||guanli[i]=="区域管理查看"){
					$("#jcsj").css('display','');
				}
				if(guanli[i]=="车辆组管理增加"||guanli[i]=="车辆组管理删除"||guanli[i]=="车辆组管理修改"||guanli[i]=="车辆组管理查看"){
					$("#vhicgroup").css('display','');
				}
				if(guanli[i]=="区域管理增加"||guanli[i]=="区域管理删除"||guanli[i]=="区域管理修改"||guanli[i]=="区域管理查看"){
					$("#areaguanli").css('display','');
				}
			}
			for(var i=0;i<yemian.length;i++){
			if(yemian[i]=="多车监控"){
					$("#dcjk").css('display','');
				}
				if(yemian[i]=="停留车辆监控"){
					$("#tlcl").css('display','');
				}
				if(yemian[i]=="历史轨迹"){
					$("#lsgj").css('display','');
				}
				if(yemian[i]=="重点监控区域车辆分析"||yemian[i]=="重点监控区域车辆明细"||yemian[i]=="重点监控区域时段停留车辆"||yemian[i]=="重点监控区域上线率分析"||yemian[i]=="重点监控区域重车率分析"||yemian[i]=="在线营运率分析"||yemian[i]=="实载率分析"||yemian[i]=="上线率分析"||yemian[i]=="重车率分析"){
					$("#sjxw").css('display','');
				}
				if(yemian[i]=="重点监控区域车辆分析"){
					$("#z1").css('display','');
				}
				if(yemian[i]=="重点监控区域车辆明细"){
					$("#z2").css('display','');
				}
				if(yemian[i]=="重点监控区域时段停留车辆"){
					$("#z3").css('display','');
				}
				if(yemian[i]=="重点监控区域上线率分析"){
					$("#z4").css('display','');
				}
				if(yemian[i]=="重点监控区域重车率分析"){
					$("#z5").css('display','');
				}
				if(yemian[i]=="在线营运率分析"){
					$("#z6").css('display','');
				}
				if(yemian[i]=="实载率分析"){
					$("#z7").css('display','');
				}
				if(yemian[i]=="上线率分析"){
					$("#z8").css('display','');
				}
				if(yemian[i]=="重车率分析"){
					$("#z9").css('display','');
				}
				if(yemian[i]=="单车平均营运收益分析"||yemian[i]=="单车平均载客里程分析"||yemian[i]=="单车平均空驶里程分析"||yemian[i]=="单车平均行驶总里程分析"||yemian[i]=="单车平均营运次数分析"||yemian[i]=="单车平均载客时间分析"||yemian[i]=="单车平均载客等候时间分析"){
					$("#yyyw").css('display','');
				}
				if(yemian[i]=="单车平均营运收益分析"){
					$("#y1").css('display','');
				}
				if(yemian[i]=="单车平均载客里程分析"){
					$("#y2").css('display','');
				}
				if(yemian[i]=="单车平均空驶里程分析"){
					$("#y3").css('display','');
				}
				if(yemian[i]=="单车平均行驶总里程分析"){
					$("#y4").css('display','');
				}
				if(yemian[i]=="单车平均营运次数分析"){
					$("#y5").css('display','');
				}
				if(yemian[i]=="单车平均载客时间分析"){
					$("#y6").css('display','');
				}
				if(yemian[i]=="单车平均载客等候时间分析"){
					$("#y7").css('display','');
				}
				
				if(yemian[i]=="营运记录查询"||yemian[i]=="群组营运记录查询"||yemian[i]=="车辆营运数据统计"||yemian[i]=="业户运营数据统计"||yemian[i]=="群组营运数据统计"||yemian[i]=="从业人员营运数据统计"||yemian[i]=="无营运数据车辆查询"||yemian[i]=="未上线车辆查询"||yemian[i]=="单车速度曲线及里程统计"||yemian[i]=="营运日报"||yemian[i]=="营运月报"||yemian[i]=="业户车辆营运情况分析"||yemian[i]=="营运年报(月)"||yemian[i]=="营运年报(季)"||yemian[i]=="营运逐年报"||yemian[i]=="上下客监测点车辆查询"){
					$("#ttbb").css('display','');
				}
				if(yemian[i]=="营运记录查询"){
					$("#t1").css('display','');
				}
				if(yemian[i]=="终端类型营运数据统计"){
					$("#t101").css('display','');
				}
				if(yemian[i]=="群组营运记录查询"){
					$("#t2").css('display','');
				}
				if(yemian[i]=="车辆营运数据统计"){
					$("#t3").css('display','');
				}
				if(yemian[i]=="业户营运数据统计"){
					$("#t4").css('display','');
				}
				if(yemian[i]=="群组营运数据统计"){
					$("#t5").css('display','');
				}
				if(yemian[i]=="从业人员营运数据统计"){
					$("#t6").css('display','');
				}
				if(yemian[i]=="无营运数据车辆查询"){
					$("#t7").css('display','');
				}
				if(yemian[i]=="未上线车辆查询"){
					$("#t8").css('display','');
				}
				if(yemian[i]=="单车速度曲线及里程统计"){
					$("#t9").css('display','');
				}
				if(yemian[i]=="营运日报"){
					$("#t10").css('display','');
				}
				if(yemian[i]=="营运月报"){
					$("#t11").css('display','');
				}
				if(yemian[i]=="业户车辆营运情况分析"){
					$("#t12").css('display','');
				}
				if(yemian[i]=="营运年报(月)"){
					$("#t13").css('display','');
				}
				if(yemian[i]=="营运年报(季)"){
					$("#t14").css('display','');
				}
				if(yemian[i]=="营运逐年报"){
					$("#t15").css('display','');
				}
				if(yemian[i]=="上下客监测点车辆查询"){
					$("#t16").css('display','');
				}
			}
			
		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();

		  function stop(obj){
			  if(obj=="0"){
				  smsbz=0;
				  query();
			  }else{
				  smsbz=1;
			  }
			  if(obj=="3011"){
				  manysmsbz=0;
				  monitorallmany();
			  }else{
				  manysmsbz=1;
			  }
			 }
			 function tabclose(obj){
 				if(obj=="tabs0"){
 						smsbz=1;
 				}
 				if(obj=="tabs3"){
 					manysmsbz=1;
				}
 			}

		</script>

	</body>

</html>