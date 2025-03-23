package com.xxx;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quincy.sdk.AuthHelper;
import com.quincy.sdk.annotation.CustomizedBeforeAuthInterceptor;
import com.quincy.sdk.o.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 模拟登录
 */
@CustomizedBeforeAuthInterceptor(pathPatterns = "/**", order = 0)
@Component
public class LoginSimulateInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		System.out.println("LoginSimulateInterceptor============"+request.getRequestURI());
		request.getSession();
		AuthHelper.setUser(request, new User());
		return true;
	}
}
