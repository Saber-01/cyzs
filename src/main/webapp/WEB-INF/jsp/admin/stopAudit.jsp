<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>关停土建审核</title> 

<link href="<%=path%>/pages/css/base1.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container">
       <div id="tb" style="padding:30 30px;">
       	<form name="stopauditForm" method="post" action="stopAudit" onSubmit="">
      <table class="kv-table" title="暂时关停土建审核功能" style="width:100%;height:220px;border:0px">
			<tr>
				<td class="kv-label" colspan="3" bgColor=#efefef style="padding-left:530px">暂时关停土建审核功能</td>
			</tr>
			<tr>
				<td class="kv-label" >当前状态</td>
				<td class="kv-label">关停失效时间</td>
				<td class="kv-label">操作</td>
			</tr>
			<tr>
			<c:if test="${stopAudit.paraValue == 1 }">
				<td class="kv-label" width="20%">
						<input type="hidden" name="paraid" value="${stopAudit.paraId }">
						<input type="hidden" name="value" value="${stopAudit.paraValue }">审核允许
				</td>
				<td class="kv-label" width="50%">
					<input type="text" size="10" name="opendate" id="dd" class="easyui-textbox" 
						style="width:166px;height:30px;line-height:35px; required">
					<input type="text" size="2" name="openhour" value="23"> 时 
					<input type="text" size="2" name="openminute" value="59"> 分
					<br><span style="color:red">${error}</span>
				</td>
				<td class="kv-label">
				<input type="button" value="关停" onclick="if(validatestopaudit(this)) stopauditForm.submit();" class="easyui-linkbutton"></td>
			</c:if>
			
			<c:if test="${stopAudit.paraValue == 0 }">
				<td class="kv-label" width="20%">
						<input type="hidden" name="paraid" value="${stopAudit.paraId }">
						<input type="hidden" name="value" value="${stopAudit.paraValue }">审核关停
				</td>
				<td class="kv-label" width="50%">
					<fmt:formatDate value="${stopAudit.paraLosetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="kv-label">
				<input type="button" value="提前开启" onclick="if(confirm('确认要提前开启审核功能吗？')) stopauditForm.submit();" class="easyui-linkbutton"></td>
			</c:if>
			</tr>
			<tr>
				<td class="kv-label" colspan="3" bgColor=#efefef style="padding-left:400px">到了关停失效时间，批量审核功能自动开启,也可以在上面手动开启。</td>
			</tr>
		</table>		
	</form>
	</div>
</div>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


<script type="text/javascript">

	$(document).ready(function() {
		$("#dd").datebox({
			required : true,
			onSelect : function(date) {
				$("#dd").val(date);
			}
		});
	});

	function isNumber(strname) {
		var reg = /^[-\+]?\d+(\.\d+)?$/;
		return reg.test(strname);
	}
	function validatestopaudit() {

		var value = document.getElementsByName("value");
		var opendate = document.getElementsByName("opendate");
		var openhour = document.getElementsByName("openhour");
		var openminute = document.getElementsByName("openminute");

		if (value[0].value == "0") {
			alert("所有审核功能已开启！");
			return true;
		}
		if (opendate[0].value == "") {
			alert("日期为空！");
			return false;
		}
		if (openhour[0].value == "") {
			alert("小时为空！");
			return false;
		}
		if (openminute[0].value == "") {
			alert("分钟为空！");
			return false;
		}
		if (!isNumber(openhour[0].value) || parseInt(openhour[0].value) < 0
				|| parseInt(openhour[0].value) > 23) {
			alert("小时格式不正确！");
			return false;
		}
		if (!isNumber(openminute[0].value) || parseInt(openminute[0].value) < 0
				|| parseInt(openminute[0].value) > 59) {
			alert("分钟格式不正确！");
			return false;
		}

		return true;
	}
	
</script>

</body>
</html>
