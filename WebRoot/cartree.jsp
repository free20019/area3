<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'treedemo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript" src="js/libs/jquery-1.4.2.min.js"></script>  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>  
	<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script> 
	
	<script type="text/javascript" language="javascript">  
	$(document).ready(function(){  
		$.ajax({
			url : 'cartree.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				fuzhi(json.data);  
			},
			error:function(){
			}		
		});
      });  

	
	var setting = { 
			async: {
				enable: true, //启用异步加载
				url:"findcars.action", //调用的后台的方法
				autoParam:["id"],  //向后台传递的参数
				dataFilter: filter
			},
            check: {  
                enable: true  
            },  
            data: {  
                simpleData: {  
                    enable: true  
                }  
            },  
            callback: {     /**回调函数的设置，随便写了两个**/  
            	 }  
        };  
	function filter(treeId, parentNode, childNodes) {
		var array =  eval("("+childNodes.zdata+")");  //获取后台传递的数据
		return array;
		}
	function fuzhi(data){  
        zNodes=eval("("+data+")");  
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);  
    } 
</script>     
  </head>
  
  <body>
    <ul id="treeDemo" class="ztree"></ul> 
  </body>
</html>
