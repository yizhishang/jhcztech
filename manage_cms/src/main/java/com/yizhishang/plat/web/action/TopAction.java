package com.yizhishang.plat.web.action;

import com.yizhishang.base.util.SessionHelper;
import com.yizhishang.base.util.UserHelper;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.system.SysLibrary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-26
 * 创建时间: 12:08:38
 */
@Controller
@RequestMapping("/admin/topAdmin")
public class TopAction extends BaseAction
{

    /**
     * 缺省的操作(function=""时调用)
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/default.action")
    public String doDefault(Model model)
    {
        List list = SysLibrary.getSecurityCatalogTree(getSession());
        dataMap.put("rootMenus", list);
        dataMap.put("userName", UserHelper.getUserName());

        //允许访问的站点信息
        List sites = (List) SessionHelper.getObject(Constants.USER_ALL_SITENO, getSession());
        dataMap.put("sites", sites);

        //当前站点编号
        String siteno = getSiteNo();
        dataMap.put("siteNo", siteno);

        model.addAttribute("data", dataMap);

        //获得相关数据并放主dataMap中
        return "/WEB-INF/views/top.jsp";
    }
}
