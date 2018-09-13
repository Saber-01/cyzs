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
<title>新建预算表</title>
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/customersTable.css" />
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 
<script type="text/javascript">

$(document).ready(function(){

   $("#prId").bind('change',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);
          changePart(prId);         }
         else{  $("#pcpId").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#pcpId").append(option1);
                $("#pName").empty(); 
                var option=$("<option>").text('---请选择---').val(null);
                $("#pName").append(option)}
  });
     

  
  
     $("#pName").bind('input propertychange',function () { 
    var pName = $("#pName").val(); 
    var prId =$("#prId").val();
    console.log(pName+prId)
    if(pName!= null && pName!=''&& prId!= null && prId!='')
    {              changePosition(prId,pName);         }
       else{  $("#psName").empty(); 
                var option1=$("<option>").text('---请选择---').val(null);
                $("#psName").append(option1)}
  });
  
   $("#budgetAccount").bind('input propertychange',function(){
   countdis();
  });
  

  
   $("#delete_submit").click(function(){
    delAll();
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



//工程与栋号级联
 function  getPcpNumber(prId)
{  
    
    var json = {"prId":prId};
     console.log(json);
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 listPcpId(data);    
                },  
                error:function(e) {                    
                }  
     });
};
function changePart(prId)
{  
 
    var json = {"prId":prId};
    if(json!=null && json!= undefined){
    console.log(json);   
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"budget/changePart",
                dataType:"json", 
                data:postdata,
                success:function(data){
               
                 $("#pName").empty(); 
                 var option1=$("<option>").text('---请选择---').val(null);
                $("#pName").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.pName).val(item.pName);
                 console.log($("<option>").val());
                 
                 $("#pName").append(option);
                 })     
                },  
                error:function(e) { alert("出错"+e);                   
                }  
     });
   
   }  
}

//分部与工程部位级联
 function  changePosition(prId,pName)
{  
    
    var json = {"prId":prId,"pName":pName};
     console.log(json);
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"contract/changePartPosition",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 listPsName(data);    
                },  
                error:function(e) {                    
                }  
     });
};
//请求显示数据
function paging()
{
   
   var json = {};
   json['prId'] =$("#prId").val();;
   json['pName'] =$("#pName").val();;
   json['psId'] = $("#psId").val(); ;
   json['pcpId'] = $("#pcpId").val(); ;
   json['unName'] = $("#unName").val(); ;
   json['cuId'] = $("#cuId").val(); ;
   json['classOrNot'] = $("#classOrNot").val(); ;
   
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json',
                type:"POST",  
                url:"budget/selectPrice", 
                dataType:"json", 
                data:postdata,
                async:false, 
                success:function(data){              
                
                if(data!=null){
                $("#customers tr:gt(0)").remove();               
                $.each(data, function(index,item){                    
                $("#customers").append("<tr bgcolor='white'><td>"+(index+1)+"</td><td></td><td>"+item.prName+"</td><td>"+item.pcpNumber+"</td><td>"+item.cuName+"</td><td algin='left'><input type='hidden' value='"+item.pId+"'>"+item.pName+"</td><td>"+item.psName+"</td><td>"+item.jobName+"</td><td>"+item.fsName+"</td><td><input type='hidden' value='"+item.unId+"'>"+item.unName+"</td><td>"+item.price+"</td><td>"+item.ykl+"</td><td>"+item.ysl+"</td><td>"+item.budgetPos+"</td><td>"+item.budgetName+"</td><td><input type='checkbox' class='acheck' id='isAdd' name='isAdd' value='"+item.opId+"' onclick='sum()'>选择</input></td></tr>");                                                                                                     
                })
                $("#customers").append("<tr bgcolor='white'><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td id='yklsum'></td><td id='yslsum'></td><td></td><td colspan='2'><input type='button' value='全选'  onclick='checkAll()'><input type='button' value='取消' onclick='clearAll()'></td></tr>");
                }
                
                },  
                
                error:function(e) {  
                    alert("出错啦："+e);  
                }  
     });

}
function listPcpId(data)
	 {
		
        var TD=document.getElementById("pcpId");
        var childs = TD.childNodes;   				//删除先前的栋号
		for(var i = 0; i < childs.length; i++) {     
    		TD.removeChild(childs[i]);   
		} 
		var unnumbox;
     	var checkboxdiv=document.createElement("div");
			checkboxdiv.setAttribute("id","divid");
			$.each(data, function(index,item){
				unnumbox=document.createElement("input");
				unnumbox.setAttribute("type","checkbox");
				unnumbox.setAttribute("id","pcpIdIsSelect");
				unnumbox.setAttribute("name","pcpIdIsSelect");
				unnumbox.setAttribute("value",item.pcPId);
				var content=document.createTextNode(item.unitNumber);
				var BR;
		        if(i%10==0)						//一行显示10个，暂时处理
		        {
		        	BR=document.createElement("br");
		        }
		        var nbsp=document.createTextNode("  ");
		        checkboxdiv.appendChild(unnumbox); 
		        checkboxdiv.appendChild(content);
		        checkboxdiv.appendChild(nbsp);
		              
			});
		TD.appendChild(checkboxdiv);
		createbutton();	
		
	 }
	//全选或取消全选
	function createbutton()
	{	
		
		var o=document.createElement("input"); 
		o.type = "button" ;                       //设置元素的类型
		o.value = "全选栋号";            //设置元素的值
		document.getElementById("click").appendChild(o); //添加控件到窗体中
		o.onclick = new Function('choose()');
		o = null; 				   //释放对象
		click.innerHTML="<input type='button' value='全选栋号' onclick='choose()'>";	   		
	}
	function choose()
	{
		var input=document.getElementsByName("pcpIdIsSelect");
		for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			input[i].checked = true;
      		}
  		}
		click.innerHTML="<input type='button' value='取消全选' onclick='chooseop();'>";		
	}
	function chooseop()
	{
		var input=document.getElementsByName("pcpIdIsSelect");
		for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			input[i].checked = false;
  			}
  		}
		click.innerHTML="<input type='button' value='全选栋号' onclick='choose()'>";		
	} 
function listPsName(data)
	 {
		
        var TD=document.getElementById("psName");
        var childs = TD.childNodes;   				//删除先前的栋号
		for(var i = 0; i < childs.length; i++) {     
    		TD.removeChild(childs[i]);   
		} 
		var unnumbox;
     	var checkboxdiv=document.createElement("div");
			checkboxdiv.setAttribute("id","divid");
			$.each(data, function(index,item){
				unnumbox=document.createElement("input");
				unnumbox.setAttribute("type","checkbox");
				unnumbox.setAttribute("id","psNameIsSelect");
				unnumbox.setAttribute("name","psNameIsSelect");
				unnumbox.setAttribute("value",item.psName);
				var content=document.createTextNode(item.psName);
				var BR;
		        if(i%10==0)						//一行显示10个，暂时处理
		        {
		        	BR=document.createElement("br");
		        }
		        var nbsp=document.createTextNode("  ");
		        checkboxdiv.appendChild(unnumbox); 
		        checkboxdiv.appendChild(content);
		        checkboxdiv.appendChild(nbsp);
		       	        
			});
		TD.appendChild(checkboxdiv);
        createbutton1();
	 }

	//全选或取消全选
	function createbutton1()
	{	
		
		var o=document.createElement("input"); 
		o.type = "button" ;                       //设置元素的类型
		o.value = "全选";            //设置元素的值
		document.getElementById("click1").appendChild(o); //添加控件到窗体中
		o.onclick = new Function('choose1()');
		o = null; 				   //释放对象		
		click1.innerHTML="<input type='button' value='全选' onclick='choose1()'>";	   		
	}
	function choose1()
	{
		var input=document.getElementsByName("psNameIsSelect");
		for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			input[i].checked = true;
      		}
  		}
		click1.innerHTML="<input type='button' value='取消全选' onclick='chooseop1();'>";		
	}
	function chooseop1()
	{
		var input=document.getElementsByName("psNameIsSelect");
		for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			input[i].checked = false;
  			}
  		}
		click1.innerHTML="<input type='button' value='全选' onclick='choose1()'>";		
	} 
	
		 
function isEmpty(strname)
	{
		if(strname==null||strname.length==0)
			return true;
		else 
			return false;
	}

function isAllFalse(name){
  var input=document.getElementsByName(name);
      for(var i=0; i<input.length; i++) {		
    		if(input[i].type.toLowerCase() == "checkbox" )
    		{
      			if(input[i].checked ==true){
      			return false;
      			}
      		}
  		}
  	return true;
}

function validate(){
  var prId=$("#prId").val();
  var pName=$("#pName").val();
  if(isAllFalse("pcpIdIsSelect")){
            {
				alert("栋号不能为空")
				return false;
			}
  }
    if(isAllFalse("psNameIsSelect")){
            {
				alert("工程部位不能为空")
				return false;
			}
  }
     if(isEmpty(prId)){
            {
				alert("工程不能为空")
				return false;
			}
  }
  
   if(isEmpty(pName)){
            {
				alert("分部不能为空")
				return false;
			}
  }
  
  return true;
  
}
</script> 

</head>
<body>

<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		<tbody>
			<tr>
				<td height="35" style="text-align:center;background:#e3e3e3;border:1px solid #cacaca">
				<font style="font-size:1.0em;">班组计件汇总</font>
				</td>
			</tr>
		</tbody>
</table>
<form name="queryForm" action="statistic/listSettledPieceWork1" method="post">
      <table id="choose" class="table table-striped table-bordered table-condensed">
            <tr>
                <td style="width:10%">工程名称（必选）</td>
                <td colspan=2>
                   <select name="prId" id=prId style="width:200px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                </td>
              </tr>
              <tr>
                <td style="width:10%">栋号（必选）</td>
                <td id=pcpId >
				
                </td>
                <td style="width:10%"><span id="click" style="background:#f1f1f1;"></span></td>
              </tr>
             <tr>
             	<td style="width:10%">分部（必选）</td>
                <td colspan=2>
                   <select name="pName" id=pName style="width:150px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pName}">${r.pName}</option>
                      </c:forEach>
                    </select>                 
                </td>
             </tr>
             <tr>
                <td>工程部位（必选）</td>
                <td id="psName">
					
    		    </td> 
    		    <td><span id="click1" style="background:#f1f1f1;"></span></td>               
             </tr>
             <tr>
             	<td style="width:10%">结算单位</td>
                <td style="width:30%">
                   <select name="cuId" id=cuId style="width:150px;height:30px;line-height:30px;">
                      <option value="">---请选择---</option>
                     <c:forEach items="${cuList}" var="r">
                        <option value="${r.cuId}">${r.cuName}</option>
                      </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="button" value="查询" class="easyui-linkbutton" style="height:30px;line-height:30px;" onclick="if(validate())queryForm.submit();"/>
                    <input type="reset" class="easyui-linkbutton" style="height:30px;line-height:30px;" value="重置"/> 
                 
                </td>
            </tr>
         </tbody>
       
</table>
</form>




</body>
</html>
