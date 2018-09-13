<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>success</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	<script type="text/javascript">
    if(${flag !=null && flag == 1}) {
    	alert("任务书提交成功!（状态:逾期）");
    	window.location.href = "${pageContext.request.contextPath}/missionSubmitList/";
    }	
    if(${flag !=null && flag == 0}) {
    	alert("任务书提交成功!（状态:正常）");
    	window.location.href = "${pageContext.request.contextPath}/missionSubmitList/";
    }
    if(${delcomplete !=null && delcomplete == 1}) {
    	alert("删除成功");
    	window.location.href = "${pageContext.request.contextPath}/toDelBackMission/";
    }
    
	</script>
  </body>
</html>
