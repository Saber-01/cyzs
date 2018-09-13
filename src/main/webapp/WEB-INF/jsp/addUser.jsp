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
<title>添加用户信息</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=path%>/js/messages_zh.js"></script>
    <script type="text/javascript" src="<%=path%>/js/additional-methods.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	
	<form id="adduserform">
	<table class="kv-table">	
		<tr>
	
			<td class="tb-left">所属角色：</td>
                <td class="kv-label">
                   <select name="roId" id="roId" onfocus="addvalidate()" style="width:153px;height:30px">
                      <option value="">----请选择角色----</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
         </tr>
			
			
		<tr>
		<td class="tb-left">用户编号：</td>
        <td class="kv-label"><input type="text" id ='userId' name='userId' readonly value= "${userId}" style="height:25px"/></td>
		</tr>	
		<tr>
		<td class="tb-left">用户姓名：</td>
        <td class="kv-label"> <input type="text" id ='userName' onfocus="addvalidate()" name='userName' style="height:25px;"/></td>
		</tr>	
			
		<tr>
		<td class="tb-left">登录名：</td>
        <td class="kv-label"> <input type="text" id ='userLoginName' onfocus="addvalidate()" name='userLoginName' style="height:25px;"/></td>
		</tr>
		
		<tr>
		<td class="tb-left">初始密码：</td>
        <td class="kv-label"><input type="text" id ='userPassword' onfocus="addvalidate()" name='userPassword' style="height:25px;"/></td>
		</tr>
		
		<tr>
		<td class="tb-left">用户备注：</td>
        <td class="kv-label"><textarea name="permissionGroupRemark" onfocus="addvalidate()" cols="15" rows="3" style="width:152px;height:60px;"></textarea></td>
		</tr>	
		</table>
		<input id="res1" type="reset" style="display:none;"/><input id="submit1" type="submit" style="display:none;"/>
	</form>
</body>
</html>
