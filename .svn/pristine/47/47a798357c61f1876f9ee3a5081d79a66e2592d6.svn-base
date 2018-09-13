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
    <title>任务书操作记录</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="任务书操作记录" data-options="
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
     	  工程名称：<select name="prName" id="prName" style="width:170px;height:35px;line-height:35px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${prList}" var="pr">
                        <option value="${pr.prName}">${pr.prName}</option>
                      </c:forEach>
                    </select>
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
	  prName: $("#prName").val(),
	  mCode: $("#mCode").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "listOperationRecord1",
         queryParams://每次请求的参数
             {
        	 prName: $("#prName").val(),
        	 mCode: $("#mCode").val(),
             },
         nowrap:false,
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '任务书编号', width: '11%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '15%', align: 'center' },  
               { field: 'operationType', title: '操作名称', width: '8%', align: 'center' }, 
               { field: 'userName', title: '操作人员', width: '15%', align: 'center' },
               { field: 'operationTime', title: '操作时间', width: '15%', align: 'center' }, 
               { field: 'remark1', title: '审核记录', width: '34%', align: 'center' },    
           ]],  
              
       });

 }
      
 function view(mId){
	   var _url = "${pageContext.request.contextPath }/viewAndToauditMission?flag=0&mId=" + mId;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '任务书详细信息',
	                    width : 600,
	                    height : 370,
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