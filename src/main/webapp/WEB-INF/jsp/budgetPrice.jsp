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
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- <style type="text/css">
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
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background: #efefef;
	font-weight: bold;
	color: #323D53;
}

#customers tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}

#yustable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#yustable td, #yustable th {
	font-size: 1em;
	border: 1px solid #e3e3e3;
	padding: 3px 7px 2px 7px;
}

#yustable th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background: #efefef;
	font-weight: bold;
	color: #323D53;
}

#yustable tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}

input[type="text"] {
width:auto
}
</style> -->

<script type="text/javascript">
$(document).ready(function(){

$("#deletePriceFromBudget").submit(function(){
if(validate())deletePriceFromBudget();
})

})

function deletePriceFromBudget(){
    var postdata = $("#deletePriceFromBudget").serialize();
    console.log(postdata);
    $.ajax({  
                
                type:"POST",  
                url:"budget/deletePriceFromBudget", 
                dataType:"text", 
                data:postdata,
                async: false,
                success:function(data){
                   console.log(data);
                   if(data=="success"){
                            location.reload();
                           }else{alert("删除失败");}	
                           },  
                error:function(data) {  
                           console.log(data);
                           alert("删除失败了："+data);  
                           }  
                         });


}


function checkAll()
{
var name="isAdd";
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = true;
}
}
sum();
}

function clearAll()
{
var name="isAdd";
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = false;
}
}
sum();
}

	function sum(){
		var sumykl=0;
		var sumysl=0;
		$("[name=isAdd]:checked").each(
			function()
			{	 
					$currentSelected=$(this);
					$row=$currentSelected.parent('td').parent('tr');				
					sumykl+=parseFloat($row.find('td:eq(8)').text());
					sumykl=parseFloat(sumykl.toFixed(2));
					sumysl+=parseFloat($row.find('td:eq(9)').text());
					sumysl=parseFloat(sumysl.toFixed(2));
			}	
		)
		$("#yklsum").html(sumykl);
		$("#yslsum").html(sumysl);		
	}
	
function isAllFalse(name){
  var input=document.getElementsByName(name);
      for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			if(input[i].checked ==true){
      			return false;
      			}
      		}
  		}  		
  	return true;
}

 function validate(){
    if(isAllFalse("isAdd")){
       alert("请选择要删除的对象！");
       return false;
     }
 return true;
 }
//action="budget/deletePriceFromBudget" method="post"
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

		<form id="deletePriceFromBudget" name="deletePriceFromBudget" >
		
		
		
		<!-- <input type="hidden" name="_method" value="put"/> -->
		<table id="viewMission">
			<tr>				    
					<th width=10%>工程名称</th>
			        <th width=8%>栋号</th>
			        <th width=8%>结算单位</th>
			        <th width=10%>分部</th>
			        <th width=10%>分项1</th>
			        <th width=10%>分项2</th>			        
			        <th width=4%>单位</th>
			        <th width=6%>单价</th>
			        <th width=10%>计件已开工程量</th>
			        <th width=10%>计件已审工程量</th>
			        <th width=9%>所属预算分类</th>
			        <th width=5%>操作</th>
			  </tr>
	    <c:forEach items="${cList}" var="item">
		<tr >
		<td><c:out value="${item.prName}"></c:out></td>
		<td><c:out value="${item.pcpNumber}"></c:out></td>
		<td><c:out value="${item.cuName}"></c:out></td>
		<td><input type='hidden' value="${item.pId }"><c:out value="${item.pName }"></c:out></td>
		<td><c:out value="${item.psName }"></c:out></td>
		<td><c:out value="${item.jobName }"></c:out></td>		
		<td><input type='hidden' value="${item.unId }"><c:out value="${item.unName }"/></td>
		<td><c:out value="${item.price }"/></td>
		<td><c:out value="${item.ykl }"/></td>
		<td><c:out value="${item.ysl }"/></td>
		<td><c:out value="${item.budgetName }"/></td>
		<td><input type='checkbox' class='acheck' id='isAdd' name='isAdd' value="${ item.opId}" onclick='sum()'>选择</td>
		</tr>                                  
		</c:forEach>
		<tr bgcolor='white'>
		<td>合计</td>
		<td></td><td>
		</td><td></td><td></td>
		<td></td><td></td><td id='yklsum'></td><td id='yslsum'>
		<td colspan='3'>
		<input type='button' value='全选'  onclick='checkAll()' class="easyui-linkbutton">
		<input type='button' value='取消' onclick='clearAll()' class="easyui-linkbutton">
		<input type="submit" value="移除所选单价" class="easyui-linkbutton"></td></tr>
		</table>		
	</form>
	<div style="text-align:center">
		<input type="button" onclick="window.location.href='/cyjz_new/budget/toManageBudget';" value="返回上一页" class="easyui-linkbutton">
	</div>
	
</body>
</html>
