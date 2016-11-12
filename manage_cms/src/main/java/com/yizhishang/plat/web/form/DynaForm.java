package com.yizhishang.plat.web.form;

import java.util.HashMap;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-8
 * 创建时间: 21:05:08
 */
public class DynaForm extends HashMap<String, Object>
{
    
    private static final long serialVersionUID = 1L;

    /**
     * 返回boolean值
     * @param name 键值名称
     * @return 若不存在，或转换失败，则返回FALSE
     */
    public boolean getBoolean(String name)
    {
        if (name == null || name.equals(""))
            return false;

        boolean value = false;
        if (containsKey(name) == false)
            return false;

        Object obj = get(name);
        if (obj == null)
            return false;

        if (!(obj instanceof Boolean))
        {
            try
            {
                value = Boolean.valueOf(obj.toString()).booleanValue();
            }
            catch (Exception ex)
            {
                value = false;
            }
        }
        else
        {
            value = ((Boolean) obj).booleanValue();
            obj = null;
        }

        return value;
    }

    /**
     * 返回整型值
     *
     * @param name 键值名称
     * @return 若不存在，或转换失败，则返回0
     */
    public int getInt(String name)
    {
        if (name == null || name.equals(""))
            return 0;

        int value = 0;
        if (containsKey(name) == false)
            return 0;

        Object obj = get(name);
        if (obj == null)
            return 0;

        if (!(obj instanceof Integer))
        {
            try
            {
                value = Integer.parseInt(obj.toString());
            }
            catch (Exception ex)
            {
                value = 0;
            }
        }
        else
        {
            value = ((Integer) obj).intValue();
            obj = null;
        }

        return value;
    }

    /**
         * 返回长整型值
         *
         * @param name 键值名称
         * @return 若不存在，或转换失败，则返回0
         */
        public long getLong(String name)
        {
            if (name == null || name.equals(""))
                return 0;
    
            long value = 0;
            if (containsKey(name) == false)
                return 0;
    
            Object obj = get(name);
            if (obj == null)
                return 0;
    
            if (!(obj instanceof Long))
            {
                try
                {
                    value = Long.parseLong(obj.toString());
                }
                catch (Exception ex)
                {
                    value = 0;
                }
            }
            else
            {
                value = ((Long) obj).longValue();
                obj = null;
            }
    
            return value;
        }

    /**
     * 获得对象
     *
     * @param name 键值名称
     * @return 返回对象值，若不存在，则返回null
     */
    public Object getObject(String name)
    {
        if (name == null || name.equals(""))
            return null;
        //如果该键不存在，则返回
        if (containsKey(name) == false)
            return null;
        return get(name);
    }


    /**
     * 根据给定的名字返回一个字符串数组,若具体的值是string数组，则直接返回
     * 若是其它的对象，则把该对象转换为字串，然后返回只有一个元素的string数组
     *
     * @param name
     * @return
     */
    public String[] getStrArray(String name)
    {
        Object value = getObject(name);
        if (value instanceof String[])
        {
            return (String[]) value;
        }
        else
        {
            String[] strArray = new String[1];
            strArray[0] = value.toString();
            return strArray;
        }
    }

    /**
     * 获得字串值
     *
     * @param name 键值名称
     * @return 若不存在，则返回空字串
     */
    public String getString(String name)
    {
        if (name == null || name.equals(""))
            return "";

        String value = "";
        if (containsKey(name) == false)
            return "";
        Object obj = get(name);
        if (obj != null)
            value = obj.toString();
        obj = null;

        return value;
    }

    /**
     * 向Map中添加bool值
     *
     * @param name  键值名称
     * @param value Bool值
     */
    public void set(String name, boolean value)
    {
        put(name, new Boolean(value));
    }


    /**
     * 向Map中添加整数
     *
     * @param name  键值名称
     * @param value 整型值
     */
    public void set(String name, int value)
    {
        put(name, new Integer(value));
    }

    /**
    * 向Map中添加长整型
    *
    * @param name  键值名称
    * @param value 整型值
    */
public void set(String name, long value)
{
    put(name, new Long(value));
}

    /**
     * 向Map中添加Object值
     *
     * @param name
     * @param value
     */
    public void set(String name, Object value)
    {
        put(name, value);
    }

    /**
     * 向Map中添加字串
     *
     * @param name  键值名称
     * @param value 具体的值
     */
    public void set(String name, String value)
    {
        if (name == null || name.equals(""))
            return;

        if (value == null)
            put(name, "");
        else
            put(name, value);
    }
    
    public void putAll(DynaModel map){
    	putAll(map.toMap());
    }
}
