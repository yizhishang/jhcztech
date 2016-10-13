package com.jhcz.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;
import com.jhcz.common.consts.Consts;

@Service
public class TableColumnService extends BaseService
{
    
    public void importCols(DataRow tableBean)
    {
        if (tableBean != null && tableBean.get("name_en") != null)
        {
            String table_name_en = tableBean.getString("name_en");
            ArrayList<Object> argList = new ArrayList<Object>();
            argList.add(table_name_en.toUpperCase());
            String sql = "select * from user_tab_columns where Table_Name=?";
            List<Object> cols = getJdbcTemplate().query(sql, argList.toArray());
            DataRow colBean = new DataRow();
            colBean.set("table_name_en", table_name_en.toLowerCase());
            colBean.set("table_name_ch", tableBean.getString("name_ch"));
            colBean.set("table_id", tableBean.getString("id"));
            // 设置默认值
            colBean.set("input_type", Consts.input_type_input);
            colBean.set("align_in_list", "center");
            colBean.set("is_single", "N");
            colBean.set("be_null", "Y");
            colBean.set("is_special_format", "N");
            colBean.set("show_in_list", "N");
            colBean.set("show_in_search", "N");
            colBean.set("is_readonly", "N");
            colBean.set("be_export", "Y");
            colBean.set("is_sys", "N");
            String colstr = "";
            // 删除已经不存在的字段的定义
            String deleteSql = "delete from t_b_table_column where table_id=?";
            List<Object> delArgList = new ArrayList<Object>();
            delArgList.add(tableBean.getString("id"));
            for (int i = 0; cols != null && i < cols.size(); i++)
            {
                DataRow col = (DataRow) cols.get(i);
                colBean.set("name_en", col.getString("column_name").toLowerCase());
                colBean.set("name_ch", col.getString("column_name").toLowerCase());
                colBean.set("orderno", i);
                if (colBean.getString("name_en").equals(tableBean.getString("pk_column")))
                { // 默认主键为隐藏
                    colBean.set("show_in_info", "N");
                }
                else
                {
                    colBean.set("show_in_info", "Y");
                }
                colstr += ",'" + col.getString("column_name").toLowerCase() + "'";
                if (!isColAdded(colBean.getString("table_name_en"), colBean.getString("name_en")))
                {
                    add(colBean);
                }
            }
            if (!StringHelper.isEmpty(colstr))
            {
                colstr = colstr.substring(1);
                deleteSql += " and name_en not in (" + colstr + ")";
            }
            getJdbcTemplate().update(deleteSql, delArgList.toArray());
        }
    }
    
    private boolean isColAdded(String table_name_en, String name_en)
    {
        boolean flag = false;
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(table_name_en);
        argList.add(name_en);
        String sql = "select count(*) from t_b_table_column where table_name_en=? and name_en=?";
        int num = getJdbcTemplate().queryInt(sql, argList.toArray());
        if (num > 0)
        {
            flag = true;
        }
        return flag;
    }
    
    private void add(DataRow data)
    {
        String id = getSeqValue("t_b_table_column");
        data.set("id", id);
        getJdbcTemplate().insert("t_b_table_column", data);
    }
    
    public DataRow load(int id)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(id);
        String sql = "select * from t_b_table_column where id=?";
        DataRow result = getJdbcTemplate().queryMap(sql, argList.toArray());
        return result;
    }
    
    public void update(DataRow[] beans)
    {
        for (int i = 0; beans != null && i < beans.length; i++)
        {
            DataRow col = beans[i];
            int id = col.getInt("id");
            getJdbcTemplate().update("t_b_table_column", col, "id", new Integer(id));
        }
    }
    
    // 数据字典的select
    public List<Object> getEnumTypeList()
    {
        String sql = "select distinct ta.enum_name,ta.enum_value from t_enum_type ta right join t_enum_value tb on ta.enum_value=tb.enum_type order by ta.enum_name";
        return getJdbcTemplate().query(sql);
    }
    
    // 数据字段选中的项
    public String getEnumTypeSelected(String enum_condition)
    {
        String result = "";
        if (!StringHelper.isEmpty(enum_condition))
        {
            String sql = "select distinct enum_type from t_enum_value where 1=1 " + enum_condition;
            result = getJdbcTemplate().queryString(sql);
        }
        return result;
    }
    
    // 查出当前表的字段
    public List<Object> getTableCols(int colid)
    {
        String sql = "select tb.name_en,tb.name_ch from t_b_table_column tb left join t_b_table_column ta on ta.table_id=tb.table_id and ta.id<>tb.id where ta.id=? order by tb.orderno";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(colid);
        List<Object> cols = getJdbcTemplate().query(sql, argList.toArray());
        return cols;
    }
    
    public List<Object> getTableCols(String table_name_en)
    {
        String sql = "select * from user_tab_columns where Table_Name=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(table_name_en.toUpperCase());
        List<Object> cols = getJdbcTemplate().query(sql, argList.toArray());
        return cols;
    }
    
    public List<Object> getListData(int table_id, String table_name_en, String table_name_ch)
    {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_b_table_column where table_id=? ");
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(table_id);
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
        sqlBuf.append(" order by orderno ");
        return getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
    }
    
    public List<Object> getOptionBeans(DataRow bean)
    {
        String select_value = bean.getString("select_value");
        String select_text = bean.getString("select_text");
        String select_table = bean.getString("select_table");
        String select_condition = bean.getString("select_condition");
        String orderbyStr = "";
        if ("t_enum_value".equals(select_table.toLowerCase()))
        {
            orderbyStr = "orderline";
        }
        else if ("t_b_dictionary".equals(select_table.toLowerCase()))
        {
            orderbyStr = "sortnum";
        }
        List<Object> list = null;
        if (!StringHelper.isEmpty(select_table) && !StringHelper.isEmpty(select_text) && !StringHelper.isEmpty(select_value))
        {
            // 找出配置的字段的值列表
            String sql = "select distinct " + select_text + " as optiontext," + select_value + " as optionvalue";
            if (!"".equals(orderbyStr))
            {
                sql += "," + orderbyStr;
            }
            sql += " from " + select_table + " where 1=1 " + select_condition;
            if (!"".equals(orderbyStr))
            {
                sql += " order by " + orderbyStr;
            }
            BaseService bservice = new BaseService();
            list = bservice.getJdbcTemplate().query(sql);
        }
        return list;
    }
    
    /**
     * 获取某个值对应的id值(针对于下拉框等)
     * @param bean
     * @return
     */
    public String getImportOptionValue(DataRow bean, String import_value)
    {
        if (StringHelper.isEmpty(import_value))
        {
            return "";
        }
        String result = import_value;
        String select_value = bean.getString("select_value");
        String select_text = bean.getString("select_text");
        String select_table = bean.getString("select_table");
        String select_condition = bean.getString("select_condition");
        String orderbyStr = "";
        if ("t_enum_value".equals(select_table.toLowerCase()))
        {
            orderbyStr = "orderline";
        }
        else if ("t_b_dictionary".equals(select_table.toLowerCase()))
        {
            orderbyStr = "sortnum";
        }
        List<Object> list = null;
        if (!StringHelper.isEmpty(select_table) && !StringHelper.isEmpty(select_text) && !StringHelper.isEmpty(select_value))
        {
            // 找出配置的字段的值列表
            String sql = "select distinct " + select_text + " as optiontext," + select_value + " as optionvalue";
            if (!"".equals(orderbyStr))
            {
                sql += "," + orderbyStr;
            }
            sql += " from " + select_table + " where 1=1 " + select_condition;
            if (!"".equals(orderbyStr))
            {
                sql += " order by " + orderbyStr;
            }
            list = getJdbcTemplate().query(sql);
            for (int i = 0; list != null && i < list.size(); i++)
            {
                DataRow option = (DataRow) list.get(i);
                String text = option.getString("optiontext");
                String value = option.getString("optionvalue");
                if (import_value.equals(text) || import_value.equals(value))
                {
                    result = value;
                    break;
                }
            }
        }
        return result;
    }
    
    // 手动添加的字典项添加
    public void updateSdsr(String temp_id, String[] values, String[] texts)
    {
        DataRow bean = new DataRow();
        bean.set("column_id", temp_id);
        // 删除已经不存在的字段的定义
        String deleteSql = "delete from t_b_dictionary where column_id=?";
        List<Object> delArgList = new ArrayList<Object>();
        delArgList.add(temp_id);
        String value_str = "";
        for (int i = 0; i < values.length; i++)
        {
            if (!StringHelper.isEmpty(values[i]))
            {
                if (StringHelper.isEmpty(texts[i]))
                {
                    texts[i] = values[i];
                }
                bean.set("item_value", values[i]);
                bean.set("item_text", texts[i]);
                bean.set("sortnum", i);
                int id_ex = getSdsrExisits(bean);
                if (id_ex > 0)
                {
                    bean.set("id", id_ex);
                    updateSdsr(bean);
                }
                else
                {
                    addSdsr(bean);
                }
                value_str += ",'" + bean.getString("item_value") + "'";
            }
        }
        if (!StringHelper.isEmpty(value_str))
        {
            value_str = value_str.substring(1);
            deleteSql += " and item_value not in (" + value_str + ")";
        }
        getJdbcTemplate().update(deleteSql, delArgList.toArray());
    }
    
    private String addSdsr(DataRow bean)
    {
        String id = getSeqValue("t_b_dictionary");
        bean.set("id", id);
        getJdbcTemplate().insert("t_b_dictionary", bean);
        return id;
    }
    
    private void updateSdsr(DataRow bean)
    {
        int id = bean.getInt("id");
        getJdbcTemplate().update("t_b_dictionary", bean, "id", new Integer(id));
    }
    
    private int getSdsrExisits(DataRow bean)
    {
        int id = -1;
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(bean.getString("column_id"));
        argList.add(bean.getString("item_value"));
        String sql = "select id from t_b_dictionary where column_id=? and item_value=?";
        int num = getJdbcTemplate().queryInt(sql, argList.toArray());
        if (num > 0)
        {
            id = num;
        }
        return id;
    }
    
    public List<Object> getSdsrList(String column_id)
    {
        String sql = "select * from t_b_dictionary where column_id=? order by sortnum";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(column_id);
        return getJdbcTemplate().query(sql, argList.toArray());
    }
}
