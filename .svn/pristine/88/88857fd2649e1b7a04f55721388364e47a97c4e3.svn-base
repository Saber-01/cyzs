package com.org.cygs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.org.cygs.pojo.User;
import com.org.cygs.util.common.CygsConst;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String url = request.getRequestURI();
		if (url.indexOf("login/login") >= 0) {
			return true;
		}
		
		if (url.indexOf("login/checkRole") >= 0) {
			return true;
		}
		if (user != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
