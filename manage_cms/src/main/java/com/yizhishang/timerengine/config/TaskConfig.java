package com.yizhishang.timerengine.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.yizhishang.base.util.StringHelper;


public class TaskConfig
{
    
    //单子属性对象
    //    private static TaskConfig instance = new TaskConfig();

    //保存每一个任务的配置文件
    private static ArrayList<HashMap<String, String>> taskList = new ArrayList<HashMap<String, String>>();

    private static Logger logger = LoggerFactory.getLogger(TaskConfig.class);
    private static String CONFIG_FILE_NAME = "tasks.xml";

    static
    {
        loadConfig();
    }

    public static List<HashMap<String, String>> getTaskList()
    {
        return taskList;
    }


    /**
     * 读入配置文件
     *
     * @param
     */
    @SuppressWarnings("rawtypes")
    private static void loadConfig()
    {
        try
        {
            InputStream is = TaskConfig.class.getResourceAsStream("/" + CONFIG_FILE_NAME);
            SAXBuilder builder = new SAXBuilder();

            Document doc = builder.build(is);
            Element tasksElement = doc.getRootElement();
            @SuppressWarnings("unchecked")
            List<Element> taskElementList = tasksElement.getChildren("task");

            Iterator<Element> taskElementIter = taskElementList.iterator();
            while (taskElementIter.hasNext())
            {
                Element taskElement = taskElementIter.next();
                HashMap<String, String> taskPropertyMap = new HashMap<String, String>();

                //获得任务的ID
                String taskId = StringHelper.n2s(taskElement.getAttributeValue("id"));
                //若ID为空，则跳过该任务配置
                if (StringHelper.isEmpty(taskId))
                {
                    continue;
                }
                taskPropertyMap.put("id", taskId);

                //获得所有的属性
                List taskPropertyList = taskElement.getChildren();
                Iterator taskPropertyElementIter = taskPropertyList.iterator();
                while (taskPropertyElementIter.hasNext())
                {
                    Element propertyElement = (Element) taskPropertyElementIter.next();
                    String name = propertyElement.getName();
                    String value = StringHelper.n2s(propertyElement.getTextTrim());
                    taskPropertyMap.put(name, value);
                }
                taskList.add(taskPropertyMap);
            }
        }
        catch (IOException ex)
        {
            logger.error("", ex);
        }
        catch (JDOMException ex)
        {
            logger.error("", ex);
        }
    }
}