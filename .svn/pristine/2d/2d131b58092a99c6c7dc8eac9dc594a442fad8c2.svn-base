<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>关停关停预算查看权限</title> 
    

<link href="<%=path%>/pages/css/base1.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container">
       <div id="tb" style="padding:30 30px;">
	<form name="stopauditForm" method="post" action="stopViewBudget" onSubmit="">
      <table class="kv-table" title="暂时关停预算查看权限" style="width:100%;height:220px;border:0px">
			<tr>
				<td class="kv-label" colspan="2" bgColor=#efefef style="padding-left:530px">暂时关停预算查看权限</td>
			</tr>
			<tr>
				<td class="kv-label" >当前状态</td>
				<td class="kv-label">操作</td>
			</tr>
			<tr>
			<c:if test="${stopbudgetview.paraValue == 1 }">
				<td class="kv-label" width="20%">
						<input type="hidden" name="value" value="${stopbudgetview.paraValue}">
						预算查看权限允许
				</td>
				<td class="kv-label">
				<input type="button" value="关停" onclick="stopauditForm.submit()" class="easyui-linkbutton"></td>
			</c:if>
			
			<c:if test="${stopbudgetview.paraValue == 0 }">
				<td class="kv-label" width="20%">
						<input type="hidden" name="value" value="${stopbudgetview.paraValue }">
						预算查看权限关停
				</td>
				<td class="kv-label">
				<input type="button" value="开启" onclick="if(confirm('确认要开启预算查看权限吗？')) stopauditForm.submit()" class="easyui-linkbutton"></td>
			</c:if>
			</tr>
		</table>		
	</form>
	</div>
</div>	
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>



</body>
</html>
