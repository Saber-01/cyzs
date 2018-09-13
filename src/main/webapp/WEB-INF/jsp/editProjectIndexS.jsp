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
    
    <title>My JSP 'projectIndexSEdit.jsp' starting page</title>
    
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
     <form method="post" id="editPrIndexS">
    	 <input type="hidden" name="prSId" id="prSId" value="${pis.prSId}"/>
      	<table class="kv-table">
		<tr>	
    		  <td class="tb-left">工程名称:</td>
			  <td class="kv-label"><select id="prName2" name="prId" style="width:153px;height:30px">
    						<option value="">请选择工程</option>
    						<c:if test="${! empty pis.prId}">
    							<option selected="selected" value="${pis.prId}">${pis.prName}</option>
    						</c:if>
    						<c:forEach items="${prName}" var="prn">
    						<c:if test="${pis.prId != prn.prId }">
  								<option value="${prn.prId}">${prn.prName}</option>
  							</c:if>
  							</c:forEach>
    					</select>
    				<br><div style="margin-top:5px">注：一个工程可以对应多个工长</div>
    			</td>
    	</tr>
		<tr>
              <td class="tb-left">工长姓名:</td>
			  <td class="kv-label"><select id="gzName2" name="uId" style="width:153px;height:30px">
    					<option value="">请选择工长</option>
    					<c:if test="${! empty pis.uId}">
    						<option selected="selected" value="${pis.uId}">${pis.uName}</option>
    					</c:if>
    					<c:forEach items="${gzName}" var="gzn">
    					<c:if test="${pis.uId != gzn.uId }">
  							<option value="${gzn.uId}">${gzn.userName}</option>
  						</c:if>
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
    	
		<input id="res2" type="reset" style="display:none;"/>
        <input id="submit2" type="submit" style="display:none;"/>
    </form>
  </body>
</html>
