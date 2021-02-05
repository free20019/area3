//vehicle.jsp
$('#search').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var cardNo = $('#cardNo').val();
	var company = $('#company option:selected').text();
	var branch = $('#branch option:selected').text();
	var url = $('#search').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":cardNo,
					"con.company":company,
					"con.branch":branch,
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
					getList(businesslist,p);
					$('#num').html(num);
					$('#pageCount').html(pageCount);
					$('#vehi').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){				
					var empty = businesslist[i].empty;
					var distance = businesslist[i].distance;
					var total = (empty+distance).toFixed(2);	
					var percent =((distance/total)*100).toFixed(2)+"%"
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].company+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";	
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";
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
		$('#tb').html(tab);
		$('#currentPage').html(page);
	}
// 获取末页
function getEndPage(pageCount){
	getCurrentPage(pageCount);
}
// 获取跳转页码
function getOnePage(){
	var onePage = $('#jumpPage').val();
	getCurrentPage(onePage);
}
//获取当前页
function getCurrentPage(cp){
	var url=$('.getData').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList(businesslist,p);
		}
	});
}
//company.jsp
$('#search1').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime1').val();
	var endTime = $('#endTime1').val();
	var company = $('#company1 option:selected').text();
	var branch = $('#branch1 option:selected').text();
	var url = $('#search1').data("url");
	var cur= 1;
	$.ajax({
			url:url,			
			data:{
				"con.startTime":startTime,
				"con.endTime":endTime,
				"con.company":company,
				"con.branch":branch,
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
				getList1(businesslist,p);
				$('#num1').html(num);
				$('#pageCount1').html(pageCount);
				$('#com').show();
				$.unblockUI();
			}
	});
});
// 添加到页面
	function getList1(businesslist,p){
		page= p.currentPage;
		var tab="";		
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){
					var total = businesslist[i].total;
					var driving = businesslist[i].driving;	
					var percent =((driving/total)*100).toFixed(2)+"%"
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].company+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+total+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].driving+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+percent+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].times+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].totalDis+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].ypercent+"</td>";	
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].timeOut/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].waitTime/3600*1.0).toFixed(2)+"</td>";
				
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb1').html(tab);
		$('#currentPage1').html(page);
	}
	
	// 获取末页
	function getEndPage1(pageCount){
		getCurrentPage1(pageCount);
	}
	// 获取跳转页码
	function getOnePage1(){
		var onePage = $('#jumpPage1').val();
		getCurrentPage1(onePage);
	}
function getCurrentPage1(cp){
	var url=$('.getData1').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList1(businesslist,p);
		}
	});
}
/*****************************************************************/
//certno.jsp

$('#search2').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime2').val();
	var endTime = $('#endTime2').val();
	var certNo = $('#certNo').val();
	var company = $('#company2 option:selected').text();
	var branch = $('#branch2 option:selected').text();
	var url = $('#search2').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.certNo":certNo,
					"con.company":company,
					"con.branch":branch,
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
					$('#num2').html(num);
					$('#pageCount2').html(pageCount);
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
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].company+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";	
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].certNo+"</td>";
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
		$('#tb2').html(tab);
		$('#currentPage2').html(page);
	}
// 获取末页
function getEndPage2(pageCount){
	getCurrentPage2(pageCount);
}
// 获取跳转页码
function getOnePage2(){
	var onePage = $('#jumpPage2').val();
	getCurrentPage2(onePage);
}

function getCurrentPage2(cp){
	var url=$('.getData2').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
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
//group.jsp
$('#search4').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime4').val();
	var endTime = $('#endTime4').val();
	var group = $('#group').val();
	var url = $('#search4').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.group":group,
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
					getList4(businesslist,p);
					$('#num4').html(num);
					$('#pageCount4').html(pageCount);
					$('#gro').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList4(businesslist,p){
	
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){					
					tab=tab+"<tr><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].group+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].total+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].driving+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vpercent+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].times+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].totalDis+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].ypercent+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].timeOut/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].waitTime/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb4').html(tab);
		$('#currentPage4').html(page);
	}
// 获取末页
function getEndPage4(pageCount){
	getCurrentPage4(pageCount);
}
// 获取跳转页码
function getOnePage4(){
	var onePage = $('#jumpPage4').val();
	getCurrentPage4(onePage);
}

function getCurrentPage4(cp){
	var url=$('.getData4').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList4(businesslist,p);
		}
	});
}
//business.jsp
$('#search3').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime3').val();
	var endTime = $('#endTime3').val();
	var cardNo = $('#cardNo3').val();
	var company = $('#company3 option:selected').text();
	var branch = $('#branch3 option:selected').text();
	var url = $('#search3').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":cardNo,
					"con.company":company,
					"con.branch":branch,
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
					getList3(businesslist,p);
					$('#num3').html(num);
					$('#pageCount3').html(pageCount);
					$('#bus').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList3(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){	
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].certNo+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].upTaxi.substr(0,businesslist[i].upTaxi.length-2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].downTaxi.substr(0,businesslist[i].downTaxi.length-2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";					
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].waitTime+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].type+"</td>";
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb3').html(tab);
		$('#currentPage3').html(page);
	}
// 获取末页
function getEndPage3(pageCount){
	getCurrentPage3(pageCount);
}
// 获取跳转页码
function getOnePage3(){
	var onePage = $('#jumpPage3').val();
	getCurrentPage3(onePage);
}

function getCurrentPage3(cp){
	var url=$('.getData3').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList3(businesslist,p);
		}
	});
}
/********************************************************************************/
//groupsel.jsp
$('#search5').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime5').val();
	var endTime = $('#endTime5').val();
	var cardNo = $('#cardNo5').val();
	var group = $('#group5').val();
	var url = $('#search5').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":cardNo,
					"con.group":group,
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
					getList5(businesslist,p);
					$('#num5').html(num);
					$('#pageCount5').html(pageCount);
					$('#gpsel').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList5(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){				
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].certNo+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].upTaxi.substr(0,businesslist[i].upTaxi.length-2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].downTaxi.substr(0,businesslist[i].downTaxi.length-2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].waitTime+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].type+"</td>";
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb5').html(tab);
		$('#currentPage5').html(page);
	}
// 获取末页
function getEndPage5(pageCount){
	getCurrentPage5(pageCount);
}
// 获取跳转页码
function getOnePage5(){
	var onePage = $('#jumpPage5').val();
	getCurrentPage5(onePage);
}

function getCurrentPage5(cp){
	var url=$('.getData5').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{			
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList5(businesslist,p);
		}
	});
}	


