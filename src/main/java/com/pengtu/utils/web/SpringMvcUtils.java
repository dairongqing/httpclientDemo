/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringMvcUtils.java,v 1.1 2017/03/15 05:52:49 zwm Exp $
 */
package com.pengtu.utils.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengtu.Constants;
import com.pengtu.utils.DateUtils;

/**
 * SpringMvcUtils工具类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author calvin
 */
public class SpringMvcUtils {

	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		   //日期输出格式yyyy-MM-dd
		   mapper.setDateFormat(new SimpleDateFormat(DateUtils.defaultDatePattern));
		   // 如果为空则不输出
		   /* mapper.setSerializationInclusion(Include.NON_NULL);*/
	       // 对于空的对象转json的时候不抛出错误
		   /*mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);*/
		   // 视空字符传为null
		   /* mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);*/
		   // 如果为空则输出空字符
		   mapper.getSerializerProvider().setNullValueSerializer(
			    new JsonSerializer<Object>(){
	            @Override  	
	            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
	            		throws IOException, JsonProcessingException {  
	                jg.writeString("");  
	            } 
	        }); 
	 	}
	   
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession(boolean isNew) {
		return getRequest().getSession(isNew);
	}

	/**
	 * 取得HttpSession中Attribute的简化函数.
	 */
	public static Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}
	
	/**
	 * 取得HttpSession中Attribute的简化函数.
	 */
	public static void setSessionAttribute(String name,Object value) {
		HttpSession session = getSession(false);
		if(session != null) {
			session.setAttribute(name, value);
		}
	}
	
	public static void setRequestAttribute(String name,Object value){
		getRequest().setAttribute(name, value);
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 取得HttpRequest中Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return filterDangerString(getRequest().getParameter(name));
	}
	/**
	 * 取得HttpRequest中Parameter的简化方法.
	 */
	public static String getParameterV2(String name) {
		return getRequest().getParameter(name);
	}
	public static String getHeader(String name) {
		return filterDangerString(getRequest().getHeader(name));
	}
	
	public static  String filterDangerString(String value) {
		if (value == null) {
			return null;
		}
     	value = value.replaceAll("\\|", "");
		value = value.replaceAll("&", "&amp;");
		//value = value.replaceAll("@", "");
		//value = value.replaceAll("'", "");
		//value = value.replaceAll("\"", "");
		//value = value.replaceAll("\\'", "");
		//value = value.replaceAll("\\\"", "");
		//value = value.replaceAll("<", "");
		//value = value.replaceAll(">", "");
     	//value = value.replaceAll("\\(", "");
		//value = value.replaceAll("\\)", "");
		//value = value.replaceAll("\\+", "");
		value = value.replaceAll("\r", "");
		value = value.replaceAll("\n", "");
		value = value.replaceAll("eval", "");
		value = value.replaceAll("src", "");
		//value = value.replaceAll("%", "");
		value = value.replaceAll("iframe", "");
		value = value.replaceAll("window", "");
		//value = value.replaceAll("location", "");
		value = value.replaceAll("script", "");
		
		//value = value.replaceAll("%27", "");//'
		//value = value.replaceAll("%22", "");//"
		//value = value.replaceAll("%3E", "");//>
		//value = value.replaceAll("%3C", "");//<
		value = value.replaceAll("%3D", "");//=
		value = value.replaceAll("%2F", "");//%2F解码/
		
		
		value = value.replaceAll("alert", "");
		value = value.replaceAll("blur", "");
		value = value.replaceAll("clearInterval", "");
		value = value.replaceAll("clearTimeout", "");
		value = value.replaceAll("close", "");
		value = value.replaceAll("confirm", "");
		value = value.replaceAll("focus", "");
		value = value.replaceAll("createPopup", "");
		value = value.replaceAll("moveTo", "");
		value = value.replaceAll("moveBy", "");
		value = value.replaceAll("print", "");
		value = value.replaceAll("prompt", "");
		value = value.replaceAll("open", "");
		
		value = value.replaceAll("resizeBy", "");
		value = value.replaceAll("resizeTo", "");
		value = value.replaceAll("scrollBy", "");
		value = value.replaceAll("scrollTo", "");
		value = value.replaceAll("setInterval", "");
		value = value.replaceAll("setTimeout", "");
		return value.trim();
	}
	
	/**
	 * 取得HttpRequest中getParameterValues的简化方法.
	 */
	public static String [] getParameterValues(String name){
		return getRequest().getParameterValues(name);
	}
	
	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	}

	
	/**
	 * 跳转到URL页面 
	 * @throws IOException 
	 */
	public static void sendRedirectUrl(String url) throws IOException {
		String  targetUrl = URLDecoder.decode(url, "UTF-8");
		RedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();
		defaultRedirectStrategy.sendRedirect(getRequest(), getResponse(), targetUrl);
	}
	
	/**
	 * 跳转回原来页面  使用sendRedirect  重定向
	 * sendRefererUrl:
	 * 适用:
	 * @throws IOException
	 * @throws ServletException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static void sendRefererUrl() throws IOException, ServletException {
		sendRefererUrl(false);
	}
	
	/**
	 * 跳转回原来页面
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public static void sendRefererUrl(boolean useForward) throws IOException, ServletException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String  targetUrl = SpringMvcUtils.getHeader("Referer");
		if(useForward) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(targetUrl);
            dispatcher.forward(request, response);
		} else {
			RedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();
			defaultRedirectStrategy.sendRedirect(request, getResponse(), targetUrl);
		}
	}
	
	/**
	 * 获得web程序的在服务器上的发布路径
	 * ServletContext:
	 * 适用:
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	/**
	 * 获得web程序的在服务器上的发布路径
	 * getAppRootPath:
	 * 适用:
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getAppRootPath() {

		return getServletContext().getRealPath("/").toString();
	}
	
	
	/**
	 * 获得web程序的classpath--localhost:8080/项目名称
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 * */
	public static String getRootPath(){
		return getRequest().getContextPath();
	}
	
	/**
	 * 
	 * renderSuccessJson:
	 * 适用: 保存失败返回json格式提示信息
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static JSONObject renderErrorInfo (String msg) {
		JSONObject json = new JSONObject();
		json.put(Constants.EXT_JSON_SUCCESS_STR, false);
        json.put(Constants.EXT_JSON_MSG, msg);
		return json;
	}

	/**
	 * 
	 * renderSuccessJson:
	 * 适用: 保存成功返回json格式提示信息
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static JSONObject renderSuccessInfo (String msg) {
		JSONObject json = new JSONObject();
		json.put(Constants.EXT_JSON_SUCCESS_STR, true);
        json.put(Constants.EXT_JSON_MSG, msg);
		return json;
	}
	
	
	public static JSONObject renderSuccessInfo(Object obj) {
		JSONObject json = new JSONObject();
		json.put(Constants.EXT_JSON_SUCCESS_STR, true);
        json.put(Constants.EXT_JSON_MSG, "操作成功");
        json.put(Constants.EXT_JSON_RESULTS_STR, obj);
		return json;
	}
	
	/**
	 * 
	 * @param o
	 * @return 把对象变成json字符串
	 * @throws Exception
	 */
	public static String rendToJsonStr(Object o) throws Exception{
		return mapper.writeValueAsString(o);
	}
	/**
	 * 
	 * @param jsonStr
	 * @return 把json字符串变成json对象
	 * @throws Exception
	 */
	public static Object rendStrToJson(String jsonStr) throws Exception{
		return mapper.readValue(jsonStr, new TypeReference<Object>() {});
	}
	
	/**
	 * 
	 * getRequestUrl:
	 * 适用:获取请求url
	 * @param jsonStr
	 * @return
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getRequestUrl(){
		HttpServletRequest request = getRequest();
		return request.getRequestURI()+"?"+request.getQueryString();
	}
}
