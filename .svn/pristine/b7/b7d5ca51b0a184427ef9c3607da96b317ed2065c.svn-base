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
 var stopaudit = $("#hid").val();
 if(stopaudit == 1){
  getData();
 }
 });
 function getData(){
 $("#dg").datagrid({  
         url: "unAduditMissionSelect",
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '14%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '15%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '13%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '9%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '16%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '10%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '14%', align: 'center' },
               { field: 'status', title: '状态', width: '0%', align: 'center', hidden: true },
               {  
                field: 'mId',  
                title: '操作',  
                width: '10%',  
                align: 'center',  
                formatter: function(value, row, index) {
                	if(row.status == -1) {
                		var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\", \""+row.mtName+"\")'>修改</a>&nbsp;||&nbsp;"  
                        var b = "<a href='javascript:void(0)' onclick='delMission(\""+value+"\", \""+row.mCode+"\", \""+row.prName+"\")'>删除</a>"  
                        return a+b; 
                	} else {
                		var a = "<a href='viewAndToauditMission?flag=1&mId="+value+"'>审核</a>"  
                		return a;
                	}
                   
                 }  
               }           
           ]],  
              
       });

 }
 
	function edit(mId, mtName) {
		window.location.href = "${pageContext.request.contextPath}/toEditMission?mId=" + mId + "&mtName=" + mtName;
	}

	function delMission(mId, mCode, prName) {
		var flag = confirm('确定要删除编号为' + mCode + '的任务书吗?');
		if (flag) {
			window.location.href = "${pageContext.request.contextPath}/delMission/" + mId + "/" + prName;
		} else {
			return;
		}
	}
       
             
          
 </script>  
</body> 
</html>