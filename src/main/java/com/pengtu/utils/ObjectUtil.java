package com.pengtu.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2013-4-27 上午10:51:14
 * @since 1.0
 */
public class ObjectUtil
{
    /**
     * <p>
     * 把原对象的属性值拷贝到目标对象，并返回目标对象.<b>拷贝空值</b>.
     * </p>
     * 
     * @param source 数据来源对象
     * @param target 目标对象
     * @return 目标对象
     */
    public static <T> T copyPropVal(Object source, T target)
    {
        return copyPropVal(source, target, true);
    }

    /**
     * 把原对象的属性值拷贝到目标对象，并返回目标对象；不处理复合属性；<b>不拷贝空值</b>
     * 
     * @param <T>
     * @param source
     * @param target
     * @return 目标对象
     */
    public static <T> T copyNotNullPropVal(Object source, T target)
    {
        return copyPropVal(source, target, false);
    }

    private static Object callGetter(Object o, List<Method> getterList)
    {
        if (getterList == null || o == null)
            return null;

        for (Method m : getterList)
        {
            if (!m.getReturnType().equals(void.class) && m.getParameterTypes().length == 0)
            {
                try
                {
                    return m.invoke(o);
                } catch (Exception e)
                {
                }
            }
        }
        return null;
    }

    private static void callSetter(Object o, Object val, List<Method> setterList)
    {
        if (setterList == null || o == null)
            return;

        for (Method m : setterList)
        {
            if (m.getReturnType().equals(void.class) && m.getParameterTypes().length == 1)
            {
                try
                {
                    m.invoke(o, val);
                    return;
                } catch (Exception e)
                {
                }
            }
        }
    }

    /**
     * 见prepareToSave，空值默认<b>不</b>覆盖
     * 
     * @param savingEntity 要保存到数据库的实体对象
     * @param valueEntity 数值实体
     * @param userId 当前用户ID
     * @return 处理以后的参数savingEntity
     */
    public static <T> T prepareToSave(T savingEntity, Object valueEntity, String userId)
    {
        return prepareToSave(savingEntity, valueEntity, userId, false);
    }

    /**
     * 在保存savingEntity实体之前，完成对savingEntity字段值的设置操作，具体如下：<br>
     * 1)设置savingEntity的通用字段getDisabled(isDisabled), getCreateTime,
     * getCreatedBy,setCreateTime,setCreateBy,setUpdateTime,setUpdateBy 2)拷贝
     * 数值实体valueEntity 所有属性值到savingEntity相应属性<br>
     * 本方法返回待保存的实体
     * 
     * @param <T>
     * @param savingEntity 要保存到数据库的实体对象
     * @param valueEntity 数值实体
     * @param userId 当前用户ID
     * @param copyNull 是否拷贝空值
     * @return 处理以后的参数savingEntity
     */
    public static <T> T prepareToSave(T savingEntity, Object valueEntity, String userId, boolean copyNull)
    {
        if (savingEntity == null)
            return savingEntity;

        HashMap<String, List<Method>> methodMap = new HashMap<String, List<Method>>();
        for (Method m : savingEntity.getClass().getMethods())
        {
            List<Method> list = methodMap.get(m.getName());
            if (list == null)
            {
                list = new ArrayList<Method>();
                methodMap.put(m.getName(), list);
            }
            list.add(m);
        }

        Object createTime = callGetter(savingEntity, methodMap.get("getCreateTime"));
        Object createBy = callGetter(savingEntity, methodMap.get("getCreateBy"));

        copyPropVal(valueEntity, savingEntity, copyNull);

        Date now = new Date();
        if (createTime == null)
            createTime = now;
        if (createBy == null)
            createBy = userId;

        // ~~exam or ERP~~
        Object creationDate = callGetter(savingEntity, methodMap.get("getCreationDate"));
        Object createdBy = callGetter(savingEntity, methodMap.get("getCreatedBy"));
        if (createdBy == null)
            createdBy = userId;
        if (creationDate == null)
            creationDate = now;
        // ~~exam or ERP~~

        Object disabled = callGetter(savingEntity, methodMap.get("getDisabled"));
        if (disabled == null)
            disabled = callGetter(savingEntity, methodMap.get("isDisabled"));
        if (disabled == null)
            callSetter(savingEntity, false, methodMap.get("setDisabled"));

        callSetter(savingEntity, createBy, methodMap.get("setCreateBy"));
        callSetter(savingEntity, createTime, methodMap.get("setCreateTime"));
        callSetter(savingEntity, now, methodMap.get("setUpdateTime"));
        callSetter(savingEntity, userId, methodMap.get("setUpdateBy"));

        // ~~exam or ERP~~
        callSetter(savingEntity, createdBy, methodMap.get("setCreatedBy"));
        callSetter(savingEntity, creationDate, methodMap.get("setCreationDate"));
        callSetter(savingEntity, now, methodMap.get("setLastUpdateDate"));
        callSetter(savingEntity, userId, methodMap.get("setLastUpdatedBy"));
        callSetter(savingEntity, userId, methodMap.get("setLastUpdateLogin"));
        // ~~exam or ERP~~
        return savingEntity;
    }

    private static boolean isZteClass(Type type)
    {
        if (!(type instanceof Class))
            return false;
        return false;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Map<?, ?> makeTargetMap(Map<?, ?> source, Type keyType, Type valType, boolean copyNull)
            throws Exception
    {
        if (!(keyType instanceof Class))
            throw new UnsupportedOperationException("makeTargetMap " + keyType);

        Class<?> keyClzz = (Class<?>) keyType;

        Map result = new HashMap();
        for (Object k : source.keySet())
        {
            Object srcVal = source.get(k);
            Object value = srcVal;
            Object key = k;
            if (isZteClass(keyClzz))
                key = copyNotNullPropVal(k, keyClzz.newInstance());

            if (isZteClass(valType))
            {
                value = copyPropVal(srcVal, ((Class<?>) valType).newInstance(), copyNull);
            } else if (checkCopyAsList(srcVal, valType))
            {
                Type actualType = ((ParameterizedType) valType).getActualTypeArguments()[0];
                value = makeTargetList((List<?>) srcVal, (Class<?>) actualType, copyNull);
            } else if (checkCopyAsMap(srcVal, valType))
            {
                ParameterizedType prmType = (ParameterizedType) valType;
                Type subKeyType = prmType.getActualTypeArguments()[0];
                Type subValType = prmType.getActualTypeArguments()[1];
                value = makeTargetMap((Map<?, ?>) srcVal, subKeyType, subValType, copyNull);
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把原对象的属性值拷贝到目标对象，并返回目标对象；不处理复合属性，可控制是否拷贝空值
     * 
     * @param <T>
     * @param source
     * @param target
     * @param copyNull 是否拷贝空值
     * @return 目标对象
     */
    public static <T> T copyPropVal(Object source, T target, boolean copyNull)
    {
        if (source == null || target == null)
            return target;

        Map<String, Method> getterMap = new HashMap<String, Method>();
        for (Method m : source.getClass().getMethods())
        {
            if (m.getParameterTypes().length > 0)
                continue;

            String name = m.getName();
            if (name.startsWith("get") && name.length() > 3)
            {
                name = name.substring(3);
                getterMap.put(name, m);
            } else if (name.startsWith("is") && name.length() > 2 && m.getReturnType() == boolean.class)
            {
                name = name.substring(2);
                getterMap.put(name, m);
            }

        }

        for (Method setter : target.getClass().getMethods())
        {
            String name = setter.getName();
            Type[] paramTypes = setter.getGenericParameterTypes();
            if (name.startsWith("set") && name.length() > 3 && paramTypes.length == 1)
            {
                name = name.substring(3);
                Method getter = getterMap.get(name);
                if (getter != null)
                {
                    try
                    {
                        Object value = getter.invoke(source);
                        if (value != null)
                        {
                            Type paramType = paramTypes[0];
                            if (isZteClass(paramType))
                            {
                                try
                                {
                                    value = copyPropVal(value, ((Class<?>) paramType).newInstance(), copyNull);
                                } catch (InstantiationException e)
                                {
                                }
                            } else if (checkCopyAsList(value, paramType))
                            {
                                Type actualType = ((ParameterizedType) paramType).getActualTypeArguments()[0];
                                value = makeTargetList((List<?>) value, (Class<?>) actualType, copyNull);
                            } else if (checkCopyAsMap(value, paramType))
                            {
                                Type keyType = ((ParameterizedType) paramType).getActualTypeArguments()[0];
                                Type valType = ((ParameterizedType) paramType).getActualTypeArguments()[1];
                                try
                                {
                                    value = makeTargetMap((Map<?, ?>) value, keyType, valType, copyNull);
                                } catch (Exception e)
                                {
                                    value = null;
                                }
                            }

                            setter.invoke(target, value);
                        } else if (copyNull)
                        {
                            setter.invoke(target, value);
                        }
                    } catch (IllegalArgumentException e)
                    {
                        // do nothing
                    } catch (IllegalAccessException e)
                    {
                        // do nothing
                    } catch (InvocationTargetException e)
                    {
                        // do nothing
                    }
                }

            }
        }

        return target;
    }

    public static <T> T copyAs(Object srcBean, Class<T> targetClass)
    {
        if (srcBean == null)
        {
            return null;
        }

        T ret;
        try
        {
            ret = targetClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return copyPropVal(srcBean, ret);
    }

    /**
     * 判断Value是否是List类型，type是泛型List，从而他们可以作为List进行bean Copy
     * 
     * @param value
     * @param type
     * @return
     */
    private static boolean checkCopyAsList(Object value, Type type)
    {
        if (!(value instanceof List) || !(type instanceof ParameterizedType))
            return false;

        ParameterizedType paramType = (ParameterizedType) type;
        if (!(paramType.getRawType() instanceof Class))
            return false;

        Class<?> rawType = (Class<?>) paramType.getRawType();
        if (!List.class.isAssignableFrom(rawType) || paramType.getActualTypeArguments().length != 1)
            return false;

        return true;
    }

    /**
     * 判断Value是否是Map类型，type是泛型Map，从而他们可以作为Map进行bean Copy
     * 
     * @param value
     * @param type
     * @return
     */
    private static boolean checkCopyAsMap(Object value, Type type)
    {
        if (!(value instanceof Map) || !(type instanceof ParameterizedType))
            return false;

        ParameterizedType paramType = (ParameterizedType) type;
        if (!(paramType.getRawType() instanceof Class))
            return false;

        Class<?> rawType = (Class<?>) paramType.getRawType();
        if (!Map.class.isAssignableFrom(rawType) || paramType.getActualTypeArguments().length != 2)
            return false;

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> makeTargetList(List<?> sourceList, Class<T> targetClzz, boolean copyNull)
    {
        if (sourceList == null || targetClzz == null)
            return null;

        List<T> ret = new ArrayList<T>();
        for (Object source : sourceList)
        {
            if (isZteClass(targetClzz))
            {
                try
                {
                    T target = targetClzz.newInstance();
                    ret.add(copyPropVal(source, target, copyNull));
                } catch (Exception e)
                {
                    // do nothing
                }
            } else if (targetClzz.isInstance(source))
            {
                ret.add((T) source);
            }
        }
        return ret;
    }

    public static <T> List<T> makeTargetList(List<?> sourceList, Class<T> targetClzz)
    {
        return makeTargetList(sourceList, targetClzz, true);
    }

    public static Object newInstance(Type type, Type parent) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        if (type.equals(parent))
        {
            return null;
        }
        if (type.toString().startsWith(List.class.getName()))
        {
            ArrayList<Object> list = new ArrayList<Object>();
            if (type instanceof ParameterizedType)
            {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 泛型类型列表
                Type actualType = types[0];
                if (!actualType.equals(parent))
                {
                    Object element1 = newInstance(actualType, null);
                    list.add(element1);

                    Object element2 = newInstance(actualType, null);
                    list.add(element2);
                }

            }
            return list;
        }else if (type.toString().startsWith(Map.class.getName()))
        {
            Map<Object,Object> map = new HashMap<Object,Object>();
            if (type instanceof ParameterizedType)
            {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 泛型类型列表
                Type keyType = types[0];
                Type valType = types[1];
                Object keyObj = newInstance(keyType, null);
                if (!valType.equals(parent))
                {
                    Object valObj = newInstance(valType, null);
                    map.put(keyObj, valObj);
                } else
                {
                    map.put(keyObj, null);
                }

            }
            return map;
        } else
        {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isPrimitive())
            {// 基础类型
                if (clazz.getName().equals("boolean"))
                {
                    return new Boolean(true);
                } else if (clazz.getName().equals("char"))
                {
                    return new Character('c');
                } else if (clazz.getName().equals("byte"))
                {
                    return new Byte((byte) 0);
                } else if (clazz.getName().equals("short"))
                {
                    return new Short((short) 0);
                } else if (clazz.getName().equals("int"))
                {
                    return new Integer(0);
                } else if (clazz.getName().equals("long"))
                {
                    return new Long(0);
                } else if (clazz.getName().equals("float"))
                {
                    return new Float(0);
                } else if (clazz.getName().equals("double"))
                {
                    return new Double(0);
                }
                return null;
            } else
            {

                if (clazz.getName().equals(Boolean.class.getName()))
                {
                    return new Boolean(true);
                } else if (clazz.getName().equals(Character.class.getName()))
                {
                    return new Character('c');
                } else if (clazz.getName().equals(Byte.class.getName()))
                {
                    return new Byte((byte) 0);
                } else if (clazz.getName().equals(Short.class.getName()))
                {
                    return new Short((short) 0);
                } else if (clazz.getName().equals(Integer.class.getName()))
                {
                    return new Integer(0);
                } else if (clazz.getName().equals(Long.class.getName()))
                {
                    return new Long(0);
                } else if (clazz.getName().equals(Float.class.getName()))
                {
                    return new Float(0);
                } else if (clazz.getName().equals(Double.class.getName()))
                {
                    return new Double(0);
                } else if (clazz.getName().equals(String.class.getName()))
                {
                    return "string";
                } else if (clazz.getName().equals(Date.class.getName()))
                {
                    return new Date();
                } 

                Constructor<?>[] constructors = clazz.getConstructors();
                Constructor<?> constructor = constructors.length > 0 ? constructors[0] : null;
                if (constructor == null)
                {
                    return null;
                }
                Type[] paramTypes = constructor.getGenericParameterTypes();
                if (paramTypes.length > 0)
                {
                    Object[] values = new Object[paramTypes.length];
                    for (int i = 0; i < paramTypes.length; i++)
                    {
                        values[i] = newInstance(paramTypes[i], null);
                    }
                    return constructor.newInstance(values);
                } else
                {
                    // 默认构造函数，需要对所有属性赋值
                    Object returnObject = constructor.newInstance();
                    if (isZteClass(type))
                    {
                        Field[] fields = clazz.getDeclaredFields();
                        for (int i = 0; i < fields.length; i++)
                        {
                            if ((fields[i].getModifiers() & Modifier.STATIC) == 0
                                    && (fields[i].getModifiers() & Modifier.FINAL) == 0)
                            {
                                fields[i].setAccessible(true);
                                Object fieldObject = newInstance(fields[i].getGenericType(), type);
                                fields[i].set(returnObject, fieldObject);
                            }
                        }
                    }
                    return returnObject;
                }

            }
        }

    }
    
    public static boolean hasText(String textValue)
    {
        return textValue != null && !"".equals(textValue);
    }
    
    public static boolean hasNumeric(Integer numeric)
    {
        return numeric != null;
    }
}
