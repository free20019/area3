/**
 * 群组管理相关JS
 */
//获取群组
getGroup();
function getGroup(){
	//加载时获取群组
$(document).ready(function(){
	$.ajax({
		url:"findGroup.action",
	dataType:"JSON",
	type:"POST",
	async:true,
	success:function(data){	
		$('#groupf').empty();
		$("#groupf").append("<option value='0' >--选择群组--</option>");
		var list = data.list;
			for(var i=0;i<list.length;i++){
				$('#groupf').append("<option value='"+list[i].groupId+"'>"+list[i].groupName+"</option>");			
		}
		
	}
	});
});
//加载时获取公司
$(document).ready(function(){
	$.ajax({
		url:"findBranch.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.list;
			$("#branchf").empty();			
				$("#branchf").append("<option value='0' selected ='selected' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branchf").append("<option value='"+list[i].branch+"'>"+list[i].branch+"</option>");				
				}
		}
	});
});
//加载时获取车号 
$(document).ready(function(){	
	$.ajax({
		url:"findVhic.action",		
		dataType:"JSON",
		type:"POST",
		async:false,
		success:function(data){
			var list = data.list;
			$("#cardNof").empty();
			$("#cardNof").append("<option value='0' >--选择车号--</option>");
			for(var i=0;i<list.length;i++){
				$("#cardNof").append("<option value='"+list[i].vehicle+"'>"+list[i].vehicle+"</option>");
			}
			getSelects();
		}
	});

});
}
//select事件触发
$('#groupf').change(function(){
	var gs = $('#groupf').val();
	getBranchs(gs);
	getCard();
	getSelects();

});
//获取分公司
function getBranchs(gs){
	$.ajax({
		url:"findBranch.action",
		data:{
			"gm.groupId":gs
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.list;
			$("#branchf").empty();			
				$("#branchf").append("<option value='0' selected ='selected' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branchf").append("<option value='"+list[i].branch+"'>"+list[i].branch+"</option>");				
				}
		}
	});
}
//select分公司事件触发
$('#branchf').change(function(){
	getCard();
	getSelects();

});
//获取车号
function getCard(){	
	var gs = $('#groupf').val();
	var branch = $('#branchf').val();
	$.ajax({
		url:"findVhic.action",
		data:{
			"gm.groupId":gs,
			"gm.branch":branch
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.list;
			$("#cardNof").empty();			
			$("#cardNof").append("<option value='0' >--选择车号--</option>");
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#cardNof").append("<option value='"+list[i].vehicle+"'>"+list[i].vehicle+"</option>");
				}
				
			}
		}
	});

}
//文本框筛选车号
var arr ;
function getSelects(){
	 arr = new Array(); 
	$("#cardNof option").each(function () {
        var val = $(this).val(); //获取单个value
        arr.push(val);
    });
}

$('#testf').blur(function(){
	$("#cardNof").empty();   
	for(var i=0;i<arr.length;i++){
		if(arr[i].indexOf($("#testf").val().toUpperCase())>=0){
			if(arr[i]=="0"){
				$("#cardNof").append("<option value='0' >--选择车辆--</option>");
			}else{
				$("#cardNof").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
			}
		}	
	}
});
//根据查询条件查询
$('#searchf').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});

	var cardNo = $('#cardNof').val();
	var group = $('#groupf').val();
	var branch = $('#branchf').val();
	var url = $('#searchf').data("url");
		$.ajax({
				url:url,			
				data:{
					 "gm.vehicle":cardNo,
					 "gm.branch":branch,
					 "gm.groupId":group
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){				
					var businesslist = data.list;
					var tab="";
					for(var i =0;i<businesslist.length;i++){		
						tab=tab+"<tr ><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].groupName+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vehicle+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].color+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].terminalType+"</td>";
						tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vehicleType+"</td>";
						tab=tab+"</tr>";
					}
					$('#tbf').html(tab);
					$('#vehi').show();
					$.unblockUI();
				}
		});
	
});
/**********************************************/
//显示增加界面
$('#add-groupf').unbind("click").click(function(){	
	$.ajax({
		url:"findAllCompany.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.cList;
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#group-add").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
				
			}
		}
	});
	$('#addgroupf').dialog('open');
});
//显示增加界面的方法
$('#addgroupf').dialog({
	autoOpen : false,
	width : 900,
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
				addOne();
				$(this).dialog("close");
				
			}
		}]
});
//增加群组
function addOne(){
	var list ="";
	var groupName = $('#vhicgroupadd').val();
	$('#sel-add option').each(function(){
		list+=$(this).val()+",";
	});
	$.ajax({
		url:"addOne.action",
		data:{
			"gm.groupName":groupName,
			"gm.vList":list
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(){
				$('#vhicgroupadd').val("");
				$('#sel-add').empty();
				$('#vehicle-add').empty();
				$('#comp-add').empty();
				getGroup();
				
			
		}
	});
}
//查询全部公司
$(document).ready(function(){
	$.ajax({
		url:"getInfo.action",
		type:"POST",
		datatype:"JSON",
		success:function(data){
			var list = data.compList;
			for(var i=0;i<list.length;i++){
				$('#group-add').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				$('#group-edit').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
			}
		}
	});
});
//获取分公司
$('#group-add').change(function(){
	var comp = $('#group-add option:selected').text();
	addBranch(comp);
	addAllCard();
});
function addBranch(comp){
	$.ajax({
		url:"getBranch.action",
		data:{
			"con.company":comp,
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.baList;				
			$("#comp-add").empty();	
			for(var i=0;i<list.length;i++){
				$("#comp-add").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
			}
		}
	});
}
//获取所有车号
$('#comp-add').change(function(){
	addAllCard();

});
//获取所有车号的方法
function addAllCard(){	
	var comp = $('#group-add option:selected').text();
	var branch = $('#comp-add option:selected').text();
	
	$.ajax({
		url:"getCard.action",
		data:{
			"con.company":comp,
			"con.branch":branch
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.cardList;
			card =list;
			getArray(list);
		}
	});

}
//将数据存入数组
function getArray(list){
	var str = new Array();
	var str2 = new Array();
	$('#sel-add option').each(function(){
		str.push($(this).val());
	});
	
	for(var i =0;i<list.length;i++){
		var flag = true;
		for(var j =0;j<str.length;j++){
			if(list[i]== str[j])
				flag = false;
		}
		if(flag)
			str2.push(list[i]);
	}
	$("#vehicle-add").empty();	
		for(var i=0;i<str2.length;i++){
			$("#vehicle-add").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
		}
		return str2;
}
//添加所有车
$('#addall').unbind('click').click(function(){
	var lists = getArray(card);
	for(var i =0;i< lists.length;i++){
		$("#sel-add").append("<option value='"+lists[i]+"'>"+lists[i]+"</option>");
	}
		$("#vehicle-add").empty();
	
});
//添加一辆车
$('#addone').unbind('click').click(function(){
	var vehicle = $("#vehicle-add option:selected").text();
	if(vehicle !=""){
		$("#sel-add").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		 var i = $("#vehicle-add").get(0).selectedIndex;
			$("#vehicle-add").get(0).remove(i);
	}else{
		alert("请选择要添加的车号");
	}
	
});
//删除一辆车
$('#delone').unbind('click').click(function(){
	var i = $("#sel-add").get(0).selectedIndex;
	var vehicle = $("#sel-add").val();
	if(i != -1){
		$("#sel-add").get(0).remove(i);
		$("#vehicle-add").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
	}else{
		alert("请选择要移除的车号");
	}
});
//删除所有车
$('#delall').unbind('click').click(function(){
	$("#sel-add").empty();
	addAllCard();
});
/****************************************************************************/
//修改
$('#update-groupf').unbind("click").click(function(){	

	var sel =$('#groupf').val();
	var se = $('#groupf option:selected').text();
	if(sel != 0){
		$.ajax({
			url:"findVhic.action",
			data:{
				"gm.groupId":sel,
			},
			dataType:"JSON",
			type:"POST",
			async:true,
			success:function(data){
				var list = data.list;
				$('#vhicgroupedit').val(se);
				$("#sel-edit").empty();			
				if(list != null){
					for(var i=0;i<list.length;i++){
						$("#sel-edit").append("<option value='"+list[i].vehicle+"'>"+list[i].vehicle+"</option>");
					}
					
				}
			}
		});
		$('#editgroupf').dialog('open');
		
	}else{
		alert("请选择一项");
	}
	
});
//显示修改界面
$('#editgroupf').dialog({
	autoOpen : false,
	width : 900,
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
				updateOne();
				$(this).dialog("close");
			}
		}]
});
$('#group-edit').change(function(){
	var comp = $('#group-edit option:selected').text();
	editBranch(comp);
	editCard();
});
//获取分公司
function editBranch(comp){
	$.ajax({
		url:"getBranch.action",
		data:{
			"con.company":comp,
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.baList;	
			
			$("#comp-edit").empty();	
			for(var i=0;i<list.length;i++){
				$("#comp-edit").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
			}
		}
	});
}
//获取所有车号
$('#comp-edit').change(function(){
	editCard();

});
//获取车号
function editCard(){	
	var comp = $('#group-edit option:selected').text();
	var branch = $('#comp-edit option:selected').text();	
	$.ajax({
		url:"getCard.action",
		data:{
			"con.company":comp,
			"con.branch":branch
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.cardList;
			cards =list;
			getArrays(list);
		}
	});

}
//将数据存入数组
function getArrays(list){
	var str = new Array();
	var str2 = new Array();
	$('#sel-edit option').each(function(){
		str.push($(this).val());
	});
	
	for(var i =0;i<list.length;i++){
		var flag = true;
		for(var j =0;j<str.length;j++){
			if(list[i]== str[j])
				flag = false;
		}
		if(flag)
			str2.push(list[i]);
	}
	$("#vehicle-edit").empty();	
		for(var i=0;i<str2.length;i++){
			$("#vehicle-edit").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
		}
		return str2;
}
//添加所有车
$('#addalls').unbind('click').click(function(){
	var lists = getArrays(cards);
	for(var i =0;i< lists.length;i++){
		$("#sel-edit").append("<option value='"+lists[i]+"'>"+lists[i]+"</option>");
	}
		$("#vehicle-edit").empty();
	
});
//添加一辆车
$('#addones').unbind('click').click(function(){
	var vehicle = $("#vehicle-edit option:selected").text();
	if(vehicle !=""){
		$("#sel-edit").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		 var i = $("#vehicle-edit").get(0).selectedIndex;
			$("#vehicle-edit").get(0).remove(i);
	}else{
		alert("请选择要添加的车号");
	}
	
});
//删除一辆车
$('#delones').unbind('click').click(function(){
	var i = $("#sel-edit").get(0).selectedIndex;
	var vehicle = $("#sel-edit").val();
	if(i != -1){
		$("#sel-edit").get(0).remove(i);
		$("#vehicle-edit").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
	}else{
		alert("请选择要移除的车号");
	}
});
//删除所有车
$('#delalls').unbind('click').click(function(){
	$("#sel-edit").empty();
	editCard();
});
//更新群组
function updateOne(){
	var list ="";
	var groupName = $('#vhicgroupedit').val();
	var groupId = $('#groupf').val();
	$('#sel-edit option').each(function(){
		list+=$(this).val()+",";
	});
	$.ajax({
		url:"updateOne.action",
		data:{
			"gm.groupName":groupName,
			"gm.vList":list,
			"gm.groupId":groupId
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			if(data.flag){
				$('#vhicgroupedit').val("");
				$('#sel-edit').empty();
				$('#vehicle-edit').empty();
				$('#comp-edit').empty();
				getGroup();
				alert("修改成功");
			}else{
				alert("修改失败");
			}
		
		}
	});
}
//删除群组
$('#delete-groupf').unbind('click').click(function(){
	var groupId = $('#groupf').val();
	if(groupId !="0"){
		var flag = window.confirm("确认删除？");
		if(flag){
			$.ajax({
				url:"deleteOne.action",
				data:{
					"gm.groupId":groupId
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){
					if(data.flag){
						getGroup();
						alert("删除成功");
					}else{
						alert("删除失败");
					}
				
				}
			});
		}
	}else{
		alert("请选择要删除的组");
	}
});
//excel文件上传
function ajaxFileUpload(){
    $("#loading")
    .ajaxStart(function(){
        $(this).show();
    })//开始上传文件时显示一个图片
    .ajaxComplete(function(){
        $(this).hide();
    });//文件上传完成将图片隐藏起来
    
    $.ajaxFileUpload
    (
        {
            url:'fileAction.action',//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
            fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            success: function (data){
                var lists =data.list;
            	for(var i =0;i< lists.length;i++){
            		alert(lists[i]);
            		$("#sel-add").append("<option value='"+lists[i]+"'>"+lists[i]+"</option>");
            	}
            },
           
        }
    )
}

function f_DL(){
	$.ajax({
	url : 'daochu.action',
	type : 'post',
	data:{
	"quyu" : $("#quyu").val()
	},
	dataType: 'json',
	timeout : 180000,
	success:function(json){
		if(json.fanhuei=='成功导成Excel!'){
			var tab=json.fanhuei+" 文件:"+json.xlsfilename+"&nbsp;<a href='"+json.action+"'>下载</a>";
			$("#daochu").html(tab);
		}else{
			var tab=json.fanhuei;
			$("#daochu").html(tab);
		}
	},
	error:function(){
		
	}
	});
}                                              




