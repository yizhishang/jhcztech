package com.yizhishang.plat.template.publish;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.service.TemplateService;
import com.yizhishang.plat.system.SysLibrary;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.TemplateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-6
 * 创建时间: 15:56:28
 */
public class TemplatePromulgate extends BasePromulgate
{

    private static Logger logger = LoggerFactory.getLogger(TemplatePromulgate.class);

    private static TemplateService templateService = (TemplateService) SpringContextHolder.getBean("templateService");

    /**
     * 需要发布的模板的ID
     */
    private int templateId = 0;

    private int queueId = 0;

    public TemplatePromulgate(int queueId, int templateId)
    {
        this.templateId = templateId;
        this.queueId = queueId;
    }

    /**
     * 发布某模板
     */
    public void publish()
    {

        //得到需要发布的模板内容
        Template template = templateService.findTemplateById(templateId);
        if (template == null) {
            String description = "找不到模板，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }

        String encoding = template.getEncoding();//发布文件使用的编码
        if (StringHelper.isEmpty(encoding)) {
            encoding = Configuration.getString("system.encoding");
        }

        int state = template.getState();
        if (state == 0) {
            String description = "模板为无效状态，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }

        String content = template.getContent();
        if (StringHelper.isEmpty(content)) {
            String description = "模板内容为空，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }

        //处理模板，获得解析后的内容
        int catalogId = template.getCatalogId();
        Context context = new Context();
        context.setCatalogId(catalogId);

        TemplateParser parser = new TemplateParser(context);
        String result = parser.parse(content);

        String errMsg = context.getErrMsg();

        if (StringHelper.isEmpty(errMsg) && !template.getFilePath().startsWith(template.getSiteNo(), 1)) {
            errMsg = "模板ID" + template.getId() + "错误 ，文件只允许生成在[" + template.getSiteNo() + "]站点下";
        }

        if (!StringHelper.isEmpty(errMsg)) //有错误产生,记录日志
        {
            String filePath = SysLibrary.getTemplateUrlPath(templateId);
            errMsg = "文件[" + filePath + "]生成出错，错误信息为：\r\n" + errMsg;
            addPublishLogInfo(queueId, errMsg);
        }

        //两种情况重写文件(第一种:开发模式 第二种：正式模式且发布正常，没有错误)
        if (getRunMode() == 0 || StringHelper.isEmpty(errMsg)) {
            //写入对应的文件
            String filePath = SysLibrary.getTemplateStorePath(templateId);
            FileHelper.createNewFile(filePath);
            FileHelper.writeToFile(filePath, result, encoding);
        }

        //记录到发布文件表中
        //filePath = SysLibrary.getTemplateUrlPath(templateId);
        //addPublishFileInfo(queueId, filePath);
    }

}
