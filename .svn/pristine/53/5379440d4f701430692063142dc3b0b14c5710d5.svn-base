<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务书列表</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

$(document).ready(function(){
	
	getData();
	
	$("#button_submit").click(function(){
		getData();
	});
		
	$(document).on("click", "#selectAll", function() {
		$("[name = checkboxname]:checkbox").prop("checked", 'true');
	});

	$(document).on("click", "#clean", function() {
		$("[name = checkboxname]:checkbox").attr("checked", false);
	});

});


function getData(){
	var json = {};
	var prId = $("#prId").val();
	json['pcPId'] = $("#pcPId").val();
	json['mtId'] = $("#mtId").val();
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType : 'application/json',
		type : "POST",
		url : "listAuditPL/" + prId,
		dataType : "json",
		data : postdata,
		success : function(data) {
			$("#plMission tr:gt(0)").remove();
			var line = "";
			var accSum, isreturn;
			//alert(data.length)
			document.getElementById("number").innerHTML = data.length;
			$.each(
				data,
				function(index, item) {
					line += "<tr><td rowspan=\'"+item.mcList.length+"\'><input type='checkbox' name='checkboxname' value=\'"+item.mission.mId+"\'></td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.mCode+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.prName+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.unitNumber+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.supervisor+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.beginDate+"到"+item.mission.endDate+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.submitTime+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.cuName+"</td>"+
					"<td rowspan=\'"+item.mcList.length+"\'>"+item.mission.mtName+"</td>"+
					"<td>"+item.mcList[0].pName+"</td>"+
					"<td>"+item.mcList[0].psName+"</td>"+
					"<td>"+item.mcList[0].oName+"</td>";
					
					/* "<c:if test="+item.mission.mtCode != 7+">"+
					"<td>"+item.mcList[0].price+"</td>"+
					"<td>"+item.mcList[0].realQuantity+"</td>"+
					"<td>"+item.mcList[0].wageSum+"</td>"+
					"<td>"+item.mcList[0].accumulateSum+"</td></c:if>"+
					"<c:if test="+item.mission.mtCode == 7+">"+
					"<td></td><td></td><td></td><td></td></c:if>"; */
				if (item.mission.mtName!="合同内其他任务书") {
					if(item.mcList[0].accumulateSum == null) {
						accSum = "";
					} else {
						accSum = item.mcList[0].accumulateSum;
					}
					line += "<td>"+item.mcList[0].floor+"</td>"+
					"<td>"+item.mcList[0].unName+"</td>"+
					"<td>"+item.mcList[0].price+"</td>"+
					"<td>"+item.mcList[0].realQuantity+"</td>"+
					"<td>"+item.mcList[0].wageSum+"</td>"+
					"<td>"+accSum+"</td>";
				} else {
					line += "<td></td><td></td><td></td><td></td><td></td><td></td>";
				}
				if(item.mcList[0].isreturn == null) {
					isreturn = "";
				} else {
					isreturn = item.mcList[0].isreturn;
				}
				line += "<td>"+item.mcList[0].remark+"</td><td>"+isreturn+"</td>";
				if (item.mission.mtName=="合同内其他任务书") {
					line += "<td>"+item.mcList[0].price+"</td>"+
					"<td>"+item.mcList[0].realQuantity+"</td>"+
					"<td>"+item.mcList[0].accumulateSum+"</td>"+
					"<td>"+item.mcList[0].wageSum+"</td>";
				} else {
					line += "<td></td><td></td><td></td><td></td>";
				}
				
				for (var i = 1; i < item.mcList.length; i++) {
					line += "<tr style='color:green'>"+
					"<td>"+item.mcList[i].pName+"</td>"+
					"<td>"+item.mcList[i].psName+"</td>"+
					"<td>"+item.mcList[i].oName+"</td>";
					/* "<td>"+item.mcList[i].floor+"</td>"+
					"<td>"+item.mcList[i].unName+"</td>"; */
					if (item.mission.mtName!="合同内其他任务书") {
						if(item.mcList[i].accumulateSum == null) {
							accSum = "";
						} else {
							accSum = item.mcList[i].accumulateSum;
						}
						line += "<td>"+item.mcList[i].floor+"</td>"+
						"<td>"+item.mcList[i].unName+"</td>"+
						"<td>"+item.mcList[i].price+"</td>"+
						"<td>"+item.mcList[i].realQuantity+"</td>"+
						"<td>"+item.mcList[i].wageSum+"</td>"+
						"<td>"+accSum+"</td>";
					} else {
						line += "<td></td><td></td><td></td><td></td><td></td><td></td>";
					}
					if(item.mcList[i].isreturn == null) {
						isreturn = "";
					} else {
						isreturn = item.mcList[i].isreturn;
					}
					line += "<td>"+item.mcList[i].remark+"</td><td>"+isreturn+"</td>";
					if (item.mission.mtName=="合同内其他任务书") {
						line += "<td>"+item.mcList[i].price+"</td>"+
						"<td>"+item.mcList[i].realQuantity+"</td>"+
						"<td>"+item.mcList[i].accumulateSum+"</td>"+
						"<td>"+item.mcList[i].wageSum+"</td>";
					} else {
						line += "<td></td><td></td><td></td><td></td>";
					}
					line += "</tr>";
				}
				
			})
			$("#plMission").append(line);
		},
		error : function(e) {
			alert("出错啦：" + e);
		}
	});
 }


	//审核通过
	function setAuditResult_Pass() {
		var length = $("input:checkbox:checked").length;
		if (length == 0) {
			alert("请选择 任务书");
		} else {
			var auditResultInput = document.getElementById("auditResult");
			auditResultInput.value = "1";
			plauditmissionform.submit()
		}

	}

	//审核不通过
	function setAuditResult_NotPass() {
		var length = $("input:checkbox:checked").length;
		if (length == 0) {
			alert("请选择 任务书");
		} else {
			var auditResultInput = document.getElementById("auditResult");
			auditResultInput.value = "0";
			plauditmissionform.submit()
		}
	}

	//暂不处理
	function setAuditResult_StopAudit() {
		var length = $("input:checkbox:checked").length;
		if (length == 0) {
			alert("请选择 任务书");
		} else {
			var auditResultInput = document.getElementById("auditResult");
			auditResultInput.value = "2";
			plauditmissionform.submit()
		}
	}
</script>
</head>
<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.1em;">未审任务书信息列表</font>
				</td>
			</tr>
		</tbody>
	</table>
<form id="role"> 
	<input type="hidden" id="prId" name="prId" value="${pj.prId}">
      <table id="choose" class="table table-striped table-bordered table-condensed" >
            <tr>
                <td width="25%">工程名称 : ${pj.prName}</td>
                <td width="20%">栋号 : 
                <select name="pcPId" id="pcPId" style="width:150px;height:30px;line-height:30px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${prIndexPcList}" var="pcl">
                        <option value="${pcl.pcPId}">${pcl.unitNumber}</option>
                      </c:forEach>
                    </select>
                </td>
                <td width="25%">任务书类型 : 
                   <select name="mtId" id="mtId" style="width:180px;height:30px;line-height:30px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${mtList}" var="mt">
                        <option value="${mt.mtId}">${mt.mtName}</option>
                      </c:forEach>
                    </select>

                </td>
            <!--     
            <td>
			<div align="left">结算单位	
			<select name="cuId" id="cuId" style="WIDTH:180px">
				<option value="">---请选择---</option>
				<c:forEach items="${mtList}" var="mt">
                    <option value="${mt.mtId}">${mt.mtName}</option>
                 </c:forEach>
			</select>
			</div>
			</td>--> 
                <td width="30%">
                    <input type="button" id="button_submit" value="查询" class="easyui-linkbutton">
                    <input type="reset" value="重置" class="easyui-linkbutton"> 
                </td>
            </tr>
       
</table>
</form>

<form name="plauditmissionform" method="post" action="auditMissionPL">
      <table id="plMission" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>选择</th>
			<th>编号</th>
			<th>工程名称</th>
			<th>栋号</th>
			<th>工长</th>
			<th>起止时间</th>
			<th>提交时间</th>
			<th>结算单位</th>
			<th>类型</th>
			<th>分部</th>
			<th>工程部位</th>
			<th>工作项目</th>
			<th>具体部位</th>
			<th>单位</th>
			<th>单价</th>
			<th>本月实际完成工程量</th>					
			<th>工资金额</th>
			<th>已结累计工程量</th>				
			<th>备注</th>
			<th>资料状态</th>
			<th>安全文明措施费</th>
			<th>罚款</th>
			<th>扣款材料</th>
			<th>其他</th>
		</tr>
	</table>
	<div style="text-align:left;padding-left:45%">
		<input type="button" value="全选" id="selectAll" class="easyui-linkbutton">
		<input type="button" value="清除" id="clean" class="easyui-linkbutton">
	</div>
	 
	 <div align="center">
				<br>
				<br>
				<input name="auditResult" id="auditResult" type="hidden" value="">
				审核意见:
				<textarea name="auditComment" cols="30" rows="5"></textarea>
				审核隐私:
				<textarea name="auditPersonal" cols="30" rows="5"></textarea>
				&nbsp; &nbsp;
				<input type="button" value="审核通过" class="easyui-linkbutton" onclick="setAuditResult_Pass()">
				<input type="button" value="审核不通过" class="easyui-linkbutton" onclick="setAuditResult_NotPass()">
				<input type="button" value="暂不处理" class="easyui-linkbutton" onclick="setAuditResult_StopAudit()">
				<br>
				<input onclick="window.history.go(-1);" type="button" value="返回" class="easyui-linkbutton" iconCls="icon-ok">
	</div>
	<br>
		<table id="tips" style="width:100%;">
			<tr>
				<td width="92%" align="center" bgcolor="#e3e3e3">
				您共有<span id="number" style="color:red"></span>条未审任务书
				</td>
			</tr>
		</table>
	</form>
</body>
</html>