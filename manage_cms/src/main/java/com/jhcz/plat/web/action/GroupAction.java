package com.jhcz.plat.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.config.SysConfig;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.BeanHelper;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.ScriptHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.domain.Group;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.domain.User;
import com.jhcz.plat.service.GroupService;
import com.jhcz.plat.web.form.DynaForm;

/**
 * 描述: 用户组管理
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-27
 * 创建时间: 上午10:22:19
 */
@Controller
@RequestMapping("/admin/groupAdmin")
public class GroupAction extends BaseAction
{
    
    @Resource
    GroupService groupService;
    
    @ResponseBody
    @RequestMapping("add.action")
    public Result add(HttpServletRequest request)
    {
        
        Result result = new Result();
        DynaForm form = normalize(request);
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "名字不能为空";
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        if (groupService.isGroupExist(form.getString("name")))
        {
            MESSAGE = "相同标志的组已经存在";
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        Group group = new Group();
        BeanHelper.mapToBean(form, group);
        String siteno = getLoginSiteNo();
        group.setSiteNo(siteno);
        group.setModified_by(getUID());
        group.setCreate_date(DateHelper.formatDate(new Date()));
        group.setModified_date(DateHelper.formatDate(new Date()));
        group.setCreate_by(getUID());
        groupService.addGroup(group);
        
        MESSAGE = "添加组[name=" + group.getName() + "]";
        addLog("添加组", MESSAGE);
        result.setErrorInfo(MESSAGE);
        return result;
    }
    
    /**
    * 添加组
    * @return
    */
    @Override
    @RequestMapping("doAdd.action")
    public ModelAndView doAdd()
    {
        mv.setViewName("/WEB-INF/views/group/add_group.jsp");
        return mv;
    }
    
    /**
     * 添加组用户
     * @return String
     */
    @ResponseBody
    @RequestMapping("addGroupUser.action")
    public Result doAddGroupUser()
    {
        int group_Id = getIntParameter("group_id");
        String userIdStr = getStrParameter("userIdStr");
        try
        {
            userIdStr = URLDecoder.decode(userIdStr, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(userIdStr, "|");
        List<Object> userList = groupService.getGroupUser(group_Id);
        DataRow existIdMap = new DataRow();
        for (Iterator<Object> iter = userList.iterator(); iter.hasNext();)
        {
            User user = (User) iter.next();
            existIdMap.set(String.valueOf(user.getId()), user.getId());
        }
        
        while (tokenizer.hasMoreTokens())
        {
            String userId = tokenizer.nextToken();
            if (!(existIdMap.containsKey(userId))) //若当前用户不在当前角色的用户，则需要添加此用户
            {
                groupService.addGroupUser(group_Id, Integer.parseInt(userId), getSiteNo());
            }
        }
        addLog("添加组用户", "添加组[id=" + group_Id + "]的用户[" + userIdStr + "]");
        
        Result result = new Result();
        MESSAGE = "添加用户成功";
        result.setErrorInfo(MESSAGE);
        return result;
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * 列出所有的组信息
    * @return
    */
    @Override
    @RequestMapping("doDefault.action")
    public ModelAndView doDefault()
    {
        int curPage = getIntParameter("page");
        String keyword = getStrParameter("keyword");
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        //获得站点编号
        String siteno = getLoginSiteNo();
        DBPage page = groupService.getPageData(curPage, SysConfig.getRowOfPage(), siteno, keyword);
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/group/list_group.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 删除组
    * @return
    */
    @RequestMapping("delete.action")
    public @ResponseBody
    Result doDelete()
    {
        
        if (isPostBack())
        {
            int[] idArray = getIntArrayParameter("id");
            for (int i = 0; i < idArray.length; i++)
            {
                groupService.deleteGroup(idArray[i]);
                groupService.deleteGroupUser(idArray[i]);
                addLog("删除组", "删除组[id=" + idArray[i] + "]");
                addLog("删除组下所有用户", "删除组[id=" + idArray[i] + "]下的所有用户成功");
            }
        }
        Result result = new Result();
        result.setErrorInfo("删除成功");
        
        return result;
    }
    
    /**
    * 删除该组用户
    * @return
    */
    @RequestMapping("deleteGroupUser.action")
    public @ResponseBody
    Result doDeleteGroupUser()
    {
        Result result = new Result();
        int groupId = getIntParameter("group_id");
        int[] idArray = getIntArrayParameter("id");
        String str = "";
        for (int i = 0; i < idArray.length; i++)
        {
            groupService.deleteGroupUser(groupId, idArray[i]);
            str = str + idArray[i] + ",";
            
        }
        addLog("删除组用户", "删除组[id=" + groupId + "]的用户[" + str + "]");
        MESSAGE = "删除组用户成功";
        result.setErrorInfo(MESSAGE);
        return result;
    }
    
    /**
    * 编辑组信息
    *
    * @return
    */
    @Override
    @RequestMapping("doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();
        int id = getIntParameter("id");
        Group group = groupService.findGroupById(id);
        if (group != null)
        {
            BeanHelper.beanToMap(group, form);
            mv.addObject("form", form);
        }
        else
        {
            ScriptHelper.alert(response, "用户组信息不存在，请勿非法操作", "close");
            return mv;
        }
        mv.setViewName("/WEB-INF/views/group/edit_group.jsp");
        return mv;
    }
    
    /**
    * 查看组的所有用户
    * @return
    */
    @RequestMapping("listGroupUser.action")
    public ModelAndView doListGroupUser(HttpServletResponse reponse)
    {
        ModelAndView mv = new ModelAndView();
        
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        int group_id = getIntParameter("group_id");
        String keyword = getStrParameter("keyword");
        Group group = groupService.findGroupById(group_id);
        DBPage page = groupService.getPageData(curPage, SysConfig.getRowOfPage(), getSiteNo(), group_id, keyword);
        if (group != null)
        {
            dataMap.put("groupId", new Integer(group_id));
            dataMap.put("groupName", group.getName());
            dataMap.put("page", page);
            mv.addObject("data", dataMap);
        }
        else
        {
            ScriptHelper.alert(reponse, "该组不存在或已经被删除", "close");
            return mv;
        }
        mv.setViewName("/WEB-INF/views/group/group_user.jsp");
        return mv;
    }
    
    /**
    * 查看组信息
    * @return
    */
    public String doView()
    {
        int id = getIntParameter("id");
        if (id > 0)
        {
            Group group = groupService.findGroupById(id);
            BeanHelper.beanToMap(group, form);
        }
        return "/WEB-INF/views/group/views_group.jsp";
    }
    
    @RequestMapping("edit.action")
    public @ResponseBody
    Result edit(HttpServletRequest request)
    {
        //对提交上来的form进行处理
        Result result = new Result();
        DynaForm form = normalize(request);
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "组名称不能为空";
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        
        Group group = new Group();
        BeanHelper.mapToBean(form, group);
        group.setModified_by(getUID());
        group.setModified_by(DateHelper.formatDate(new Date()));
        groupService.updateGroup(group);
        
        addLog("编辑组", "编辑组[name=" + group.getName() + "]");
        MESSAGE = "编辑组成功";
        result.setErrorInfo(MESSAGE);
        return result;
    }
    
}
