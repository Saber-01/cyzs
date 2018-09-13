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
    <title>分部基本信息列表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="分部基本信息列表" data-options="
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
       
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
        
      </div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


<script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>
  

    
<script type="text/javascript"> 
  
getData();
function select(){
  $("#dg").datagrid("load",{                          
	  partId: $("#partId").val(),
	  pName: $("#pName").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "getAllPart1",
         queryParams://每次请求的参数
             {

             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'partId', title: '分部编号', width: '34%', align: 'center' },  
               { field: 'pName', title: '分部名', width: '30%', align: 'center' }, 
               {  
                field: 'pId',  
                title: '操作',  
                width: '35%',  
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
 
 function edit(pId){
	   var _url = "${pageContext.request.contextPath }/part/toEditPart/" + pId;
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
	                       $("#editPart").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            partId:{required: true}, 
                            pName:{required: true}, 
                          },   

                         submitHandler: function(){
                         var postdata = $("#editPart").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editPart",
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

    });
 }
 
 function del(id){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"deletePart/"+id,
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
   var _url = "${pageContext.request.contextPath }/part/toAddPart";
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '添加分部',
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
                   $("#addPart").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            
                            pName:{required: true}, 
                          },   

                         submitHandler: function(){
                         var postdata = $("#addPart").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"addPart",
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