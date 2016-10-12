package com.jhcz.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;

@Service
public class CommonService extends BaseService
{
    
    // 获取列表展示用字段配置
    public List<Object> getListColumns(String table_name)
    {
        String condition = " and show_in_list='Y' ";
        return getColumns(table_name, condition);
    }
    
    // 获取详情展示用字段配置
    public List<Object> getInfoColumns(String table_name)
    {
        String condition = " and show_in_info='Y'";
        return getColumns(table_name, condition);
    }
    
    // 获取系统用字段配置
    public List<Object> getSysColumns(String table_name)
    {
        String condition = " and is_sys='Y'";
        return getColumns(table_name, condition);
    }
    
    // 获取查询展示用字段配置
    public List<Object> getSearchColumns(String table_name)
    {
        String condition = " and show_in_search='Y' ";
        return getColumns(table_name, condition);
    }
    
    // 获取所有字段配置
    public List<Object> getAllColumns(String table_name)
    {
        String condition = "";
        return getColumns(table_name, condition);
    }
    
    // 获取指定的字段的配置
    private List<Object> getColumns(String table_name, String condition)
    {
        String sql = "select * from t_b_table_column where table_name_en=? " + condition + " order by orderno";
        ArrayList<Object> argList = new ArrayList<Object>();
        ;
        argList.add(table_name);
        List<Object> list = getJdbcTemplate().query(sql, argList.toArray());
        return list;
    }
    
    // 获取配置的按钮
    public DataRow getTableInfo(String table_name)
    {
        String sql = "select * from t_b_table where name_en=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(table_name);
        return getJdbcTemplate().queryMap(sql, argList.toArray());
    }
    
    // 新增
    public void add(String table_name, String pkcol, String orderno, DataRow bean)
    {
        String id = getSeqValue(table_name.toUpperCase());
        bean.set(pkcol, id);
        if (!StringHelper.isEmpty(orderno))
        {
            bean.set(orderno, id);
        }
        getJdbcTemplate().insert(table_name, bean);
    }
    
    // 更新
    public void update(String table_name, String pkcol, DataRow bean)
    {
        getJdbcTemplate().update(table_name, bean, pkcol, bean.getString(pkcol));
    }
    
    // 获取单个bean
    public DataRow loadBean(String table_name, String pkcol, String pkvalue)
    {
        String sql = "select * from " + table_name + " where " + pkcol + "=? ";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(pkvalue);
        return getJdbcTemplate().queryMap(sql, argList.toArray());
    }
    
    // 删除
    public void delete(String table_name, String pkcol, String pkvalue)
    {
        String sql = "delete from " + table_name + " where " + pkcol + "=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(pkvalue);
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    // 导出数据前处理待导出的数据
    @SuppressWarnings("unused")
    public void dealExportData(List<Object> list)
    {
        for (int i = 0; list != null && i < list.size(); i++)
        {
            DataRow bean = (DataRow) list.get(i);
        }
    }
    
    // 获取列表数据
    public List<Object> getListData(String table_name, String orderby, String orderbysort, List<Object> params)
    {
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("select * from " + table_name + " where 1=1 ");
        // 开始拼装查询参数
        for (int i = 0; params != null && i < params.size(); i++)
        {
            DataRow item = (DataRow) params.get(i);
            String pname = item.getString("pname");
            String pvalue = item.getString("pvalue");
            String type = item.getString("type");
            if (!StringHelper.isEmpty(pname) && !StringHelper.isEmpty(pvalue))
            {
                if ("eq".equals(type))
                {
                    sqlBuf.append(" and " + pname + "=?");
                    argList.add(pvalue);
                }
                else if (">".equals(type))
                {
                    sqlBuf.append(" and " + pname + ">?");
                    argList.add(pvalue);
                }
                else if (">=".equals(type))
                {
                    sqlBuf.append(" and " + pname + ">=?");
                    argList.add(pvalue);
                }
                else if ("<".equals(type))
                {
                    sqlBuf.append(" and " + pname + "<?");
                    argList.add(pvalue);
                }
                else if ("<=".equals(type))
                {
                    sqlBuf.append(" and " + pname + "<=?");
                    argList.add(pvalue);
                }
                else if ("in".equals(type))
                {
                    sqlBuf.append(" and " + pname + "in (?)");
                    argList.add(pvalue);
                }
                else
                {
                    sqlBuf.append(" and " + pname + " like '%" + pvalue + "%'");
                }
            }
        }
        if (!StringHelper.isEmpty(orderby))
        {
            sqlBuf.append(" order by " + orderby + " " + orderbysort);
        }
        return getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
    }
    
    // 获取分页列表数据
    public DBPage getPageData(String table_name, String orderby, String orderbysort, int curPage, int numPerPage, List<Object> params)
    {
        DBPage page = null;
        StringBuffer sqlBuf = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuf.append("select * from " + table_name + " where 1=1 ");
        // 开始拼装查询参数
        for (int i = 0; params != null && i < params.size(); i++)
        {
            DataRow item = (DataRow) params.get(i);
            String pname = item.getString("pname");
            String pvalue = item.getString("pvalue");
            String type = item.getString("type");
            if (!StringHelper.isEmpty(pname) && !StringHelper.isEmpty(pvalue))
            {
                if ("eq".equals(type))
                {
                    sqlBuf.append(" and " + pname + "=?");
                    argList.add(pvalue);
                }
                else if (">".equals(type))
                {
                    sqlBuf.append(" and " + pname + ">?");
                    argList.add(pvalue);
                }
                else if (">=".equals(type))
                {
                    sqlBuf.append(" and " + pname + ">=?");
                    argList.add(pvalue);
                }
                else if ("<".equals(type))
                {
                    sqlBuf.append(" and " + pname + "<?");
                    argList.add(pvalue);
                }
                else if ("in".equals(type))
                {
                    sqlBuf.append(" and " + pname + "in (?)");
                    argList.add(pvalue);
                }
                else if ("<=".equals(type))
                {
                    sqlBuf.append(" and " + pname + "<=?");
                    argList.add(pvalue);
                }
                else
                {
                    sqlBuf.append(" and " + pname + " like '%" + pvalue + "%'");
                }
            }
        }
        if (!StringHelper.isEmpty(orderby))
        {
            sqlBuf.append(" order by " + orderby + " " + orderbysort);
        }
        page = getJdbcTemplate().queryPage(sqlBuf.toString(), argList.toArray(), curPage, numPerPage);
        return page;
    }
}
