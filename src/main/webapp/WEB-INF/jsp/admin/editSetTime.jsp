<%@page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
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
<title>统计月度时间范围</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

$(document).ready(function() {
	$("#dd").datebox({
		required : true,
		onSelect : function(date) {
			$("#dd").val(date);
		}
	});
});



</script>

</head>

<body>

<form id="editstform" name="editstform" method="post" action="editSetTime" onSubmit="">
		<input type="hidden" name="setId" value="${setTime.setId }">
            	<table class="kv-table">
		
		<tr>
              <td class="kv-label" style="padding-left:10%">结算月份</td>
              <td class="kv-label">${setTime.sYear}年${setTime.sMonth}月</td>
         </tr>
		<tr>
              <td class="kv-label" style="padding-left:10%">开始时间(最后审核时间)</td>
			  <td class="kv-label">${begenTime}</td>
		</tr>
		<tr>
              <td class="kv-label" style="padding-left:10%">结束时间(最后审核时间)</td>
			  <td class="kv-label"><input type="text" size="10" name="enddate" id="dd" value="${endTime}" class="easyui-textbox" 
					style="width:153px;height:25px; required"></input>&nbsp;23时59分</td>
		</tr>
		<tr>
              <td class="kv-label" style="padding-left:10%">备注</td>
			  <td class="kv-label"><input type="text" id="remark" name="remark" value="${setTime.remark }" style="width:153px;height:25px">
			  </td>
		
		</tr>


	 </table>
	 <input id="sumbit1" type="submit" value="提交" style="margin-left:170px;display:none"/>
	<input id="res1" type="reset" value="重置" style="display:none"/>
</form>
     <div id="example" style="display:none"></div>


</body>
</html>