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
<title>添加工程部位</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>

	
	<form id="form" name="form" action="/addPosition" method="post">
	<table class="kv-table" id="addPart">
		
		<tr>
        <td class="tb-left">工程部位：</td>
        <td class="kv-label"><input type="text" id ='psName' name='psName' style="height:25px"/>
        	<br><div style="margin-top:5px">格式：不能为空，且不能超过10个汉字</div></td>
		</tr>
		<tr>
                <td class="tb-left">分部：</td>
                <td  class="kv-label">
                   <select name="pName" id=pName style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${partList}" var="r">
                       <option value="${r.pId}">${r.pName}</option>
                      </c:forEach>
                    </select>
                </td>
         </tr>
			
		<tr>
		<td class="tb-left">备注：</td>
        <td class="kv-label"><textarea name="txt" clos="15" rows="3" id ='remark' name='remark' style="width:153px;height:60px"></textarea>
        	<br><div style="margin-top:5px">格式：不能超过250个汉字</div></td>
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
		<input id="res1" type="reset" value="重置" style="display:none"/> 
		<input id="submit" type="submit" value="重置" style="display:none"/> 
	</form>
</body>
</html>
