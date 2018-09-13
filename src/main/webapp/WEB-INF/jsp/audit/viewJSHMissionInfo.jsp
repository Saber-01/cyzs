<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<title>添加用户信息</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.printarea.js"></script>
<script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



<script type="text/javascript"> 

function printWithAlert(){
  $("div#tbView").printArea();
  
}

function AllAreaExcel() 
	 {
	  var elTable = document.getElementById("viewjsmission"); //你的tableID
                var oRangeRef = document.body.createTextRange();
                oRangeRef.moveToElementText(elTable);
                oRangeRef.execCommand("Copy");
                try {
                    var appExcel = new ActiveXObject("Excel.Application");
                } catch (e) {
                    alert("If you change your mind, refresh your page  and select 'yes' to download excel.");
                    return;
                }
                appExcel.Visible = true;
                appExcel.Workbooks.Add().Worksheets.Item(1).Paste();
                appExcel = null;
	 } 



</script>





</head>

<body>
		<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
			<tbody>
				<tr>
					<td height="22" style="text-align:center" bgcolor="#e3e3e3">
						<font style="FONT-SIZE: 14px; LETTER-SPACING: 2px; color:#171e24">历史计时任务书详细信息</font>
					</td>
				</tr>
			</tbody>
		</table>

				<div align="right" class="NOPRINT" >
				<h3>
				<button title="打印" onclick="printWithAlert()" class="easyui-linkbutton">打印</button>
				<input type="button" onclick="$('#viewMission').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton"> &nbsp; &nbsp;&nbsp; &nbsp;
				</h3>
			</div>
			
		<div id="tbView">
			<table id="viewMission">
				<tbody>
				<tr>
					<td colspan="10">
						<div align="center">历 史 计 时 任 务 书 详 细 信 息</div>
						<div align="right">
							任务书编号 : ${mission.mCode}</div>
					</td>
				</tr>
				<tr>
					<td colspan="10">
						工程名称 : ${mission.prName}&nbsp;&nbsp;&nbsp;&nbsp; 
						栋号 : ${mission.unitNumber}&nbsp;&nbsp;&nbsp;&nbsp; 

					</td>
				</tr>
				<tr>
					<td colspan="10">
						开始时间 : <fmt:formatDate pattern="yyyy-MM-dd" value="${mission.beginDate}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						结束时间 : <fmt:formatDate pattern="yyyy-MM-dd" value="${mission.endDate}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						结算单位 : ${mission.cuName}
						
					</td>
				</tr>
						
				<tr>
					<td width="5%">
						序号
					</td>
					<td width="10%">
						分部
					</td>
					<td width="10%">
						工程部位
					</td>

					<td width="12%">
						工作项目
					</td>
					<td width="14%">
						具体部位
					</td>
					<td width="6%">
						单位
					</td>
					<td width="10%">
						单价
					</td>
					<td width="12%">
						当日实际用工数量
					</td>
					<td width="10%"> 
						工资金额
					</td>
					<td width="13%">
						备注
					</td>
				</tr>
								
				
				
      <c:forEach items="${missionContentList}" var="mcl">
        <tr>
            <td>${mcl.mcCode }</td>
            <td>${mcl.pName}</td> 
            <td>${mcl.psName}</td> 
            <td>${mcl.oName}</td> 
            <td>${mcl.floor}</td> 
            <td>${mcl.unName}</td> 
            <td>${mcl.price}</td> 
            <td>${mcl.realQuantity}</td> 
            <td>${mcl.wageSum}</td> 
            <td>${mcl.remark}</td>                         
	   </tr>
    </c:forEach>		
				<tr>
					<td colspan="10">
						工长 : ${mission.supervisor}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
						提交时间 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${mission.submitTime}"/>
						
					</td>
				</tr>					
			</tbody></table></div>
			<br>
			<div align="center">
						<input type="button" value="返回" class="easyui-linkbutton" onclick="window.history.go(-1);">
					
				</div>
		  
		    
		
		

</body>
</html>