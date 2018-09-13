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
    <title>权限管理</title> 
    
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="任务书类型信息列表" data-options="
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

function select(){
  $("#dg").datagrid("load",{                          

 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "listSetTime1",
         queryParams://每次请求的参数
             {
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'sYear', title: '结算年份', width: '8%', align: 'center' },  
               { field: 'sMonth', title: '结算月份', width: '8%', align: 'center' }, 
               { field: 'sBeginTime', title: '开始时间(最后审核时间)', width: '20%', align: 'center' },  
               { field: 'sEndTime', title: '结束时间(最后审核时间)', width: '20%', align: 'center' }, 
               { field: 'remark', title: '备注', width: '20%', align: 'center' },  
               {  
                field: 'setId',  
                title: '操作',  
                width: '22%',  
                align: 'center',  
                formatter: function(value, row, index) {   
                var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>" 
                   return a; 
                 }  
               }           
           ]],  
              
       });

 }
      
 function edit(setId){
	   var _url = "${pageContext.request.contextPath }/toEditSetTime/" + setId;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '修改统计月度时间范围',
	                    width : 600,
	                    height : 270,
	                    closed : false,
	                    cache : false,
	                    href : _url,
	                    modal : true,
	                    collapsible:false, //设置可折叠  
	                    minimizable:false,  
	                    maximizable:false,  
	                    resizable:false, 
	                    onLoad : function() {    //初始化表单数据
	                    },
	                    onClose : function() {
	                        $(this).dialog('destroy');
	                    },               
	                });
	 }
	                   
          
 </script>  
</body> 
</html>