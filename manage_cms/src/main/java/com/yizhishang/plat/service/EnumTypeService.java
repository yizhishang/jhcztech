package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2007-6-30
 * 创建时间: 18:20:07
 */
@Service
public class EnumTypeService extends BaseService
{
	
	/** 
	 * @see com.yizhishang.plat.service.EnumTypeService#addItem(com.yizhishang.base.jdbc.DynaModel)
	 * 描述：增加枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:56:39 AM
	 * @param data
	 */
	public void addItem(DynaModel data)
	{
		getJdbcTemplateUtil().insert("T_ENUM_TYPE", data);
	}
	
	/**
	 * 描述：根据类型名删除具体的枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:46:05 AM
	 * @param enum_value
	 */
	public void delItemByName(String enum_name)
	{
		getJdbcTemplateUtil().delete("T_ENUM_TYPE", "ENUM_NAME", enum_name);
	}
	
	/**
	 * 描述：根据类型值删除具体的枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:46:05 AM
	 * @param enum_value
	 */
	public void delItemByVal(String enumValue, String siteno)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "DELETE FROM T_ENUM_TYPE WHERE ENUM_VALUE=? AND SITENO = ?";
		argList.add(enumValue);
		argList.add(siteno);
		getJdbcTemplateUtil().update(sql, argList.toArray());
	}
	
	/**
	 * 描述：根据类型名查找枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:59:35 AM
	 * @param enum_name
	 * @return
	 */
	public DynaModel findItemByName(String enum_name)
	{
		String sql = "select * from T_ENUM_TYPE where enum_name=?";
		return getJdbcTemplateUtil().queryMap(sql, new Object[] { enum_name });
	}
	
	/**
	 * 描述：根据类型值查找枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:59:29 AM
	 * @param enum_value
	 * @return
	 */
	public DynaModel findItemByVal(String enum_value)
	{
		String sql = "select * from T_ENUM_TYPE where enum_value=?";
		return getJdbcTemplateUtil().queryMap(sql, new Object[] { enum_value });
	}
	
	/**
	 * 获得系统枚举类型的分类
	 * @return
	 */
    public List<DynaModel> getEnumTypeList()
	{
		String sql = "select * from T_ENUM_TYPE";
		return getJdbcTemplateUtil().queryForList(sql);
	}
	
	/**
	 * 根据枚举类型得到具体类型下的枚举值
	 *
	 * @param curPage    当前页
	 * @param numPerPage 每页多少条记录
	 * @param enum_value       枚举类型
	 * @return
	 */
	public DBPage getEnumTypePage(int curPage, int numPerPage, String enum_value)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM T_ENUM_TYPE");
		ArrayList<Object> argList = new ArrayList<Object>();
		if (!StringHelper.isEmpty(enum_value))
		{
			sqlBuffer.append(" WHERE ENUM_VALUE=?");
			argList.add(enum_value);
		}
		sqlBuffer.append(" ORDER BY ENUM_NAME");
		return getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
	}
	
	/** 
	 * @see com.yizhishang.plat.service.EnumTypeService#isExitDictItem(java.lang.String, java.lang.String)
	 * 描述：判断字典类型是否存在
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:47:52 AM
	 * @param enum_name
	 * @param enum_value
	 * @param siteno
	 * @return
	 */
	public boolean isExitDictItem(String enum_name, String enum_value, String siteno)
	{
		boolean isExit = true;
		ArrayList<Object> list = new ArrayList<Object>();
		String sql = "SELECT COUNT(*) COUNT FROM T_ENUM_TYPE T WHERE T.ENUM_NAME=? AND T.ENUM_VALUE=? AND T.SITENO=? ";
		list.add(enum_name);
		list.add(enum_value);
		list.add(siteno);
		int num = getJdbcTemplateUtil().queryInt(sql, list.toArray());
		if (num <= 0)
			isExit = false;
		return isExit;
	}
	
	/** 
	 * @see com.yizhishang.plat.service.EnumTypeService#updateItem(com.yizhishang.base.jdbc.DynaModel)
	 * 描述：更新具体的枚举类型
	 * 作者：袁永君  lijian@yizhishang.com
	 * 时间：May 18, 2010 10:52:45 AM
	 * @param data
	 */
	public void updateItem(DynaModel data)
	{
		String enumValue = data.getString("enum_value");
		data.remove(enumValue);
		getJdbcTemplateUtil().update("T_ENUM_TYPE", data, "ENUM_VALUE", enumValue);
	}
	
}
