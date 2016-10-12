package com.jhcz.common.bean;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.web.form.DynaForm;

public class TableColumnBean
{
    
    // 变量意义见数据字典或数据库注释
    private String table_name_ch;
    
    private int table_id;
    
    private String name_en;
    
    private String name_ch;
    
    private String input_type;
    
    private String is_single;
    
    private String be_null;
    
    private String is_special_format;
    
    private String special_format;
    
    private String show_in_list;
    
    private String show_in_info;
    
    private String default_value;
    
    private String is_readonly;
    
    private String be_export;
    
    private String show_in_search;
    
    private int orderno;
    
    private int id;
    
    private String table_name_en;
    
    private String select_value;
    
    private String select_text;
    
    private String select_table;
    
    private String select_condition;
    
    private String is_sys;
    
    private String sys_type;
    
    private String align_in_list;
    
    private String select_text_column;
    
    public static void main(String[] args)
    {
        String aa = "inputtypecheckbox";
        String[] aaarr = aa.split("\\_");
        String bb = "";
        for (int i = 0; i < aaarr.length; i++)
        {
            String temp = aaarr[i].substring(0, 1).toUpperCase() + aaarr[i].substring(1);
            bb += temp;
        }
        System.out.println(bb);
    }
    
    public static DataRow[] getTableColumnBeanArray(DynaForm form)
    {
        DataRow[] reuslt = null;
        if (form != null)
        {
            String[] ids = form.getStrArray("id");
            if (ids != null && ids.length > 0)
            {
                reuslt = new DataRow[ids.length];
                for (int i = 0; i < ids.length; i++)
                {
                    DataRow tempBean = putAllByArray(form, i, ids.length);
                    reuslt[i] = tempBean;
                }
            }
        }
        return reuslt;
    }
    
    //  多对单复制
    private static DataRow putAllByArray(Map<String, Object> map, int step, int length)
    {
        DataRow bean = new DataRow();
        Field[] fields = TableColumnBean.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            String fname = fields[i].getName().toLowerCase();
            String type = fields[i].getType().getName();
            Object value = getStringFromMapByArray(map, fname, step, length);
            if ("int".equals(type))
            {
                value = tranToInt((String) value);
            }
            bean.set(fname, value);
        }
        return bean;
    }
    
    private static String getStringFromMapByArray(Map<String, Object> map, String name, int step, int length)
    {
        String result = "";
        if (map != null && name != null && !"".equals(name) && map.get(name) != null)
        {
            Object obj = map.get(name);
            if ((obj instanceof Object[]) && ((Object[]) obj).length == length)
            {
                Object temp = ((Object[]) obj)[step];
                result = (temp == null) ? "" : temp.toString();
            }
        }
        return result;
    }
    
    // 单对单复制
    public void putAll(Map<String, Object> map)
    {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            String fname = fields[i].getName().toLowerCase();
            String type = fields[i].getType().getName();
            Object value = getStringFromMap(map, fname);
            if ("int".equals(type))
            {
                value = tranToInt((String) value);
            }
            String method = "set" + fname.substring(0, 1).toUpperCase() + fname.substring(1);
            try
            {
                MethodUtils.invokeMethod(this, method, value);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private String getStringFromMap(Map<String, Object> map, String name)
    {
        if (map == null || name == null || name.equals(""))
            return "";
        
        Object obj = map.get(name);
        return (obj == null) ? "" : obj.toString();
    }
    
    public static int tranToInt(String str)
    {
        int result = 0;
        try
        {
            result = Integer.parseInt(str);
        }
        catch (Exception e)
        {
            result = 0;
        }
        return result;
    }
    
    @SuppressWarnings("unused")
    private int getInt(Map<String, Object> map, String name)
    {
        if (map == null || name == null || name.equals(""))
            return 0;
        
        int value = 0;
        if (map.containsKey(name) == false)
            return 0;
        
        Object obj = map.get(name);
        if (obj == null)
            return 0;
        
        if (!(obj instanceof Integer))
        {
            try
            {
                value = Integer.parseInt(obj.toString());
            }
            catch (Exception ex)
            {
                value = 0;
            }
        }
        else
        {
            value = ((Integer) obj).intValue();
            obj = null;
        }
        
        return value;
    }
    
    public String getTable_name_ch()
    {
        return table_name_ch;
    }
    
    public void setTable_name_ch(String tableNameCh)
    {
        table_name_ch = tableNameCh;
    }
    
    public int getTable_id()
    {
        return table_id;
    }
    
    public void setTable_id(int tableId)
    {
        table_id = tableId;
    }
    
    public String getName_en()
    {
        return name_en;
    }
    
    public void setName_en(String nameEn)
    {
        name_en = nameEn;
    }
    
    public String getName_ch()
    {
        return name_ch;
    }
    
    public void setName_ch(String nameCh)
    {
        name_ch = nameCh;
    }
    
    public String getInput_type()
    {
        return input_type;
    }
    
    public void setInput_type(String inputType)
    {
        input_type = inputType;
    }
    
    public String getIs_single()
    {
        return is_single;
    }
    
    public void setIs_single(String isSingle)
    {
        is_single = isSingle;
    }
    
    public String getBe_null()
    {
        return be_null;
    }
    
    public void setBe_null(String beNull)
    {
        be_null = beNull;
    }
    
    public String getIs_special_format()
    {
        return is_special_format;
    }
    
    public void setIs_special_format(String isSpecialFormat)
    {
        is_special_format = isSpecialFormat;
    }
    
    public String getSpecial_format()
    {
        return special_format;
    }
    
    public void setSpecial_format(String specialFormat)
    {
        special_format = specialFormat;
    }
    
    public String getShow_in_list()
    {
        return show_in_list;
    }
    
    public void setShow_in_list(String showInList)
    {
        show_in_list = showInList;
    }
    
    public String getShow_in_info()
    {
        return show_in_info;
    }
    
    public void setShow_in_info(String showInInfo)
    {
        show_in_info = showInInfo;
    }
    
    public String getDefault_value()
    {
        return default_value;
    }
    
    public void setDefault_value(String defaultValue)
    {
        default_value = defaultValue;
    }
    
    public String getIs_readonly()
    {
        return is_readonly;
    }
    
    public void setIs_readonly(String isReadonly)
    {
        is_readonly = isReadonly;
    }
    
    public String getBe_export()
    {
        return be_export;
    }
    
    public void setBe_export(String beExport)
    {
        be_export = beExport;
    }
    
    public String getShow_in_search()
    {
        return show_in_search;
    }
    
    public void setShow_in_search(String showInSearch)
    {
        show_in_search = showInSearch;
    }
    
    public int getOrderno()
    {
        return orderno;
    }
    
    public void setOrderno(int orderno)
    {
        this.orderno = orderno;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getTable_name_en()
    {
        return table_name_en;
    }
    
    public void setTable_name_en(String tableNameEn)
    {
        table_name_en = tableNameEn;
    }
    
    public String getSelect_value()
    {
        return select_value;
    }
    
    public void setSelect_value(String selectValue)
    {
        select_value = selectValue;
    }
    
    public String getSelect_text()
    {
        return select_text;
    }
    
    public void setSelect_text(String selectText)
    {
        select_text = selectText;
    }
    
    public String getSelect_table()
    {
        return select_table;
    }
    
    public void setSelect_table(String selectTable)
    {
        select_table = selectTable;
    }
    
    public String getSelect_condition()
    {
        return select_condition;
    }
    
    public void setSelect_condition(String selectCondition)
    {
        select_condition = selectCondition;
    }
    
    public String getIs_sys()
    {
        return is_sys;
    }
    
    public void setIs_sys(String isSys)
    {
        is_sys = isSys;
    }
    
    public String getSys_type()
    {
        return sys_type;
    }
    
    public void setSys_type(String sysType)
    {
        sys_type = sysType;
    }
    
    public String getAlign_in_list()
    {
        return align_in_list;
    }
    
    public void setAlign_in_list(String alignInList)
    {
        align_in_list = alignInList;
    }
    
    public String getSelect_text_column()
    {
        return select_text_column;
    }
    
    public void setSelect_text_column(String selectTextColumn)
    {
        select_text_column = selectTextColumn;
    }
}
