/**
 * <p>Copyright (c) 2017 深圳市鹏途交通科技有限公司 </p>
 * <p>				   All right reserved. 		     </p>
 * 
 * <p>项目名称 ：深圳市养护管理系统 	         </p>
 * <p>创建者   :	ce 
 * 
 * <p>描   述  :   UrlUtils.java for com.pengtu.utils.web    </p>
 * 
 * <p>最后修改 : $: 2017年10月5日-下午10:14:31 v 1.0.0	 ce   $ </p>
 * 
*/

package com.pengtu.utils.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.codec.EncodingException;
//import com.pengtu.utils.ConfigUtils;

/**
 * 
 * UrlUtils
 * 
 * 2017年10月5日 下午10:14:31
 * 
 * @version 1.0.0
 * 
 */
public class UrlUtils {
	private static Log log = LogFactory.getLog(UrlUtils.class);
    private UrlUtils() {
        
    }
	
    /**
     * Convenience method to get the application's URL based on request
     * variables.
     */
    public static String getAppURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
    	int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    /**
     * 用于menu配置地址
     * mixMenuUrl:
     * 适用:
     * @param baseUrl
     * @param menuid
     * @return 
     * @exception 
     * @since  1.0.0
     */
    public static String mixMenuUrl(String baseUrl, String menuid ){
    	StringBuffer urlBuff = new StringBuffer("");
    	if(StringUtils.isEmpty(baseUrl)) {
    		return "/";
    	}
    	urlBuff.append(baseUrl);
    	if(StringUtils.isNotEmpty(menuid)){
    		if(baseUrl.contains("?")){
    			urlBuff.append("&");
    		} else {
    			urlBuff.append("?");
    		}
    		urlBuff.append("menuid=").append(menuid);
    	}
    		
    	return urlBuff.toString();
    }
    
    /**
     * 用于menu配置地址
     * mixMenuUrl:
     * 适用:
     * @param baseUrl
     * @param menuid
     * @return 
     * @throws EncodingException 
     * @exception 
     * @since  1.0.0
     */
//    public static String mixBusinessUrl(String baseUrl, String busid ,String idName ){
//    	StringBuffer urlBuff = new StringBuffer("");
//    	if(StringUtils.isEmpty(baseUrl)) {
//    		return "/";
//    	}
//    	urlBuff.append(baseUrl);
//    	if(StringUtils.isNotEmpty(busid)){
//    		if(baseUrl.contains("?")){
//    			if(baseUrl.indexOf("id=") == -1){
//    				urlBuff.append("&");
//    			}
//    		} else {
//    			urlBuff.append("?");
//    		}
//    		if(StringUtils.isNotEmpty(idName) && baseUrl.indexOf("id=") == -1){
//    			urlBuff.append(idName).append("=");
//    		} else {
//    			if(baseUrl.indexOf("id=") == -1){
//    				urlBuff.append("id").append("=");
//    			}
//    		}
//    		try {
//				urlBuff.append(ConfigUtils.encodeParamUrl(busid));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//    	}
//    		
//    	return urlBuff.toString();
//    }

    
    /**
     * 
     * isMenuShow:
     * 适用:仅适用于首页菜单显示
     * @param cmenuid 
     * @param pmenuid
     * @param isshow 显示可见
     * @param tag  菜单及待办类菜单可见
     * @return 
     * @exception 
     * @since  1.0.0
     */
    public static boolean isMenuShow(String cmenuid, String pmenuid ,String isshow,String tag ){
    	// 不是可见的菜单不可见
    	if(!"1".equals(isshow)){
    		return false;
    	}
    	// 不是菜单及待办菜单的不可见
    	if(!("1".equals(tag)||"2".equals(tag))){
    		return false;
    	}
    	// 菜单
    	if(!StringUtils.equals(cmenuid, pmenuid)) {
    		return false;
    		
    	}
    	return true;
    }

    
    /**
     * Decodes using URLDecoder - use when queries or form post values are decoded
     * @param value value to decode
     * @param enc encoding
     * @return
     */
    public static String urlDecode(String value, String enc) {
        try {
            value = URLDecoder.decode(value, enc);
        } catch (UnsupportedEncodingException e) {
        	log.warn("UTF-8 encoding can not be used to decode " + value);          
        }
        return value;
    }
    
    public static String urlDecode(String value) {
        return urlDecode(value, "UTF-8");
    }
}
