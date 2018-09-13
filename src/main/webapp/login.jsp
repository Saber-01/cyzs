 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head> 
    <title>重庆诚业建筑工程有限公司</title> 
    
    <link rel="stylesheet" type="text/css" href="css/base.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css"/>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	

   <script type="text/javascript">  
    
$(document).ready(function(){
         $("#dropdownlist").hide();
         $("#name").bind('input propertychange',function () { 
         var userLoginName = $("#name").val().trim();
         if(userLoginName != null && userLoginName != "")
         {
          getRole(userLoginName);
         }
         
    });
});



function getRole(loginName)
{        
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"login/checkRole",
                dataType:"json", 
                data:loginName,
                success:function(data){
                 $("#roId").empty(); 
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.roleName).val(item.roId);
                 $("#roId").append(option);
                 })
                $("#dropdownlist").show();          
                },  
                error:function(e) {  
                  $("#dropdownlist").hide();   
                }  
     });
}


	   /** 给登录按钮绑定点击事件  */

	function Click() {
		/** 校验登录参数 ctrl+K */
		var name = document.getElementById("name").value;
		var password = document.getElementById("password").value;
		var roId = document.getElementById("roId").value;
		if (name == "" || name == null) {
 			document.getElementById("message").innerHTML = "*请输入用户名";
 			return false;
 		}
		else if (password == "" || password == null) {
	 		document.getElementById("message").innerHTML = "*请输入密码";
	 		return false;
	 	}
		else {
			/** 提交表单 */
			document.getElementById("login").submit();
		}
		
	}
</script>  
</head>  
<body class="white">
	<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<!-- <span class="logo"></span>
			<span class="split"></span> -->
			<span class="sys-name">重庆诚业建筑工程有限公司</span>
		</div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label"><h4>用户登录</h4></div>
						<span id="message" style="display:inlie-block; width:200px;height:20px;line-height:20px;padding-left:25px;
									margin:10px; color:red; font-size: 14px">
								${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['message']}
						</span>
						<form id="login" action="login/login" method="post">
							<div class="lg-username input-item clearfix">
							
								<input type="text" placeholder="&nbsp;&nbsp;&nbsp;账号" id="name" name="name" style="padding-left: 10px;"/>
								
							</div>
							<div id = "dropdownlist" class="lg-rolechoice input-item clearfix">
								<select name="roId" id="roId" style="height: 40px;width: 296px;padding-left: 10px" >
								  
							</select>
							</div>
							<div class="lg-password input-item clearfix">
								<input type="password" id="password" name="password" required="true" placeholder="&nbsp;&nbsp;&nbsp;请输入密码" style="padding-left: 10px;"/>
							</div>		
							<div class="enter">
								<a href="javascript:;" class="purchaser" onclick="Click()">登录</a>
								<a href="javascript:;" class="supplier" >取消</a>
							</div>
						</form>
						<div class="line line-y"></div>
						<div class="line line-g"></div>
					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<!-- <div class="login-ft">
		<div class="ft-inner">
			<div class="about-us">
				<a href="javascript:;">关于我们</a>
				<a href="javascript:;">法律声明</a>
				<a href="javascript:;">服务条款</a>
				<a href="javascript:;"></>联系方式</a>
			</div>
			<div class="address">地址：重庆市沙坪坝区重庆大学虎溪校区425办公室&nbsp;邮编：210019&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2015&nbsp;-&nbsp;2017&nbsp;朱胜男-专注于ui设计&nbsp;版权所有</div>
			<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;渝ICP备&nbsp;09003078号&nbsp;E-mail：zhu_elena@163.com</div>
		</div>
	</div> -->
</body> 
</html>
    
