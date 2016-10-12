package com.jhcz.plat.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.domain.Right_Url;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.RoleDao;
import com.jhcz.plat.dao.SiteDao;
import com.jhcz.plat.domain.ManageCatalog;
import com.jhcz.plat.domain.Right_Function;
import com.jhcz.plat.domain.Right_Module;
import com.jhcz.plat.domain.Role;
import com.jhcz.plat.domain.Role_Catalog_Right;
import com.jhcz.plat.domain.Role_Right;
import com.jhcz.plat.domain.Role_Site_Right;
import com.jhcz.plat.domain.Site;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.service.ManageCatalogService;
import com.jhcz.plat.service.RoleService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-9
 * 创建时间: 14:37:52
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService
{
    
    @Resource
    private RoleDao roleDao;
    
    /**
     * 描述：添加权限模块
     * @param right_module
     */
    @Override
    public void addModule(Right_Module right_module)
    {
        roleDao.addModule(right_module);
    }
    
    /**
     * 描述：添加权限功能
     * @param right_function
     */
    @Override
    public void addRightFunction(Right_Function right_function)
    {
        roleDao.addRightFunction(right_function);
    }
    
    /**
     * 描述：添加跳转路径
     * @param right_url
     */
    @Override
    public void addRightUrl(Right_Url right_url)
    {
        roleDao.addRightUrl(right_url);
    }
    
    /**
     * 添加角色
     *
     * @param role 角色对象
     */
    @Override
    public void addRole(Role role)
    {
        String id = getSequenceGenerator().getNextSequence("T_ROLE");
        role.setId(Integer.parseInt(id));
        roleDao.addRole(role);
    }
    
    /**
     * 增加角色用户
     *
     * @param roleId 角色ID
     * @param userId 用户ID
     */
    @Override
    public void addRoleUser(int roleId, int userId, String siteNo)
    {
        roleDao.addRoleUser(roleId, userId, siteNo);
    }
    
    private void catalogsToArr(List<Object> catalogs, List<Object> arrayList, HashSet<String> rights)
    {
        
        if (catalogs != null)
        {
            for (int i = 0; i < catalogs.size(); i++)
            {
                ManageCatalog catalog = (ManageCatalog) catalogs.get(i);
                DataRow dataRow = new DataRow();
                String catalogId = String.valueOf(catalog.getId());
                dataRow.set("catalog_id", catalogId);
                dataRow.set("name", catalog.getName());
                dataRow.set("route", catalog.getRoute());
                
                String route = catalog.getRoute();
                if (rights.contains(catalogId))
                {
                    dataRow.set("show", "1");//显示权限
                }
                dataRow.set("rownum", StringHelper.count(route, '|'));
                
                arrayList.add(dataRow);
                List<Object> children = catalog.getChildren();
                if (children != null && children.size() > 0)
                {
                    catalogsToArr(children, arrayList, rights);
                }
                else
                {
                    dataRow.set("isLastTree", "true");
                }
            }
        }
    }
    
    /**
     * 删除角色
     *
     * @param id 角色的ID
     */
    @Override
    public void deleteRole(int id)
    {
        roleDao.deleteRole(id);
    }
    
    /**
     * 描述：删除权限模块信息
     * @param module_code
     */
    @Override
    public void deleteRoleModule(int module_code, String siteno)
    {
        roleDao.deleteRoleModule(module_code, siteno);
    }
    
    /**
     * 删除角色所有权限
     *
     * @param roleId 角色ID
     */
    @Override
    public void deleteRoleRight(int roleId)
    {
        roleDao.deleteRoleRight(roleId);
    }
    
    /**
     * 删除角色所有用户
     *
     * @param roleId 角色ID
     */
    @Override
    public void deleteRoleUser(int roleId)
    {
        roleDao.deleteRoleUser(roleId);
    }
    
    /**
     * 删除角色用户
     *
     * @param roleId 角色ID
     * @param userId 用户ID
     */
    @Override
    public void deleteRoleUser(int roleId, int userId)
    {
        roleDao.deleteRoleUser(roleId, userId);
    }
    
    /**
     * 描述：删除权限方法
     * @param module_code
     */
    @Override
    public void delRightFunction(int module_code, String siteno)
    {
        roleDao.delRightFunction(module_code, siteno);
    }
    
    /**
     * 描述：删除跳转路径
     * @param module_code
     * @param siteno
     */
    @Override
    public void delRightUrl(int module_code, String siteno)
    {
        roleDao.delRightUrl(module_code, siteno);
    }
    
    private void docToArr(List<Object> catalogs, List<Object> arrayList, DataRow rights)
    {
        
        if (catalogs != null)
        {
            for (int i = 0; i < catalogs.size(); i++)
            {
                DataRow dataRow = (DataRow) catalogs.get(i);
                String catalogId = dataRow.getString("catalog_id");
                String route = dataRow.getString("route");
                String attribute = "";
                if (rights.containsKey(catalogId))
                {
                    dataRow.set("show", "1");//显示权限
                    attribute = rights.getString(catalogId);
                }
                dataRow.set("rownum", StringHelper.count(route, '|'));
                
                arrayList.add(dataRow);
                @SuppressWarnings("unchecked")
                List<Object> children = (List<Object>) dataRow.getObject("children");
                if (children != null && children.size() > 0)
                {
                    docToArr(children, arrayList, rights);
                }
                else
                {
                    dataRow.putAll(getDocRight(attribute));
                    
                    dataRow.set("isLastTree", "true");
                }
            }
        }
    }
    
    @Override
    public void editCatalogRoleRights(String editString, int role_id, String siteNo)
    {
        if (roleDao.getRoleCatalogRight_by_id(role_id, siteNo) != null)
        {
            roleDao.editCatalogRoleRights(editString, role_id, siteNo);
        }
        else
        {
            roleDao.addRoleCatalogRight(role_id, editString, siteNo);
        }
    }
    
    /**
     * 设置权限
     */
    @Override
    public void editRoleRights(String editString, int role_id, String siteNo)
    {
        if (roleDao.getRoleRight_by_Id(role_id, siteNo) != null)
        {
            roleDao.editRoleRights(editString, role_id, siteNo);
        }
        else
        {
            roleDao.addRoleRight(role_id, editString, siteNo);
        }
    }
    
    /**
     * 
     * @描述：设置站点权限
     * @作者：袁永君
     * @时间：2011-3-14 下午08:39:07
     * @param editString
     * @param role_id
     * @param siteNo
     */
    @Override
    public void editSiteRoleRights(String editString, int role_id)
    {
        if (roleDao.getRoleSiteRight_by_id(role_id) != null)
        {
            roleDao.editSiteRoleRights(editString, role_id);
        }
        else
        {
            roleDao.addRoleSiteRight(role_id, editString);
        }
    }
    
    /**
     * 
     * @描述：查询栏目权限
     * @作者：袁永君
     * @时间：2011-3-13 下午09:21:04
     * @param roleId
     * @param siteno
     * @return
     */
    @Override
    public List<Object> findCatalogRight(int roleId, String siteno)
    {
        Role_Right roleRight = roleDao.getRoleRight_by_Id(roleId, siteno);
        HashSet<String> catalogRight = new HashSet<String>();
        if (roleRight != null)
        {
            String strRights = roleRight.getCatalogNoList();
            String[] temp = StringHelper.split(strRights, "|");
            for (int i = 0; i < temp.length; i++)
            {
                catalogRight.add(temp[i]);
            }
        }
        
        ManageCatalogService manageService = new ManageCatalogServiceImpl();
        List<Object> catalogs = manageService.findAllChildrenCatalogsById(1, "");
        
        ArrayList<Object> dataList = new ArrayList<Object>();
        catalogsToArr(catalogs, dataList, catalogRight);
        
        return dataList;
    }
    
    /**
     * 
     * @描述：查询文档权限
     * @作者：袁永君
     * @时间：2011-3-12 上午08:48:02
     * @return
     */
    @Override
    public List<Object> findDocRight(int roleId, String siteNo)
    {
        DataRow catalogRole = getDocRight(roleId, siteNo);
        
        CatalogService catalogService = new CatalogServiceImpl();
        List<Object> catalogs = catalogService.findWholeCatalogById(1, siteNo);
        
        ArrayList<Object> dataList = new ArrayList<Object>();
        
        docToArr(catalogs, dataList, catalogRole);
        return dataList;
        
    }
    
    /**
     * 描述：根据父级栏目编号查找模块表中是否有相关数据
     * @return
     */
    @Override
    public List<Object> findModuleByParentId(int parentId, String siteno)
    {
        return roleDao.findModuleByParentId(parentId, siteno);
    }
    
    /**
     * 描述：查找权限方法
     * @param module_code
     * @return
     */
    @Override
    public List<Object> findRightFunction(int module_code, String siteno)
    {
        return roleDao.findRightFunction(module_code, siteno);
    }
    
    /**
     * 根据角色的ID，查找相应的角色
     *
     * @param id 角色的ID
     * @param siteno 站点标号
     * @return 若没有发现角色，则返回null
     */
    @Override
    public Role findRoleById(int id, String siteno)
    {
        return roleDao.findRoleById(id, siteno);
    }
    
    /**
     * 根据角色RoleNo，查找相应的角色
     *
     * @param roleNo 角色RoleNo
     * @return 若没有发现角色，则返回null
     */
    @Override
    public Role findRoleByRoleNo(String roleNo)
    {
        return roleDao.findRoleByRoleNo(roleNo);
    }
    
    /**
     * 
     * 描述：
     * 作者：袁永君
     * 时间：May 23, 2013 6:34:18 PM
     * @param siteno
     * @return
     */
    @Override
    public List<Object> findRoleBySiteno(String siteno)
    {
        return roleDao.findRoleBySiteno(siteno);
    }
    
    /**
     * 根据角色的ID，查找相应的站点角色
     *
     * @param siteNo 站点编号
     * @return
     */
    @Override
    public List<Object> findRoleBySiteNo(String siteNo)
    {
        return roleDao.findRoleBySiteNo(siteNo);
    }
    
    /**
     * 
     * @描述：查询所有的站点权限
     * @作者：袁永君
     * @时间：2011-3-15 下午02:18:45
     * @param roleId
     * @param siteNo
     * @return
     */
    @Override
    public List<Object> findSiteRight(int roleId)
    {
        List<Object> dataList = new ArrayList<Object>();
        
        Role_Site_Right roleSiteRight = getRoleSiteRight_by_id(roleId);
        
        HashSet<String> siteRight = new HashSet<String>();
        if (roleSiteRight != null)
        {
            String strRights = roleSiteRight.getSiteNoList();
            String[] temp = StringHelper.split(strRights, "|");
            for (int i = 0; i < temp.length; i++)
            {
                siteRight.add(temp[i]);
            }
        }
        
        SiteDao siteDao = (SiteDao) getDao(SiteDao.class);
        List<Object> siteList = siteDao.getAllSite();
        
        if (siteList != null)
        {
            DataRow dataRow = null;
            for (Iterator<Object> iter = siteList.iterator(); iter.hasNext();)
            {
                dataRow = new DataRow();
                Site site = (Site) iter.next();
                String no = site.getSiteNo();
                String name = site.getName();
                dataRow.set("siteNo", no);
                dataRow.set("name", name);
                if (siteRight.contains(no))
                {
                    dataRow.set("show", "1");
                }
                dataList.add(dataRow);
            }
        }
        return dataList;
    }
    
    @Override
    public List<Object> findUserCatalogRightList(int userId)
    {
        return roleDao.findUserCatalogRightList(userId);
    }
    
    /**
     *
     * 通过用户ID查找该用户具有的功能权限
     * @param userId
     */
    @Override
    public DataRow findUserCatalogRights(int userId, String siteNo)
    {
        return roleDao.findUserCatalogRights(userId, siteNo);
    }
    
    /**
     * 通过用户ID查找用户具有的权限
     *
     * @param userId
     */
    @Override
    public HashSet<String> findUserRights(int userId, String siteNo)
    {
        return roleDao.findUserRights(userId, siteNo);
        
    }
    
    /**
     * 
     * 描述：根据用户ID查询用户所在的角色
     * 作者：袁永君
     * 时间：May 23, 2013 5:34:35 PM
     * @param userId
     * @return
     */
    @Override
    public List<Object> findUserRoleById(int userId)
    {
        return roleDao.findUserRoleById(userId);
    }
    
    /**
     * 返回某用户所属的站点角色列表，是一个整型数组
     *
     * @param userId
     * @return
     */
    @Override
    public HashSet<String> findUserSiteRights(int userId)
    {
        return roleDao.findUserSiteRights(userId);
    }
    
    /**
     * 
     * 描述：
     * 作者：袁永君
     * 时间：May 25, 2013 11:00:37 PM
     * @param roleId
     * @param siteNo
     * @return
     */
    @Override
    public DataRow getDocRight(int roleId, String siteNo)
    {
        
        Role_Catalog_Right catalogRight = null;
        if (roleId > 0)
        {
            catalogRight = getRoleCatalogRight_by_id(roleId, siteNo);
        }
        
        DataRow catalogRole = new DataRow();
        if (catalogRight != null)
        {
            String catalogIdList = catalogRight.getCatalogIdList();
            String[] temp = StringHelper.split(catalogIdList, "|");
            
            for (int i = 0; i < temp.length; i++)
            {
                String str = temp[i];
                if (StringHelper.isNotEmpty(str))
                {
                    String key = "";
                    String value = "";
                    if (str.indexOf("[") > 0)
                    {
                        key = str.substring(0, str.indexOf("["));
                        value = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
                        
                    }
                    else
                    {
                        key = str;
                    }
                    catalogRole.set(key, value);
                }
            }
        }
        return catalogRole;
    }
    
    /**
     * 
     * @描述：角色属性
     * @作者：袁永君
     * @时间：2011-3-13 下午03:47:50
     * @param attribute
     * @return
     */
    private DataRow getDocRight(String attribute)
    {
        String[] temp = StringHelper.split(attribute, ":");
        DataRow dataRow = new DataRow();
        if (temp.length > 0)
        {
            for (int i = 0; i < temp.length; i++)
            {
                dataRow.set(temp[i], "1");
            }
        }
        return dataRow;
    }
    
    /**
     * 以分页方式返回某角色的用户
     *
     * @param curPage    当前第几页
     * @param numPerPage 每页多少条记录
     * @param siteNo     站点编号
     * @param roleId     角色ID
     * @return
     */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int roleId, String keyword)
    {
        return roleDao.getPageData(curPage, numPerPage, siteNo, roleId, keyword);
    }
    
    /**
     * 以分页方式返回某站点的角色
     *
     * @param curPage    当前第几页
     * @param numPerPage 每页多少条记录
     * @param siteNo     站点编号
     * @param keyword    关键词
     * @return
     */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        return roleDao.getPageData(curPage, numPerPage, siteNo, keyword);
    }
    
    /**
     * 查出所有权限功能模块
     *
     * @return
     */
    
    @Override
    public List<Object> getRightFunctionAll()
    {
        return roleDao.getRightFunctionAll();
        
    }
    
    /**
     * 查出所有权限功能模块
     * @param siteno
     * @return
     */
    @Override
    public List<Object> getRightFunctionAll(String siteno)
    {
        return roleDao.getRightFunctionAll(siteno);
    }
    
    /**
     * 查出所有权限模块
     *
     * @return
     */
    @Override
    public List<Object> getRightModuleAll()
    {
        return roleDao.getRightModuleAll();
        
    }
    
    /**
     * 查出所有权限模块
     * @param siteno
     * @return
     */
    @Override
    public List<Object> getRightModuleAll(String siteno)
    {
        return roleDao.getRightModuleAll(siteno);
    }
    
    /**
     * 
     * 描述：查询角色拥有的栏目权限
     * 作者：袁永君
     * 时间：May 25, 2013 4:10:04 PM
     * @param roleId
     * @param siteNo
     * @return
     */
    @Override
    public HashSet<String> getRoleCatalogRight(int roleId, String siteNo)
    {
        Role_Right roleRight = roleDao.getRoleRight_by_Id(roleId, siteNo);
        HashSet<String> catalogRight = new HashSet<String>();
        if (roleRight != null)
        {
            String strRights = roleRight.getCatalogNoList();
            String[] temp = StringHelper.split(strRights, "|");
            for (int i = 0; i < temp.length; i++)
            {
                catalogRight.add(temp[i]);
            }
        }
        return catalogRight;
    }
    
    /**
     * 查看该角色栏目权限
     *
     * @param roleId
     * @return
     */
    @Override
    public Role_Catalog_Right getRoleCatalogRight_by_id(int roleId, String siteNo)
    {
        return roleDao.getRoleCatalogRight_by_id(roleId, siteNo);
    }
    
    /**
     * 查看该角色权限
     *
     * @param roleId
     * @return
     */
    @Override
    public Role_Right getRoleRight_by_Id(int roleId)
    {
        
        return roleDao.getRoleRight_by_Id(roleId);
        
    }
    
    /**
     * 查看该角色权限
     * @param roleId
     * @param siteNo
     * @return
     */
    @Override
    public Role_Right getRoleRight_by_Id(int roleId, String siteNo)
    {
        return roleDao.getRoleRight_by_Id(roleId, siteNo);
    }
    
    /**
     * 查看该角色站点权限
     *
     * @param roleId
     * @return
     */
    @Override
    public Role_Site_Right getRoleSiteRight_by_id(int roleId)
    {
        return roleDao.getRoleSiteRight_by_id(roleId);
    }
    
    /**
     * 返回某角色所有用户，返回的list中的每一个元素是一个User对象
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<Object> getRoleUser(int roleId, String siteNo)
    {
        return roleDao.getRoleUser(roleId, siteNo);
    }
    
    /**
     * 返回某用户所属的角色列表，是一个整型数组
     *
     * @param userId
     * @return
     */
    @Override
    public int[] getUserRole(int userId)
    {
        return roleDao.getUserRole(userId);
    }
    
    /**
     * 根据角色RoleNo，判断角色是否已经存在
     *
     * @param roleNo
     * @return
     */
    @Override
    public boolean isRoleExist(String roleNo)
    {
        Role role = roleDao.findRoleByRoleNo(roleNo);
        return role != null;
    }
    
    /**
     * 描述：更新模块信息
     * @param right_module
     */
    @Override
    public void updateModule(Right_Module right_module)
    {
        roleDao.updateModule(right_module);
    }
    
    /**
     * 更新角色
     * @param role 角色对象
     */
    @Override
    public void updateRole(Role role)
    {
        roleDao.updateRole(role);
    }
}
