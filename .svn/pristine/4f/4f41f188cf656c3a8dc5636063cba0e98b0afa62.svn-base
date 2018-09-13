var SystemMenu;
var mainPlatform = {

	init: function(){
		$.ajax({  
            contentType: 'application/json',
            type:"POST",  
            url:"menuSelect", 
            dataType:"json", 
            async: false,
            success:function(data){
            	SystemMenu = data;
            },  
            error:function(e) {  
              alert("出错啦："+e);  
            }  
        });

		//SystemMenu = [{"isCurrent":true,"title":"系统操作菜单","menu":[{"children":[{"href":"/cyjz_new/missionType/showMissionType","title":"任务书类型"},{"href":"/cyjz_new/checkUnit/showCheckUnit","title":"结算单位"},{"href":"/cyjz_new/auditRole/showAuditRole","title":"审核流程管理"}],"title":"基础信息管理"},{"children":[{"href":"/cyjz_new/viewInfo","title":"修改密码"},{"href":"/cyjz_new/prelockProject","title":"工程信息锁定"}],"title":"个人信息管理"},{"children":[{"href":"/cyjz_new/preAddJJMissionDj","title":"按单价计件任务书"},{"href":"/cyjz_new/createJSMission/preAddJSMission","title":"计时任务书"},{"href":"/cyjz_new/createAlterVisaMission/preAddAlterVisaMission","title":"变更签证任务书"},{"href":"/cyjz_new/createOutMission/preAddOutMission","title":"合同外补差价任务书"},{"href":"/cyjz_new/createOtherMission/preAddOtherMission","title":"合同内其他任务书"},{"href":"/cyjz_new/createJSMissionDj/preAddJSMissionDj","title":"按单价计时任务书"},{"href":"/cyjz_new/preAddJJMission","title":"计件任务书"},{"href":"/cyjz_new/preCopyMission","title":"复制计件任务书"}],"title":"新建任务书"},{"children":[{"href":"/cyjz_new/missionSubmitList","title":"已提交待审核任务书"},{"href":"/cyjz_new/missionUnAuditList","title":"查看未审/退修任务书"},{"href":"/cyjz_new/auditInfoList","title":"查看审核历史记录"},{"href":"/cyjz_new/missionSelectList","title":"按条件查看任务书"},{"href":"/cyjz_new/viewInfo","title":"批量审核任务书"},{"href":"/cyjz_new/viewInfo","title":"下载任务书"},{"href":"/cyjz_new/missionDeleteList","title":"已删除任务书查询"},{"href":"/cyjz_new/missionList","title":"查看任务书"},{"href":"/cyjz_new/viewInfo","title":"查看计件任务书统计表"}],"title":"任务书查询审核"},{"children":[{"href":"/cyjz_new/ykl/showBudgetAndYkl","title":"预算与已开量对比"}],"title":"预控量管理"},{"children":[{"href":"/cyjz_new/viewInfo","title":"班组月度报表"},{"href":"/cyjz_new/viewInfo","title":"劳务工资支付情况明细"}],"title":"统计报表"}]}];
		this.bindEvent();
		this._createTopMenu();
	},

	bindEvent: function(){
		var self = this;
    	var status = 0;
    	var clickNumber =0;
		// 顶部大菜单单击事件
		$(document).on('click', '.pf-nav-item', function() {
            $('.pf-nav-item').removeClass('current');
            $(this).addClass('current');

            // 渲染对应侧边菜单
            var m = $(this).data('sort');
            self._createSiderMenu(SystemMenu[m], m);
        });

        $(document).on('click', '.sider-nav .pf-menu-title', function() {

        	$(this).closest('.pf-sider').find('.sider-nav li').removeClass('current');
            $(this).closest('li').addClass('current');
        	if(status==0){
            $(this).closest('li').children("ul").show(); 
            // if is no-child
            if($(this).closest('.no-child').size() > 0) {
            	var index = $(this).closest('.pf-sider').attr('arrindex');
	        	if($('.easyui-tabs1[arrindex='+ index +']').tabs('exists', $(this).find('.sider-nav-title').text())){
	        		$('.easyui-tabs1[arrindex='+ index +']').tabs('select', $(this).find('.sider-nav-title').text())
	        		return false;
	        	}
	        	$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
					title: $(this).find('.sider-nav-title').text(),
					content: '<iframe class="page-iframe" src="'+ $(this).closest('.no-child').data('href') +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
					closable: true
				});
            }
            status = 1;
        }else {
    			$(this).closest('li').removeClass('current');
        		$(this).closest('li').children("ul").hide();
        		status = 0;
        		}
            //$('iframe').attr('src', $(this).data('src'));
        });

        $(document).on('click', '.pf-logout', function() {
            layer.confirm('您确定要退出吗？', {
              icon: 4,
			  title: '确定退出' //按钮
			}, function(){
			  location.href= 'login.html'; 
			});
        });
        $(document).on('click', '.sider-nav-s li', function(e){
        	var index = $(this).closest('.pf-sider').attr('arrindex');
        	$(this).closest('.pf-sider').find('.active').removeClass('active');
        	$(this).addClass('active');
        	if($('.easyui-tabs1[arrindex='+ index +']').tabs('exists', $(this).text())){
        		$('.easyui-tabs1[arrindex='+ index +']').tabs('select', $(this).text())
        		return false;
        	}
        	$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
				title: $(this).text(),
				content: '<iframe class="page-iframe" src="'+ $(this).data('href') +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
				closable: true
			});
        });
        //左侧菜单收起
        $(document).on('click', '.toggle-icon', function() {
            $(this).closest("#pf-bd").toggleClass("toggle");
            $(window).resize();
        });

         //关闭当前
	     $('#mm-tabclose').click(function(){
	         var currtab_title = $('#mm').data("currtab");
	         $(".easyui-tabs1:visible").tabs('close',currtab_title);
	     })
	     //全部关闭
	     $('#mm-tabcloseall').click(function(){
	         $(".easyui-tabs1:visible").find('.tabs li').each(function(i,n){
	             $(".easyui-tabs1:visible").tabs('close', $(n).text());
	         });    
	     });
	     //关闭除当前之外的TAB
	     $('#mm-tabcloseother').click(function(){
	         var currtab_title = $('#mm').data("currtab");
	         $('.tabs-inner span').each(function(i,n){
	             if($(n).text() !== currtab_title)
	                 $(".easyui-tabs1:visible").tabs('close',$(n).text());
	         });    
	     });


        // $(document).on('click', '.pf-modify-pwd', function() {
        //     $('#pf-page').find('iframe').eq(0).attr('src', 'backend/modify_pwd.html')
        // });

        // $(document).on('click', '.pf-notice-item', function() {
        //     $('#pf-page').find('iframe').eq(0).attr('src', 'backend/notice.html')
        // });
	},

	// renderTopMenu
	_createTopMenu: function(){
		var menuStr = '',
			currentIndex = 0;
		for(var i = 0, len = SystemMenu.length; i < len; i++) {
			menuStr += '<li class="pf-nav-item project" data-sort="'+ i +'" data-menu="system_menu_" + i>'+
                      '<a href="javascript:;">'+
                          '<span class="pf-nav-title">'+ SystemMenu[i].title +'</span>'+
                      '</a>'+
                  '</li>';
            // 渲染当前
            if (SystemMenu[i].isCurrent){
            	currentIndex = i;
            	this._createSiderMenu(SystemMenu[i], i);
            }
		}

		$('.pf-nav').html(menuStr);
		$('.pf-nav-item').eq(currentIndex).addClass('current');
	},

	_createSiderMenu: function(menu, index){
		$('.pf-sider').hide();
		this._createPageContainer(index);
		if($('.pf-sider[arrindex='+ index +']').size() > 0) {
			
			$('.pf-sider[arrindex='+ index +']').show();
			return false;
		};
		var menuStr = '<h2 class="pf-model-name">'+
                    '<span class="pf-name">'+ menu.title +'</span>'+
                    '<span class="toggle-icon"></span>'+
                '</h2><ul class="sider-nav">';

        for(var i = 0, len = menu.menu.length; i < len; i++){
        	var m = menu.menu[i],
        		mstr = '';
        	var str = '';

        	if(m.isCurrent){
        		if(m.children && m.children.length > 0) {
        			str = '<li class="current">';
        		}else{
        			str = '<li class="current no-child" data-href="'+ m.href +'">';
        		}
        	}else{
        		if(m.children && m.children.length > 0) {
        			str = '<li>';
        		}else{
        			str = '<li class="no-child" data-href="'+ m.href +'">';
        		}
        	}
        	//str = m.isCurrent ? '<li class="current">' : '<li>';

           str += '<a href="javascript:;" class="pf-menu-title">'+
                '<span class="sider-nav-title">'+ m.title +'</span>'+
                '<i class="iconfont">&#xe642;</i>'+
            '</a>'+
            '<ul class="sider-nav-s">';
            var childStr = '';
            for(var j = 0, jlen = m.children.length; j < jlen; j++){
            	var child = m.children[j];
            	if(child.isCurrent){
            		childStr += '<li class="active" text="'+ child.title +'" data-href="' + child.href + '"><a href="#">'+ child.title +'</a></li>';
            		$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
						title: child.title,
						content: '<iframe class="page-iframe" src="'+ child.href +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
						closable: true
					});
            	}else {
            		childStr += '<li text="'+ child.title +'" data-href="' + child.href + '"><a href="#">'+ child.title +'</a></li>';
            	}
            }

            mstr = str + childStr + '</ul></li>';

            menuStr += mstr;

            
        }
        $('.pf-sider-wrap').append($('<div class="pf-sider" arrindex="'+ index +'"></div>').html(menuStr + '</ul>'));

	},

	_createPageContainer: function(index){
		$('.easyui-tabs1').hide();
		if($('.easyui-tabs1[arrindex='+ index +']').size() > 0){
			$('.easyui-tabs1[arrindex='+ index +']').show();
			return false;
		}
		var $tabs = $('<div class="easyui-tabs1" arrindex="'+ index +'" style="width:100%;height:100%;">');
		$('#pf-page').append($tabs);
		$tabs.tabs({
	      tabHeight: 44,
	      onSelect:function(title, index){
	        var currentTab = $tabs.tabs("getSelected");
	        if(currentTab.find("iframe") && currentTab.find("iframe").size()){
	            //currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
	        }
	        $('.pf-sider:visible').find('.sider-nav-s li').removeClass('active');
	        $('.pf-sider:visible').find('.sider-nav-s li[text='+ title +']').addClass('active');
	      }
	    });

	    $tabs.find('.tabs-header').on('contextmenu', function(e){
	    	e.preventDefault();
	    	if($(e.target).closest('li').size() > 0 || $(e.target).is('li')){
	    		$('#mm').menu('show', {
		             left: e.pageX,
		             top: e.pageY,
		         });
	    		var subtitle = $(e.target).closest('li').size() ? $(e.target).closest('li') : $(e.target);
        		$('#mm').data("currtab",subtitle.text());
	    	}
	    })
	}

};

mainPlatform.init();