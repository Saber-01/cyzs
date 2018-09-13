<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>任务书列表</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
<div class="container" style="height: 100%;">

       <table id="dg" style="width:100%;height:99%" title="复制计件任务书" data-options="
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
      <c:if test="${error == null }">
      <form>
		工程名称: <select name="prId" id="prId" onChange="listUnitNumber()" style="width:120px;height:35px;line-height:35px;">
					  <option value="">----请选择----</option>
                     <c:forEach items="${prList}" var="pr">
                        <option value="${pr.prId}">${pr.prName}</option>
                      </c:forEach>
			</select>
		栋号: <select name="pcPId" id="pcPId" style="width:120px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                    </select>
		结算单位: <select name="cuId" id="cuId" style="width:120px;height:35px;line-height:35px;">
                      <option value="">----请选择----</option>
                     <c:forEach items="${cuList}" var="cu">
                        <option value="${cu.cuId}">${cu.cuName}</option>
                      </c:forEach>
                  </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
        <input type="hidden" id="mtId" name="mtId" value="${mtId }">
        <input id="res" name="res" type="reset" style="display:none;"/>
        </form>
      </div>
    </div>
    </c:if>
    
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
    
	<span style="color:red; font-weight: bold">${error }</span>
</body>

<script type="text/javascript">

if (${error == null}) {
	getData();
}

function select(){
 $("#dg").datagrid("load",{                          
	 mtId: $("#mtId").val(),
     prId: $("#prId").val(),
	 pcPId: $("#pcPId").val(),
	 cuId: $("#cuId").val(),
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
             mtId: $("#mtId").val(),
             prId: $("#prId").val(),
			 pcPId: $("#pcPId").val(),
        	 cuId: $("#cuId").val(),
            },
        pagination: true,//表示在datagrid设置分页              
        rownumbers: false,  
        singleSelect: true,  
        pageSize: 15,
        pageList: [15, 20, 30,],
        columns: [[  
              { field: 'mCode', title: '编号', width: '12%', align: 'center' },  
              { field: 'prName', title: '工程名称', width: '15%', align: 'center' },
              { field: 'unitNumber', title: '栋号', width: '10%', align: 'center' },
              { field: 'bAndeDate', title: '起止时间', width: '15%', align: 'center' },
              { field: 'cuName', title: '结算单位', width: '10%', align: 'center' },
              { field: 'submitTime', title: '提交时间', width: '14%', align: 'center' },
              { field: 'missionStatus', title: '状态', width: '8%', align: 'center' },
              { field: 'file', title: '附件', width: '3%', align: 'center' },
              {  
               field: 'mId',  
               title: '操作',  
               width: '9%',  
               align: 'center',  
               formatter: function(value, row, index) {
                  var a = "<a href='toCopyMission?mId="+row.mId+"'>复制</a>"  
                  return a; 
                }  
              }           
          ]],
      });

}

	function listUnitNumber() {
		var prObj = document.getElementById("prId"); //[index];
		var prId = prObj.options[prObj.selectedIndex].value
		if (prId !=null && prId != "") {
			$.ajax({  
		        contentType: 'application/json',
		        type:"POST",  
		        url:"selectUnitNumber/" + prId, 
		        dataType:"json", 
		        success:function(data){
		        var pcpObj = document.getElementById("pcPId");
		        pcpObj.length = 1;
		        $.each(data, function(index,item){
		        	var option = document.createElement("OPTION");
		        	
		        	option.appendChild(document.createTextNode(item.unitNumber));
		        	option.setAttribute("value", item.pcPId);
		        	
		        	pcpObj.options.add(option);                             
		                                               
		        })
		        },  
		        error:function(e) {  
		            alert("出错啦："+e);  
		        }  
			});
		}
	}


</script>

</html>