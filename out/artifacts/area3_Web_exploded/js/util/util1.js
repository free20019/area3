//获取公司
$(document).ready(function(){
	$.ajax({
		url:"getInfo.action",
	dataType:"JSON",
	type:"POST",
	async:true,
	success:function(data){	
		$('#areaselect').empty();
		$("#areaselect").append("<option value='0' >--选择公司--</option>");
		var list = data.compList;
		if(list.length == 1){
			$("#areaselect").append("<option value='"+list[0]+"'>"+list[0]+"</option>");
			 getBranch(list[0]);
		}else{
			for(var i=0;i<list.length;i++){
				$('#areaselect').append("<option value='"+list[i]+"'>"+list[i]+"</option>");
			}
		}
		
	}
	});
});
$('#areaselect').change(function(){
	var comp = $('#areaselect option:selected').text();
	getBranch(comp);
	getCard();
});
	
$('#compselect').change(function(){
	getCard();
})
//获取分公司
function getBranch(comp){
	$.ajax({
		url:"getBranch.action",
		data:{
			company:comp,
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.baList;
			$("#compselect").empty();			
				$("#compselect").append("<option value='0' >--选择分公司--</option>");
				for(var i=0;i<list.length;i++){
					$("#compselect").append("<option value='"+list[i]+"'>"+list[i]+"</option>");				
				}
		}
	});
}
//获取车号 
$(document).ready(function(){
	$.ajax({
		url:"getCard.action",		
		dataType:"JSON",
		type:"POST",
		async:false,
		success:function(data){
			var list = data.cardList;
			$("#vehiselect").empty();
			$("#vehiselect").append("<option value='0' >--选择车号--</option>");
			for(var i=0;i<list.length;i++){
				$("#vehiselect").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
			}
		}
	});
});
function getCard(){
	
	var comp = $('#areaselect option:selected').text();
	var branch = $('#compselect option:selected').text();
	$.ajax({
		url:"getCard.action",
		data:{
			company:comp,
			branch:branch
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			var list = data.cardList;
			$("#vehiselect").empty();			
			$("#vehiselect").append("<option value='0' >--选择车号--</option>");
			if(list != null){
				for(var i=0;i<list.length;i++){
					$("#vehiselect").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
		}
	});
}
	