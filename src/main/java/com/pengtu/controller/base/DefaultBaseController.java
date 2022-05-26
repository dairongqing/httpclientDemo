package com.pengtu.controller.base;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class DefaultBaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DefaultDateEditor());
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
	}

	/**
	 * 自定义DateEditor，绑定多个日期格式
	 * 
	 * @author Administrator
	 *
	 */
	private class DefaultDateEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (StringUtils.isEmpty(text)) {
				setValue(null);
				return;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = format.parse(text);
			} catch (ParseException e) {
				format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = format.parse(text);
				} catch (ParseException ex) {
					throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
				}
			}
			setValue(date);
		}
	}

}
