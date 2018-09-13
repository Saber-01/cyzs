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
     <link href="<%=path%>/pages/css/base.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/sunny/jquery-ui.css" />
	<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
	
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
	<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script type="text/javascript">
function projectChange(){
	var value = $("#prId").val();
	if(value != "" && value != null){
		getUnitNumberList();
	}else{
		$("#pcPId").empty();
		$("#pcPId").append("<option value=''>---请选择---</option>");
	}
}

function getUnitNumberList(){
	var json = {};
	json['prId'] = $("#prId").val();
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"statistic/getUnitNumberList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#pcPId").empty();
			$("#pcPId").append("<option value=''>---请选择栋号---</option>");
			$.each(data,function(index,item){
				
				$("#pcPId").append("<option value='"+item.pcPId+"'>"+item.unitNumber+"</option>");
		
			})
		},
		error:function(e){
			alert(e);
		}
	});
}

function unitNumberChange(){
	var value = $("#pcPId").val();
	if(value != "" && value != null){
		getPartList();
	}else{
		$("#pId").empty();
		$("#pId").append("<option value=''>---请选择---</option>");
	}
}

function getPartList(){
	var json = {};
	json['pcPId'] = $("#pcPId").val();
	var postdata = JSON.stringify(json);
		$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"statistic/getPartList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#pId").empty();
			$("#pId").append("<option value=''>---请选择分部---</option>");
			$.each(data,function(index,item){
				
				$("#pId").append("<option value='"+item.pId+"'>"+item.pName+"</option>");
		
			})
		},
		error:function(e){
			alert(e);
		}
	});
}
function partChange(){
	var value = $("#pId").val();
	if(value != "" && value != null){
		getPartPositionList();
	}else{
		$("#psId").empty();
		$("#psId").append("<option value=''>---请选择---</option>");
	}
}
function getPartPositionList(){
	var json = {};
	json['pId'] = $("#pId").val();
	var postdata = JSON.stringify(json);
		$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"statistic/getPartPositionList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#psId").empty();
			$("#psId").append("<option value=''>---请选择工程部位---</option>");
			$.each(data,function(index,item){
				
				$("#psId").append("<option value='"+item.psId+"'>"+item.psName+"</option>");
		
			})
		},
		error:function(e){
			alert(e);
		}
	});
}
function partPositionChange(){
	var value = $("#psId").val();
	if(value != "" && value != null){
		getOperationList();
	}else{
		$("#oId").empty();
		$("#oId").append("<option value=''>---请选择---</option>");
	}
}

function getOperationList(){
	var json = {};
	json['psId'] = $("#psId").val();
	var postdata = JSON.stringify(json);
		$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"statistic/getOperationList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#oId").empty();
			$("#oId").append("<option value=''>---请选择工作项目---</option>");
			$.each(data,function(index,item){
				
				$("#oId").append("<option value='"+item.oId+"'>"+item.oName+"</option>");
		
			})
		},
		error:function(e){
			alert(e);
		}
	});
}
</script>
  </head>
  
  <body>
  <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">工程量对比查询</font>
				</td>
			</tr>
		</tbody>
	</table>
    <form action="statistic/getLWGCLDB" onsubmit="">
    	<table id="lwfbList" border="1" class="table table-striped table-bordered table-condensed">
    		<tr>
    			<td style="width:6%">工程名称</td>
    			<td style="width:20%">
    				<select id="prId" name="prId" onchange="projectChange()" style="width:200px;height:30px;line-height:30px;">
    					<option value="">---请选择---</option>
    					<c:forEach items="${prList}" var="pr">
    						<option value="${pr.prId}">${pr.prName}</option>
    					</c:forEach>
    				</select>
    			</td>
    			<td style="width:6%">栋号</td>
    			<td style="width:20%">
    				<select id="pcPId" name="pcPId" onchange="unitNumberChange()" style="width:150px;height:30px;line-height:30px;">
    					<option value="">---请选择---</option>
    				</select>
    			</td>
    			<td style="width:6%">单位</td>
    			<td style="width:42%" colspan=2>
    				<select id="unId" name="unId" style="width:150px;height:30px;line-height:30px;">
    					<option value="">---请选择---</option>
    					<c:forEach items="${unList}" var="un">
    						<option value="${un.unId}">${un.unName }</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td>分部</td>
    			<td>
    				<select id="pId" name="pId" onchange="partChange()" style="width:150px;height:30px;line-height:30px;">
    					<option value="">---请选择---</option>
    				</select>
    			</td>
    			<td>工程部位</td>
    			<td colspan="1">
    				<select id="psId" name="psId" onchange="partPositionChange()" style="width:200px;height:30px;line-height:30px;">
    					<option value="">---请选择---</option>
    				</select>
    			</td>
    			<td>工作项目</td>
    			<td>
    				<select id="oId" name="oId" style="width:200px;height:30px;line-height:30px;">
    					<option value="">--请选择--</option>
    				</select>
    			</td>
    			<td style="width:25%">
    			<input type="submit" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="提交"/>&nbsp;&nbsp;
    			<input type="reset" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="重置"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
