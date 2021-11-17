package com.ajv.crowd.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CrowdUtil {
	/**
	 * 对明文密码进行加密处理
	 * @param source 传入需要加密的明文
	 * @return 加密结果
	 */
	public static String md5(String source){

		// 判断字符是否有效
		if (source == null || source.length() == 0){
			//如果无效则抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}

		try {
			// 获取MessageDigest对象
			String algorithm = "md5";
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

			// 获取明文字符串的字节数组
			byte[] input = source.getBytes();

			// 执行加密
			byte[] output = messageDigest.digest(input);

			// 创建bigInteger对象
			// 规定返回的值-1为负数，0为0，1为正数
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum,output);

			//按照16进制将bigInteger的值转换为字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();

			return encoded;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断是否为Ajax请求
	 * @param request 用户发送的请求
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
