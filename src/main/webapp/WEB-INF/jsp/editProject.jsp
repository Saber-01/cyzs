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
    
    <title>My JSP 'projectEdit.jsp' starting page</title>
    
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
  	<form method="post" id="editProject">
  		<input type="hidden" name="prId" id="prId" value="${pr.prId}">
  		<table class="kv-table" id="showProject">
  			<tr>
  				<td class="tb-left">工程编号：</td>
  				<td  class="kv-label"><input type="text" name="prCode" value="${pr.prCode}" readonly="readonly" style="height:25px"/></td>
  				<!-- <td  class="kv-label"></td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">工程名称：</td>
  				<td  class="kv-label"><input type="text" name="prName" id="prName" value="${pr.prName}" style="height:25px"/>
  					<br><div style="margin-top:5px">格式：不超过50个汉字，不允许重复</div></td>
  			</tr>
  			<tr>
  				<td class="tb-left">项目经理：</td>
  				<td  class="kv-label">
  					<select name="uId" id="uId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:if test="${! empty pr.uId}">
  						<option selected="selected" value="${pr.uId}">${pr.uName}</option>
  						</c:if>
  						<c:forEach items="${xmjingli}" var="xm">
  						<c:if test="${pr.uId != xm.uId }">
  							<option value="${xm.uId}">${xm.userName}</option>
  						</c:if>
  						</c:forEach>
  					</select>
  				</td>
  				<!-- <td  class="kv-label" style="padding-right:25px">格式：不超过10个汉字，项目经理与部门一对一</td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">审计人员：</td>
  				<td  class="kv-label">
  					<select name="shenjiId" id="shenjiId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:if test="${! empty pr.shenjiId}">
  						<option selected="selected" value="${pr.shenjiId}">${pr.shenjiName}</option>
  						</c:if>
  						<c:forEach items="${shenji}" var="sj">
  						<c:if test="${pr.shenjiId != sj.uId }">
  							<option value="${sj.uId}">${sj.userName}</option>
  						</c:if>
  						</c:forEach>
  					</select>
  				</td>
  				<!-- <td  class="kv-label"></td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">成控部经理：</td>
  				<td  class="kv-label">
  					<select name="ckjlId" id="ckjlId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:if test="${! empty pr.ckjlId}">
  						<option selected="selected" value="${pr.ckjlId}">${pr.ckjlName}</option>
  						</c:if>
  						<c:forEach items="${ckbjl}" var="ckb">
  						<c:if test="${pr.ckjlId != ckb.uId }">
  							<option value="${ckb.uId}">${ckb.userName}</option>
  						</c:if>
  						</c:forEach>
  					</select>
  				</td>
  				<!-- <td  class="kv-label" style="padding-right:25px">格式：不超过10个汉字，成控部经理与部门一对一</td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">成控部副经理：</td>
  				<td  class="kv-label">
  					<select name="ckfjlId" id="ckfjlId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:if test="${! empty pr.ckfjlId}">
  						<option selected="selected" value="${pr.ckfjlId}">${pr.ckfjlName}</option>
  						</c:if>
  						<c:forEach items="${ckbfjl}" var="ckbf">
  						<c:if test="${pr.ckfjlId != ckbf.uId }">
  							<option value="${ckbf.uId}">${ckbf.userName}</option>
  						</c:if>
  						</c:forEach>
  					</select>
  				</td>
  				<!-- <td  class="kv-label" style="padding-right:25px">格式：不超过10个汉字，成控部副经理与部门一对一</td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">主管预算：</td>
  				<td  class="kv-label">
  					<select name="zgysId" id="zgysId" style="width:153px;height:30px">
  						<option value="">---请选择---</option>
  						<c:if test="${! empty pr.zgysId}">
  						<option selected="selected" value="${pr.zgysId}">${pr.zgysName}</option>
  						</c:if>
  						<c:forEach items="${zgys}" var="zs">
  						<c:if test="${pr.zgysId != zs.uId }">
  							<option value="${zs.uId}">${zs.userName}</option>
  						</c:if>
  						</c:forEach>
  					</select>
  				</td>
  				<!-- <td  class="kv-label" style="padding-right:25px">格式：不超过10个汉字，主管预算与部门一对一</td> -->
  			</tr>
  			<tr>
  				<td class="tb-left">开始时间：</td>
  				<td  class="kv-label"><input type="text" id="prTime" name="prTime" value="${pr.prTime}" style="height:30px"/></td>
  			</tr>
  		</table>
  				<input id="res2" type="reset" style="display:none;"/><input id="submit2" type="submit" style="display:none;"/>
  	</form>
  </body>
</html>
