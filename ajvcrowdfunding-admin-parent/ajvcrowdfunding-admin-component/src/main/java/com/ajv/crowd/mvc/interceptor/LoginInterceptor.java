package com.ajv.crowd.mvc.interceptor;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 1.获取session域对象
		HttpSession session = request.getSession();

		// 2.从session中获取登录用户信息
		Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

		// 3.判断登录信息是否为空
		if (admin == null){
			// 4.如果为空表示未登录，则抛出验证异常
			throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
		}

		return true;
	}
}
