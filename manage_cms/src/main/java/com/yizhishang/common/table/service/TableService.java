package com.yizhishang.common.table.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class TableService extends BaseService
{

    public void add(DynaModel data)
    {
        String id = getSeqValue("t_b_table");
        data.set("id", id);
        getJdbcTemplateUtil().insert("t_b_table", data);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> load(int id)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(id);
        String sql = "select * from t_b_table where id=?";
        try {
            return getJdbcTemplateUtil().queryMap(sql, Map.class, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void update(DynaModel data)
    {
        int id = data.getInt("id");
        getJdbcTemplateUtil().update("t_b_table", data, "id", new Integer(id));
    }

    public void delte(int id)
    {
        getJdbcTemplateUtil().delete("t_b_table", "id", new Integer(id));
    }

    @SuppressWarnings("rawtypes")
    public DBPage<Map> getPageData(int curPage, int rowOfPage, String table_name_en, String table_name_ch)
    {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_b_table where 1=1 ");
        ArrayList<Object> argList = new ArrayList<Object>();
        if (!StringHelper.isEmpty(table_name_en)) {
            sqlBuf.append(" and name_en like ? ");
            argList.add("%" + table_name_en + "%");
        }
        if (!StringHelper.isEmpty(table_name_ch)) {
            sqlBuf.append(" and name_ch like ? ");
            argList.add("%" + table_name_ch + "%");
        }
        sqlBuf.append(" order by update_time desc ");
        return getJdbcTemplateUtil().queryPage(sqlBuf.toString(), Map.class, argList.toArray(), curPage, rowOfPage);
    }
}
