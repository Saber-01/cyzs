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
    <title>工程部位基本信息列表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="工程部位基本信息列表" data-options="
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
    </div>
    <div id="tb" style="padding:0 30px;">
       <form>
		分部: <select name="pName" id="pName" style="width:170px;height:35px;line-height:35px;">
					  <option value="">--------请选择--------</option>
					  <c:forEach items="${partList}" var="r">
                        <option value="${r.pName}">${r.pName}</option>
                      </c:forEach>
			</select>
       工程部位: <input id='psName' name='psName' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
       
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
		<input id="res" name="res" type="reset" style="display:none;"/>
		</form>
      </div>
    
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>

<script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>
    
  

    
<script type="text/javascript"> 
  
getData();
function select(){
  $("#dg").datagrid("load",{                          
	  psName: $("#psName").val(),
	  pName: $("#pName").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "selectPosition1",
         queryParams://每次请求的参数
             {
          psName: $("#psName").val(),
       	  pName: $("#pName").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'psName', title: '工程部位名', width: '35%', align: 'center' },  
               { field: 'pName', title: '分部', width: '30%', align: 'center' }, 
               {  
                field: 'psId',  
                title: '操作',  
                width: '34%',  
                align: 'center',  
                formatter: function(value, row, index) {   
                var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
                var b = "<a href='javascript:void(0)' onclick='del(\""+value+"\")'>删除</a>"   
                   return a +"     ||     "+ b; 
                 }  
               }           
           ]],  
              
       });

 }
      
 function edit(psId){
	   var _url = "${pageContext.request.contextPath }/part/toEditPosition/" + psId;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '修改分部信息',
	                    width : 602,
	                    height : 480,
	                    closed : false,
	                    cache : false,
	                    href : _url,
	                    modal : true,
	                    collapsible:false, //设置可折叠  
	                    minimizable:false,  
	                    maximizable:false,  
	                    resizable:false, 
	                    onLoad : function() {    //初始化表单数据
	                     $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            psName:{required: true}, 
                            pId:{required: true},
                            remark:{required: false,maxlength:250},  
                          },   

                         submitHandler: function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editPosition",
                           data:postdata,
                           dataType:"text", 
                           async: false,
                           success:function(data){
                           if(data==1){
                            reloadtb();
                           }else{alert("修改失败");}	
                           },  
                           error:function(e) {  
                           alert("修改失败："+e);  
                           }  
                         });
                        $('#newDialog').dialog('close');  
                        }, 
                    invalidHandler: function(form, validator) {  //不通过回调   
                    return false;   
                    }   
                   });
	                    },
	                    onClose : function() {
	                        $(this).dialog('destroy');
	                    },
	                    buttons:[{  
                        text:'提交',
                        iconCls: 'icon-ok',
                        handler:function(){
                         $("#submit").click();  
                        }
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res1").click();  
                        }  
                      }]                     
	                });
	 }

function reloadtb(){
  $("#dg").datagrid("reload",{                          
	  psName: $("#psName").val(),
	  pName: $("#pName").val(),
 });
}	                   
 function del(id){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"deletePosition/"+id,
            data:id,
            dataType:"text", 
            async: false,
            success:function(data){
            if(data==1){
              reloadtb();
            }else{alert("删除失败");}	
            },  
            error:function(e) {  
              alert("删除失败："+e);  
            }  
        });
	} else {
		return;
	}
 }
 
   function add(){
   var _url = "${pageContext.request.contextPath }/part/toAddPosition";
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '添加工程部位',
                    width : 602,
                    height : 480,
                    closed : false,
                    cache : false,
                    href : _url,
                   modal : true,
                   collapsible:false, //设置可折叠  
                   minimizable:false,  
                   maximizable:false,  
                   resizable:false, 
                   onLoad : function() {    //初始化表单数据
                    $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            psName:{required: true}, 
                            pName:{required: true},
                            remark:{required: false,maxlength:250},  
                          },   

                         submitHandler: function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"addPosition",
                           data:postdata,
                           dataType:"text", 
                           async: false,
                           success:function(data){
                           if(data==1){
                            reloadtb();
                           }else{alert("添加失败");}	
                           },  
                           error:function(e) {  
                           alert("添加失败："+e);  
                           }  
                         });
                        $('#newDialog').dialog('close');  
                        }, 
                    invalidHandler: function(form, validator) {  //不通过回调   
                    return false;   
                    }   
                   });
                    },
                    onClose : function() {
                        $(this).dialog('destroy');
                    }, 
                    buttons:[{  
                        text:'提交',
                        iconCls: 'icon-ok',
                        handler: function(){
                         $("#submit").click();  
                        }
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res1").click();  
                        }  
                      }]                    
                });
 }    	                   
               
 </script>  
</body> 
</html>