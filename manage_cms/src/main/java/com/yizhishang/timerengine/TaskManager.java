package com.yizhishang.timerengine;

import com.yizhishang.base.util.StringHelper;
import com.yizhishang.timerengine.config.TaskConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class TaskManager extends TimerTask
{

    private static HashMap<Object, Object> taskEntryMap = new HashMap<Object, Object>();

    /**
     * 停止所有任务
     */
    public static void stop()
    {
        if (taskEntryMap != null) {
            for (Iterator<Object> iter = taskEntryMap.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                TaskEntry taskEntry = (TaskEntry) taskEntryMap.get(key);
                taskEntry.stop();
            }
        }
    }

    /**
     * 启动所有任务
     */
    @Override
    public void run()
    {
        List<HashMap<String, String>> taskList = TaskConfig.getTaskList();
        Iterator<HashMap<String, String>> iter = taskList.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> taskPropertyMap = iter.next();
            String taskType = StringHelper.n2s(taskPropertyMap.get("task-type"));
            AbstractTaskBuilder taskBuilder = TaskBuilderFactory.getInstance().getTaskBuilder(taskType);
            if (taskBuilder != null) {
                taskBuilder.setTaskProperty(taskPropertyMap);
                TaskEntry taskEntry = taskBuilder.builderTask();
                if (taskEntry != null) {
                    taskEntryMap.put(taskEntry.getId(), taskEntry);
                    taskEntry.start();
                }
            }
        }
    }
}
