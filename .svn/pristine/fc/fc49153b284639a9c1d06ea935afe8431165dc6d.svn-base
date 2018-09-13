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
<title>修改分部信息</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

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


		<form:form id="editPart" name="editPart" action="editPart" method="post" modelAttribute="part">
		<form:errors path="*"></form:errors>
		
		<form:hidden path="pId"/>
		<!-- <input type="hidden" name="_method" value="put"/> -->
      	<table class="kv-table">
		
		<tr>
              <td class="tb-left">分部编号：</td> 
              <td class="kv-label"><form:input path="partId" readonly="true" style="height:25px"/></td>
			<form:errors path="partId"></form:errors>
		</tr>
		<tr>
			<td class="tb-left">分部名称：</td>
			<td class="kv-label"><form:input path="pName" style="height:25px"/></td>
			<form:errors path="pName"></form:errors>
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
		<input id="res1" name="res1" type="reset" style="display:none;"/>
		<input id="submit" name="submit" type="submit" style="display:none;"/>
	</form:form>
</body>
</html>
