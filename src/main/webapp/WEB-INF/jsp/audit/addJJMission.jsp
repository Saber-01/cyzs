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

	function addNewContractRow(otable) {
		var button = document.getElementById("clickbutton");
		button.style.display = 'inline-block';
		var FlowListTable = otable;
		var row = FlowListTable.insertRow();
		document.getElementById("tableLength").value = FlowListTable.rows.length;
		var col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="5" name="contentSerial" value='
				+ rowcount + ' readonly onBlur=checkContentSerial(this);></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="partPositionText" id="partPositionText'+FlowListTable.rows.length+'" readonly><input type="hidden" value="" name="partId"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="partItemoneText" id="partItemoneText'+FlowListTable.rows.length+'" readonly><input type="hidden" value="" name="psId"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="partItemtwoText" id="partItemtwoText'+FlowListTable.rows.length+'" readonly><input type="hidden" value="" name="operationId"></div></TD>';
		col = row.insertCell();
		col.innerHTML = ' <TD><div align="center"><input type="text" size="8" name="floor"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="7" name="unName" id="unName'+FlowListTable.rows.length+'" readonly><input type="hidden" value="" name="unitId"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="7" name="price" id="price'+FlowListTable.rows.length+'" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="realQuantity"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="wageSum" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="accQuantity" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<TD><div align="center"><input type="text" size="8" name="jjykl" readonly></div></TD>';
		col = row.insertCell();
		col.innerHTML = ' <TD><div align="center"><input type="hidden" size="8" name="ykl" readonly><input type="text" size="15" name="remark"></div></TD>';
		col = row.insertCell();
		col.innerHTML = '<a id="FlowListLabel" style="color:blue;cursor:hand;text-decoration:underline" onclick=\'delCurrentRow(this)\'>删除</a>';
		rowcount++;
		if (!newAddMissionTree(customers))
			FlowListTable.deleteRow(FlowListTable.rows.length - 1);
	}
	function delCurrentRow(obj)
	{
		var tr = obj.parentNode.parentNode;
		rowcount = rowcount - 1;	// 行数减一
		tr.parentNode.removeChild(tr);
		if (rowcount == 1) {
			var button = document.getElementById("clickbutton");
			button.style.display = 'none';
		}
		var contentserials = document.getElementsByName("contentSerial");
		var i=0;
		for(i=0;i<rowcount;i++){
			var serial=contentserials[i];
			serial.value = i + 1;
		}
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
	
	function emptytable(tableObj) {
		var table = tableObj;
		for (var j = table.rows.length; j > 1; j--) {
			table.deleteRow(table.rows.length - 1);
			rowcount = rowcount - 1;
		}
		var button = document.getElementById("clickbutton");
		button.style.display = 'none';
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
	function isOverfour(strname)
	{	
		if(strname.toString().split(".")[1]!=null&&strname.toString().split(".")[1].length>4)
			return true
		else 
			return false
	}
	function validate()
	{
		var flist=document.getElementsByName("floor");
		var wageSum=document.getElementsByName("wageSum");
		var rqlist=document.getElementsByName("realQuantity");
		var remarklist=document.getElementsByName("remark");	
		var cansubmit=document.getElementsByName("cansubmit")[0].value;

			for(i=0;i<wageSum.length;i++)
			{
				if(wageSum[i].value==0&&rqlist[i].value!=0)
				{
					alert("第"+(i+1)+"行工资金额 为0！")
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
			for(i=0;i<rqlist.length;i++)
			{
				if(isEmpty(rqlist[i].value)||!isNumber(rqlist[i].value))
				{
					alert("第"+(i+1)+"行实际工程量为空或者格式不是数字！")
					return false;
				}
				if(rqlist[i].value<0)
				{					
					if(remarklist[i].value=="")
					{			
						alert("第"+(i+1)+"行实际工程量为负，备注必须填写！");
						return false;
					}
				}
				if(isOverfour(rqlist[i].value)){
					alert("第"+(i+1)+"行实际工程量超过四小数！");
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
			var jjykllist=document.getElementsByName("jjykl");
			var ykllist=document.getElementsByName("ykl");
			var acclist=document.getElementsByName("accQuantity");
			for(var j=0;j<rqlist.length;j++)
			{	
				if(parseFloat(rqlist[j].value)>0&&parseFloat(ykllist[j].value)>0){
				if(parseFloat(rqlist[j].value)+parseFloat(jjykllist[j].value)>parseFloat(ykllist[j].value))
					{										
						var ce=parseFloat(rqlist[j].value)+parseFloat(jjykllist[j].value)-parseFloat(ykllist[j].value);					
						alert("第"+(j+1)+"行使已开量超预控量"+ce.toFixed(2)+"，请与审计联系调整预控量，或自己调整工程量");
						return false;
					}
				if(parseFloat(rqlist[j].value)+parseFloat(acclist[j].value)>parseFloat(ykllist[j].value))
					{						
						var ce1=parseFloat(rqlist[j].value)+parseFloat(acclist[j].value)-parseFloat(ykllist[j].value);
						alert("第"+(j+1)+"行使已结量超预控量"+ce1.toFixed(2)+"，请与审计联系调整预控量，或自己调整工程量");
						return false;
					}
				}
			}
			
			return true;
	}
	function upload() {
		var loginname = document.getElementsByName("loginname")[0].value;
		window.location.href = "${pageContext.request.contextPath }/preUploadFile?mid="
				+ loginname;

	}
</script>

<body onload="parseXML()">
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">计件任务书</font>
				</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${error == null }">
	<form name="addmissionform" method="post" action="addJJMission" onSubmit="">
		<input type="hidden" name="loginname" id="loginname" value="${user.userLoginName }" />
		<input type="hidden" name="tableLength" id="tableLength" value="" />
		<input type="hidden" name="mtid" id="mtid" value="${mtid }" />
		<input type="hidden" name="mtcode" id="mtcode" value="${mtcode }" />
		<input type="hidden" name="cansubmit" id="cansubmit" value="" />
		<table id="addmission" class="table table-striped table-bordered table-condensed">
			<tr>
				<td width="30%">&nbsp;&nbsp;
				工程名称 : <select style="width:150px;height:30px;line-height:35px;" id="prId" name="prId" onchange="updateother();emptytable(customers);"></select>
				</td>
				<td width="20%">&nbsp;&nbsp;
				栋号 : <select style="width:120px;height:30px;line-height:35px;" id="unitnumber" name="unitnumber" onchange="emptytable(customers);"></select>
				</td>
				<td width="50%">&nbsp;&nbsp;
				结算单位 : <select style="width:120px;height:30px;line-height:35px;" id="cuId" name="cuId" onchange="emptytable(customers);"></select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;
		   	   	工作月度 : <select style="width:100px;height:30px;line-height:35px;" id="styear" name="styear">
							<option value="${submityear}">${submityear }</option>
							<option value="${submityear - 1 }">${submityear - 1 }</option>
							<option value="${submityear}">${submityear }</option>
							<option value="${submityear + 1 }">${submityear + 1 }</option>
							<option value="${submityear + 2 }">${submityear + 2 }</option>
							<option value="${submityear + 3 }">${submityear + 3 }</option>
							<option value="${submityear + 4 }">${submityear + 4 }</option>
							<option value="${submityear + 5 }">${submityear + 5 }</option>
							<option value="${submityear + 6 }">${submityear + 6 }</option>
							<option value="${submityear + 7 }">${submityear + 7 }</option>
						</select> 年
						<select style="width:100px;height:30px;line-height:35px;" id="stmonth" name="stmonth">
							<option value="${submitmonth }">${submitmonth }</option>
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
				<td colspan=2>&nbsp;&nbsp;
				<a onclick="window.open('preUploadFile?mid=${user.userLoginName }','添加附件',
				'width=800,height=650,toolbar=no,scrollbars=anto,menubar=no,top=50,left=200')">添加附件</a></td>
			</tr>
		</table>
		<table id="customers">
			<tr>
				<th style="width: 4%">序号</th>
				<th style="width: 7%">分部</th>
				<th style="width: 8%">工程部位</th>
				<th style="width: 10%">工作项目</th>
				<th style="width: 13%">具体部位</th>
				<th style="width: 4%">单位</th>
				<th style="width: 6%">单价</th>
				<th style="width: 10%">本月实际完成工程量</th>
				<th style="width: 8%">工资金额</th>
				<th style="width: 8%">已结累计工程量</th>
				<th style="width: 8%">已开工程量</th>
				<th style="width: 11%">备注</th>
				<th style="width: 3%">
				<a id="FlowListLabel" style="color:blue;cursor:hand;text-decoration:underline" onclick="addNewContractRow(customers)">新增</a>
				</th>
			</tr>
		</table>
		<br>
		<br>
		<div style="text-align:center; width:100%;">
		<div id="clickbutton" style="display:none;">
		<input type="button" value="提交" class="easyui-linkbutton" onclick="if(validate())addmissionform.submit();" />
		<input onclick="{window.location.href='missionSubmitList'}" type="button" value="取消" class="easyui-linkbutton" iconCls="icon-ok">
		</div>
	</form>
	</c:if>
	<span style="color:red; font-weight: bold">${error }</span>
</body>
</html>
