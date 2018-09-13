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
<title>导入计时单价表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.printarea.js"></script> 

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
  

<form >

        <input type = "hidden" id = "message" value = "${massage }">
		<input type = "hidden" id = "sumCount" value = "${sumCount}">
		<input type = "hidden" id = "existCount" value = "${existCount}">
		<input type = "hidden" id = "saveCount" value = "${saveCount}">
		<br>
       <input type="button" onclick="printWithAlert()" value = "打印" class="easyui-linkbutton">
<div id="tbView">     
	<table id="customers" style="margin-top:20px;">
		<tr>
			<th width="15%">工程名称</th>
			<th width="10%">栋号</th>
			<th width="10%">结算单位</th>
			<th width="10%">分部</th>
			<th width="15%">工程部位/分项1</th>
			<th width="15%">工作项目/分项2</th>	
			<th width="10%">单位</th>
			<th width="15%">单价</th>
		</tr>
		<c:choose>
		  <c:when test="${timePrices==null && fn:length(timePrices)==0}">
		        <tr>
					<td colspan=8>没有操作！</td>
				</tr>
		  </c:when>
		  <c:when test="${not empty message}">
		        <tr>
					<td><h3 style="color:red">${message}</h3></td>
			   </tr>
		  </c:when>
		  <c:otherwise>
		            <c:forEach items="${timePrices}" var="item">
		<tr >
		<td><c:out value="${item.prName}"></c:out></td>
		<td><c:out value="${item.pcpNumber}"/></td>
		<td><c:out value="${item.cuName}"></c:out></td>
		<td><c:out value="${item.pName}"></c:out></td>
		<td><c:out value="${item.psName}"></c:out></td>
		<td><c:out value="${item.jobName}"></c:out></td>
		
		<td><c:out value="${item.unName}"></c:out></td>
        <td><c:out value="${item.price}"></c:out></td>
       
		</tr>                                  
		</c:forEach>
		  </c:otherwise>
		</c:choose>
        
		<tr>
			<td colspan="8"><h3 style="color:red">成功导入${saveCount}条记录，覆盖了${existCount}条记录。</h3></td>
	    </tr>	
	</table>
</form>
</div>
<div id="example"></div>

</body>
</html>
