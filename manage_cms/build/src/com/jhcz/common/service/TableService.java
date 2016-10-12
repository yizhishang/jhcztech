package com.jhcz.common.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;

@Service
public class TableService extends BaseService
{
    
    public void add(DataRow data)
    {
        String id = getSeqValue("t_b_table");
        data.set("id", id);
        getJdbcTemplate().insert("t_b_table", data);
    }
    
    public DataRow load(int id)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(id);
        String sql = "select * from t_b_table where id=?";
        DataRow result = getJdbcTemplate().queryMap(sql, argList.toArray());
        return result;
    }
    
    public void update(DataRow data)
    {
        int id = data.getInt("id");
        getJdbcTemplate().update("t_b_table", data, "id", new Integer(id));
    }
    
    public void delte(int id)
    {
        getJdbcTemplate().delete("t_b_table", "id", new Integer(id));
    }
    
    public DBPage getPageData(int curPage, int rowOfPage, String table_name_en, String table_name_ch)
    {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_b_table where 1=1 ");
        ArrayList<Object> argList = new ArrayList<Object>();
        if (!StringHelper.isEmpty(table_name_en))
        {
            sqlBuf.append(" and name_en like ? ");
            argList.add("%" + table_name_en + "%");
        }
        if (!StringHelper.isEmpty(table_name_ch))
        {
            sqlBuf.append(" and name_ch like ? ");
            argList.add("%" + table_name_ch + "%");
        }
        sqlBuf.append(" order by update_time desc ");
        return getJdbcTemplate().queryPage(sqlBuf.toString(), argList.toArray(), curPage, rowOfPage);
    }
}
