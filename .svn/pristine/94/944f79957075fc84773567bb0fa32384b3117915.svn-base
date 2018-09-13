<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重庆诚业建筑工程有限公司班组计件汇总</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>
<script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.printarea.js"></script> 
<script type="text/javascript" src="<%=path%>/js/excelExport.js"></script>
    
    
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
function printWithAlert(){
  $("div#tbView").printArea();
  
}
</script>
</head> 
 
<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">重庆诚业建筑工程有限公司班组计件汇总</font>
				</td>
			</tr>
		</tbody>
</table>
		<input type="button"  onclick="$('#lwgzzf').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton">
		<input type="button"  onclick="printWithAlert()" value="打印" class="easyui-linkbutton">
<br><br>
<table class="table table-striped table-bordered table-condensed" id="lwgzzf">
	<tr>
	<td colspan="8" > 
	重庆诚业建筑工程有限公司班组计件汇总</td>
	</tr>
	<tr>
		<td width=15%>工程名称</td>
		<td width=10%>栋号</td>
		<td width=7%>结算单位</td>
		<td width=10%>分部</td>
		<td width=15%>工程部位</td>			
		<td width=10%>单价</td>
		<td width=10%>已结工程量</td>
        <td width=10%>已结金额</td>			
	</tr>
	<c:choose>
	   <c:when test="${pstList!=null && fn:length(pstList)>0}">
	    <c:forEach items="${pstList}" var="item">
		<tr >
		<td><c:out value="${item.prName}"></c:out></td>
		<td><c:out value="${item.unitNumber}"/></td>
		<td><c:out value="${item.cuName}"></c:out></td>
		<td><c:out value="${item.pName}"></c:out></td>
		<td><c:out value="${item.psName}"></c:out></td>
        <td><c:out value="${item.price}"></c:out></td>
        <td><c:out value="${item.settledConstruction}"></c:out></td>
        <td><c:out value="${item.settledMoney}"></c:out></td>
		</tr>                                  
		</c:forEach>
	  </c:when>
	<c:otherwise>
	    <tr>
			<td colspan="8"><font color="#ff0000"> 
			---没有相应的结果---</font> 
			</td>
	   </tr>
	</c:otherwise>
	</c:choose>			
</table>
<br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>

</body>
</html>
