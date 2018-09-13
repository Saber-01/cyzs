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
    <title>未处理任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="未处理任务书列表" data-options="
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
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


    
  

    
<script type="text/javascript"> 
  
 getData();
 function getData(){
 $("#dg").datagrid({  
         url: "unCompletedMissionSelect",
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '9%', align: 'center' },  
               { field: 'mtName', title: '任务书类型', width: '9%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '10%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '7%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '12%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '6%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '10%', align: 'center' },
               { field: 'missionStatus', title: '状态', width: '10%', align: 'center' },
               { field: 'nextstatus', title: '待审核人员', width: '10%', align: 'center' },
               { field: 'file', title: '附件', width: '3%', align: 'center' },
               {  
                field: 'mId',  
                title: '操作',  
                width: '9%',  
                align: 'center',  
                formatter: function(value, row, index) {
                   var a = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>详细查看及打印</a>"  
                   return a; 
                 }  
               }           
           ]],  
              
       });

 }
 
  function view(mId){
 window.location.href = "${pageContext.request.contextPath}/viewAndToauditMission?flag=0&mId="+mId;
 }
 

 
       
             
          
 </script>  
</body> 
</html>