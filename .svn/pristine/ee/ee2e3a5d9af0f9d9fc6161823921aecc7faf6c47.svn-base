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
    
    <title>My JSP 'projectDetailAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form method="post" id="addProjectDetail">
    	<input type="hidden" name="prId" id="prId" value="${prId}"/>
    	<table class="kv-table">
    		<tr>
    			<td class="tb-left">工程栋号：</td>
    			<td class="kv-label"><input type="text" id="unitNumber" name="unitNumber"/>
    				<br><div style="margin-top:5px">格式：不超过20个字，不能重复</div></td>
    		</tr>
    		<tr>
				<td class="tb-left"></td>
    			<td class="kv-label"></td>
    		</tr>
    		<tr>
				<td class="tb-left"></td>
    			<td class="kv-label"></td>
    		</tr>
    	</table>
	<input id="res1" type="reset" style="display:none;"/><input id="submit1" type="submit" style="display:none;"/>
    </form>
  </body>
</html>
