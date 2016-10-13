package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2007-6-30
 * 创建时间: 17:54:01
 */
public interface EnumTypeService
{
    
    /** 
     * @see com.jhcz.plat.service.EnumTypeService#addItem(com.jhcz.base.jdbc.DataRow)
     * 描述：增加枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:56:39 AM
     * @param data
     */
    public void addItem(DataRow data);
    
    /**
     * 描述：根据类型名删除具体的枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:46:05 AM
     * @param enum_value
     */
    public void delItemByName(String enum_name);
    
    /**
     * 描述：根据类型值删除具体的枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:46:05 AM
     * @param enum_value
     */
    public void delItemByVal(String enum_value, String siteno);
    
    /**
     * 描述：根据类型名查找枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:59:35 AM
     * @param enum_name
     * @return
     */
    public DataRow findItemByName(String enum_name);
    
    /**
     * 描述：根据类型值查找枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:59:29 AM
     * @param enum_value
     * @return
     */
    public DataRow findItemByVal(String enum_value);
    
    /**
     * 描述：获得系统枚举类型的分类
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 11:09:02 AM
     * @return
     */
    public List<Object> getEnumTypeList();
    
    /**
     * 描述：获得系统枚举类型的分类
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 11:08:59 AM
     * @param curPage
     * @param numPerPage
     * @param enum_value
     * @return
     */
    public DBPage getEnumTypePage(int curPage, int numPerPage, String enum_value);
    
    /** 
     * @see com.jhcz.plat.service.EnumTypeService#isExitDictItem(java.lang.String, java.lang.String)
     * 描述：判断字典类型是否存在
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:47:52 AM
     * @param enum_name
     * @param enum_value
     * @return
     */
    public boolean isExitDictItem(String enum_name, String enum_value, String siteno);
    
    /** 
     * @see com.jhcz.plat.service.EnumTypeService#updateItem(com.jhcz.base.jdbc.DataRow)
     * 描述：更新具体的枚举类型
     * 作者：袁永君  lijian@jhcz.com
     * 时间：May 18, 2010 10:52:45 AM
     * @param data
     */
    public void updateItem(DataRow data);
}
