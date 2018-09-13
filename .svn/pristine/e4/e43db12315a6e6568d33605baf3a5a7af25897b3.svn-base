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
    <title>查看任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
</head> 
<body>
    <div class="container" style="height:100%;">
       <input type="hidden" name="prId" id="prId" value="${prId}" >
       <table id="dg" style="width:100%;height:99%" title="未审任务书列表" data-options="
                rownumbers:false,
                singleSelect:false,
                autoRowHeight:false,
                pagination:false,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                collapsible:true,
                toolbar:'#tb',
                loadMsg:0">
        </table>
      <div id="tb" style="padding:0 30px;">
      <form>
                         工程名称：${pj.prName}
		
		栋号：<select name="pcPId" id="pcPId" style="width:120px;height:35px;line-height:35px;">
			      <option value="">----请选择-----</option>
                     <c:forEach items="${prIndexPcList}" var="pcl">
                        <option value="${pcl.pcPId}">${pcl.unitNumber}</option>
                      </c:forEach>
			</select>
                任务书类型：<select name="mtId" id="mtId" style="width:120px;height:35px;line-height:35px;">
			      <option value="">----请选择-----</option>
                     <c:forEach items="${mtList}" var="mt">
                        <option value="${mt.mtId}">${mt.mtName}</option>
                      </c:forEach>
			</select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
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
     mtId:$("#mtId").val(),
     pcPId: $("#pcPId").val().trim()
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
	 
 $("#dg").datagrid({  
         url: "AuditByOne",
         queryParams://每次请求的参数
             {
              prId: $("#prId").val(),
              mtId: $("#mtId").val(),
              pcPId: $("#pcPId").val().trim(),
             },  
             pagination: true,//表示在datagrid设置分页        
         columns: [[  
               { field: 'mCode', title: '编号', width: '14%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '15%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '19%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '10%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '19%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '10%', align: 'center' },
               {  
                field: 'mId',  
                title: '操作',  
                width: '10%',  
                align: 'center',  
                formatter: function(value, row, index) {
                   var a = "<a href='javascript:void(0)' onclick='audit(\""+value+"\")'>审核</a>"  
                   return a; 
                 }  
               }           
           ]],  
              
       });

 }
 
 function audit(mId){
 window.location.href = "${pageContext.request.contextPath}/viewAndToauditMission?flag=1&mId="+mId;
 }
 
         
          
 </script>  
</body> 
</html>