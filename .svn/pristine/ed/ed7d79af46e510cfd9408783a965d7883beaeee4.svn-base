<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>导入合同单价</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/sunny/jquery-ui.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.2em;">修改合同单价</font>
				</td>
			</tr>
		</tbody>
</table>


<c:if test="${not empty msg}"><p style="color:red">${msg}</p></c:if>
<c:if test="${not empty msgsuccess}"><p style="color:green">${msgsuccess}</p></c:if>
<br>
    <form  action="contract/uploadFile" name="fileuploadform" method="POST" enctype="multipart/form-data">
		<!-- <input type="file" id="file" name="uploadfile" maxlength="200">
		<input type="button" value="开始上传" onclick="if(validate())fileuploadform.submit();"/> -->
		<input class="easyui-filebox" id="uploadfile" name="uploadfile" data-options="buttonText:'选择文件',prompt:'选择上传的文件...'" style="width:260px;height:34px">
		<input type="button" class="easyui-linkbutton" onclick="if(validate())fileuploadform.submit();" value="开始上传">
		
		&nbsp;&nbsp;<a href="<%=basePath%>/contract/downloadFile">范本下载</a>
	</form> 
<form	action="contract/importExcel" name="importExcel" method="post"> 
<input type="hidden" name="filename" id="filename" value="${filename}">
</form>	
	   <input type="button" value="导入数据库" class="easyui-linkbutton" onclick="if(validate1())importExcel.submit();"/>
		<br><br>
		<div style="padding-left:20px;">
		注：<br />
		 请先上传excel文件，再点击导入数据库；<br />
   		模板中的工程名称只能有一个；<br />
   		模板中结算单位、栋号、分部等信息可以有多个，但要注意其组合范围；<br />
   		分项1不能为空,不能超过25个汉字（包括标点符号）；<br />
   		分项1、2、3要按顺序从左往右放置，不能出现分项2为空，分项3不为空的情况。<br />				
		</div>
<script type="text/javascript">

function importExcel()
 {  
    console.log($("#filename").val());  
    var filename = $("#filename").val();  
    if(filename != ""){  
        $.ajax({  
               cache: false,  
               type: "POST", 
               contentType: "application/json;charset=utf-8", 
               url:"contract/importExcel",  
               data:filename,  
               dataType:"String",
               async: false,  
               error: function(data){  
                    alert("访问后台失败!");               
               },    
               success: function(data) {
               if(data)  
                   {alert("导入成功!");
                   windows.location.href}
               }  
          });   
    }else{  
        alert("请选择文件");  
    }  
}  

function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else 
			return false;
	}


function validate(){
     // var input=document.getElementById("file").value;
     var input=$("#uploadfile").filebox("getValue");
     console.log(input);
     if(isEmpty(input)){
            {
				alert("请选择文件")
				return false;
			}
  }

  return true;
  
}
function validate1(){
    var input=document.getElementById("filename").value;
    console.log(input);
    if(isEmpty(input)){
           {
				alert("请先上传文件")
				return false;
			}
 }
 return true; 
}

    </script>
</body>
</html>
