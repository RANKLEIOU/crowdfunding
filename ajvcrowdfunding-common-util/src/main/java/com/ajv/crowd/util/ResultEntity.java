package com.ajv.crowd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {

	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	//用于封装当前请求的结果
	private String result;
	//请求失败后返回的消息
	private String message;
	//返回的数据
	private T data;

	/**
	 * 请求成功但是没有携带数据调用
	 * @param <Type>
	 * @return
	 */
	public static <Type> ResultEntity<Type> success(){
		return new ResultEntity<>(SUCCESS,null,null);
	}

	/**
	 * 请求成功并且携带数据调用
	 * @param data
	 * @param <Type>
	 * @return
	 */
	public static <Type> ResultEntity<Type> successWithData(Type data){
		return new ResultEntity<>(SUCCESS,null,data);
	}

	/**
	 * 请求失败后调用
	 * @param message
	 * @param <Type>
	 * @return
	 */
	public static <Type> ResultEntity<Type> failed(String message){
		return new ResultEntity<>(FAILED,message,null);
	}

}
