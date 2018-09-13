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
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script src="<%=path%>/custom/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>


	<form id="edituserform">
		<input type="hidden" name="uId" id="uId" value="${user.uId }"/>
		
      	<table class="kv-table">
		
		<tr>
              <td class="tb-left">用户编号：</td> 
              <td class="kv-label"><input type="text" name="userId" id="userId" readonly value="${user.userId }" style="height:25px;margin-top:6px"/></td>
		</tr>
		
		<tr>
              <td class="tb-left">用户姓名：</td>
              <td class="kv-label"><input type="text" name="userName" id="userName" value="${user.userName }" style="height:25px;margin-top:6px"/></td>
		</tr>
		
		<tr>
              <td class="tb-left">登录名：</td> 
              <td class="kv-label"><input type="text" name="userLoginName" id="userLoginName" value="${user.userLoginName }" style="height:25px;margin-top:6px"></td>
		</tr>
		
		<tr>
              <td class="tb-left">用户密码：</td> 
              <td class="kv-label"><input type="text" name="userPassword" id="userPassword" value="${user.userPassword }" style="height:25px;margin-top:6px"></td>
		</tr>
		
		<tr>
              <td class="tb-left">用户备注：</td> 
              <td class="kv-label"><textarea name="userRemark" id="userRemark" style="width:152px;height:60px;margin-top:6px">${user.userRemark }</textarea></td>
		
		</tr>
		
	  <tr>
             <td class="tb-left">用户当前角色：</td>
	 		<td class="kv-label">
	 			<c:forEach items="${urList}" var="ur">
             	<input id="checkboxname" name="checkboxname" type="checkbox" value="${ur.roId}" checked/>${ur.roleName}
	 			</c:forEach></td>
	 </tr>
	 
	 <tr>
	  		<td class="kv-label" colspan="2" style="padding-left:15px"><c:forEach items="${nurList}" var="nur"> 
            <input id="checkboxname" name="checkboxname" type="checkbox"  value="${nur.roId}" style="height:10px;margin-top:6px"/>${nur.roleName}
	 </c:forEach></td>
	 </tr>
	 </table>
	 <input id="res2" type="reset" style="display:none;"/>
     <input id="submit2" type="submit" style="display:none;"/>
	</form>
	<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>

</body>
</html>