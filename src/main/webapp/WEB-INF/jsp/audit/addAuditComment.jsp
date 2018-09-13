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
<title>补充意见</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
<%-- 
	补充意见
	
	<form id="addAuditComment">
	    <input type="hidden" name="aId" id="aId" value="${auditInfo.aId }"/>
		补充意见栏:<textarea name="auditComment" id="auditComment" onfocus="editvalidate()" style="width:152px;height:60px;margin-top:6px">${auditInfo.auditComment }</textarea>
		<br>
		<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>
	</form> --%>
	
	<form id="addAuditComment">
		<input type="hidden" name="aId" id="aId" value="${auditInfo.aId}">
    	<table id="auditComment_Add" class="kv-table">
    		<tr>
        	<td class="tb-left">补充意见栏：</td>
        	<td  class="kv-label"><textarea name="auditComment" id="auditComment" onfocus="editvalidate()" 
        							style="width:152px;height:60px;">${auditInfo.auditComment }</textarea></td>
        	</tr>
    		<tr>
				<td class="tb-left"></td>
    			<td class="kv-label"></td>
    		</tr>
    		</table>
    	<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>
    </form>
	
</body>
</html>