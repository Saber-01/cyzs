<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>工程信息锁定</title> 	

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">

</head> 
<body onload="showLock()">
    <div class="container">
       <table id="dg" style="width:100%;height:601px" title="工程信息锁定" data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                collapsible:true,
                toolbar:'#tb',
                loadMsg:0">
        </table>
       <div id="tb" style="padding:0 30px;">
       <form>
			工程名称:<select name="prId" id="prId" style="width:170px;height:35px;line-height:35px;">
						<c:forEach items="${prList}" var="p">
							<option value="${p.prId}">${p.prName}</option>
						</c:forEach>
				</select>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="toLockProject()">添加</a>
				<input type="hidden" name="loginname" id="loginname" value="${user.userLoginName}">
		</form>
</div>


</div>
<script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
  	function showLock() {
		var xmlDoc = null;
		var xmlhttp = null;
		var Allnode = null;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		}
		else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var loginname = document.getElementById("loginname").value;
		var url = "/cyjz/cyjz_file/xmle/" + loginname + ".xml" + "?time=" + new Date().getTime();
		
		xmlhttp.open("GET", url, false);
		xmlhttp.setRequestHeader("If-Modified-Since", "0");
		xmlhttp.send();
		xmlDoc = xmlhttp.responseXML;
		xmlDoc.async = false;
		
		Allnode = xmlDoc.getElementsByTagName("project");
		//alert(Allnode.length);
		for (var i = 0; i < Allnode.length; i++) {
			var FlowListTable = document.getElementById("dg");
			//alert(FlowListTable);
			var row = FlowListTable.insertRow();
			var m = FlowListTable.rows[0].cells.length;
			var prnamenode = Allnode[i].getElementsByTagName("prname");
			var unitnode = Allnode[i].getElementsByTagName("unit");
			// var departmentnode = Allnode[i].getElementsByTagName("department");
			var checkunitnode = Allnode[i].getElementsByTagName("checkunit");
			var currentState = null;
			currentState = prnamenode[0];

			var col = row.insertCell();
			col.innerHTML = "<td><div align='center'>" + currentState.childNodes[0].nodeValue + "</div></td>";

			var units = "<td><div align='center'>";
			for (var j = 0; j < unitnode.length; j++) {
				currentState = unitnode[j];
				var unit = currentState.childNodes[0].nodeValue + "<br>";
				units += unit;
			}
			units += "</div></td>";
			var col = row.insertCell();
			col.innerHTML = units;

			var checkunits = "<td><div align='center'>";
			for (var z = 0; z < checkunitnode.length; z++) {
				currentState = checkunitnode[z];
				var checkunit = currentState.childNodes[0].nodeValue + "<br>";
				checkunits += checkunit;
			}
			checkunits += "</div></td>";
			var col = row.insertCell();
			col.innerHTML = checkunits;

			var prid = Allnode[i].getAttribute("prid");
			var prname = prnamenode[0].childNodes[0].nodeValue;
			
			var operation = "<td><div align='center'>";
			operation += '<a id="editLabel" href=\'javascript:void(0)\' onclick=\'doReset(\"'
					+ prid + '\", \"' + prname + '\")\'>重设</a>&nbsp;&nbsp;&nbsp;<a id=\"delLabel\" href=\'javascript:void(0)\' onclick=\'doDelete(\"'
					+ prid + '\")\'>删除</a>';
			operation += "</div></td>";
			var col = row.insertCell();
			col.innerHTML = operation;
			/* col = row.insertCell();
			col.innerHTML = '<a id="editLabel" href=\'javascript:void(0)\' onclick=\'doReset(\"'
					+ prid + '\")\'>重设</a>&nbsp;&nbsp;&nbsp;<a id=\"delLabel\" href=\'javascript:void(0)\' onclick=\'doDelete(\"'
					+ prid + '\")\'>删除</a>'; */
			/* col = row.insertCell();
			col.innerHTML = '<a id=\"delLabel\" href=\'javascript:void(0)\' onclick=\'doDelete(\"'
					+ prid + '\")\'>删除</a>'; */
		}
	}
	
	function doReset(prid) {
		//alert(prid);
		window.location.href = "${pageContext.request.contextPath }/tolockProject/" + prid;
	}
	function doDelete(prid) {
		//alert(prid);
		var flag = confirm('是否确认删除？');
		if (flag) {
			window.location.href = "${pageContext.request.contextPath }/delLockProject/" + prid;
		} else {
			return;
		}
	}

	function toLockProject() {
		var prId = document.getElementsByName("prId")[0].value;
		
		var index = document.getElementById("prId").selectedIndex;
		if (prId != "") {
			window.location.href = "${pageContext.request.contextPath }/tolockProject/" + prId;
		}
	}
</script>
<script type="text/javascript">
$("#dg").datagrid({
	url: "toShowLockInfo",
    pagination: false,//表示在datagrid不设置分页
    rownumbers: false,  
    singleSelect: true,  
    pageSize: 15,
    pageList: [15, 20, 30,],
    columns: [[  
          { field: 'prname', title: '工程名称', width: '25%', align: 'center' },  
          { field: 'units', title: '栋号', width: '25%', align: 'center' },  
          { field: 'checkunits', title: '结算单位', width: '25%', align: 'center' },
          {  
           field: 'lId',  
           title: '操作',  
           width: '25%',  
           align: 'center',  
           formatter: function(value, row, index) {
              var a = "<a href='javascript:void(0)' onclick='doReset(\""+row.prid+"\")'>修改</a>"
              var b = "<a href='javascript:void(0)' onclick='doDelete(\""+row.prid+"\")'>删除</a>"
              return a +"     ||     "+ b; 
            }  
          }           
      ]],  
         
  });


</script>


</body>
</html>
