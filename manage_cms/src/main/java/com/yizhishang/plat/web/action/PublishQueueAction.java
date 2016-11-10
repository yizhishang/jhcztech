package com.yizhishang.plat.web.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.PublishQueueService;

/**
 * 描述: 模板发布队列
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 yuanyj@yizhishang.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:26:22
 */
@Controller
@RequestMapping("/admin/publishQueueAdmin")
public class PublishQueueAction extends BaseAction
{
    
    @Resource
    PublishQueueService publishQueueService;
    
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        String startDate = getStrParameter("startDate");//开始时间
        String endDate = getStrParameter("endDate");//结束时间
        
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        DBPage page = publishQueueService.findPublishQueue(curPage, SysConfig.getRowOfPage(), startDate, endDate, getSiteNo());
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/template_queue/list_template_queue.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/doDelete.action")
    public Result doDelete()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            publishQueueService.delete(idArray[i]);
            addLog("删除发布队列", "删除发布队列[id=" + idArray[i] + "]");
        }
        result.setErrorNo(0);
        result.setErrorInfo("删除发布队列!");
        return result;
    }
    
    /**
    * 删除全部发布队列
    * @return
    */
    @ResponseBody
    @RequestMapping("/doDeleteAll.action")
    public Result doDeleteAll()
    {
        publishQueueService.deleteAllLog();
        addLog("删除发布队列", "删除所有发布队列");
        result.setErrorNo(0);
        result.setErrorInfo("删除所有发布队列!");
        return result;
    }
    
    /**
    * 描述：重置发布队列
    * 作者：袁永君
    * 时间：Nov 7, 2013 12:35:49 PM
    * @return
    */
    @ResponseBody
    @RequestMapping("/doResetQueue.action")
    public Result doResetQueue()
    {
        publishQueueService.resetExceptionQueue();
        addLog("重置发布队列", "重置所有发布队列");
        result.setErrorNo(0);
        result.setErrorInfo("重置发布队列!");
        return result;
    }
}
