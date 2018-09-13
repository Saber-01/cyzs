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
    <title>结算单位列表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="结算单位信息列表" data-options="
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
        结算单位: <input id='cuName' name='cuName' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
		<input id="res" name="res" type="reset" style="display:none;"/>
		</form>
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
	  //cuCode: $("#cuCode").val(),
	  cuName: $("#cuName").val(),
	});
}
	 
function reset(){
	   $("input[name='res']").click();
}
function getData(){
	 $("#dg").datagrid({  
	         url: "selectCheckUnitList1",
	         queryParams://每次请求的参数
	             {
	        	 cuName: $("#cuName").val(),
	             },
	         pagination: true,//表示在datagrid设置分页              
	         rownumbers: false,  
	         singleSelect: true,  
	         pageSize: 15,
	         pageList: [15, 20, 30,],
	         columns: [[  
	               { field: 'cuCode', title: '结算单位编号', width: '25%', align: 'center' },  
	               { field: 'cuName', title: '结算单位名', width: '40%', align: 'center' },  
	               {  
	                field: 'cuId',  
	                title: '操作',  
	                width: '34%',  
	                align: 'center',  
	                formatter: function(value, row, index) {  
	                        var b = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"   
	                        var c = "<a href='javascript:void(0)' onclick='del(\""+value+"\")'>删除</a>"
	                        return b +"     ||     "+c;  
	                 }  
	               }           
	           ]],  
	              
	       });

	 }
 function reloadtb(){
   $("#dg").datagrid("reload",{                          
         cuName:$("#cuName").val(),
    });
 }
function del(cuId){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"delete1",
            data:cuId,
            dataType:"text", 
            async: false,
            success:function(data){
            if(data==1){
              reloadtb();
            }else{alert("删除失败："+e);}	
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
	   var _url = "${pageContext.request.contextPath }/checkUnit/add";
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '添加结算单位基本信息',
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
                        $("#cuAdd").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            cuName:{required: true,rangelength:[1,50],
                            remote:{     //验证用户名是否存在
                         		type:"POST",
                          		url:"addTest",   
                          		data:{
                          			cuName:function(){return $("#cuName1").val();}
                              	} 
                          	}
                            }, 
                          },   

                         submitHandler: function(form) {  //通过之后回调   
                         var postdata = $("#cuAdd").serialize();   
                         $.ajax({   
                          type:"POST",  
                          url:"addCheckUnit1",
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
                      }]       
	                });
	 }


	 function edit(cuId){
		   var _url = "${pageContext.request.contextPath }/checkUnit/edit/" + cuId;
		   $('<div></div>').dialog({
		                    id : 'newDialog',
		                    title : '修改结算单位信息',
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
                    
                    $("#editCheckUnit").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            cuName:{required: true,rangelength:[1,50],
                            remote:{     //验证用户名是否存在
                         		type:"POST",
                          		url:"editTest",   
                          		data:{
                          			cuName:function(){return $("#cuName1").val();},
                          			cuId:function(){return $("#cuId").val();}
                              	} 
                          	}
                            },
                          },   
                         submitHandler: function(form) {  //通过之后回调   
                         var postdata = $("#editCheckUnit").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editCheckUnit1",
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
		                   


</script> 

  </body>
</html>
