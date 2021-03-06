package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.Config;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.ConfigService;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:  系统配置管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 16:45:54
 */
@Controller
@RequestMapping("/admin/configAdmin")
public class ConfigAction extends BaseAction
{

    @Resource
    ConfigService configService;

    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse reponse)
    {
        form = normalize(request);
        Config config = new Config();
        BeanHelper.mapToBean(form, config);

        String siteno = getLoginSiteNo();
        config.setSiteNo(siteno);
        config.setIsSystem(0);
        configService.addConfig(config);
        addLog("添加配置信息", "添加配置[name=" + config.getName() + "]成功");

        return super.add(request, reponse);
    }

    /**
     * 添加配置信息
     *
     * @return
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/config/add_config.jsp");
    }

    /**
     * 列出所有的系统配置信息
     *
     * @return
     */
    @RequestMapping("/doDefault.action")
    public String doDefault(HttpServletRequest request)
    {
        int curPage = this.getIntParameter("page");
        String keyword = getStrParameter("keyword");
        String siteno = getLoginSiteNo();
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        DBPage<DynaModel> page = configService.getPageData(curPage, SysConfig.getRowOfPage(), keyword, siteno);
        dataMap.put("page", page);
        request.setAttribute("data", dataMap);
        return "/WEB-INF/views/config/list_config.jsp";
    }

    /**
     * 删除配置信息
     *
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++) {
            configService.deleteConfig(idArray[i]);
            addLog("删除配置信息", "删除配置信息[id=" + idArray[i] + "]");
        }
        return super.delete(request, response);
    }

    /**
     * 编辑配置信息
     *
     * @return
     */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = getIntParameter("id");
        Config config = configService.findConfigById(id);
        BeanHelper.beanToMap(config, form);
        mv.setViewName("/WEB-INF/views/config/edit_config.jsp");
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 重新读入所有配置信息到内存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/reload.action")
    public Result doReload(HttpServletResponse response)
    {
        SysConfig.getInstance();
        SysConfig.loadConfig();
        Result result = new Result();
        result.setErrorInfo("重新读入配置文件到内存成功！");
        return result;
    }

    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse reponse)
    {
        form = normalize(request);
        Config config = new Config();
        BeanHelper.mapToBean(form, config);

        configService.updateConfig(config);
        addLog("编辑配置信息", "编辑配置信息[name=" + config.getName() + "]成功");

        return super.edit(request, reponse);
    }

}
