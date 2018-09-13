 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>   
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>重庆诚业建筑工程有限公司</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/pages/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/pages/css/platform.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css" />
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.spritely-0.6.js"></script>
    <script type="text/javascript" src="<%=path%>/js/chur.min.js"></script>
    
    <script type="text/javascript">
        $(function () {
            $('#clouds').pan({ fps: 20, speed: 0.7, dir: 'right', depth: 10 });
            $('.login').click(function () {
                if ($('#uid').val() == "" || $('#pwd').val() == "" || $('#code').val() == "") { $('.tip').html('用户名或密码不可为空！') }
                else {
                    location.href = 'index.html';
                }
            })
        })
    </script>
</head>
<body>
    <div id="clouds" class="stage"></div>
    <div class="loginmain"></div>
    <form id="login" action="login/login" method="post">  
    <div class="row-fluid">
        <h1>重庆诚业建筑工程有限公司</h1>
        <p>
            <label>帐&nbsp;&nbsp;&nbsp;号：<input type="text" id="name" name="name" /></label>
        </p>
        <p>
            <label>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password" /></label>
        </p>
        <p class="pcode">
            <label>效验码：<input type="text" id="code" maxlength="5" class="code" value="e5g88" />
            <img src="img/code.gif" alt="" class="imgcode" /><a href="#">下一张</a></label>
        </p>
        <p class="tip">&nbsp;</p>
        <hr />
        <input type="submit" name="submit" value=" 登 录 " />&nbsp;&nbsp;&nbsp;<input type="button" value=" 取 消 " />
    </div>
    </form>
    
    
    
</body>
</html>