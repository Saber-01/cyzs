<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改预算</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
input[type="text"] {
	width: 200px;
	height: 30px;
	text-align: center;
	background:transparent;
}
</style>

<script type="text/javascript">

	function isNumber(strname)
	{
		var reg=/^[-\+]?\d+(\.\d+)?$/;
		return reg.test(strname);
	}
	
	function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else 
			return false;
	}
	
    function validate(){
       var account=$("#budgetAccount").val();
       var remark=$("#remark").val();
       var budgetPos=$("#budgetPos").val();
       var budgetName=$("#budgetName").val();
       
       
       if(isEmpty(account)){
          alert("预算工程量不能为空");
          return false;          
       }
       if(!isNumber(account)){
          alert("预算工程量必须为数字");
          return false;          
       }
       if(remark.length>250){
          alert("备注超出范围");
          return false;          
       }
       
        if(isEmpty(budgetPos)){
          alert("预算工程部位不能为空");
          return false;          
       }
        if(isEmpty(budgetName)){
          alert("预算名不能为空");
          return false;          
       }
       return true;
    }

</script>
</head>

<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">修改预算信息</font>
				</td>
			</tr>
		</tbody>
   </table>
	
		<form:form  name="form" action="budget/editBudget" method="post" modelAttribute="budget">
		<form:errors path="*"></form:errors>
		<br>
		<table class="kv-table" style="width:100%;height:391px;padding:10px;border:0px">
		<!-- <thead>
		<tr class="kv-label" style="width:100%;text-align:center;padding-left:40%"> 
		<td colspan="2">修改预算信息</td>
		</tr>
		</thead> -->
		<form:hidden path="bId"/>
		<!-- <input type="hidden" name="_method" value="put"/> -->
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">工程名称 : </td>
		<td class="kv-label" ><form:input path="prName"  readonly="true"/>
		<form:errors path="prId"></form:errors>
		</td>		
		</tr>
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%"> 栋号 :</td>
		<td class="kv-label"><form:input path="unitNumber" readonly="true"/>
		<form:errors path="unitNumber"></form:errors>
		</td>
		</tr>
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">分部:</td>
		<td class="kv-label"><form:input path="pName" readonly="true"/>
		<form:errors path="pId"></form:errors>
		</td>
		</tr>
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">预算工程部位:</td> 
		<td class="kv-label"><form:input path="budgetPos"/>
		<form:errors path="budgetPos"></form:errors>
		</td>
		</tr>
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">预算自定义名称:</td> 
		<td class="kv-label"><form:input path="budgetName" />
		<form:errors path="budgetName"></form:errors>
		</td>
		</tr>
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">单位:</td> 
		<td class="kv-label"><form:input path="unName" readonly="true"/>
		<form:errors path="unId"></form:errors>
		</td>
		</tr>
		
	
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">备注:</td>
		<td class="kv-label"><form:input path="remark" />
		<form:errors path="remark"></form:errors>
		</td>
		</tr>
		
		<tr>
		<td class="kv-label" style="width:40%;text-align:left;padding-left:25%">预算工程量:</td> 
		<td class="kv-label"><form:input path="budgetAccount" />
		<form:errors path="budgetAccount"></form:errors>
		</td>
		</tr>
		</table>
		<br>
		<div style="padding-left:35%">
		<input type="button" value="提交" class="easyui-linkbutton" style="height:30px;line-height:30px;" onclick="if(validate())form.submit();"/>
		<input type="reset" value="重置" class="easyui-linkbutton" style="height:30px;line-height:30px;"/>
		<input type="button" onclick="window.location.href='/cyjz_new/budget/toManageBudget';" value="返回上一页" class="easyui-linkbutton" style="height:30px;line-height:30px;"/> 
		</div>
	</form:form>
	
</body>
</html>
