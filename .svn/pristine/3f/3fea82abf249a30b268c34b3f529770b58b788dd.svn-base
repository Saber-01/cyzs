<%@ page language="java" import="java.util.*,java.lang.Integer,com.org.cygs.pojo.Lwfb,java.math.BigDecimal" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listLwfb.jsp' starting page</title>
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
    <script type="text/javascript" src="<%=path%>/js/excelExport.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	//指定页面区域内容导入Excel,这种方法需要修改浏览器设置
	
</script>
  </head>
    <%
  String reportdate=(String)request.getAttribute("reportdate");
  String bdate=(String)request.getAttribute("bdate");
  String edate=(String)request.getAttribute("edate");
  List<Lwfb> VOlist=(List<Lwfb>)request.getAttribute("lwfbList");
  String proNumStr=(String)request.getAttribute("prNum");
  int proNum=Integer.parseInt(proNumStr); 
  
  //ls
  String year=(String)request.getAttribute("year");
  String month=(String)request.getAttribute("month"); 
  String dpname=(String)request.getAttribute("dpName");
   %>
<body>

<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">${year}年${month}月重庆诚业建筑工程有限公司项目劳务分包结算汇总表</font>
				</td>
			</tr>
		</tbody>
</table>
  	<%-- <p align="center">${year}年${month}月重庆诚业建筑工程有限公司项目劳务分包结算汇总表</p> --%>
   <input type="button" onclick="$('#lwfbhz').tableExport({type:'excel',escape:'false'});" value="导出到Excel" class="easyui-linkbutton">
   <br>
    <table class="table table-striped table-bordered table-condensed" id="lwfbhz">
    		<TR>			
			<% 
			if( proNum==0 )
			{
			%>
			<TD colspan="9" > 
			重庆诚业建筑工程有限公司劳务分包结算汇总表（<%=year%>年<%=month%>月）<br>		
			<%-- 所属部门：<%=dpname%> &nbsp;  --%>
			<div align=left>
			结算时段：<%=bdate %>至<%=edate %> &nbsp;&nbsp;
			报表日期：<%=reportdate %> &nbsp;&nbsp;
			报表编号：</div>
			</TD>
			<%
			}
			else
			{
			 %>
			<TD colspan=<%=proNum+8%> >	
			重庆诚业建筑工程有限公司劳务分包结算汇总表（<%=year%>年<%=month%>月）<br>		
			<%-- 所属部门：<%=dpname%> &nbsp;  --%>
			<div align=left>
			结算时段：<%=bdate %>至<%=edate %> &nbsp;&nbsp;
			报表日期：<%=reportdate %> &nbsp;&nbsp;
			报表编号：</div>
			</TD>
			<%
			} 
			%>
			</TR>
			<TR>
				<TD rowspan="2" colspan="1">
					序号
				</TD>
				<TD rowspan="2" colspan="1">
					结算单位
				</TD>
				<%
				  if( proNum==0 )				     
				   {
				 %>
				 <TD colspan="1" >
				工程名称
				</TD>
				<%
					}
				  else
				  {
				 %>
				<TD colspan=<%=proNum %> >
				工程名称
				</TD>
				 <%
				   } 
				 %>
				<TD rowspan="2" colspan="1">
					本期结算金额（元）
				</TD>
				<TD rowspan="2" colspan="1">
					累积结算金额（元）
				</TD>
				<TD colspan="3">
					扣款
				</TD>
				<TD rowspan="2" colspan="1">
					本期应付金额
				</TD>
			</TR>									
			<% 
				if(VOlist==null||VOlist.size()==0)
				{
			%>
			<TR>
				<TD colspan="1" >
				<font color="#ff0000">没有相关工程</font> 
				</TD>
			
			<% 
				}
			else
				{							
					for(int i=0;i<proNum;i++)//取工程名称
					{
						Lwfb lwVO = (Lwfb)VOlist.get(i);
						String proName=lwVO.getPrName();												
			 %>
			 		<TD>
			 		<%=proName%>
			 		</TD>
			 <%
			 		}
			 	}
			 %>			
					
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
			<TD colspan="9" >
			<font color="#ff0000"> 
			---没有相应的结果---</font> 
			</TD>
			</TR>
			<%
				}
			else
				{
				    int id=0;
				    int mod=proNum;
				    BigDecimal bqjs=new BigDecimal("0.00"); //本期结算金额
				    BigDecimal ljjs=new BigDecimal("0.00");
					BigDecimal fk=new BigDecimal("0.00"); //罚款
					BigDecimal cl=new BigDecimal("0.00"); //材料
					BigDecimal qt=new BigDecimal("0.00"); //其他
					BigDecimal bqyf=new BigDecimal("0.00"); //本期应付金额
					BigDecimal bqjs_sum=new BigDecimal("0.00");
					BigDecimal ljjs_sum=new BigDecimal("0.00");
					BigDecimal fk_sum=new BigDecimal("0.00");
					BigDecimal cl_sum=new BigDecimal("0.00");
					BigDecimal qt_sum=new BigDecimal("0.00");
					BigDecimal bqyf_sum=new BigDecimal("0.00");
	            //    float []pr_sum=new float[proNum];
	            	BigDecimal []pr_sum=new BigDecimal[proNum]; 
	            	for(int cnt = 0;cnt<proNum;cnt++)
	            	{
	            		pr_sum[cnt]=new BigDecimal("0.00");
	            	}
	            	
					for(int i=0;i<VOlist.size();)
					{					   
					          id++;					       
		     %>					         
			<TR>
			                  <TD>
			                   <%=id%>
			                  </TD> 		              		
			         <%			           
			             Lwfb lwVO1 = (Lwfb)VOlist.get(i);
			         %>
			             <TD>
			              		<%=lwVO1.getCuName()%>
			             </TD>
			         <% 
			             bqjs=lwVO1.getBqjs();
			             ljjs=lwVO1.getLjjsje();
					 	 fk=lwVO1.getFk(); 
					 	 cl=lwVO1.getCl(); 
					 	 qt=lwVO1.getQt();
					 	 bqyf=lwVO1.getBqyf();    
			             for(int pro_num=0;pro_num<proNum;pro_num++)
			              {
			              	Lwfb lwVO = (Lwfb)VOlist.get(i);
			                //pr_sum[pro_num]+=lwVO.getSum_wage();
			                pr_sum[pro_num]= pr_sum[pro_num].add(lwVO.getSumWage());
			          %>
			             <TD>
			               		<%=lwVO.getSumWage().toString() %>
			             </TD>
			         <% 
			              i++;
			              }			              
			         %>			              		
			         <%			         		
					 	  bqjs_sum=bqjs_sum.add(bqjs);
					 	  ljjs_sum=ljjs_sum.add(ljjs);
						  fk_sum=fk_sum.add(fk);
						  cl_sum=cl_sum.add(cl);
						  qt_sum=qt_sum.add(qt);
						  bqyf_sum=bqyf_sum.add(bqyf);			         					              				              	
			         %>	            
			              
			          <%  			          	  	
			          	 
			          	 
			          %>
			               <TD>
			               <%=bqjs.toString()%>
			               </TD>
			         	   <TD>
			               <%=ljjs.toString()%>
			               </TD>
			               <TD>
			               <%=fk.toString()%>
			               </TD>
			               <TD>
			               <%=cl.toString()%>
			               </TD>
			               <TD>
			               <%=qt.toString()%>
			               </TD>
			               <TD>
			               <%=bqyf.toString()%>
			               </TD>		              
			</TR>
					 <%
						}
			 		 %>
			 
			 <TR>
			 <TD colspan="2"> 
			 合计
			 </TD>
			 <% 
			 for(int i=0;i<proNum;i++)
			  {
			  %>			  
			   <TD>
			   <%=pr_sum[i].toString()%>
			   </TD>
			 <%
			  } 
			 %>			
			 <td>
			 <%=bqjs_sum.toString() %>
			 </td>
			 <td>
			 <%=ljjs_sum.toString() %>
			 </td>
			 <td>
			 <%=fk_sum.toString() %>
			 </td>
			 <td>
			 <%=cl_sum.toString() %>
			 </td>
			 <td>
			 <%=qt_sum.toString() %>
			 </td>
			 <td>
			 <%=bqyf_sum.toString() %>
			 </td>			 
			 </TR>
			  <%
				}
			 %>
			 <TR>
			 <% 
			 if( proNum==0 )
			 {
			 %>
			 <TD colspan="9" style="text-align:left">
			 董事长：&nbsp;  &nbsp;&nbsp;  &nbsp;
			 总经理：&nbsp;  &nbsp;&nbsp;  &nbsp;
			 审计：&nbsp;  &nbsp;&nbsp;  &nbsp;
			 劳务：&nbsp;  &nbsp;&nbsp;  &nbsp;
			 财务：&nbsp;  &nbsp;&nbsp;  &nbsp;
			 </TD>
			 <%
			 }
			 else
			 { 
			 %>
			 <TD colspan=<%=proNum+8 %> style="text-align:left">
			 董事长：
			&nbsp;  &nbsp;&nbsp;  &nbsp;   总经理：
			&nbsp;  &nbsp;&nbsp;  &nbsp;   审计：
			&nbsp;  &nbsp;&nbsp;  &nbsp;   劳务：
			&nbsp;  &nbsp;&nbsp;  &nbsp;   财务：		
			 </TD>
			 <%
			 } 
			 %>			
			 </TR>
    </table>
    
    <br><br>
	<div style="text-align:center">
		<input type="button" onclick="window.history.back();" value="返回上一页" class="easyui-linkbutton">
	</div>
  </body>
</html>
