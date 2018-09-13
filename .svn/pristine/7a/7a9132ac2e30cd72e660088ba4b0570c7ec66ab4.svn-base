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
    <title>已结算工程任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="已结算工程任务书" data-options="
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
     	  输入15位编号: <input type="text" id="mcode" class="easyui-textbox" name="mcode" value="" style="width:166px;height:35px;line-height:35px;">
     	  工程名称: <select name="zPrId" id="zPrId" onChange="listUnitNumber()" style="width:170px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${prList}" var="pr">
                        <option value="${pr.zPrId}">${pr.prName}</option>
                      </c:forEach>
                    </select>
                    栋号: <select name="zPcPId" id="zPcPId" style="width:140px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                    </select>          
                    任务书类型: <select name="mtId" id="mtId" style="width:140px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${mtList}" var="mt">
                        <option value="${mt.mtName}">${mt.mtName}</option>
                      </c:forEach>
                    </select>
                    结算单位: <select name="cuId" id="cuId" style="width:140px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${cuList}" var="cu">
                        <option value="${cu.cuName}">${cu.cuName}</option>
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
	  mtId: $("#mtId").val(),
	  zPrId: $("#zPrId").val(),
	  mCode: $("#mCode").val(),
	  zPcPId: $("#zPcPId").val(),
	  cuId: $("#cuId").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "listZMission1",
         queryParams://每次请求的参数
             {
        	 mtId: $("#mtId").val(),
        	 zPrId: $("#zPrId").val(),
        	 mCode: $("#mCode").val(),
        	 zPcPId: $("#zPcPId").val(),
        	 cuId: $("#cuId").val(),
             },
         nowrap:false,
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '任务书编号', width: '11%', align: 'center' },  
               { field: 'mtId', title: '工程名称', width: '12%', align: 'center' },  
               { field: 'prName', title: '操作名称', width: '7%', align: 'center' }, 
               { field: 'unitNumber', title: '操作人员', width: '7%', align: 'center' },
               { field: 'beginDate', title: '操作时间', width: '9%', align: 'center' }, 
               { field: 'endDate', title: '审核记录', width: '10%', align: 'center' },
               { field: 'cuId', title: '操作人员', width: '10%', align: 'center' },
               { field: 'submitTime', title: '操作时间', width: '15%', align: 'center' }, 
               {  
	                field: 'zMId',  
	                title: '操作',  
	                width: '16%',  
	                align: 'center',  
	                formatter: function(value, row, index) {  
	                    var a = "<a href='viewZMissionInfo/"+value+"'>详细查看及打印</a>"  
	                       return a; 
	                 }  
	               }       
               
               ]],  
              
       });

 }
 
	function listUnitNumber() {
		var zprObj = document.getElementById("zPrId"); //[index];
		var zprId = zprObj.options[zprObj.selectedIndex].value
		if (zprId !=null && zprId != "") {
			$.ajax({  
		        contentType: 'application/json',
		        type:"POST",  
		        url:"queryUnitNumber/" + zprId, 
		        dataType:"json", 
		        success:function(data){
		        var pcpObj = document.getElementById("zPcPId");
		        pcpObj.length = 1;
		        $.each(data, function(index,item){
		        	var option = document.createElement("OPTION");
		        	//objSelect.options.add(new Option(item.unitNumber, item.zPcPId));
		        	
		        	option.appendChild(document.createTextNode(item.unitNumber));
		        	option.setAttribute("value", item.zPcPId);
		        	
		        	pcpObj.options.add(option);
		             //$("#pcPId").append("<option value="${item.zPcPId}">${item.unitNumber}</option>");                                
		                                               
		        })
		        },  
		        error:function(e) {  
		            alert("出错啦："+e);  
		        }  
			});
		}
	
	}         
 </script>  
</body> 
</html>