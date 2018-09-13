<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>新建预算表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
a {
	color: #0000ff;
	font-size: 0.8em;
	text-decoration: underline;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
paging();
   $("#prId").bind('input propertychange',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);         }
         else{  $("#pcpId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpId").append(option1);}
  });
     
   $("#pcpId").bind('input propertychange',function () { 
    var prId =$("#prId").val();
    var pcpId = $("#pcpId").val();
    var cuId = $("#cuId").val(); 
    if(pcpId!= undefined && pcpId!= null && pcpId!='' && prId!= null && prId!='')
    {              changePart(prId,pcpId,cuId);         }
       else{  $("#pId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pId").append(option1)}
  });
  
   $("#budgetAccount").bind('input propertychange',function(){
   countdis();
  });
  
  $("#button_submit").click(function(){
     
   if(validate())
       paging();   
  });
  
   $("#delete_submit").click(function(){
    delAll();
   });
   

  
});

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

function  deleteAll()
{                
                var ids=[];            
               $("[name=isAdd]:checked").each(function(){
                    ids.push($(this).val()); //如果选中，将value添加到变量s中
                } );
                 
               if(ids!='' && ids!=null){
                alert("确定要删除吗？");
            var postdata = JSON.stringify(ids);
             $.ajax({  
                contentType: 'application/json', 
                type:"POST", 
                async:false, 
                url:"budget/deleteBudget",
                dataType:"h", 
                data:postdata,
                success:function(data){
                 if (data == "0")alert("删除失败！");
                 else { alert("删除成功！");  
                 paging();          }
                 }
                 })}
                 else{
                 layer.msg('请先选择要删除的内容');
                 } 
}
//工程与栋号级联
 function  getPcpNumber(prId)
{  
    
    var json = {"prId":prId};
     console.log(json);
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#pcpId").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpId").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.unitNumber).val(item.pcPId);
                 $("#pcpId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};
function changePart(a,b,c)
{  
    var prId=a;
    var pcpId=b;
    var cuId=c;
    var json = {"prId":prId,"pcpId":pcpId,"cuId":cuId};
    if(json!=null && json!= undefined){
    console.log(json);   
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"budget/changePart",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#pName").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#pName").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.pName).val(item.pName);
                 $("#pName").append(option);
                 })     
                },  
                error:function(e) { alert("出错"+e);                   
                }  
     });
   
   }  
}

//请求显示数据
function paging()
{
   console.log("哈哈哈");
   var prId=$("#prId").val();
   console.log(prId);
   if(prId!=null && prId!=''){
   var json = {};
   json['prId'] =$("#prId").val();;
   json['pName'] =$("#pName").val();;
   json['pcpId'] = $("#pcpId").val(); ;
   json['classOrNot'] ="yes";
   
  console.log("来打我啊，我在这儿呢");
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"budget/selectBudget", 
                dataType:"json", 
                data:postdata,
                async:false, 
                success:function(data){              
                
                if(data!=null&&""!=data){
                $("#customers tr:gt(0)").remove();               
                $.each(data, function(index,item){                    
                $("#customers").append("<tr bgcolor='white'><td>"+(index+1)+"</td><td>"+item.prName+"</td><td><input type='hidden' value='"+item.pcPId+"'>"+item.unitNumber+"</td><td algin='left'><input type='hidden' value='"+item.pId+"'>"+item.pName+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td><input type='hidden' value='"+item.unId+"'>"+item.unName+"</td><td>"+item.budgetAccount+"</td><td>"+item.remark+"</td><td>"+item.ykl+"</td><td>"+item.disc+"<td>"+item.ysl+"</td><td><a href='budget/toEditBudget/"+item.bId+"'>修改</a> <a href='budget/toBudgetPrice/"+item.bId+"'>单价</a> <a href='budget/toBudgetDetail/"+item.bId+"'>清单</a> <input type='checkbox'  id='isAdd' name='isAdd' value='"+item.bId+"' onclick='sum()'>选择</input></td></tr>");                                                                                                     
                })
                $("#customers").append("<tr bgcolor='white'><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td><td id='accountsum'></td><td></td><td id='yklsum'></td><td id='discsum'></td><td id='yslsum'></td><td colspan='2'><input type='button' value='全选'  onclick='checkAll()'><input type='button' value='取消' onclick='clearAll()'><input type='button' value='删除选择预算' onclick='deleteAll()'></td></tr>");
                }else{  $("#customers tr:gt(0)").remove(); 
                $("#customers").append(" <tr><td colspan = '13'>未查询到相应结果 或者 必选条件没有全部选择</td></tr>");}
                
                },  
                
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
               
     });
}
}

	function sum(){
	    var sumAccount=0;
		var sumykl=0;
		var sumdisc=0;
		var sumysl=0;
		$("[name=isAdd]:checked").each(
			function()
			{	 
					$currentSelected=$(this);
					$row=$currentSelected.parent('td').parent('tr');
					sumAccount+=parseFloat($row.find('td:eq(7)').text());
					sumAccount=parseFloat(sumAccount.toFixed(2));				
					sumykl+=parseFloat($row.find('td:eq(9)').text());
					sumykl=parseFloat(sumykl.toFixed(2));
					sumdisc+=parseFloat($row.find('td:eq(10)').text());
					sumdisc=parseFloat(sumdisc.toFixed(2));
					sumysl+=parseFloat($row.find('td:eq(11)').text());
					sumysl=parseFloat(sumysl.toFixed(2));
			}	
		)
		$("#accountsum").html(sumAccount);
		$("#yklsum").html(sumykl);
		$("#discsum").html(sumdisc);
		$("#yslsum").html(sumysl);	
		console.log(sumAccount+""+sumykl+""+sumdisc+""+sumysl)	
	}

	function addnewys(){
		$yusRow=$('#yustable').find('tr:eq(1)');
		var flag=0;
		var original="";
		$("[name=isAdd]:checked").each(
			function()
			{									
					$currentSelected=$(this);
					$row=$currentSelected.parent('td').parent('tr');
					if(flag==0){
					original=$row.find('td:eq(6)').text();
					flag=flag+1;
					}
					if(original!=$row.find('td:eq(6)').text()){
					  alert("单位"+original+"与单位"+$row.find('td:eq(9)').text()+"不同，不应合并到同一个预算");
						$yusRow.find('td:eq(0)').find('input').attr('value',"");
						$yusRow.find('td:eq(1)').find('input').attr('value',"");
						$yusRow.find('td:eq(2)').find('input').attr('value',"");
						$yusRow.find('td:eq(3)').find('input').attr('value',"");
					  return false;
					}else if(flag==1){
					$yusRow.find('td:eq(0)').find('input').attr('value',$row.find('td:eq(3)').text());
					$yusRow.find('td:eq(1)').find('input').attr('value',$row.find('td:eq(4)').text());
					$yusRow.find('td:eq(2)').find('input').attr('value',$row.find('td:eq(5)').text());
					$yusRow.find('td:eq(3)').find('input').attr('value',$row.find('td:eq(6)').text());
					$('#budgetPId').attr('value',$row.find('td:eq(3)').find('input').val());
					$('#budgetPcpId').attr('value',$row.find('td:eq(2)').find('input').val());			
		            $('#budgetUnId').attr('value',$row.find('td:eq(6)').find('input').val());
		            console.log($row.find('td:eq(2)').find('input').val());
		            console.log($("#budgetPId").val()+$("#budgetUnId").val()+$('#budgetPcpId').val());
					flag=flag+1;
					}else
					flag=flag+1;				
			}	
		)
		
		$yusRow.find('td:eq(4)').find('input').attr('value',$("#accountsum").text());
		$yusRow.find('td:eq(5)').find('input').attr('value',$("#yklsum").text());
		$yusRow.find('td:eq(6)').find('input').attr('value',$("#discsum").text());
		$yusRow.find('td:eq(7)').find('input').attr('value',$("#yslsum").text());
		countdis();

        
		$('#budgetPrId').attr('value',$('#prId').val());
		
        console.log($("#budgetPId").val()+$("#budgetUnId").val());
	}
	
function countdis(){
	    var dis=parseFloat($("#budgetAccount").val().trim())-parseFloat($("#ykl").attr("value"));
	    console.log(dis);
		$("#disc").attr('value',dis.toFixed(2));
}

function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else 
			return false;
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
  var prId=$("#prId").val();

     if(isEmpty(prId)){
            {
				alert("工程不能为空")
				return false;
			}
  }

  
  return true;
  
}

function validate1(){
  var budgetPos=$("#budgetPos").val();
  var budgetName=$("#budgetName").val();
  var budgetAccount=$("#budgetAccount").val();
  
     if(isEmpty(budgetPos)){
            {
				alert("预算工程部位不能为空")
				return false;
			}
  }
     if(isEmpty(budgetName)){
            {
				alert("预算自定义名称不能为空")
				return false;
			}
  }
  
   if(isEmpty(budgetAccount)){
            {
				alert("预算量不能为空")
				return false;
			}
  }
    if(isAllFalse("isAdd")){
            {
				alert("请选择要加入合并的预算")
				return false;
			}
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
				<font style="font-size:1.0em;">管理预算分类</font>
				</td>
			</tr>
		</tbody>
   </table>
   <form id="query"> 
      <table id="addmission" class="table table-striped table-bordered table-condensed">
            <tr>
                <td width="12%">工程名称（必选）</td>
                <td width="20%">
                   <select name="prId" id=prId style="width:200px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                                                 
                </td>
                 <td width="5%">栋号</td>
                <td width="15%">
                   <select name="pcpId" id=pcpId  onchange="changePart()" style="width:150px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                    </select>
                </td>              
            
                <td width="5%">分部</td>
                <td width="15%">
                   <select name="pName" id=pName style="width:150px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pName}">${r.pName}</option>
                      </c:forEach>
                    </select>                 
                </td>
                <td>
                    <input type="button" id="button_submit" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="查询">
                    <input type="reset" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="重置"/> 
                 
                </td>
            </tr>
</table>
</form>


<form id="budgetList" name="budgetList" action="budget/mergeBudget" method="post">

	 	<table id="customers">	
				<tr>
				    <th>序号</th>			        
					<th>工程名称</th>
			        <th>栋号</th>
			        <th>分部</th>
			        <th>预算工程部位</th>
			        <th>预算自定义名称</th>
			        <th>单位</th>
			        <th>预算工程量</th>
			        <th>备注</th>
			        <th>计件已开工程量</th>
			        <th>工程量差</th>
			        <th>计件已审工程量</th>			        
			        <th>操作</th>
			  </tr>
			  <tr>
			  <td colspan = "13">未查询到相应结果 或者 必选条件没有全部选择</td>			    			                   
			  </tr>
		</table><br>
	 	点击“生成新的预算分类”按钮，由上表所选单价生成新的预算分类并默认出相关初值，已分类的单价默认加入新的分类。	<br>
	 	选择单位相同的单价。
	 	<table>
				<tr>
				<td>
				<input type="button" name="" value="生成新的预算分类"  onclick="addnewys()" class="easyui-linkbutton" style="height:30px;line-height:30px;"/></td>
				</tr>
		</table>
		<br>
	 	<input type="hidden" name="budgetPrId" id="budgetPrId" value="" />
	 	<input type="hidden" name="budgetPcpId" id="budgetPcpId" value="" />
	 	<input type="hidden" name="budgetPId" id="budgetPId" value="" />
	 	<input type="hidden" name="budgetUnId" id="budgetUnId" value="" />
	 	<table id="yustable">	
				<tr>
					<th style="width:9%">分部</th>
					<th style="width:15%">预算工程部位</th>
					<th style="width:10%">预算自定义名称</th>
					<th style="width:5%">单位</th>
					<th style="width:10%">预算工程量</th>					
					<th style="width:10%">计件已开工程量</th>
					<th style="width:10%">工程量差</th>
					<th style="width:10%">计件已审工程量</th>					
					<th style="width:13%">备注</th>
					<th style="width:8%">操作</th>
				</tr>
				<tr>
					<td><input type="text" name="budgetPart" id="budgetPart" readonly></td>
					<td><input type="text" name="budgetPos" id="budgetPos"></td>
					<td><input type="text" name="budgetName" id="budgetName"></td>
					<td><input type="text" name="unit" readonly></td>
					<td><input type="text" id="budgetAccount" value="0" name="budgetAccount"></td>					
					<td><input type="text" id="ykl" value="0" name="ykl" readonly></td>
					<td><input type="text" id="disc" name="disc" readonly></td>
					<td><input type="text" name="ysl" readonly></td>					
					<td><input type="text" id="remark" name="remark"></td>					
					<td><input type="button" onclick="if(validate1())budgetList.submit();" class="easyui-linkbutton" style="height:25px;line-height:25px;" value="合并预算"></td>					
				</tr>
			</table>
			
</form>
<div id="example"></div>

</body>
</html>
