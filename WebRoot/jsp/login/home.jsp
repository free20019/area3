<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<script src="js/echarts/echarts.js"></script>
<script src="js/echarts/bar.js"></script>
<script src="js/echarts/line.js"></script>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height:98%;overflow: scroll">
<div  style="width: 70%;  position: relative;left: 30%;;">
<span style="font-size: 40px;font-weight: bold;">出租汽车营运态势</span><span id="dqtime"></span></div>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>最近1小时营运概况</h2>				
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<table style="width: 100%;">
	<thead>
		<tr style="height:50px;font-weight: bold" >
			<td colspan="3">入网车辆总数</td>
			<td colspan="3">参与营运车辆数</td>
			<td colspan="3">出车率</td>
			<td colspan="3">上线数量</td>
			<td colspan="3">上线率</td>
			<td colspan="3">单车周转次数</td>
			<td colspan="3">单车营收金额</td>							
		</tr>
	</thead>
	<tbody id="">
		<tr>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-primary"  style="font-size: 20px;" id= "homo_cls">9612</span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-primary" style="font-size: 15px" id="home_zs">9887</span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-primary" style="font-size: 15px" id = "home_wrw">275</span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">车<br>辆<br>总<br>数 </div>
			  
			   <div style="float: right; padding: 0px 15px;">未<br>入<br>网<br>数 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-success" style="font-size: 20px;" id="oneyingyun"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-success" style="font-size: 15px" id="szyingyun"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-success" style="font-size: 15px"id="szpjyingyun"></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			  <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			  
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-default" style="font-size: 20px;" id="onechuche"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-default" style="font-size: 15px" id="szchuche"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-default" style="font-size: 15px" id=szpjchuche></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			  <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			  
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-warning" style="font-size: 20px;" id="oneonlines"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-warning" style="font-size: 15px" id="szonlines"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-warning" style="font-size: 15px" id="szpjonlines"></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			  <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			  
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-primary"  style="font-size: 20px;c" id="oneonline"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-primary" style="font-size: 15px" id="szonline"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-primary" style="font-size: 15px" id="szpjonline"></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			   <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-info" style="font-size: 20px;" id="onezhouzhuan"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-info" style="font-size: 15px" id="szzhouzhuan"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-info" style="font-size: 15px" id="szpjzhouzhuan"></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			  <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			  
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
			<td width="100" colspan="3"><div style="text-align:center"><span class="label label-danger" style="font-size: 20px;" id="onejine"></span></div>
				<div style="float: left; text-align: left; padding: 5px 5px 5px 15px;"><span class="label label-danger" style="font-size: 15px" id="szjine"></span></div>
				<div style="float: right; text-align: right; padding: 5px 15px 5px 5px;"><span class="label label-danger" style="font-size: 15px" id="szpjjine"></span></div>
			  <div style="clear: both">
			  <div style="float: left; padding: 0px 15px;">上<br>周<br>同<br>比 </div>
			  <div style="float: left; text-align: center; margin-left: 35px;">	今<br>日 </div>
			  
			   <div style="float: right; padding: 0px 15px;">上<br>周<br>平<br>均 </div>
			  </div>
			</td>
		</tr>
	</tbody>
</table>
					</div>
				</div>
			</div>
		</article>
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>在线营运率分析(最近十二小时)				
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="main1" class="chart has-legend"></div>
					</div>
				</div>
			</div>
		</article>
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>1小时以上未营运车辆(最近十二小时)</h2>				
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="wyy" class="chart has-legend"></div>
					</div>
				</div>
			</div>
		</article>
	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>重点监控区域车辆数量分析(最近十二小时)</h2>				
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="zdl" class="chart has-legend"></div>
					</div>
				</div>
			</div>
		</article>
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
			<div class="jarviswidget" id="wid-id-2" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>重点监控区域时段停留车辆数量</h2>				
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="main2" style="height: 280px"></div>
				</div>
			</div>
		</article>
		
		<article class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
			<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>下线车辆分析</h2>				
				</header>
				<div>
				<div><span id="xxcl"></span></div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="xxf" class="chart"></div>
					</div>
				</div>
			</div>
		</article>
		<article class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
			<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>故障车辆分析</h2>				
				</header>
				<div>
					<div><span id="gzcl"></span></div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="xxf1" class="chart"></div>
					</div>
				</div>
			</div>
		</article>
		
		
</div>

<script type="text/javascript">
	pageSetUp();
	findareanum();
	findyy12();
	findwyy12();
	findclsl12();
	time();
	var date= new Date();
	var f=date.getMinutes();
						var m=date.getSeconds();
						if(f<10){
						 f=10-f;
						}else{
						 f=f%10;
						}
						f=((10-f)*60-m)*1000;
	//时间
	function time(){
		var obj1= new Date();
		var y=(obj1.getFullYear()).toString();
		var m=(obj1.getMonth()+1).toString();
		if(m.length==1){
			m="0"+m;
		}
		var d=obj1.getDate().toString();
		if(d.length==1){
			d="0"+d;
		}
		var h = obj1.getHours().toString();
		if(h.length==1){
			h="0"+h;
		}
		var mi = obj1.getMinutes().toString();
		if(mi.length==1){
			mi="0"+mi;
		}
		var s = obj1.getSeconds().toString();
		if(s.length==1){
			s="0"+s;
		}
		var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
		$("#dqtime").html(time);
		setTimeout('time()', f);
	}
	//重点监控区域停留车辆数量图
	 // 使用
	 function clsl(obj){
        require(
            [
                'echarts',
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表          
				var myChart2 = ec.init(document.getElementById('main2')); 
           
				option2 = {
				grid:{
					x:30,y:10,x2:10,y2:30
				},
    title : {
        text: '',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['']
    },
    toolbox: {
        show : false,//隐藏右上角工具按钮
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true},
           
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.01]
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['1','2','3','6','12','24','48']
        }
    ],
    series : [
        {
            name:'车辆数量',
            type:'bar',
            data:[obj[6], obj[5], obj[4], obj[3], obj[2], obj[1],obj[0]]
        }
    ]
};
                    
				myChart2.setOption(option2); 
            }
        ); 
        }
        function findareanum(){
	$.ajax({
			url : 'findareanum.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var noline = json.areanum;
				clsl(noline);
				setTimeout('findareanum( )', f); 
			},
			error:function(){
				
			}		
	});
}
function findyy12(){
	$.ajax({
			url : 'yingyun12.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var cc = json.loadonline.yy12[0];
					var dd = json.loadonline.yy12[1];
					var ee = json.loadonline.yy12[2];
					var ff = json.loadonline.yy12[3];
					var gg = json.list1[0].pjyingyun;
					var hh = json.list1[0].shijian;
					
					var day=[];
					var yingyuns=[];
					var yingyunq=[];
					var yingyunz=[];
					var yingyunj=[];
					var yingyunp=[];
					var yingyuntime=[];
					for(var i=0;i<hh.length;i++){
							yingyuntime.push(hh[i]);
					}
					var date = new Date();
					var hour = date.getHours();
					for(var i=0;i<cc.length;i++){
							yingyuns.push(cc[i]);
					}
					for(var i=0;i<dd.length;i++){
							yingyunq.push(dd[i]);
					}
					for(var i=0;i<ee.length;i++){
							yingyunz.push(ee[i]);
					}
					for(var i=0;i<ff.length;i++){
							yingyunj.push(ff[i]);
					}
					for(var i=0;i<gg.length;i++){
						if(gg[i].length>2){
							yingyunp.push(gg[i].substr(0,2));
						}
					}
					yy12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime);
					setTimeout('findyy12( )', f); 
			},
			error:function(){
				
			}		
	});
}
	function yy12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime){
	var yyj=yingyunj;
	var yyz=yingyunz;
	var yyq=yingyunq;
	var yys=yingyuns;
		  // 使用
         require(
            [
                'echarts',
                'echarts/chart/line', // 
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main1'));
              option = {
              grid:{
					x:30,y:10,x2:10,y2:30
				},
    title : {
        text: '',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['今天','昨天','前天','上周同比','上周平均']
    },
    toolbox: {
        show : false,
       
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : yingyuntime
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'今天',
            type:'line',
            data:yyj,
        },
        {
            name:'昨天',
            type:'line',
            data:yyz,
        },
        {
            name:'前天',
            type:'line',
            data:yyq,
        },
        {
            name:'上周同比',
            type:'line',
            data:yys,
        },
        {
            name:'上周平均',
            type:'line',
            data:yingyunp,
        }
    ]
};
      
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
	}
	
	//最近1小時未營運
	
	function findwyy12(){
	$.ajax({
			url : 'wyyyingyun12.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var cc = json.loadonline.yy12[0];
					var dd = json.loadonline.yy12[1];
					var ee = json.loadonline.yy12[2];
					var ff = json.loadonline.yy12[3];
					var gg = json.list1[0].pjyingyun;
					var hh = json.list1[0].shijian;
					var day=[];
					var yingyuns=[];
					var yingyunq=[];
					var yingyunz=[];
					var yingyunj=[];
					var yingyunp=[];
					var yingyuntime=[];
					for(var i=0;i<hh.length;i++){
							yingyuntime.push(hh[i]);
					}
					var date = new Date();
					var hour = date.getHours();
					for(var i=0;i<cc.length;i++){
							yingyuns.push(cc[i]);
					}
					for(var i=0;i<dd.length;i++){
							yingyunq.push(dd[i]);
					}
					for(var i=0;i<ee.length;i++){
							yingyunz.push(ee[i]);
					}
					for(var i=0;i<ff.length;i++){
							yingyunj.push(ff[i]);
					}
					for(var i=0;i<gg.length;i++){
							yingyunp.push(gg[i]);
					}
					wyy12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime);
					setTimeout('findwyy12( )', f); 
			},
			error:function(){
				
			}		
	});
}
	function wyy12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime){
	var yyj=yingyunj;
	var yyz=yingyunz;
	var yyq=yingyunq;
	var yys=yingyuns;
		  // 使用
         require(
            [
                'echarts',
                'echarts/chart/line', // 
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('wyy')); 
			
              option = {
              grid:{
					x:40,y:10,x2:10,y2:30
				},
    title : {
        text: '',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['今天','昨天','前天','上周同比','上周平均']
    },
    toolbox: {
        show : false,
       
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : yingyuntime
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'今天',
            type:'line',
            data:yyj,
        },
        {
            name:'昨天',
            type:'line',
            data:yyz,
        },
        {
            name:'前天',
            type:'line',
            data:yyq,
        },
        {
            name:'上周同比',
            type:'line',
            data:yys,
        },
        {
            name:'上周平均',
            type:'line',
            data:yingyunp,
        }
    ]
};
      
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
	}
	
	
	//重點監控區域車輛數量
	
	function findclsl12(){
	$.ajax({
			url : 'clsl12.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var cc = json.loadonline.yy12[0];
					var dd = json.loadonline.yy12[1];
					var ee = json.loadonline.yy12[2];
					var ff = json.loadonline.yy12[3];
					var gg = json.cs[0].sj;
					var hh = json.cs[0].shijian;
					var day=[];
					var yingyuns=[];
					var yingyunq=[];
					var yingyunz=[];
					var yingyunj=[];
					var yingyunp=[];
					var yingyuntime=[];
					var date = new Date();
					var hour = date.getHours();
					for(var i=0;i<gg.length;i++){
							yingyunp.push(gg[i]);
					}
					for(var i=0;i<hh.length;i++){
							yingyuntime.push(hh[i]);
					}
					for(var i=0;i<cc.length;i++){
							yingyuns.push(cc[i]);
					}
					for(var i=0;i<dd.length;i++){
							yingyunq.push(dd[i]);
					}
					for(var i=0;i<ee.length;i++){
							yingyunz.push(ee[i]);
					}
					for(var i=0;i<ff.length;i++){
							yingyunj.push(ff[i]);
					}
					clsl12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime);
					setTimeout('findclsl12( )', f); 
			},
			error:function(){
				
			}		
	});
}
	function clsl12(yingyuns,yingyunq,yingyunz,yingyunj,yingyunp,yingyuntime){
	var yyj=yingyunj;
	var yyz=yingyunz;
	var yyq=yingyunq;
	var yys=yingyuns;
		  // 使用
         require(
            [
                'echarts',
                'echarts/chart/line', // 
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('zdl')); 
			
              option = {
              grid:{
					x:40,y:10,x2:10,y2:30
				},
    title : {
        text: '',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['今天','昨天','前天','上周同比','上周平均']
    },
    toolbox: {
        show : false,
       
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : yingyuntime
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'今天',
            type:'line',
            data:yyj,
        },
        {
            name:'昨天',
            type:'line',
            data:yyz,
        },
        {
            name:'前天',
            type:'line',
            data:yyq,
        },
        {
            name:'上周同比',
            type:'line',
            data:yys,
        }
        ,
        {
            name:'上周平均',
            type:'line',
            data:yingyunp,
        }
    ]
};
      
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
	}
	find();
	findthreetb();
	loadScript("js/plugin/flot/jquery.flot.cust.js", loadFlotResize);
	
	function loadFlotResize() {
		loadScript("js/plugin/flot/jquery.flot.resize.js", loadFlotFillbetween);
	}
	function loadFlotFillbetween() {
		loadScript("js/plugin/flot/jquery.flot.fillbetween.js", loadFlotOrderBar);
	}
	function loadFlotOrderBar() {
		loadScript("js/plugin/flot/jquery.flot.orderBar.js", loadFlotPie);
	}	
	function loadFlotPie() {
		loadScript("js/plugin/flot/jquery.flot.pie.js", loadFlotToolTip);
	}
	function loadFlotToolTip() {
		loadScript("js/plugin/flot/jquery.flot.tooltip.js", generateAllFlotCharts);
	}
	
                                                           
	function findthreetb(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'threetb.action',
			type : 'post',
			data:{
				"time" : $("#yingyuntime").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			//营运率
					//故障车辆
					var rr = json.r;
					var aa = json.cls;
					var bb = json.shuju;
					$("#gzcl").html("故障车辆总数"+rr.total+"辆");
					$("#xxcl").html("下线车辆总数"+(aa[0]+aa[1]+aa[2])+"辆");
					generateAllFlotCharts(rr,aa,bb);
					//loadDataTableScripts_yingyun();
					setTimeout('findthreetb()', f); 
			},
			error:function(){
				$.unblockUI();
			}		
		});
		$.unblockUI();
	}

	/* chart colors default */
	var $chrt_border_color = "#efefef";
	var $chrt_grid_color = "#DDD"
	var $chrt_main = "#E24913";			/* red       */
	var $chrt_second = "#6595b4";		/* blue      */
	var $chrt_third = "#FF9F01";		/* orange    */
	var $chrt_fourth = "#7e9d3a";		/* green     */
	var $chrt_fifth = "#BD362F";		/* dark red  */
	var $chrt_mono = "#000";
	
	function generateAllFlotCharts(rr,aa,bb) {
		
		
		/* end pie chart */
		
		/* site stats chart */
	

	
		if ($('#xxf').length) {

				var data_pie = [];
				var series = 4;
				data_pie[0] = {
						label : "0.5小时<下线时间<1天",
						data :aa[0]
					}
					data_pie[1] = {
						label : "1天<下线时间<3天",
						data : aa[1]
					}
					data_pie[2] = {
						label : "3天<下线时间",
						data : aa[2]
					}
					data_pie[3] = {
						label : "1小时<上线未营运 <3小时",
						data : bb[0]
					}
					data_pie[4] = {
						label : "3小时<上线未营运",
						data : bb[1]
					}
				$.plot($("#xxf"), data_pie, {
					series : {
						pie : {
							show : true,
							innerRadius : 0,
							radius : 1,
							label : {
								show : true,
								radius : 2 / 3,
								formatter : function(label, series) {
									return '<div style="font-size:16px;color:white;">'  + '<br/>' + Math.round(series.percent*((aa[0]+aa[1]+aa[2]+bb[0]+bb[1])/100)) + '</div>';
								}
								
							}
						}
					},
					legend : {
						show : true,
						noColumns : 1, // number of colums in legend table
						labelFormatter : function(label, series) {
									return  '<div style="width:80px;font-size:12px;text-align:center; ">' +  label + '</div>';
								}, // fn: string -> string
						labelBoxBorderColor : "#ffa", // border color for the little label boxes
						container : null, // container (as jQuery object) to put legend in, null means default on top of graph
						position : "ne", // position of default legend container within plot
						margin : [5, 10], // distance from grid edge to default legend container within plot
						backgroundColor : "#efefef", // null means auto-detect
						backgroundOpacity : 1 // set to 0 to avoid background
					},
					grid : {
						hoverable : true,
						clickable : true
					},
				});
	
		}
		
		if ($('#xxf1').length) {
			
				var data_pie = [];
				data_pie[0] = {
						label : "企业报停数量",
						data : rr.cpNum
					}
					data_pie[1] = {
						label : "设备故障数量",
						data : rr.efNum
					}
					data_pie[2] = {
						label : "SIM卡故障数量",
						data : rr.simNum
					}
					data_pie[3] = {
						label : "其他故障数量",
						data : rr.otherNum
					}
	
				$.plot($("#xxf1"), data_pie, {
					series : {
						pie : {
							show : true,
							innerRadius : 0,
							radius : 1,
							label : {
								show : true,
								radius : 2 / 3,
								formatter : function(label, series) {
									return '<div style="font-size:16px;text-align:center;padding:1px;color:white;">'  + '<br/>' + Math.round(series.percent*(rr.total/100)) + '</div>';
								}
							}
						}
					},
					legend : {
						show : true,
						noColumns : 1, // number of colums in legend table
						labelFormatter : function(label, series) {
									return  '<div style="width:80px;font-size:12px;text-align:center; ">' +  label + '</div>';
								}, // fn: string -> string
						labelBoxBorderColor : "#ffa", // border color for the little label boxes
						container : null, // container (as jQuery object) to put legend in, null means default on top of graph
						position : "ne", // position of default legend container within plot
						margin : [5, 10], // distance from grid edge to default legend container within plot
						backgroundColor : "#efefef", // null means auto-detect
						backgroundOpacity : 1 // set to 0 to avoid background
					},
					grid : {
						hoverable : true,
						clickable : true
					},
				});
	
		}
	}
	
	function find(){
	$.ajax({
			url : 'find1.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var oneyingyun = json.yingyun;
				var szyingyun=json.szyingyun;
				var szpjyingyun=json.szpjyingyun;
				var onezhouzhuan = json.zhouzhuan;
				var szzhouzhuan=json.szzhouzhuan;
				var szpjzhouzhuan=json.szpjzhouzhuan;
				var onejine = json.jine;
				var szjine=json.szjine;
				var szpjjine=json.szpjjine;
				var online=json.l;
				var clzzz = json.cls;
				$("#oneyingyun").html(oneyingyun);
				$("#szyingyun").html(szyingyun);
				$("#szpjyingyun").html(szpjyingyun);
				$("#onechuche").html(parseInt(oneyingyun/9612*100)+"%");
				$("#szchuche").html(parseInt(szyingyun/9612*100)+"%");
				$("#szpjchuche").html(parseInt(szpjyingyun/9612*100)+"%");
				$("#onezhouzhuan").html((onezhouzhuan/oneyingyun).toFixed(2));
				$("#szzhouzhuan").html((szzhouzhuan/szyingyun).toFixed(2));
				$("#szpjzhouzhuan").html(szpjzhouzhuan);
				$("#onejine").html((onejine/oneyingyun).toFixed(2));
				$("#szjine").html((szjine/szyingyun).toFixed(2));
				$("#szpjjine").html(szpjjine);
				$("#oneonline").html(online.online_rate);
				$("#szonline").html(online.area_online_rate);
				$("#szpjonline").html(online.load_rate);
				$("#oneonlines").html((parseInt(online.online_rate)/100*9612).toFixed(0));
				$("#szonlines").html((parseInt(online.area_online_rate)/100*9612).toFixed(0));
				$("#szpjonlines").html((parseInt(online.load_rate)/100*9612).toFixed(0));
				$("#home_cls").html(clzzz[1]);
				$("#home_zs").html(clzzz[0]);
				$("#home_wrw").html(clzzz[2]);
				setTimeout('find()', f); 
			},
			error:function(){
				
			}		
	});
}
	/* end flot charts */	
	
</script>