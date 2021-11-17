package com.ajv.crowd.util;

import javax.servlet.http.HttpServletRequest;

public class CrowdUtil {

	/**
	 * 判断是否为Ajax请求
	 * @param request
	 * @return true表示该请求是Ajax请求
	 * 			false表示该请求是普通请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request){
		//获取请求头
		String acceptHeader = request.getParameter("Accept");
		String XRequestHeader = request.getParameter("X-Request-With");

		//判断
		return (acceptHeader != null && acceptHeader.contains("application/json")) || (XRequestHeader != null && XRequestHeader.equals("XMLRequest"));
	}
}
