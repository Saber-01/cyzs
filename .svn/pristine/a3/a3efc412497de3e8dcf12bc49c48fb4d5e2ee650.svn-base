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
    <title>查看任务书审核历史记录</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
    <input id="uName" name="uName" type="hidden" value="${user.userName}"/>
    <input id="rName" name="rName" type="hidden" value="${user.roleName}"/>
       <table id="dg" style="width:100%;height:99%" title="任务书审核历史记录列表" data-options="
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
      任务书类型：<select name="mtId" id="mtId" style="width:120px;height:35px;line-height:35px;">
      <option value="">---请选择---</option>
                     <c:forEach items="${mtList}" var="mt">
                        <option value="${mt.mtId}">${mt.mtName}</option>
                      </c:forEach>
      </select>
		任务书编号：<input id='mCode' name='mCode' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
		工程名称：<select name="prId" id="prId" style="width:120px;height:35px;line-height:35px;">
			      <option value="">---请选择---</option>
                     <c:forEach items="${projectList}" var="pj">
                        <option value="${pj.prId}">${pj.prName}</option>
                      </c:forEach>
			</select>
		审核人：<input id='userName' name='userName' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
		
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
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
     mtId: $("#mtId").val(),
     mCode: $("#mCode").val().trim(),
     prId: $("#prId").val(),
     userName: $("#userName").val(),
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "auditInfoSelect",
         queryParams://每次请求的参数
             {
              mtId: $("#mtId").val(),
              mCode: $("#mCode").val().trim(),
              prId: $("#prId").val(),
              userName: $("#userName").val(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '任务书编号', width: '12%', align: 'center' },
               { field: 'prName', title: '工程名称', width: '13%', align: 'center' },  
               { field: 'mtName', title: '任务书类型', width: '12%', align: 'center' },  
               { field: 'auditResult', title: '审核结果', width: '16%', align: 'center', 
               formatter: function(value, row, index) {
                 var a= row.roleName;
                 if(value==1){ a+="审核通过";}
                 if(value==2){ a+="暂不处理";}
                 if(value==0){ a+="审核不通过，驳回";}
                 if(value==-1){ a+="新建\\修改任务书";}
                 return a;
                }     
               },
               { field: 'userName', title: '审核人', width: '12%', align: 'center' },
               { field: 'auditTime', title: '审核时间', width: '18%', align: 'center' },
               {  
                field: 'aId',  
                title: '操作',  
                width: '14%',  
                align: 'center',  
                formatter: function(value, row, index) {
                var userName = $("#uName").val();
                var roleName = $("#rName").val();
                var a = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>查看意见</a>" 
                if(row.userName==userName && row.roleName==roleName){
                  var b = "||&nbsp;<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>补充意见</a>"
                  return a+" "+b;
                }else{return a;} 
                }  
              }           
           ]],  
              
       });

 }
 
 function view(aId){
 var _url = "${pageContext.request.contextPath}/viewAuditComment?aId="+aId;
 
 
 $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '审核意见详细信息',
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
 
 function edit(aId){
  _url = "${pageContext.request.contextPath}/toAddAuditComment?aId="+aId;
  
  $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '补充意见',
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
                    
                    $("#addAuditComment").validate({
                         onsubmit:true,// 是否在提交是验证   
                         onfocusout:false,// 是否在获取焦点时验证   
                         onkeyup :false,// 是否在敲击键盘时验证   
                         rules: {auditComment:{required: true}},
                         messages: {auditComment:{required:"意见不能为空"}},  
                         submitHandler: function(form) {  //通过之后回调   
                           //var postdata = $("#addAuditComment").serialize();
                           var auditComment = $("#auditComment").val();
                           var postdata = {"aId": aId, "auditComment": auditComment};
                           $.ajax({  
                            type:"POST",  
                            url:"addAuditComment",
                            data:postdata,
                            dataType:"text", 
                            async: false,
                            success:function(data){
                            if(data==1){
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