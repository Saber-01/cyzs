<%@ page language="java" import="java.util.*,com.org.cygs.pojo.Lwgzzf" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listLWGZZF.jsp' starting page</title>
    <link href="<%=path%>/pages/css/base.css" rel="stylesheet">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
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
 	 String reportdate=(String)request.getAttribute("reportdate");
  	String bdate=(String)request.getAttribute("bdate");
  	String edate=(String)request.getAttribute("edate");
 	List<Lwgzzf> VOlist=(List<Lwgzzf>)request.getAttribute("lwgzzfList");
  	Lwgzzf sumVO=(Lwgzzf)request.getAttribute("lwgzzfSum");
  
 	 String year=(String)request.getAttribute("year");
  	String month=(String)request.getAttribute("month");
  	String dpname=(String)request.getAttribute("dpName");
  
   %>
  <body>
    <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;"><%=year%>年<%=month%>月重庆诚业建筑工程有限公司项目劳务工资支付情况明细表</font>
				</td>
			</tr>
		</tbody>
	</table>

		<input type="button" onclick="$('#lwgzzf').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton">


			<TABLE id="lwgzzf" class="table table-striped table-bordered table-condensed">
			<TR>
			<TD colspan="17" style="text-align:center">  
			重庆诚业建筑工程有限公司工资支付情况明细表（<%=year%>年<%=month%>月）<br>
			<%-- 所属部门：<%=dpname%> &nbsp;  --%>
			<div align=left>
			结算时段：<%=bdate %>至<%=edate %> &nbsp;&nbsp;
			报表日期：<%=reportdate%> &nbsp;&nbsp;
			报表编号： </div>
			</TD>
			</TR>
			<TR>
				<TD rowspan="2">
					序号
				</TD>
				<TD rowspan="2">
					工程名称
				</TD>
				<TD rowspan="2">
					结算单位
				</TD>
				<TD rowspan="2">
					上期累计结算金额（元）
				</TD>
				<TD rowspan="2">
					上期应付余额（元）
				</TD>
				<TD rowspan="2">
					本期结算金额（元）
				</TD>
				<TD rowspan="2">
					累计结算金额（元）
				</TD>
				<TD colspan="4">
					扣款
				</TD>
				<TD rowspan="2">
					本期应付余额（元）
				</TD>				
				<TD colspan="3">
					本次拟付
				</TD>
				<TD colspan="1">
					拟欠付金额
				</TD>
				<TD rowspan="2">
					备注
				</TD>
			</TR>
			<TR>
			<TD>
			罚款
			</TD>
			<TD>
			材料
			</TD>
			<TD>
			其他
			</TD>
			<TD>
			财务借支
			</TD>
			<TD>
			现金
			</TD>
			<TD>
			借支
			</TD>
			<TD>
			保证金
			</TD>
			<TD>
			（元）
			</TD>
			</TR>
			<% 
				if(VOlist==null||VOlist.size()==0)
				{
			%>
			<TR>
			<TD colspan="17"><font color="#ff0000"> 
			---没有相应的结果---</font> 
			</TD>
			</TR>
			<%
			}
			else
				{
					for(int i=0;i<VOlist.size();i++)
					{
						Lwgzzf lwVO = (Lwgzzf)VOlist.get(i);
			 %>
			<TR>
			<TD>
			<%=lwVO.getId()%>
			</TD>
			<TD>
			<%=lwVO.getPrName()%>
			</TD>
			<TD>
			<%=lwVO.getCuName()%>
			</TD>
			<TD>
			<%=lwVO.getSqjs().toString()%>
			</TD>
			<TD>
			</TD>
			<TD>
			<%=lwVO.getBqjs().toString()%>
			</TD>
			<TD>
			<%=lwVO.getLjjs().toString()%>
			</TD>
			<TD>
			<%=lwVO.getFk().toString()%>
			</TD>
			<TD>
			<%=lwVO.getCl().toString()%>
			</TD>
			<TD>
			<%=lwVO.getQt().toString()%>
			</TD>
			<TD>
			</TD>
			<TD>
			</TD>
			<TD>			
			</TD>
			<TD>
			</TD>
			<TD>
			</TD>
			<TD>
			</TD>
			<TD>
			</TD>
			</TR>
			 <%
				}
			 %>
			 <TR>
			 <TD>
			 </TD>	
			 <TD> 
			 合计
			 </TD>
			<TD></TD>
			<TD>
			<%=sumVO.getSqjs().toString()%>
			</TD>
			<TD></TD>
			<TD><%=sumVO.getBqjs().toString()%></TD>
			<TD>
			<%=sumVO.getLjjs().toString()%>
			</TD>
			 <TD>
			 <%=sumVO.getFk().toString()%>
			 </TD>
			 <TD>
			 <%=sumVO.getCl().toString()%>
			 </TD>
			 <TD>
			 <%=sumVO.getQt().toString()%>
			 </TD>
			 <TD></TD>
			 <TD></TD>
			 
			 <TD></TD>
			 <TD></TD>
			 <TD></TD>
			 <TD></TD>
			 <TD></TD>
			 </TR>
			  <%
				}
			 %>
			 <TR>
			 <TD colspan="17" style="text-align:left">
			 董事长：
			 &nbsp;  &nbsp;&nbsp;  &nbsp;  总经理：
			 &nbsp;  &nbsp;&nbsp;  &nbsp;  审计：
			 &nbsp;  &nbsp;&nbsp;  &nbsp;  劳务：
			 &nbsp;  &nbsp;&nbsp;  &nbsp; 财务：
			 &nbsp;  &nbsp;&nbsp;  &nbsp;  项目：
			 </TD>
			 </TR>
			</TABLE>
			
<br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>
  </body>
</html>
