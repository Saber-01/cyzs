<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>添加分部</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else 
			return false;
	}

function isAllFalse(name){
  var input=document.getElementsByName(name);
      for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			if(input[i].checked ==true){
      			return false;
      			}
      		}
  		}
  	return true;
}

function validate(){

  var pName=$("#pName").val();

  
   if(isEmpty(pName)){
            {
				alert("分部名称不能为空")
				return false;
			}
  }
  
  return true;
  
}
</script>
</head>

<body>

	
	<form id="addPart" name="addPart" action="/addPart" method="post">
   	<table id="Part_Add" class="kv-table">
    	<tr>
        	<td class="tb-left">分部名称：</td>
        	<td class="kv-label"><input type="text" id ='pName' name='pName' style="height:25px"/>
        		<br><div style="margin-top:5px">格式：不能超过10个汉字，不能重复</div></td>
		</tr>
		<tr>
				<td class="tb-left"></td>
    			<td class="kv-label"></td>
    		</tr>
    		<tr>
				<td class="tb-left"></td>
    			<td class="kv-label"></td>
    		</tr>
		</table>
	    <input id="res1" type="reset" value="重置" style="display:none;"/> 
	    <input id="submit" name="submit" type="submit" style="display:none;"/>
	</form>
</body>
</html>
