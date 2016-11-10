package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2007-6-30
 * 创建时间: 17:54:01
 */
public interface EnumService
{
    
    /**
    * 
    * 描述:  添加具体的枚举值
    * 作者:	 
    * 创建日期: 2010-1-5
    * 创建时间: 下午01:03:25
    * @return void
    * @throws
    */
    public void addItem(DataRow data);
    
    /**
    * 
    * 描述:  删除具体的枚举值
    * 作者:	 
    * 创建日期: 2010-1-5
    * 创建时间: 下午12:56:51
    * @return void
    * @throws
    */
    public void delItem(int itemCode);
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2011-3-26 下午09:02:57
    * @param type
    * @param siteno
    */
    public void delItemByType(String type, String siteno);
    
    /**
    * 
    * @描述：分页查询枚举类型
    * @作者：袁永君
    * @时间：2011-3-26 下午05:21:59
    * @param curPage
    * @param numPerPage
    * @param siteNo
    * @return
    */
    public DBPage findEnumItem(int curPage, int numPerPage, String siteNo);
    
    /**
    * 根据编码查找具体的枚举值
    *
    * @param itemCode
    * @return
    */
    public DataRow findItemByCode(String itemCode);
    
    /**
    * 根据枚举类型得到具体类型下的枚举值
    *
    * @param curPage    当前页
    * @param numPerPage 每页多少条记录
    * @param type       枚举类型
    * @return
    */
    public DBPage getEnumItemByType(int curPage, int numPerPage, String type, String siteno);
    
    /** 
    *@作者:DAF
    *@描述: 
    *@param dictName
    *@return
    */
    public List<Object> getEnumListByType(String dictName);
    
    /**
    * 获得系统枚举类型的分类
    * @return
    */
    public List<Object> getEnumTypeList(String siteNo);
    
    /**
    * 
    * 描述：判断字典中是否存在相同数据项
    * 作者：刘宝
    * 时间：Jan 20, 2010 2:59:49 PM
    * @param dictName
    * @param dictValue
    * @return
    */
    public boolean isExitDictItem(String enum_type, String item_name);
    
    /**
    * 更新具体的枚举值
    *
    * @param data
    */
    public void updateItem(DataRow data);

}
