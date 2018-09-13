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
    <title>合同单价表</title> 

<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/custom/uimaker/icon.css">
<link rel="stylesheet" href="<%=path%>/pages/css/providers.css">
<link rel="stylesheet" href="<%=path%>/js/validation/validate.css">
</head> 
<body>
    <div class="container" style="height:100%">
       <table id="dg" style="width:100%;height:99%" title="合同单价管理" data-options="
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
    <form id="contract"> 
		工程名称： <select name="prId" id=prId style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${prList}" var="r">
                        <option value="${r.prId}">${r.prName}</option>
                      </c:forEach>
                    </select>
                       栋号：<select name="pcpNumber" id=pcpNumber style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                    </select>
                     结算单位： <select name="cuName" id=cuName style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${cuList}" var="r">
                        <option value="${r.cuName}">${r.cuName}</option>
                      </c:forEach>
                    </select>
 	            分部：<select name="pId" id=pId style="width:135px;height:35px;line-height:35px;margin:10px">
                      <option value="">----请选择----</option>
                     <c:forEach items="${partList}" var="r">
                        <option value="${r.pId}">${r.pName}</option>
                      </c:forEach>
                    </select>
                           	
        <a href="#" onclick="select()" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</a>
        <input id="res" name="res" type="reset" style="display:none;"/>
        <br>
        <input type="button" align="right" value="全选" onclick="checkAll('isDelete')" class="easyui-linkbutton" style="height:30px;line-height:30px">
		<input type="button" align="right" value="取消全选" onclick="clearAll('isDelete')" class="easyui-linkbutton" style="height:30px;line-height:30px">
		<input type="button" align="right" value="删除所选" id="delete_submit" class="easyui-linkbutton" style="height:30px;line-height:30px">

</form>
 </div>
</div>
    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/easyui-lang-zh_CN.js"></script>


    
<script src="<%=path%>/js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=path%>/js/validation/custom.validate.js" type="text/javascript"></script>
    
<script type="text/javascript"> 
  
 getData();
 function select(){
   $("#dg").datagrid("load",{                          
	          prId: $("#prId").val(),
        	  pId: $("#pId").val(),        	         	  
        	  pcpNumber: $("#pcpNumber").val(),       	  
        	  cuName:$("#cuName").val(),   
  }); 
 }
 function reset(){
   $("input[name='res']").click();
 }

 function getData(){
 $("#dg").datagrid({  
         url: "selectContract2",
        queryParams://每次请求的参数
             {
        	  prId: $("#prId").val(),
        	  pId: $("#pId").val(),        	         	  
        	  pcpNumber: $("#pcpNumber").val(),       	  
        	  cuName:$("#cuName").val(),       	  
             },
         pagination: true,//表示在datagrid设置分页              
         rownumbers: false,  
         singleSelect: true,  
         pageSize: 15,
         pageList: [15, 20, 30, 50],
         columns: [[ 
               
               { field: 'prName', title: '工程名称', width: '12%', align: 'center' },  
               { field: 'pName', title: '分部', width: '12%', align: 'center' },                
               { field: 'psName', title: '工程部位/分项1', width: '12%', align: 'center' },                 
               { field: 'jobName', title: '工作项目/分项2', width: '13%', align: 'center' },                             
               { field: 'pcpNumber', title: '栋号', width: '8%', align: 'center'},
               { field: 'unName', title: '单位', width: '8%', align: 'center' },
               { field: 'cuName', title: '结算单位', width: '8%', align: 'center' },
               { field: 'price', title: '单价', width: '8%', align: 'center'},
               {  
                field: 'opId',  
                title: '操作',  
                width: '15%',  
                align: 'center',  
                formatter: function(value, row, index) {  
                
                var a = "<a href='javascript:void(0)' onclick='edit(\""+value+"\")'>修改</a>"
                var b = "<input type='checkbox' id='isDelete' name='isDelete' value='"+value+"'>是否删除</input>"   
                   return  a +"     ||     "+ b; 
                 }  
               }     
                                                  
           ]],
           
           onLoadSuccess:function(data){                   
           if(data){
             $.each(data.rows, function(index, item){
               if(item.roPerId != null){
                $('#dg').datagrid('checkRow', index);
              }
                 });
               }
           }
              
       });

 }

function edit(id){
	   var _url = "${pageContext.request.contextPath }/contract/toEditContract/" + id;
	   $('<div></div>').dialog({
	                    id : 'newDialog',
	                    title : '修改合同单价',
	                    width : 602,
	                    height : 480,
	                    closed : false,
	                    cache : false,
	                    href : _url,
	                    modal : true,
	                    collapsible:false, //设置可折叠  
	                    minimizable:false,  
	                    maximizable:false,  
	                    resizable:false, 
	                    onLoad : function() {    //初始化表单数据
	                    
	                    $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: { 
                            price:{required: true,number: true},                              
                          },   

                         submitHandler:function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"editContract",
                           data:postdata,
                           dataType:"text", 
                           async: false,
                           success:function(data){
                           if(data==1){
                            reloadtb();
                           }else{alert("修改失败");}	
                           },  
                           error:function(e) {  
                           alert("修改失败："+e);  
                           }  
                         });
                        $('#newDialog').dialog('close');  
                        }, 
                    invalidHandler: function(form, validator) {  //不通过回调   
                    return false;   
                    }   
                   });
	                    },
	                    onClose : function() {
	                        $(this).dialog('destroy');
	                    },
	                    buttons:[{  
                        text:'提交',
                        iconCls: 'icon-ok',
                        handler:function(){
                         $("#submit").click();  
                        }  
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res1").click();  
                        }  
                      }]                     
	                });
	 }

function reloadtb(){
  $("#dg").datagrid("reload",{                          
	  prId: $("#prId").val(),
        	  pId: $("#pId").val(),        	         	  
        	  pcpNumber: $("#pcpNumber").val(),       	  
        	  cuName:$("#cuName").val(),   
 });
}	                   

 
   function add(){
   var _url = "${pageContext.request.contextPath }/contract/toAddPrice";
   $('<div></div>').dialog({
                    id : 'newDialog',
                    title : '添加合同单价',
                    width : 602,
                    height : 480,
                    closed : false,
                    cache : false,
                    href : _url,
                   modal : true,
                   collapsible:false, //设置可折叠  
                   minimizable:false,  
                   maximizable:false,  
                   resizable:false, 
                   onLoad : function() {    //初始化表单数据
                    $("#prId1").bind('input propertychange',function () { 
                    console.log("怎么回事啊？");
                      var prId = $(this).val();       
                       if(prId!= null && prId!='')
                            {          getUnitNumber(prId);         }
                       else{  $("#pcpId").empty(); }
                   });
  
                   $("#pId1").bind('input propertychange',function () { 
                       var pId = $(this).val();       
                       if(pId!= null  && pId!='')
                             {          getPosition(pId);         }
                       else{  $("#psId").empty(); 
                           var option1=$("<option>").text('--请选择--').val(null);
                           $("#psId").append(option1);}
                       });
  
  $("#psId").bind('input propertychange',function () { 
         var psId = $(this).val();       
         if(psId!= null  && psId!='')
         {          getJobName(psId);         }
         else{   $("#jobKey").empty();             
                var option1=$("<option>").text('--请选择--').val(null);
                $("#jobKey").append(option1);}
 
  });
  
     $("#jobKey").bind('input propertychange',function () { 
         var jobKey = $(this).val();       
         if(jobKey!= null  && jobKey!='')
         {          getUnName(jobKey);         }
     
  });
	                    $("#form").validate({
                           onsubmit:true,// 是否在提交是验证   
                           onfocusout:false,// 是否在获取焦点时验证   
                           onkeyup :false,// 是否在敲击键盘时验证   
                           rules: {                            
                            prId:{required: true}, 
                            pcpIdIsSelect:{required: true}, 
                            cuId:{required: true}, 
                            price:{required: true,number:true}, 
                            psId:{required: true}, 
                            pId:{required: false},
                            jobKey:{required: true,maxlength:50}, 
                            unId:{required: true},                              
                          },   

                         submitHandler:function(){
                         var postdata = $("#form").serialize();
                         $.ajax({  
                           type:"POST",  
                           url:"addPrice",
                           data:postdata,
                           dataType:"text", 
                           async: false,
                           success:function(data){
                           if(data==1){
                            reloadtb();
                           }else{alert("添加失败");}	
                           },  
                           error:function(e) {  
                           alert("添加失败："+e);  
                           }  
                         });
                        $('#newDialog').dialog('close');  
                        }  , 
                    invalidHandler: function(form, validator) {  //不通过回调   
                    return false;   
                    }   
                   });
                    },
                    onClose : function() {
                        $(this).dialog('destroy');
                    }, 
                    buttons:[{  
                        text:'提交',
                        iconCls: 'icon-ok',
                        handler:function(){
                         $("#submit").click();  
                        } 
                      },
                      {  
                        text:'重置',
                        iconCls: 'icon-reload',
                        handler:function(){
                         $("#res1").click();  
                        }  
                      }]                    
                });
 }
 
 
 
  $("#prId").on('input propertychange',function () { 
         var prId = $(this).val();       
         if(prId!= null && prId!='')
         {          getPcpNumber(prId);         }
         else{  $("#pcpNumber").empty(); 
                var option1=$("<option>").text('--请选择--').val(null);
                $("#pcpNumber").append(option1);}
 
  });
     


//联动函数实现
 function  getPcpNumber(prId)
{  
    
    var json = {"prId":prId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#pcpNumber").empty(); 
                var option1=$("<option>").text('--请选择--').val(null);
                $("#pcpNumber").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.unitNumber).val(item.unitNumber);
                 $("#pcpNumber").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};


$("#delete_submit").click(function(){
    if(isAllFalse("isDelete")){
      alert("请选择要删除的对象");
      return false;
    }
    else{ if(confirm("确定要删除吗？")) 
              delAll();}
   });
//批量删除
function  delAll(){                               
                var ids=[];            
               $("[name=isDelete]:checked").each(function(){
                    ids.push($(this).val()); //如果选中，将value添加到变量s中
                } );
                 
               if(ids!='' && ids!=null){
               
            var postdata = JSON.stringify(ids);
             $.ajax({  
                contentType: 'application/json', 
                type:"POST", 
                async:false, 
                url:"deletePrice",
                dataType:"text", 
                data:postdata,
                success:function(data){
                 if(data==1){
                 reloadtb();
                 }else{alert("删除失败");}	
                 },  
                error:function(e) {  
                  alert("删除失败："+e);  
                }  
              });}
                 else{ return;} 
}

function checkAll(name)
{
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = true;
}
}
}

function clearAll(name)
{
var el = document.getElementsByTagName('input');
var len = el.length;
for(var i=0; i<len; i++)
{
if((el[i].type=="checkbox") && (el[i].name==name))
{
el[i].checked = false;
}
}
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

//联动
 function  getUnitNumber(prId)
{  
    
    var json = {"prId":prId};
     console.log(json);
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeUnitNumber",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 listPcpId(data);    
                },  
                error:function(e) {                    
                }  
     });
};

 function  getPosition(pId)
{  
    
    var json = {"pId":pId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changePosition",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#psId").empty(); 
               var option1=$("<option>").text('--请选择--').val(null);
                $("#psId").append(option1);
                $.each(data, function(index,item){                
                 var option = $("<option>").text(item.psName).val(item.psId);                
                 $("#psId").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getJobName(psId)
{  
    
    var json = {"psId":psId};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeJob",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#jobKey").empty(); 
                 var option1=$("<option>").text('--请选择--').val(null);
                $("#jobKey").append(option1);
                $.each(data, function(index,item){ 
                 var option = $("<option>").text(item.jobName).val(item.jobKey);
                 $("#jobKey").append(option);
                 })     
                },  
                error:function(e) {                    
                }  
     });
};

 function  getUnName(jobKey)
{  
    
    var json = {"jobKey":jobKey};
    var postdata = JSON.stringify(json);
    $.ajax({  
                contentType: 'application/json', 
                type:"POST",  
                url:"changeUnitName",
                dataType:"json", 
                data:postdata,
                success:function(data){
                 $("#unId").empty();                
                 var option = $("<option>").text(data.unName).val(data.unId);
                 $("#unId").append(option);                    
                },  
                error:function(e) {                    
                }  
     });
};   

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
		click.innerHTML="<input type='button' value='取消全选栋号' onclick='chooseop();'>";		
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
 </script>  
</body> 
</html>