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
<title>修改工作项目</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
   $(document).ready(function(){
   
   $("#pId").bind('change',function () { 
         var pId = $(this).val();       
         if(pId!= null)
         {          getPartPosition(pId);         }
 
 });
 
 function getPartPosition(pId)
{  
    
    var json = {"pId":pId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"job/onChange",
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
}});
         
          
</script>
</head>

<body>

	
	<form id="form" action="job/editJob" method="post">
	<input type="hidden" id="jobKey" name="jobKey" value="${job.jobKey}"/>
	<table class="kv-table" id="addJob">
		<tr>
                <td class="tb-left">工作项目编号：</td>
                <td  class="kv-label">
                   <input type="text" name="jobId" id="jobId" value="${job.jobId}" readonly="true" style="height:25px" >                  
                </td>
         </tr>
		<tr>
                <td class="tb-left">分部：</td>
                <td  class="kv-label">
                   <select name="pId" id="pId" style="width:153px;height:30px">
                      <option value="">----请选择----</option>   
                     <c:forEach items="${partList}" var="r">                       
                       <option value="${r.pId}">${r.pName}</option>
                      </c:forEach>
                    </select>
                </td>
         </tr>
         		<tr>
                <td class="tb-left">工程部位：</td>
                <td  class="kv-label">
                   <select name="psId" id=psId style="width:153px;height:30px">
                      <option value="${job.psId}">${partPosition.psName}</option>                
                    </select>
                </td>
         </tr>
		<tr>
		<td class="tb-left">工作项目名称：</td>
        <td  class="kv-label"><textarea id ='jobName' name='jobName' rows="2" style="width:152px;height:60px">${job.jobName}</textarea>
        	<br><div style="margin-top:5px">格式：不能超过50个汉字，不能重复</div></td>
		</tr>
			<tr>
                <td class="tb-left">单位：</td>
                <td  class="kv-label">
                   <select name="unId" id=unId style="width:153px;height:30px">                   
                     <option value="${job.unId}">${unit.unName}</option>                                       
                     <c:forEach items="${unitList}" var="r">
                       <option value="${r.unId}">${r.unName}</option>
                      </c:forEach>
                    </select>
                </td>
         </tr>	
		<tr>
		<td class="tb-left">备注：</td>
        <td class="kv-label"><textarea id ='remark' name='remark' rows="8" style="width:153px;height:60px">${job.remark} </textarea>
        	格式：不超过250个汉字</td>
		</tr>	
		</table>
		 <input id="res1" type="reset" value="重置" style="display:none"/> 
		 <input id="submit" type="submit" value="重置" style="display:none"/>
	</form>
</body>
</html>
