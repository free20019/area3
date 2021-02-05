$(document).ready(function(){
//	$('#vehi').hide();
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
	$("#mdtetime").val(time2);
	$("#mdtstime").val(time1);
});

$(document).ready(function(){
		$.ajax({
			url:"findmdtno.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			$('#mdtnotype').empty();
			$("#mdtnotype").append("<option value='0' >--选择终端类型--</option>");
			var list = data.condition;
			var tab="";
			var vehicle=data.vehicle;
				for(var i=0;i<list.length;i++){
					$('#mdtnotype').append("<option value='"+list[i].mdtno+"' onclick='findmdtvhic(this.value)'>"+list[i].mdtno+"</option>");
				}
				for ( var i = 0; i < vehicle.length; i++) {
					tab+="<option value='"+vehicle[i].vehino+"'></option>";
				}
				$("#mdtvhicno").html(tab);
		}
		});
});
function findmdtvhic(obj){
	$.ajax({
		url:"findVehicle.action",
	dataType:"JSON",
	data : {
		mdtno : obj
	},
	type:"POST",
	async:true,
	success:function(data){
		$('#mdtvhicno').empty();
		var tab="";
		var vehicle=data.vehicle;
			for ( var i = 0; i < vehicle.length; i++) {
				tab+="<option value='"+vehicle[i].vehino+"'></option>";
			}
			$("#mdtvhicno").html(tab);
	}
	});
}

$('#findmdtv').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#mdtstime').val();
	var endTime = $('#mdtetime').val();
	var certNo = $('#mdtvhic').val();
	var mdtno = $('#mdtnotype option:selected').val();
		var cur= 1;
		$.ajax({
				url:"findmdtdata.action",			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.certNo":certNo,
					"con.mdtno":mdtno,
					"page.currentPage":cur
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){				
					var businesslist = data.list;
					var p = data.page;	
					num =p.pageCount;
					pageCount=parseInt((num-1)/p.pageSize+1);
					getList2(businesslist,p);
					$('#num10').html(num);
					$('#pageCount10').html(pageCount);
					$('#cert').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList2(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){
					
					var empty = businesslist[i].empty;
					var distance = businesslist[i].distance;
					var total = (empty+distance).toFixed(2);	
					var percent =((distance/total)*100).toFixed(2)+"%"
					tab=tab+"<tr><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].company+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";	
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].times+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+total+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+percent+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].timeOut/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].waitTime/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"</tr>";
				}						
		}
		$('#mdttbody').html(tab);
		$('#currentPage10').html(page);
	}
// 获取末页
function getEndPage10(pageCount){
	getCurrentPage10(pageCount);
}
// 获取跳转页码
function getOnePage10(){
	var onePage = $('#jumpPage10').val();
	getCurrentPage10(onePage);
}

function getCurrentPage10(cp){
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:"mdtpage.action",			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList2(businesslist,p);
		}
	});
}


function mdtnoexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		var startTime = $('#mdtstime').val();
		var endTime = $('#mdtetime').val();
		var certNo = $('#mdtvhic').val();
		var mdtno = $('#mdtnotype option:selected').val();
		$.ajax({
		url : 'mdtnoexcle.action',
		type : 'post',
		data:{
			"con.startTime":startTime,
			"con.endTime":endTime,
			"con.certNo":certNo,
			"con.mdtno":mdtno,
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