/**
 * 营运统计,查询相关页面JS
 */
//vehicle.jsp
//获取当前日期
$(document).ready(function(){
	$('#vehi').hide();
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
	var time1=time; 
	var time2=time;
	$("#endTime").val(time2);
	$("#startTime").val(time1);
});
//获取公司
$(document).ready(function(){
	var msg = $('#search').data('msg');
	if(msg =="vehicle"){
		$.ajax({
			url:"getInfo.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#company').empty();
			var list = data.compList;
			if(list.length == 1){
				$("#company").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
				 getBranch(list[0]);
			}else{
				$("#company").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$('#company').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
			
		}
		});
	}
});
$('#company').change(function(){
	var comp = $('#company option:selected').text();
	getBranch(comp);
	getCard();
	getSelect();

});
	

//获取分公司
function getBranch(comp){
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
			$("#branch").empty();			
				$("#branch").append("<option value='0' selected ='selected' >--选择分公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branch").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
				}
		}
	});
}
$('#branch').change(function(){
		getCard();
		getSelect();
	
});
//获取车号 
$(document).ready(function(){
	var msg = $('#search').data('msg');
	if(msg == "vehicle"){		
		$.ajax({
			url:"getCard.action",		
			dataType:"JSON",
			type:"POST",
			async:false,
			success:function(data){
				var list = data.cardList;
				$("#cardNo").empty();
				$("#cardNo").append("<option value='0' >--选择车号--</option>");
				for(var i=0;i<list.length;i++){
					$("#cardNo").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
				getSelect();
			}
		});
	}
});

function getCard(){	
	var comp = $('#company option:selected').text();
	var branch = $('#branch option:selected').text();
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
			$("#cardNo").empty();			
			$("#cardNo").append("<option value='0' >--选择车号--</option>");
			if(list != null){
				
				for(var i=0;i<list.length;i++){
					$("#cardNo").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
				
			}
		}
	});

}
//筛选
var arr ;
function getSelect(){
	 arr = new Array(); 
	$("#cardNo option").each(function () {
        var val = $(this).val(); //获取单个value
        arr.push(val);
    });
}

$('#test').blur(function(){
	$("#cardNo").empty();   
	for(var i=0;i<arr.length;i++){
		if(arr[i].indexOf($("#test").val().toUpperCase())>=0){
			if(arr[i]=="0"){
				$("#cardNo").append("<option value='0' >--选择车辆--</option>");
			}else{
				$("#cardNo").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
			}
		}	
	}
});
/****************************************************************/
//certno.jsp
$(document).ready(function(){
	$('#cert').hide();
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
	$("#endTime2").val(time2);
	$("#startTime2").val(time1);
});
//获取公司
$(document).ready(function(){
	var msg = $('#search2').data('msg');
	if(msg =="certno"){
		$.ajax({
			url:"getInfo.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#company2').empty();
			var list = data.compList;
			if(list.length == 1){
				$("#company2").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
				 getBranch2(list[0]);
			}else{
				$("#company2").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$('#company2').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
			
		}
		});
	}
});
$('#company2').change(function(){
	var comp = $('#company2 option:selected').text();
	getBranch2(comp);
	getCert();
	getSelect2();
});
	

//获取分公司
function getBranch2(comp){
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
			$("#branch2").empty();			
				$("#branch2").append("<option value='0' selected ='selected' >--选择分公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branch2").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
				}
		}
	});
}
$('#branch2').change(function(){
		getCert();
		getSelect2();
});


function getCert(){
	var comp = $('#company2 option:selected').text();
	var branch = $('#branch2 option:selected').text();
	var startTime = $('#startTime2').val();
	var endTime = $('#endTime2').val();
	
	$.ajax({
		url:"getCertNo.action",
		data:{
			"con.company":comp,
			"con.branch":branch,
			"con.startTime":startTime,
			"con.endTime":endTime
			},
		dataType:"JSON",
		type:"POST",
		success:function(data){
			var list = data.certList;
			$("#certNo").empty();			
			$("#certNo").append("<option value='0' >--资格证号--</option>");
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#certNo").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
		}
	});
	
}
function getSelect2(){
	arr = new Array();
	$("#certNo option").each(function () {
        var val = $(this).val(); //获取单个value
        arr.push(val);
    });
}
$('#test2').blur(function(){
	$("#certNo").empty();   
	console.log(arr.length);
	for(var i=0;i<arr.length;i++){
		if(arr[i].indexOf($("#test2").val().toUpperCase())>=0){
			if(arr[i]=="0"){
				$("#certNo").append("<option value='0' >--选择车号--</option>");
			}else{
				$("#certNo").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
			}
		}	
	}
});
/****************************************************************************************/
//group.jsp
//获取当前日期
$(document).ready(function(){
	$('#gro').hide();
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
	$("#endTime4").val(time2);
	$("#startTime4").val(time1);
});
//获取群组
$(document).ready(function(){
	var msg = $('#search4').data('msg');	
	if(msg =="group"){
		$.ajax({
			url:"getGroups.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#group').empty();
			$("#group").append("<option value='0' >--选择群组--</option>");
			var list = data.groupList;
				for(var i=0;i<list.length;i++){
					$('#group').append("<option value='"+list[i].id+"'>"+list[i].groupName+"</option>");
				}
			
			
		}
		});
	}
});
/***********************************************************/
//company.jsp
//获取当前日期
$(document).ready(function(){
	$('#com').hide();
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
//	var time1=time+" 00:00:00"; 
//	var time2=time+" 23:59:59"; 
	$("#endTime1").val(time);
	$("#startTime1").val(time);
});
//获取公司
$(document).ready(function(){
	var msg = $('#search1').data('msg');
	if(msg =="company"){
		$.ajax({
			url:"getInfo.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#company1').empty();
			var list = data.compList;
			if(list.length == 1){
				$("#company1").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
				 getBranch(list[0]);
			}else{
				$("#company1").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$('#company1').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
			
		}
		});
	}
});
$('#company1').change(function(){
	var comp = $('#company1 option:selected').text();
	getBranch1(comp);

});
	

//获取分公司
function getBranch1(comp){
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
			$("#branch1").empty();			
				$("#branch1").append("<option value='0' selected ='selected' >--选择分公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branch1").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
				}
		}
	});
}

//business.jsp
//获取当前日期
$(document).ready(function(){
	$('#bus').hide();
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
	$("#endTime3").val(time2);
	$("#startTime3").val(time1);
});
//获取公司
$(document).ready(function(){
	var msg = $('#search3').data('msg');
	if(msg =="business"){
		$.ajax({
			url:"getInfo.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#company3').empty();
			var list = data.compList;
			if(list.length == 1){
				$("#company3").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
				 getBranch(list[0]);
			}else{
				$("#company3").append("<option value='0' >--选择公司--</option>");
				for(var i=0;i<list.length;i++){
					$('#company3').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
			
		}
		});
	}
});
$('#company3').change(function(){
	var comp = $('#company3 option:selected').text();
	getBranch3(comp);
	getCard3();
	getSelect3();

});
	

//获取分公司
function getBranch3(comp){
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
			$("#branch3").empty();			
				$("#branch3").append("<option value='0' selected ='selected' >--选择分公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#branch3").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
				}
		}
	});
}
$('#branch3').change(function(){
		getCard3();
		getSelect3();
	
});
//获取车号 
$(document).ready(function(){
	var msg = $('#search3').data('msg');
	if(msg == "business"){
		$.ajax({
			url:"getCard.action",		
			dataType:"JSON",
			type:"POST",
			async:false,
			success:function(data){
				var list = data.cardList;
				$("#cardNo3").empty();
				$("#cardNo3").append("<option value='0' >--选择车号--</option>");
				for(var i=0;i<list.length;i++){
					$("#cardNo3").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
				getSelect3();
			}
		});
	}
});

function getCard3(){	
	var comp = $('#company3 option:selected').text();
	var branch = $('#branch3 option:selected').text();
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
			$("#cardNo3").empty();			
			$("#cardNo3").append("<option value='0' >--选择车号--</option>");
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#cardNo3").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
		}
	});

}
//筛选
function getSelect3(){
	arr = new Array(); 
	$("#cardNo3 option").each(function () {
        var val = $(this).val(); //获取单个value
        arr.push(val);
    });
}
$('#test3').blur(function(){
	
	$("#cardNo3").empty();   
	console.log(arr.length);
	console.log(arr);
	for(var i=0;i<arr.length;i++){
		if(arr[i].indexOf($("#test3").val().toUpperCase())>=0){
			if(arr[i]=="0"){
				$("#cardNo3").append("<option value='0' >--选择车号--</option>");
			}else{
				$("#cardNo3").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
			}
		}	
	}
});
/******************************************************/
//groupsel.jsp
//获取当前日期
$(document).ready(function(){
	$('#gpsel').hide();
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
	$("#endTime5").val(time2);
	$("#startTime5").val(time1);
});
//获取群组
$(document).ready(function(){
	var msg = $('#search5').data('msg');	
	if(msg =="groupsel"){
		$.ajax({
			url:"getGroups.action",
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){	
			$('#group5').empty();
			$("#group5").append("<option value='0' >--选择群组--</option>");
			var list = data.groupList;
				for(var i=0;i<list.length;i++){
					$('#group5').append("<option value='"+list[i].id+"'>"+list[i].groupName+"</option>");
				}
			
			
		}
		});
	}
});
$('#group5').change(function(){
	getCard5();
	getSelect5();
});
	
//获取车号 
$(document).ready(function(){
	var msg = $('#search5').data('msg');
	if(msg == "groupsel"){		
		$.ajax({
			url:"getGroupCard.action",		
			dataType:"JSON",
			type:"POST",
			async:false,
			success:function(data){
				var list = data.gcardList;
				$("#cardNo5").empty();
				$("#cardNo5").append("<option value='0' >--选择车号--</option>");
				for(var i=0;i<list.length;i++){
					$("#cardNo5").append("<option value='"+list[i].groupVhic+"'>"+list[i].groupVhic+"</option>");
				}
				getSelect5();
			}
		});
	}
});

function getCard5(){	
	var id = $('#group5').val();
	$.ajax({
		url:"getGroupCard.action",
		data:{
			"g.id":id
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.gcardList;
			$("#cardNo5").empty();			
			$("#cardNo5").append("<option value='0' >--选择车号--</option>");
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#cardNo5").append("<option value='"+list[i].groupVhic+"'>"+list[i].groupVhic+"</option>");
				}
			}
		}
	});

}
//筛选
function getSelect5(){
	 arr = new Array(); 
	$("#cardNo5 option").each(function () {
        var val = $(this).val(); //获取单个value
        arr.push(val);
    });
}
$('#test5').blur(function(){
	
	$("#cardNo5").empty();   
	console.log(arr.length);
	for(var i=0;i<arr.length;i++){
		if(arr[i].indexOf($("#test5").val().toUpperCase())>=0){
			if(arr[i]=="0"){
				$("#cardNo5").append("<option value='0' >--选择车号--</option>");
			}else{
				$("#cardNo5").append("<option value='"+arr[i]+"' >"+arr[i]+"</option>");
			}
		}	
	}
});