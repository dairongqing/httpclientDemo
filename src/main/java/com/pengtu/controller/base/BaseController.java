package com.pengtu.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder, WebDataBinder dataBinder) {
		    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));    
	        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));    
	        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));  
		    binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));    
	}
	
	@ModelAttribute
	public void safeFilter(String menuid, Model model) {
		if(StringUtils.isNotBlank(menuid)) {
			model.addAttribute("menuid", menuid);
		}
	}


	/**
	 * 得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
}
