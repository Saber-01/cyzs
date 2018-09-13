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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载任务书</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript">

	function listUnitNumber() {
		var prObj = document.getElementById("prId"); //[index];
		var prId = prObj.options[prObj.selectedIndex].value
		if (prId !=null && prId != "") {
			$.ajax({  
		        contentType: 'application/json',
		        type:"POST",  
		        url:"selectUnitNumber/" + prId, 
		        dataType:"json", 
		        success:function(data){
		        var pcpObj = document.getElementById("pcPId");
		        pcpObj.length = 1;
		        $.each(data, function(index,item){
		        	var option = document.createElement("OPTION");
		        	
		        	option.appendChild(document.createTextNode(item.unitNumber));
		        	option.setAttribute("value", item.pcPId);
		        	
		        	pcpObj.options.add(option);                             
		                                               
		        })
		        },  
		        error:function(e) {  
		            alert("出错啦："+e);  
		        }  
			});
		}
	}
	
	function tijiao() {
		var prObj = document.getElementById("prId"); //[index];
		var prId = prObj.options[prObj.selectedIndex].value
		if (prId == "" || prId == null) {
			alert("请选择一个工程!");
			return;
		}
		else {
			var flag = confirm('该过程可能会花几分钟时间,确定要导出到Excel?');
			if (flag) {
				downLoadMissionForm.submit();
			} else {
				return;
			}
		}
	}
</script>

</head>

<body>
	<%-- <input type="hidden" id="mtId" name="mtId" value="${mtId }"> --%>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">下载任务书</font>
				</td>
			</tr>
		</tbody>
	</table>
<form id="role" name="downLoadMissionForm" method="post" action="downloadMission">
      <table id="addmission" class="table table-striped table-bordered table-condensed">
			<tr>
			<td width="25%">工程名称: 
				<select name="prId" id="prId" onChange="listUnitNumber()" style="width:200px;height:30px;line-height:30px;">
					<option value="">----请选择----</option>
						<c:forEach items="${prList}" var="pr">
						<option value="${pr.prId}">${pr.prName}</option>
						</c:forEach>
				</select>
			</td>
			<td width="25%">栋号: 
				<select name="pcPId" id="pcPId" style="width:150px;height:30px;line-height:30px;">
					<option value="">----请选择----</option>
				</select>
			</td>
			<td width="50%" colspan=2>结算月度: 
					<select id="year" name="year" style="width:130px;height:30px;line-height:30px;">
					<option value="">----请选择----</option>
								<option value="${year-7}">${year-7}</option>
								<option value="${year-6}">${year-6}</option>
								<option value="${year-5}">${year-5}</option>
								<option value="${year-4}">${year-4}</option>
								<option value="${year-3}">${year-3}</option>
								<option value="${year-2}">${year-2}</option>
								<option value="${year-1}">${year-1}</option>
								<option value="${year}">${year}</option>
					</select> 年 <select id="month" name="month" style="width:130px;height:30px;line-height:30px;">
								<option value="">----请选择----</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
					</select> 月
				</td>
			</tr>
			<tr>
                <td>任务书类型: 
					<select name="mtId" id="mtId" style="width:200px;height:30px;line-height:30px;">
						<option value="">----请选择----</option>
						<c:forEach items="${mtList}" var="mt">
						<option value="${mt.mtId}">${mt.mtName}</option>
						</c:forEach>
					</select>
				</td>
                <td>结算单位: 
					<select name="cuId" id="cuId" style="width:150px;height:30px;line-height:30px;">
						<option value="">----请选择----</option>
						<c:forEach items="${cuList}" var="cu">
						<option value="${cu.cuId}">${cu.cuName}</option>
						</c:forEach>
					</select>
                </td>
                <td>审核状态: 
					<select id="isAudited" name="isAudited" style="width:150px;height:30px;line-height:30px;">
							<option value="">----请选择----</option>
							<option value="1">全部审核通过</option>
							<option value="2">审核中</option>
							<option value="3">退修</option>
					</select>
				</td>
				<td width="30%">
                    <input type="button" id="button_submit" value="导出到Excel" onclick="tijiao()" class="easyui-linkbutton" style="height:30px;line-height:30px;">
                    		<!-- onclick="if(confirm('该过程可能会花几分钟时间,确定要导出到Excel?')){downLoadMissionForm.submit()}" -->
                    <input type="reset" value="重置" class="easyui-linkbutton" style="height:30px;line-height:30px;">
                </td> 
		</tr>
</table>
</form>
	
	<table>
	<tr>
		<td colspan="10">
			<div style="text-align:left; padding-left:20px">
				<br>
				注：<br>
				导出任务书明细数据时,直接导出到EXCEL中,出现对话框时点"另存为",可选择保存到计算机中。<br />
				注意保存导出的EXCEL的路径。<br />
				工长或项目经理必须选择工程名称。<br />
				<br>	
			</div>
		</td>
	</tr>
	</table>
</body>
</html>