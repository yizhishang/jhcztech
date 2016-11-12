package com.yizhishang.business.other.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.business.domain.Ad_Group;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-28
 * 创建时间: 10:00:54
 */
@Service
public class AdGroupService extends BaseService
{
    
    public DBPage<Ad_Group> getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_ad_group where 1=1");
        if (StringHelper.isNotEmpty(siteNo))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteNo);
        }
        
        if (StringHelper.isNotEmpty(keyword))
        {
            sqlBuf.append(" and name like ?");
            argList.add("%" + keyword + "%");
        }
        sqlBuf.append(" order by orderline");
        DBPage<Ad_Group> page = getJdbcTemplateUtil().queryPage(sqlBuf.toString(), Ad_Group.class, argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Ad_Group> dataList = page.getData();
            ArrayList<Ad_Group> newDataList = Lists.newArrayList();
            for (Iterator<Ad_Group> iter = dataList.iterator(); iter.hasNext();)
            {
                Ad_Group ad_group = new Ad_Group();
                DynaModel row = (DynaModel) iter.next();
                ad_group.fromMap(row);
                newDataList.add(ad_group);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    /**
     * 添加广告组
     * @param ad_group
     */
    public void addAdGroup(Ad_Group ad_group)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(ad_group.toMap());
        String id = getSeqValue("T_AD_GROUP");
        dataRow.set("id", id);
        dataRow.set("orderline", id);
        getJdbcTemplateUtil().insert("T_AD_GROUP", dataRow);
    }
    
    /**
     * 修改广告组
     * @param ad_group
     */
    public void updateAdGroup(Ad_Group ad_group)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(ad_group.toMap());
        getJdbcTemplateUtil().update("T_AD_GROUP", dataRow, "id", new Integer(ad_group.getId()));
    }
    
    public void deleteAdGroup(int id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("delete t_ad_group where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public Ad_Group findAdGroupById(int id, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_ad_group where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        DynaModel dataRow = getJdbcTemplateUtil().queryMap(sqlBuf.toString(), argList.toArray());
        if (dataRow != null)
        {
            Ad_Group ad_group = new Ad_Group();
            ad_group.fromMap(dataRow);
            return ad_group;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
	public List<Map> findAdById(int id, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("SELECT * FROM T_AD A INNER JOIN T_AD_GROUP B ON A.GROUP_NO = B.ID WHERE 1=1");
        if (id != 0)
        {
            sqlBuf.append(" AND B.ID = ?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" AND B.SITENO = ?");
            argList.add(siteno);
        }
        return getJdbcTemplateUtil().queryForList(sqlBuf.toString(), Map.class, argList.toArray());
    }
}
