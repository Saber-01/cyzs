<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>合同单价表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="合同单价表" data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                collapsible:true,
                toolbar:'#tb',
                loadMsg:0">
        </table>
      <div id="tb" style="padding:0 30px;">
    <form id="contract"> 
		工程名称： <select name="prId" id=prId style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                       栋号：<select name="pcpNumber" id=pcpNumber style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                    </select>
                     结算单位： <select name="cuName" id=cuName style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${cuList}" var="r">
                        <option value="${r.cuName}">${r.cuName}</option>
                      </c:forEach>
                    </select>
                     单价：<input class="easyui-textbox" type="text" id ="price" name=price style="width:135px;height:35px;line-height:35px;margin:10px"/></td>
            </br>
 	            分部：<select name="pId" id=pId style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pId}">${r.pName}</option>
                      </c:forEach>
                    </select>
          
	             工程部位/分项1：<select name="psId" id=psId style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                    </select>
	            
	             工作项目/分项2：<select name="jobKey" id=jobKey style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                    </select>
                 
          	
       <a href="#" onclick="select()" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
<input id="res" name="res" type="reset" style="display:none;"/>
</form>
 </div>
</div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


    
  

    
<script type="text/javascript"> 
  
 getData();
 function select(){
   $("#dg").datagrid("load",{                          
	          prId: $("#prId").val(),
        	  pId: $("#pId").val(),
        	  psId:$("#psId").val(),
        	  jobKey:$("#jobKey").val(),        	  
        	  pcpNumber: $("#pcpNumber").val(),       	  
        	  cuName:$("#cuName").val(),
        	  price:$("#price").val(),
  }); 
 }
 function reset(){
   $("input[name='res']").click();
 }

 function getData(){
 $("#dg").datagrid({  
         url: "selectContract2",
        queryParams://每次请求的参数
             {
        	  prId: $("#prId").val(),
        	  pId: $("#pId").val(),
        	  psId:$("#psId").val(),
        	  jobKey:$("#jobKey").val(),        	  
        	  pcpNumber: $("#pcpNumber").val(),       	  
        	  cuName:$("#cuName").val(),
        	  price:$("#price").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30, 50],
         columns: [[ 
               
               { field: 'prName', title: '工程名称', width: '13%', align: 'center' },  
               { field: 'pName', title: '分部', width: '12%', align: 'center' },                
               { field: 'psName', title: '工程部位/分项1', width: '12%', align: 'center' },                 
               { field: 'jobName', title: '工作项目/分项2', width: '20%', align: 'center' },                             
               { field: 'pcpNumber', title: '栋号', width: '10%', align: 'center'},
               { field: 'unName', title: '单位', width: '10%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '10%', align: 'center' },
               { field: 'price', title: '单价', width: '9%', align: 'center'},        
           ]],
           
           onLoadSuccess:function(data){                   
           if(data){
             $.each(data.rows, function(index, item){
               if(item.roPerId != null){
                $('#dg').datagrid('checkRow', index);
              }
                 });
               }
           }
              
       });

 }

  $("#prId").on('input propertychange',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);         }
         else{  $("#pcpNumber").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpNumber").append(option1);}
 
  });
  
  $("#pId").on('input propertychange',function () { 
         var pId = $(this).val();       
         if(pId!= null  && pId!='')
         {          getPosition(pId);         }
         else{  $("#psId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#psId").append(option1);}
 
  });
  
  $("#psId").on('input propertychange',function () { 
         var psId = $(this).val();       
         if(psId!= null  && psId!='')
         {          getJobName(psId);         }
         else{   $("#jobKey").empty();             
                var option1=$("<option>").text('---请选择---').val(null);
                $("#jobKey").append(option1);}
 
  });      


//联动函数实现
 function  getPcpNumber(prId)
{  
    
    var json = {"prId":prId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#pcpNumber").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpNumber").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.unitNumber).val(item.unitNumber);
                 $("#pcpNumber").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getPosition(pId)
{  
    
    var json = {"pId":pId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changePosition",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#psId").empty(); 
               var option1=$("<option>").text('---请选择---').val(null);
                $("#psId").append(option1);
                $.each(data, function(index,item){                
                 var option = $("<option>").text(item.psName).val(item.psId);                
                 $("#psId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getJobName(psId)
{  
    
    var json = {"psId":psId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeJob",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#jobKey").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#jobKey").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.jobName).val(item.jobKey);
                 $("#jobKey").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};
             
          
 </script>  
</body> 
</html>