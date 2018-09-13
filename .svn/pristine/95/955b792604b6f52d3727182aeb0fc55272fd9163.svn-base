<%@ page language="java" import="java.util.*,java.math.BigDecimal,com.org.cygs.pojo.MissionVo,com.org.cygs.pojo.Lwfbjs" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listLWFBJS.jsp' starting page</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
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
  String pjmanager=(String)request.getAttribute("prManager");
  String reportdate=(String)request.getAttribute("reportdate");
  String bdate=(String)request.getAttribute("bdate");
  String edate=(String)request.getAttribute("edate");
  List<Lwfbjs> VOlist=(List<Lwfbjs>)request.getAttribute("lwfbjsList");
  List<MissionVo> VOlist1=(List<MissionVo>)request.getAttribute("missionVoList");
  Lwfbjs sumVO=(Lwfbjs)request.getAttribute("lwfbjsSum");
  
  //ls
  String year=(String)request.getAttribute("year");
  String month=(String)request.getAttribute("month");
  //String dpname=(String)request.getAttribute("dpName");
  
   %>
<body>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
	<tbody>
		<tr>
			<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
			<font style="font-size:1.0em;"><%=year%>年<%=month%>月重庆诚业建筑工程有限公司项目劳务分包结算汇总表</font>
			</td>
		</tr>
	</tbody>
</table>
		<input type="button" onclick="$('#lwfbjs').tableExport({type:'excel',escape:'false'});" value="导出到Execl..." class="easyui-linkbutton">
<br>
			<TABLE id="lwfbjs" class="table table-striped table-bordered table-condensed">
			<TR>
			<TD colspan="14"> 
			重庆诚业建筑工程有限公司劳务分包结算汇总表（<%=year%>年<%=month%>月）
			<br>
			<%-- 所属部门：<%=dpname%> &nbsp;  --%>
			<div align=left>
			工程名称：<%=pjname %> &nbsp;&nbsp;
			项目经理：<%=pjmanager %> &nbsp;&nbsp;
			结算时段：<%=bdate %>至<%=edate %> &nbsp;&nbsp;
			报表日期：<%=reportdate %> &nbsp;&nbsp;
			报表编号：</div>
			</TD>
			</TR>
			<TR>
				<TD rowspan="2">
					序号
				</TD>
				<TD rowspan="2">
					结算单位
				</TD>
				<TD colspan="6">
					结算金额（元）
				</TD>
				<TD colspan="3">
					扣款金额（元）
				</TD>
				<TD rowspan="2">
					本期应付金额
				</TD>
				<TD rowspan="2">
					任务单数量（张）
				</TD>
				<TD rowspan="2">
					签字
				</TD>
			</TR>
			<TR>
			<TD>
			计件工资
			</TD>
			<TD>
			计时工资
			</TD>
			<TD>
			变更签证工资
			</TD>
			<TD>
			合同外补差价工资
			</TD>
			<TD>
			安全文明措施费
			</TD>
			<TD>
			本期结算金额
			</TD>
			<TD>
			罚款
			</TD>
			<TD>
			材料
			</TD>
			<TD>
			其他
			</TD>
			</TR>
			<% 
				if(VOlist==null||VOlist.size()==0)
				{
			%>
			<TR>
			<TD colspan="14"><font color="#ff0000"> 
			---没有相应的结果---</font> 
			</TD>
			</TR>
			<%
			}
			else
				{
					for(int i=0;i<VOlist.size();i++)
					{
						Lwfbjs lwVO = VOlist.get(i);
			 %>
			<TR>
			<TD>
			<%=lwVO.getId()%>
			</TD>
			<TD>
			<%=lwVO.getCuName()%>
			</TD>
			<TD>
			<%=lwVO.getJjgz().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getJsgz().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getBgqzgz().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getHtwbgz().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getHtcl().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getBqjs().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getFk().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getCl().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getQt().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getBqyf().toString()
			%>
			</TD>
			<TD>
			<%=lwVO.getMissionAmount()%>
			</TD>
			<TD>
			
			</TD>
			</TR>
			 <%
				}
			 %>
			 <TR>
			 <TD colspan="2"> 
			 合计</TD>
			 <TD>
			 <%
			 	//DecimalFormat decimalformat = new DecimalFormat("0.00");
			 %>
			 <%=//decimalformat.format(sumVO.getJjgz())
			 sumVO.getJjgz().toString()
			 %>			 
			 </TD>
			 <TD>
			 <%=sumVO.getJsgz().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getBgqzgz().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getHtwbgz().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getHtcl().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getBqjs().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getFk().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getCl().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getQt().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getBqyf().toString()
			 %>
			 </TD>
			 <TD>
			 <%=sumVO.getMissionAmount()%>
			 </TD>
			 <TD>
	
			 </TD>
			 </TR>
			  <%
				}
			 %>
			 <TR style="hight:100px;">
			 <TD colspan="14" style="text-align:left">
			 董事长：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总经理：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审计：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;劳务：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;财务：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目：
			 </TD>
			 </TR>
			</TABLE>
			<br/>
			<div style="width:1xp;hight:1px;">
			
		<TABLE id="plMission" class="table table-striped table-bordered table-condensed">
			<tr>
			<TD>
				任务书编号
			 </TD>
			<TD>
				任务书类型
			 </TD>
			 <TD>
				工程名称
			 </TD>
			 
			 <TD>
				栋号
			 </TD>
			 <TD>
				开始时间
			 </TD>
			 <TD>
				结束时间
			 </TD>
			 <TD>
				结算单位
			 </TD>
			
			 <TD>
				分部
			 </TD>
			 <TD>
				工程部位
			 </TD>
			 <TD>
				工作项目
			 </TD>
			 <TD>
				具体部位
			 </TD>
			 
			 <TD>
				提交时间
			 </TD>
			 <TD>
				单位
			 </TD>
			 <TD>
				单价
			 </TD>
			  <TD>
				工程量
			 </TD>
			  <TD>
				工资金额
			 </TD>
			
			  <TD>
				合同材料
			 </TD>
			  <TD>
				罚款
			 </TD>
			  <TD>
				扣款材料
			 </TD>
			  <TD>
				其他
			 </TD>
			  <TD>
				最后审核时间
			 </TD>
			  <TD>
				状态
			 </TD>
			  <TD>
				备注
			 </TD>
			  <TD>
				项目经理意见
			 </TD>
			  <TD>
				预算意见
			 </TD>
			 <TD>
				成控部经理意见
			 </TD>
			  <TD>
				审计意见
			 </TD>
			  <TD>
				分管领导意见
			 </TD>			
			 </tr>
			 <%
			 	
				 for(int i=0;i<VOlist1.size()-1;i++){
						MissionVo mVO = VOlist1.get(i);
			 %>
			 <tr>
			<TD>
				<%=mVO.getMCode() %>
			 </TD>
			 <TD>
				<%=mVO.getMissionTypeName() %>
			 </TD>
			 <TD>
				<%=mVO.getProjectName() %>
			 </TD>
			
			 <TD>
				<%=mVO.getDongHao() %>
			 </TD>
			 <TD>
				<%=mVO.getBeginDate() %>
			 </TD>
			 <TD>
				<%=mVO.getEndDate() %>
			 </TD>
			 <TD>
				<%=mVO.getCuName() %>
			 </TD>
			 
			 <TD>
				<%=mVO.getFenBu() %>
			 </TD>
			 <TD>
				<%=mVO.getGongChengBuWei() %>
			 </TD>
			 <TD>
				<%=mVO.getGongZuoXiangMu() %>
			 </TD>
			 <TD>
			 <% if(mVO.getJuTiBuWei() != null) { %>
				<%=mVO.getJuTiBuWei() %>
			 <% } %>
			 </TD>
			
			 <TD>
				<%=mVO.getTiJiaoShiJian() %>
			 </TD>
			 <TD>
				<%=mVO.getDanWei() %>
			 </TD>
			 <TD>
				<%=mVO.getDanJia() %>
			 </TD>
			 <TD>
				<%=mVO.getGongChengLiang() %>
			 </TD>
			 <TD>
				<%=mVO.getGongZiJinE() %>
			 </TD>
			
			 <TD>
				<%=mVO.getHeTongCaiLiao() %>
			 </TD>
			 <TD>
				<%=mVO.getFaKuan() %>
			 </TD>
			 <TD>
				<%=mVO.getKouKuanCaiLiao() %>
			 </TD>
			 <TD>
				<%=mVO.getQiTa() %>
			 </TD>
			 <TD>
				<%=mVO.getZuiHouShenHe() %>
			 </TD>
			 <TD>
				<%=mVO.getZhuangTai() %>
			 </TD>
			 <TD>
				<%=mVO.getRemark() %>
			 </TD>
			 <TD>
				<%=mVO.getXiangMuJingLi() %>
			 </TD>
			 <TD>
				<%=mVO.getYuSuan() %>
			 </TD>
			 <TD>
				<%=mVO.getChengkong() %>
			 </TD>
			 <TD>
				<%=mVO.getShenJi() %>
			 </TD>
			 <TD>
				<%=mVO.getFenGuanLingDao() %>
			 </TD>
			 
			 </tr>
			 <%} 
			 	MissionVo mVOl = VOlist1.get(VOlist1.size()-1);
			 %>
			 <tr>
			 	<TD>
				<%=mVOl.getMCode() %>
				 </TD>
			 	<TD>
				<%=mVOl.getMissionTypeName() %>
				 </TD>
				 <td colspan="26">
				 </td>
			 </tr>
		</TABLE>
				</div>
		<br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>
  </body>
</html>
