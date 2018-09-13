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
    <title>预控量管理</title> 	

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
       <form>
       
		工程名称 :  
				<select name="prId" id="prId" onchange="javascript:selectChange()" style="width:170px;height:35px;line-height:35px;">
					  	<option value="">---请选择---</option>
    				<c:forEach items="${prList}" var="pr">
    					<option value="${pr.prId}">${pr.prName}</option>
    				</c:forEach>
    			</select>
    			(*必选)&nbsp;&nbsp;
    	栋号 : 
    			<select id="pcPId" name="pcPId" style="width:170px;height:35px;line-height:35px;">
    				<option value="">---请选择---</option>
    			</select>
    			(*必选)&nbsp;&nbsp;
    	班组（结算单位）: 
    			<select id="cuId" name="cuId" style="width:170px;height:35px;line-height:35px;">
    				<option value="">---请选择---</option>
    				<c:forEach items="${cuList}" var="cu">
    					<option value="${cu.cuId}">${cu.cuName}</option>
    				</c:forEach>
    			</select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		</form>
      </div>
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


  

    
<script type="text/javascript"> 
prepare();
 function select(){
 	var prId = $("#prId").val();
 	var pcPId = $("#pcPId").val();
 	if(prId == "" || pcPId == ""){
 		alert("工程与楼栋号不能为空！");
 		return;
 	}
 	getData();
    $("#dg").datagrid("load",{                          
    	pName: $("#pName").val(),
    	psName: $("#psName").val(),
    	oName:$("#oName").val(),
    	unName: $("#unName").val(),
    	price: $("#price").val(),
    	accumulateSum:$("#accumulateSum").val(),
    	realSum: $("#realSum").val(),
    	budgetSum: $("#budgetSum").val(),
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "getYKLBudget",
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
               { field: 'pName', title: '分部', width: '10%', align: 'center' },  
               { field: 'psName', title: '工程部位/分项1', width: '15%', align: 'center' },  
               { field: 'oName', title: '工作项目/分项2', width: '21%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '10%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '15%', align: 'center' },  
               { field: 'realSum', title: ' 已开工程量', width: '13%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '12%', align: 'center' },
                         
           ]],  
              
       });

 }
 function prepare(){
 	$("#dg").datagrid({ 
 		 rownumbers: false, 
         columns: [[  
               { field: 'pName', title: '分部', width: '10%', align: 'center' },  
               { field: 'psName', title: '工程部位/分项1', width: '15%', align: 'center' },  
               { field: 'oName', title: '工作项目/分项2', width: '20%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '10%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '14%', align: 'center' },  
               { field: 'realSum', title: ' 已开工程量', width: '12%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '10%', align: 'center' },                     
           ]],              
    });
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
          
 </script>  
</body> 
</html>