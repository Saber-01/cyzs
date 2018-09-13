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
                pagination:false,
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
		工程名称: 
				<select name="prId" id="prId" onchange="javascript:selectChange()" style="width:170px;height:35px;line-height:35px;">
					  	<option value="">---请选择---</option>
    				<c:forEach items="${prList}" var="pr">
    					<option value="${pr.prId}">${pr.prName}</option>
    				</c:forEach>
    			</select>
    			(*必选)&nbsp;&nbsp;
    	栋号: 
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
		<a href="#" onclick="submit()" class="easyui-linkbutton" iconCls="icon-reload">提交</a>
		</form>
      </div>
    </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


  

    
<script type="text/javascript"> 
var state = 0;
prepare();
 function select(){
 	var prId = $("#prId").val();
 	var pcPId = $("#pcPId").val();
 	if(prId == "" || pcPId == ""){
 		alert("工程名与楼栋号均不能为空！");
 		return;
 	} 
    getData();
    state = 1;
    $("#dg").datagrid("load",{                          
    	      prId: $("#prId").val(),
              pcPId: $("#pcPId").val(),
              cuId:$("#cuId").val(),
  });
 }
 $(document).ready(function(){
	var inputList = $("input[type=text]");
	inputList.each(function(){
		$(this).numberbox({
			min:0
		});
	});
});
 function submit(){
 	  	//提交
  	if(state==0){
  		alert("无数据，无法提交！");
  	}else{
  		getAllInput();
  		
  	}
 }
 
 function getAllInput(){
	var inputs = document.querySelectorAll("input");
	var json = {};
	for(var i=0,len=inputs.length;i<len;i++){
		if(inputs[i].name == "budgetSum"){
			json[inputs[i].id] = inputs[i].value;
		}
	}
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"editYKL",
		dataType:"json",
		data:postdata,
		success:function(data){
			//do nothing
			$("#dg").datagrid("reload");
			alert(data.toString());
		},
		error:function(e){
			alert("出错了："+e);
		}
	});
}
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "getYKL",
         queryParams://每次请求的参数
             {
        	  prId: $("#prId").val(),
              pcPId: $("#pcPId").val(),
              cuId:$("#cuId").val(),
             },
         singleSelect: true,  
         columns: [[  
               { field: 'pName', title: '分部', width: '10%', align: 'center' },  
               { field: 'psName', title: '工程部位/分项1', width: '10%', align: 'center' },  
               { field: 'oName', title: '工作项目/分项2', width: '15%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '10%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '10%', align: 'center' },  
               { field: 'cuName', title: ' 	结算单位', width: '10%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '13%', align: 'center' },
               {  
                field: 'opId',  
                title: '修改预控量',  
                width: '15%',  
                align: 'center', 
                formatter: function(value, row, index) {  
                   var a = "<input type='text' id='" + value + "' name='budgetSum'/>"
                   return a; 
                 }  
               }           
           ]],  
              
       });

 }
 function prepare(){
 	$("#dg").datagrid({  
         columns: [[  
               { field: 'pName', title: '分部', width: '10%', align: 'center' },  
               { field: 'psName', title: '工程部位/分项1', width: '10%', align: 'center' },  
               { field: 'oName', title: '工作项目/分项2', width: '15%', align: 'center' },
               { field: 'unName', title: '单位 ', width: '5%', align: 'center' },
               { field: 'price', title: '单价', width: '10%', align: 'center' },  
               { field: 'accumulateSum', title: '已结工程量', width: '10%', align: 'center' },  
               { field: 'cuName', title: ' 	结算单位', width: '10%', align: 'center' },
               { field: 'budgetSum', title: '预控量 ', width: '10%', align: 'center' },
               { field: 'opId',  title: '修改预控量', width: '12%', align: 'center',  }           
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