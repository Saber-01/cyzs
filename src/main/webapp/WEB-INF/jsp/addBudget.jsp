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
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

$(document).ready(function(){

  var prId1=$("#prId").val();
  var pName1=$("#pName").val();
  var pcpId1=$("#pcpId").val();
 if(!(isEmpty(pcpId1))&&!(isEmpty(prId1))&&!(isEmpty(pName1)))
   {paging();}
   
   $("#prId").bind('change',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);         }
         else{  $("#pcpId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpId").append(option1);}
  });
     
   $("#pcpId").bind('change',function () { 
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
   {paging();}

  });
  
   $("#delete_submit").click(function(){
    if(confirm("确定要删除所选项吗？")&& !isAllFalse("isAdd"))delAll();
    else
    {alert("请选择要删除的对象");}
   });
   
$("#budgetList").submit(function(){
if(validate1())addBudget();
})     
});

function addBudget(){
    var postdata = $("#budgetList").serialize();
    console.log(postdata);
    $.ajax({  
                
                type:"POST",  
                url:"budget/addBudget", 
                dataType:"text", 
                data:postdata,
                async: false,
                success:function(data){
                   console.log(data);
                   if(data=="success"){
                             location.reload([bForceGet]);
                           }else{alert("新建失败");}	
                           },  
                error:function(data) {  
                           console.log(data);
                           alert("新建失败了："+data);  
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
   
   var json = {};
   json['prId'] =$("#prId").val();;
   json['pName'] =$("#pName").val();;
   json['psId'] = $("#psId").val(); ;
   json['pcpId'] = $("#pcpId").val(); ;
   json['unName'] = $("#unName").val(); ;
   json['cuId'] = $("#cuId").val(); ;
   json['classOrNot'] = $("#classOrNot").val(); ;
   
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"budget/selectPrice", 
                dataType:"json", 
                data:postdata,
                async:false, 
                success:function(data){              
                
                if(data!=null){
                $("#customers tr:gt(0)").remove();               
                $.each(data, function(index,item){                    
                $("#customers").append("<tr bgcolor='white'><td>"+(index+1)+"</td><td>"+item.prName+"</td><td>"+item.pcpNumber+"</td><td>"+item.cuName+"</td><td algin='left'><input type='hidden' value='"+item.pId+"'>"+item.pName+"</td><td>"+item.psName+"</td><td>"+item.jobName+"</td><td><input type='hidden' value='"+item.unId+"'>"+item.unName+"</td><td>"+item.price+"</td><td>"+item.ykl+"</td><td>"+item.ysl+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td><input type='checkbox' class='acheck' id='isAdd' name='isAdd' value='"+item.opId+"' onclick='sum()'>选择</input></td></tr>");                                                                                                     
                })
                $("#customers").append("<tr bgcolor='white'><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td id='yklsum'></td><td id='yslsum'></td><td></td><td colspan='2'><input type='button' value='全选'  onclick='checkAll()'><input type='button' value='取消' onclick='clearAll()'></td></tr>");
                }
                
                },  
                
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
     });

}

	function sum(){
		var sumykl=0;
		var sumysl=0;
		$("[name=isAdd]:checked").each(
			function()
			{	 
					$currentSelected=$(this);
					$row=$currentSelected.parent('td').parent('tr');				
					sumykl+=parseFloat($row.find('td:eq(9)').text());
					sumykl=parseFloat(sumykl.toFixed(2));
					sumysl+=parseFloat($row.find('td:eq(10)').text());
					sumysl=parseFloat(sumysl.toFixed(2));
			}	
		)
		$("#yklsum").html(sumykl);
		$("#yslsum").html(sumysl);	
		
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
					original=$row.find('td:eq(7)').text();
					flag=flag+1;
					}
					if(original!=$row.find('td:eq(7)').text()){
					  alert("单位"+original+"与单位"+$row.find('td:eq(7)').text()+"不同，不应合并到同一个预算");
						$yusRow.find('td:eq(0)').find('input').attr('value',"");
						$yusRow.find('td:eq(1)').find('input').attr('value',"");
						$yusRow.find('td:eq(2)').find('input').attr('value',"");
						$yusRow.find('td:eq(3)').find('input').attr('value',"");
					  return false;
					}else if(flag==1){
					$yusRow.find('td:eq(0)').find('input').attr('value',$row.find('td:eq(4)').text());
					$yusRow.find('td:eq(1)').find('input').attr('value',$row.find('td:eq(5)').text());
					$yusRow.find('td:eq(2)').find('input').attr('value',$row.find('td:eq(6)').text());
					$yusRow.find('td:eq(3)').find('input').attr('value',$row.find('td:eq(7)').text());
					$('#budgetPId').attr('value',$row.find('td:eq(4)').find('input').val());
		            $('#budgetUnId').attr('value',$row.find('td:eq(7)').find('input').val());
		            console.log($row.find('td:eq(7)').find('input').val());
		            console.log($("#budgetPId").val()+$("#budgetUnId").val());
					flag=flag+1;
					}else
					flag=flag+1;				
			}	
		)
		
		$yusRow.find('td:eq(5)').find('input').attr('value',$("#yklsum").text());
		$yusRow.find('td:eq(7)').find('input').attr('value',$("#yslsum").text());
		countdis();

        
		$('#budgetPrId').attr('value',$('#prId').val());
		$('#budgetPcpId').attr('value',$('#pcpId').val());
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
  var pName=$("#pName").val();
  var pcpId=$("#pcpId").val();
  
     if(isEmpty(pcpId)){
            {
				alert("栋号不能为空")
				return false;
			}
  }
     if(isEmpty(prId)){
            {
				alert("工程不能为空")
				return false;
			}
  }
  
   if(isEmpty(pName)){
            {
				alert("分部不能为空")
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
				alert("请选择要加入预算的单价")
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
				<font style="font-size:1.0em;">新建预算分类</font>
				</td>
			</tr>
		</tbody>
  </table>
   <form id="query">
   		<table id="addmission" class="table table-striped table-bordered table-condensed">
            <tr>
                <td>工程名称（必选）</td>
                <td>
                   <select name="prId" id=prId>
                      <option value="">---请选择---</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                                                 
                </td>
                
                 <td>栋号（必选）</td>
                <td>
                   <select name="pcpId" id=pcpId  onchange="changePart()">
                      <option value="">---请选择---</option>
                    </select>
            
                </td>
                 <td>结算单位</td>
                <td>
                   <select name="cuId" id=cuId>
                      <option value="">---请选择---</option>
                     <c:forEach items="${cuList}" var="r">
                        <option value="${r.cuId}">${r.cuName}</option>
                      </c:forEach>
                    </select>
                </td>
                
               
              
              <tr>
                <td>分部（必选）</td>
                <td>
                   <select name="pName" id=pName>
                      <option value="">---请选择---</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pName}">${r.pName}</option>
                      </c:forEach>
                    </select>                 
                </td>
                <td>
    			是否已分类</td>
    			<td>
						<select name="classOrNot" id="classOrNot">
							<option value="">
								---请选择---
							</option>
							<option value="yes">
								已分类单价
							</option>
							<option value="no">
								未分类单价
							</option>
						</select> 
    		</td>
                <td colspan="2">
                    <input type="button" id="button_submit" class="easyui-linkbutton" style="height:25px;line-height:25px;" value="查询">
                    <input type="reset" class="easyui-linkbutton" style="height:25px;line-height:25px;" value="重置"/> 
                 
                </td>
            </tr>
</table>
</form>


<form name="budgetList" id="budgetList" >				 
	 	<table id="customers">		
				<tr>
				    <th>序号</th>
					<th>工程名称</th>
			        <th>栋号</th>
			        <th>结算单位</th>
			        <th>分部</th>
			        <th>分项1</th>
			        <th>分项2</th>
			        <th>单位</th>
			        <th>单价</th>
			        <th>计件已开工程量</th>
			        <th>计件已审工程量</th>
			        <th>预算工程部位</th>
			        <th>预算自定义名称</th>
			        <th>操作</th>
			  </tr>
			  <tr>
			  <td colspan = "14">未查询到相应结果 或者 必选条件没有全部选择</td>			    			                   
			  </tr>
		</table><br>
	 	点击“生成新的预算分类”按钮，由上表所选单价生成新的预算分类并默认出相关初值，已分类的单价默认加入新的分类。	<br>
	 	选择单位相同的单价。
	 	<table>
			<tr>
				<td>
				<input type="button" value="生成新的预算分类" class="easyui-linkbutton" style="height:30px;line-height:30px;" onclick="addnewys()"/></td>
			</tr>
		</table>
		<br>
	 	<input type="hidden" name="budgetPrId" id="budgetPrId" value="" />
	 	<input type="hidden" name="budgetPcpId" id="budgetPcpId" value="" />
	 	<input type="hidden" name="budgetPId" id="budgetPId" value="" />
	 	<input type="hidden" name="budgetUnId" id="budgetUnId" value="" />
	 	<table id="yustable" >	
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
					<td><input type="text" id="budgetPos" name="budgetPos" id="budgetPos"></td>
					<td><input type="text" id="budgetName" name="budgetName" id="budgetName"></td>
					<td><input type="text" name="unit" readonly></td>
					<td><input type="text" id="budgetAccount" value="0" name="budgetAccount"></td>					
					<td><input type="text" id="ykl" value="0" name="ykl" readonly></td>
					<td><input type="text" id="disc" name="disc" readonly></td>
					<td><input type="text" name="ysl" readonly></td>					
					<td><input type="text" id="remark" name="remark"></td>					
					<td>
					<input type="submit" value="新建预算" class="easyui-linkbutton" style="height:25px;line-height:25px;" >
					</td>
					
				</tr>
			</table>
			
		</form>
<div id="example"></div>

</body>
</html>
