<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
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
<title>修改任务书</title>
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
	var XMLHttpReq = false;
	function createXMLHttpRequest() {
		//创建XMLHttpRequest对象 
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			XMLHttpReq = new XMLHttpRequest();
		}
		else {// code for IE6, IE5
			XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function sendRequestPartPosition()//查分项一 
	{
		var index = document.getElementById("index").value;
		var partSelectObj = document.getElementById("partId");//[index];
		var url = "listPartPositionByPart?partId="
					+ partSelectObj.options[partSelectObj.selectedIndex].value
					+ "&index=" + index;
		createXMLHttpRequest(); //调用创建对象方法        
		XMLHttpReq.open("post", url, false);
		XMLHttpReq.onreadystatechange = processResponse1;//指定响应函数 调用
		XMLHttpReq.setRequestHeader("If-Modified-Since", "0");
		XMLHttpReq.send();//发送请求 
	}
	//处理返回信息函数 
	function processResponse1() {
		if (XMLHttpReq.readyState == 4) {//代表服务器已经传回所有的信息，可以处理了 
			if (XMLHttpReq.status == 200) { //信息已经成功返回,开始处理信息 
				listPartPosition();
			} else //页面不正常 
			{
				window.alert("listPartPosition--您所请求的页面有异常。");
			}
		}
	}
	function listPartPosition() {
		var results1 = XMLHttpReq.responseXML.getElementsByTagName("partPosition");
		var psobj = document.getElementById("psId"); //删除psobj选项
		psobj.length = 1;
		var opobj = document.getElementById("operationId"); //删除opobj选项
		opobj.length = 1;
		var mtCode = document.getElementById("mtCode").value;
		if (mtCode == "1") {
			var unitobj = document.getElementById("unitId"); //删除unitobj选项
			if (unitobj != null)//合同内其他任务书没有单位
				unitobj.length = 1;
		}
		if (results1.length > 0) {
			for (var i = 0; i < results1.length; i++) {
				var psoption = document.createElement("OPTION");
				psoption.appendChild(document.createTextNode(results1[i].childNodes[1].firstChild.nodeValue)) 
				psoption.setAttribute("value", results1[i].childNodes[0].firstChild.nodeValue);
				if (results1.length == 1) {
					psoption.setAttribute("selected", true);
					psobj.options.add(psoption);
					sendRequestOperation();
				} else {
					psobj.options.add(psoption);
				}
			}
		}
	}
	function sendRequestOperation()//查分项二
	{
		var index = document.getElementById("index").value;
		var psObj = document.getElementById("psId"); //[index];
		var url = "listOperationByPS?psId="
					+ psObj.options[psObj.selectedIndex].value + "&index="
					+ index;
		createXMLHttpRequest(); //调用创建对象方法        
		XMLHttpReq.open("post", url, false);
		XMLHttpReq.onreadystatechange = processResponse2;//指定响应函数 调用
		XMLHttpReq.setRequestHeader("If-Modified-Since", "0");
		XMLHttpReq.send();//发送请求   
	}
	//处理返回信息函数 
	function processResponse2() {
		if (XMLHttpReq.readyState == 4) {//代表服务器已经传回所有的信息，可以处理了 
			if (XMLHttpReq.status == 200) { //信息已经成功返回,开始处理信息 
				listOperation();
			} else //页面不正常 
			{
				window.alert("listOperation--您所请求的页面有异常。");
			}
		}
	}
	function listOperation() {
		var results2 = XMLHttpReq.responseXML.getElementsByTagName("index");
		var results1 = XMLHttpReq.responseXML.getElementsByTagName("Operation");
		var index = 0;
		if (results2.length > 0) {
			var index = results2[0].firstChild.nodeValue;
		}
		var opobj = document.getElementById("operationId"); //删除psobj选项
		opobj.length = 1;
		var mtCode = document.getElementById("mtCode").value;
		if (mtCode == "1") {
			var unitobj = document.getElementById("unitId"); //删除unitobj选项 只有计件才删除
			if (unitobj != null)//合同内其他任务书没有单位
				unitobj.length = 1;
		}
		if (results1.length > 0) {
			for (var i = 0; i < results1.length; i++) {
				var ooption = document.createElement("OPTION");
				//ooption.setAttribute("text", results1[i].childNodes[1].firstChild.nodeValue);
				ooption.appendChild(document.createTextNode(results1[i].childNodes[1].firstChild.nodeValue)) 
				ooption.setAttribute("value", results1[i].childNodes[0].firstChild.nodeValue);
				if (results1.length == 1) {
					ooption.setAttribute("selected", true);
					opobj.options.add(ooption);
					if (mtCode != "3" && mtCode != "7")	//不是计时任务书
						sendRequestUnit();
				} else {
					opobj.options.add(ooption);
				}
			}
		}
	}

	function sendRequestUnit()//查单位
	{
		var index = document.getElementById("index").value;
		var prId = document.getElementById("prId").value;
		var pcpId = document.getElementById("pcpId").value;
		var cuId = document.getElementById("cuId").value;
		//var mtId = document.getElementById("mtId").value;
		// 找出工作项目
		var operationObj = document.getElementById("operationId");
		var oId = operationObj.options[operationObj.selectedIndex].value
		var psobj = document.getElementById("psId");
		var psId = psobj.options[psobj.selectedIndex].value;
		// 找出分部
		var partSelect = document.getElementById("partId").selectedIndex;
		var pId = document.getElementById("partId").options[partSelect].value;
		
		var url = "listUnit?index=" + index + "&cuId=" + cuId + "&prId=" + prId + "&pId=" + pId + "&psId=" + psId +
					"&pcpId=" + pcpId + "&oId=" + oId;
		createXMLHttpRequest(); //调用创建对象方法        
		XMLHttpReq.open("post", url, false);
		XMLHttpReq.onreadystatechange = processResponse4;//指定响应函数 调用
		XMLHttpReq.setRequestHeader("If-Modified-Since", "0");
		XMLHttpReq.send();//发送请求
	}
	//处理返回信息函数 
	function processResponse4() {
		if (XMLHttpReq.readyState == 4) {//代表服务器已经传回所有的信息，可以处理了 
			if (XMLHttpReq.status == 200) { //信息已经成功返回,开始处理信息 
				listUnit();
			} else //页面不正常 
			{
				alert(XMLHttpReq.status);
				window.alert("listUnit--您所请求的页面有异常。");
			}
		}
	}
	function listUnit() {
		//alert("显示单位")
		var mtCode = document.getElementById("mtCode").value;
		var results2 = XMLHttpReq.responseXML.getElementsByTagName("index");
		var results1 = XMLHttpReq.responseXML.getElementsByTagName("Unit");
		var results3 = XMLHttpReq.responseXML.getElementsByTagName("ykl");
		var results4 = XMLHttpReq.responseXML.getElementsByTagName("jjykl");
		var ykl = document.getElementById("ykl");
		var jjykl = document.getElementById("jjykl");
		ykl.value = results3[0].firstChild.nodeValue;
		jjykl.value = results4[0].firstChild.nodeValue;
		var index = 0;
		if (results2.length > 0) {
			var index = results2[0].firstChild.nodeValue;
		}
		var unitobj = document.getElementById("unitId"); //删除unitobj选项 只有计件才删除
		if (unitobj != null)//合同内其他任务书没有单位
			unitobj.length = 1;
		if (results1.length > 0) {
			for (var i = 0; i < results1[0].childNodes.length; i++) {
			//alert(results1[i].childNodes[1].firstChild.nodeValue)
				var unitoption = document.createElement("OPTION");
				unitoption.appendChild(document.createTextNode(results1[i].childNodes[1].firstChild.nodeValue)) 
				unitoption.setAttribute("value", results1[i].childNodes[0].firstChild.nodeValue);
				unitoption.setAttribute("selected", true);
				unitobj.options.add(unitoption);
				if (mtCode == 1) {
					sendRequestAccQuantity();//为计件任务书时，查询 已结累计工程量
				}
			}
		}
	}
	function queryobj(gcmc, gcbw, jsdw, gzxm, dw, unumber, psid, mtid) {
		this.project = gcmc;
		this.part = gcbw;
		//this.department = ssbm;
		this.checkunit = jsdw;
		this.operation = gzxm;
		this.unit = dw;
		//this.pcid=pcid;
		this.unumber = unumber;
		this.psid = psid;
		//this.index=index;
		this.mtid = mtid;
	}
	//发送请求函数 
	function sendRequestAccQuantity() {
		var url = "countAccQuantity";
		//找出工程名称
		var gcmc = window.opener.document.getElementById("prId").value;
		//找出分部
		var gcbw = document.getElementById("partId").value;
		//找出结算单位
		var jsdw = window.opener.document.getElementById("cuId").value;
		//找出工程部位 
		var psSelect = document.getElementById("psId").selectedIndex;
		var psid = document.getElementById("psId").options[psSelect].value;
		//找出工作项目
		var oSelect = document.getElementById("operationId").selectedIndex;
		var gzxm = document.getElementById("operationId").options[oSelect].value;
		//找出单位			
		var unitSelect = document.getElementById("unitId").selectedIndex;
		var dw = document.getElementById("unitId").options[unitSelect].value;
		//找出栋号
		var unumber = window.opener.document.getElementById("pcpId").value;
		//找出任务书类型
		var mtid = window.opener.document.getElementById("mtId").value;
		if (gcmc == null || gcmc == "" || gcbw == null || gcbw == ""
						|| jsdw == null
						|| jsdw == "" || gzxm == null || gzxm == ""
						|| dw == null || dw == "" || unumber == null
						|| unumber == "" || psid == null || psid == "" || mtid==null || mtid=="") {
			return;
		} else {
			var query_obj = new queryobj(gcmc, gcbw, jsdw, gzxm, dw, unumber, psid, mtid);
			createXMLHttpRequest(); //调用创建对象方法
			XMLHttpReq.open("post", url, true);
			XMLHttpReq.onreadystatechange = processResponse5;//指定响应函数 调用 
			XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			XMLHttpReq.send("querystr=" + JSON.stringify(query_obj));//发送请求 
		}
	}
	//处理返回信息函数 
	function processResponse5() {
		if (XMLHttpReq.readyState == 4) {//代表服务器已经传回所有的信息，可以处理了 
			if (XMLHttpReq.status == 200) { //信息已经成功返回,开始处理信息       
				//alert("1");  
				updateaccQuantity();
			} else //页面不正常 
			{
				//alert(XMLHttpReq.status);
				window.alert("addmission--4--您所请求的页面有异常。");
			}
		}
	}
	function updateaccQuantity() {
		var resultjson = eval("(" + XMLHttpReq.responseText + ")");
		var aq = document.getElementById("accQuantity");
		if (typeof (resultjson.accQuantity) == "undefined")
			aq.value = "0";
		else {
			aq.value = resultjson.accQuantity;
		}
		var price = document.getElementById("price");
		var tmp = resultjson.price * 1;
		if (!tmp) {
			alert("没有此项的单价，请制定！");
			price.value = "0";
		} else {
			price.value = resultjson.price;
		}
	}
	function isEmpty(strname) {
		if (strname == null || strname.length == 0)
			return true;
		else
			return false;
	}
	function isCurrency(strname) {
		var reg = /^\d{1,20}(\.\d{0,2})?$/;
		return reg.test(strname);
	}
	function isNumber(strname) {
		var reg = /^[-\+]?\d+(\.\d+)?$/;
		return reg.test(strname);
	}
	function isOverfour(strname) {
		if (strname.toString().split(".")[1] != null && strname.toString().split(".")[1].length > 4)
			return true;
		else
			return false;
		}
	function validate() {
		var partSelect = document.getElementById("partId").selectedIndex;
		var partPosSelect = document.getElementById("psId").selectedIndex;
		var operationSelect = document.getElementById("operationId").selectedIndex;
		//var fsSelect = document.getElementById("FsId").selectedIndex;
		var mtCode = document.getElementById("mtCode").value;
		var price = document.getElementById("price").value;
		var realQuantity = document.getElementById("realQuantity").value;
		if (mtCode == '4') {
			var innerId = "";
			var getDisplay = document.getElementById("innerIdselect").style.display;
			if (getDisplay == 'none') {
				innerId = document.getElementById("innerIdinput").value;
			} else {
				var innerIdselect = document.getElementById("innerIdselect").selectedIndex;
				innerId = document.getElementById("innerIdselect").options[innerIdselect].value;
			}
			if(isEmpty(innerId.trim())||innerId.length>4)
			{
				alert("内编号为空或者长度不合法！")
				return false;
			}
			if(innerId.substring(0,1)!='内')
			{
				alert("内编号格式不合法！必须是:'\'内\'+数字'");
				return false;
			}
			if(isNaN(innerId.substring(1,innerId.length-1)))
			{
				alert("内编号格式不合法！必须是:'\'内\'+数字'");
				return false;
			}
		}
		
		if (0 == partSelect) {
			alert("未选择分部！");
			return false;
		} else if (0 == partPosSelect) {
			alert("未选择分项一！");
			return false;
		} else if (0 == operationSelect) {
			alert("未选择分项二！");
			return false;
		}
		if (mtCode != '7') {
			var unitSelect = document.getElementById("unitId").selectedIndex;
			var unit = document.getElementById("unitId");
			if (unit.length > 1 && 0 == unitSelect) {
				alert("未选择单位！");
				return false;
			}
			if (price == 0) {
				if (mtCode == '1')
					alert("没有该单价信息！");
				else
					alert("请输入单价！");
				return false;
			}
			//alert("realQuantity = " + realQuantity);
			if (realQuantity == 0) {
				alert("请输入工程量！");
				return false;
			}
			if (!isNumber(price)) {
				alert("输入的单价不是数字！");
				return false;
			}
			if (!isNumber(realQuantity)) {
				alert("输入的工程量不是数字！");
				return false;
			}
		} else {
			var accQuantity = document.getElementById("accQuantity").value;
			var wageSum = document.getElementById("wageSum").value;
			if (!isNumber(price) || isEmpty(price)) {
				alert("输入的安全文明措施费为空不是数字！");
				return false;
			}
			if (!isNumber(realQuantity) || isEmpty(realQuantity)) {
				alert("输入的罚款为空不是数字！");
				return false;
			}
			if (!isNumber(accQuantity) || isEmpty(accQuantity)) {
				alert("输入的扣款材料为空不是数字！");
				return false;
			}
			if (!isNumber(wageSum) || isEmpty(wageSum)) {
				alert("输入的其他为空不是数字！");
				return false;
			}
		}
		return true;
	}
	function submit() {
		var index = document.getElementById("index").value;
		var partSelect = document.getElementById("partId").selectedIndex;
		var partId = document.getElementById("partId").options[partSelect].value;
		var partName = document.getElementById("partId").options[partSelect].innerHTML.replace(/(^\s*)|(\s*$)/g, "");
		var psSelect = document.getElementById("psId").selectedIndex;
		var psId = document.getElementById("psId").options[psSelect].value;
		var psName = document.getElementById("psId").options[psSelect].innerHTML.replace(/(^\s*)|(\s*$)/g, "");
		var oSelect = document.getElementById("operationId").selectedIndex;
		var operationId = document.getElementById("operationId").options[oSelect].value;
		var operationName = document.getElementById("operationId").options[oSelect].innerHTML.replace(/(^\s*)|(\s*$)/g, "");
		var mtCode = document.getElementById("mtCode").value;
		if (mtCode != '7') {
			var unitSelect = document.getElementById("unitId").selectedIndex;
			var unitId = document.getElementById("unitId").options[unitSelect].value;
			var unitName = document.getElementById("unitId").options[unitSelect].innerHTML.replace(/(^\s*)|(\s*$)/g, "");
			var floor = document.getElementById("floor").value;
			window.opener.document.getElementsByName("floor")[index].value = floor;
			window.opener.document.getElementsByName("unId")[index].value = unitId;
			window.opener.document.getElementsByName("unName")[index].value = unitName;
			if (mtCode == '1') {
				var accQuantity = document.getElementById("accQuantity").value;
				window.opener.document.getElementsByName("accQuantity")[index].value = accQuantity;
			}
		} else {
			var wageSum = document.getElementById("wageSum").value;
			var accQuantity = document.getElementById("accQuantity").value;
			window.opener.document.getElementsByName("wageSum")[index].value = wageSum;
			window.opener.document.getElementsByName("accQuantity")[index].value = accQuantity;
		}
		var price = document.getElementById("price").value;
		var realQuantity = document.getElementById("realQuantity").value;
		if (realQuantity == "")
			realQuantity = "0";
		var remark = document.getElementById("remark").value;
		
		if(mtCode == '4'){
			var getDisplay = document.getElementById("innerIdselect").style.display;
			if (getDisplay == 'none') {
				var innerId = document.getElementById("innerIdinput").value;
				window.opener.document.getElementsByName("innerId")[index].value = innerId;
			} else {
				var innerIdselect = document.getElementById("innerIdselect").selectedIndex;
				var innerId = document.getElementById("innerIdselect").options[innerIdselect].value;
				window.opener.document.getElementsByName("innerId")[index].value = innerId;
			}
			var isReturn = document.getElementById("isReturn").value;
			window.opener.document.getElementsByName("isReturn")[index].value = isReturn;
		}

		window.opener.document.getElementsByName("pId")[index].value = partId;
		window.opener.document.getElementsByName("psId")[index].value = psId;
		window.opener.document.getElementsByName("oId")[index].value = operationId;
		//window.opener.document.getElementsByName("fsId")[index].value = fsId;

		window.opener.document.getElementsByName("pName")[index].value = partName;
		window.opener.document.getElementsByName("psName")[index].value = psName;
		window.opener.document.getElementsByName("oName")[index].value = operationName;
		//window.opener.document.getElementsByName("partItemthreeText")[index].value = fsName;

		window.opener.document.getElementsByName("price")[index].value = price;
		window.opener.document.getElementsByName("realQuantity")[index].value = realQuantity;
		window.opener.document.getElementsByName("remark")[index].value = remark;
		//window.opener.document.getElementsByName("isReturn")[index].value = isReturn;
		window.close();
	}
	function cancel() {
		window.close();
	}
	
	function chooseInnerid() {
		var innerid = document.getElementById("innerIdselect");
		innerid.style.display = 'inline-block';
		var innerinput = document.getElementById("innerIdinput");
		innerinput.style.display = 'none';
		var chooseinner = document.getElementById("chooseinner");
		chooseinner.style.display = 'none';
		var inputinner = document.getElementById("inputinner");
		inputinner.style.display = 'inline-block';
	}
	function inputInnerid() {
		var innerid = document.getElementById("innerIdselect");
		innerid.style.display = 'none';
		var innerinput = document.getElementById("innerIdinput");
		innerinput.style.display = 'inline-block';
		var chooseinner = document.getElementById("chooseinner");
		chooseinner.style.display = 'inline-block';
		var inputinner = document.getElementById("inputinner");
		inputinner.style.display = 'none';
	}

</script>
<body>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#1da02b;border:1px solid #cacaca">
				<font style="font-size:1.2em;color: white">修改任务书</font>
				</td>
			</tr>
		</tbody>
	</table>
		<input type="hidden" value="${pcpId }" id="pcpId" />
		<input type="hidden" value="${prId }" id="prId" />
		<input type="hidden" value="${mtCode }" id="mtCode" />
		<input type="hidden" value="${mtId }" id="mtId" />
		<input type="hidden" value="${prName }" name="prName" id="prName" />
		<input type="hidden" value="${index }" name="index" id="index" />
		<input type="hidden" value="${cuId }" name="cuId" id="cuId" />
	
	<h3 style="color:red">${error }</h3>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="30" style="text-align:center;background:#efefef;border:1px solid #cacaca">
				<font style="font-size: 0.9em;">
				工程名称: ${prName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;栋号: ${unitNumber }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结算单位: ${cuName }</font>
				</td>
			</tr>
		</tbody>
	</table>
<c:choose>
	<c:when  test="${ mtCode != 7}">
	<table id="preaddMission">
		<c:if test="${mtCode == 4}">
		<tr>
			<td style="width:40%;text-align:right;padding-right:20px">内编号: </td>
			<td width="60%">
				<div>
				<c:if test="${innerList != '0'}">
					<input type="text" name="innerId" id="innerIdinput" value="${innerId}" style="color:red">
					<select name="innerId" id="innerIdselect" style="display:none">
						<option value="" selected="selected">请选择</option>
						<c:forEach items="${innerList }" var="in">
							<option value="${in.innerId }">${in.innerId }</option>
						</c:forEach>
					</select>
					<a onclick="chooseInnerid()" id="chooseinner">选择已有的内编号</a>
					<a onclick="inputInnerid()" id="inputinner" style="display:none">手动输入</a>
				</c:if>
				<c:if test="${innerList == '0'}">
					<input type="text" name="innerId" id="innerIdinput" value="内00" style="color:red">
					<select name="innerId" id="innerIdselect" style="display:none">
						<option value="" selected="selected">请选择</option>
					</select>
					<a onclick="chooseInnerid()" id="chooseinner">选择已有的内编号</a>
					<a onclick="inputInnerid()" id="inputinner" style="display:none">手动输入</a>
				</c:if>
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<td style="width:40%;text-align:right;padding-right:20px">分部: </td>
			<td width="60%">
				<div>
				<c:if test="${mtCode == 1}">
					<select name="partId" id="partId" onChange="sendRequestPartPosition()">
						<option value="" selected="selected">请选择</option>
						<c:forEach items="${parts }" var="p">
							<c:if test="${p.pName != '计时工'}">
								<option value="${p.pId }">${p.pName }</option>
							</c:if>
					</c:forEach>
					</select>
				</c:if>
				<c:if test="${mtCode == 3}">
					<select name="partId" id="partId" onChange="sendRequestPartPosition()">
						<option value="" selected="selected">请选择</option>
						<c:if test="${js == 'no'}">
						<option value="${pId }">${pName }</option>
						</c:if>
					</select>
				</c:if>
				<c:if test="${mtCode == 4 || mtCode == 5 || mtCode == 7}">
					<select name="partId" id="partId" onChange="sendRequestPartPosition()">
						<option value="" selected="selected">请选择</option>
						<c:if test="${other == 'no'}">
						<option value="${pId }">${pName }</option>
						</c:if>
					</select>
				</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">工程部位/分项一: </td>
			<td>
				<div>
					<select name="psId" id="psId" onChange="sendRequestOperation()">
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		
		<c:choose>
		<c:when test="${mtCode == 1}">
		<tr>
			<td style="text-align:right;padding-right:20px">工作项目/分项二: </td>
			<td>
				<div>
					<select name="operationId" id="operationId" onChange="sendRequestUnit()">
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td style="text-align:right;padding-right:20px">工作项目/分项二: </td>
			<td>
				<div>
					<select name="operationId" id="operationId">	<!-- 这里不用查单价 -->
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${mtCode == 3}">
		<tr>
			<td style="text-align:right;padding-right:20px">单位: </td>
			<td>
				<div>
					<select name="unitId" id="unitId">
						<option value="${workday.unId }" selected="selected">工日</option>
					</select>
				</div>
			</td>
		</tr>
		</c:when>
		
		<c:when test="${mtCode == 4 || mtCode == 5 || mtCode == 6}">
		<tr>
			<td style="text-align:right;padding-right:20px">单位: </td>
			<td>
				<div>
					<select name="unitId" id="unitId">
						<option value="" selected="selected">请选择</option>
						<c:forEach items="${units }" var="u">
							<option value="${u.unId }">${u.unName }</option>
						</c:forEach>
					</select>
				</div>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td style="text-align:right;padding-right:20px">单位: </td>
			<td>
				<div>
					<select name="unitId" id="unitId" onChange="sendRequestAccQuantity()">
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${mtCode == 1}">
		<tr>
			<td style="text-align:right;padding-right:20px">单价: </td>
			<td>
				<div>
					<input type="text" value="${price}" id="price" readonly />
				</div>
			</td>
		</tr>
		</c:when>
		
		<c:otherwise>
		<tr>
			<td style="text-align:right;padding-right:20px">单价: </td>
			<td>
				<div>
					<input type="text" value="${price}" id="price" />
				</div>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td style="text-align:right;padding-right:20px">
			<c:if test="${mtCode == 1}">本月实际完成工程量: </c:if>
			<c:if test="${mtCode == 4}">工程量: </c:if>
			<c:if test="${mtCode == 5}">预计工程量/用工数量: </c:if>
			<c:if test="${mtCode == 3}">当日实际用工数量: </c:if>
			</td>
			<td>
				<div>
					<input type="text" value="${realQuantity}" id="realQuantity" />
				</div>
			</td>
		</tr>
		<c:choose>
		<c:when test="${ mtCode != 1}"><input type="hidden" value="${accQuantity}" id="accQuantity" />
		</c:when>
		<c:otherwise>
		<tr>
			<td style="text-align:right;padding-right:20px">已结累计工程量: </td>
			<td>
				<div>
					<input type="text" value="${accQuantity}" id="accQuantity" readonly />
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">已开工程量: </td>
			<td>
				<div>
					<input type="text" value="" id="jjykl" readonly />
					<input type="hidden" value="" id="ykl" readonly />
				</div>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${mtCode == 4}">    		
    		<tr>
    		<td style="text-align:right;padding-right:20px">资料是否返回: </td>
    		<td>
				<div>
				<select id="isReturn" name="isReturn">
					<option value="未返回">未返回</option>
					<option value="已经返回">已经返回</option>
				</select>
				</div>
			</td>
    	</tr>
		</c:when>
		</c:choose>
		<tr>
			<td style="text-align:right;padding-right:20px">具体部位: </td>
			<td>
				<div>
					<textarea rows="4" cols="50" name="floor" id="floor">${floor}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">备注: </td>
			<td>
				<div>
					<textarea rows="4" cols="50" name="remark" id="remark">${remark}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<br><br>
			<div id="clickbutton" style="text-align:center; width:100%;">
			<input type="button" value="提交" class="easyui-linkbutton" iconCls="icon-ok" onclick="if(validate()) submit()" />
			<input onclick="cancel()" type="button" value="取消" class="easyui-linkbutton">
			</div>
			</td>
		</tr>
	</table>
	</c:when>
	<c:otherwise>
	<table id="preaddMission">
		<tr>
			<td style="width:40%;text-align:right;padding-right:20px">分部: </td>
			<td width="60%">
				<div>
					<select name="partId" id="partId" onChange="sendRequestPartPosition()">
						<option value="" selected="selected">请选择</option>
						<c:if test="${other == 'no'}">
						<option value="${pId }">${pName }</option>
						</c:if>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">工程部位/分项一: </td>
			<td>
				<div>
					<select name="psId" id="psId" onChange="sendRequestOperation()">
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">工作项目/分项二: </td>
			<td>
				<div>
					<select name="operationId" id="operationId">
						<option value="" selected="selected">请选择</option>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">安全文明措施费: </td>
			<td>
				<div>
					<input type="text" id="price" value="${price}">
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">罚款: </td>
			<td>
				<div>
					<input type="text" id="realQuantity" value="${realQuantity}">
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">扣款材料: </td>
			<td>
				<div>
					<input type="text" id="accQuantity" value="${accQuantity}">
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">其他: </td>
			<td>
				<div>
					<input type="text" id="wageSum" value="${wageSum}">
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:20px">备注: </td>
			<td>
				<div>
					<textarea rows="4" cols="50" name="remark" id="remark">${remark}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<br><br>
			<div id="clickbutton" style="text-align:center; width:100%;">
			<input type="button" value="提交" class="easyui-linkbutton" iconCls="icon-ok" onclick="if(validate()) submit()" />
			<input onclick="cancel()" type="button" value="取消" class="easyui-linkbutton">
			</div>
			</td>
		</tr>
	</table>
	</c:otherwise>
</c:choose>

</body>
</html>
