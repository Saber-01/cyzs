<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editYAuditRole.jsp' starting page</title>
    
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

    <form id="editAuditRole">
    	<input type="hidden" id="arId" name="arId" value="${ar.arId}"/>
       	<table class="kv-table">
		
		<tr>
              <td class="tb-left">任务书类型：</td>
    		  <td class="kv-label">${ar.mtName}</td>
    		</tr>
    		<tr>
    			<td class="tb-left">授权角色：</td>
    			<td class="kv-label">${ar.roName}</td>
    		</tr>
    		<tr>
    			<td class="tb-left">任务书步骤：</td>
    			<td class="kv-label"><input type="text" name="auditStep" value="${ar.auditStep}" style="height:25px"/>
    				<br><div style="margin-top:5px">格式：1位非负整数，最小值为0，最大值是当前最大步骤值</div></td>
    		</tr>
    		<tr>
    			<td class="tb-left">授权角色备注：</td>
    			<td class="kv-label"><textarea  name="remark" clos="15" rows="3">${ar.remark}</textarea>
    				<br><div style="margin-top:5px">格式：不超过250个汉字，一个汉字相当于两个半角字符</div>
    			</td >
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
