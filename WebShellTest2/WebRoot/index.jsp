<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后端组测试页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h1>后端组getWebShell第二轮测试</h1>
    <form method="post" enctype="multipart/form-data"  action="fileUpload" id="frm">
			<span id="span1">
				<input type="file" name="hupeng" id="scv"/>
			</span>
			<input type="button" value="点击上传jpg图片>>" onClick="check()"/>
	</form>
	
	<script language="javascript">
	function check(){
	  var fileName = document.getElementById("scv").value;
	  if(fileName==""){
	    alert("请选择要上传的文件!");
	    return false;
	  }
	  //lastIndexOf如果没有搜索到则返回为-1
	  if(fileName.lastIndexOf(".")!=-1)
	  {
	    var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
	    var suppotFile = new Array();
	    suppotFile[0] = "jpg";
	    for(var i =0;i<suppotFile.length;i++){
	      if(suppotFile[i]==fileType){
	        document.getElementById("frm").submit();
	        return true;
	      } else {
	        alert("不支持文件类型："+fileType + "，仅支持jpg格式文件上传");
	        //document.getElementById("span1").innerHTML='<INPUT TYPE="file" NAME="csv" id="csv"/>';//因为IE不支持file控件清空，所有只有重新动态的追加file控件了
	        //document.getElementById("csv").value="";
	        return false;
	      }
	    }
	  }
	}
	</script>
	
  </body>
</html>
