package com.pengtu.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 
 * 
 * FreemarkerUtils
 * 2017年8月28日 上午9:01:57
 * @author yanghong
 * @version 1.0.0
 *
 */
public class FreemarkerUtils {

	/**
	 * 
	 * createDocByTemplate:
	 * 适用:根据xml模板生成doc
	 * @param context
	 * @param templatePath
	 * @param wordFileName
	 * @param dataModel 
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static void createDocByTemplate(ServletContext context, String templatePath, String wordFileName, Map<String, Object> dataModel) throws IOException{
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setEncoding(Locale.CHINA, "UTF-8");
		Writer out = null;
		try {
			configuration.setServletContextForTemplateLoading(context, "/resources/xml");
			Template template = configuration.getTemplate(templatePath);
			File file = new File(wordFileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			template.process(dataModel, out);
			out.flush();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (out!=null) {
				out.close();
			}
			
		}
		
	}
	
	/**
	 * 
	 * createDocByTemplate:
	 * 适用:根据xml模板生成doc
	 * @param context
	 * @param templatePath
	 * @param wordFileName
	 * @param dataModel 
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static void createDocByTemplatePath(String director, String templatePath, String wordFileName, Map<String, Object> dataModel) throws IOException{
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setEncoding(Locale.CHINA, "UTF-8");
		Writer out = null;
		try {
		    configuration.setDirectoryForTemplateLoading(new File(director));
			Template template = configuration.getTemplate(templatePath);
			File file = new File(wordFileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			template.process(dataModel, out);
			out.flush();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (out!=null) {
				out.close();
			}
			
		}
		
	}
}
