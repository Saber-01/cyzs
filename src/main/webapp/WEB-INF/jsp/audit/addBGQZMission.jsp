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
<title>新建任务书</title>
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
	var xmlDoc=null;
	var xmlhttp = null;
	var XMLHttpReq = false;
	var wid=1;
	var rowcount=1;
	//定时器计算工资
	setInterval("jswagesum()",500);
	function parseXML() {	// 初始加载时，从本地读取锁定的工程信息
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		}
		else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var loginname = document.getElementsByName("loginname")[0].value;
		var url = "/cyjz/cyjz_file/xmle/" + loginname + ".xml" + "?time=" + new Date().getTime();
	
		xmlhttp.open("POST", url, false);
		xmlhttp.setRequestHeader("If-Modified-Since", "0");
		xmlhttp.send();
		xmlDoc = xmlhttp.responseXML;
		xmlDoc.async = false;
		var x = xmlDoc.getElementsByTagName("project");
		for (i = 0; i < x.length; i++) //工程列表
		{
			var opoption = document.createElement("OPTION");
			opoption.value = x[i].attributes[0].value;
			opoption.text = x[i].childNodes[0].firstChild.nodeValue;
			document.getElementsByName("prId")[0].options.add(opoption);
		}
		for (j = 0; j < x[0].childNodes[1].childNodes.length; j++) //栋号列表
		{
			var opoption = document.createElement("OPTION");
			opoption.value = x[0].childNodes[1].childNodes[j].attributes[0].value;
			opoption.text = x[0].childNodes[1].childNodes[j].childNodes[0].nodeValue;
			document.getElementsByName("unitnumber")[0].options.add(opoption);
		}
		for (j = 0; j < x[0].childNodes[2].childNodes.length; j++) //结算单位列表
		{
			var opoption = document.createElement("OPTION");
			opoption.value = x[0].childNodes[2].childNodes[j].attributes[0].value;
			opoption.text = x[0].childNodes[2].childNodes[j].childNodes[0].nodeValue;
			document.getElementsByName("cuId")[0].options.add(opoption);
		}
	}
	
	function updateother() {	//当更改select工程选项时，工程对应信息也要改变
		var x = xmlDoc.getElementsByTagName("project");
		var flag = document.addmissionform.prId.selectedIndex;
		var prid = document.addmissionform.prId.options[flag].value;
		var t;
		for (i = 0; i < x.length; i++) //工程列表
		{
			if (prid == x[i].attributes[0].value) {
				t = i;
				break;
			}
		}
		document.getElementById("unitnumber").options.length=0;
		document.getElementById("cuId").options.length=0;
		for (j = 0; j < x[t].childNodes[1].childNodes.length; j++) //栋号列表
		{
			var opoption = document.createElement("option");
			opoption.value = x[t].childNodes[1].childNodes[j].attributes[0].value;
			opoption.text = x[t].childNodes[1].childNodes[j].childNodes[0].nodeValue;
			document.getElementsByName("unitnumber")[0].options.add(opoption);
		}
		for (j = 0; j < x[t].childNodes[2].childNodes.length; j++) //结算单位列表
		{
			var opoption = document.createElement("option");
			opoption.value = x[t].childNodes[2].childNodes[j].attributes[0].value;
			opoption.text = x[t].childNodes[2].childNodes[j].childNodes[0].nodeValue;
			document.getElementsByName("cuId")[0].options.add(opoption);
		}
	}
	
	function newAddMissionTree(otable)
	{
		var prNameSelect=document.getElementById("prId");
		var cuNameSelect=document.getElementById("cuId");
		var unitnumberSelect=document.getElementById("unitnumber");
		var prName=prNameSelect.options[prNameSelect.selectedIndex].value;
		var cuName=cuNameSelect.options[cuNameSelect.selectedIndex].value;
		var unitnumber=unitnumberSelect.options[unitnumberSelect.selectedIndex].value;
		var mtid = document.getElementById("mtid").value;

		var table=otable;
		var index = table.rows.length;
		index = index - 1;

		if(prName == "")
		{
			alert("请选择工程名称！");
			return false;
		}else if(cuName == "请选择")
			{
				alert("请选择结算单位！");
				return false;
			}else if(unitnumber == "")
				{
					alert("请选择栋号！");
					return false;
				}else
					{
						window.open("toAddMission?index=" + index
							+ "&prName=" + prName + "&cuName=" + cuName
							+ "&unitnumber=" + unitnumber + "&mtid=" + mtid,
							"新建任务书",
							"width=800,height=650,toolbar=no,scrollbars=anto,menubar=no,top=50,left=200");
			return true;
		}
	}
	
	function addNewContractRow(otable)
	{
		var button = document.getElementById("clickbutton");
		button.style.display = 'inline-block';
		var FlowListTable=otable;
		var row = FlowListTable.insertRow();
		var m =FlowListTable.rows[0].cells.length;
		//列号
		var col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="5" name="contentSerial"	size="8" value='+rowcount+' readonly onBlur=checkContentSerial(this);></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="5" name="innerId"	size="8" value="" style="color:red"></TD>';
		//分部
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="10" name="partPositionText" value="" readonly><input type="hidden" value="" name="partId"></div></TD>';
		//工程部位
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="15" name="partItemoneText" value="" readonly><input type="hidden" value="" name="psId"></div></TD>';
		//工作项目
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="15" name="partItemtwoText" value="" readonly><input type="hidden" value="" name="operationId"></div></TD>';
		//具体部位
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="10" name="floor"></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="5" name="unName" value="" readonly><input type="hidden" value="" name="unitId"></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="5" name="price"></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="8" name="realQuantity"></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="8" name="wageSum" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="15" name="isReturn" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML=' <TD><div align="center"><input type="text" size="15" name="remark"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<a id="FlowListLabel" style="color:blue;cursor:hand;text-decoration:underline" onclick="delCurrentRow(this)">删除</a>';
		
		rowcount++;
		if(!newAddMissionTree(customers))
			FlowListTable.deleteRow(FlowListTable.rows.length-1);
	}
	function delCurrentRow(obj)
	{
		var tr = obj.parentNode.parentNode;
		rowcount = rowcount - 1;
		tr.parentNode.removeChild(tr);
		if (rowcount == 1) {
			var button = document.getElementById("clickbutton");
			button.style.display = 'none';
		}
		var contentserials = document.getElementsByName("contentSerial");
		var i=0;
		for(i=0;i<rowcount;i++){
			var serial=contentserials[i];
			serial.value=i+1;
		}		
	}
	function emptytable(tableObj) {
		var table = tableObj;
		for (var j = table.rows.length; j > 1; j--) {
			table.deleteRow(table.rows.length - 1);
			rowcount = rowcount - 1;
		}
		var button = document.getElementById("clickbutton");
		button.style.display = 'none';
	}
	function jswagesum()
	{
		var price=document.getElementsByName("price");
		var rq=document.getElementsByName("realQuantity");
		var ws=document.getElementsByName("wageSum");
		if(price.length==0) { }
		else
		{
			for(var i=0;i<price.length;i++)
			{
				var pricevalue=price[i].value*rq[i].value;
				ws[i].value=pricevalue.toFixed(2);		
			}
		}		
	}
	
	function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else
			return false;
	}
	function isCurrency(strname)
	{
		var reg=/^\d{1,20}(\.\d{0,2})?$/;
		return reg.test(strname);
	}
	function isNumber(strname)
	{
		var reg=/^[-\+]?\d+(\.\d+)?$/;
		return reg.test(strname);
	}
	function isOvertwo(strname)
	{
		if(strname.toString().split(".")[1]!=null&&strname.toString().split(".")[1].length>2)
			return true;
		else
			return false;
	}
	function isOverfour(strname)
	{
		if(strname.toString().split(".")[1]!=null&&strname.toString().split(".")[1].length>4)
			return true;
		else
			return false;
	}	
	function validate()
	{
		var flist=document.getElementsByName("floor");
		var prlist=document.getElementsByName("price");
		var rqlist=document.getElementsByName("realQuantity");
		var remarklist=document.getElementsByName("remark");
		var inneridlist=document.getElementsByName("innerId");
		for(i=0;i<inneridlist.length;i++)
		{
			if(isEmpty(inneridlist[i].value)||inneridlist[i].value.length>4)
			{
				alert("第"+(i+1)+"行内编号为空或者长度不合法！")
				return false;
			}
			if(inneridlist[i].value.substring(0,1)!='内')
			{
				alert("第"+(i+1)+"行内编号格式不合法！必须是:'\'内\'+数字'");
				return false;
			}
			if(isNaN(inneridlist[i].value.substring(1,inneridlist[i].value.length-1)))
			{
				alert("第"+(i+1)+"行内编号格式不合法！必须是:'\'内\'+数字'");
				return false;
			}
		}
		for(i=0;i<flist.length;i++)
		{
			if(isEmpty(flist[i].value.trim())||flist[i].value.length>250)
			{
				alert("第"+(i+1)+"行具体部位为空或者长度不合法！")
				return false;
			}
		}
		//ls 验证负数必须填写备注
		for(i=0;i<rqlist.length;i++)
		{
			if(isEmpty(rqlist[i].value)||!isNumber(rqlist[i].value))
			{
				alert("第"+(i+1)+"行工程量为空或者格式不是数字！")
				return false;
			}
			if(rqlist[i].value<0)
			{
				if(remarklist[i].value=="")
				{
					alert("第"+(i+1)+"行工程量为负，备注必须填写！");
					return false;
				}
			}
			if(isOverfour(rqlist[i].value)){
				alert("第"+(i+1)+"行工程量超过四小数！");
				return false;
			}
		}
		//判断单价的小数位数和是否是数字
		for(i=0;i<prlist.length;i++){
			if(isEmpty(prlist[i].value)||!isNumber(prlist[i].value))
			{
				alert("第"+(i+1)+"行单价为空或者格式不是数字！")
				return false;
			}
			if(isOvertwo(prlist[i].value)){
				alert("第"+(i+1)+"行单价超过两位小数！");
				return false;
			}
		}
		for(i=0;i<remarklist.length;i++)
		{
			if(remarklist[i].value.length>250)
			{
				alert("第"+(i+1)+"行备注内容过多！")
				return false;
			}
		}
		return true;
	}
	function upload() {
		var loginname = document.getElementsByName("loginname")[0].value;
		window.location.href = "${pageContext.request.contextPath }/preUploadFile?mid=" + loginname;

	}
	
	// 计算结束时间
	$(document).ready(function() {
		$("#dd").datebox({
			required : true,
			onSelect : function(date) {
				$("#dd").val(date);
				var year = date.getFullYear();
				var month = date.getUTCMonth()+2;
				var day = date.getDate() - 1;
				if (month + 1 > 12) {
					month = 1;
					year = year + 1;
				}
				if (month < 10)
					month = "0" + month;
				
				if (day == 0) {
					if (month == 4 || month == 6 || month == 9 || month == 11) {
						day = 30;
					} else if (day == 2) {
						if (year%400 == 0 && year%100 == 0) {
							day = 29;
						} else if (year%4 == 0) {
							day = 29;
						} else {
							day = 28;
						}
					} else {
						day = 31;
					}
				}
				if (day < 10)
					day = "0" + day;
				var end = year + "-" + month + "-" + day;
				$("#cc").datebox('setValue', end);
			}
		});
		// end date
		$("#cc").datebox({
			required : true
		});
		
	});
	
</script>

<body onload="parseXML()">

	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">变更签证任务书</font>
				</td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${error == null }">
	<form name="addmissionform" method="post" action="addBGQZMission" onSubmit="">
		<input type="hidden" name="loginname" id="loginname" value="${user.userLoginName }" />
		<input type="hidden" name="tableLength" id="tableLength" value="" />
		<input type="hidden" name="mtid" id="mtid" value="${mtid }" />
		<input type="hidden" name="mtcode" id="mtcode" value="${mtcode }" />
		<table id="addmission" class="table table-striped table-bordered table-condensed">
			<tr>
				<td width="20%">&nbsp;&nbsp;
				工程名称 : <select style="width:150px;height:30px;line-height:30px;" id="prId" name="prId" onchange="updateother();emptytable(customers);"></select>
				</td>
				<td width="20%">&nbsp;&nbsp;
				栋号 : <select style="width:120px;height:30px;line-height:30px;" id="unitnumber" name="unitnumber" onchange="emptytable(customers);"></select>
				</td>
				<td width="60%">&nbsp;&nbsp;
				结算单位 : <select style="width:120px;height:30px;line-height:30px;" id="cuId" name="cuId" onchange="emptytable(customers);"></select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;
		   	   	起始时间 : <input type="text" size="10" name="beginDate" value='${begindate}' 
		   	   				id="dd" class="easyui-textbox" style="width:135px;height:30px;line-height:30px; required">
		   	   				</td>
		   	   	<td>&nbsp;&nbsp;
		   	   	结束时间 : <input type="text" size="10" name="endDate" value='${endate}' readonly="readonly"
		   	   				id="cc" class="easyui-textbox" style="width:135px;height:30px;line-height:30px; required;">
				</td>
				<td>&nbsp;&nbsp;
				<a onclick="window.open('preUploadFile?mid=${user.userLoginName }','添加附件','width=800,height=650,toolbar=no,scrollbars=anto,menubar=no,top=50,left=200')">
				添加附件
				</a>
				</td>
			</tr>
		</table>
		<table id="customers">
			<tr>
				<th style="width: 4%">序号</th>
				<th style="width: 5%">内编号</th>
				<th style="width: 7%">分部</th>
				<th style="width: 11%">工程部位</th>
				<th style="width: 11%">工作项目</th>
				<th style="width: 15%">具体部位</th>
				<th style="width: 5%">单位</th>
				<th style="width: 6%">单价</th>
				<th style="width: 6%">工程量</th>
				<th style="width: 7%">工资金额</th>
				<th style="width: 8%">资料是否返回</th>
				<th style="width: 12%">备注</th>
				<th style="width: 3%">
				<a id="FlowListLabel" style="color:blue;cursor:hand;text-decoration:underline" onclick="addNewContractRow(customers)">新增</a>
				</th>
			</tr>
		</table>
		<br>
		<br>
		<div style="text-align:center; width:100%;">
		<div id="clickbutton" style="display:none;">
		<input type="button" value="提交" class="easyui-linkbutton" iconCls="icon-ok" onclick="if(validate())addmissionform.submit();" />
		<input onclick="{window.location.href='missionSubmitList'}" type="button" value="取消" class="easyui-linkbutton" iconCls="icon-ok">
		</div>
	</form></c:if>
	<span style="color:red; font-weight: bold">${error }</span>

</body>
</html>
