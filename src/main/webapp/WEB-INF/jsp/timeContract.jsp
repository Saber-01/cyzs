<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同单价表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->



<style type="text/css">
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#customers td, #customers th {
	font-size: 1em;
	border: 1px solid #980f21;
	padding: 3px 7px 2px 7px;
}

#customers th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background: #efefef;
	font-weight: bold;
	color: #323D53;
}

#customers tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}
</style>

 
<script type="text/javascript">

$(document).ready(function(){
paging(1);

  $("#button_submit").click(function(){
   paging(1);
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


function paging(page)
{

   var json = {};
   json['prName'] =$("#prName").val();;
   json['pName'] =$("#pName").val();;
   json['psName'] = $("#psName").val(); ;
   json['jobName'] = $("#jobName").val(); ;
   json['fsName'] = $("#fsName").val(); ;
   json['pcpNumber'] = $("#pcpNumber").val(); ;
   json['unName'] = $("#unName").val(); ;
   json['cuName'] = $("#cuName").val(); ;
   json['price'] = $("#price").val(); ;
   json['pageNo'] = page;
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"contract/selectContract", 
                dataType:"json", 
                data:postdata,
                success:function(data){
                var options = {
               alignment:'center',
               size: 'large',
               currentPage: data.pageNum,
               totalPages: data.pages,
               numberOfPages:5,
                itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },
               onPageChanged: function(e,oldPage,newpage){
                paging(newpage);}
                         }
        $('#example').bootstrapPaginator(options);
                
                
               
                
            
              
                $.each(data.list, function(index,item){  
                  
                    $("#customers").append("<tr bgcolor='white'><td>"+item.prName+"</td><td algin='left'>"+item.pName+"</td><td>"+item.psName+"</td><td>"+item.jobName+"</td><td>"+item.fsName+"</td><td>"+item.pcpNumber+"</td><td>"+item.unName+"</td><td>"+item.cuName+"</td><td>"+item.price+"</td></tr>");                               
                                                       
                })
                },  
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
     });

}


</script> 

</head>
  <body>
  
   <form id="user"> 
      <table class="table table-striped table-bordered table-condensed">
         <thead>
            <tr>
                <td colspan="6" class="auto-style2">&nbsp;请填写查询条件 </td>
            </tr>
          </thead>
         <tbody>
            <tr>
                <td>工程名称</td>
                <td>
                   <select name="prName" id=prName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                
                 <td>栋号</td>
                <td>
                   <select name="pcpNumber" id=pcpNumber>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                 <td>结算单位</td>
                <td>
                   <select name="cuName" id=cuName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                
               
              
              <tr>
                <td>分部</td>
                <td>
                   <select name="pName" id=pName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                  
                  <td>工程部位/分项1</td>
                <td>
                   <select name="psName" id=psName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                 
                  <td>工作项目/分项2</td>
                <td>
                   <select name="jobName" id=jobName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
                 
                 
              
                
              </tr>
              
              <tr>
                  <td>分项3</td>
                <td>
                   <select name="fsName" id=fsName>
                      <option value="">----请选择------</option>
                     <c:forEach items="${rList}" var="r">
                        <option value="${r.roleId}">${r.roleName}</option>
                      </c:forEach>
                    </select>
                </td>
              <td>单价</td>
                <td class="td_detail">
                    <input type="text" id ='price' name='price'/></td>
              </tr>
              
              
                <tr>
                <td colspan="6" align="right">
                    <input type="button" id="button_submit" value="查询">
                    <input type="reset" value="重置"/>       
               </td>
                     
           </tr>
         </tbody>
       
</table>
</form>



<table id="customers" border="1" cellspacing="0">
		<tr>
			<th>工程名称</th>
			<th>分部</th>
			<th>工程部位/分项1</th>
			<th>工作项目/分项2</th>
			<th>分项3</th>
			<th>栋号</th>
			<th>单位</th>
			<th>结算单位</th>
			<th>单价</th>
		</tr>

	</table>
	
<div id="example"></div>

</body>
</html>
