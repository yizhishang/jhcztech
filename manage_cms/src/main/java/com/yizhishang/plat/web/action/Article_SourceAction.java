package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Article_Source;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.Article_SourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 描述:
 * 版权:	 Copyright (c) 2005
 * 版本:	 1.0
 * 创建日期: 2016-03-21
 * 创建时间: 10:26:36
 */
@Controller
@RequestMapping("/admin/articleSourceAction")
public class Article_SourceAction extends BaseAction
{

    @Resource
    Article_SourceService article_SourceService;

    /**
     * 缺省的操作(function=""时调用)
     * 列出所有的信息来源
     *
     * @return
     */
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        int curPage = this.getIntParameter("page");
        String keyword = getStrParameter("keyword");
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        DBPage<DynaModel> page = article_SourceService.getPageData(curPage, SysConfig.getRowOfPage(), getSiteNo(),
                keyword);
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/article_source/list_article_source.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }

    /**
     * 添加信息来源
     *
     * @return
     */
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/article_source/add_article_source.jsp");
    }

    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        form = normalize(request);
        Article_Source as = new Article_Source();
        BeanHelper.mapToBean(form, as);
        as.setSiteno("main");
        as.setCreate_by(this.getUID());
        as.setCreate_date(DateHelper.formatDate(new Date()));
        as.setModified_by(this.getUID());
        as.setSiteno(this.getSiteNo());
        as.setModified_date(DateHelper.formatDate(new Date()));
        article_SourceService.addArticle_Source(as);
        addLog("添加信息来源", "添加信息来源[name=" + as.getName() + "]");
        return super.add(request, response);
    }

    /**
     * 查看信息来源详细信息
     *
     * @return
     */
    @RequestMapping("/view.action")
    public ModelAndView doView()
    {
        int id = this.getIntParameter("id");
        Article_Source as = new Article_Source();
        as = article_SourceService.findArticle_SourceById(id);
        BeanHelper.beanToMap(as, form);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/article_source/view_article_source.jsp");
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 编辑信息来源
     *
     * @return
     */
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit()
    {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/article_source/edit_article_source.jsp");
        int id = getIntParameter("id");
        Article_Source as = article_SourceService.findArticle_SourceById(id);
        if (as != null) {
            BeanHelper.beanToMap(as, form);
            mv.addObject("form", form);
        } else {
            ScriptHelper.alert(getResponse(), "信息源不存在，请勿非法操作", "close");
        }
        return mv;
    }

    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        form = normalize(request);
        Article_Source as = new Article_Source();
        BeanHelper.mapToBean(form, as);
        as.setModified_by(this.getUID());
        as.setModified_date(DateHelper.formatDate(new Date()));
        article_SourceService.updateArticle_Source(as);
        addLog("编辑信息来源", "编辑信息来源[name=" + as.getName() + "]");
        return super.edit(request, response);
    }

    /**
     * 删除信息来源
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] id = this.getIntArrayParameter("id");
        for (int i = 0; i < id.length; i++) {
            article_SourceService.deleteArticle_Source(id[i]);
            addLog("删除信息来源", "删除信息来源[id=" + id[i] + "]");
        }
        return super.delete(request, response);
    }
}
