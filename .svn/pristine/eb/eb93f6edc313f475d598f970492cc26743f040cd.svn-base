<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加工程锁定信息</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/sunny/jquery-ui.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#customers td, #customers th {
	font-size: 1em;
	border: 1px solid #e3e3e3;
	padding: 3px 7px 2px 7px;
}

#customers tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}
</style>

<script type="text/javascript">

	function getdates() {
		var prids = "";
		var content = document.getElementsByName("checkprojects");

		for (var i = 0; i < content.length; i++) {
			if (content[i].checked == true)
				prids += content[i].value + "|";
		}
		window.location.href = "addSjLockInfo?prids=" + prids;
	}
</script>

</head>

<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">审计工程信息锁定</font>
				</td>
			</tr>
		</tbody>
</table>

	<form name="sjlockinfform" method="post" action="addlockinfor.do"
		onSubmit="">
		<TABLE id="customers" class="table3">
			<tr>
				<td style="color:#ff0000">请选择要锁定的工程</td>
				<td style="color:#ff0000">已锁定的工程</td>
			</tr>
			<TR>
				<td width="40%">
					<div id="prid" style="height:200px;OVERFLOW-y:auto;">
						<c:forEach items="${projects }" var="pr">
							<input type="checkbox" name="checkprojects" value="${pr.prId }" />${pr.prName}<br />
						</c:forEach>
					</div>
				</td>

				<td width="60%">
					<div id="prid" style="height:200px;OVERFLOW-y:auto;">
						<c:forEach items="${lockedprojects }" var="lopr">
							${lopr.prName}<br />
						</c:forEach>

					</div>
				</td>

			</TR>
		</TABLE>
		<br><br>
		<div align="center">
			<input type="button" value="提交" onclick="getdates()" class="easyui-linkbutton">
		</div>
	</form>
</body>
</html>
