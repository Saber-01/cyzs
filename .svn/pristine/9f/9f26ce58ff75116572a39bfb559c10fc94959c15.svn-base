<%@ page language="java" import="java.util.*,com.org.cygs.pojo.MissionContent" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listYGGZFFHZ.jsp' starting page</title>
    <link href="<%=path%>/pages/css/base.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />
	<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.base64.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
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
  <%
  	  	String year = (String)request.getAttribute("year");
 	 	String month = (String)request.getAttribute("month");
 	 	String prName = (String)request.getAttribute("prName");
 	 	String dpName = (String)request.getAttribute("dpName");
 	 	String bdate = (String)request.getAttribute("bdate");
 	 	String edate = (String)request.getAttribute("edate");
 	 	String reportDate = (String)request.getAttribute("reportDate");
 	 	List<MissionContent> mcvoList = (List<MissionContent>)request.getAttribute("mcList");
   %>
  <body>

<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
	<tbody>
		<tr>
			<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
			<font style="font-size:1.0em;"><%=year%>年<%=month%>月重庆诚业建筑工程有限公司员工工资发放汇总表</font>
			</td>
		</tr>
	</tbody>
</table>

    		<input type="button" onclick="$('#lwgzzf').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton">
  	<br>
  	<TABLE class="table table-striped table-bordered table-condensed" id="lwgzzf">
		<TR>
			<TD colspan="26">  
				重庆诚业建筑工程有限公司员工工资发放汇总表（<%=year%>年<%=month%>月）<br>
				<div style="text-align:left">
				工程名称：<%=prName %> &nbsp;&nbsp;
				<%-- 所属部门：<%=dpName%> &nbsp;&nbsp; --%>
				结算时段：<%=bdate %>至<%=edate %> &nbsp;&nbsp;
				报表日期：<%=reportDate%> &nbsp;&nbsp;
				报表编号： </div>
			</TD>
		</TR>
		<tr>
			<td rowspan = "2">
				序号				
			</td>
			<td rowspan = "2">
				姓名
			</td>
			<td rowspan = "2">
				工资金额
			</td>
			<td colspan = "4">
				其中
			</td>
			<td colspan = "8">
				扣各种保险金
			</td>
			<td rowspan = "2">
				计税总额
			</td>
			<td rowspan = "2">
				扣税
			</td>
			<td rowspan = "2">
				实发工资
			</td>
			<td rowspan = "2">
				领款人
			</td>
		</tr>
		<tr>
			<td>
				基本工资
			</td>
			<td>
				岗位工资
			</td>
			<td>
				加班工资
			</td>
			<td>
				其他
			</td>
			<td>
				养老保险
			</td>
			<td>
				失业保险
			</td>
			<td>
				基本医保
			</td>
			<td>
				大病保险
			</td>
			<td>
				公积金
			</td>
			<td>
				会费
			</td>
			<td>
				其他
			</td>
			<td>
				扣款合计
			</td>						             						
		</tr>
		<%
			if(mcvoList != null && mcvoList.size() > 0)
			{
				for(int i = 0; i < mcvoList.size(); i++)
				{
					MissionContent mcvo = mcvoList.get(i);
		%>
					<tr>
						<td>
							<%=(i+1)%>
						</td>
						<td>
							<%=mcvo.getFloor() %>
						</td>
						<td>
							<%=mcvo.getWageSum() %>
						</td>
						<td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td>							
					</tr>
		<%
				}
		%>
				<tr>
					<td>
						小计
					</td>
					<td></td><td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td></td><td></td>
					<td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
		<%		
			}
		%>
		<tr>
			<td colspan="26" style="text-align:left">
				单位（盖章）： &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				负责人：（签字） &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				企审部（审核）： &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				制表人： &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
			</td>							
		</tr>
	</TABLE>
	
	<br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>
						
  </body>
</html>
