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
<title>查看权限基本信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>


	<div>
	    <table class="kv-table">
			
		<tr>
              <td class="tb-left">权限组名称：</td>
        	  <td class="kv-label">${permission.permissionGroupName}</td>
		</tr>	
							
		<tr>
              <td class="tb-left">权限编号：</td>
              <td class="kv-label">${permission.permissionId}</td>
		</tr>
		
		<tr>
              <td class="tb-left">权限名称：</td>
              <td class="kv-label">${permission.permissionName}</td>
		</tr>
		
		<tr>
              <td class="tb-left">页面地址：</td>
              <td class="kv-label">${permission.pageAddress}</td>
		</tr>
		
		
		<tr>
              <td class="tb-left">权限备注：</td>
              <td class="kv-label">${permission.permissionRemark}</td>
		</tr>
		
		<tr>
              <td class="tb-left"></td>
              <td class="kv-label"></td>
		</tr>
		</table>
		</div>

</body>
</html>