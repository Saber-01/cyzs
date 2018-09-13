
$(document).ready(function(){
	// 密码
    jQuery.validator.addMethod("Password", function(value, element) {
        var reg = /^[A-Za-z0-9]{6,20}$/;
        return reg.exec(value);
    }, "密码最少为6位字符，最多为20位字符(只能输入数字、英文字母)"),
    
    // 重复密码
    jQuery.validator.addMethod("PasswordAgain", function(value, element) {
    	return $.trim(value) == $.trim($(value[0]).val());
    }, "两次输入的密码不一致"),
    
    // 下拉框不为空
    jQuery.validator.addMethod("notNull", function(value, element) {
    	return $.trim(value) != "";
    }, "请选择一项值"),
	
    // 判断英文和数字字符 
    jQuery.validator.addMethod("EnglishNumber", function(value, element) {
    	var reg = /^[A-Za-z0-9]+$/;
         return reg.exec(value);
    }, "只能包含英文字母和数字"); 
    
	// 判断中文字符 
    jQuery.validator.addMethod("isnotChinese", function(value, element) {
    	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
         return reg.exec(value);
    }, "只能包含中文、英文、数字");   
 
    // 判断英文字符 
    jQuery.validator.addMethod("isnotEnglish", function(value, element) {   
    	 var reg = /^[A-Za-z]+$/;
    	 return reg.exec(value);   
    }, "只能包含英文字符"); 
    
	// 只能输入[0-9]数字
    jQuery.validator.addMethod("isDigits", function(value, element) { 
    	 var reg = /^\d{4}$/;
         return reg.exec(value);     
    }, "只能输入4位数字");  
    

});

/*
$.validator.setDefaults({  
    
    submitHandler:function(form){  
        form.submit();//提交时拦截  
          
    },  
    errorElement:'div',  
    errorPlacement: function(error, element) {  
        error.addClass('tooltip tooltip-inner arrow-left');  
        if (element.is(":radio")){  
            error.appendTo(element.parent().next().next());  
        }else if (element.is(":checkbox")){  
            error.appendTo(element.next());  
        }else{  
             element.after(error);  
        }  
        var pos = $.extend({}, element.offset(), {  
            width: element.outerWidth()  
          , height: element.outerHeight()  
          }),  
          actualWidth = error.outerWidth(),  
          actualHeight = error.outerHeight();  
        if((pos.top - actualHeight)<0){actualHeight=0;pos.width+=10;}//如果输入框距离顶端为0情况把提示放右边  
        if(element.parents(".blockPage").attr("class")=="blockUI blockMsg blockPage"){//如果是弹出框的，那么设置如下  
             error.css({display:'block',opacity:'0.6' ,top:pos.top - $(document).scrollTop() - actualHeight - 100, "border-left": '0px'});  
        }  
        else if (element.is(":radio")){//类型为radio的显示如下  
            error.css({display:'block',opacity:'0.6',top: pos.top - actualHeight, left: pos.left + pos.width / 2 });  
        }else{//其他均为以下显示  
            error.css({display:'block',opacity:'0.6',top: pos.top - actualHeight, left: pos.left + pos.width-10 });  
        }  
    },  
    highlight: function(element, errorClass) {  
        //高亮显示  
        $(element).addClass(errorClass);  
        $(element).parents('li:first').children('label').addClass(errorClass);  
    },  
    unhighlight:function(element, errorClass){  
        $(element).removeClass(errorClass);  
        $(element).parents('li:first').children('label').removeClass(errorClass);  
    }  
});*/
