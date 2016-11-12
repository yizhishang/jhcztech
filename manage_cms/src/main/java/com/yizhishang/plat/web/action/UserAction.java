package com.yizhishang.plat.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.security.SecurityHelper;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Role;
import com.yizhishang.plat.domain.Site;
import com.yizhishang.plat.domain.User;
import com.yizhishang.plat.domain.UserPasswordLog;
import com.yizhishang.plat.service.BranchService;
import com.yizhishang.plat.service.RoleService;
import com.yizhishang.plat.service.SiteService;
import com.yizhishang.plat.service.UserExcelServeice;
import com.yizhishang.plat.service.UserPasswordLogService;
import com.yizhishang.plat.service.UserService;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:  系统用户管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-8
 * 创建时间: 14:04:39
 */
@Controller
@RequestMapping("/admin/userAdmin")
public class UserAction extends BaseAction
{
    
    private final Logger logger = LoggerFactory.getLogger(UserAction.class);
    
    @Resource
    BranchService branchService;
    
    @Resource
    RoleService roleService;
    
    @Resource
    SiteService siteService;
    
    @Resource
    UserPasswordLogService userPasswordLogService;
    
    @Resource
    UserService userService;
    
    @RequestMapping("add.action")
    public @ResponseBody
    Result add(HttpServletRequest request)
    {
        Result result = new Result(-1);
        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        
        if (StringHelper.isEmpty(form.getString("uid")))
        {
            logger.error("用户标识不能为空");
            MESSAGE = "用户标识不能为空";
            result.setErrorInfo(MESSAGE);
            return result;
        }
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "用户名称不能为空";
            logger.error(MESSAGE);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        if (StringHelper.isEmpty(form.getString("password")))
        {
            MESSAGE = "用户密码不能为空";
            logger.error(MESSAGE);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        if (userService.isUserExist(form.getString("uid").trim()))
        {
            MESSAGE = "相同标志的用户已经存在";
            logger.error(MESSAGE);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        
        //获得user对象
        User user = new User();
        //将所有表单信息存放到user里面。user对象实际上是一个javabean，只有set和get方法
        BeanHelper.mapToBean(form, user);
        user.setUid(user.getUid().trim());
        
        /**
        * 修改站点，首先需要判断用户可能会有多个站点的登陆权限，如果存在多个站点话，就用特殊符号“|”来分隔。而最后一个值后面
        *则不需要该特殊符号，所以用subString方法将最后一个值后面的符号去掉。
        */
        //获得站点。对获得多个站点的值进行处理
        //String siteno = this.getStrParameter("site").substring(0, this.getStrParameter("site").length() - 1);
        String siteno = getSiteNo();
        /**
        * 获得分配给该用户所有站点名称，多个站点站点名称使用特殊符号“、”来分隔，而最后一个值后面
        *则不需要该特殊符号，所以用subString方法将最后一个值后面的符号去掉。
        */
        //		String sitename = this.getStrParameter("name").substring(0, this.getStrParameter("name").length() - 1);
        //将处理后的值存放到user里面
        user.setSiteNo(siteno);
        //敬爱那个处理后的值存放到user对象里
        //		user.setSiteName(sitename);
        user.setPassword(SecurityHelper.getMD5of32Str(form.getString("password")));
        //站点是否对用户开放，如果开放就改变该字段的状态
        user.setState(User.STATE_OPEN);
        user.setIsSystem(Integer.parseInt(form.getString("isSystem")));
        user.setLoginTimes(0);
        user.setLastTime("");
        user.setCreateBy(getUID());
        user.setCreateDate(DateHelper.formatDate(new Date()));
        user.setModifiedBy(getUID());
        user.setModifiedDate(DateHelper.formatDate(new Date()));
        userService.addUser(user);
        
        MESSAGE = "添加用户[UID=" + user.getUid() + "]";
        addLog("添加用户", MESSAGE);
        
        //增加用户密码修改记录
        UserPasswordLog log = new UserPasswordLog();
        log.setCreateBy(user.getUid());
        log.setPassword(user.getPassword());
        log.setDescription("修改密码成功");
        userPasswordLogService.addLog(log);
        logger.info(MESSAGE);
        result.setErrorNo(0);
        result.setErrorInfo(MESSAGE);
        return result;
        
    }
    
    /**
    * 添加用户
    *
    * @return
    */
    @Override
    @RequestMapping("doAdd.action")
    public ModelAndView doAdd()
    {
        //查找所有营业部信息
        List<DynaModel> dataList = branchService.getAll();
        dataMap.put("branchList", dataList);
        //查找所有站点信息
        List<Site> siteList = siteService.getAllSite();
        dataMap.put("siteList", siteList);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/add_user.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 关闭用户
    * @return
    */
    @RequestMapping("close.action")
    public @ResponseBody
    Result doClose()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            userService.closeUser(idArray[i]);
            addLog("关闭用户", "关闭用户[id=" + idArray[i] + "]");
        }
        Result result = new Result();
        result.setErrorInfo("操作成功");
        return result;
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * 列出所有的用户信息
    * @return
    */
    @SuppressWarnings("rawtypes")
	@Override
    @RequestMapping("doDefault.action")
    public ModelAndView doDefault()
    {
        int curPage = this.getIntParameter("page");
        String keyword = getStrParameter("keyword");
        String branchno = getStrParameter("branchno");//营业部编号
        String roleid = getStrParameter("roleid");//角色编号
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        String siteno = getSiteNo();
        DBPage<Map> page = userService.getPageData(curPage, SysConfig.getRowOfPage(), siteno, keyword, branchno, roleid);
        /**
        * 查询用户所属的角色名称
        *  by 20130522
        */
        if (page != null)
        {
            List<Map> dataList = page.getData();
            for (Iterator<Map> iter = dataList.iterator(); iter.hasNext();)
            {
                DynaModel userData = (DynaModel) iter.next();
                List<DynaModel> roleInfo = roleService.findUserRoleById(userData.getInt("user_id"));
                if (roleInfo != null)
                {
                    String roleStr = "";
                    for (int i = 0; i < roleInfo.size(); i++)
                    {
                        DynaModel roleData = (DynaModel) roleInfo.get(i);
                        roleStr += roleData.getString("roleno");
                        if (i < roleInfo.size() - 1)
                        {
                            roleStr += ",";
                        }
                    }
                    userData.set("role_str", roleStr);
                }
                
            }
        }
        
        //查询所有营业部
        List<DynaModel> branchs = branchService.getAll();
        if (branchs != null)
        {
            dataMap.put("branchs", branchs);
        }
        //查询所有角色
        List<Role> roles = roleService.findRoleBySiteno(getSiteNo());
        if (roles != null)
        {
            dataMap.put("roles", roles);
        }
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/list_user.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 删除用户
    * @return
    */
    @ResponseBody
    @RequestMapping("delete.action")
    public Result doDelete()
    {
        int[] idArray = getIntArrayParameter("id");
        User user = null;
        for (int i = 0; i < idArray.length; i++)
        {
            user = userService.findUserById(idArray[i]);
            userService.deleteUser(idArray[i]);
            addLog("删除用户", "删除用户[id=" + idArray[i] + "]");
            
            //删除用户的密码修改记录
            userPasswordLogService.deleteLogByUserId(user.getUid());
        }
        Result result = new Result();
        result.setErrorInfo("删除成功");
        
        return result;
    }
    
    /**
    * 编辑用户信息
    * @return
    */
    @Override
    @RequestMapping("doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        //获得所有营业部信息
        List<DynaModel> dataList = branchService.getAll();
        dataMap.put("branchList", dataList);
        
        //根据用户登陆的编号，查询用户的信息
        int id = getIntParameter("id");
        User user = null;
        //判断如果用户编号不为0的时候
        if (id > 0)
        {
            user = userService.findUserById(id);
            BeanHelper.beanToMap(user, form);
        }
        
        //查找所有站点信息
        //		SiteService siteService = (SiteService) getService(SiteService.class);
        //		List siteList = siteService.getAllSite();
        //		dataMap.put("siteList", siteList);
        
        //获得用户所拥有的站点
        //		getRequest().setAttribute("siteno", user.getSiteNo());
        
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/edit_user.jsp");
        mv.addObject("data", dataMap);
        mv.addObject("form", form);
        return mv;
    }
    
    @RequestMapping("doEditPassword.action")
    public ModelAndView doEditPassword()
    {
        int id = this.getIntParameter("id");
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/edit_password.jsp");
        mv.addObject("userid", id);
        return mv;
        
    }
    
    /**
    * 开放用户
    * @return
    */
    @RequestMapping("open.action")
    public @ResponseBody
    Result doOpen()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            userService.openUser(idArray[i]);
            addLog("开放用户", "开放用户[id=" + idArray[i] + "]");
            
            //清除该用户当日密码输错次数
            logService.clearErrorPwdNum(idArray[i]);
        }
        Result result = new Result();
        result.setErrorInfo("操作成功");
        return result;
    }
    
    //文件生成
    public String doUserExcel(HttpServletRequest request) throws Exception
    {
        String keyword = getStrParameter("keyword");//查询条件
        String loanNo = getStrParameter("loanNo");//排序条件
        UserExcelServeice service = new UserExcelServeice();
        keyword = StringHelper.trim(keyword);
        loanNo = StringHelper.trim(loanNo);
        List<DynaModel> list = service.getUser(keyword, loanNo);
        exportToExcel(list, "user", request);
        return "";
    }
    
    @RequestMapping("userList.action")
    public ModelAndView doUserList()
    {
        int group_id = getIntParameter("group_id", 0);
        int roleId = getIntParameter("roleId", 0);
        String keyword = getStrParameter("keyword");
        String loanNo = getStrParameter("loanNo");
        int curPage = this.getIntParameter("page");
        
        curPage = (curPage <= 0) ? 1 : curPage;
        loanNo = StringHelper.trim(loanNo);
        keyword = StringHelper.trim(keyword);
        
        DBPage<DynaModel> page = null;
        if (group_id != 0 && roleId == 0)
        {
            page = userService.getPageData(curPage, 16, group_id, "", keyword, loanNo, false);
        }
        else if (group_id == 0 && roleId != 0)
        {
            page = userService.getPageDataNotInRole(curPage, 16, "", keyword, roleId);
        }
        else
        {
            page = userService.getPageDataNotInRole(curPage, 16, "", keyword, roleId);
        }
        dataMap.put("page", page);
        
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/role_user_list.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 查看用户信息
    * @return
    */
    public String doView()
    {
        int id = getIntParameter("id");
        if (id > 0)
        {
            User user = userService.findUserById(id);
            BeanHelper.beanToMap(user, form);
            String branchname = branchService.findBranchByNo(user.getBranchNo()).getString("branchname");
            form.put("branchName", branchname);
        }
        return "/WEB-INF/views/user/views_user.jsp";
    }
    
    @RequestMapping("edit.action")
    public @ResponseBody
    Result edit(HttpServletRequest request)
    {
        
        DynaForm form = normalize(request);
        Result result = new Result(-1);
        /**
        * 修改站点，首先需要判断用户可能会有多个站点的登陆权限，如果存在多个站点话，就用特殊符号“|”来分隔。而最后一个值后面
        *则不需要该特殊符号，所以用subString方法将最后一个值后面的符号去掉。
        */
        //获得站点
        //String siteno = this.getStrParameter("site");
        /**
        * 获得分配给该用户所有站点名称，多个站点站点名称使用特殊符号“、”来分隔，而最后一个值后面
        *则不需要该特殊符号，所以用subString方法将最后一个值后面的符号去掉。
        */
        //String sitename = this.getStrParameter("name");
        //对获得多个站点的值进行处理
        //siteno = siteno.substring(0, siteno.length() - 1);
        //对获得多个站点的值进行处理
        //sitename = sitename.substring(0, sitename.length() - 1);
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "用户名称不能为空";
            addLog("编辑用户失败", MESSAGE);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        //获得user对象
        User user = new User();
        //将所有表单信息存放到user里面。user对象实际上是一个javabean，只有set和get方法
        BeanHelper.mapToBean(form, user);
        //将处理后的值存放到user里面
        //user.setSiteNo(siteno);
        //获得系统管理员权限
        user.setIsSystem(form.getInt("isSystem"));
        //将处理后的值存放到user里面
        //user.setSiteName(sitename);
        user.setModifiedBy(getUID());
        user.setModifiedDate(DateHelper.formatDate(new Date()));
        userService.updateUser(user);
        addLog("编辑用户", "编辑用户[id=" + user.getId() + "]");
        
        result.setErrorNo(0);
        MESSAGE = "编辑用户成功";
        result.setErrorInfo(MESSAGE);
        return result;
    }
    
    @RequestMapping("editPassword.action")
    public @ResponseBody
    Result editPassword(HttpServletRequest request)
    {
        DynaForm form = normalize(request);
        String newPassword = form.getString("password");
        int userId = form.getInt("user_id");
        
        User user = userService.findUserById(userId);
        
        User newUser = new User();
        newUser.setPassword(SecurityHelper.getMD5of32Str(newPassword));
        newUser.setId(userId);
        //清空用户最后登录时间
        newUser.setLastTime("");
        userService.updateUser(newUser);
        
        if (SysConfig.getInt("system.errorPwdNum") > 0)
        {
            //清除该用户当日密码输错次数
            logService.clearErrorPwdNum(userId);
        }
        
        this.addLog(user.getUid(), "main", "修改密码", "修改密码成功");
        addLog("修改密码", "修改用户ID[" + user.getUid() + "]密码成功");
        
        //增加用户密码修改记录
        UserPasswordLog log = new UserPasswordLog();
        log.setCreateBy(user.getUid());
        log.setPassword(user.getPassword());
        log.setDescription("修改密码成功");
        userPasswordLogService.addLog(log);
        
        Result result = new Result();
        result.setErrorInfo("修改密码成功");
        return result;
        
    }
    
    private void exportToExcel(List<DynaModel> list, String value, HttpServletRequest request) throws Exception
    {
        if (list == null)
        {
            return;
        }
        //      String filePath = "/opt/zhql/xls/test"+today+".xls";
        //String filePath = "e:/zhql/xls/";
        //		String filePath = "c:/xls/test"+today+".xls";
        
        //获得当前系统时间，做为导出文件名
        Calendar calCurrent = Calendar.getInstance();
        int hour = calCurrent.get(Calendar.HOUR);
        int minute = calCurrent.get(Calendar.MINUTE);
        int second = calCurrent.get(Calendar.SECOND);
        
        /***
         * yuezz  add this for some exceptions in debug~ can't find file!!! at 2015-8-11 15:07:42
         */
        //String fileName = "/opt/web/xls/" + value +"(" + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second) +")" + ".xls";
        String fileName = value + "(" + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second) + ")" + ".xls";
        
        //		String fileDir = ServletActionContext.getServletContext().getRealPath("/") + "xls" + File.separator;
        String fileDir = request.getSession().getServletContext().getRealPath("/") + "xls" + File.separator;
        File dir = new File(fileDir);
        File file = new File(fileDir + fileName);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        if (!file.exists())
        {
            file.createNewFile();
        }
        /*
         * end yuezz add
         */
        
        dataMap.put("export", fileName);
        OutputStream os = new FileOutputStream(file);
        WritableWorkbook book = Workbook.createWorkbook(os);
        WritableSheet ws = book.createSheet("用户数据", 0);
        String[] Items = { "用户标识", "用户名称", "电子邮箱", "登录次数", "最后登录时间", "用户状态" };
        
        WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        Label label;
        for (int i = 0; i < Items.length; i++)
        {
            label = new Label(i, 0, Items[i], wcf);
            ws.addCell(label);
        }
        
        /*label = new Label(0,1,"adsf",wcf);
          ws.addCell(label);*/
        DynaModel row;
        
        for (int i = 1; i <= list.size(); i++)
        {
            row = (DynaModel) list.get(i - 1);
            //System.out.println("##############"+row);
            label = new Label(0, i, row.getString("uid2"), wcf);
            ws.addCell(label);
            
            label = new Label(1, i, row.getString("name"), wcf);
            ws.addCell(label);
            
            label = new Label(2, i, row.getString("email"), wcf);
            ws.addCell(label);
            
            label = new Label(3, i, row.getString("login_times"), wcf);
            ws.addCell(label);
            
            label = new Label(4, i, row.getString("last_time"), wcf);
            ws.addCell(label);
            
            String state = row.getString("state");
            if (state.equals("0"))
            {
                label = new Label(5, i, "关闭", wcf);
            }
            else
            {
                label = new Label(5, i, "开放", wcf);
            }
            
            ws.addCell(label);
        }
        book.write();
        book.close();
        getResponse().setContentType("text/plain");
        getResponse().setHeader("Location", fileName);
        getResponse().setHeader("Cache-Control", "max-age=" + 3);
        getResponse().setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream outStream = getResponse().getOutputStream();
        byte[] bytes = new byte[1024 * 4];
        InputStream inStream = new FileInputStream(file);
        int count = 0;
        while ((count = inStream.read(bytes)) != -1)
        {
            outStream.write(bytes, 0, count);
        }
        
        os.flush();
        os.close();
        os = null;
        outStream.flush();
        outStream.close();
        inStream.close();
        inStream = null;
        outStream = null;
        
        // File f=new File("/opt/web/xls");
        // File f=new File("c:/xls");
        File subs[] = dir.listFiles();
        for (int i = 0; i <= subs.length - 1; i++)
        {
            //subs[i].isDirectory();
            subs[i].delete();//删除子文件夹本身
        }
        
    }
}
