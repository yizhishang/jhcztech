package com.jhcz.plat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.service.CustomFieldService;
import com.jhcz.plat.service.impl.CustomFieldServiceImpl;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-05-17
 * 创建时间: 14:24:44
 */
public class ExtendFieldManage
{
	
	
    private static Set<Object> fieldData = new TreeSet<Object>();
	
	
	private static ExtendFieldManage fieldManage = new ExtendFieldManage();
	
	static
	{
		initFieldDataMap();
	}
	
	
	public static ExtendFieldManage getInstance()
	{
		return fieldManage;
	}
	
	
	private static void initFieldDataMap()
	{
		try
		{
            Set<Object> fieldSet = new HashSet<Object>();
            //查询已经被使用的字段
			CustomFieldService fieldService = new CustomFieldServiceImpl();
            List<Object> fieldList = fieldService.findExtendFieldInfo(0);
            for (Iterator<Object> iter = fieldList.iterator(); iter.hasNext();)
			{
                DataRow data = (DataRow) iter.next();
				fieldSet.add(new Integer(data.getInt("id")));
			}
			
			Integer field = new Integer(0);
			for (int i = 0; i < 50; i++)
			{
				field = new Integer(i);
				if (fieldSet.contains(field))
				{
					continue;
				}
				fieldData.add(field);
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	
	
	private ExtendFieldManage()
	{
		
	}
	
	
	public void addField(Integer field)
	{
		fieldData.add(field);
	}
	
	
    public Set<Object> getFieldData()
	{
		return fieldData;
	}
	
	
	public void removeField(Integer field)
	{
		fieldData.remove(field);
	}
}
