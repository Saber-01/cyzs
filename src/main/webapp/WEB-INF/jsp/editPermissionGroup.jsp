<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>权限组列表</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/admin-all.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.22.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="../js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>

	
	<form:form id="editPermissionGroup" modelAttribute="permissionGroupInfo">
		<form:errors path="*"></form:errors>
		
		<form:hidden path="pgId"/>
		<!-- <input type="hidden" name="_method" value="put"/> -->
		
      	<table class="kv-table">
		
		<tr>
              <td class="tb-left">权限组编号：</td> 
              <td class="kv-label"><form:input path="permissionGroupId" style="height:25px"/></td>
		<form:errors path="permissionGroupId"></form:errors>
		</tr>
		
		<tr>
              <td class="tb-left">权限组名称：</td> 
              <td class="kv-label"><form:input path="permissionGroupName" style="height:25px"/></td>
		<form:errors path="permissionGroupName"></form:errors>
		</tr>
		
		<tr>
              <td class="tb-left">备注：</td> 
              <td class="kv-label"><form:textarea path="permissionGroupRemark" style="width:152px;height:60px"/></td>
		<form:errors path="permissionGroupRemark"></form:errors>
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
		<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>
	</form:form>
</body>
</html>