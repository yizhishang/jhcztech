package com.yizhishang.common.table.consts;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.MethodUtils;

import com.yizhishang.base.util.StringHelper;

public class Consts
{
    
    // 输入框类型
    public static final String input_type_input = "input_type_input"; // input框
    
    public static final String input_type_textarea = "input_type_textarea"; // 文本域
    
    public static final String input_type_select = "input_type_select"; // 下拉框
    
    public static final String input_type_open_select_radio = "input_type_open_select_radio"; // 弹出选择页面单选
    
    public static final String input_type_open_select_checkbox = "input_type_open_select_checkbox"; // 弹出选择页面多选
    
    public static final String input_type_radio = "input_type_radio"; // 单选框
    
    public static final String input_type_checkbox = "input_type_checkbox"; // 多选框
    
    public static final String input_type_html_editor = "input_type_html_editor"; // html编辑框
    
    public static final String input_type_file = "input_type_file"; // 文件上传
    
    // 展示内容
    public static final String showtext_input_type_input = "input框"; // input框
    
    public static final String showtext_input_type_textarea = "文本域"; // 文本域
    
    public static final String showtext_input_type_select = "下拉框"; // 下拉框
    
    public static final String showtext_input_type_open_select_radio = "弹出单选页面"; // 弹出单选页面
    
    public static final String showtext_input_type_open_select_checkbox = "弹出多选页面"; // 弹出多选页面
    
    public static final String showtext_input_type_radio = "单选框"; // 单选框
    
    public static final String showtext_input_type_checkbox = "多选框"; // 多选框
    
    public static final String showtext_input_type_html_editor = "html编辑框"; // html编辑框
    
    public static final String showtext_input_type_file = "文件上传"; // 文件上传
    
    // 特殊格式
    public static final String special_format_time = "special_format_time"; // 时间到秒
    //  public static final String special_format_date = "special_format_date"; // 时间到天
    
    public static final String special_format_number = "special_format_number"; // 数字
    
    //public static final String special_format_mobile = "special_format_mobile"; // 移动电话
    // 展示内容
    public static final String showtext_special_format_time = "时间到秒"; // 时间到秒
    //  public static final String showtext_special_format_date = "时间到天"; // 时间到天
    
    public static final String showtext_special_format_number = "数字"; // 数字
    
    //public static final String showtext_special_format_mobile = "移动电话"; // 移动电话
    
    //系统字段
    public static final String sys_col_time_once = "sys_col_time_once"; // 取一次系统时间
    
    public static final String sys_col_userid_once = "sys_col_userid_once"; // 取一次当前用户id
    
    public static final String sys_col_time = "sys_col_time"; // 每次取系统时间
    
    public static final String sys_col_userid = "sys_col_userid"; // 每次取当前用户id
    
    // 展示内容
    public static final String showtext_sys_col_time_once = "取一次系统时间"; // 取一次系统时间
    
    public static final String showtext_sys_col_userid_once = "取一次当前用户id"; // 取一次当前用户id
    
    public static final String showtext_sys_col_time = "每次取系统时间"; // 每次取系统时间
    
    public static final String showtext_sys_col_userid = "每次取当前用户id"; // 每次取当前用户id
    
    // 获取下拉框选项,参数为同一类型变量的前缀如"input_type_"
    public static String getInputTypeOptions(String prefix, String curValue)
    {
        Field[] fields = Consts.class.getDeclaredFields();
        Consts temp = new Consts();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fields.length; i++)
        {
            String fname = fields[i].getName();
            if (fname.startsWith(prefix))
            {
                String valuemethod = getMethodName("get", fname);
                String textmethod = getMethodName("get", "showtext_" + fname);
                try
                {
                    String value = (String) MethodUtils.invokeMethod(temp, valuemethod, null);
                    String text = (String) MethodUtils.invokeMethod(temp, textmethod, null);
                    if (!StringHelper.isEmpty(value))
                    {
                        sb.append("<option value='" + value + "'");
                        if (!StringHelper.isEmpty(curValue) && value.equals(curValue))
                        { // 选中值
                            sb.append(" selected='selected'");
                        }
                        sb.append(" >");
                        sb.append(text);
                        sb.append("</option>");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    
    // 获得get或set方法的名称
    private static String getMethodName(String methodtype, String name)
    {
        String result = "";
        if (!StringHelper.isEmpty(methodtype) && !StringHelper.isEmpty(name))
        {
            String[] nameArr = name.split("\\_");
            String temp = "";
            for (int i = 0; i < nameArr.length; i++)
            {
                temp += nameArr[i].substring(0, 1).toUpperCase() + nameArr[i].substring(1);
            }
            result = methodtype + temp;
        }
        return result;
    }
    
    public static String getInputTypeInput()
    {
        return input_type_input;
    }
    
    public static String getInputTypeTextarea()
    {
        return input_type_textarea;
    }
    
    public static String getInputTypeSelect()
    {
        return input_type_select;
    }
    
    public static String getInputTypeRadio()
    {
        return input_type_radio;
    }
    
    public static String getInputTypeCheckbox()
    {
        return input_type_checkbox;
    }
    
    public static String getInputTypeHtmlEditor()
    {
        return input_type_html_editor;
    }
    
    public static String getInputTypeFile()
    {
        return input_type_file;
    }
    
    public static String getShowtextInputTypeInput()
    {
        return showtext_input_type_input;
    }
    
    public static String getShowtextInputTypeTextarea()
    {
        return showtext_input_type_textarea;
    }
    
    public static String getShowtextInputTypeSelect()
    {
        return showtext_input_type_select;
    }
    
    public static String getShowtextInputTypeRadio()
    {
        return showtext_input_type_radio;
    }
    
    public static String getShowtextInputTypeCheckbox()
    {
        return showtext_input_type_checkbox;
    }
    
    public static String getShowtextInputTypeHtmlEditor()
    {
        return showtext_input_type_html_editor;
    }
    
    public static String getShowtextInputTypeFile()
    {
        return showtext_input_type_file;
    }
    
    public static String getSpecialFormatTime()
    {
        return special_format_time;
    }
    
    public static String getSpecialFormatNumber()
    {
        return special_format_number;
    }
    
    public static String getShowtextSpecialFormatTime()
    {
        return showtext_special_format_time;
    }
    
    public static String getShowtextSpecialFormatNumber()
    {
        return showtext_special_format_number;
    }
    
    public static String getSysColTimeOnce()
    {
        return sys_col_time_once;
    }
    
    public static String getSysColUseridOnce()
    {
        return sys_col_userid_once;
    }
    
    public static String getSysColTime()
    {
        return sys_col_time;
    }
    
    public static String getSysColUserid()
    {
        return sys_col_userid;
    }
    
    public static String getShowtextSysColTimeOnce()
    {
        return showtext_sys_col_time_once;
    }
    
    public static String getShowtextSysColUseridOnce()
    {
        return showtext_sys_col_userid_once;
    }
    
    public static String getShowtextSysColTime()
    {
        return showtext_sys_col_time;
    }
    
    public static String getShowtextSysColUserid()
    {
        return showtext_sys_col_userid;
    }
    
    public static String getInputTypeOpenSelectRadio()
    {
        return input_type_open_select_radio;
    }
    
    public static String getInputTypeOpenSelectCheckbox()
    {
        return input_type_open_select_checkbox;
    }
    
    public static String getShowtextInputTypeOpenSelectRadio()
    {
        return showtext_input_type_open_select_radio;
    }
    
    public static String getShowtextInputTypeOpenSelectCheckbox()
    {
        return showtext_input_type_open_select_checkbox;
    }
}
