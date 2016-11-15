package com.yizhishang.plat.web.action;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.*;
import com.yizhishang.plat.domain.*;
import com.yizhishang.plat.service.*;
import com.yizhishang.plat.web.form.DynaForm;
import jxl.Workbook;
import jxl.write.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 描述:  系统角色管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 修改      袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-9
 * 创建时间: 16:05:50
 */
@Controller
@RequestMapping("/admin/roleAdmin")
public class RoleAction extends BaseAction
{

    private static Logger logger = LoggerFactory.getLogger(RoleAction.class);

    @Resource
    CatalogService catalogService;

    @Resource
    EnumService enumService;

    @Resource
    ManageCatalogService manageCatalogService;

    @Resource
    RoleService roleService;

    @Resource
    SiteService siteService;

    @RequestMapping("/add.action")
    public
    @ResponseBody
    Result add(HttpServletRequest request)
    {

        Result result = new Result(-1);

        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        if (StringHelper.isEmpty(form.getString("roleNo"))) {
            logger.error("角色标识不能为空");
            result.setErrorInfo("角色标识不能为空");
            return result;
        }
        if (StringHelper.isEmpty(form.getString("name"))) {
            logger.error("角色名称不能为空");
            result.setErrorInfo("角色名称不能为空");
            return result;
        }
        if (roleService.isRoleExist(form.getString("roleNo"))) {
            logger.error("相同标志的角色已经存在");
            result.setErrorInfo("相同标志的角色已经存在");
            return result;
        }

        Role role = new Role();
        BeanHelper.mapToBean(form, role);

        //获得用户登陆后的站点信息
        String siteno = getLoginSiteNo();
        role.setSiteNo(siteno);
        role.setIsSystem(0);
        role.setCreateBy(getUID());
        role.setCreateDate(DateHelper.formatDate(new Date()));
        role.setModifiedBy(getUID());
        role.setModifiedDate(DateHelper.formatDate(new Date()));
        roleService.addRole(role);
        addLog("添加角色", "添加角色[name=" + role.getName() + "]");

        result.setErrorNo(0);
        result.setErrorInfo("添加角色成功!");
        return result;

    }

    /**
     * 添加角色
     *
     * @return
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/role/add_role.jsp");
    }

    /**
     * 添加角色用户
     *
     * @return
     */
    @RequestMapping("addRoleUser.action")
    public
    @ResponseBody
    Result doAddRoleUser()
    {
        //获得角色的编号
        Result result = new Result();

        int roleId = getIntParameter("roleId");
        String userIdStr = getStrParameter("userIdStr");
        try {
            userIdStr = URLDecoder.decode(userIdStr, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result.setErrorNo(-1);
            result.setErrorInfo("用户id传参失败");
            return result;
        }
        StringTokenizer tokenizer = new StringTokenizer(userIdStr, "|");
        String siteno = getLoginSiteNo();
        List<DynaModel> userList = roleService.getRoleUser(roleId, siteno);
        DynaModel existIdMap = new DynaModel();
        for (Iterator<DynaModel> iter = userList.iterator(); iter.hasNext(); ) {
            User user = (User) iter.next();
            existIdMap.set(String.valueOf(user.getId()), user.getId());
        }

        while (tokenizer.hasMoreTokens()) {
            String userId = tokenizer.nextToken();
            if (!(existIdMap.containsKey(userId))) //若当前用户不在当前角色的用户，则需要添加此用户
            {
                roleService.addRoleUser(roleId, Integer.parseInt(userId), siteno);
            }
        }
        this.addLog("添加角色用户成功", "添加角色用户" + userIdStr + "成功");

        result.setErrorInfo("添加角色用户成功");
        return result;
    }

    /**
     * 描述：查询栏目树
     * 作者：袁永君
     * 时间：May 25, 2013 2:39:58 PM
     *
     * @return
     */
    @RequestMapping("/doCatalogTree.action")
    public ModelAndView doCatalogTree()
    {
        ModelAndView mv = new ModelAndView();
        int type = getIntParameter("type");
        int roleId = getIntParameter("roleId");
        String siteNo = getStrParameter("siteNo");
        int roleType = getIntParameter("role_type");//0:查询系统栏目 1:查询资讯中心栏目

        String result = "";
        if (0 == roleType) {
            result = getRoleTree(type, roleId, siteNo);
        } else if (1 == roleType) {
            result = getArticleRoleTree(type, roleId, siteNo);
        }

        mv.addObject("result", result);
        mv.setViewName("/WEB-INF/views/role/catalogTree.jsp");
        return mv;
    }

    /**
     * 缺省的操作(function=""时调用)
     * 列出所有的角色信息
     *
     * @return
     */
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        mv.setViewName("/WEB-INF/views/role/default.jsp");
        return mv;
    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping("delete.action")
    public
    @ResponseBody
    Result doDelete()
    {
        Result result = new Result(-1);
        int roleId = getIntParameter("roleId");

        //查找角色信息
        Role role = roleService.findRoleById(roleId, "");
        if (role != null) {
            if (role.getIsSystem() == 1) {
                result.setErrorInfo("【" + role.getRoleno() + "】为系统角色，不能删除！");
                return result;
            }
            roleService.deleteRole(roleId);
            roleService.deleteRoleRight(roleId);
            roleService.deleteRoleUser(roleId);
            addLog("删除角色", "删除角色[id=" + roleId + "]");

            result.setErrorNo(0);
            result.setErrorInfo("删除角色成功!");
            return result;
        } else {
            result.setErrorInfo("角色信息不存在！");
            return result;
        }
    }

    /**
     * 删除角色用户
     *
     * @return
     */
    @RequestMapping("deleteRoleUser.action")
    public
    @ResponseBody
    Result doDeleteRoleUser()
    {
        Result result = new Result();

        int roleId = getIntParameter("roleId");
        int[] idArray = getIntArrayParameter("id");
        String strUserId = "";
        for (int i = 0; i < idArray.length; i++) {
            roleService.deleteRoleUser(roleId, idArray[i]);
            strUserId = strUserId + idArray[i] + "|";
        }

        this.addLog("删除角色用户成功", "删除角色权限下" + strUserId + "成功");

        result.setErrorInfo("删除角色用户成功");
        return result;
    }

    /**
     * 编辑角色信息
     *
     * @return
     */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/role/edit_role.jsp");
        //首先声明变量，是用来存放用户所选择的登陆站点
        //		String siteno = getLoginSiteNo();
        //获得角色的编号
        int id = getIntParameter("id");
        //便查找该角色的信息
        Role role = roleService.findRoleById(id, "");
        if (role != null) {
            BeanHelper.beanToMap(role, form);
            mv.addObject("form", form);
        } else {
            ScriptHelper.alert(response, "该角色不存在或已经被删除", "close");
            return null;
        }
        return mv;
    }

    /**
     * 描述:  编辑栏目权限
     * 作者:
     * 创建日期: 2009-12-31
     * 创建时间: 下午01:09:01
     *
     * @return String
     * @throws
     */
    @RequestMapping("/doEditArticleRoleRight.action")
    public ModelAndView doEditArticleRoleRight(HttpServletRequest request)
    {
        List<Site> siteList = siteService.getAllSite();
        dataMap.put("siteList", siteList);
        mv.setViewName("/WEB-INF/views/role/edit_article_right.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }

    /**
     * 编辑角色权限
     *
     * @return
     */
    @RequestMapping("/doEditRoleRight.action")
    public ModelAndView doEditRoleRight(HttpServletRequest request)
    {
        //        int roleId = RequestHelper.getInt(request, "roleId");
        //        if (roleId == 0)
        //        {
        //            return null;
        //        }
        //
        //			//查找角色信息
        //			Role role = service.findRoleById(roleId, "");
        //			if (role != null)
        //			{
        //				BeanHelper.beanToMap(role, form);
        //			}
        //
        //			List dataList = service.findCatalogRight(roleId, siteNo);
        //			dataMap.put("dataList", dataList);
        //
        List<Site> siteList = siteService.getAllSite();
        dataMap.put("siteList", siteList);

        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/role/edit_role_right.jsp");
        return mv;
    }

    @RequestMapping("/doEditSiteRoleRight.action")
    public ModelAndView doEditSiteRoleRight(HttpServletRequest request)
    {
        int roleId = RequestHelper.getInt(request, "roleId");
        //查找角色信息
        Role role = roleService.findRoleById(roleId, "");
        if (role != null) {
            BeanHelper.beanToMap(role, form);
        }

        List<Map<String, Object>> siteList = roleService.findSiteRight(roleId);
        dataMap.put("dataList", siteList);

        mv.addObject("form", form);
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/role/edit_role_site_right.jsp");

        return mv;
    }

    /**
     * @return
     * @描述：导出角色下的所有用户
     * @作者：袁永君
     * @时间：2011-1-19 下午05:26:05
     */
    public String doExportAllRoleUser()
    {
        String siteno = getSiteNo();

        List<DynaModel> excelList = new ArrayList<DynaModel>();
        DynaModel excelData = null;

        //查询所有角色信息
        List<Role> allRoleList = roleService.findRoleBySiteNo(siteno);
        for (int i = 0; i < allRoleList.size(); i++) {
            Role role = (Role) allRoleList.get(i);
            //查询角色下的所有用户
            List<DynaModel> roleUser = roleService.getRoleUser(role.getId(), siteno);
            for (int j = 0; j < roleUser.size(); j++) {
                User user = (User) roleUser.get(j);
                excelData = new DynaModel();
                excelData.set("roleno", role.getRoleno());//角色标识
                excelData.set("roleName", role.getName());//角色名称
                excelData.set("roleCreateDate", role.getCreateDate());//角色创建时间
                excelData.set("userUid", user.getUid());//用户标识
                excelData.set("userName", user.getName());//用户名称
                excelData.set("email", user.getEmail());//电子邮箱
                excelData.set("loginTimes", user.getLoginTimes());//登录次数
                excelData.set("lastTime", user.getLastTime());//最后登录时间
                excelData.set("userState", user.getState());//用户状态
                excelList.add(excelData);
            }
        }

        try {
            OutputStream os = getResponse().getOutputStream();// 取得输出流
            getResponse().reset();// 清空输出流

            String filename = URLEncoder.encode("角色用户导出", "UTF-8");
            getResponse().setHeader("Content-disposition", "attachment; filename=" + filename + ".xls");// 设定输出文件头
            getResponse().setContentType("application/msexcel");// 定义输出类型

            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("sheet1", 0); // sheet名称

            String[] Items = {"角色标识", "角色名称", "创建时间", "用户标识", "用户名称", "电子邮箱", "登录次数", "最后登录时间", "用户状态"};
            WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            Label label;
            for (int i = 0; i < Items.length; i++) {
                label = new Label(i, 0, Items[i], wcf);
                wsheet.addCell(label);
            }

            for (int i = 1; i <= excelList.size(); i++) {
                DynaModel data = (DynaModel) excelList.get(i - 1);

                label = new Label(0, i, data.getString("roleno"), wcf);
                wsheet.addCell(label);

                label = new Label(1, i, data.getString("roleName"), wcf);
                wsheet.addCell(label);

                label = new Label(2, i, data.getString("roleCreateDate"), wcf);
                wsheet.addCell(label);

                label = new Label(3, i, data.getString("userUid"), wcf);
                wsheet.addCell(label);

                label = new Label(4, i, data.getString("userName"), wcf);
                wsheet.addCell(label);

                label = new Label(5, i, data.getString("email"), wcf);
                wsheet.addCell(label);

                label = new Label(6, i, data.getString("loginTimes"), wcf);
                wsheet.addCell(label);

                label = new Label(7, i, data.getString("lastTime"), wcf);
                wsheet.addCell(label);

                int state = data.getInt("userState");
                label = new Label(8, i, (state == 0) ? "关闭" : "开放", wcf);
                wsheet.addCell(label);

            }

            //			wsheet.mergeCells(0, 1, 0, 2);
            //			wsheet.mergeCells(0, 2, 0, 2);
            //主体内容生成结束
            wbook.write(); //写入文件
            wbook.close();
            os.close(); //关闭流
        } catch (Exception ex) {
            logger.error("", ex);
        }

        return NONE;
    }

    /**
     * @return
     * @描述：导出角色下的所有用户
     * @作者：袁永君
     * @时间：2011-1-5 下午05:06:14
     */
    public String doExportListRoleUser()
    {
        //获得角色的编号
        int roleId = getIntParameter("roleId");

        //首先声明变量，是用来存放用户所选择的登陆站点
        String siteno = getLoginSiteNo();
        //查找角色信息
        Role role = roleService.findRoleById(roleId, siteno);

        List<DynaModel> roleUser = roleService.getRoleUser(roleId, siteno);

        try {
            OutputStream os = getResponse().getOutputStream();// 取得输出流
            getResponse().reset();// 清空输出流
            String filename = URLEncoder.encode("角色用户(" + role.getName() + ")", "UTF-8");
            getResponse().setHeader("Content-disposition", "attachment; filename=" + filename + ".xls");// 设定输出文件头
            getResponse().setContentType("application/msexcel");// 定义输出类型

            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("sheet1", 0); // sheet名称

            String[] Items = {"用户标识", "用户名称", "电子邮箱", "登录次数", "最后登录时间", "用户状态"};
            WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            Label label;
            for (int i = 0; i < Items.length; i++) {
                label = new Label(i, 0, Items[i], wcf);
                wsheet.addCell(label);
            }

            User user = null;

            for (int i = 1; i <= roleUser.size(); i++) {
                user = (User) roleUser.get(i - 1);

                label = new Label(0, i, user.getUid(), wcf);
                wsheet.addCell(label);

                label = new Label(1, i, user.getName(), wcf);
                wsheet.addCell(label);

                label = new Label(2, i, user.getEmail(), wcf);
                wsheet.addCell(label);

                label = new Label(3, i, String.valueOf(user.getLoginTimes()), wcf);
                wsheet.addCell(label);

                label = new Label(4, i, user.getLastTime(), wcf);
                wsheet.addCell(label);

                int state = user.getState();
                label = new Label(5, i, (state == 0) ? "关闭" : "开放", wcf);
                wsheet.addCell(label);

            }
            //主体内容生成结束
            wbook.write(); //写入文件
            wbook.close();
            os.close(); //关闭流
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return NONE;
    }

    /**
     * 显示左侧的页面
     *
     * @return
     */
    @RequestMapping("/doLeft.action")
    public String doLeft()
    {
        return "/WEB-INF/views/role/left.jsp";
    }

    @RequestMapping("/doList.action")
    public ModelAndView doList()
    {

        //首先声明变量，是用来存放用户所选择的登陆站点
        //String siteno = getLoginSiteNo();

        String keyword = getStrParameter("keyword");

        //获得当前的页面数据
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        //获得角色的编号
        int roleId = getIntParameter("roleId", 1);

        //查找角色信息
        Role role = roleService.findRoleById(roleId, "");
        DBPage<DynaModel> page = roleService.getPageData(curPage, 10, "", roleId, keyword);
        if (role != null) {
            BeanHelper.beanToMap(role, form);
            dataMap.put("page", page);
        }

        ModelAndView mv = new ModelAndView("/WEB-INF/views/role/list_role.jsp");
        mv.addObject("data", dataMap);
        mv.addObject("form", form);
        mv.addObject("roleId", roleId);

        return mv;
    }

    /**
     * 列出角色用户
     *
     * @return
     */
    public String doListRoleUser()
    {
        //获得当前的页面数据
        int curPage = this.getIntParameter("page");
        //获得角色的编号
        int roleId = getIntParameter("roleId");
        curPage = (curPage <= 0) ? 1 : curPage;

        //查找角色信息
        Role role = roleService.findRoleById(roleId, "");
        DBPage<DynaModel> page = roleService.getPageData(curPage, SysConfig.getRowOfPage(), "", roleId, "");
        if (role != null) {
            dataMap.put("roleId", new Integer(roleId));
            dataMap.put("roleName", role.getName());
            dataMap.put("page", page);
        } else {
            ScriptHelper.alert(getResponse(), "该角色不存在或已经被删除", "close");
            return NONE;
        }
        return "listRoleUser";
    }

    @RequestMapping(value = "/showTree.action", produces = "text/xml;charset=GBK")
    public
    @ResponseBody
    String doShowTree(HttpServletResponse response)
    {
        StringBuffer buffer = new StringBuffer();

        response.setContentType("text/xml;charset=GBK");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", -10);

        buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n");
        buffer.append("<tree>\n");

        String siteno = getLoginSiteNo();
        List<Role> roles = roleService.findRoleBySiteNo(siteno);
        for (Iterator<Role> iter = roles.iterator(); iter.hasNext(); ) {
            Role role = (Role) iter.next();
            buffer.append("   <tree text=\"" + role.getRoleno() + "(" + role.getName() + ")\" " +
                    "target=\"roleRightFrame\" action=\"doList.action?&amp;roleId=" + role.getId() + "\" value=\"" +
                    role.getId() + "\" src=\"\"  oncontextmenu=\"true\" />\n");
        }

        buffer.append("</tree>");
        return buffer.toString();
    }

    /**
     * 查看角色信息
     *
     * @return
     */
    @RequestMapping("/doView.action")
    public ModelAndView doView()
    {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/role/views_role.jsp");
        //站点编号
        String siteno = getLoginSiteNo();
        //获得角色编号
        int id = getIntParameter("id");
        if (id > 0) {
            //查找角色信息
            Role role = roleService.findRoleById(id, siteno);
            BeanHelper.beanToMap(role, form);
            mv.addObject("form", form);
        }
        return mv;
    }

    @RequestMapping("/edit.action")
    public
    @ResponseBody
    Result edit(HttpServletRequest request)
    {
        Result result = new Result();

        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        if (StringHelper.isEmpty(form.getString("name"))) {
            logger.error("角色名称不能为空");
            result.setErrorNo(-1);
            result.setErrorInfo("角色名称不能为空");
            return result;
        }
        Role role = new Role();
        BeanHelper.mapToBean(form, role);
        role.setModifiedBy(getUID());
        role.setModifiedDate(DateHelper.formatDate(new Date()));
        roleService.updateRole(role);
        addLog("编辑角色", "编辑角色[name=" + role.getName() + "]");

        result.setErrorInfo("编辑角色成功！");
        return result;
    }

    @ResponseBody
    @RequestMapping("/editArticleRoleRight.action")
    public void EditArticleRoleRight(HttpServletRequest request, HttpServletResponse response)
    {

        int roleId = this.getIntParameter("roleId");
        String userIdStr = RequestHelper.getString(request, "userIdStr");
        String siteNo = RequestHelper.getString(request, "siteNo");

        roleService.editCatalogRoleRights(userIdStr, roleId, siteNo);
        this.addLog("修改角色栏目权限成功", "修改" + roleId + "的文档权限成功");

        String successPage = RequestHelper.getString(request, "successPage");
        ScriptHelper.redirect(response, successPage);
    }

    @ResponseBody
    @RequestMapping("/editRoleRight.action")
    public Result editRoleRight(HttpServletRequest request)
    {
        Result result = new Result();

        int roleId = RequestHelper.getInt(request, "roleId");
        String siteNo = RequestHelper.getString(request, "siteNo");
        String userIdStr = RequestHelper.getString(request, "userIdStr");
        try {
            userIdStr = URLDecoder.decode(userIdStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            userIdStr = "";
        }
        roleService.editRoleRights(userIdStr, roleId, siteNo);
        addLog("修改角色权限成功", "修改[roleId=" + roleId + "]的权限成功");

        result.setErrorInfo("修改角色权限成功");
        return result;
    }

    @RequestMapping("/editSiteRoleRight.action")
    public
    @ResponseBody
    Result editSiteRoleRight()
    {
        Result result = new Result();
        String[] roalRightTree = getStrArrayParameter("id_fun");
        int roleId = this.getIntParameter("roleId");

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < roalRightTree.length; i++) {
            buffer.append(roalRightTree[i]);
            if (i < roalRightTree.length - 1) {
                buffer.append("|");
            }
        }

        roleService.editSiteRoleRights(buffer.toString(), roleId);

        addLog("编辑角色站点权限成功", "编辑角色[roleId=" + roleId + "]成功！");

        result.setErrorInfo("编辑角色站点权限成功！");
        return result;
    }

    /**
     * 描述：
     * 作者：袁永君
     * 时间：May 25, 2013 10:41:03 PM
     *
     * @return
     */
    public JSONArray formatButtonTree(int type, String catalogId, String rightStr)
    {
        JSONArray array = new JSONArray();
        JSONObject jsonObject = null;

        Map<String, String> buttons = new LinkedHashMap<String, String>();
        buttons.put("add", "添加");
        buttons.put("delete", "删除");
        buttons.put("publish", "发布");
        buttons.put("reject", "驳回");
        buttons.put("hot", "推荐");
        buttons.put("head", "置顶");
        buttons.put("copy", "复制");

        int inx = 0;
        for (Iterator<String> iter = buttons.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String value = buttons.get(key);

            jsonObject = new JSONObject();
            jsonObject.put("id", catalogId + "_" + inx);
            jsonObject.put("pId", catalogId);
            jsonObject.put("name", value);
            jsonObject.put("funName", key);
            /**
             * 设置已拥有的权限为选中状态
             */
            if (StringHelper.isNotEmpty(rightStr) && rightStr.indexOf(key) != -1) {
                if (type == 1) {
                    jsonObject.put("checked", "true");
                }
                array.add(jsonObject);
            } else {
                if (type == 1) {
                    array.add(jsonObject);
                }
            }
            inx++;
        }

        return array;
    }

    public String getArticleRoleTree(int type, int roleId, String siteNo)
    {
        JSONArray jsonArray = new JSONArray();

        DynaModel rightMap = roleService.getDocRight(roleId, siteNo);

        List<DynaModel> catalogs = catalogService.findRouteCatalogById(1, siteNo);
        if (catalogs != null) {
            for (Iterator<DynaModel> iter = catalogs.iterator(); iter.hasNext(); ) {
                DynaModel catalog = (DynaModel) iter.next();
                String catalogId = catalog.getString("catalog_id");
                String pid = catalog.getString("parent_id");
                String name = catalog.getString("name");
                int childrennum = catalog.getInt("childrennum");

                JSONObject obj = new JSONObject();
                obj.put("id", catalogId);
                obj.put("pId", pid);
                obj.put("name", name);

                if (type == 0) {
                    //只显示拥有权限的栏目树
                    if (rightMap.containsKey(catalogId)) {
                        jsonArray.add(obj);
                    }
                } else {
                    //显示所有的栏目树
                    if (rightMap.containsKey(catalogId)) {
                        obj.put("checked", "true");
                    }
                    jsonArray.add(obj);
                }

                if (childrennum == 0) {
                    String rightStr = rightMap.getString(catalogId);
                    jsonArray.addAll(formatButtonTree(type, catalogId, rightStr));
                }
            }
        }
        //		ResponseHelper.print(getResponse(), jsonArray);
        //		return NONE;
        return jsonArray.toString();

    }

    public String getRoleTree(int type, int roleId, String siteNo)
    {
        JSONArray jsonArray = new JSONArray();

        HashSet<String> roleRight = roleService.getRoleCatalogRight(roleId, siteNo);

        List<ManageCatalog> catalogs = manageCatalogService.findCatalogLikeRoute(1, "");
        if (catalogs != null) {
            for (Iterator<ManageCatalog> iter = catalogs.iterator(); iter.hasNext(); ) {
                ManageCatalog catalog = (ManageCatalog) iter.next();
                String id = String.valueOf(catalog.getId());
                String pid = String.valueOf(catalog.getParentId());
                String name = catalog.getName();

                JSONObject obj = new JSONObject();
                obj.put("id", id);
                obj.put("pId", pid);
                obj.put("name", name);

                if (type == 0) {
                    //只显示拥有权限的栏目树
                    if (roleRight.contains(id)) {
                        jsonArray.add(obj);
                    }
                } else {
                    //显示所有的栏目树
                    if (roleRight.contains(id)) {
                        obj.put("checked", "true");
                    }
                    jsonArray.add(obj);
                }
            }
        }
        //		ResponseHelper.print(getResponse(), jsonArray);
        //		return NONE;
        return jsonArray.toString();
    }
}
