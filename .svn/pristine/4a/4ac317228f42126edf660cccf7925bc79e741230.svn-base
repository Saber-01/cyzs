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
<title>审核合同内其他任务书</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/pages/css/base.css" >
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



<script type="text/javascript">
$(document).ready(function(){});


//审核通过
function setAuditResult_Pass(){
var auditResultInput =document.getElementById("auditResult");
auditResultInput.value = "1";
submitForm();


}

//审核不通过
function setAuditResult_NotPass(){
var auditResultInput =document.getElementById("auditResult");
auditResultInput.value = "0";
submitForm();
}


//暂不处理
function setAuditResult_StopAudit(){
var auditResultInput =document.getElementById("auditResult");
auditResultInput.value = "2";
submitForm();
}

function viewMH(mhId){
 window.location.href = "${pageContext.request.contextPath}/viewHMission?mhId="+mhId;
 }

function submitForm(){
  //var json = $("#auditMissionform").serializeArray();
   var json = {};
     json['mId'] =$("#mId").val();
     json['auditResult'] = $("#auditResult").val();
     json['auditComment'] = $("#auditComment").val();
     json['auditPersonal'] = $("#auditPersonal").val();
   var postdata = JSON.stringify(json);
   $.ajax({  
            contentType:'application/json',
            type:"POST",  
            url:"auditMission",
            data:postdata,
            dataType:"text", 
            async: false,
            success:function(data){
           	
			if (data == '1') {
				parent.updateCount();
				window.history.go(-1);
			} else {
				alert("审核失败：kkk" + e);
			}
		},
		error : function(e) {
			alert("审核失败：" + e);
		}
	});

	}
</script>

</head>

<body>
<div class="container">
		<table cellspacing="0" cellpadding="0" width="100%" style="border:0;text-align:center;height:19px;" border="0">
			<tbody>
				<tr>
					<td height="22" style="text-align:center" bgcolor="#e3e3e3">
						<font style="FONT-SIZE: 14px; LETTER-SPACING: 2px; color:#171e24">合同内其他任务书审核</font>
					</td>
				</tr>
			</tbody>
		</table>

			<form name="auditmissionform" method="post" action="auditMission">
			<table id="viewMission">
				<tbody><tr>
					<td colspan="9">
						<div align="right">
							任务书编号 : ${mission.mCode}&nbsp;&nbsp;
							<input type="hidden" name="mId" id="mId" value="${mission.mId}" >
							<input type="hidden" value="null" name="flag" id="flag">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="9">
						工程名称 : ${mission.prName}&nbsp;&nbsp;&nbsp;&nbsp; 
						栋号 : ${mission.unitNumber} 
					</td>
				</tr>
				<tr>
					<td colspan="9">
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
					<td width="13%">
						工程部位
					</td>
					<td width="13%">
						工作项目
					</td>
					<td width="12%">
						安全文明措施费
					</td>
					<td width="10%">
						罚款
					</td>
					<td width="12%">
						扣款材料
					</td>
					<td width="10%">
						其它
					</td>
					<td width="15%">
						备注
					</td>
				</tr>
				
				<c:forEach items="${missionContentList}" var="mcl">
        <tr>
            <td>${mcl.mcCode }</td>
            <td>${mcl.pName}</td> 
            <td>${mcl.psName}</td> 
            <td>${mcl.oName}</td>
            <td>${mcl.price}</td> 
            <td>${mcl.realQuantity}</td> 
            <td>${mcl.accumulateSum}</td> 
            <td>${mcl.wageSum}</td> 
            <td>${mcl.remark}</td>                       
	   </tr>
    </c:forEach>		
				
				<tr>
					<td colspan="9">
						工长 : ${mission.supervisor}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						提交时间 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${mission.submitTime}"/>&nbsp;&nbsp;&nbsp;
                        <c:if test="${file == '有附件'}">
							<a onclick="touploadFile('${mission.mId}', '${mission.mCode}')">
								<font color="#0000ff">有附件</font></a>
						</c:if>
						<c:if test="${file == '无附件'}">无附件</c:if>

					</td>
				</tr>
				<tr>
				<td colspan="9">
				历史版本信息 : 
				<c:if test="${mhList.size()>0}">                                                
                <c:forEach items="${mhList}" var="mhl" varStatus="status">
                <a href="javascript:void(0)" onclick="viewMH('${mhl.mhId}')"> 
					<font color="#0000ff">查看历史版本${status.index + 1}</font> </a>
                </c:forEach>                                                   
             </c:if>
				</td>
				</tr>

				<c:forEach items="${auditInfoList}" var="ail">
					<tr>
						<td colspan="3">
							${ail.roleName } : ${ail.userName}
						</td>
						
						<td colspan="6">
							<div align="left">
							意见 : ${ail.auditComment }&nbsp;<fmt:formatDate value="${ail.auditTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						</td>
						
					</tr>					
				</c:forEach>
			
				
			</tbody>
			</table>
			<div style="border:0;text-align:center">
				<br>
				<br>
				<input name="auditResult" id="auditResult" type="hidden" value="">
				审核意见 : 
				<textarea name="auditComment" id="auditComment" cols="30" rows="5"></textarea>
				审核隐私 : 
				<textarea name="auditPersonal" id="auditPersonal" cols="30" rows="5"></textarea>
				&nbsp; &nbsp;
				<input type="button" value="审核通过" class="easyui-linkbutton" onclick="setAuditResult_Pass()">
				<input type="button" value="审核不通过" class="easyui-linkbutton" onclick="setAuditResult_NotPass()">
				<input type="button" value="暂不处理" class="easyui-linkbutton" onclick="setAuditResult_StopAudit()">
				<br>
				<input type="button" value="返回上一页" onclick="window.history.go(-1);" class="easyui-linkbutton">
			</div>
			<div id="remarkDiv" style="BORDER-RIGHT: #333366 1px solid; BORDER-TOP: #333366 1px solid; DISPLAY: none; Z-INDEX: 1; BORDER-LEFT: #333366 1px solid; BORDER-BOTTOM: #333366 1px solid; POSITION: absolute; BACKGROUND-COLOR: #ffffcc">
				alt描述
			</div>
		</form>
		   
		    
		
	</div>
		

</body>
</html>