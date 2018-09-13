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
    <title>工程名称及栋号与预算</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="工程名称及栋号与预算信息列表" data-options="
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
       		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
      </div>
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

 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "selectProjectList1",
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  

               { field: 'prCode', title: '项目编号', width: '8%', align: 'center' },  
               { field: 'prName', title: '项目名称', width: '12%', align: 'center' }, 
               { field: 'uName', title: '项目经理', width: '10%', align: 'center' },  
               { field: 'shenjiName', title: '审计', width: '10%', align: 'center' }, 
               { field: 'ckjlName', title: '成控经理', width: '10%', align: 'center' },  
               { field: 'ckfjlName', title: '成控副经理', width: '10%', align: 'center' }, 
               { field: 'zgysName', title: '主管预算', width: '10%', align: 'center' },  
               { field: 'prTime', title: '开始时间', width: '15%', align: 'center' }, 

               {  
                field: 'prId',  
                title: '操作',  
                width: '11%',  
                align: 'center',  
                formatter: function(value, row, index) {   
                var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
                var b = "<a href='detail1/"+value+"'>栋号</a>"
                var c = "<a href='javascript:void(0)' onclick='del(\""+value+"\")'>删除</a>"   
                   return a +"     ||     "+ b +"      ||      "+ c; 
                 }  
               }           
           ]],  
              
       });

 }
function add(){
	   var _url = "${pageContext.request.contextPath }/project/add";
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '添加工程信息',
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
	                     onLoad : function() { 
	                     $("#prTime").datebox({
    						onSelect:function(date){
   							 var a = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
   							 $("#prTime").val(a);
   							}
  							}),
                        $("#addProject").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            prName:{
                            required: true,
                            remote:{     //验证用户登录名是否存在
                          type:"POST",
                          url:"addTest",   
                          data:{prName:function(){return $("#prName").val();}
                              } 
                            }
                            }, 
                            uId:{required:true}
                          }, 
                          messages: {
                         prName:{remote:"该工程名称已经存在",required:"项目名不能为空！"},
                         uId:{required:"项目经理不能为空！"}
                         
                           },  

                         submitHandler: function(form) {  //通过之后回调   
                         var postdata = $("#addProject").serialize();   
                         $.ajax({   
                          type:"POST",  
                          url:"addProject1",
                          data:postdata,
                          dataType:"json", 
                          async: false,
                          success:function(data){
                          if(data==1){
                            reloadtb();
                           alert("成功");
                           $('#newDialog').dialog('close');
                         }else{alert("添加失败："+data);}	
                        },  
                          error:function(e) {  
                        alert("添加失败："+e);  
                        }    
                        });
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
                        $("#submit1").click();
                        }  
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res1").click();  
                        }  
                      },
                      {  
                        text:'取消',
                        iconCls: 'icon-cancel',
                        handler:function(){
                         $('#newDialog').dialog('close'); 
                        }  
                      }]              
	                });
	 }      
 function edit(prId){
	   var _url = "${pageContext.request.contextPath }/project/edit/" + prId;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '修改工程名称及栋号与预算信息',
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
	                    onLoad : function() {
                    	$("#prTime").datebox({
    						onSelect:function(date){
    						var a = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    						$("#prTime").val(a);
    						}
  						}),
                    $("#editProject").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            prName:{required: true,rangelength:[1,50],
                            remote:{     //验证用户登录名是否存在
                          			type:"POST",
                          			url:"editTest",   
                          			data:{ prName:function(){return $("#prName").val();},
                          					prId:function(){return $("#prId").val();}
                              		} 
                            	}
                            },
                            prTime:{dateISO:true},
                            uId:{required: true}, 
                          },   
                         submitHandler: function(form) {  //通过之后回调   
                         var postdata = $("#editProject").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editProject1",
                           data:postdata,
                           dataType:"text", 
                           async: false,
                           success:function(data){
                           if(data==1){
                            $('#newDialog').dialog('close');
                            reloadtb();
                           }else{alert("修改失败："+data);}	
                           },  
                           error:function(e) {  
                           alert("修改失败："+e);  
                           }  
                         });
                        
                      },  
                    invalidHandler: function(form, validator) {  //不通过回调   
                    return false;   
                    }   
                   });    //初始化表单数据
                    },
                    onClose : function() {
                        $(this).dialog('destroy');
                    },
                    buttons:[{  
                        text:'提交',
                        iconCls: 'icon-ok',
                        handler:function(){
                        $("#submit2").click(); 
                        }  
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res2").click();  
                        }  
                      },
                      {  
                        text:'取消',
                        iconCls: 'icon-cancel',
                        handler:function(){
                         $('#newDialog').dialog('close'); 
                        }  
                      }
                      ]          
	                });
}
function del(prId){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"deleteProject1",
            data:prId,
            dataType:"text", 
            async: false,
            success:function(data){
            if(data==1){
              reloadtb();
            }else{alert("删除失败："+data);}	
            },  
            error:function(e) {  
              alert("删除失败："+e);  
            }  
        });
	} else {
		return;
	}
 }	                   
function reloadtb(){
   $("#dg").datagrid("reload",{                          
         
    });
 }          
 </script>  
</body> 
</html>