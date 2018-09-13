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
<title>修改合同单价</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>	
		<form:form id="form" action="timePrice/editTimePrice" method="post" modelAttribute="timePrice">
		<form:errors path="*"></form:errors>

		<form:hidden path="jspId"/>
		<!-- <input type="hidden" name="_method" value="put"/> -->
		<table id="showProject" class="kv-table">
	<tr>
        <td class="tb-left">工程名称：</td> 
        <td  class="kv-label"><form:input path="prName"  readonly="true" style="height:25px"/></td>
		<form:errors path="prId"></form:errors>
	</tr>
	<tr>
        <td class="tb-left">栋号：</td> 
        <td  class="kv-label"><form:input path="pcpNumber" readonly="true" style="height:25px"/></td>
		<form:errors path="pcpId"></form:errors>
	</tr>
	<tr>
        <td class="tb-left">分部：</td>
        <td  class="kv-label"><form:input path="pName" readonly="true" style="height:25px"/></td>
		<form:errors path="pId"></form:errors>
	</tr>		
	<tr>
        <td class="tb-left">工程部位/分项1：</td>
        <td  class="kv-label"><form:input path="psName" readonly="true" style="height:25px"/></td>
		<form:errors path="psId"></form:errors>
	</tr>		
	<tr>
        <td class="tb-left">工作项目 /分项2：</td>
        <td  class="kv-label"><form:input path="jobName" readonly="true" style="height:25px"/></td>
		<form:errors path="jobKey"></form:errors>
	</tr>				
	<tr>
        <td class="tb-left">单位：</td>
        <td  class="kv-label"><form:input path="unName" readonly="true" style="height:25px"/></td>
		<form:errors path="unId"></form:errors>
	</tr>		
	
	<tr>
        <td class="tb-left">单价：</td>
        <td  class="kv-label"><form:input id="price" name="price" path="price" style="height:25px"/></td>
		<form:errors path="price"></form:errors>
	</tr>		
		</table>
		<input id="res1" type="reset" value="重置" style="display:none;"/> 
	    <input id="submit" name="submit" type="submit" style="display:none;"/>
	</form:form>

</body>
</html>
