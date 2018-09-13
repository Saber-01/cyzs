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
    
    <title>My JSP 'addYAuditRole.jsp' starting page</title>
    
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
    <form id="addAuditRole">
    	<input type="hidden" id="mtId" name="mtId" value="${mt.mtId}"/>
    	<table class="kv-table">
    		<tr>
    			<td class="tb-left">任务书类型：</td>
    			<td class="kv-label"><input type="text" name="mtName" value="${mt.mtName}" readonly="readonly" style="height:25px"/></td>
    		</tr>
    		<tr>
    			<td class="tb-left">任务书步骤：</td>
    			<td class="kv-label"><input type="text" name="auditStep" value="${auditStep}" readonly="readonly" style="height:25px"/></td>
    		</tr>
    		<tr>
    			<td class="tb-left">角色信息：</td>
    			<td class="kv-label">
    				<select name="roId" style="width:152px;height:30px">
    					<option value="">---请选择---</option>
    					<c:forEach items="${roleList}" var="role">
    						<option value="${role.roId}">${role.roleName}</option>			
    					</c:forEach>
    				</select>
    				<br><div style="margin-top:5px">角色不能为空！</div>
    			</td>
    		</tr>
    		<tr>
    			<td class="tb-left">授权角色备注：</td>
    			<td class="kv-label">
    				<textarea rows="10px" cols="15px" name="remark" style="height:60px;width:152px"></textarea>
    				<br><div style="margin-top:5px">格式：不能超过250个汉字，一个汉字相当于两个半角英文字符</div>
    			</td>
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
