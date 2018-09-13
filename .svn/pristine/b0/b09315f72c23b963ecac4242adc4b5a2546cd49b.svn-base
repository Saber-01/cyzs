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
    <title>安装工程信息锁定</title> 	

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">

</head> 
<body>
    <div class="container">
       <table id="dg" style="width:100%;height:601px" title="安装工程信息锁定" data-options="
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
			工程名称:<select name="prId" id="prId" style="width:170px;height:35px;line-height:35px;">
						<c:forEach items="${prList}" var="p">
							<option value="${p.prId}">${p.prName}</option>
						</c:forEach>
				</select>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="toLockProject()">添加</a>

		</form>
</div>


</div>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$("#dg").datagrid({  
    pagination: true,//表示在datagrid设置分页              
    rownumbers: false,  
    singleSelect: true,  
    pageSize: 15,
    pageList: [15, 20, 30,],
    columns: [[  
          { field: 'prname', title: '工程名称', width: '25%', align: 'center' },  
          { field: 'pcpNumber', title: '栋号', width: '25%', align: 'center' },  
          { field: 'cnName', title: '结算单位', width: '25%', align: 'center' },
          {  
           field: 'lId',  
           title: '操作',  
           width: '26%',  
           align: 'center',  
           formatter: function(value, row, index) {  
              var a = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>详细</a>"
              var b = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
              return a +"     ||     "+ b; 
            }  
          }           
      ]],  
         
  });


</script>


</body>
</html>
