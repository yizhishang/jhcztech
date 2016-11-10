package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.jdbc.JdbcTemplate;
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
public class EnumService extends BaseService
{
	
	    /**
     * 
     * 描述:  具体的枚举值  
     * 作者:	 
     * 创建日期: 2010-1-5
     * 创建时间: 下午12:53:36
     * @return void
     * @throws
     */
	public void addItem(DataRow data)
	{
		String itemcode = getSeqValue("T_ENUM_VALUE");
		data.set("item_code", itemcode);
		data.set("orderline", itemcode);
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.insert("T_ENUM_VALUE", data);
	}
	
	    /**
     * 
     * 描述:  删除具体的枚举值
     * 作者:	 
     * 创建日期: 2010-1-5
     * 创建时间: 下午12:55:33
     * @return void
     * @throws
     */
	public void delItem(int itemCode)
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.delete("T_ENUM_VALUE", "ITEM_CODE", new Integer(itemCode));
	}
	
	    /**
     * 
     * @描述：
     * @作者：袁永君
     * @时间：2011-3-26 下午09:02:57
     * @param type
     * @param siteno
     */
	public void delItemByType(String type, String siteno)
	{
		List<Object> argList = new ArrayList<Object>();
		//String sql = "DELETE FROM T_ENUM_VALUE WHERE ENUM_TYPE=? AND SITENO = ?";
		String sql = "DELETE FROM T_ENUM_VALUE WHERE ENUM_TYPE=?";
		argList.add(type);
		//argList.add(siteno);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		jdbcTemplate.update(sql, argList.toArray());
	}
	
	    /**
     * 
     * @描述：分页查询枚举类型
     * @作者：袁永君
     * @时间：2011-3-26 下午05:20:31
     * @param curPage
     * @param numPerPage
     * @param siteNo
     * @return
     */
	public DBPage findEnumItem(int curPage, int numPerPage, String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_ENUM_TYPE WHERE SITENO = ? ORDER BY ENUM_NAME";
		argList.add(siteNo);
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.queryPage(sql, argList.toArray(), curPage, numPerPage);
		
	}
	
	        /**
    * 根据编码查找具体的枚举值
    *
    * @param itemCode
    * @return
    */
	public DataRow findItemByCode(String itemCode)
	{
		String sql = "select * from T_ENUM_VALUE where ITEM_CODE=?";
		ArrayList<Object> argList = new ArrayList<Object>();
		argList.add(itemCode);
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.queryMap(sql, argList.toArray());
	}
	
	    /**
     * 根据枚举类型得到具体类型下的枚举值
     *
     * @param curPage    当前页
     * @param numPerPage 每页多少条记录
     * @param type       枚举类型
     * @return
     */
	public DBPage getEnumItemByType(int curPage, int numPerPage, String type, String siteno)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select A.*,B.ENUM_NAME,B.ENUM_VALUE from T_ENUM_VALUE A,T_ENUM_TYPE B where A.ENUM_TYPE = B.ENUM_VALUE ");
		ArrayList<Object> argList = new ArrayList<Object>();
		if (!StringHelper.isEmpty(type))
		{
			sqlBuffer.append(" and A.enum_type=? ");
			argList.add(type);
		}
		if (StringHelper.isNotEmpty(siteno))
		{
			sqlBuffer.append(" AND A.SITENO = ?");
			argList.add(siteno);
		}
		sqlBuffer.append(" order by A.enum_type desc,A.orderline desc ");
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
	}
	
	    /** 
     *@作者:DAF
     *@描述: 
     *@param dictName
     *@return
     */
	public List<Object> getEnumListByType(String dictName)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(dictName);
		return getJdbcTemplate().query("SELECT * FROM T_ENUM_VALUE WHERE ENUM_TYPE = ? ORDER BY ORDERLINE", list.toArray());
	}
	
	    /**
     * 获得系统枚举类型的分类
     *
     * @return
     */
	public List<Object> getEnumTypeList(String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer("select * from T_ENUM_TYPE where 1 = 1");
		if (StringHelper.isNotEmpty(siteNo))
		{
			buffer.append(" and siteno = ? ");
			argList.add(siteNo);
			
		}
		buffer.append(" order by enum_name");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.query(buffer.toString(), argList.toArray());
	}
	
	public boolean isExitDictItem(String enum_type, String item_name)
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		boolean isExit = true;
		ArrayList<Object> list = new ArrayList<Object>();
		String sql = "select count(*) count from t_enum_value t where t.enum_type=? and t.item_name=? ";
		list.add(enum_type);
		list.add(item_name);
		int num = jdbcTemplate.queryInt(sql, list.toArray());
		if (num <= 0)
			isExit = false;
		return isExit;
	}
	
	    /**
     * 更新具体的枚举值
     * @param data
     */
	public void updateItem(DataRow data)
	{
		String itemCode = data.getString("item_code");
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.update("T_ENUM_VALUE", data, "ITEM_CODE", itemCode);
	}
	
}
