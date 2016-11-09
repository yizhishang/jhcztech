package com.jhcz.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jhcz.base.jdbc.DataRow;

public class ToolKit
{
    
    private static Logger logger = LoggerFactory.getLogger(ToolKit.class);
    
    /**
     * 转换空字符串
     */
    public static String nullTrans(String source, String defaultvalue)
    {
        String result = source;
        if (StringHelper.isEmpty(result))
        {
            result = defaultvalue;
        }
        return result;
    }
    
    /**
     * 将object转化为字符串
     */
    public static String o2s(Object source, String defaultvalue)
    {
        String result = defaultvalue;
        if (source != null)
        {
            result = source.toString();
        }
        return result;
    }
    
    /**
     * 获取n天之后的日期
     */
    public static Date getLastNDay(int n)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new java.util.Date());
        gc.add(GregorianCalendar.DATE, n);
        return gc.getTime();
    }
    
    /**
     * 获取本周第一天
     * 返回java.util.Date
     */
    public static Date getFirstDayOfWeek()
    {
        int week = getWeekNum();
        if (week == 0)
        {
            week = 7;
        }
        return getLastNDay(-1 * (week - 1));
    }
    
    /**
     * 获取本周第一天
     * 返回String
     */
    public static String getFirstDayOfWeek(String pattern)
    {
        int week = getWeekNum();
        if (week == 0)
        {
            week = 7;
        }
        Date date = getLastNDay(-1 * (week - 1));
        return DateHelper.formatDate(date, pattern);
    }
    
    /**
     * 获取今天是星期几(周天是0)
     */
    public static int getWeekNum()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        week = week - 1;
        return week;
    }
    
    /**
     * 截断字符串
     * @param str 要截断的字符串
     * @param length 截断后字符串的长度
     * @param type 字符串的长度计量单位(1:英文,2:中文)
     * @return
     */
    public static String spliteStr(String str, int length, String type)
    {
        str = str.trim();
        if ("".equals(type))
        {
            type = "2";
        }
        String chinese = "[\u0391-\uFFE5]";
        int count = 0;
        int num = 0;
        if ("2".equals(type))
        {
            length = length * 2;
        }
        boolean flag = false;
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < str.length(); i++)
        {
            /* 获取一个字符 */
            String temp = str.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese))
            {
                /* 中文字符长度为2 */
                count = count + 2;
                flag = false;
            }
            else
            {
                count = count + 1;
                flag = true;
            }
            if (count < length)
            {
                num = i + 1;
            }
            else if (count >= length)
            {
                num = i + 1;
                break;
            }
        }
        if (flag)
        {
            num = num - 1;
        }
        String result = str;
        if (count >= length)
        {
            result = str.substring(0, num);
        }
        if (!result.equals(str))
        {
            result += "...";
        }
        return result;
    }
    
    /**
     * 获取request中的所有参数并组成DataRow返回(默认的每个值的默认值是"",默认分隔符是",")
     * @param request
     * @return
     */
    public static DataRow getFormParams(HttpServletRequest request)
    {
        return getFormParams(request, ",", "", "");
    }
    
    /**
     * 获取request中的所有参数并组成DataRow返回(默认的每个值的默认值是"",默认分隔符是",",只取"form."开头的参数)
     * @param request
     * @return
     */
    public static DataRow getCmsFormParams(HttpServletRequest request)
    {
        return getFormParams(request, ",", "", "form.");
    }
    
    /**
     * 获取request中的所有参数并组成DataRow返回(默认的每个值的默认值是"")
     * @param request
     * @param seperator 分隔符
     * @return
     */
    public static DataRow getFormParamsDefSep(HttpServletRequest request, String seperator)
    {
        return getFormParams(request, seperator, "", "");
    }
    
    /**
     * 获取request中的所有参数并组成DataRow返回(默认数组的分隔符是",")
     * @param request
     * @param default_value 数组里每个值的默认值
     * @return
     */
    public static DataRow getFormParamsDefDefault(HttpServletRequest request, String default_value)
    {
        return getFormParams(request, ",", default_value, "");
    }
    
    /**
     * 获取request中的所有参数并组成DataRow返回
     * @param request
     * @param seperator 分隔符
     * @param default_value 数组里每个值的默认值
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static DataRow getFormParams(HttpServletRequest request, String seperator, String default_value, String prefix)
    {
        Enumeration ema = request.getParameterNames();
        DataRow param = new DataRow();
        while (ema.hasMoreElements())
        {
            String pname = (String) ema.nextElement();
            String sname = pname;
            if (!StringHelper.isEmpty(prefix) && !StringHelper.isEmpty(pname))
            {
                if (pname.indexOf(prefix) == -1)
                {
                    continue;
                }
                else
                {
                    sname = pname.substring(prefix.length());
                }
            }
            String[] value = request.getParameterValues(pname);
            String pvalue = "";
            if (value instanceof String[] && value != null)
            {
                String[] strArray = value;
                if (strArray.length == 1)
                {
                    pvalue = strArray[0];
                }
                else if (strArray.length > 1)
                {
                    pvalue = "";
                    for (int i = 0; i < strArray.length; i++)
                    {
                        String temp = ToolKit.nullTrans(strArray[i], default_value);
                        pvalue += (seperator + temp);
                    }
                    if (!StringHelper.isEmpty(pvalue) && pvalue.startsWith(seperator))
                    {
                        pvalue = pvalue.substring(seperator.length());
                    }
                }
                else
                {
                    pvalue = default_value;
                }
            }
            pvalue = ToolKit.nullTrans(pvalue, default_value);
            param.set(sname, pvalue);
        }
        return param;
    }
    
    /**
     * 获得转为字符串的数组参数(默认的每个值的默认值是"",默认分隔符是",")
     * @param request
     * @param parametername 参数名称
     * @return
     */
    public static String getArrParamStr(HttpServletRequest request, String parametername)
    {
        return getArrParamStr(request, parametername, ",", "");
    }
    
    /**
     * 获得转为字符串的数组参数(默认的每个值的默认值是"")
     * @param request
     * @param parametername 参数名称
     * @param seperator 分隔符
     * @return
     */
    public static String getArrParamStrDefSep(HttpServletRequest request, String parametername, String seperator)
    {
        return getArrParamStr(request, parametername, seperator, "");
    }
    
    /**
     * 获得转为字符串的数组参数(默认分隔符是",")
     * @param request
     * @param parametername 参数名称
     * @param default_value 数组里每个值的默认值
     * @return
     */
    public static String getArrParamStrDefDefault(HttpServletRequest request, String parametername, String default_value)
    {
        return getArrParamStr(request, parametername, ",", default_value);
    }
    
    /**
     * 获得转为字符串的数组参数
     * @param request
     * @param parametername 参数名称
     * @param seperator 分隔符
     * @param default_value 数组里每个值的默认值
     * @return
     */
    public static String getArrParamStr(HttpServletRequest request, String parametername, String seperator, String default_value)
    {
        String[] value = request.getParameterValues(parametername);
        String pvalue = "";
        if (value instanceof String[] && value != null)
        {
            String[] strArray = value;
            if (strArray.length == 1)
            {
                pvalue = strArray[0];
            }
            else if (strArray.length > 1)
            {
                pvalue = "";
                for (int i = 0; i < strArray.length; i++)
                {
                    String temp = ToolKit.nullTrans(strArray[i], default_value);
                    pvalue += (seperator + temp);
                }
                if (!StringHelper.isEmpty(pvalue) && pvalue.startsWith(seperator))
                {
                    pvalue = pvalue.substring(seperator.length());
                }
            }
            else
            {
                pvalue = default_value;
            }
        }
        pvalue = ToolKit.nullTrans(pvalue, default_value);
        return pvalue;
    }
    
    /**
     * 获取excel的标题(取第一行)
     * @param file
     * @return
     * @throws IOException
     */
    public static DataRow ftExcelTitle(File file) throws IOException
    {
        DataRow titlebean = ftExcelRow(file, 1, null, null);
        return titlebean;
    }
    
    /**
     * 获取指定行数的excel数据(包含第一行标题行,下标从1开始)
     * @param file
     * @param select_row
     * @param fieldsName
     * @param dafaultValue
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static DataRow ftExcelRow(File file, int select_row, String[] fieldsName, Map<String, Object> dafaultValue) throws IOException
    {
        DataRow rowbean = new DataRow();
        if (!file.isFile())
        {
            return rowbean;
        }
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
            //			excel工作布，即一个excel文件
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            
            HSSFSheet childSheet = wb.getSheetAt(0);
            int rowNum = childSheet.getLastRowNum();
            if (select_row > rowNum || select_row < 1)
            {
                return rowbean;
            }
            HSSFRow row = childSheet.getRow(select_row - 1);
            if (row != null)
            {
                int cellNum = row.getLastCellNum();
                for (int k = 0; k < cellNum; k++)
                {
                    HSSFCell cell = row.getCell((short) k);
                    if (cell != null)
                    {
                        //System.out.println(cell.getCellType() + ":" +cell.toString());
                        String key = (fieldsName != null && fieldsName.length >= k) ? fieldsName[k] : String.valueOf(k);
                        String value = cell.toString();
                        int celltype = cell.getCellType();
                        if (Cell.CELL_TYPE_NUMERIC == celltype)
                        { // 数值型
                            DecimalFormat df = new DecimalFormat("0.0#####");
                            value = df.format(cell.getNumericCellValue());
                        }
                        if (StringHelper.isEmpty(value) && dafaultValue != null && !dafaultValue.isEmpty())
                        {
                            value = (String) dafaultValue.get(key);
                        }
                        rowbean.set(key, value);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }
        finally
        {
            try
            {
                if (inputStream != null)
                {
                    inputStream.close();
                }
            }
            catch (IOException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        return rowbean;
    }
    
    /**
     * 读取excel内容，只取第一个sheet
     * @param file File对象
     * @param fieldsName List中Map的键名，可为空，默认为列数索引（首列索引为0）
     * @param hasTitle excel中是否有标题（如果有标题则去掉第一列数据）
     * @param dafaultValue 默认值（和fieldsName对应的值为空时，取默认值）
     * @return 返回一个包含DataRow的List
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static List<Object> readExcel(File file, Object[] fieldsName, boolean hasTitle, Map<String, Object> dafaultValue) throws IOException
    {
        List<Object> list = new ArrayList<Object>();
        if (!file.isFile())
        {
            return list;
        }
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
            //			excel工作布，即一个excel文件
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            
            HSSFSheet childSheet = wb.getSheetAt(0);
            int rowNum = childSheet.getLastRowNum() + 1;
            
            for (int j = 0; j < rowNum; j++)
            {
                if (hasTitle)
                {
                    hasTitle = !hasTitle;
                    continue;
                }
                DataRow map = new DataRow();
                HSSFRow row = childSheet.getRow(j);
                if (row != null)
                {
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++)
                    {
                        HSSFCell cell = row.getCell((short) k);
                        if (cell != null)
                        {
                            String key = String.valueOf(k);
                            if (fieldsName != null && fieldsName.length > k)
                            {
                                if (fieldsName[k] != null)
                                {
                                    key = fieldsName[k].toString();
                                }
                            }
                            String value = cell.toString();
                            if (!StringHelper.isEmpty(value))
                            {
                                value = charsetfilter(value); // 过滤掉乱码
                            }
                            int celltype = cell.getCellType();
                            if (Cell.CELL_TYPE_NUMERIC == celltype)
                            { // 数值型
                                DecimalFormat df = new DecimalFormat("0.0#####");
                                value = df.format(cell.getNumericCellValue());
                            }
                            if (StringHelper.isEmpty(value) && dafaultValue != null && !dafaultValue.isEmpty())
                            {
                                value = (String) dafaultValue.get(key);
                            }
                            map.set(key, value);
                        }
                    }
                }
                list.add(map);
            }
        }
        catch (FileNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }
        finally
        {
            try
            {
                if (inputStream != null)
                {
                    inputStream.close();
                }
            }
            catch (IOException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        return list;
    }
    
    public static String charsetfilter(String str)
    {
        String source = str;
        String result = "";
        for (int i = 0; i < source.length(); i++)
        {
            String temp = source.substring(i, i + 1);
            String after;
            try
            {
                after = new String(temp.getBytes("gbk"));
                if (temp.equals(after))
                {
                    result += temp;
                }
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception
    {
        DataRow titles = ToolKit.ftExcelTitle(new File("/Volumes/mac/testExcel.xls"));
        System.out.println(titles);
    }
}
