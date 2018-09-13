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
       <table id="dg" style="width:100%;height: 99%;" title="任务书列表" data-options="
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
      <form>
		输入15位编号：<input id='mCode' name='mCode' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
		审核状态：<select name="auditStatus" id="auditStatus" style="width:120px;height:35px;line-height:35px;">
			     <option value="5">全部</option>
                 <option value="0">未审核</option>   
                 <option value="1">已审核</option>
                 <option value="3">退回</option>
			</select>
		任务书最后审核时间段：<input type="text" size="10" name="bDate" id="bDate" class="easyui-textbox" style="width:125px;height:30px;line-height:35px">
        —— <input type="text" size="10" name="eDate" id="eDate" class="easyui-textbox" style="width:125px;height:30px;line-height:35px">
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
     auditStatus: $("#auditStatus").val(),
     mCode: $("#mCode").val().trim(),
     bDate: $("#bDate").val(),
     eDate: $("#eDate").val(),
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "missionSelect",
         queryParams://每次请求的参数
             {
              auditStatus: $("#auditStatus").val(),
              mCode: $("#mCode").val().trim(),
              bDate: $("#bDate").val(),
              eDate: $("#eDate").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '11%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '10%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '12%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '7%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '15%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '7%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '12%', align: 'center' },
               { field: 'missionStatus', title: '状态', width: '11%', align: 'center' },
               {  
                field: 'mId',  
                title: '操作',  
                width: '11%',  
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
 
$(document).ready(function() {
$("#bDate").datebox({
    onSelect:function(date){
    var a = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    $("#bDate").val(a);
    }
  });
$("#eDate").datebox({
    onSelect:function(date){
    var a = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    $("#eDate").val(a);
    }
        });
});

function toDDMMMYYYY(date) {  
    var d = new Date(date.getTime());  
    var dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate().toString();  
    var mmm = mths[d.getMonth()];  
    var yyyy = d.getFullYear().toString(); //2011  
    return dd + mmm + yyyy;  
} 
 
       
             
          
 </script>  
</body> 
</html>