<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java"  pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>上传附件页面</title> 	
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.jqGrid.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
    
    function delconfirm(mid,fname) {
		var flag = confirm("确定要删除 '"+fname+"' 吗?");
		if (flag) {
			window.location.href = "${pageContext.request.contextPath }/deleteFile?mid="+mid+"&fn="+fname;
		} else {
			return;
		}
	}

</script>
</head>

<body style="leftmargin:30; topmargin:20">
<br/>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
		<TBODY>
			<TR>
				<TD vAlign=top width=13 bgColor=#1da02b></TD>
				<TD height=22 align="left" bgColor=#1da02b>
				<FONT style="FONT-SIZE: 14px; LETTER-SPACING: 2px;color:white;padding-left:340px">附件列表</FONT>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<table id="uploadFile">
		<% int i = 1; %>
		<tr>
			<td>序号</td>
			<td>附件名</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${filenamelist}" var="file">
			<tr>
				<td><font color="#0000ff"><%=i++%></font> <br></td>
				<td><font color="#0000ff">${file }</font> <br></td>
				<td>
				<a href="javascript:void(0)" onclick="delconfirm('${mid}','${file}')" style="">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<br/>
	<a href="preUploadFile?mid=${mid}&mCode=${mCode}" class="easyui-linkbutton" iconCls="icon-reload" style="height:30px;margin-left:320px">刷新附件列表</a>

	<br/>
	<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>  
	
	<br/>
	<c:if test="${not empty msg}"><p style="color:red">${msg}</p></c:if>
	<c:if test="${not empty msgsuccess}"><p style="color:green">${msgsuccess}</p></c:if>
		<form action="uploadFile?mid=${mid}&mCode=${mCode}" name="fileuploadform" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="mid" value="${mid}">
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;上传文件：
			<input class="easyui-filebox" name="uploadfile" data-options="prompt:'选择上传的文件...'" style="width:260px;height:34px">
			<input type="submit" class="easyui-linkbutton" iconCls="icon-add" value="开始上传">
			<input type="button" class="easyui-linkbutton" iconCls="icon-ok" value = "完成" onclick ="window.close()">
		</form>
	<br/>
	<p>&nbsp;&nbsp;*上传格式为rar,zip,txt,doc,xls,ppt,jpg类型文件,最大不超过20M</p>	






    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>

</body>
</html>
