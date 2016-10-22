package com.jhcz.trade.framework.plugin.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.jhcz.trade.common.util.MathUtil;


/**
 * @author Administrator 反射工具
 */
public class ReflectHelper
{
    /**
     * 获取obj对象fieldName的Field
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getFieldByFieldName(Object obj, String fieldName)
    {
        if (obj == null || fieldName == null)
        {
            return null;
        }
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(fieldName);
            } catch (Exception e)
            {
            }
        }
        return null;
    }

    /**
     * 获取obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
    {
        Object value = null;
        try
        {
            Field field = getFieldByFieldName(obj, fieldName);
            if (field != null)
            {
                if (field.isAccessible())
                {
                    value = field.get(obj);
                } else
                {
                    field.setAccessible(true);
                    value = field.get(obj);
                    field.setAccessible(false);
                }
            }
        } catch (Exception e)
        {
        }
        return value;
    }

    /**
     * 获取obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getValueByFieldType(Object obj, Class<T> fieldType)
    {
        Object value = null;
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
        {
            try
            {
                Field[] fields = superClass.getDeclaredFields();
                for (Field f : fields)
                {
                    if (f.getType() == fieldType)
                    {
                        if (f.isAccessible())
                        {
                            value = f.get(obj);
                            break;
                        } else
                        {
                            f.setAccessible(true);
                            value = f.get(obj);
                            f.setAccessible(false);
                            break;
                        }
                    }
                }
                if (value != null)
                {
                    break;
                }
            } catch (Exception e)
            {
            }
        }
        return (T) value;
    }

    /**
     * 设置obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static boolean setValueByFieldName(Object obj, String fieldName,
            Object value)
    {
        try
        {
            // java.lang.Class.getDeclaredField()方法用法实例教程 -
            // 方法返回一个Field对象，它反映此Class对象所表示的类或接口的指定已声明字段。
            // 此方法返回这个类中的指定字段的Field对象
            Field field = obj.getClass().getDeclaredField(fieldName);
            /**
             * public void setAccessible(boolean flag) throws
             * SecurityException将此对象的 accessible 标志设置为指示的布尔值。值为 true
             * 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
             * 首先，如果存在安全管理器，则在 ReflectPermission("suppressAccessChecks") 权限下调用
             * checkPermission 方法。 如果 flag 为 true，并且不能更改此对象的可访问性（例如，如果此元素对象是
             * Class 类的 Constructor 对象），则会引发 SecurityException。 如果此对象是
             * java.lang.Class 类的 Constructor 对象，并且 flag 为 true，则会引发
             * SecurityException。 参数： flag - accessible 标志的新值 抛出：
             * SecurityException - 如果请求被拒绝。
             */
            if (field.isAccessible())
            {   
                // 获取此对象的 accessible 标志的值。
                // 将指定对象变量上此 Field 对象表示的字段设置为指定的新值
                field.set(obj, value);
            } else
            {
                field.setAccessible(true);
                field.set(obj, value);
                field.setAccessible(false);
            }
            return true;
        } catch (Exception e)
        {
        }
        return false;
    }
    
    /**
     * getter:(根据属性名称调用get方法).
     * @author 林太平
     * @param obj Object
     * @param att String
     * @return String
     */
    public static String getter(Object obj, String att) {
        String valueString = "";
        try {
        	if (StringUtils.isNotEmpty(att)) {
        		Method method = obj.getClass().getMethod("get" + att.substring(0, 1).toUpperCase() 
        				+ att.substring(1));
        		Object result = method.invoke(obj);
        		if (result != null) {
        			valueString = result.toString().trim();
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueString;
    }
    /**
     * 把一个类中的所有的float类型的属性都执行四舍五入并保留一位小数的操作
     * @param obj
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @author tigerhoo
     */
    public static void resetFloatField(Object obj) throws IllegalArgumentException, IllegalAccessException {
    	Class<?> clz = obj.getClass();
    	Field[] fields = clz.getDeclaredFields();
    	for (Field field : fields) {
    		//如果该属性是float类型
			if(field.getGenericType().toString().equals("float"))
			{
				float value=0f;
				boolean flag = false;
				if (!field.isAccessible()) {
					field.setAccessible(true);
					flag = true;
				}
				value = field.getFloat(obj);
				field.setFloat(obj, MathUtil.round_1(value));
				if(flag)
				{
					field.setAccessible(false);
				}
			}
		}
    }
}
