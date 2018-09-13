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
    <title>暂不处理任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
   
    <div class="container" style="height: 100%;">
       <input id="hid" name="hid" type="hidden" value="${stopaudit.paraValue}"/>
       <c:choose>
       <c:when  test="${stopaudit.paraValue == 0}">
       
       <table id = "tips" style="width:100%">
         <tr>
       <td width="92%"  align="center" bgcolor="#e3e3e3">当前暂时关闭审核功能</td>
       </tr>
        </table>
       </c:when>
       <c:otherwise>
        <table id="dg" style="width:100%;height: 100%;" title="暂不处理任务书列表" data-options="
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
       </c:otherwise>
       </c:choose>
    </div>
   <%--  <table id = "tips" style="width:100%;padding-top:-5px">
         <tr>
       <td width="92%"  align="center" bgcolor="#e3e3e3">您共有<span style="color:red">${count}</span>条暂不处理任务书</td>
       </tr>
        </table> --%>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


    
  

    
<script type="text/javascript"> 
$(document).ready(function(){
 var stopaudit = $("#hid").val();
 if(stopaudit == 1){
  getData();
 }
 });
 
 
 function getData(){
 $("#dg").datagrid({  
         url: "stopAuditMissionSelect",
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         columns: [[  
               { field: 'mCode', title: '编号', width: '14%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '14%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '14%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '8%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '16%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '10%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '16%', align: 'center' },
               {  
                field: 'mId',  
                title: '操作',  
                width: '9%',  
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
 var ss = $("#hid").val();
 //alert(ss);
 window.location.href = "${pageContext.request.contextPath}/viewAndToauditMission?flag=1&mId="+mId;
 }
 

 
       
             
          
 </script>  
</body> 
</html>