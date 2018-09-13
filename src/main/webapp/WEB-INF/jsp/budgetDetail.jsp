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
<title>包含单价信息列表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">

input[type="text"] {
	width: 200px;
	height: 30px;
}
</style>

<script type="text/javascript">
$(document).ready(function(){

$("#addBudgetContent").submit(function(){
if(validate())addBudgetContent();
})

})

function addBudgetContent(){
    var postdata = $("#addBudgetContent").serialize();
    console.log(postdata);
    $.ajax({  
                
                type:"POST",  
                url:"budget/addBudgetContent1", 
                dataType:"text", 
                data:postdata,
                async: false,
                success:function(data){
                   console.log(data);
                   if(data=="success"){
                            location.reload();
                           }else{alert("新建失败");}	
                           },  
                error:function(data) {  
                           console.log(data);
                           alert("新建失败了："+data);  
                           }  
                         });


}

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
       var account=$("#account").val();
       var remark=$("#remark").val();
       
       if(isEmpty(account)){
          alert("请填写预算变更量");
          return false;          
       }
       if(!isNumber(account)){
          alert("预算变更量必须为数字");
          return false;          
       }
       if(remark.length>250){
          alert("备注超出范围");
          return false;          
       }
       
       return true;
    }
//action="budget/addBudgetContent" method="post"
</script>
</head>

<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">包含单价信息列表</font>
				</td>
			</tr>
		</tbody>
</table>
	
   <form id="addBudgetContent" name="addBudgetContent" >
		
		<input type="hidden" id="bId" name="bId" value="${budget.bId}">
		
		<!-- <input type="hidden" name="_method" value="put"/> -->
		<table id="viewMission">								
			<tr>
					<th width=15%>工程名称</th>
			        <th width=10%>栋号</th>			        
			        <th width=10%>分部</th>
			        <th width=15%>预算工程部位</th>
			        <th width=15%>预算自定义名称</th>			       
			        <th width=10%>单位</th>
			        <th width=10%>预算工程量</th>
			        <th width=15%>备注</th>
			  </tr>
			  <tr>
			  <td><c:out value="${budget.prName}"></c:out></td>
			  <td><c:out value="${budget.unitNumber}"></c:out></td>
			  <td><c:out value="${budget.pName}"></c:out></td>
			  <td><c:out value="${budget.budgetPos}"></c:out></td>
			  <td><c:out value="${budget.budgetName}"></c:out></td>
			  <td><c:out value="${budget.unName}"></c:out></td>
			  <td><c:out value="${budget.budgetAccount}"></c:out></td>
			  <td><c:out value="${budget.remark}"></c:out></td>
			
			  </tr>
       </table>

	<table id="yustable">
	    <tr>
	                <th width=15%>编号</th>
			        <th width=20%>工程量</th>			       
			        <th width=20%>预算员</th>
			        <th width=25%>预算时间</th>
			        <th width=20%>备注</th>
	    </tr>
	    <c:forEach items="${bcList}" var="item">
		<tr >
		<td><c:out value="${item.serial}"></c:out></td>
		<td><c:out value="${item.account }"/></td>
		<td><c:out value="${item.userName}"></c:out></td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.budgetTime}"/></td>
		<td><c:out value="${item.remark}"></c:out></td>
		</tr>                                  
		</c:forEach>		
		</table>
		<br>
		预算变更量（为数字）：
		<input type="text" id="account" name="account">
		备注：<input type="text" id="remark" name="remark"  style="height: 103px; width: 182px; ">
		<input type="submit" value="新建清单" class="easyui-linkbutton">
	</form>
	<div style="text-align:center">
		<input type="button" onclick="window.location.href='/cyjz_new/budget/toManageBudget';" value="返回上一页" class="easyui-linkbutton">
	</div>
</body>
</html>
