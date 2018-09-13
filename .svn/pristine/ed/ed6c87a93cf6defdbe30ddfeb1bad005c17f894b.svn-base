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
    <title>工作项目基本信息列表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">

</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="工作项目基本信息列表" data-options="
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
		工程部位: <select name="psName" id="psName" style="width:170px;height:35px;line-height:35px;">
					  <option value="">----请选择----</option>
					  <c:forEach items="${pList}" var="r">
                        <option value="${r.psName}">${r.psName}</option>
                      </c:forEach>
			</select>
       工作项目: <input id='jobName' name='jobName' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
       
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
		<input id="res" name="res" type="reset" style="display:none;"/>
		</form>
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
	  jobName: $("#jobName").val(),
	  psName: $("#psName").val(),
 });
}

function reset(){
  $("input[name='res']").click();
}
 function getData(){
 $("#dg").datagrid({  
         url: "selectJob",
         queryParams://每次请求的参数
             {
          psName: $("#psName").val(),
       	  pName: $("#jobName").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'jobId', title: '工作项目编号', width: '10%', align: 'center' },  
               { field: 'jobName', title: '工程项目', width: '35%', align: 'center' },  
               { field: 'psName', title: '工程部位', width: '20%', align: 'center' },  
               { field: 'unName', title: '单位', width: '10%', align: 'center' }, 
               {  
                field: 'jobKey',  
                title: '操作',  
                width: '23%',  
                align: 'center',  
                formatter: function(value, row, index) {  
                var c = "<a href='javascript:void(0)' onclick='remark(\""+value+"\")'>备注</a>" 
                var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
                var b = "<a href='javascript:void(0)' onclick='del(\""+value+"\")'>删除</a>"   
                   return c +"     ||     "+a +"     ||     "+ b; 
                 }  
               }           
           ]],  
              
       });

 }
      
 function edit(id){
	   var _url = "${pageContext.request.contextPath }/job/toEditJob/" + id;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '修改工作项目',
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
	                    $("#pId").bind('input propertychange',function () { 
                            var pId = $(this).val();       
                              if(pId!= null)
                                 getPartPosition(pId);         
                           });
	                    $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            jobId:{required: true},
                            psId:{required: true}, 
                            pId:{required: true},
                            jobName:{required: true,maxlength:50}, 
                            unId:{required: true}, 
                            remark:{required: false,maxlength:250},  
                          },   

                         submitHandler:function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editJob",
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
	  jobName: $("#jobName").val(),
	  psName: $("#psName").val(),
 });
}	                   
 function del(id){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"deleteJob/"+id,
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
   var _url = "${pageContext.request.contextPath }/job/toAddJob";
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '添加工作项目',
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
                   $("#pId").bind('input propertychange',function () { 
                            var pId = $(this).val();       
                              if(pId!= null)
                                 getPartPosition(pId);         
                           });
	                    $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                           
                            psId:{required: true}, 
                            pId:{required: false},
                            jobName:{required: true,maxlength:50}, 
                            unId:{required: true}, 
                            remark:{required: false,maxlength:250},  
                          },   

                         submitHandler:function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"addJob",
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
                        }  , 
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
 
    function remark(id){
   var _url = "${pageContext.request.contextPath }/job/toViewRemark/"+id;
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '查看备注信息',
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
                    },
                    onClose : function() {
                        $(this).dialog('destroy');
                    },
                    buttons:[{  
                        text:'确定',
                        iconCls: 'icon-ok',
                        handler:function(){
                        	$('#newDialog').dialog('close');  
                        } 
                      }],
                });
 }   	                   
   

 function getPartPosition(pId)
{  
    
    var json = {"pId":pId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"onChange",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#psId").empty(); 
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.psName).val(item.psId);
                 $("#psId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
}
        
 </script>  
</body> 
</html>