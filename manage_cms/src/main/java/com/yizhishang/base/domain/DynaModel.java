package com.yizhishang.base.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-18
 * 创建时间: 13:53:48
 */
public class DynaModel
{

    private final HashMap<String, Object> innerMap = new HashMap<String, Object>();

    /**
     * 从Map中初始化动态Domain
     *
     * @param map
     */
    public DynaModel fromMap(DynaModel map)
    {
        innerMap.putAll(map.toMap());
        return this;
    }

    public boolean getBoolean(String name)
    {
        if (name == null || name.equals(""))
            return false;

        boolean value = false;
        if (innerMap.containsKey(name) == false)
            return false;
        Object obj = innerMap.get(name);
        if (obj == null)
            return false;

        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }

        value = Boolean.valueOf(obj.toString()).booleanValue();
        obj = null;
        return value;
    }

    public double getDouble(String name)
    {
        if (name == null || name.equals(""))
            return 0;

        double value = 0;
        if (innerMap.containsKey(name) == false)
            return 0;

        Object obj = innerMap.get(name);
        if (obj == null)
            return 0;

        if (!(obj instanceof Double)) {
            try {
                value = Double.parseDouble(obj.toString());
            } catch (Exception ex) {
                value = 0;
            }
        } else {
            value = ((Double) obj).doubleValue();
            obj = null;
        }

        return value;
    }

    public float getFloat(String name)
    {
        if (name == null || name.equals(""))
            return 0;

        float value = 0;
        if (innerMap.containsKey(name) == false)
            return 0;

        Object obj = innerMap.get(name);
        if (obj == null)
            return 0;

        if (!(obj instanceof Float)) {
            try {
                value = Float.parseFloat(obj.toString());
            } catch (Exception ex) {
                value = 0;
            }
        } else {
            value = ((Float) obj).floatValue();
            obj = null;
        }

        return value;
    }

    public int getInt(String name)
    {
        if (name == null || name.equals(""))
            return 0;

        int value = 0;
        if (innerMap.containsKey(name) == false)
            return 0;

        Object obj = innerMap.get(name);
        if (obj == null)
            return 0;

        if (!(obj instanceof Integer)) {
            try {
                value = Integer.parseInt(obj.toString());
            } catch (Exception ex) {
                value = 0;
            }
        } else {
            value = ((Integer) obj).intValue();
            obj = null;
        }

        return value;
    }

    public long getLong(String name)
    {
        if (name == null || name.equals(""))
            return 0;

        long value = 0;
        if (innerMap.containsKey(name) == false)
            return 0;

        Object obj = innerMap.get(name);
        if (obj == null)
            return 0;

        if (!(obj instanceof Long)) {
            try {
                value = Long.parseLong(obj.toString());
            } catch (Exception ex) {
                value = 0;
            }
        } else {
            value = ((Long) obj).longValue();
            obj = null;
        }

        return value;
    }

    public Object getObject(String name)
    {
        if (name == null || name.equals(""))
            return null;
        //如果该键不存在，则返回
        if (innerMap.containsKey(name) == false)
            return null;
        return innerMap.get(name);
    }

    public String getString(String name)
    {
        if (name == null || name.equals(""))
            return "";

        String value = "";
        if (innerMap.containsKey(name) == false)
            return "";
        Object obj = innerMap.get(name);
        if (obj != null)
            value = obj.toString();
        obj = null;

        return value;
    }

    public void remove(String name)
    {
        innerMap.remove(name);
    }

    public void set(String name, boolean value)
    {
        innerMap.put(name, new Boolean(value));
    }

    public void set(String name, double value)
    {
        innerMap.put(name, new Double(value));
    }

    public void set(String name, float value)
    {
        innerMap.put(name, new Float(value));
    }

    public void set(String name, int value)
    {
        innerMap.put(name, new Integer(value));
    }

    public void set(String name, long value)
    {
        innerMap.put(name, new Long(value));
    }

    public void set(String name, Object value)
    {
        innerMap.put(name, value);
    }

    public void set(String name, String value)
    {
        if (name == null || name.equals(""))
            return;

        if (value == null)
            innerMap.put(name, "");
        else
            innerMap.put(name, value);
    }

    public boolean containsKey(Object key)
    {
        return innerMap.containsKey(key);
    }

    public Object get(Object key)
    {
        return innerMap.get(key);
    }

    public void put(String key, Object value)
    {
        innerMap.put(key, value);
    }

    public void putAll(Map<String, Object> map)
    {
        innerMap.putAll(map);
    }

    public int size()
    {
        return innerMap.size();
    }

    public void clear()
    {
        innerMap.clear();
    }

    /**
     * 把Domain模型转化为Map
     *
     * @return
     */
    public Map<String, Object> toMap()
    {
        return innerMap;
    }

    //    @Override
    //    public String toString(){
    //        return innerMap.toString();
    //    }
}