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
<title>添加合同单价</title>
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


  $("#button_submit").click(function(){
  addPrice();
  });
  
  $("#prId").bind('change',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);         }
         else{  $("#pcpId").empty(); 
                }
 
  });
  
  $("#pId").bind('change',function () { 
         var pId = $(this).val();       
         if(pId!= null  && pId!='')
         {          getPosition(pId);         }
         else{  $("#psId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#psId").append(option1);}
 
  });
  
  $("#psId").bind('change',function () { 
         var psId = $(this).val();       
         if(psId!= null  && psId!='')
         {          getJobName(psId);         }
         else{   $("#jobKey").empty();             
                var option1=$("<option>").text('---请选择---').val(null);
                $("#jobKey").append(option1);}
 
  });
  
     $("#unId").bind('onchange',function () { 
         var jobKey = $(this).val();       
         if(jobKey!= null  && jobKey!='')
         {          getUnName(jobKey);         }
     
  });
  $("#test1").on('click', function(){
  layer.open({
  type: 1,
  area: ['600px', '360px'],
  shadeClose: true, //点击遮罩关闭
  content: '\<\div style="padding:20px;">自定义内容\<\/div>'
  });
});
  
});
//联动
 function  getPcpNumber(prId)
{  
    
    var json = {"prId":prId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#pcpId").empty(); 
                $.each(data, function(index,item){ 
                $("#pcpId").append("<input type='checkbox' id='pcpId' name='pcpId' value='"+item.pcPId+"'>"+item.unitNumber+"</input>");                               
                   
                })},
                error:function(e) {   alert("出错了");              
                }  
                
                
     });
};

 function  getPosition(pId)
{  
    
    var json = {"pId":pId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changePosition",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#psId").empty(); 
               var option1=$("<option>").text('---请选择---').val(null);
                $("#psId").append(option1);
                $.each(data, function(index,item){                
                 var option = $("<option>").text(item.psName).val(item.psId);                
                 $("#psId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getJobName(psId)
{  
    
    var json = {"psId":psId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changeJob",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#jobKey").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#jobKey").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.jobName).val(item.jobKey);
                 $("#jobKey").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getUnName(jobKey)
{  
    
    var json = {"jobKey":jobKey};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changeUnitName",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#unId").empty(); 
   
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.unName).val(item.unId);
                 $("#unId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};
function checkAll(name)
{
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = true;
}
}
}

function clearAll(name)
{
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = false;
}
}
}
//提交添加信息
function addPrice()
{
   var jsons=[]; 
   $("[name=pcpId]:checked").each(function(){  
   var json = {};                                 
   json['prId'] =$("#prId").val();;
   json['pId'] =$("#pId").val();;
   json['psId'] = $("#psId").val(); ;
   json['jobKey'] = $("#jobKey").val(); ;
   json['pcpId'] = $(this).val();; //如果选中，将value添加到变量s中
   json['unId'] = $("#unId").val(); ;
   json['cuId'] = $("#cuId").val(); ;
   json['price'] = $("#price").val(); ;
   jsons.push(json);
     } );
    var postdata = JSON.stringify(jsons);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"contract/addPrice", 
                dataType:"string", 
                data:postdata,
                async:false, 
                success:function(data){
                 if (data == "0")alert("添加失败！");
                 else { alert("添加成功！");} 
              }
     });
}

</script> 
</head>

<body>

	
	  <form id="form"> 
      	<table class="kv-table">

            <tr>
                <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">工程名称：</td>
                <td  class="kv-label">
                   <select name="prId" id="prId1" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                </td>
                <td class="kv-label"/>
           </tr>
           <tr>     
                 <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">栋号：</td>
                <td  class="kv-label" id="pcpId">
                </td>
                <td class="kv-label"><span id="click"></span></td>
          </tr>
              
     
               
              
          <tr>
                <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">分部：</td>
                <td  class="kv-label">
                   <select name="pId" id="pId1" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pId}">${r.pName}</option>
                      </c:forEach>
                    </select>
                </td>
                <td class="kv-label"/>
           </tr>    
           <tr>      
                  <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">工程部位/分项1：</td>
                <td  class="kv-label">
                   <select name="psId" id="psId" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                    </select>
                </td>
                <td class="kv-label"/>
           </tr>
           <tr> 
                <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">工作项目/分项2：</td>
                <td  class="kv-label">
                   <select name="jobKey" id="jobKey" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                    </select>
                </td>
                <td class="kv-label"/>                
          </tr>
          
          <tr>      
                <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">结算单位：</td>
                <td  class="kv-label">
                   <select name="cuId" id="cuId" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${cuList}" var="r">
                        <option value="${r.cuId}">${r.cuName}</option>
                      </c:forEach>
                    </select>
                </td>
                <td class="kv-label"/>
          </tr>     
          
          <tr>      
                <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">单位：</td>
                <td  class="kv-label">
                   <select name="unId" id="unId" style="width:153px;height:30px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${unList}" var="r">
                        <option value="${r.unId}">${r.unName}</option>
                      </c:forEach>
                    </select>
                </td>
                <td class="kv-label"/>
          </tr> 
          <tr>
              <td class="kv-label" style="width:37%;text-align:left;padding-left:10%">单价：</td>
                <td  class="kv-label">
                    <input type="text" id ="price" name="price"></td>
               <td class="kv-label"/>
          </tr>                    
</table>
        <input id="res1" type="reset" value="重置" style="display:none;"/> 
	    <input id="submit" name="submit" type="submit" style="display:none;"/>
</form>
</body>
</html>
