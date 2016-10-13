package com.jhcz.plat.template.publish;

import java.util.List;

import org.apache.log4j.Logger;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.ConvertHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.queue.WorkCallBack;
import com.jhcz.plat.domain.Catalog;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.service.PublishLogService;
import com.jhcz.plat.service.PublishQueueService;
import com.jhcz.plat.util.QueueThreadProperty;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-30
 * 创建时间: 14:58:42
 */
public class PublishCallBack implements WorkCallBack
{
	
	private static Logger logger = Logger.getLogger(PublishCallBack.class);
	
	private static PublishLogService logService = (PublishLogService) ServiceLocator.getService(PublishLogService.class);
	
	private static PublishQueueService publishQueueService = (PublishQueueService) ServiceLocator.getService(PublishQueueService.class);
	
	private static CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
	
	    /**
     * 发布队列具体内容
     *
     * @param queueId
     */
	private void publish(int queueId)
	{
		String cmdStr = publishQueueService.findCmdStrByQueueId(queueId);
		
		if (!StringHelper.isEmpty(cmdStr))
		{
			String[] itemArray = StringHelper.split(cmdStr, ":");
			if (itemArray.length == 2)
			{
				String flag = itemArray[0];
				int id = ConvertHelper.strToInt(itemArray[1]);
                if (flag.equals("A")) //发布文章
				{
					publishArticle(queueId, id);
				}
                else if (flag.equals("T")) //发布模板
				{
					publishTemplate(queueId, id);
				}
                else if (flag.equals("C")) //发布栏目
				{
					publishCatalog(queueId, id);
				}
                else if (flag.equals("CR")) //发布栏目及其所有子栏目(递归发布)
				{
					publishCatalogRecursion(queueId, id);
				}
			}
		}
	}
	
	    /**
     * 发布文章
     *
     * @param queueId   队列ID
     * @param articleId 文章ID
     */
	private void publishArticle(int queueId, int articleId)
	{
		ArticlePromulgate promulgate = new ArticlePromulgate(queueId, articleId);
		promulgate.publish();
	}
	
	    /**
     * 发布栏目
     *
     * @param queueId   队列ID
     * @param catalogId 栏目ID
     */
	private void publishCatalog(int queueId, int catalogId)
	{
		CatalogPromulgate promulgate = new CatalogPromulgate(queueId, catalogId);
		promulgate.publish();
	}
	
	    /**
     * 发布某栏目及其所有子栏目
     *
     * @param queueId   队列ID
     * @param catalogId 栏目ID
     */
	private void publishCatalogRecursion(int queueId, int catalogId)
	{
        //发布栏目
		publishCatalog(queueId, catalogId);
		
        //发布其所有子目录
		List<Catalog> dataList = catalogService.findChildrenById(catalogId);
		for (int i = 0; i < dataList.size(); i++)
		{
			Catalog catalog = dataList.get(i);
			publishCatalogRecursion(queueId, catalog.getId());
		}
	}
	
	    /**
     * 发布某模板
     *
     * @param queueId    队列ID
     * @param templateId 模板ID
     */
	private void publishTemplate(int queueId, int templateId)
	{
		TemplatePromulgate promulgate = new TemplatePromulgate(queueId, templateId);
		promulgate.publish();
	}
	
	@Override
    public void run(Object workObject)
	{
		int queueId = 0;
		try
		{
            //得到需要发布的队列的ID
			queueId = ((Integer) workObject).intValue();
			
            //更新为正在处理状态
			publishQueueService.updateState(queueId, 1);
			
            //发布队列中某内容
			publish(queueId);
			
            //更新为处理完成状态
			//publishQueueService.updateState(queueId, 2);
			publishQueueService.delete(queueId);
		}
		catch (Exception ex)
		{
            String description = "发布[queueId=" + queueId + "]的内容出现错误";
			logger.error(description, ex);
			
			logService.add(queueId, description);
            //删除队列
			if (queueId > 0)
			{
				publishQueueService.delete(queueId);
			}
		}
		
        //记录线程工作的日志
		int isThreadQueueLog = Configuration.getInt("publish.isThreadQueueLog");
		if (isThreadQueueLog == 1)
		{
			QueueThreadProperty.setPropertyString(Thread.currentThread().getName(), String.valueOf(System.currentTimeMillis()));
		}
	}
}
