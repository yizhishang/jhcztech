package com.jhcz.plat.service;

import java.util.HashSet;
import java.util.List;

import com.jhcz.base.domain.Right_Url;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.domain.Right_Function;
import com.jhcz.plat.domain.Right_Module;
import com.jhcz.plat.domain.Role;
import com.jhcz.plat.domain.Role_Catalog_Right;
import com.jhcz.plat.domain.Role_Right;
import com.jhcz.plat.domain.Role_Site_Right;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-9
 * 创建时间: 14:33:48
 */
public interface RoleService
{
    
    /**
    * 描述：添加权限模块
    * @param right_module
    */
    public void addModule(Right_Module right_module);
    
    /**
    * 描述：添加权限功能
    * @param right_function
    */
    public void addRightFunction(Right_Function right_function);
    
    /**
    * 描述：添加跳转路径
    * @param right_url
    */
    public void addRightUrl(Right_Url right_url);
    
    /**
    * 添加角色
    *
    * @param role 角色对象
    */
    public void addRole(Role role);
    
    /**
    * 增加角色用户
    *
    * @param roleId 角色ID
    * @param userId 用户ID
    */
    public void addRoleUser(int roleId, int userId, String siteNo);
    
    /**
    * 删除角色
    *
    * @param id 角色的ID
    */
    public void deleteRole(int id);
    
    /**
    * 描述：删除权限模块信息
    * @param module_code
    */
    public void deleteRoleModule(int module_code, String siteno);
    
    /**
    * 删除角色权限
    *
    * @param roleId 角色ID
    */
    public void deleteRoleRight(int roleId);
    
    /**
    * 删除角色用户
    *
    * @param roleId 角色ID
    */
    public void deleteRoleUser(int roleId);
    
    /**
    * 删除角色用户
    *
    * @param roleId 角色ID
    * @param userId 用户ID
    */
    public void deleteRoleUser(int roleId, int userId);
    
    /**
    * 描述：删除权限方法
    * @param module_code
    */
    public void delRightFunction(int module_code, String siteno);
    
    /**
    * 描述：删除跳转路径
    * @param module_code
    * @param siteno
    */
    public void delRightUrl(int module_code, String siteno);
    
    /**
    * 
    * 描述:  设置栏目权限
    * 作者:	 
    * 创建日期: 2009-12-31
    * 创建时间: 下午08:27:19
    * @return void
    * @throws
    */
    public void editCatalogRoleRights(String editString, int role_id, String siteNo);
    
    /**
    *
    * 设置权限
    */
    public void editRoleRights(String editString, int role_id, String siteNo);
    
    /**
    * 
    * @描述：设置站点权限
    * @作者：袁永君
    * @时间：2011-3-14 下午08:38:18
    * @param editString
    * @param role_id
    * @param siteNo
    */
    public void editSiteRoleRights(String editString, int role_id);
    
    public List<Object> findCatalogRight(int roleId, String siteno);
    
    public List<Object>  findDocRight(int roleId, String siteNo);
    
    /**
    * 描述：根据父级栏目编号查找模块表中是否有相关数据
    * @return
    */
    public List<Object>  findModuleByParentId(int parentId, String siteno);
    
    /**
    * 描述：查找权限方法
    * @param module_code
    * @return
    */
    public List<Object>  findRightFunction(int module_code, String siteno);
    
    /**
    * 根据角色的ID，查找相应的角色
    *
    * @param id 角色的ID
    * @return 若没有发现角色，则返回null
    */
    public Role findRoleById(int id, String siteno);
    
    /**
    * 根据角色RoleNo，查找相应的角色
    *
    * @param roleNo 角色RoleNo
    * @return 若没有发现角色，则返回null
    */
    public Role findRoleByRoleNo(String roleNo);
    
    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：May 23, 2013 6:34:18 PM
    * @param siteno
    * @return
    */
    public List<Object>  findRoleBySiteno(String siteno);
    
    /**
    * 根据站点标识，查找该站点的所有角色
    *
    * @param siteNo 站点编号
    * @return
    */
    public List<Object>  findRoleBySiteNo(String siteNo);
    
    /**
    * 
    * @描述：查询角色拥有的站点权限
    * @作者：袁永君
    * @时间：2011-3-15 下午02:18:45
    * @param roleId
    * @param siteNo
    * @return
    */
    public List<Object>  findSiteRight(int roleId);
    
    public List<Object>  findUserCatalogRightList(int userId);
    
    /**
    *
    * 通过用户ID查找该用户具有的栏目权限
    * @param userId
    */
    public DataRow findUserCatalogRights(int userId, String siteNo);
    
    /**
    *
    * 通过用户ID查找该用户具有的权限
    * @param userId
    */
    public HashSet<String> findUserRights(int userId, String siteNo);
    
    /**
    * 
    * 描述：根据用户ID查询用户所在的角色
    * 作者：袁永君
    * 时间：May 23, 2013 5:34:35 PM
    * @param userId
    * @return
    */
    public List<Object>  findUserRoleById(int userId);
    
    /**
    * 
    * @描述：根据角色的ID，查找相应的站点角色
    * @作者：袁永君
    * @时间：2011-3-17 下午02:44:10
    * @param userId
    * @return
    */
    public HashSet<String> findUserSiteRights(int userId);
    
    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：May 25, 2013 11:00:37 PM
    * @param roleId
    * @param siteNo
    * @return
    */
    public DataRow getDocRight(int roleId, String siteNo);
    
    /**
    * 以分页方式返回某角色的用户
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param roleId     角色ID
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int roleId, String keyword);
    
    /**
    * 以分页方式返回某站点的角色
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword    关键词
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);
    
    /**
    * 查询所有权限功能模块
    *
    * @return
    */
    public List<Object>  getRightFunctionAll();
    
    /**
    * 查询所有权限功能模块
    * @param siteno
    * @return
    */
    public List<Object>  getRightFunctionAll(String siteno);
    
    /**
    * 查出所有权限模块
    *
    * @return
    */
    public List<Object>  getRightModuleAll();
    
    /**
    * 查出所有权限模块
    * @param siteno
    * @return
    */
    public List<Object>  getRightModuleAll(String siteno);
    
    /**
    * 
    * 描述：查询角色拥有的栏目权限
    * 作者：袁永君
    * 时间：May 25, 2013 4:10:04 PM
    * @param roleId
    * @param siteNo
    * @return
    */
    public HashSet<String> getRoleCatalogRight(int roleId, String siteNo);
    
    /**
    * 查看该角色的栏目权限
    *
    * @param roleId
    * @return
    */
    public Role_Catalog_Right getRoleCatalogRight_by_id(int roleId, String siteNo);
    
    /**
    * 查看该角色的权限
    *
    * @param roleId
    * @return
    */
    public Role_Right getRoleRight_by_Id(int roleId);
    
    /**
    * 查看该角色的权限
    *
    * @param roleId
    * @return
    */
    public Role_Right getRoleRight_by_Id(int roleId, String siteno);
    
    /**
    * 
    * @描述：查看该角色的站点权限
    * @作者：袁永君
    * @时间：2011-3-15 下午02:08:06
    * @param roleId
    * @param siteNo
    * @return
    */
    public Role_Site_Right getRoleSiteRight_by_id(int roleId);
    
    /**
    * 返回某角色所有用户，返回的list中的每一个元素是一个User对象
    * @param roleId 角色ID
    * @return
    */
    public List<Object> getRoleUser(int roleId, String siteNo);

    /**
     * 返回某用户所属的角色列表，是一个整型数组
     * @param userId
     * @return
     */
    public int[] getUserRole(int userId);
    
    /**
    * 根据角色RoleNo，判断角色是否已经存在
    * @param roleNo
    * @return
    */
    public boolean isRoleExist(String roleNo);
    
    /**
    * 描述：更新模块信息
    * @param right_module
    */
    public void updateModule(Right_Module right_module);
    
    /**
    * 更新角色
    *
    * @param role 角色对象
    */
    public void updateRole(Role role);
}
