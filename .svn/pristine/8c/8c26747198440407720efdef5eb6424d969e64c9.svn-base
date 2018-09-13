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
    <title>角色权限管理</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="角色权限管理列表" data-options="
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
		所属权限组：<select name="permissionGroupId" id="permissionGroupId" style="width:135px;height:35px;line-height:35px;margin:10px">
					  <option value="">------请选择------</option>
					   <c:forEach items="${pgList}" var="pg">
                        <option value="${pg.permissionGroupId}">${pg.permissionGroupName}</option>
                      </c:forEach>
			</select>
		所属角色：<select name="roleId" id="roleId" style="width:135px;height:35px;line-height:35px;margin:10px">
					  <option value="">------请选择------</option>
					 <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
			</select>
		是否选中：<select name="select" id="select" style="width:135px;height:35px;line-height:35px;margin:10px">
					  <option value="2">------请选择------</option>
                      <option value="1">选中</option>
                      <option value="0">未选中</option>
			   </select>
        权限编号：<input id='permissionId' name='permissionId' class="easyui-textbox" type="text" style="width:135px;height:35px;line-height:35px;"></input>
        权限名称：<input id='permissionName' name='permissionName' class="easyui-textbox" type="text" style="width:135px;height:35px;line-height:35px;"></input>
        </br>
        <a href="#" onclick="select()" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		<a href="#" onclick="submit()" class="easyui-linkbutton" iconCls="icon-reload">提交</a>
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
     permissionGroupId: $("#permissionGroupId").val(),
     roleId: $("#roleId").val(),
     permissionId:$("#permissionId").val(),
     permissionName:$("#permissionName").val(),
     select:$("#select").val(),
  }); 
 }
 function reset(){
   $("input[name='res']").click();
 }
 function submit(){
   var roPerid =new Array();
   var editRoid = new Array();
   var editPerid = new Array();
   var isSelect = new Array();
   var isDisplay =new Array();
   
  var rows = $("#dg").datagrid("getRows");
  for(var i=0;i<rows.length;i++){
     var textRoid = rows[i].roId;
     var textPerid = rows[i].perId;
     var textroPerid = rows[i].roPerId;
     var textSelect = checkSelect(rows[i].perId);
     
     editRoid.push(textRoid);
     editPerid.push(textPerid);
     isSelect.push(textSelect);
     isDisplay.push("false");
     if(textroPerid != "null"){roPerid.push(textroPerid);}else{roPerid.push("");}
  }
  
  var rpJson = "";
    var size = editRoid.length;
    rpJson+="[";  
    for(var i=0;i<size;i++){ 
    if(i!=(size-1)){  
            rpJson+="{\"roPerId\":"+"\""+roPerid[i]+"\",\"roId\":"+"\""+editRoid[i]+"\",\"perId\":\""+editPerid[i]+"\",\"isSelect\":\""+isSelect[i]+"\",\"isDisplay\":\""+isDisplay[i]+"\"},";  
        }else{  
            rpJson+="{\"roPerId\":"+"\""+roPerid[i]+"\",\"roId\":"+"\""+editRoid[i]+"\",\"perId\":\""+editPerid[i]+"\",\"isSelect\":\""+isSelect[i]+"\",\"isDisplay\":\""+isDisplay[i]+"\"}";  
       } 
     
   }
    rpJson+="]"; 
    //alert(rpJson);
    
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"updateRolePermissionSelect",
                async:false, 
                dataType:"json", 
                data:rpJson,
                success:function(data){
                alert("操作成功");
                },
                 error:function(e) {  
                    //alert("出错啦："+e);  
                } 
        });
      //重新加载当前页数据
      reloadtb();
  
 }
 function checkSelect(perID){
 var rows = $("#dg").datagrid("getChecked");
 var select = "false";
  $.each(rows, function(index, item){
     var Perid = item.perId;
     if(Perid == perID){
     select = "true";
     }
  });
  return select;
 }
 
 
 function reloadtb(){
   $("#dg").datagrid("reload",{                          
         permissionGroupId: $("#permissionGroupId").val(),
         roleId: $("#roleId").val(),
         permissionId:$("#permissionId").val(),
         permissionName:$("#permissionName").val(),
         select:$("#select").val()
    });
 }
 
 function getData(){
 $("#dg").datagrid({  
         url: "rolePermissionSelect",
        queryParams://每次请求的参数
             {
              permissionGroupId: $("#permissionGroupId").val(),
              roleId: $("#roleId").val(),
              permissionId:$("#permissionId").val(),
              permissionName:$("#permissionName").val(),
              select:$("#select").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30, 50],
         columns: [[ 
               
               { field: 'roleId', title: '角色编号', width: '21%', align: 'center' },  
               { field: 'roleName', title: '角色名称', width: '25%', align: 'center' }, 
               
               { field: 'isSelect', title: '是否选中',checkbox:true, width: '15%', align: 'center' }, 
                
               { field: 'permissionId', title: '权限编号编号', width: '25%', align: 'center' },
               { field: 'permissionName', title: '权限名称', width: '25%', align: 'center' },
               
               { field: 'roId', title: 'roId', width: '15%', align: 'center',hidden:'true'},
               { field: 'perId', title: 'perId', width: '15%', align: 'center' ,hidden:'true'},
               { field: 'roPerId', title: 'roPerId', width: '15%', align: 'center',hidden:'true'},        
           ]],
           
           onLoadSuccess:function(data){                   
           if(data){
             $.each(data.rows, function(index, item){
               if(item.roPerId != null){
                $('#dg').datagrid('checkRow', index);
              }
                 });
               }
           }
              
       });

 }
      
             
          
 </script>  
</body> 
</html>