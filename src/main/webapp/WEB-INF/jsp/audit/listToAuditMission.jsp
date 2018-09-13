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
</head> 
<body>
    <div class="container" style="height: 100%;">
     <input id="hid" name="hid" type="hidden" value="${stopaudit.paraValue}"/>
     <input id="hidpl" name="hidpl" type="hidden" value="${stopplaudit.paraValue}"/>
     <c:choose>
       <c:when  test="${stopaudit.paraValue == 0}">
       <table id = "tips" style="width:100%">
         <tr>
       <td width="92%"  align="center" bgcolor="#e3e3e3">当前暂时关闭审核功能</td>
       </tr>
        </table>
       </c:when>
       
        <c:otherwise>
        <table id="dg" style="width:100%;height:100%" title="待审核任务书情况" data-options="
                rownumbers:true,
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
     </c:otherwise>  
     </c:choose>
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


<script type="text/javascript"> 
  
 $(document).ready(function(){
 parent.updateCount();
 
 var stopaudit = $("#hid").val();
 if(stopaudit == 1){
  getData();
 }
 });
 function getData(){
 $("#dg").datagrid({  
         url: "selectMissionToAudit", 
         rownumbers: false,  
         singleSelect: true,  
         columns: [[  
               { field: 'prName', title: '工程名称', width: '35%', align: 'center' },  
               { field: 'count', title: '待审任务书数量', width: '30%', align: 'center' },  
               {  
                field: 'prId',  
                title: '操作',  
                width: '36%',  
                align: 'center',  
                formatter: function(value, row, index) {
                   var stopplaudit = $("#hidpl").val();
                   var a = "<a href='javascript:void(0)' onclick='auditByone(\""+value+"\")'>逐条审核</a>"
                   if(stopplaudit == 1){
                    var b = "<a href='javascript:void(0)' onclick='auditPL(\""+value+"\")'>批量审核</a>" 
                   }else{
                    var b = "<a href='javascript:void(0)'>不允许批量审核</a>"
                   }
                   
                    
                   return a+"   ||   "+b; 
                 }  
               }           
           ]],  
              
       });

 }
 
 function auditByone(prId){
 window.location.href = "${pageContext.request.contextPath}/toAuditByOne?prId="+prId;
 }
 
function auditPL(prId){
 window.location.href = "${pageContext.request.contextPath}/toAuditPL/"+prId;
 }
 
       
             
          
 </script>  
</body> 
</html>