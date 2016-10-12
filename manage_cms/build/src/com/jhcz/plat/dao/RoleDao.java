package com.jhcz.plat.dao;

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
 * 创建时间: 14:16:13
 */
public interface RoleDao
{
    
    public void addModule(Right_Module right_module);
    
    public void addRightFunction(Right_Function right_function);
    
    public void addRightUrl(Right_Url right_url);
    
    public void addRole(Role role);
    
    public void addRoleCatalogRight(int role_id, String catalog_no_list, String siteNo);
    
    public void addRoleRight(int role_id, String catalog_no_list, String siteNo);
    
    public void addRoleSiteRight(int role_id, String catalog_no_list);
    
    public void addRoleUser(int roleId, int userId, String siteNo);
    
    public void deleteRole(int id);
    
    public void deleteRoleModule(int module_code, String siteno);
    
    public void deleteRoleRight(int roleId);
    
    public void deleteRoleUser(int roleId);
    
    public void deleteRoleUser(int roleId, int userId);
    
    public void delRightFunction(int module_code, String siteno);
    
    public void delRightUrl(int module_code, String siteno);
    
    public void editCatalogRoleRights(String editString, int role_id, String siteNo);
    
    public void editRoleRights(String editString, int role_id, String siteNo);
    
    public void editSiteRoleRights(String editString, int role_id);

    public List<Object> findModuleByParentId(int parentId, String siteno);
    
    public List<Object> findRightFunction(int module_code, String siteno);
    
    public Role findRoleById(int id, String siteNo);
    
    public Role findRoleByRoleNo(String roleNo);
    
    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：May 23, 2013 6:34:18 PM
    * @param siteno
    * @return
    */
    public List<Object> findRoleBySiteno(String siteno);
    
    public List<Object> findRoleBySiteNo(String siteNo);
    
    public List<Object> findUserCatalogRightList(int userId);
    
    public DataRow findUserCatalogRights(int userId, String siteNo);
    
    public HashSet<String> findUserRights(int userId, String siteNo);
    
    /**
    * 
    * 描述：根据用户ID查询用户所在的角色
    * 作者：袁永君
    * 时间：May 23, 2013 5:34:35 PM
    * @param userId
    * @return
    */
    public List<Object> findUserRoleById(int userId);
    
    public HashSet<String> findUserSiteRights(int userId);
    
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int roleId, String keyword);
    
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);
    
    public List<Object> getRightFunctionAll();
    
    public List<Object> getRightFunctionAll(String siteno);
    
    public List<Object> getRightModuleAll();
    
    public List<Object> getRightModuleAll(String siteno);
    
    public Role_Catalog_Right getRoleCatalogRight_by_id(int roleId, String siteNo);
    
    public Role_Right getRoleRight_by_Id(int roleId);
    
    public Role_Right getRoleRight_by_Id(int roleId, String siteNo);
    
    public Role_Site_Right getRoleSiteRight_by_id(int roleId);
    
    public List<Object> getRoleUser(int roleId, String siteNo);
    
    public int[] getUserRole(int userId);
    
    public void updateModule(Right_Module right_module);
    
    public void updateRole(Role role);
}
