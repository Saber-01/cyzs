<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限组列表</title>
<link href="<%=path%>/pages/css/base1.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>



<script type="text/javascript">
 	$(document).ready(function(){
		$.ajax({
			contentType: 'application/json',
            type:"POST",
			url:"listUserInfo",
			dataType:"json",
			success:function(data){
			$("#customers tr:gt(0)").remove();
	        $("#customers").append("<tr><td class=\"kv-label\" style=\"width:70px;padding-left:35px\">"+"所属角色"+"</td><td class=\"kv-label\" style=\"padding-left:35px\">"+data.roleName+"</td></tr><tr><td class=\"kv-label\" style=\"width:70px;padding-left:35px\">"+"用户编号"+"</td><td class=\"kv-label\" style=\"padding-left:35px\">"+data.userId+
	        	"</td></tr><tr><td class=\"kv-label\" style=\"width:70px;padding-left:35px\">"+"用户名称"+"</td><td class=\"kv-label\" style=\"padding-left:35px\">"+data.userName+"</td></tr><tr><td class=\"kv-label\" style=\"width:70px;padding-left:35px\">"+"登录名"+"</td><td class=\"kv-label\" style=\"padding-left:35px\">"+data.userLoginName+
	        	"</td></tr><tr><td class=\"kv-label\" style=\"width:70px;padding-left:35px\">"+"用户备注"+"</td><td class=\"kv-label\" style=\"padding-left:35px\">"+data.userRemark+"</td></tr>");
	        
	        ("#user").append("<input type=\"hidden\" name=\"uId\" value=\"${data.UId}\">");
			
			}
		});
	});
</script>

</head>

<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">查看用户信息</font>
				</td>
			</tr>
		</tbody>
</table>
<!-- 	<table class="kv-table">
		<thead>
			<tr>
				<td colspan="6" class="kv-label" style="font-weight:bold;padding-left:500px;height:50px">&nbsp;查看用户信息</td>
			</tr>
		</thead>  
	</table> -->
	<form id="user" name="viewuserform" method="post" action="editPassword">
		<table id="customers" class="kv-table" style="width:100%;height:260px;padding:10px;border:0px"></table>
		<br><br>
		<div align="center">
		<input type="button" name="alterpassword" onclick="viewuserform.submit()" value="修改密码" class="easyui-linkbutton">
		</div>
	</form>
	
</body>
</html>