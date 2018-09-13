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
    <title>用户管理</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="角色管理列表" data-options="
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
		所属角色：<select name="roleId1" id="roleId1" style="width:120px;height:35px;line-height:35px;margin:10px">
					  <option value="">-----请选择------</option>
					  <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
			</select>
		用户编号:<input id ='userId1' name='userId1' class="easyui-textbox" type="text" style="width:120px;height:35px;line-height:35px;"></input>
			
        用户名:<input id ='userName1' name='userName1' class="easyui-textbox" type="text" style="width:120px;height:35px;line-height:35px;"></input>
        用户登录名:<input id ='userLoginName1' name='userLoginName1' class="easyui-textbox" type="text" style="width:120px;height:35px;line-height:35px;"></input>
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
      roleId: $("#roleId1").val(),
     userId: $("#userId1").val(),
     userName:$("#userName1").val(),
     userLoginName:$("#userLoginName1").val(),
  });
 }
 
 
 function reloadtb(){
 $("#dg").datagrid("reload",{                          
      roleId: $("#roleId1").val(),
     userId: $("#userId1").val(),
     userName:$("#userName1").val(),
     userLoginName:$("#userLoginName1").val(),
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "userSelect",
         queryParams://每次请求的参数
             {
              roleId: $("#roleId1").val(),
              userId: $("#userId1").val(),
              userName:$("#userName1").val(),
              userLoginName:$("#userLoginName1").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'userId', title: '用户编号', width: '13%', align: 'center' },  
               { field: 'userName', title: '用户名', width: '17%', align: 'center' },  
               { field: 'userLoginName', title: '用户登录名', width: '16%', align: 'center' },
               { field: 'roleId', title: '所属角色编号', width: '17%', align: 'center' },
               { field: 'roleName', title: '所属角色名称', width: '17%', align: 'center' },
               {  
                field: 'uId',  
                title: '操作',  
                width: '17%',  
                align: 'center',  
                formatter: function(value, row, index) {
                   var a = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>详细</a>"
                   var b = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
                   var c = "<a href='javascript:void(0)' onclick='del(\""+value+"\")'>删除</a>"  
                   return a +"     ||     "+ b+ "      ||     "+ c; 
                 }  
               }           
           ]],  
              
       });

 }
 
  function view(uId){
   var _url = "${pageContext.request.contextPath }/user/viewUser/" + uId;
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '查看用户',
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
                      }]  
                });
 }
 
 
 function add(){
  var _url = "${pageContext.request.contextPath }/user/toAddUser";
  $('<div></div>').dialog({  
           id : 'newDialog',
                    title : '添加用户',
                    width : 602,
                    height : 480,
                    closed : false,
                    cache : false,
                    href : _url,
                    modal : true,
                    onLoad : function() {
                    $("#adduserform").validate({
                    onsubmit:true,// 是否在提交是验证   
                    onfocusout:false,// 是否在获取焦点时验证   
                    onkeyup :false,// 是否在敲击键盘时验证   
                    rules: { 
                     roId:{
                       required: true,notNull:"#roId"
                            },
                     userId:{
                       required: true, digits:true
                            }, 
                     userName:{
                       required: true, isnotChinese:"#userName"
                              },
                     userLoginName:{
                       required: true, EnglishNumber:"#userLoginName",
                       remote:{     //验证用户登录名是否存在
                          type:"POST",
                          url:"vfyLgNameAdd",   
                          data:{userLoginName:function(){return $("#userLoginName").val();}
                              } 
                            } 
                          },
                    userPassword:{
                       required: true, Password:[6,20]
                             } 
                          },
                   messages: {
                   userLoginName:{remote:"该登录名已经存在"}
                   
                   },  

                  submitHandler: function(form) {  //通过之后回调   
                   var postdata = $("#adduserform").serialize();   
                   $.ajax({   
                      type:"POST",  
                      url:"addUser",
                      data:postdata,
                      dataType:"text", 
                      async: false,
                      success:function(data){
                       if(data==1){
                        reloadtb();
                        $('#newDialog').dialog('close');
                       }else{alert("添加失败："+e);}	
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
      
 function edit(uId){
   var _url = "${pageContext.request.contextPath }/user/toEditUser/" + uId;
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '修改用户',
                    width : 602,
                    height : 480,
                    closed : false,
                    cache : false,
                    href : _url,
                    modal : true,
                    onLoad : function() {
                      $("#edituserform").validate({
                    onsubmit:true,// 是否在提交是验证   
                    onfocusout:false,// 是否在获取焦点时验证   
                    onkeyup :false,// 是否在敲击键盘时验证   
                    rules: { 
                     roId:{
                       required: true,notNull:"#roId"
                            }, 
                     userId:{
                       required: true, digits:true
                            }, 
                     userName:{
                       required: true, isnotChinese:"#userName"
                              },
                     userLoginName:{
                       required: true, EnglishNumber:"#userLoginName",
                       remote:{     //验证用户登录名是否存在
                          type:"POST",
                          url:"vfyLgNameEdit",   
                          data:{userLoginName:function(){return $("#userLoginName").val();},
                                uId:function(){return $("#uId").val();}
                              } 
                            }
                              }, 
                    userPassword:{
                       required: true, Password:[6,20]
                             } 
                          }, 
                   messages: {
                   userLoginName:{remote:"该登录名已经存在"}
                   
                   },  

                  submitHandler: function(form) { //通过之后回调  
                  var user = {};
                  var data= {};
                  user.uId = $("#uId").val();
                  user.userId = $("#userId").val();
                  user.userName = $("#userName").val();
                  user.userLoginName = $("#userLoginName").val();
                  user.userPassword = $("#userPassword").val();
                  user.userRemark = $("#userRemark").val();
                  
                   var roId = new Array();
                   $("input[name=checkboxname]:checked").each(function() {   
                    roId.push($(this).val());   
                   });
                   data.user = user;
                   data.roIdList = roId;
                   var postdata = JSON.stringify(data)   
                   //alert(postdata);
                         $.ajax({ 
                             contentType: 'application/json', 
                             type:"POST",  
                             url:"editUser",
                             data:postdata,
                             dataType:"text", 
                             async: false,
                             success:function(data){
                             if(data==1){
                              reloadtb();
                              $('#newDialog').dialog('close');
                             }else{alert("修改失败："+e);}	
                             },  
                             error:function(e) {  
                             alert("修改失败："+e);  
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
 
 
 function del(uId){
 var flag = confirm('是否确认删除？');
   if (flag) {
	$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"deleteUser",
            data:uId,
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
            
 

   
   
 </script>  
</body> 
</html>