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
<title>权限组列表</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/admin-all.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.22.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="../js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<form:form id= "editPermissionform" modelAttribute="permission" style="hidden">
		<form:errors path="*"></form:errors>
		
		<form:hidden path="perId"/>
		<!-- <input type="hidden" name="_method" value="put"/> -->
		
		

      	<table class="kv-table" >
		
		<tr>
              <td class="tb-left">权限组名称：</td>
              <td  class="kv-label"><select name="pgId" id="pgId" style="width:153px;height:30px">
                   <option value="${permission.pgId}">${permission.permissionGroupName}</option>                  
                  <c:forEach items="${pgList}" var="pg">            
                     <c:choose>
                      <c:when test = "${pg.pgId != permission.pgId}">
                      <option value="${pg.pgId}">${pg.permissionGroupName}</option>
                      </c:when>
                     </c:choose> 
                    </c:forEach>
               </select>
			</td>
			</tr>
			
		<tr>
			<td  class="tb-left">权限编号：</td> 
			<td  class="kv-label"><form:input path="permissionId" style="height:25px;"/>
			<form:errors path="permissionId"></form:errors></td>
		</tr>
		
		<tr>
			<td  class="tb-left">权限名称：</td> 
			<td  class="kv-label"><form:input path="permissionName" style="height:25px;"/>
			<form:errors path="permissionGroupName"></form:errors></td>
		</tr>
		
		<tr>
			<td  class="tb-left">页面地址：</td> 
			<td  class="kv-label"><form:input path="pageAddress" style="height:25px;"/>
			<form:errors path="pageAddress"></form:errors></td>
		</tr>
		
		<tr>
			<td  class="tb-left">权限备注：</td> 
			<td  class="kv-label" ><form:textarea path="permissionRemark" style="width:152px;height:60px;"/>
			<form:errors path="permissionRemark"></form:errors></td>
		</tr>
		
		</table>
		<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>
	</form:form>
</body>
</html>