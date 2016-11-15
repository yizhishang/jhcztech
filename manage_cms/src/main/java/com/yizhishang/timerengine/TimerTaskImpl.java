package com.yizhishang.timerengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTaskImpl extends AbstractTimerTask
{

    private static Logger logger = LoggerFactory.getLogger(TimerTaskImpl.class);

    //需要执行的任务
    private Task task = null;

    public TimerTaskImpl(TaskEntry taskEntry, Task task)
    {
        super(taskEntry);
        this.task = task;
    }

    public AbstractTimerTask getCloneObject()
    {
        return new TimerTaskImpl(taskEntry, task);
    }

    public void execute()
    {
        try {
            task.execute();
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    public void setTask(Task task)
    {
        this.task = task;
    }
}
