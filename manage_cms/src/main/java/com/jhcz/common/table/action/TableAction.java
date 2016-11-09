package com.jhcz.common.table.action;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.DateHelper;
import com.jhcz.common.table.service.TableService;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.web.action.BaseAction;
import com.jhcz.plat.web.form.DynaForm;

@Controller
@RequestMapping("/admin/tableAdmin")
public class TableAction extends BaseAction
{
    
    private DynaForm form = new DynaForm();
    
    private final HashMap<String, Object> dataMap = new HashMap<String, Object>();
    
    @Resource
    TableService tableService;
    
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        String table_name_en = getStrParameter("table_name_en", "").toLowerCase();
        String table_name_ch = getStrParameter("table_name_ch", "").toLowerCase();
        
        DBPage page = tableService.getPageData(curPage, 20, table_name_en, table_name_ch);
        
        dataMap.put("page", page);
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/tableconfig/table/list.jsp");
        return mv;
    }
    
    @Override
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        normalize(form);
        DataRow data = new DataRow();
        data.putAll(form);
        data.set("create_time", DateHelper.formatDate(new Date(), "yyyy-MM-dd"));
        data.set("create_by", getUID());
        data.set("update_time", DateHelper.formatDate(new Date(), "yyyy-MM-dd"));
        data.set("update_by", getUID());
        data.set("name_en", data.getString("name_en").toLowerCase());
        tableService.add(data);
        return super.add(request, response);
    }
    
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        mv.setViewName("/WEB-INF/views/tableconfig/table/add.jsp");
        return mv;
    }
    
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = this.getIntParameter("id", 0);
        DataRow bean = new DataRow();
        if (id > 0)
        {
            bean = tableService.load(id);
        }
        this.setAttribute("bean", bean);
        mv.setViewName("/WEB-INF/views/tableconfig/table/edit.jsp");
        return mv;
    }
    
    @Override
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        form = normalize(request);
        DataRow data = new DataRow();
        data.putAll(form);
        data.set("update_time", DateHelper.formatDate(new Date(), "yyyy-MM-dd"));
        data.set("update_by", getUID());
        tableService.update(data);
        return super.edit(request, response);
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            tableService.delte(idArray[i]);
        }
        return super.delete(request, response);
    }
}
