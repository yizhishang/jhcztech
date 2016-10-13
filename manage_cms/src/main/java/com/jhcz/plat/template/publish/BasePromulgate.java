/*
 * Copyright (c) 2010 Your Corporation. All Rights Reserved.
 */

package com.jhcz.plat.template.publish;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.plat.service.PublishLogService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 13:51:18
 */
public class BasePromulgate
{
    /**
     * 记录发布日志信息
     *
     * @param queueId
     * @param description
     */
    public void addPublishLogInfo(int queueId, String description)
    {
        PublishLogService logService = (PublishLogService) ServiceLocator.getService(PublishLogService.class);
        logService.add(queueId, description);
    }

    /**
     * 记录发布的文章信息
     *
     * @param queueId
     * @param filePath
     */
    /*
    public void addPublishFileInfo(int queueId, String filePath)
    {
        PublishFileService fileService = (PublishFileService) ServiceLocator.getService(PublishFileService.class);
        fileService.add(queueId, filePath);
    }
    */

    /**
     * 返回当前运选行模式
     *
     * @return
     */
    public int getRunMode()
    {
        return Configuration.getInt("system.runMode", 0);
    }
}
