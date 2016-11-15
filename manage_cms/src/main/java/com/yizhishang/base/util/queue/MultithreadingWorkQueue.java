package com.yizhishang.base.util.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述: 多线程工作队列
 * 版权: Copyright (c) 2015
 * 公司:
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-02-17
 * 创建时间: 下午04:03:03
 */
public class MultithreadingWorkQueue
{

    private static Logger logger = LoggerFactory.getLogger(MultithreadingWorkQueue.class);

    // 内部工作对象存放队列
    private final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();

    // 发送线程数量
    private int threadNum = 10;

    // 最大发送队列长度
    private int maxQueueLen = 1024;

    // 发送回调接口
    private WorkCallBack callBack = null;

    // 工作线程数组
    private WorkThread[] workThreads = null;

    /**
     * 构造函数
     *
     * @param threadNum   工作线程数量
     * @param maxQueueLen 最大工作队列长度
     * @param callBack    具体对象工作回挑接口
     */
    public MultithreadingWorkQueue(int threadNum, int maxQueueLen, WorkCallBack callBack)
    {
        this.threadNum = threadNum;
        this.maxQueueLen = maxQueueLen;
        this.callBack = callBack;
        init();
    }

    /**
     * 初始化，启动相应的线程
     */
    private void init()
    {
        if (threadNum > 0) {
            workThreads = new WorkThread[threadNum];
            for (int i = 0; i < threadNum; i++) {
                workThreads[i] = new WorkThread();
                // workThreads[i].start();
                new Thread(workThreads[i], "work-queue-thread" + i).start();
            }
        }
    }

    /**
     * 判断发布队列是否已空。若为空，则返回true,否则返回False
     *
     * @return
     */
    public boolean isEmpty()
    {
        return (queue.size() == 0) ? true : false;
    }

    /**
     * 判断工作对象存放队列是否已满。若队列已满，则返回true,否则返回false
     *
     * @return
     */
    public boolean isFull()
    {
        if (queue.size() >= maxQueueLen) {
            return true;
        }
        return false;
    }

    /**
     * 添加对象到工作队列
     *
     * @param workObject
     */
    public void put(Object workObject)
    {
        try {
            queue.put(workObject);
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    /**
     * 发送工作线程
     *
     * @author owner
     */
    class WorkThread extends Thread
    {

        @Override
        public void run()
        {
            while (true) {
                try {
                    Object workObject = queue.take(); // 获得一个需要发送的对象
                    if (callBack != null) {
                        callBack.run(workObject);
                    }
                } catch (Exception ex) {
                    logger.error("", ex);
                }
            }
        }
    }
}
