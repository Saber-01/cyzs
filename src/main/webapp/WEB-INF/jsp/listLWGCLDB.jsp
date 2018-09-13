<%@ page language="java" import="java.util.*,com.org.cygs.pojo.Lwgcldb" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listLWGCLDB.jsp' starting page</title>
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
 		 String pjname=(String)request.getAttribute("prName");
 		 String managerName=(String)request.getAttribute("prManager");
 		 String reportdate=(String)request.getAttribute("reportdate");
  %>
  <body>

<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
	<tbody>
		<tr>
			<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
			<font style="font-size:1.0em;">重庆诚业建筑工程有限公司项目劳务分包工程量对比表</font>
			</td>
		</tr>
	</tbody>
</table>

		<input type="button" onclick="$('#lwgcldb').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton">
<br>
			<TABLE id="lwgcldb" class="table table-striped table-bordered table-condensed">
			<TR>
			<TD colspan="14"> 
			重庆诚业建筑工程有限公司项目劳务分包工程量对比表<br>
			<div align=left>工程名称：<%=pjname %>&nbsp;&nbsp;
			项目经理：<%=managerName %>  &nbsp;&nbsp;
			报表日期：<%=reportdate %> &nbsp;&nbsp;
			报告编号：</div>
			</TD>
			</TR>
			<TR>
				<TD rowspan="2">
					序号
				</TD>
				<TD rowspan="2">
					栋号
				</TD>
				<TD rowspan="2">
					分部
				</TD>
				<TD rowspan="2">
					工程部位
				</TD>
				<TD rowspan="2">
					工作项目
				</TD>
				<TD rowspan="2">
					单位
				</TD>
				<TD rowspan="2">
					单价
				</TD>
				<TD colspan="2">
					累计数量
				</TD>
				<TD colspan="2">
					累计金额
				</TD>
				<TD colspan="2">
					对比
				</TD>
				<TD rowspan="2">
					备注
				</TD>
			</TR>
			<TR>
			<TD>
			预算量
			</TD>
			<TD>
			已开量
			</TD>
			<TD>
			预算量
			</TD>
			<TD>
			已开量
			</TD>
			<TD>
			差量
			</TD>
			<TD>
			金额差
			</TD>
			</TR>
			<%
				List<Lwgcldb> VOlist=(List<Lwgcldb>)request.getAttribute("lwgcldbList"); 
				if(VOlist!=null&&VOlist.size()>0)
				{
					for(int i=0;i<VOlist.size();i++)
					{
						Lwgcldb gclVO=VOlist.get(i);
			 %>
			<TR>
			<TD> 
			<%=gclVO.getId() %>
			</TD>
			<TD>
			<%=gclVO.getBuildNO() %>
			</TD>
			<TD>
			<%=gclVO.getPart() %>
			</TD>
			<TD>
			<%=gclVO.getPartposition() %>
			</TD>
			<TD>
			<%=gclVO.getOperation() %>
			</TD>
			<TD>
			<%=gclVO.getUnit() %>
			</TD>
			<TD>
			<%=gclVO.getPrice() %>
			</TD>
			<TD>
			<%=gclVO.getBudgetamount() %>
			</TD>
			<TD>
			<%=gclVO.getYikaiamount() %>
			</TD>
			<TD>
			<%=gclVO.getBudgetmoney() %>
			</TD>
			<TD>
			<%=gclVO.getYikaimoney() %>
			</TD>
			<TD>
			<%=gclVO.getAmountgap() %>
			</TD>
			<TD>
			<%=gclVO.getMoneygap() %>
			</TD>
			<TD></TD>
			</TR>
			<%	}
			}
			 %>
			<% 
				Lwgcldb sumgclVO=(Lwgcldb)request.getAttribute("lwgcldbSum");
			%> 
			<TR>
			<TD> 
			 合计
			</TD>
			<TD></TD>
			<TD></TD>
			<TD></TD>
			<TD></TD>
			<TD></TD>
			<TD></TD>
			<TD>
			<%=sumgclVO.getBudgetamount() %>
			</TD>
			<TD>
			<%=sumgclVO.getYikaiamount() %>
			</TD>
			<TD>
			<%=sumgclVO.getBudgetmoney() %>
			</TD>
			<TD>
			<%=sumgclVO.getYikaimoney() %>
			</TD>
			<TD>
			<%=sumgclVO.getAmountgap() %>
			</TD>
			<TD>
			<%=sumgclVO.getMoneygap() %>
			</TD>
			<TD>
			</TD>
			</TR>
		</TABLE>
		
		<br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>
  </body>
</html>
