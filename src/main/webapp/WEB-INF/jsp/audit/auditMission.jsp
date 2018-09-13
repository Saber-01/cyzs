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
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
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
	<table class="table table-striped table-bordered table-condensed">
		
	<tr>
		<td>
		  <div align="center">计时任务书详细信息</div>
		   <div align="right">任务书编号:${mission.mCode}</div>
		</td>
	</tr>
	<tr>
        <td>
                                        工程名称:${mission.project.prName} &nbsp;&nbsp;&nbsp;
                                        栋号 :${mission.prIndexPc.unitNumber}&nbsp;&nbsp;&nbsp;                     
        </td>
	</tr>
	<tr>
        <td>
                                        起始时间:${mission.beginDate} &nbsp;&nbsp;&nbsp;
                                        结束时间 :${mission.endDate}&nbsp;&nbsp;&nbsp;
                                         结算单位 :${mission.checkUnit.cuName}&nbsp;&nbsp;&nbsp;                     
        </td>
	</tr>
	<tr>
        <td width="5%">序号</td>
        <td width="5%">分部</td> 
        <td width="5%">分项一</td> 
        <td width="5%">分项二</td> 
        <td width="5%">具体部位</td> 
        <td width="5%">单位</td> 
        <td width="5%">单价</td> 
        <td width="5%">本月实际完成工程量</td> 
        <td width="5%">工资金额</td> 
        <td width="5%">备注</td>                         
	</tr>
	<c:forEach items="${missionContentList}" var="mcl">
    <tr>
        <td>${mcl.mcCode }</td>
        <td>${mcl.part.pName}</td> 
        <td>${mcl.partPosition.psName}</td> 
        <td>${mcl.operation.oName}</td> 
        <td>${mcl.floor}</td> 
        <td>${mcl.unit.unName}</td> 
        <td>${mcl.price}</td> 
        <td>${mcl.realQuantity}</td> 
        <td>${mcl.wageSum}</td> 
        <td>${mcl.remark}</td>                         
	</tr>
    </c:forEach>		
		
	<tr>
        <td>
                                        工长:${mission.supervisor} &nbsp;&nbsp;&nbsp;
                                        提交时间 :${mission.submitTime}&nbsp;&nbsp;&nbsp;                                                      
        </td>
	</tr>
		</table>
		
		
  <div align= "center">
  <br>
  <br>
  <br>
          审核意见:<textarea name="auditComment" cols="15" rows="3"></textarea>
          审核隐私:<textarea name="auditPersonal" cols="15" rows="3"></textarea>
          <br>
          <br>
          <a href="javascript:;" class="purchaser" onclick="Click()">审核通过</a>
          <a href="javascript:;" class="purchaser" onclick="Click()">审核不通过</a>
          <a href="javascript:;" class="purchaser" onclick="Click()">暂不处理</a>
  </div>
		
		

</body>
</html>