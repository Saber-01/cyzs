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
<title>审核计件任务书</title>
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
<style type="text/css">
input[type="text"] {
	width: 100%;
	height: 100%;
	text-align: center;
}
</style>

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

function submitForm(){
  //var json = $("#auditMissionform").serializeArray();
   var json = {};
     json['mId'] =$("#mId").val();
     json['auditResult'] = $("#auditResult").val();
     json['auditComment'] = $("#auditComment").val();
     json['auditPersonal'] = $("#auditPersonal").val();
  var postdata = JSON.stringify(json);
   //alert(postdata);
   $.ajax({  
            contentType:'application/json',
            type:"POST",  
            url:"auditMission",
            data:postdata,
            dataType:"text", 
            async: false,
            success:function(data){
            if(data=='1'){
             parent.updateCount();
             window.history.go(-1);
            }else{alert("审核失败：kkk"+e);}	
            },  
            error:function(e) {  
              alert("审核失败："+e);  
            }  
        });

}

function viewMH(mhId){
 window.location.href = "${pageContext.request.contextPath}/viewHMission?mhId="+mhId;
 }
 
	function changerealQuantity(realQ, mcId, mcCode, realQuantity, remark0) {
		var price = document.getElementById("Price_" + mcCode);
		var wageS = document.getElementById("WageS_" + mcCode);
		var remark = document.getElementById("Remark_" + mcCode);
		var btnSubmit = document.getElementById("alterQ_" + mcCode);
		var username = document.getElementById("username").value;
		if(realQ.value != realQuantity){
			wageS.innerHTML = realQ.value * price.innerHTML;
			remark.innerHTML = remark0 + " 【" + getNowFormatDate() + " "+ username +"将"+ realQuantity +"改为" + realQ.value + "】";
			btnSubmit.style.display = 'block';
			var realQuantity = realQ.value;
			var wageSum = wageS.innerHTML;
			var remark1 = remark.innerHTML;
			btnSubmit.setAttribute("onclick","alterQ('"+mcId+"', '"+mcCode+"','"+realQuantity+"','"+wageSum+"','"+remark1+"')");
			wageS.innerHTML = realQuantity * price.innerHTML;
			remark.innerHTML = remark1;
		}
	}
	
	function alterQ(mcId, mcCode, realQuantity, wageSum, remark){
		var btnSubmit = document.getElementById("alterQ_" + mcCode);
		var url = "editMissionContentRQ/" + mcId;
		url = url + "?realQuantity=" + realQuantity + "&wageSum=" + wageSum + "&remark=" + encodeURI(remark);
		$.ajax({  
	        contentType:'application/json',
	        type:"POST",  
	        url: url,
	        dataType:"text", 
	        async: false,
	        success:function(data){
	        	if(data=='1'){
	        		alert("更新成功!");
	        		btnSubmit.style.display = 'none';
	        	} else {
	        		alert("更新失败!");
	        	}
	        },  
	        error:function(e) {  
	          alert("更新失败: "+e);
	        }  
	    });
	}
	
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
		return currentdate;
	}

</script>

</head>

<body>
<div class="container">
		<table cellspacing="0" cellpadding="0" width="100%" style="text-align:center;height:19px;" border="0">
			<tbody>
				<tr>
					<td height="22" style="text-align:center" bgcolor="#e3e3e3">
						<font style="FONT-SIZE: 14px; LETTER-SPACING: 2px; color:#171e24">计件任务书审核</font>
					</td>
				</tr>
			</tbody>
		</table>
	<input type="hidden" name="username" id="username" value="${user.userName}" >
		<form name="auditmissionform" method="post" action="auditMission">
			<table id="viewMission">
				<tbody><tr>
					<td colspan="14">
						<div align="right">
							任务书编号 : ${mission.mCode}&nbsp;&nbsp; 
							<input type="hidden" value="null" name="flag" id="flag">
							<input type="hidden" name="mId" id="mId" value="${mission.mId}" >
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="14">
						工程名称 : ${mission.prName}&nbsp;&nbsp;&nbsp;&nbsp; 
						栋号 : ${mission.unitNumber} 
					</td>
				</tr>
				<tr>
					<td colspan="14">
						开始时间 : <fmt:formatDate pattern="yyyy-MM-dd" value="${mission.beginDate}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						结束时间 : <fmt:formatDate pattern="yyyy-MM-dd" value="${mission.endDate}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						结算单位 : ${mission.cuName}
					</td>
				</tr>
				
				<tr>
					<td width="3%">
						序号
					</td>
					<td width="7%">
						分部
					</td>
					<td width="8%">
						工程部位
					</td>
					<td width="8%">
						工作项目
					</td>	
					<td width="10%">
						具体部位
					</td>
					<td width="4%">
						单位
					</td>
					<td width="6%">
						单价
					</td>
					<td width="10%">
						本月实际完成工程量
					</td>
					<td width="7%">
						工资金额
					</td>
					<td width="6%">
						已结量
					</td>
					<td width="7%">
						已结量金额
					</td>
					<td width="8%">
						项目累计已结量
					</td>
					<td width="9%">
						项目累计已结金额
					</td>
					<td width="7%">
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
            <td id="Price_${mcl.mcCode }">${mcl.price}</td> 
            <td>
            <c:if test="${user.roleName == '主管预算' }">
            	<input type="text" id="RealQ_${mcl.mcCode }" onChange="changerealQuantity(this, '${mcl.mcId }', 
            		'${mcl.mcCode }', '${mcl.realQuantity}', '${mcl.remark}')" value="${mcl.realQuantity}">
            	<div align="center"><input type="button" id="alterQ_${mcl.mcCode }" value="提交修改" style="display:none;height:25px;line-height:25px;" 
            		class="easyui-linkbutton"></div>
            </c:if>
            <c:if test="${user.roleName != '主管预算' }">
            	${mcl.realQuantity}
            </c:if>
            </td> 
            <td id="WageS_${mcl.mcCode }">${mcl.wageSum}</td>
            <td>${mcl.yjl}</td>
            <td>${mcl.yjlSum}</td>
            <td>${mcl.ljgcl}</td>
            <td>${mcl.ljgclSum}</td> 
            <td id="Remark_${mcl.mcCode }">${mcl.remark}</td>                         
	   </tr>
    </c:forEach>		
				
				<tr>
					<td colspan="14">
						工长 : ${mission.supervisor}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						提交时间: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${mission.submitTime}"/>
                                                                                    无附件

					</td>
				</tr>
				<tr>
				<td colspan="14" align="left">
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
						
						<td colspan="11">
							<div align="left">
							意见 : ${ail.auditComment }&nbsp;<fmt:formatDate value="${ail.auditTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						</td>
						
					</tr>					
				</c:forEach>
				
			</tbody>
			</table>
			<br>
			<br>
			<div style="border:0;text-align:center;">
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