package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.TemplateVarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 描述: 模板变量管理
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:27:52
 */
@Controller
@RequestMapping("/admin/templateVarAdmin")
public class TemplateVarAction extends BaseAction
{

    @Resource
    TemplateVarService templateVarService;

    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request)
    {
        form = normalize(request);
        DynaModel data = new DynaModel();
        data.putAll(form);

        String siteno = getSiteNo();
        data.set("state", "1");
        data.set("siteno", siteno);
        data.set("create_by", getUID());
        data.set("create_date", DateHelper.formatDate(new Date()));

        templateVarService.addTemplateVar(data);
        addLog("添加模板变量", "添加模板变量" + data.getString("form.item_name"));

        result.setErrorNo(0);
        result.setErrorInfo("操作成功！");
        return result;
    }

    /**
     * 描述:  批量删除模板变量
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 下午04:25:02
     *
     * @return String
     * @throws
     */
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++) {
            templateVarService.deleteTemplateVar(idArray[i]);
        }
        addLog("删除模板变量", "删除模板变量");

        return super.delete(request, response);
    }

    /**
     * 描述:  添加模板变量
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 下午03:42:14
     *
     * @return String
     * @throws
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/template_var/add_template_var.jsp");
    }

    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        String keyword = getStrParameter("keyword");

        int curPage = getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;

        @SuppressWarnings(
                "rawtypes") DBPage<Map> page = templateVarService.findTemplateVar(curPage, SysConfig.getRowOfPage(),
                keyword, getSiteNo());
        dataMap.put("page", page);
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/template_var/list_template_var.jsp");
        return mv;
    }

    /**
     * 描述:  编辑模板变量
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 下午04:04:41
     *
     * @return String
     * @throws
     */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = getIntParameter("id");
        DynaModel data = templateVarService.findTemplateVarById(id, getSiteNo());
        if (data != null) {
            form.putAll(data);
        } else {
            ScriptHelper.alert(getResponse(), "模板变量不存在或已被删除！", "close");
            return null;
        }
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/template_var/edit_template_var.jsp");
        return mv;
    }

    /**
     * 描述:  批量更新模板变量
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 下午04:20:25
     *
     * @return String
     * @throws
     */
    @Override
    @ResponseBody
    @RequestMapping("/editState.action")
    public Result doEditState(HttpServletRequest request, HttpServletResponse reponse)
    {
        int state = getIntParameter("state");
        int[] idArray = getIntArrayParameter("id");
        DynaModel data = null;
        for (int i = 0; i < idArray.length; i++) {
            data = new DynaModel();
            data.set("id", idArray[i]);
            data.set("state", state);
            templateVarService.editTemplateVar(data);
        }
        return super.doEditState(request, reponse);
    }

    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse reponse)
    {
        form = normalize(request);
        DynaModel data = new DynaModel();
        data.putAll(form);

        templateVarService.editTemplateVar(data);

        return super.edit(request, reponse);
    }
}
