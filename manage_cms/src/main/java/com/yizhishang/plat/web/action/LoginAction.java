package com.yizhishang.plat.web.action;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.IPHelper;
import com.yizhishang.base.util.SessionHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.domain.*;
import com.yizhishang.plat.service.*;
import com.yizhishang.plat.service.exception.FistLoginModiPasswordException;
import com.yizhishang.plat.service.exception.LoginFailedException;
import com.yizhishang.plat.service.exception.PasswordErrorException;
import com.yizhishang.plat.util.LoggerUtil;
import com.yizhishang.plat.web.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 描述: LoginAction.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-12
 * 创建时间: 上午10:53:33
 */
@Controller
@RequestMapping("/loginAdmin")
public class LoginAction extends BaseAction
{

    @Resource
    LogService logService;

    @Resource
    ManageCatalogService manageCatalogService;

    @Resource
    UserPasswordLogService passwordLogService;

    @Resource
    RoleService roleService;

    @Resource
    SiteService siteService;

    @Resource
    UserService userService;

    /**
     * 描述：根据用户角色返回应用程序菜单,其中issystemuser为1的用户可获得所有功能菜单
     * 作者：袁永君
     * 时间：2010-1-8 下午02:26:53
     *
     * @param roleString
     * @return
     */
    private List<ManageCatalog> createSecureCatalog(HashSet<String> roleString, User user, String siteno)
    {
        List<ManageCatalog> result = new ArrayList<ManageCatalog>();
        if (user.getIsSystem() > 0) {
            //result = catalogService.findAllChildrenCatalogsById(1, siteno);
            result = manageCatalogService.findAllChildrenCatalogsById(1, "");
        } else {
            //List list = catalogService.findManageCatalogLikePId(1, siteno);
            List<ManageCatalog> list = manageCatalogService.findManageCatalogLikePId(1, "");
            for (Iterator<ManageCatalog> iter = list.iterator(); iter.hasNext(); ) {
                ManageCatalog manageCatalog = (ManageCatalog) iter.next();
                if (roleString.contains(String.valueOf(manageCatalog.getId()))) {
                    result.add(manageCatalog);
                }
            }
        }
        return result;
    }

    @RequestMapping("/login.action")
    public String doLogin(Model model)
    {
        List<Site> list = siteService.getAllSite();
        HashMap<String, Object> map = Maps.newHashMap();
        map.putAll(list.get(0).toMap());
        List<Map<String, Object>> list1 = Lists.newArrayList();
        list1.add(map);
        model.addAttribute("list", list);
        return "/WEB-INF/views/login.jsp";
    }

    @RequestMapping("/loginOut.action")
    public String loginOut()
    {
        addLog("退出系统", "退出系统成功");
        SessionHelper.removeAllAttribute(getSession());
        return "redirect:/";
    }

    @RequestMapping("/reLogin.action")
    public String reLogin()
    {
        return "/WEB-INF/views/reLogin.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/loginValidate.action", method = RequestMethod.POST)
    public Result loginValidate(@ModelAttribute(
            "loginForm") LoginForm loginForm, String ticket, HttpServletRequest request)
    {
        Result result = new Result(-1);
        String message = "";
        String uid = "";
        String siteno = "";
        try {
            //验证验证码
            String ticketInSession = (String) getSession().getAttribute(Constants.TICKET);
            if (StringHelper.isEmpty(ticket) || !ticket.equalsIgnoreCase(ticketInSession)) {
                throw new LoginFailedException("验证码不对");
            }

            //获得用户的账号和密码，并对账号密码进行验证；
            uid = loginForm.getName();
            String password = loginForm.getPassword();
            siteno = request.getParameter("siteno");
            User user = userService.login(uid, password);

            HttpSession session = getSession();

            //判断密码是否过期
            int pwdValidity = SysConfig.getInt("system.pwdValidity");
            if (!"admin".equals(uid) && pwdValidity > 0) {
                //String popupModifyPwdMsg = logService.isPasswordValidity(pwdValidity, uid);
                String popupModifyPwdMsg = passwordLogService.isPasswordValidity(pwdValidity, uid);
                if (popupModifyPwdMsg == null) {
                    UserPasswordLog passwordLog = new UserPasswordLog();
                    passwordLog.setCreateBy(user.getUid());
                    passwordLog.setDescription("用户登陆");
                    passwordLog.setPassword(user.getPassword());
                    passwordLogService.addLog(passwordLog);
                }
                if (StringHelper.isNotEmpty(popupModifyPwdMsg)) {
                    session.setAttribute(Constants.POPUP_MODIFYPWD_MESSAGE, popupModifyPwdMsg);
                }
            }

            HashSet<String> siteRights = roleService.findUserSiteRights(user.getId());
            //验证用户登陆的站点是否合法
            if (!"all".equals(user.getSiteNo()) && !(siteRights != null && siteRights.contains(siteno))) {
                throw new LoginFailedException("登陆的站点无此用户");
            }

            //将用户信息保存到session
            saveUserDataSession(session, user, siteno);
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_IP, IPHelper.getIpAddr(request));//增加请求ip地址到session
        } catch (PasswordErrorException ex) {
            if (StringHelper.isNotEmpty(uid) && StringHelper.isNotEmpty(siteno)) {
                addLog(uid, siteno, "系统登录", ex.getMessage());
            }

            int errorPwdNum = SysConfig.getInt("system.errorPwdNum");//Configuration.getInt("system.errorPwdNum");
            // 当日允许输入错密码次数
            if (!"admin".equals(uid) && errorPwdNum > 0) {
                int userErrorPwdNum = logService.getNowErrorPwdNum(uid);
                if (userErrorPwdNum >= errorPwdNum) {
                    userService.closeUser(uid);
                    LoggerUtil.error(this.getClass(), "您已经连续" + errorPwdNum + "次输错密码，您的帐户已被关闭，请与系统管理员联系！");
                } else {
                    LoggerUtil.error(this.getClass(), StringHelper.toScript("您输入的密码不正确！\n您当日已输错" + userErrorPwdNum +
                            "次密码，请检查后谨慎输入！\n如果当日对同一用户名输错" + errorPwdNum + "次密码，系统将自动关闭该用户。"));
                }
            } else {
                LoggerUtil.error(this.getClass(), ex.getMessage());
            }
            message = ex.getMessage();
        } catch (FistLoginModiPasswordException ex) {
            //第一次登录，需要修改密码
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_UID, uid);
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_SITENO, siteno);

            dataMap.put(Constants.POPUP_MODIFYPWD_WINDOW, "1");
            message = ex.getMessage();

        } catch (LoginFailedException ex) {
            LoggerUtil.error(this.getClass(), ex.getMessage());
            message = ex.getMessage();
        }

        if (StringHelper.isNotEmpty(message)) {
            result.setErrorInfo(message);
        } else {
            result.setErrorNo(0);
        }

        return result;
    }

    /**
     * @param session
     * @param user
     * @param siteno
     * @throws LoginFailedException
     * @描述：登录后将用户信息保存到session
     * @作者：袁永君
     * @时间：2011-1-19 下午03:30:17
     */
    public void saveUserDataSession(HttpSession session, User user, String siteno)
    {
        //查找站点信息
        Site site = siteService.findSiteBySiteNO(siteno);

        /************************************** 判断用户权限模块开始 ***********************************************/
        //查询用户具有的权限
        HashSet<String> roleString = roleService.findUserRights(user.getId(), siteno);

        //查询用户具有的栏目权限
        DynaModel CatalogRoleString = roleService.findUserCatalogRights(user.getId(), siteno);

        List<ManageCatalog> menuCatalogs = createSecureCatalog(roleString, user, siteno);
        //如果是超级管理员
        if ("all".equals(user.getSiteNo())) {
            List<Site> list = siteService.getAllSite();
            dataMap.put("list", list);
        }
        /**************************************** 判断用户权限模块结束 ***********************************************/
        Integer[] roleArray = roleService.getUserRole(user.getId());
        //设置相应的会话数据
        session.setAttribute(Constants.ADMIN_UID, user.getUid());//用户编码
        session.setAttribute(Constants.ADMIN_USER_ID, new Integer(user.getId()));//用户loginid
        session.setAttribute(Constants.ADMIN_USER_NAME, user.getName());//用户姓名
        session.setAttribute(Constants.ADMIN_IS_SYSTEM, new Integer(user.getIsSystem()));//是否是系统用户
        session.setAttribute(Constants.ADMIN_SITENO, site.getSiteNo());//当前站点编号
        session.setAttribute(Constants.USER_MENU_CATALOGS, menuCatalogs);//当前应用菜单树
        session.setAttribute(Constants.ADMIN_SITE_IS_MAIN, new Integer(site.getIsMain()));//是否是主站点
        session.setAttribute(Constants.USER_BRANCHNO, user.getBranchNo());//用户的营业部编号
        session.setAttribute(Constants.USER_RIGHT, roleString);//用户权限表
        session.setAttribute(Constants.USER_CATALOG_RIGHT, CatalogRoleString);//用户功能权限表
        session.setAttribute(Constants.USER_ROLE, roleArray);//用户角色
        addLog("登录系统", "登录系统成功");

        /*************************************** 查询我可以使用的站点信息开始 ********************************************/
        List<Site> sites = null;
        if ("all".equals(user.getSiteNo())) {
            //系统超级管理员可以使用所有站点
            sites = siteService.getAllSite();

        } else {
            //允许登录的站点
            HashSet<String> siteRights = roleService.findUserSiteRights(user.getId());
            sites = Lists.newArrayList();
            for (Iterator<String> iter = siteRights.iterator(); iter.hasNext(); ) {
                String siteNo = iter.next();
                Site tempSite = siteService.findSiteBySiteNO(siteNo);
                if (tempSite != null) {
                    sites.add(tempSite);
                }
            }
        }
        session.setAttribute(Constants.USER_ALL_SITENO, sites);
        /*************************************** 查询我可以使用的站点信息结束 ********************************************/

        //更新在线状态
        //		ExpertService exr = new ExpertService();
        //		DynaModel data = new DynaModel();
        //		data.set("expid", user.getId());
        //		exr.getUpdateOnlineType(data, "1");
    }
}
