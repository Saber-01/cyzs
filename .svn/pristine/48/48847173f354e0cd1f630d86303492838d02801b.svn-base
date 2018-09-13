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
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.22.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>


<body>
	<form id= "addpermissionform">
	<table class="kv-table">
		
		<tr>
              <td class="tb-left">权限组名称：</td>
                <td class="kv-label">
                   <select name="pgId" id="pgId" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${pgList}" var="pg">
                        <option value="${pg.pgId}">${pg.permissionGroupName}</option>
                      </c:forEach>
                    </select>
                </td>
         </tr>
				
		<tr>
		<td  class="tb-left">权限名称：</td>
        <td  class="kv-label"><input type="text" id ='permissionName' name='permissionName' style="height:25px;"/></td>
		</tr>	
		<tr>
		<td  class="tb-left" >页面地址：</td>
        <td  class="kv-label"><input type="text" id ='pageAddress' name='pageAddress' style="height:25px;" /></td>
		</tr>	
			
		<tr>
		<td  class="tb-left">权限备注：</td>
        <td   class="kv-label"><textarea id="permissionRemark" name="permissionRemark" cols="15" rows="3" style="width:152px;"></textarea></td>
		</tr>
		<tr>
			<td class="tb-left"></td>
    		<td class="kv-label"></td>
    	</tr>
    	
	</table>
		
		<input id="submit1" type="submit" style="display:none;"/>
	</form>
</body>
</html>