package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Log;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 描述:  系统日志管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-11
 * 创建时间: 14:47:03
 */
@Controller
@RequestMapping("/admin/logAdmin")
public class LogAction extends BaseAction
{

    @Resource
    LogService logService;

    /**
     * 列出相应的日志信息
     *
     * @return
     */
    @RequestMapping("/doDefault.action")
    public String doDefault(Model model)
    {
        //获得当前页
        int curPage = this.getIntParameter("page");
        //获得关键字
        String keyword = getStrParameter("keyword");
        //获得ID
        String uid = getStrParameter("uid");
        curPage = (curPage <= 0) ? 1 : curPage;
        //对关键字进行特殊处理
        keyword = StringHelper.trim(keyword);
        uid = StringHelper.trim(uid);
        //获得登陆的站点
        String siteno = getLoginSiteNo();
        DBPage<Log> page = logService.getPageData(curPage, SysConfig.getRowOfPage(), siteno, keyword, uid);
        dataMap.put("page", page);

        model.addAttribute("data", dataMap);
        return "/WEB-INF/views/log/list_log.jsp";
    }

    /**
     * 删除日志信息
     *
     * @return
     */
    @SuppressWarnings("finally")
    @ResponseBody
    @RequestMapping("/delete.action")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Result doDelete()
    {
        Result result = new Result();
        try {
            int[] idArray = getIntArrayParameter("id");
            for (int i = 0; i < idArray.length; i++) {
                logService.deleteLog(idArray[i]);
            }
            MESSAGE = "删除日志成功";
        } catch (Exception e) {
            MESSAGE = "删除日志失败";
            result.setErrorNo(-1);
            throw new Exception();
        } finally {
            addLog("删除日志", MESSAGE);
            result.setErrorInfo(MESSAGE);
            return result;
        }
    }

    /**
     * 删除全部日志
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteAll.action")
    public Result doDeleteAll()
    {
        Result result = new Result();
        logService.deleteAllLog();
        MESSAGE = "删除所有日志成功";
        addLog("删除所有日志", MESSAGE);
        result.setErrorInfo(MESSAGE);
        return result;
    }
}
