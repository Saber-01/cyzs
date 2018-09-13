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
    
    <title>My JSP 'projectIndexSAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>

    <form action="projectIndexS/addAndSubmit" method="post" id="addPrIndexS">
    	<table id="checkUnitAdd" class="kv-table">
    	    <tr>
        	<td class="tb-left">工程名称：</td>
        	<td  class="kv-label"><select id="prName1" name="prId" style="width:153px;height:30px">
    						<option value="">请选择工程</option>
    						<c:forEach items="${prName}" var="prn">
  							<option value="${prn.prId}">${prn.prName}</option>
  							</c:forEach>
    					</select>
    			<br><div style="margin-top:5px">注：一个工程可以对应多个工长</div>
    		</td>
        	</tr>
    		<tr>
        	<td class="tb-left">工长姓名：</td>
        	<td  class="kv-label">
        	<select id="gzName1" name="uId" style="width:153px;height:30px">
    					<option value="">请选择工长</option>
    					<c:forEach items="${gzName}" var="gzn">
  						<option value="${gzn.uId}">${gzn.userName}</option>
  						</c:forEach>
    				</select>
    			<br><div style="margin-top:5px">注：一个工长只能对应一个项目</div>
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
