<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- widget grid -->
<div class="row" style="height: 100%;overflow: auto;">
	<!-- row -->
<table border="1" style="width: 101%;height: 100%;overflow: auto;">
	<tr style="height: 150px;overflow: auto;">
		<td style="vertical-align: top">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon" > <i class="fa fa-edit"></i> </span>
					<h2 id="sdqxbb">单车速度曲线及里程统计</h2>

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
							
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate">
								<section class="col col-5" style="width: 140px;float:left;" >
										<label class="select">
											<select  id="sdareaselect" onchange='findcomp(this.value);'>
											<option value="0" >--选择公司--</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 140px;float:left;">
										<label class="select">
											<select id="sdcompselect" class="select" onchange='findvehi(this.value);'>
												<option value="0" selected=""  disabled="">--选择分公司--</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 140px;float:left;">
										<label class="select">
											<select id="sdvehiselect" class="select">
												<option value="0" selected=""  disabled="">--选择车辆--</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 80px;float:left;" >
									<input type="text" style="width: 80px;height: 28px;color:gray" id="test7" placeholder="请输入车号" onkeyup="queryspdcar(this.value)"/>
									</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="speed_stime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--开始时间--"></input>
										</label>
									</section>
									<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="speed_etime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--结束时间--"></input>
										</label>
									</section>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="querySpeed();">查   询</a>
								<a href="javascript:void(0);" class="btn btn-primary" onclick="exportSpeed();">导   出</a><span id="speeddaochu"></span>
								<section style="width:110px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="nolinelc"></span>
										</label>
									</section>
								</form>

						</div>

						<!-- gps数据 -->
		
		<!-- NEW WIDGET START -->
						<div id="non-date-graph" class="chart no-padding" style="border: 1px"></div>
				<table id="dt_speed" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								<th>序号</th>
									<th>车牌号</th>
									<th>经度</th>
									<th>纬度</th>
									<th>速度（km/h）</th>
									<th>方向</th>
									<th>时间</th>
									<th>地址</th>
								</tr>
							</thead>
							<tbody id="speeddata">
							</tbody>
						</table>	

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
		</td>
	</tr>
</table>
</div>

<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	findba();
	// PAGE RELATED SCRIPTS
loadScript("js/plugin/morris/raphael.2.1.0.min.js", loadMorrisEngine);
	// Load morris dependency 2
	function loadMorrisEngine() {
		loadScript("js/plugin/morris/morris.min.js", runMorrisCharts);
	}
	
	/*
	 * Run all morris chart on this page
	 */
	function runMorrisCharts(){
	}
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*1);
		$("#speed_stime").val(setformatnewlc(smydate));
		$("#speed_etime").val(setformatnewlc(mydate));
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

	var zLC=0;
	function querySpeed(){
		if($("#carspeed").val()=="0"){
			alert("请选择一辆车辆");
		}else if($("#speed_stime").val()==""){
			alert("开始时间不能为空");
		}else if($("#speed_etime").val()==""){
			alert("结束时间不能为空");
		}else{
			$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
			$.ajax({
				url : 'speed.action',
				type : 'post',
				data:{
					"vehi_no" : $("#sdvehiselect").val(),
					"stime" : $("#speed_stime").val(),
					"etime" : $("#speed_etime").val(),
					"ba_name" : $("#sdareaselect").val(),
					"comp_name" : $("#sdcompselect").val()
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					$.unblockUI();
						var vehispeed = json.list3;
<%--						var lc=json.dc;--%>
						var quxian="";
						if(vehispeed.length==0){
							alert("该车辆在该时段 没有数据");
						}else{
							var day_data = [];
							for(var i=0;i<vehispeed.length;i++){
							//	quxian+='{"elapsed":"'+vehispeed[i].speedtime+'","value":'+vehispeed[i].speed+'},';		
							var a=eval("({'elapsed':'"+vehispeed[i].speedtime+"','value':'"+vehispeed[i].speed+"'})");
							day_data.push(a);
							}
							$('#non-date-graph').html("");
							if ($('#non-date-graph').length){ 
								Morris.Line({
								  element: 'non-date-graph',
								  data: day_data,
								  xkey: 'elapsed',
								  ykeys: ['value'],
								  labels: ['速度'],
								  lineWidth:1,
								  pointSize:0,
								  parseTime: false
								});
							}
							addspeedtable(vehispeed);
						}
				},
				error:function(){
				}		
			});
		}
		
	}
	function addspeedtable(obj){
		var trdata="";
		for(var i=0;i<obj.length;i++){
				trdata +="<tr><td>"+(i+1)+"</td><td>"+obj[i].vehinum+"</td><td>"+obj[i].longi+"</td><td>"+obj[i].lati+"</td><td>"+obj[i].speed+"</td>";
				trdata +="<td>"+obj[i].direction1+"</td>";
				trdata +="<td>"+obj[i].speedtime+"</td><td>"+obj[i].address+"</td></tr>";
				if(i!=0){
						var l1=new AMap.LngLat(obj[i].longi,obj[i].lati);
						var l2=new AMap.LngLat(obj[i-1].longi,obj[i-1].lati);
						zLC += l1.distance(l2);
				}
		}
		$("#nolinelc").html("总里程:"+parseFloat((zLC/1000).toFixed(2))+"公里");
		$("#speeddata").html(trdata);
	}

	function exportSpeed(){
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
			$.ajax({
			url : 'speedexcle.action',
			type : 'post',
			data:{
				"vehi_no" : $("#sdvehiselect").val(),
					"stime" : $("#speed_stime").val(),
					"etime" : $("#speed_etime").val(),
					"ba_name" : $("#sdareaselect").val(),
					"comp_name" : $("#sdcompselect").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				window.location.href=json.action;
				$.unblockUI();
			},
			error:function(){
				
			}
			});
		}
	}
	function findba(){
		$.ajax({
			url : 'findba.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list1;
					for(var i=0;i<area.length;i++){
						$("#sdareaselect").append("<option value='"+area[i].ba_id+"'>"+area[i].ba_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findcomp(obj){
		$.ajax({
			url : 'findcompgroup.action',
			type : 'post',
			data:{
				'ba_id': obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#sdcompselect").empty();
			$("#sdvehiselect").empty();
					var area=json.list;
					var comp=json.list1;
					for(var i=0;i<area.length;i++){
						$("#sdcompselect").append("<option value='"+area[i].comp_id+"' >"+area[i].comp_name+"</option>");
					}
					for(var i=0;i<comp.length;i++){
						$("#sdvehiselect").append("<option value='"+comp[i].vehi_no+"'>"+comp[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findvehi(obj){
		$.ajax({
			url : 'findvehigroup.action',
			type : 'post',
			data:{
				"comp_id": obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#sdvehiselect").empty();
					var area=json.list;
					for(var i=0;i<area.length;i++){
						$("#sdvehiselect").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	
	//筛选
	/*
	var arr1;
	function getSelect7(){
	 arr1 = new Array(); 
	$("#sdvehiselect option").each(function () {
        var val = $(this).val(); //获取单个value
        arr1.push(val);
    });
}
	$('#test7').blur(function(){
	
	$("#sdvehiselect").empty();   
	for(var i=0;i<arr1.length;i++){
		if(arr1[i].indexOf($("#test7").val().toUpperCase())>=0){
			if(arr1[i]=="0"){
				$("#sdvehiselect").append("<option value='0' >--选择车号--</option>");
			}else{
				$("#sdvehiselect").append("<option value='"+arr1[i]+"' >"+arr1[i]+"</option>");
			}
		}	
	}
});
	*/
	$(document).ready(function(){
		/*
		$.ajax({
			url:"getGroupCard.action",		
			dataType:"JSON",
			type:"POST",
			async:false,
			success:function(data){
				var list = data.gcardList;
				$("#sdvehiselect").empty();
				$("#sdvehiselect").append("<option value='0' >--选择车号--</option>");
				for(var i=0;i<list.length;i++){
					$("#sdvehiselect").append("<option value='"+list[i].groupVhic+"'>"+list[i].groupVhic+"</option>");
				}
				getSelect7();
			}
		});
		*/
	
});


function queryspdcar(obj){
	if(obj.length>2||obj==""){

	
	$.ajax({
		url : 'findspdvehis.action',
		type : 'post',
		data:{
			"ba_id": $("#sdareaselect").val(),
			"comp_id": $("#sdcompselect").val(),
			"info" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				$("#sdvehiselect").empty();
				var area=json.list;
				for(var i=0;i<area.length;i++){
					$("#sdvehiselect").append("<option value='"+area[i].vehi_no+"'>"+area[i].vehi_no+"</option>");
				}
		},
		error:function(){
			
		}		
	});
	}
}
	
</script>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">