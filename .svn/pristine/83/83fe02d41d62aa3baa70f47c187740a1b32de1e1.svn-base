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
<title>修改密码</title>
<link href="<%=path%>/pages/css/base1.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
    <script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
	<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>
    
<script type="text/javascript">
$(document).ready(function(){
	$("#editPsw").validate();
	$('#oldPassword').rules('add', { required: true });
	$('#newPassword').rules('add', { required: true, Password:'#newPassword'});
	$('#reNewPassword').rules('add', { required: true, PasswordAgain:'#newPassword'});

});

</script>

</head>

<body>
<form id="user">
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">修改密码</font>
				</td>
			</tr>
		</tbody>
</table>
<!-- 	<table class="kv-table">
		<thead>
			<tr>
				<td colspan="6" class="kv-label" style="font-weight:bold;padding-left:500px;height:50px">&nbsp;修改密码</td>
			</tr>
		</thead>
	</table> -->
</form>
	${message}
	<form id="editPsw" name="alterpasswordform" method="post" action="alterPassword">
		<input type="hidden" name="uId" value="${userIfo.UId}">
		<table id="customers" class="kv-table" style="width:100%;height:260px;padding:10px;border:0px">
			<tr>
				<td class="kv-label" style="width:70px;padding-left:35px">旧密码：</td>
				<td class="kv-label" style="padding-left:35px"><input type="password" id="oldPassword" name="oldPassword" value="" style="width:220px;height:25px;margin-top:6px"></td>
				<td class="kv-label" rowspan="3" width="30%" style="line-height:30px;padding-left:15px">提示: 密码最少为6位字符, 最多为20位字符<br>(从a到z、A到Z、0到9等字符), 不能包含汉字</td>
			</tr>
			<tr>
				<td class="kv-label" style="width:70px;padding-left:35px">新密码：</td>
				<td class="kv-label" style="padding-left:35px"><input type="password" id="newPassword" name="newPassword" value="" style="width:220px;height:25px;margin-top:6px"></td>

			</tr>
			<tr>
				<td class="kv-label" style="width:70px;padding-left:35px">重复新密码：</td>
				<td class="kv-label" style="padding-left:35px">
				<input type="password" id="reNewPassword" name="reNewPassword" value="" style="width:220px;height:25px;margin-top:6px">
				</td>
			</tr>
			</table>
			<br><br>
			<div align="center">
				<input type="submit" name="bt_confirm" value="确认" class="easyui-linkbutton">
				<input type="reset" value="清空" class="easyui-linkbutton">
				<input type="button" value="放弃" onclick="window.history.go(-1);" class="easyui-linkbutton">
			</div>
		
	</form>

</body>
</html>