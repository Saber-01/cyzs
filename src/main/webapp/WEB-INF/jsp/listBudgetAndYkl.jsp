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
    
    <title>My JSP 'listBudgetAndYkl.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />

	<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="<%=path%>/js/excelExport.js"></script>
	<script type="text/javascript" src="<%=path%>/js/index.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
$(document).ready(function(){

  $("#button_search").click(function(){
  		var prId = $("#prId").val();
  		if(prId != null && prId != "" ){
   			getBudgetAndYkl();
   		}else{
   			alert("请选择工程名称！");
   		}
  });
  
  
});
function selectChange(){
	var value = $("#prId").val();
	if(value != "" && value != null){
		getUnitNumberList();
	}else{
		$("#pcPId").empty();
		$("#pcPId").append("<option value=''>---请选择栋号---</option>");
	}
}

function getUnitNumberList(){
	var json = {};
	json['prId'] = $("#prId").val();
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"ykl/getUnitNumberList",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#pcPId").empty();
			$("#pcPId").append("<option value=''>---请选择栋号---</option>");
			$.each(data,function(index,item){
				
				$("#pcPId").append("<option value='"+item.pcPId+"'>"+item.unitNumber+"</option>");
		
			})
		},
		error:function(e){
			alert("出错了："+e);
		}
	});
}

function getBudgetAndYkl(){
	var json = {};
	json['prId'] = $("#prId").val();
	json['pcPId'] = $("#pcPId").val();
	json['pId'] = $("#pId").val();
	var postdata = JSON.stringify(json);
	$.ajax({
		contentType:'application/json',
		type:"POST",
		url:"ykl/getBudgetAndYkl",
		dataType:"json",
		data:postdata,
		success:function(data){
			$("#budget_ykl tr:gt(0)").remove();
			
			$.each(data,function(index,item){
				
					$("#budget_ykl").append("<tr><td>"+index+"</td><td>"+item.prName+"</td><td>"+item.unitNumber+"</td><td>"+
						item.pName+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td>"+item.unName+"</td><td>"+
						item.remark+"</td><td>"+item.budgetAccount+"</td><td>"+item.jjykAmount+"</td><td>"+item.gclcAmount+"</td><td>"+
						item.jjysAmount+"</td></tr>");
		
			});
		
		},
		error:function(e){
			alert(e);
		}
	});
}
//导出Excel
var idTmr;  
        function  getExplorer() {  
            var explorer = window.navigator.userAgent ;  
            //ie  
            if (explorer.indexOf("MSIE") >= 0 || (explorer.indexOf("Windows NT 6.1;") >= 0 && explorer.indexOf("Trident/7.0;") >= 0)) {  
             
                return 'ie';  
            }  
            //firefox  
            else if (explorer.indexOf("Firefox") >= 0) {  
                return 'Firefox';  
            }  
            //Chrome  
            else if(explorer.indexOf("Chrome") >= 0){  
                return 'Chrome';  
            }  
            //Opera  
            else if(explorer.indexOf("Opera") >= 0){  
                return 'Opera';  
            }  
            //Safari  
            else if(explorer.indexOf("Safari") >= 0){  
                return 'Safari';  
            }  
        }  
        //另一种表格导出方法
        function excelExport(tableid) {  
            if(getExplorer()=='ie')  
            {  
            	 var curTbl = document.getElementById(tableid);
				 var oXL = new ActiveXObject("Excel.Application");
				 var oWB = oXL.Workbooks.Add();
				var xlsheet = oWB.Worksheets(1);
				var sel = document.body.createTextRange();
				sel.moveToElementText(curTbl);
				sel.execCommand("Copy");
				xlsheet.Paste();
				var fname = oXL.Application.GetSaveAsFilename(name+".xls", "Excel Spreadsheets (*.xls), *.xls");
				oWB.SaveAs(fname);
				oWB.Close(savechanges = false);
				oXL.Quit();
				oXL = null;
            } else {  
                tableToExcel(tableid)  
            } 
    }  
        function Cleanup() {  
            window.clearInterval(idTmr);  
            CollectGarbage();  
        }  
        var tableToExcel = (function() {  
            var uri = 'data:application/vnd.ms-excel;base64,',  
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',  
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },  
                    format = function(s, c) {  
                        return s.replace(/{(\w+)}/g,  
                                function(m, p) { return c[p]; }) }  
            return function(table, name) {  
                if (!table.nodeType) table = document.getElementById(table)  
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}  
                window.location.href = uri + base64(format(template, ctx))  
            }  
        })()  
</script>
  </head>
  
  <body>
         <p>查询条件</p>
    <form>
    <table>
    	<tr>
    		<td>工程名称：
    			<select id="prId" onchange="selectChange()">
    				<option value="">---请选择---</option>
    				<c:forEach items="${prList}" var="pr">
    					<option value="${pr.prId}">${pr.prName}</option>
    				</c:forEach>
    			</select>
    			(必选)
    		</td>
    		<td>栋号：
    			<select id="pcPId" name="pcPId">
    				<option value="">---请选择---</option>
    			</select>
    		</td>
    		<td>分部：
    			<select id="pId" name="pId">
    				<option value="">--请选择---</option>
    				<c:forEach items="${pList}" var="p">
    					<option value="${p.pId}">${p.pName}</option>
    				</c:forEach>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td><input type="button" id="button_search" value="查询"/>
    		<input type="reset" value="重置"/>
    		<input type="button" id="export_excel" onclick="excelExport('budget_ykl')" value="导出到Excel"/></td>
    	</tr>
    </table>
  </form>
  <p>预算与已开量对比表</p>
  <table id="budget_ykl" border="1">
  		<tr>
  			<th>序号</th>
  			<th>工程名称</th>
  			<th>楼栋号</th>
  			<th>分部</th>
  			<th>预算工程部位/分项1</th>
  			<th>预算自定义名称/分项3</th>
  			<th>单位</th>
  			<th>备注</th>
  			<th>预算工程量</th>
  			<th>计件已开工程量</th>
  			<th>工程量差</th>
  			<th>计件已审工程量</th>
  		</tr>
  </table>
  </body>
</html>
