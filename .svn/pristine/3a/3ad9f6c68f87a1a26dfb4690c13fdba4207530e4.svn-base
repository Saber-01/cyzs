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
    <title>删除回退土建任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container">
       <table id="dg" style="width:100%;height:601px" title="删除回退土建任务书" data-options="
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
       <form id="role"> 
     	  输入15位编号：<input type="text" id="mcode" class="easyui-textbox" name="mcode" value="" style="width:166px;height:35px;line-height:35px;">
     	  起始时间：<input type="text" size="10" name="beginDate"
		   	   				id="beginDate" class="easyui-textbox" style="width:166px;height:30px;line-height:35px;">
		 结束时间：<input type="text" size="10" name="endDate"
		   	   				id="endDate" class="easyui-textbox" style="width:166px;height:30px;line-height:35px;">
        <a href="#" id="button_submit" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
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
	  mcode: $("#mcode").val(),
	  beginDate: $("#beginDate").val(),
	  endDate:$("#endDate").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
	  
 $("#dg").datagrid({  
         url: "missionSelect1",
         queryParams://每次请求的参数
             {
        	 mcode: $("#mcode").val().trim(),
        	 beginDate: $("#beginDate").val(),
        	 endDate:$("#endDate").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '11%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '12%', align: 'center' }, 
               { field: 'prName', title: '工程名称', width: '8%', align: 'center' },  
               { field: 'unitNumber', title: '栋号', width: '8%', align: 'center' }, 
               { field: 'beginDate', title: '开始时间', width: '8%', align: 'center' },
               { field: 'endDate', title: '结束时间', width: '8%', align: 'center' }, 
               { field: 'cuName', title: '结算单位', width: '6%', align: 'center' }, 
               { field: 'submitTime', title: '提交时间', width: '12%', align: 'center' }, 
               { field: 'missionStatus', title: '状态', width: '8%', align: 'center' },  
               {  
                field: 'mId',  
                title: '操作',  
                width: '15%',  
                align: 'center',  
                formatter: function(value, row, index) {  
                    var a = "<a href='viewAndToauditMission?flag=0&mId="+value+"'>详细查看及打印</a>"
                    var b = "<a href='javascript:void(0)' onclick='delete(\""+value+"\")'>删除</a>"
                    var c = "<a href='javascript:void(0)' onclick='back(\""+value+"\")'>回退</a>" 
                    return a +"     ||     "+ b+ "      ||     "+ c; 
                 }  
               }           
           ]],  
              
       });

 }
 
	                   
          
 </script>  
</body> 
</html>