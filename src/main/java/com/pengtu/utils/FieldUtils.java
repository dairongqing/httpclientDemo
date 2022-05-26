/**
 * <p>Copyright (c) 2017 深圳市鹏途交通科技有限公司 </p>
 * <p>				   All right reserved. 		     </p>
 * 
 * <p>项目名称 ： 	szyh         </p>
 * <p>创建者   :	yanghong 
 * 
 * <p>描   述  :   FieldUtils.java for com.pengtu.utils    </p>
 * 
 * <p>最后修改 : $: 2017年12月7日-下午4:38:53 v 1.0.0	 yanghong   $ </p>
 * 
*/

package com.pengtu.utils;

import java.lang.reflect.Method;

/**
 * 
 * FieldUtils
 * 2017年12月7日 下午4:38:53
 * @author yanghong
 * @version 1.0.0
 * 
 */
public class FieldUtils {

	public static Object getFieldValueByName(String fieldName, Object o) {  
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(o, new Object[] {});    
            return value;    
        } catch (Exception e) {    
            return null;    
        }    
    }  

}
