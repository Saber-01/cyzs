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
    <title>预控量修改记录</title> 	

	<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
	<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">

  </head>
  
  <body>
  	  <div class="container" style="height:100%">
       <table id="dg" style="width:100%;height:99%" title="查询条件" data-options="
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
                loadMsg:1">
        </table>
       <div id="tb" style="padding:0 30px;">
       <form id="selects">
		工程名称 :  
				<select name="prId" id="prId" onchange="javascript:selectChange()" style="width:170px;height:35px;line-height:35px;">
					  	<option value="">---请选择---</option>
    				<c:forEach items="${prList}" var="pr">
    					<option value="${pr.prId}">${pr.prName}</option>
    				</c:forEach>
    			</select>&nbsp;&nbsp;
    	栋号 :  
    			<select id="pcPId" name="pcPId" style="width:170px;height:35px;line-height:35px;">
    				<option value="">---请选择---</option>
    			</select>&nbsp;&nbsp;
    	班组（结算单位）: 
    			<select id="cuId" name="cuId" style="width:170px;height:35px;line-height:35px;">
    				<option value="">---请选择---</option>
    				<c:forEach items="${cuList}" var="cu">
    					<option value="${cu.cuId}">${cu.cuName}</option>
    				</c:forEach>
    			</select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
		<a href="#" onclick="formReset()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
		<input id="res" name="res" type="reset" style="display:none;"/>
		</form>
      </div>
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
prepare();
function select(){
	getData();
	$("#dg").datagrid("load",{
		      prId: $("#prId").val(),
              pcPId: $("#pcPId").val(),
              cuId:$("#cuId").val(),
  });
}
function formReset(){
	$("input[name='res']").click();
}
function selectChange(){
	var value = $("#prId").val();
	if(value != "" && value != null){
		getUnitNumberList();
	}else{
		$("#pcPId").empty();
		$("#pcPId").append("<option value=''>---请选择栋号---</option>");
	}
}

function getUnitNumberList(){
	var json = {};
	json['prId'] = $("#prId").val();
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"getUnitNumberList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#pcPId").empty();
			$("#pcPId").append("<option value=''>---请选择栋号---</option>");
			$.each(data,function(index,item){
				
				$("#pcPId").append("<option value='"+item.pcPId+"'>"+item.unitNumber+"</option>");
		
			})
		},
		error:function(e){
			alert(e);
		}
	});
}
function prepare(){
	$("#dg").datagrid({ 
		rownumbers: false,
         columns: [[  
         	   { field: 'prName', title: '工程名称', width: '8%', align: 'center' }, 
         	   { field: 'unitNumber', title: '栋号', width: '6%', align: 'center' }, 
               { field: 'pName', title: '分部', width: '8%', align: 'center' },  
               { field: 'psName', title: '工程部位', width: '10%', align: 'center' },  
               { field: 'oName', title: '工作项目', width: '10%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '8%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '10%', align: 'center' },  
               { field: 'cuName', title: ' 	结算单位', width: '8%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '8%', align: 'center' },
               { field: 'mender', title: '修改者', width: '6%', align: 'center' }, 
               { field: 'auditTime', title: '修改时间', width: '8%', align: 'center' }            
           ]],             
       });
}
function getData(){
 $("#dg").datagrid({  
         url: "getYKLHistory",
         queryParams://每次请求的参数
             {
        	  prId: $("#prId").val(),
              pcPId: $("#pcPId").val(),
              cuId:$("#cuId").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
         	   { field: 'prName', title: '工程名称', width: '8%', align: 'center' }, 
         	   { field: 'unitNumber', title: '栋号', width: '6%', align: 'center' }, 
               { field: 'pName', title: '分部', width: '8%', align: 'center' },  
               { field: 'psName', title: '工程部位', width: '10%', align: 'center' },  
               { field: 'oName', title: '工作项目', width: '12%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '8%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '10%', align: 'center' },  
               { field: 'cuName', title: ' 	结算单位', width: '8%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '8%', align: 'center' },
               { field: 'mender', title: '修改者', width: '6%', align: 'center' }, 
               { field: 'auditTime', title: '修改时间', width: '13%', align: 'center' }            
           ]],             
       });
 }  		
</script>
  </body>
</html>
