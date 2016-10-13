package com.jhcz.plat.template.plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.JdbcTemplate;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.service.PublishQueueService;

/**
 * 描述:  定时发布栏目
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 
 * 版本:	 1.0
 * 创建日期: 2010-3-12
 * 创建时间: 上午10:30:54
 */
public class PublishPlan extends Thread
{
    
    private static Logger logger = Logger.getLogger(PublishPlan.class);

    private static List<Object> dataList = null;
    
    /**
    * 
    * 描述:  比较当前时间与传入的时间大小  
    * 作者:	 
    * 创建日期: 2010-3-14
    * 创建时间: 下午12:19:50
    * @param dateStr
    * @param pattern
    * @param isEqual 是否判断相等
    * @return 
    * @return boolean
    * @throws
    */
    public boolean compareNowDate(String dateStr, String pattern, boolean isEqual)
    {
        try
        {
            DateFormat df = new SimpleDateFormat(pattern);
            Date nowDate = df.parse(df.format(new Date()));//格式化当前日期
            
            Date compareDate = df.parse(dateStr);
            if (isEqual)
            {
                //判断当前时间是否大于或等于传入的时间
                return !nowDate.before(compareDate);
            }
            else
            {
                //判断当前时间是否大于传入的时间
                return nowDate.after(compareDate);
            }
            
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        return false;
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-06-20 22:43:41
    */
    private void disposeTask()
    {
        String machineId = Configuration.getString("system.machineId");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        dataList = jdbcTemplate.query(
                "SELECT ID,CATALOG_ID,TYPE,RECURSION,TIME,PUBLISHTIME,MACHINE_ID FROM T_PUBLISH_PLAN WHERE MACHINE_ID = ? ORDER BY ID ASC",
                new Object[] { machineId });
        if (dataList != null && dataList.size() > 0)
        {
            DataRow data = null;
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                data = (DataRow) iter.next();
                if (data != null)
                {
                    int type = data.getInt("type");//计划类型
                    int recursion = data.getInt("recursion");//是否需要同时发布子栏目
                    String time = data.getString("time");//固定或间隔的时间
                    String publishTime = data.getString("publishtime");
                    
                    if (type == 0) //固定时间发布
                    {
                        //判断当天是否定时发布过栏目
                        if (StringHelper.isEmpty(publishTime) || compareNowDate(publishTime, "yyyy-MM-dd", false))
                        {
                            //判断目前时间是否等于或大于指定时间
                            if (compareNowDate(time, "HH:mm", true))
                            {
                                publishCatalog(data.getInt("id"), data.getInt("catalog_id"), recursion, machineId);
                            }
                        }
                    }
                    else
                    //间隔时间发布
                    {
                        //最后发布时间发布时间发如果为空，则直接发布当前栏目
                        if (StringHelper.isEmpty(publishTime))
                        {
                            publishCatalog(data.getInt("id"), data.getInt("catalog_id"), recursion, machineId);
                        }
                        else
                        {
                            //取当前时间与最后发布时间的毫秒数
                            long compareTime = DateHelper.getDateMiliDispersion(new Date(), DateHelper.parseString(publishTime));
                            
                            //将间隔的时间转化为毫秒数进行比较
                            if (compareTime >= Integer.parseInt(time) * 60 * 1000)
                            {
                                publishCatalog(data.getInt("id"), data.getInt("catalog_id"), recursion, machineId);
                            }
                        }
                    }
                }
            }
        }
        
    }
    
    /**
    * 
    * 描述:  
    * 作者:	 
    * 创建日期: 2010-3-12
    * 创建时间: 下午02:55:34
    * @param id
    * @param catalogId
    * @param recursion 
    * @return void
    * @throws
    */
    private void publishCatalog(int id, int catalogId, int recursion, String machineId)
    {
        //把要发布的栏目添加入发布队列
        DataRow data = new DataRow();
        if (recursion == 0)
        {
            data.put("cmd_str", "C:" + catalogId);
            data.put("show_info", "发布栏目[catalogId=" + catalogId + "]");
        }
        else
        {
            data.put("cmd_str", "CR:" + catalogId);
            data.put("show_info", "发布栏目[catalogId=" + catalogId + "]及其所有子栏目");
        }
        data.set("state", 0);
        data.set("siteno", "all");
        data.set("create_by", "admin");
        data.set("create_date", DateHelper.formatDate(new Date()));
        if (StringHelper.isEmpty(machineId))
        {
            machineId = Configuration.getString("system.machineId");
        }
        data.set("machine_id", machineId);
        
        PublishQueueService publishQueueService = (PublishQueueService) ServiceLocator.getService(PublishQueueService.class);
        publishQueueService.add(data);
        
        JdbcTemplate template = new JdbcTemplate();
        //发布完成，更新发布时间
        template.update("UPDATE T_PUBLISH_PLAN SET PUBLISHTIME = ? WHERE ID = ?", new Object[] { DateHelper.formatDate(new Date()), new Integer(id) });
    }
    
    @Override
    public void run()
    {
        
        while (true)
        {
            try
            {
                //暂停30秒
                Thread.sleep(30 * 1000);
                disposeTask();
            }
            catch (Exception ex)
            {
                logger.error("定时发布任务出错", ex);
            }
        }
        
    }
}
