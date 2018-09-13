<%@page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
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
<title>备份数据库</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">备份数据库</font>
				</td>
			</tr>
		</tbody>
</table>
	
	<form name="stopauditForm" method="post" action="backupDatabase">
		<table id="choose" class="table table-striped table-bordered table-condensed">
		<tr>     
			<td width="10%">备份数据库：</td>
			<td>
				<input type="submit" value="手动备份" class="easyui-linkbutton">
			</td>        
		</tr>
	</table>
	</form>

</body>
</html>
