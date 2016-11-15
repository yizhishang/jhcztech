package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.PublishLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 描述: 模板发布日志
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 yuanyj@yizhishang.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:24:51
 */
@Controller
@RequestMapping("/admin/publishLogAdmin")
public class PublishLogAction extends BaseAction
{

    @Resource
    PublishLogService publishLogService;

    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        String startDate = getStrParameter("startDate");
        String endDate = getStrParameter("endDate");

        int curPage = getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;

        DBPage<DynaModel> page = publishLogService.findPublishLog(curPage, SysConfig.getRowOfPage(), startDate,
                endDate);
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/publish_log/list_publish_log.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }

    /**
     * 描述:
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 上午09:47:01
     *
     * @return String
     * @throws
     */
    @ResponseBody
    @RequestMapping("/doDelete.action")
    public Result doDelete()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++) {
            publishLogService.delete(idArray[i]);
            addLog("删除发布日志", "删除发布日志[id=" + idArray[i] + "]");
        }
        result.setErrorNo(0);
        result.setErrorInfo("删除发布日志!");
        return result;
    }

    /**
     * 删除全部日志
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/doDeleteAll.action")
    public Result doDeleteAll()
    {
        publishLogService.deleteAllLog();
        addLog("删除发布日志", "删除所有发布日志");
        result.setErrorNo(0);
        result.setErrorInfo("删除所有发布日志!");
        return result;
    }

}
