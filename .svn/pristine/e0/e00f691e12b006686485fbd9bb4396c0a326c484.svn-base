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
<title>单价信息预算分类管理</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

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
 
<script type="text/javascript">

$(document).ready(function(){

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
   if(pName!=null && pName!='' && pcpId!= null && pcpId!='' && prId!= null && prId!='')
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
                url:"budget/selectPrice1", 
                dataType:"json", 
                data:postdata,
                async:false, 
                success:function(datas){              
                var cList=datas.cList;
                var bList=datas.bList;
                if(cList!=null){
                $("#customers tr:gt(0)").remove();               
                $.each(cList, function(index,item){                    
                $("#customers").append("<tr bgcolor='white'><td>"+(index+1)+"</td><td>"+item.prName+"</td><td>"+item.pcpNumber+"</td><td>"+item.cuName+"</td><td algin='left'><input type='hidden' value='"+item.pId+"'>"+item.pName+"</td><td>"+item.psName+"</td><td>"+item.jobName+"</td><td><input type='hidden' value='"+item.unId+"'>"+item.unName+"</td><td>"+item.price+"</td><td>"+item.ykl+"</td><td>"+item.ysl+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td><input type='checkbox' class='acheck' id='isAdd' name='isAdd' value='"+item.opId+"' onclick='sum()'>选择</input></td></tr>");                                                                                                     
                })
                $("#customers").append("<tr bgcolor='white'><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td id='yklsum'></td><td id='yslsum'></td><td></td><td colspan='2'><input type='button' value='全选'  onclick='checkAll()'><input type='button' value='取消' onclick='clearAll()'></td></tr>");
                }
                
                if(bList!=null){
                $("#bId").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#bId").append(option1);
                $.each(bList, function(index,item){ 
                console.log(item);
                 var option = $("<option>").text(item.budgetName).val(item.bId);
                 console.log(item.bId+"   "+item.budgetName);
                 $("#bId").append(option);
                 })     }
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
					sumykl+=parseFloat($row.find('td:eq(11)').text());
					sumykl=parseFloat(sumykl.toFixed(2));
					sumysl+=parseFloat($row.find('td:eq(12)').text());
					sumysl=parseFloat(sumysl.toFixed(2));
			}	
		)
		$("#yklsum").html(sumykl);
		$("#yslsum").html(sumysl);		
	}

	
</script> 

</head>
<body>
  <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">单价加入已有分类</font>
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
                <td>分部(必选)</td>
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
                <td colspan=2>
                    <input type="button" id="button_submit" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="查询">
                    <input type="reset" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="重置"/>
                </td>
              
  
            </tr>
       
</table>
</form>


<form id="budgetList" action="budget/addPriceToBudget" method="post">
 	<table id="customers" >		
			<tr>
			    <th>序号</th>
		        <!-- <th>类别</th> -->
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
	</table>
	<br>
 	将上表选择的单价加入下表选择的分类中，已分类的单价默认加入新的分类。	
 

 	<input type="hidden" name="budgetPrId" id="budgetPrId" value="" />
 	<input type="hidden" name="budgetPcpId" id="budgetPcpId" value="" />
 	<input type="hidden" name="budgetPId" id="budgetPId" value="" />
 	<input type="hidden" name="budgetUnId" id="budgetUnId" value="" />
 	<table id="yustable" border="1" cellspacing="0" >	
			<tr>
		    <td width="15%">预算自定义名称</td>
               <td width="30%">
                  <select name="bId" id=bId  >
                     <option value="">---请选择---</option>
                   </select>                  
               </td>
               <td width="55%">
               <input type="submit" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="确认合并"></td>
			</tr>
	
	</table>
		
	</form>
<div id="example"></div>

</body>
</html>
