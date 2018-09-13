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
    
    <title>My JSP 'projectAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
	<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
	
	<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form id="addProject">

  		<table id="showProject" class="kv-table">

  			<tr>
  				<td class="tb-left">工程名称：</td>
  				<td  class="kv-label"><input type="text" name="prName" id="prName" style="height:25px"/>
  					<br><div style="margin-top:5px">格式：不超过50个汉字，不允许重复</div></td>
  			</tr>
  			<tr>

  				<td class="tb-left">项目经理：</td>
  				<td  class="kv-label">
  					<select name="uId" id="uId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:forEach items="${xmjingli}" var="xm">
  							<option value="${xm.uId}">${xm.userName}</option>
  						</c:forEach>
  					</select>
  					<!-- <br><div style="margin-top:5px">格式：不超过10个汉字，项目经理与部门一对一</div> -->
  				</td>
  			</tr>
  			<tr>
  				<td class="tb-left">审计人员：</td>
  				<td  class="kv-label">
  					<select name="shenjiId" id="shenjiId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:forEach items="${shenji}" var="sj">
  							<option value="${sj.uId}">${sj.userName}</option>
  						</c:forEach>
  					</select>
  				</td>
  			</tr>
  			<tr>
  				<td class="tb-left">成控部经理：</td>
  				<td  class="kv-label" >
  					<select name="ckjlId" id="ckjlId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:forEach items="${ckbjl}" var="ckb">
  							<option value="${ckb.uId}">${ckb.userName}</option>
  						</c:forEach>
  					</select>
  					<!-- <br><div style="margin-top:5px">格式：不超过10个汉字，成控部经理与部门一对一</div> -->
  					</td>
  			</tr>
  			<tr>
  				<td class="tb-left">成控部副经理：</td>
  				<td  class="kv-label">
  					<select name="ckfjlId" id="ckfjlId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:forEach items="${ckbfjl}" var="ckbf">
  							<option value="${ckbf.uId}">${ckbf.userName}</option>
  						</c:forEach>
  					</select>
  					<!-- <br><div style="margin-top:5px">格式：不超过10个汉字，成控部副经理与部门一对一</div> -->
  				</td>
  				</tr>
  				<tr>
  				<td class="tb-left">主管预算：</td>
  				<td  class="kv-label">
  					<select name="zgysId" id="zgysId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:forEach items="${zgys}" var="zs">
  							<option value="${zs.uId}">${zs.userName}</option>
  						</c:forEach>
  					</select>
  			</tr>
  			<tr>
  				<td class="tb-left">开始时间：</td>
  				<td  class="kv-label"><input type="text" id="prTime" name="prTime" class="easyui-textbox" style="width:153px;height:30px"/></td>
  			</tr>
  		</table>
  		<input id="res1" type="reset" style="display:none;"/><input id="submit1" type="submit" style="display:none;"/>
  	</form>
  </body>
</html>
