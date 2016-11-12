package com.yizhishang.business.other.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.business.domain.Ad;
import com.yizhishang.business.domain.Ad_Group;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-26
 * 创建时间: 11:45:07
 */
@Service
public class AdManageService extends BaseService
{
    
    @SuppressWarnings("rawtypes")
	public DBPage<Map> getPageData(int curPage, int numPerPage, String siteNo, String type, String keyword)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("SELECT A.*,B.NAME GROUPNAME FROM T_AD A,T_AD_GROUP B WHERE A.GROUP_NO = B.ID");
        if (StringHelper.isNotBlank(siteNo))
        {
            sqlBuf.append(" AND A.SITENO = ?");
            argList.add(siteNo);
        }
        if (StringHelper.isNotBlank(type))
        {
            sqlBuf.append(" AND A.TYPE = ?");
            argList.add(type);
        }
        if (StringHelper.isNotBlank(keyword))
        {
            sqlBuf.append(" AND A.NAME LIKE ?");
            argList.add("%" + keyword + "%");
        }
        sqlBuf.append(" ORDER BY A.ORDERLINE");
        DBPage<Map> page = getJdbcTemplateUtil().queryPage(sqlBuf.toString(), Map.class, argList.toArray(), curPage, numPerPage);
        /*
         if (page != null)
         {
         List dataList = page.getData();
         ArrayList newDataList = new ArrayList();
         for (Iterator iter = dataList.iterator(); iter.hasNext();)
         {
         Ad ad = new Ad();
         DynaModel row = (DynaModel) iter.next();
         ad.fromMap(row);
         newDataList.add(ad);
         }
         page.setData(newDataList);
         }*/
        return page;
    }
    
    /**
     * 添加广告
     * @param ad
     */
    public void addAd(DynaModel ad)
    {
        String id = getSeqValue("T_AD");
        ad.set("ad_id", id);
        ad.set("orderline", id);
        getJdbcTemplateUtil().insert("T_AD", ad);
    }
    
    /**
     * 修改广告
     * @param ad
     */
    public void updateAd(DynaModel ad)
    {
        getJdbcTemplateUtil().update("t_ad", ad, "ad_id", ad.getString("ad_id"));
    }
    
    public Ad findAdById(int ad_id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("select * from t_ad where 1=1");
        if (ad_id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(ad_id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        sqlBuf.append(" order by ad_id desc");
        
        return getJdbcTemplateUtil().queryMap(sqlBuf.toString(), Ad.class, argList.toArray());
    }
    
    public void deteleAdInfo(int ad_id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("delete t_ad where 1=1");
        if (ad_id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(ad_id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public List<Ad_Group> findAllAdGroup()
    {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_ad_group order by orderline desc, id desc");
        return getJdbcTemplateUtil().queryForList(sqlBuf.toString(), Ad_Group.class);
    }
    
    public void linkYes(int id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("update t_ad set state='1' where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public void linkNo(int id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("update t_ad set state='0' where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public void showYes(int id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("update t_ad set file_state='1' where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public void showNo(int id, String siteno)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("update t_ad set file_state='0' where 1=1");
        if (id != 0)
        {
            sqlBuf.append(" and ad_id=?");
            argList.add(new Integer(id));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }
    
    public List<DynaModel> findAdsByGroupNo(int groupNo)
    {
        String sql = "SELECT * FROM T_AD WHERE STATE = 1 AND GROUP_NO = ? ORDER BY ORDERLINE";
        return getJdbcTemplateUtil().queryForList(sql, DynaModel.class, new Object[] { groupNo });
    }
    
}
