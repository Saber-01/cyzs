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
       <table id="dg" style="width:100%;height:99%" title="已提交待审核任务书列表" data-options="
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
        ${backMsg }
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


    
  

    
<script type="text/javascript"> 
  
 getData();
 function getData(){
 $("#dg").datagrid({  
         url: "submitMissionSelect",
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '10%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '12%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '13%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '8%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '15%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '7%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '12%', align: 'center' },
               {  
                field: 'mId',
                title: '操作',
                width: '19%',
                align: 'center',
                formatter: function(value, row, index) {
                   var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\", \""+row.mtName+"\")'>修改</a>&nbsp;||&nbsp;"
                   var b = "<a href='javascript:void(0)' onclick='cancel(\""+value+"\", \""+row.mCode+"\")'>撤销 </a>&nbsp;||&nbsp;"
                   var c = "<a href='javascript:void(0)' onclick='delMission(\""+value+"\", \""+row.mCode+"\", \""+row.prName+"\")'>删除</a>&nbsp;||&nbsp;"
                   var d = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>查看详细或添加附件</a>"  
                   return a+b+c+d; 
                 }
               }           
           ]],  
              
       });

 }
 
	function edit(mId, mtName) {
		window.location.href = "${pageContext.request.contextPath}/toEditMission?mId=" + mId + "&mtName=" + mtName;
	}
	function view(mId) {
		window.location.href = "${pageContext.request.contextPath}/viewAndToauditMission?flag=0&&mId=" + mId;
	}
	function cancel(mId, mCode) {
		var flag = confirm('确定要撤销编号为' + mCode + '的任务书吗?');
		if (flag) {
		window.location.href = "${pageContext.request.contextPath}/backMission/mId=" + mId;
		} else {
			return;
		}
	}
	function delMission(mId, mCode, prName) {
		var flag = confirm('确定要删除编号为' + mCode + '的任务书吗?');
		if (flag) {
			//window.location.href = "${pageContext.request.contextPath}/delMission/" + mId + "/" + prName;
			$.ajax({  
	            contentType: 'application/json',
	            type:"POST",  
	            url:"delMission/" + mId + "/" + prName,
	            //data: "mId=" + mId + "&prName=" + prName,
	            dataType:"text", 
	            async: false,
	            success:function(data){
	            if(data==1){
	              $("#dg").datagrid("reload");
	              alert("删除成功");
	            }	else {
	            	alert("删除失败");
	            }
	            },
	            error:function(e) {
	              alert("删除失败："+e);  
	            }  
	        });
		} else {
			return;
		}
		
	}
	
</script>  
</body> 
</html>