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
    
    <title>查看计件任务书统计表</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
    
    <script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="<%=path%>/js/excelExport.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
<script type="text/javascript">
  $(document).ready(function(){

  $("#search").click(function(){
   		getJJMissionList();
  });
  
/*   $("#exportToExcel").click(function(){
  		$("#jj_mission").excelExport({type:'excel',escape:'false'});
  });*/
}); 

function getJJMissionList()
{

   var json = {};
   json['prId'] =$("#prId").val();
   json['status'] = $("input[type='radio']:checked").val();
   var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"getJJMissionList", 
                dataType:"json", 
                data:postdata,
                success:function(data){
 
               		 $("#jj_mission tr").remove();
              	 	 var unitNumbers = data.pipList;
              		var mission1 = data.mission1;
              		var mission2 = data.mission2;
              		if(unitNumbers != null){
              			$("#jj_mission").append("<tr><td colspan='"+(unitNumbers.length+8)+"'>计件任务书统计表</td></tr>");
              			$("#jj_mission").append("<tr><td rowspan='2'>项目名称</td><td rowspan='2'>分部</td><td rowspan='2'>分项一</td><td rowspan='2'>分项二</td><td rowspan='2'>单价</td><td rowspan='2'>单位</td><td rowspan='2'>工程量</td><td rowspan='2'>总金额</td><td colspan='"+unitNumbers.length+"'>楼栋号</td></tr>");  
              	  		var i = 0;
              	  		var j = 0;
              	  		var k = 0;
              	  		var flag = 0;
              	  		var unitnumbers = "<tr>";
              	  		for(i=0;i<unitNumbers.length;i++){
              	  			unitnumbers =  unitnumbers + "<td>" + unitNumbers[i].unitNumber+"</td>";
              	  		}
              	  		unitnumbers = unitnumbers + "</tr>";
              	  		$("#jj_mission").append(unitnumbers);
              	  		var missionOne = "<tr>";
              	  		for(i=0;i<mission1.length;i++){
              	  			missionOne = missionOne + "<td>" + mission1[i].prName + "</td><td>" + mission1[i].pName+"</td><td>" + mission1[i].psName + "</td><td>" + 
              	  				mission1[i].oName + "</td><td>" + mission1[i].price + "</td><td>" + mission1[i].unName + "</td><td>" + mission1[i].gcl + "</td><td>" +
              	  				mission1[i].accountSum + "</td>";
              	  				for(k = 0;k<unitNumbers.length;k++){
              	  					for(j=0;j<mission2.length;j++){
              	  						if(mission1[i].prName == mission2[j].prName && 
              	  							mission1[i].pName == mission2[j].pName && 
              	  							mission1[i].psName == mission2[j].psName &&
              	  							mission1[i].oName == mission2[j].oName && 
              	  							mission1[i].price == mission2[j].price &&
              	  							mission1[i].unName == mission2[j].unName &&
              	  							mission2[j].unitNumber == unitNumbers[k].unitNumber)
              	  						{
              	  									missionOne =  missionOne + "<td>" + mission2[j].gcl +"</td>";
              	  									flag = 1;//若某些分项二只对应于某个楼栋号，并非所有楼栋号均有某个分项二
              	  									break;
              	  						}
              	  					}			              	  				
              	  					if(flag == 0){
              	  						missionOne = missionOne + "<td>0.0</td>";
              	  					}else{
              	  						flag = 0;
              	  					}
              	  				}
              	  			missionOne = missionOne + "</tr>";
              	  			$("#jj_mission").append(missionOne);
              	  			missionOne = "<tr>";
              	  		}      	  		
                	}
                	
                },  
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
     });

}

</script>
<body>
  <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">查看计件任务书统计表</font>
				</td>
			</tr>
		</tbody>
	</table>
  	<form>
  		<table id="addmission" class="table table-striped table-bordered table-condensed">
  		<tr>
  		<td width="30%">工程名称: 
  			<select id="prId" name="prId" style="height:30px;line-height:30px;">
  				<option value="">---请选择---</option>
  				<c:forEach items="${prList}" var="pr">
  					<option value="${pr.prId}">${pr.prName}</option>
  				</c:forEach>
  			</select>
  		</td>
  		<td width="20%">
  			是否审核通过: 
  			<input type="radio" value="1" checked="checked" name="status"/>是
  			<input type="radio" value="0" name="status"/>否
  		</td>
  		<td width="50%">
  			<input type="button"  id="search" value="查询" class="easyui-linkbutton" style="height:30px;line-height:30px;">
  			<input type="button" id="exportToExcel" value="导出到Excel" onclick="$('#jj_mission').tableExport({type:'excel',escape:'false'});" class="easyui-linkbutton" style="height:30px;line-height:30px;">
  		</td>
  		</tr>
  		</table>
  	</form>
  	<table id="jj_mission" class="table table-striped table-condensed"></table>
</body>
</html>
