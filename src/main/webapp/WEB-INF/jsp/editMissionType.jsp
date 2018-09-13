<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'missionTypeEdit.jsp' starting page</title>
    
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

    <form method="post" id="editMissionType">
      	<table class="kv-table">
		
		<tr>
              <td class="tb-left">任务书编号：</td>
			  <td class="kv-label"><input type="text" name="mtCode" value="${mt.mtCode}" readonly="readonly" style="height:25px"/></td>
    			
    			
    		</tr>
    		<tr>
    			<td class="tb-left">任务书名称：</td>
    			<td class="kv-label"><input type="text" name="mtName" id="mtName" value="${mt.mtName}" style="height:25px"/>
    				<br><div style="margin-top:5px">注：不超过50个字</div></td>
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
			<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/> 	
    </form>
  </body>
</html>
