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
    <title>查看任务书</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
</head> 
<body>
    <div class="container" style="height: 100%;">
       <table id="dg" style="width:100%;height:99%" title="已删除任务书信息列表" data-options="
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
      输入15位编号 : <input id='mCode' name='mCode' class="easyui-textbox" type="text" style="width:166px;height:35px;line-height:35px;"></input>
		任务书类型 : <select name="mtId" id="mtId" style="width:120px;height:35px;line-height:35px;">
					  <option value="">----请选择----</option>
                     <c:forEach items="${mtList}" var="mt">
                        <option value="${mt.mtId}">${mt.mtName}</option>
                      </c:forEach>
			</select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
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
     mtId:$("#mtId").val(),
     mCode: $("#mCode").val().trim()
  });
 }
 
 function reset(){
   $("input[name='res']").click();
 }
 function getData(){
 $("#dg").datagrid({  
         url: "missionDeleteSelect",
         queryParams://每次请求的参数
             {
              mtId: $("#mtId").val(),
              mCode: $("#mCode").val().trim(),
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30,],
         columns: [[  
               { field: 'mCode', title: '编号', width: '11%', align: 'center' },  
               { field: 'mtName', title: '类型', width: '11%', align: 'center' },  
               { field: 'prName', title: '工程名称', width: '14%', align: 'center' },
               { field: 'unitNumber', title: '栋号', width: '7%', align: 'center' },
               { field: 'bAndeDate', title: '起止时间', width: '15%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '7%', align: 'center' },
               { field: 'submitTime', title: '提交时间', width: '13%', align: 'center' },
               { field: 'missionStatus', title: '状态', width: '8%', align: 'center' },
               {  
                field: 'mId',  
                title: '操作',  
                width: '10%',  
                align: 'center',  
                formatter: function(value, row, index) {
                   var a = "<a href='javascript:void(0)' onclick='view(\""+value+"\")'>详细查看</a>&nbsp;||"
                    var b = "<a href='javascript:void(0)' onclick='recover(\""+value+"\")'>恢复</a>"  
                   return a+" "+b; 
                 }  
               }           
           ]],  
              
       });

 }
 
 function view(mId){
 window.location.href = "${pageContext.request.contextPath}/viewDelMission?mId="+mId;
 }
 
 function recover(mId){
 $.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"missionRecov",
            data:mId,
            dataType:"text", 
            async: false,
            success:function(data){
            if(data==1){
              $("#dg").datagrid("reload",{                          
                 mtId:$("#mtId").val(),
                 mCode: $("#mCode").val().trim()
               });
             alert("恢复成功"); 
            }else{alert("未恢复成功："+e);}
            	
            },  
            error:function(e) {  
              alert("未恢复成功："+e);  
            }  
        });

 }

 
       
             
          
 </script>  
</body> 
</html>