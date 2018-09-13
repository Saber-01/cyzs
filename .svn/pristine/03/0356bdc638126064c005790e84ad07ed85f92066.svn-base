<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>重庆诚业建筑工程有限公司</title> 
<link href="<%=path%>/pages/css/base.css" rel="stylesheet">
<link href="<%=path%>/pages/css/platform.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/custom/uimaker/easyui.css">
</head> 
<body>
    <div class="container">
        <div id="pf-hd">
            <div class="pf-logo">
            <table>
            <tr>
                <%-- <td>
                <img width="70" height="70" src="<%=path%>/pages/images/cyjz_logo.png" alt="logo">
                </td> --%>
                <td style="padding-top: 5px;">
                <img width=460 height=70 src="<%=path%>/pages/images/cyjz_title.png" alt="logo">
            	</td>
            </tr>
            </table>
            </div>

           

	
            <div class="pf-user">
                <div class="pf-user-photo">
                    <img src="<%=path%>/pages/images/main/user.png" alt="">
                </div>
                <h4 class="pf-user-name ellipsis">${user.userName}</h4>
                <i class="iconfont xiala">&#xe607;</i>

                <div class="pf-user-panel">
                    <ul class="pf-user-opt">
                        <li>
                            <a onclick="addTab('用户信息','/cyjz_new/viewInfo#aaaa')">
                                <i class="iconfont">&#xe60d;</i>
                                <span class="pf-opt-name">用户信息</span>
                            </a>
                        </li>
                        <li class="pf-modify-pwd">
                            <a onclick="addTab('修改密码','/cyjz_new/editPassword')">
                                <i class="iconfont">&#xe634;</i>
                                <span class="pf-opt-name">修改密码</span>
                            </a>
                        </li>
                        <li class="pf-logout">
                            <a href="logout">
                                <i class="iconfont">&#xe60e;</i>
                                <span class="pf-opt-name">退出</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            
		<div style="width:100%;text-align:center;line-height:70px">
           <c:choose>
              <c:when test="${user.roleName == '工长'}">
               <a href="javascript:void(0)" style="color:#f0f0f0;text-decoration:none" onclick="addTab('查看未审/退修任务书','/cyjz_new/missionUnAuditList')">您有<span id="count" style="color:#ffff37"><b>0</b></span>条未审/退修任务书</a> &nbsp;&nbsp;&nbsp;&nbsp;
              </c:when>
              <c:otherwise>
              <a href="javascript:void(0)" style="color:#f0f0f0;text-decoration:none" onclick="addTab('审核任务书','/cyjz_new/missionToAuditList')">您有<span id="count" style="color:#ffff37"><b>0</b></span>条未审/退修任务书</a> &nbsp;&nbsp;&nbsp;&nbsp; 
              </c:otherwise>
           </c:choose>
		   <a href="javascript:void(0)" style="color:#f0f0f0;text-decoration:none" onclick="addTab('暂不处理任务书','/cyjz_new/missionStopAuditList')">您有<span id="stopcount" style="color:#ffff37"><b>0</b></span>暂不处理任务书</a>
		   
		</div>

			
			</div>
        


        <div id="pf-bd">
            <div class="pf-sider-wrap" id="aaaa">
            
            </div>
            

            <div id="pf-page">
            </div>
        </div>

        <div id="pf-ft">
            <div class="system-name">
              <i class="iconfont">&#xe6fe;</i>
              <span>重庆诚业建筑工程有限公司&nbsp;v2.0</span>
            </div>
            <div class="copyright-name">
              <span>CopyRight&nbsp;2017&nbsp;&nbsp;重庆大学软件学院软件研发中心&nbsp;</span>
              <i class="iconfont" >&#xe6ff;</i>
            </div>
        </div>
    </div>

    <div id="mm" class="easyui-menu tabs-menu" style="width:120px;">
         <div id="mm-tabclose">关闭</div>
         <div id="mm-tabcloseall">关闭所有</div>
         <div id="mm-tabcloseother">关闭其他</div> 
    </div>

    <script type="text/javascript" src="<%=path%>/custom/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/custom/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="<%=path%>/pages/js/getTime.js"></script>

    <script type="text/javascript" src="<%=path%>/pages/js/main.js"></script>
    <!--[if IE 7]>
      <script type="text/javascript">
        $(window).resize(function(){
          $('#pf-bd').height($(window).height()-76);
        }).resize();
        
      </script>
    <![endif]-->  

    
    <script type="text/javascript">
    // $('.easyui-tabs1').tabs({
    //   tabHeight: 44,
    //   onSelect:function(title,index){
    //     var currentTab = $('.easyui-tabs1').tabs("getSelected");
    //     if(currentTab.find("iframe") && currentTab.find("iframe").size()){
    //         currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
    //     }
    //   }
    // })
    $(window).resize(function(){
          $('.tabs-panels').height($("#pf-page").height()-46);
          $('.panel-body').height($("#pf-page").height()-76)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
      $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


      if($(this).hasClass('disabled')) return;
      if($(this).hasClass('pf-nav-next')){
        page++;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == pages){
          $(this).addClass('disabled');
          $('.pf-nav-prev').removeClass('disabled');
        }else{
          $('.pf-nav-prev').removeClass('disabled');
        }
        
      }else{
        page--;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == 0){
          $(this).addClass('disabled');
          $('.pf-nav-next').removeClass('disabled');
        }else{
          $('.pf-nav-next').removeClass('disabled');
        }
        
      }
    })

    // setTimeout(function(){
    //    $('.tabs-panels').height($("#pf-page").height()-46);
    //    $('.panel-body').height($("#pf-page").height()-76)
    // }, 200)
    
        function addTab(title, url){
    	if ($('.easyui-tabs1').tabs('exists', title)){
    		$('.easyui-tabs1').tabs('select', title);
    	} else {
    		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    		$('.easyui-tabs1').tabs('add',{
    			title:title,
    			content:content,
    			closable:true
    		});
    	}
    }
    
    
    
 $(document).ready(function(){
updateCount(); 
  
});
   
  function updateCount(){
  $.ajax({  
           type:"POST",  
           url:"${pageContext.request.contextPath }/updateAuditCount",
           dataType:"json", 
           async: false,
           success:function(data){
        	var count =document.getElementById ("count");
        	var stopcount =document.getElementById ("stopcount");
        	count.innerHTML = data.toAuditCount;
        	stopcount.innerHTML = data.stopAcount;
            },  
           error:function(e) {    
            }  
       });
  }
    
    
    
    
    
    
    </script>
    

    
</body> 
</html>
