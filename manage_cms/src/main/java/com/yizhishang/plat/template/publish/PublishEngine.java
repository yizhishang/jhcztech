package com.yizhishang.plat.template.publish;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.jdbc.JdbcTemplate;
import com.yizhishang.base.util.queue.MultithreadingWorkQueue;
import com.yizhishang.plat.util.QueueThreadProperty;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-4
 * 创建时间: 14:13:48
 */
public class PublishEngine extends TimerTask
{
    
    private final Logger logger = LoggerFactory.getLogger(PublishEngine.class);
    
    private static PublishThead publishThread = null;
    
    /**
    * 启动模板发布引擎，开始模板的发布
    */
    @Override
    public synchronized void run()
    {
        if (publishThread == null)
        {
            logger.info("发布模板开始------------------");
            
            //把因为服务器被非正常中断时还没有发布完成的内容再重新发布一次
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            String MachineId = Configuration.getString("system.machineId");
            
            ArrayList<Object> argList = new ArrayList<Object>();
            argList.add(MachineId);
            jdbcTemplate.update("update T_PUBLISH_QUEUE set state=0 where state=1 and machine_id=?", argList.toArray());
            
            publishThread = new PublishThead();
            new Thread(publishThread, "publish-main-thread").start();
        }
    }
}

class PublishThead extends Thread
{
    
    private static Logger logger = LoggerFactory.getLogger(PublishThead.class);
    
    int workThreadNum = Configuration.getInt("system.workThreadNum", 10);//模板发布队列工作线程数量，默认为10个线程
    
    int maxQueueLen = Configuration.getInt("system.maxQueueLen", 100);//模板发布队列最大数量，默认为100
    
    //模板发布工作队列
    private final MultithreadingWorkQueue publishQueue = new MultithreadingWorkQueue(workThreadNum, maxQueueLen, new PublishCallBack());
    
    @Override
    public void run()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String MachineId = Configuration.getString("system.machineId");
        
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(0));
        argList.add(MachineId);
        
        while (true)
        {
            try
            {
                if (publishQueue.isEmpty()) //队列已经空了，需要继续加入，每次加入100条
                {
                    List<Object> dataList = jdbcTemplate.query("SELECT ID FROM T_PUBLISH_QUEUE WHERE STATE=? AND MACHINE_ID=? ORDER BY ID ", argList.toArray(),
                            100);
                    if (dataList != null && dataList.size() > 0)
                    {
                        for (Object dataRow : dataList)
                        {
                            int queueId = ((DataRow) dataRow).getInt("id");
                            publishQueue.put(new Integer(queueId)); //加入发布队列
                        }
                    }
                }
                int checkInterval = Configuration.getInt("publish.checkInterval", 1); //以毫秒为单位
                Thread.sleep(checkInterval * 1000);
                
                //记录线程工作的日志
                int isThreadQueueLog = Configuration.getInt("publish.isThreadQueueLog");
                if (isThreadQueueLog == 1)
                {
                    QueueThreadProperty.setPropertyString(Thread.currentThread().getName(), String.valueOf(System.currentTimeMillis()));
                }
            }
            catch (Exception ex)
            {
                logger.error("", ex);
            }
        }
    }
}
