<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加工程锁定信息</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/sunny/jquery-ui.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">

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
<style type="text/css">
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#customers td, #customers th {
	font-size: 1em;
	border: 1px solid #e3e3e3;
	padding: 3px 7px 2px 7px;
}

#customers th {
	color: #ff0000;
}

#customers tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}

input[type="text"] {
	BACKGROUND-COLOR: transparent;
    font-size: 1.2em;
    width: 250px;
    height:30px;
}
</style>
<script type="text/javascript">
	function addLock() {
		/*var prId = "";
		var prName = "";
		var units = "";
		var departments = "";
		var checkunits = "";
		var content = "";
		prId = document.getElementsByName("prId")[0].value;
		prName = document.getElementsByName("prName")[0].value;
		
		content = document.getElementsByName("units");
		for (var i = 0; i < content.length; i++) {
			if (content[i].checked == true)
				units += content[i].value + "|";
		}
		if (units == "") {
			alert("栋号为空！")
			return false;
		}*/
		/* content = document.getElementsByName("departments");
		for (var i = 0; i < content.length; i++) {
			if (content[i].checked == true)
				departments += content[i].value + "|";
		}
		if (departments == "") {
			alert("所属部门为空！")
			return false;
		} */

		//content = document.getElementsByName("checkunits");
		//for (var i = 0; i < content.length; i++) {
			//if (content[i].checked == true)
				//checkunits += content[i].value + "|";
				//checkunits += content[i].value;
		//}
		//if (checkunits == "") {
			//alert("结算单位为空！")
			//return false;
		//}
		//window.location.href = "addLockProject?prId=" + prId + "&prName=" + prName + "&units="
				//+ units + "&checkunits=" + checkunits;
		//var a = "addLockProject?prId=" + prId  + "&units="+ units + "&checkunits=" + checkunits;
		//alert(a)
       // window.location.href = "addLockProject?prId=" + prId  + "&units="
				//+ units + "&checkunits=" + checkunits;	
	  var culength=$("input[name=checkunits]:checked").length;
	  var unlength=$("input[name=units]:checked").length;
	  if(unlength == 0){
	    alert("请选择栋号");
	    return false;
	  }
	  if(culength ==0){
	    alert("请选择结算单位");
	    return false;
	  }
	  else{
	   lockform.submit();
	  }

	}
</script>

</head>
<body>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">工程信息锁定</font>
				</td>
			</tr>
		</tbody>
</table>
	<form name="lockform" action="addLockProject" method="post">
		<TABLE id="customers" border="1" cellspacing="0">
		<tr>
			<th>工程名称</th>
			<th>请选择栋号</th>
			<th>请选择结算单位</th>
		</tr>
			<TR>
				<td width="15%">
					<div id="box">
						<input type="hidden" name="prId" value="${prId}" /> 
						<input type="text" size="40" name="prName" value="${prName}" readonly />
					</div>
				</td>

				<td width="20%">
					<div id="unitnumber" class="check">
						<c:forEach items="${unitVector}" var="u">
						<input type="checkbox" name="units" value="${u.unitnumId}" ${u.check} />${u.unitnumName}<br />
						</c:forEach>
					</div>
				</td>

				<td width="25%">
					<div id="checkunit" class="check">
						<c:forEach items="${cuVector}" var="cu">
						<input type="checkbox" name="checkunits" value="${cu.cuId}" ${cu.check} />${cu.cuName}<br />
						</c:forEach>
					</div>
				</td>
			</TR>
		</TABLE>
      </form>
		<div align="center">
				<td><input type="button" value="提交" onclick="addLock()" class="easyui-linkbutton">
				<input type="button" value="取消" onclick="window.history.go(-1);" class="easyui-linkbutton"></td>
		</div>


</body>
</html>
