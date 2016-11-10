package com.yizhishang.business.other.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
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
    
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage page = null;
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
        page = getJdbcTemplate().queryPage(sqlBuf.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Ad_Group ad_group = new Ad_Group();
                DataRow row = (DataRow) iter.next();
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
        DataRow dataRow = new DataRow();
        dataRow.putAll(ad_group.toMap());
        String id = getSeqValue("T_AD_GROUP");
        dataRow.set("id", id);
        dataRow.set("orderline", id);
        getJdbcTemplate().insert("T_AD_GROUP", dataRow);
    }
    
    /**
     * 修改广告组
     * @param ad_group
     */
    public void updateAdGroup(Ad_Group ad_group)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(ad_group.toMap());
        getJdbcTemplate().update("T_AD_GROUP", dataRow, "id", new Integer(ad_group.getId()));
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
        getJdbcTemplate().update(sqlBuf.toString(), argList.toArray());
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
        DataRow dataRow = getJdbcTemplate().queryMap(sqlBuf.toString(), argList.toArray());
        if (dataRow != null)
        {
            Ad_Group ad_group = new Ad_Group();
            ad_group.fromMap(dataRow);
            return ad_group;
        }
        return null;
    }
    
    public List<Object> findAdById(int id, String siteno)
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
        return getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
    }
}
