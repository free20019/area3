/**
 * @author sven.zhang
 * 	常规下线分析JS
 */

//获取时间
$(document).ready(function(){
	var mydate = new Date();
	var y=(mydate.getFullYear()).toString();
	var m=(mydate.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=mydate.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var time=y+"-"+m+"-"+d; 
	var time1=time+" 00:00:00"; 
	var time2=time+" 23:59:59"; 
	$("#endTimer").val(time2);
	$("#startTimer").val(time1);
});


//显示增加界面
$('#add-group').unbind("click").click(function(){	
	//document.getElementById("groupadd").options.length=0;
	$('#addgroup').dialog('open');
});
//显示增加界面的方法
$('#addgroup').dialog({
	autoOpen : false,
	width : 300,
	resizable : false,
	modal : true,
	buttons : [{
			html : "<i class='fa fa-times'></i>&nbsp; 取消",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");

			}
		}, {

			html : "<i class='fa fa-plus'></i>&nbsp; 增加",
			"class" : "btn btn-danger",
			click : function() {
				var flag = addOne();
				if(flag){
					$(this).dialog("close");
					 $('#efNum').val("");
					 $('#sim').val("");
					 $('#cpNum').val("");
					 $('#other').val("");
				}
			}
		}]
});
function addOne(){
	var efNum = $('#efNum').val();
	var simNum = $('#sim').val();
	var cpNum = $('#cpNum').val();
	var other = $('#other').val();
	 if(cpNum =="" || isNaN(cpNum)){
		alert("企业报停数为数字");
	}else if( efNum =="" || isNaN(efNum) ){
		alert("设备故障数为数字");
	}else if(simNum =="" || isNaN(simNum) ){
		alert("SIM卡故障数为数字");
	}else if( other =="" || isNaN(other)){
		alert("其他数量为数字");
	}else{
		$.ajax({
			url:"addRegular.action",
			data:{
				"re.efNum":parseInt(efNum),
				"re.simNum":parseInt(simNum),
				"re.cpNum":parseInt(cpNum),
				"re.otherNum":parseInt(other)
			},
			datatype:"JSON",
			type:"POST",
			async:true,
			success:function(data){
				var businesslist = data.list;
				getList(businesslist);			
				
			}
		
		});
		 return true;
	}
}

function getList(businesslist){
	var tab ="";
	if(businesslist != null){
		for(var i=0;i<businesslist.length;i++){
			tab=tab+"<tr ><td><input type='radio' name='onesel' value='"+businesslist[i].regularId+"' /></td>";
			tab=tab+"<td nowrap='nowrap'>"+(i+1)+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].cpNum+"</td>";	
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].efNum+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].simNum+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].otherNum+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].total+"</td>";
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].operatingTime+"</td>";								
			tab=tab+"<td nowrap='nowrap'>"+businesslist[i].operatingUser+"</td>";
			tab=tab+"</tr>";
		}
	}
		$('#tbs').html(tab);
	
}
//载入所有数据
$(document).ready(function(){
	$.ajax({
		url:"addRegular.action",		
		datatype:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var businesslist = data.list;
			getList(businesslist);	
		}
	});
});
//查询
$('#regular-search').unbind('click').click(function(){
	var startTime = $('#startTimer').val();
	var endTime = $('#endTimer').val();
	$.ajax({
		url:"regularSearch.action",	
		data:{
			"con.startTime":startTime,
			"con.endTime":endTime,
		},
		datatype:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var businesslist = data.list;
			getList(businesslist);	
		}
	});
});
//修改
$('#update-group').unbind("click").click(function(){	

	var sel = $('input[name="onesel"]:checked').val();
	if(sel != null){
		$.ajax({
			url:"selone.action",
			data:{
				"re.regularId":sel,
			},
			datatype:"JSON",
			type:"POST",
			async:true,
			success:function(data){
				$('#efNums').val(data.re.efNum);
				$('#sims').val(data.re.simNum);
				$('#cpNums').val(data.re.cpNum);
				$('#others').val(data.re.otherNum);
			}
		});
		$('#editgroup').dialog('open');
		
	}else{
		alert("请选择一项");
	}
	
});
$('#editgroup').dialog({
	autoOpen : false,
	width : 300,
	resizable : false,
	modal : true,
	buttons : [{
			html : "<i class='fa fa-times'></i>&nbsp; 取消",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");

			}
		}, {

			html : "<i class='fa fa-plus'></i>&nbsp; 修改",
			"class" : "btn btn-danger",
			click : function() {
				var flag = updateOne();
				if(flag){
					$(this).dialog("close");
					 $('#efNums').val("");
					 $('#sims').val("");
					 $('#cpNums').val("");
					 $('#others').val("");
				}
			}
		}]
});
function updateOne(){
	var sel = $('input[name="onesel"]:checked').val();
	var efNum = $('#efNums').val();
	var simNum = $('#sims').val();
	var cpNum = $('#cpNums').val();
	var other = $('#others').val();
	 if(cpNum =="" || isNaN(cpNum)){
			alert("企业报停数为数字");
		}else if( efNum =="" || isNaN(efNum) ){
			alert("设备故障数为数字");
		}else if(simNum =="" || isNaN(simNum) ){
			alert("SIM卡故障数为数字");
		}else if( other =="" || isNaN(other)){
			alert("其他数量为数字");
		}else{
		$.ajax({
			url:"updateOnes.action",
			data:{
				"re.regularId":sel,
				"re.efNum":parseInt(efNum),
				"re.simNum":parseInt(simNum),
				"re.cpNum":parseInt(cpNum),
				"re.otherNum":parseInt(other)
			},
			datatype:"JSON",
			type:"POST",
			async:true,
			success:function(data){
				var businesslist = data.list;
				getList(businesslist);
			}
		
		});
		return true;
	}
}
//删除
$('#del-one').unbind("click").click(function(){	
	var sel = $('input[name="onesel"]:checked').val();
	if(sel != null){
		var flag = window.confirm("确定删除？");
		if(flag){
			$.ajax({
				url:"delone.action",
				data:{
					"re.regularId":sel,
				},
				datatype:"JSON",
				type:"POST",
				async:true,
				success:function(data){
					var businesslist = data.list;
					getList(businesslist);	
				}
			});
		}
	}else{
		alert("请选择一项删除");
	}
	
});