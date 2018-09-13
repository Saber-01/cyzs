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
<script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.printarea.js"></script> 
<script type="text/javascript" src="<%=path%>/js/excelExport.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 
<script type="text/javascript">

$(document).ready(function(){
paging();
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
    var prId =$("#prId").val();
    var pcpId = $("#pcpId").val();
    var pName = $("#pName").val(); 
   if(prId!= null && prId!='')
   {paging();}
   else{alert("必选条件必须全部选择")}
  });
  
   $("#delete_submit").click(function(){
    delAll();
   });
   
  $("#test1").on('click', function(){
  layer.open({
  type: 1,
  area: ['600px', '360px'],
  shadeClose: true, //点击遮罩关闭
  content: '\<\div style="padding:20px;">自定义内容\<\/div>'
  });
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
                $("#customers").append("<tr bgcolor='white'><td>"+(index+1)+"</td><td>"+item.prName+"</td><td>"+item.unitNumber+"</td><td algin='left'><input type='hidden' value='"+item.pId+"'>"+item.pName+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td><input type='hidden' value='"+item.unId+"'>"+item.unName+"</td><td>"+item.remark+"</td><td>"+item.budgetAccount+"</td><td>"+item.ykl+"</td><td>"+item.disc+"<td>"+item.ysl+"</td></tr>");                                                                                                     
                })            
                }else{  $("#customers tr:gt(0)").remove(); 
                $("#customers").append(" <tr><td colspan = '12'>未查询到相应结果 或者 必选条件没有全部选择</td></tr>");}
                
                },  
                
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
               
     });
}
}

</script> 

</head>
  <body>
	<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">预算与已开量对比</font>
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
                
                 <td>栋号</td>
                <td>
                   <select name="pcpId" id=pcpId  onchange="changePart()">
                      <option value="">---请选择---</option>
                    </select>
            
                </td>              
            
                <td>分部</td>
                <td>
                   <select name="pName" id=pName>
                      <option value="">---请选择---</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pName}">${r.pName}</option>
                      </c:forEach>
                    </select>                 
                </td>
                <td>
                    <input type="button" id="button_submit" value="查询" class="easyui-linkbutton" style="height:30px;line-height:30px;">
                    <input type="reset" value="重置" class="easyui-linkbutton" style="height:30px;line-height:30px;"> 
                    <input type="button" onclick="excelExport('customers');" value="导出到Excel" class="easyui-linkbutton" style="height:30px;line-height:30px;"> 
                </td>
            </tr>
	</table>
</form>


<form id="budgetList" action="budget/mergeBudget" method="post">

	 	<table id="customers">		
				<tr>
				    <th>序号</th>			        
					<th>工程名称</th>
			        <th>栋号</th>
			        <th>分部</th>
			        <th>预算工程部位</th>
			        <th>预算自定义名称</th>
			        <th>单位</th>			       
			        <th>备注</th>
			        <th>预算工程量</th>
			        <th>计件已开工程量</th>
			        <th>工程量差</th>
			        <th>计件已审工程量</th>			        
			        
			  </tr>
			  <tr>
			  <td colspan = "12">未查询到相应结果 或者 必选条件没有全部选择</td>			    			                   
			  </tr>
		</table>
</form>
<div id="example"></div>

</body>
</html>
