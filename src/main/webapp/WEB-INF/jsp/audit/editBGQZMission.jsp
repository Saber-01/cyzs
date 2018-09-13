<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改变更签证任务书</title>
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
setInterval("jswagesum()",500);
var rowcount;
	$(document).ready(function() {
		$("#dd").datebox({
			required : true,
			onSelect : function(date) {
				$("#dd").val(date);
			}
		});
		
		rowcount = document.getElementById("viewMission").rows.length;
		if (rowcount > 5) {
			var button = document.getElementById("clickbutton");
			button.style.display = 'inline-block';
		}
	});

	function editMission(obj)
	{
		var tr = obj.parentNode.parentNode.rowIndex;
		var index = tr - 4;
		//找出工程名称
		var prId=document.getElementById("prId").value;
	    var prName=document.getElementById("prName").value;
		//找出栋号
		var pcpId=document.getElementById("pcpId").value;
		var unitNumber=document.getElementById("unitNumber").value;
		//找出结算单位
		var cuId=document.getElementById("cuId").value;
		var cuName=document.getElementById("cuName").value;
		//找出任务书类型
		var mtCode=document.getElementById("mtCode").value;
		var partId=document.getElementsByName("pId")[index].value;
		var psId=document.getElementsByName("psId")[index].value;
		var oId=document.getElementsByName("oId")[index].value;
		var floor=document.getElementsByName("floor")[index].value;
		var realQuantity=document.getElementsByName("realQuantity")[index].value;
		var remark=document.getElementsByName("remark")[index].value;
		var price=document.getElementsByName("price")[index].value;
		//找出单位
		var unitId=document.getElementsByName("unId")[index].value;
		var wageSum=document.getElementsByName("wageSum")[index].value;
		var accQuantity="";
		if(mtCode == "1" || mtCode == "7")//只有计件任务书和合同内其他任务书才有accQuantity
			accQuantity=document.getElementsByName("accQuantity")[index].value;
		
		window.open("preEditMission?index=" + index + "&prId=" + prId + "&prName=" + prName + "&pcpId=" + pcpId + 
				 "&unitNumber=" + unitNumber + "&cuId=" + cuId + "&cuName=" + cuName + "&mtCode=" + mtCode + "&partId=" +partId +"&psId=" + psId + "&oId=" + oId +
				 "&floor=" + floor + "&realQuantity=" + realQuantity + "&remark=" + remark + "&price=" + price + "&wageSum=" + wageSum + 
				 "&accQuantity=" + accQuantity+"&unitId="+unitId,
				"修改任务书","width=800,height=650,toolbar=no,scrollbars=anto,menubar=no,top=50,left=200");	
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
	function emptytable(tableObj) {
		var table = tableObj;
		//alert(table.rows.length);
		for (var j = table.rows.length; j > 1; j--) {
			table.deleteRow(table.rows.length - 1);
		}
		var button = document.getElementById("clickbutton");
		button.style.display = 'none';
	}
	function validate()	// 验证格式
	{
		var flist=document.getElementsByName("floor");
		var prlist=document.getElementsByName("price");
		var rqlist=document.getElementsByName("realQuantity");
		var remarklist=document.getElementsByName("remark");
		var wageSum=document.getElementsByName("wageSum");
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
		//ls 验证负数必须填写备注
		for(i=0;i<rqlist.length;i++)
		{
			if(isEmpty(rqlist[i].value)||!isNumber(rqlist[i].value))
			{
				alert("第"+(i+1)+"行当日实际用工数量为空或者格式不是数字！")
				return false;
			}
			if(rqlist[i].value<0)
			{	
				if(remarklist[i].value=="")
				{			
					alert("第"+(i+1)+"行当日实际用工数量为负，备注必须填写！");
					return false;
				}
			}
			if(isOverfour(rqlist[i].value)){
				alert("第"+(i+1)+"行当日实际用工数量超过四小数！");
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
		return true;
	}
	
	function delCurrentRow(obj)
	{
		var tr = obj.parentNode.parentNode;
		tr.parentNode.removeChild(tr);
		rowcount = rowcount - 1;
		if (rowcount == 5) {
			var button = document.getElementById("clickbutton");
			button.style.display = 'none';
		}
	}
	
	function touploadFile(mid, mCode) {
		window.open("preUploadFile?up=1" + "&mid=" + mid + "&mCode=" + mCode, '添加附件',
						'width=800,height=650,toolbar=no,scrollbars=anto,menubar=no,top=50,left=200')
	}
	function refreshWin() {
		window.location.reload();
	}
	
</script>

</head>

<body>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">修改变更签证任务书</font>
				</td>
			</tr>
		</tbody>
	</table>
		<form name="editmissionform" method="post" action="editMission">
		<input type="hidden" name="mId" value="${mission.mId}" id="mId"/> 
			<input type="hidden" name="mtCode" value="${mission.mtCode}" id="mtCode"/>
			<input type="hidden" value="${mission.prName}" name="prName" id="prName">
			<input type="hidden" value="${mission.prId}" name="prId" id="prId">
			<input type="hidden" value="${mission.unitNumber}" name="unitNumber" id="unitNumber">
			<input type="hidden" value="${mission.pcPId}" name="pcpId" id="pcpId">
			<input type="hidden" value="${mission.cuName}" name="cuName" id="cuName">
			<input type="hidden" value="${mission.cuId}" name="cuId" id="cuId">
			
			<table id="viewMission">
				<tr>
					<td colspan="14">
						<div align="center">变 更 签 证 任 务 书 详 细 信 息</div>
						<div align="right">
							任务书编号 : ${mission.mCode}&nbsp;&nbsp; </div>
					</td>
				</tr>
				<tr>
					<td colspan="14">
						工程名称 : ${mission.prName}&nbsp;&nbsp;&nbsp;&nbsp; 
						栋号 : ${mission.unitNumber}&nbsp;&nbsp;&nbsp;&nbsp; 

					</td>
				</tr>
				<tr>
					<td colspan="14">
						工作日期 : <fmt:formatDate value="${mission.beginDate}" pattern="yyyy-MM-dd "/>&nbsp;&nbsp;&nbsp;&nbsp;
						结算单位 : ${mission.cuName}&nbsp;&nbsp;&nbsp;&nbsp;
						<a onclick="touploadFile('${mission.mId}', '${mission.mCode}')">
							<font color="#0000ff">附件</font></a>
					</td>
				</tr>
						
				<tr>
					<th width="4%">
						序号
					</th>
					<th width="4%">
						内编号
					</th>
					<th width="7%">
						分部
					</th>
					<th width="10%">
						工程部位
					</th>

					<th width="10%">
						工作项目
					</th>
					<th width="12%">
						具体部位
					</th>
					<th width="6%">
						单位
					</th>
					<th width="8%">
						单价
					</th>
					<th width="8%">
						工程量
					</th>
					<th width="8%"> 
						工资金额
					</th>
					<th width="7%"> 
						资料是否返回
					</th>
					<th width="10%">
						备注
					</th>
					<th colspan=2 width="6%">
						操作
					</th>
				</tr>
								
				
				
      <c:forEach items="${missionContentList}" var="mcl">
        <tr>
            <td align="center">
            <input type="text" size="1" name="mcCode" readonly value="${mcl.mcCode }"/>
            </td>
            <td align="center">
            <input type="text" size="1" name="innerId" style="color:red" value="${mcl.innerId }"/>
            </td>
            <td align="center">
            <input type="hidden" value="${mcl.pId}" name="pId" id="pId">
            <input type="text" size="1" name="pName" readonly value="${mcl.pName }"/>
            </td>
            <td align="center">
            <input type="hidden" value="${mcl.psId}" name="psId" id="psId">
            <input type="text" size="5" name="psName" readonly value="${mcl.psName }"/>
            </td> 
            <td align="center">
            <input type="hidden" value="${mcl.oId}" name="oId" id="oId">
            <input type="text" size="5" name="oName" readonly value="${mcl.oName }"/>
            </td> 
            <td align="center">
            <input type="text" size="5" name="floor" value="${mcl.floor }"/>
            </td>  
             <td align="center">
             <input type="hidden" value="${mcl.unId}" name="unId" id="unId">
            <input type="text" size="5" name="unName" readonly value="${mcl.unName }"/>
            </td> 
             <td align="center">
            <input type="text" size="5" name="price" value="${mcl.price }"/>
            </td> 
             <td align="center">
            <input type="text" size="5" name="realQuantity" value="${mcl.realQuantity }"/>
            </td> 
             <td align="center">
            <input type="text" size="5" name="wageSum" readonly value="${mcl.wageSum }"/>
            </td> 
             <td align="center">
            <input type="text" size="5" name="isReturn" readonly value="${mcl.isreturn }"/>
            </td> 
             <td align="center">
            <input type="text" size="5" name="remark" value="${mcl.remark }"/>
            </td>
            <td align="center">
            <a href="javascript:void(0)" onclick='editMission(this)'>修改</a>
            </td> 
            <td align="center">
            <a href="javascript:void(0)" onclick='delCurrentRow(this)'>删除</a>
            </td>                          
	   </tr>
    </c:forEach>
					<tr>
						<td colspan="14">
							<div align="left">
							审核修改意见 : ${auditInfoList}
							<input type="hidden" value="${auditInfoList}" name="auditComment" id="auditComment"></div>
						</td>
						
					</tr>	
			</table>
			</form>
		
		<div style="text-align:center; width:100%;">
		<div id="clickbutton" style="display:none;">
			<input type="button" value="提交" class="easyui-linkbutton" iconCls="icon-ok" onclick="if(validate()) editmissionform.submit();" />
		</div>
		<input onclick="window.history.go(-1);" type="button" value="返回" class="easyui-linkbutton" iconCls="icon-ok">
		</div>

</body>
</html>