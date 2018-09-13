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
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/admin-all.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.22.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>

	
	
	<form id="addRoleform">
	<table class="kv-table">	
		<tr>
	
		<td class="tb-left">角色编号 : </td>  
		<td class="kv-label"><input type="text" id="roleId" name="roleId" readonly value="${roleId}" style="height:25px;"/></td>
		</tr>
		
		<tr>
		<td class="tb-left">角色名称 : </td>
		<td class="kv-label"><input type="text" id="roleName" name="roleName" style="height:30px;"/></td>
		</tr>
		
		<tr>
		<td class="tb-left">角色备注 : </td>
		<td class="kv-label"><textarea id="roleRemark" name="roleRemark" cols="15" rows="3" style="width:150px;"></textarea></td>
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
	<input id="res1" type="reset" style="display:none;"/>
	<input id="submit1" type="submit" style="display:none;"/>
	</form>

</body>
</html>