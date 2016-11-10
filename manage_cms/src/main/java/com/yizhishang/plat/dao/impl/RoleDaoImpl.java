package com.yizhishang.plat.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.BaseDao;
import com.yizhishang.plat.dao.RoleDao;
import com.yizhishang.plat.domain.Right_Function;
import com.yizhishang.plat.domain.Right_Module;
import com.yizhishang.plat.domain.Role;
import com.yizhishang.plat.domain.Role_Catalog_Right;
import com.yizhishang.plat.domain.Role_Right;
import com.yizhishang.plat.domain.Role_Site_Right;
import com.yizhishang.plat.domain.User;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-9
 * 创建时间: 14:19:46
 */
@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao
{
    
    private static String FIND_ROLE_BY_ROLENO = "select * from T_ROLE where roleno = ?";
    
    private static String FIND_ROLE_RIGHT_BY_ID = "select * from T_ROLE_RIGHT where role_id=?";
    
    private static String FIND_ROLE_SITE_RIGHT_BY_ID = "select * from T_ROLE_SITE_RIGHT where role_id=?";
    
    private static String FIND_ROLE_CATALOG_RIGHT_BY_ID = "select * from T_ROLE_CATALOG_RIGHT where role_id=? and siteno = ?";
    
    private static String FIND_RIGHT_MODULE_ALL = "select * from T_RIGHT_MODULE where enable=1 order by module_code";
    
    private static String FIND_RIGHT_MODULE_FUNCTION_ALL = "select * from T_RIGHT_FUNCTION order by function_code";
    
    private static String UPDATE_ROLE_RIGHT = "update T_ROLE_RIGHT set CATALOG_NO_LIST=? where ROLE_ID=? AND SITENO = ?";
    
    private static String UPDATE_CATALOG_ROLE_RIGHT = "update T_ROLE_CATALOG_RIGHT set CATALOG_ID_LIST=? where ROLE_ID=? AND SITENO = ?";
    
    private static String UPDATE_SITE_ROLE_RIGHT = "update T_ROLE_SITE_RIGHT set SITE_NO_LIST=? where ROLE_ID=?";
    
    private static String FIND_USER_RIGHTS = "select * from T_ROLE_RIGHT where SITENO = ? AND ROLE_ID in(select ROLE_ID from T_ROLE_USER where USER_ID=?)";
    
    private static String FIND_USER_SITE_RIGHTS = "select * from T_ROLE_SITE_RIGHT where ROLE_ID in(select ROLE_ID from T_ROLE_USER where USER_ID=?)";
    
    private static String FIND_USER_CATALOG_RIGHTS = "select * from T_ROLE_CATALOG_RIGHT where SITENO = ? AND ROLE_ID in(select ROLE_ID from T_ROLE_USER where USER_ID=?)";
    
    @Override
    public void addModule(Right_Module right_module)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(right_module.toMap());
        getJdbcTemplate().insert("t_right_module", dataRow);
    }
    
    @Override
    public void addRightFunction(Right_Function right_function)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(right_function.toMap());
        getJdbcTemplate().insert("t_right_function", dataRow);
    }
    
    @Override
    public void addRightUrl(Right_Url right_url)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(right_url.toMap());
        getJdbcTemplate().insert("t_right_url", dataRow);
    }
    
    @Override
    public void addRole(Role role)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(role.toMap());
        getJdbcTemplate().insert("T_ROLE", dataRow);
        
        addRoleRight(role.getId(), "", role.getSiteNo());
        addRoleCatalogRight(role.getId(), "", role.getSiteNo());
        
    }
    
    @Override
    public void addRoleCatalogRight(int role_id, String catalog_no_list, String siteNo)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_ROLE_CATALOG_RIGHT");
        
        DataRow dataRowRight = new DataRow();
        Role_Catalog_Right roleCatalogRight = new Role_Catalog_Right();
        
        roleCatalogRight.setRole_id(role_id);
        roleCatalogRight.setId(Integer.parseInt(id));
        roleCatalogRight.setSiteNo(siteNo);
        roleCatalogRight.setCatalogIdList(catalog_no_list);
        dataRowRight.putAll(roleCatalogRight.toMap());
        this.getJdbcTemplate().insert("T_ROLE_CATALOG_RIGHT", dataRowRight);
    }
    
    @Override
    public void addRoleRight(int role_id, String catalog_no_list, String siteNo)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_GROUP_USER");
        
        DataRow dataRowRight = new DataRow();
        
        Role_Right roleRight = new Role_Right();
        roleRight.setRole_id(role_id);
        roleRight.setId(Integer.parseInt(id));
        roleRight.setSiteNo(siteNo);
        roleRight.setCatalogNoList(catalog_no_list);
        dataRowRight.putAll(roleRight.toMap());
        this.getJdbcTemplate().insert("T_ROLE_RIGHT", dataRowRight);
    }
    
    public void addRoleRights(DataRow data)
    {
        getJdbcTemplate().insert("T_ROLE_RIGHT", data);
    }
    
    @Override
    public void addRoleSiteRight(int role_id, String site_no_list)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_ROLE_SITE_RIGHT");
        
        DataRow dataRowRight = new DataRow();
        Role_Site_Right roleCatalogRight = new Role_Site_Right();
        
        roleCatalogRight.setRole_id(role_id);
        roleCatalogRight.setId(Integer.parseInt(id));
        roleCatalogRight.setSiteNoList(site_no_list);
        dataRowRight.putAll(roleCatalogRight.toMap());
        this.getJdbcTemplate().insert("T_ROLE_SITE_RIGHT", dataRowRight);
    }
    
    @Override
    public void addRoleUser(int roleId, int userId, String siteNo)
    {
        String sql = "insert into T_ROLE_USER(role_id,user_id,siteno) values(?,?,?)";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(roleId));
        argList.add(new Integer(userId));
        argList.add(siteNo);
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    @Override
    public void deleteRole(int id)
    {
        getJdbcTemplate().delete("T_ROLE", "role_id", new Integer(id));
        this.getJdbcTemplate().delete("T_ROLE_RIGHT", "ROLE_ID", new Integer(id));
        this.getJdbcTemplate().delete("T_ROLE_CATALOG_RIGHT", "ROLE_ID", new Integer(id));
    }
    
    @Override
    public void deleteRoleModule(int module_code, String siteno)
    {
        String sql = "delete from t_right_module where module_code=? and siteno=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    @Override
    public void deleteRoleRight(int roleId)
    {
        getJdbcTemplate().update("delete from T_ROLE_RIGHT where role_id = ?", new Object[] { new Integer(roleId) });
    }
    
    @Override
    public void deleteRoleUser(int roleId)
    {
        getJdbcTemplate().update("delete from T_ROLE_USER where role_id = ?", new Object[] { new Integer(roleId) });
    }
    
    @Override
    public void deleteRoleUser(int roleId, int userId)
    {
        String sql = "delete from T_ROLE_USER where role_Id=? and user_Id=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(roleId));
        argList.add(new Integer(userId));
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    @Override
    public void delRightFunction(int module_code, String siteno)
    {
        String sql = "delete from t_right_function where module_code=? and siteno=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    @Override
    public void delRightUrl(int module_code, String siteno)
    {
        String sql = "delete from t_right_url where function_code_list in (select function_code from t_right_function where module_code=? and siteno=?)";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplate().update(sql, argList.toArray());
    }
    
    @Override
    public void editCatalogRoleRights(String editString, int role_id, String siteNo)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        list.add(siteNo);
        this.getJdbcTemplate().update(UPDATE_CATALOG_ROLE_RIGHT, list.toArray());
    }
    
    @Override
    public void editRoleRights(String editString, int role_id, String siteNo)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        list.add(siteNo);
        this.getJdbcTemplate().update(UPDATE_ROLE_RIGHT, list.toArray());
    }
    
    @Override
    public void editSiteRoleRights(String editString, int role_id)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        this.getJdbcTemplate().update(UPDATE_SITE_ROLE_RIGHT, list.toArray());
    }
    
    @Override
    public List<Object> findModuleByParentId(int parentId, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_right_module where 1=1");
        if (parentId != 0)
        {
            sqlBuf.append(" and module_code=?");
            argList.add(new Integer(parentId));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        return getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
    }
    
    @Override
    public List<Object> findRightFunction(int module_code, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select a.*, b.right_url from t_right_function a, t_right_url b where 1=1");
        if (module_code != 0)
        {
            sqlBuf.append(" and a.module_code=?");
            argList.add(new Integer(module_code));
        }
        
        if (StringHelper.isNotEmpty(siteno))
        {
            sqlBuf.append("  and a.siteno=?");
            argList.add(siteno);
        }
        sqlBuf.append(" and a.function_code=b.function_code_list order by a.function_code");
        
        return getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
    }
    
    @Override
    public Role findRoleById(int id, String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_ROLE where id = ?";
        argList.add(new Integer(id));
        if (StringHelper.isNotEmpty(siteNo))
        {
            sql += " and siteno = ?";
            argList.add(siteNo);
        }
        DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
        if (dataRow == null)
            return null;
        Role role = new Role();
        role.fromMap(dataRow);
        return role;
    }
    
    @Override
    public Role findRoleByRoleNo(String roleNo)
    {
        DataRow dataRow = getJdbcTemplate().queryMap(FIND_ROLE_BY_ROLENO, new Object[] { roleNo });
        if (dataRow == null)
            return null;
        Role role = new Role();
        role.fromMap(dataRow);
        return role;
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
        String sql = "SELECT ID,ROLENO,NAME,IS_SYSTEM FROM T_ROLE WHERE SITENO = ? ORDER BY ID DESC";
        return getJdbcTemplate().query(sql, new Object[] { siteno });
    }
    
    @Override
    public List<Object> findRoleBySiteNo(String siteNo)
    {
        List<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_ROLE where 1 = 1 ";
        if (StringHelper.isNotEmpty(siteNo))
        {
            sql += " and siteno = ? ";
            argList.add(siteNo);
            
        }
        sql += " order by id";
        
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Object> newDataList = new ArrayList<Object>();
        if (dataList != null)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Role role = new Role();
                DataRow row = (DataRow) iter.next();
                role.fromMap(row);
                newDataList.add(role);
            }
        }
        return newDataList;
    }
    
    @Override
    public List<Object> findUserCatalogRightList(int userId)
    {
        return this.getJdbcTemplate().query(FIND_USER_CATALOG_RIGHTS, new Object[] { new Integer(userId) });
    }
    
    @Override
    public DataRow findUserCatalogRights(int userId, String siteNo)
    {
        List<Object> list = this.getJdbcTemplate().query(FIND_USER_CATALOG_RIGHTS, new Object[] { siteNo, new Integer(userId) });
        
        DataRow rights = new DataRow();
        String key = "";
        String value = "";
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Role_Catalog_Right roleRight = new Role_Catalog_Right();
            roleRight.fromMap(dr);
            String[] strRight = StringHelper.split(roleRight.getCatalogIdList(), "|");
            for (int k = 0; k < strRight.length; k++)
            {
                if (strRight[k].indexOf("[") <= 0)
                {
                    key = strRight[k];
                }
                else
                {
                    key = strRight[k].substring(0, strRight[k].indexOf("["));
                    value = strRight[k].substring(strRight[k].indexOf("[") + 1, strRight[k].indexOf("]"));
                }
                String[] temp = StringHelper.split(value, ":");
                if (rights.containsKey(key))
                {
                    @SuppressWarnings("unchecked")
                    HashSet<String> hs = (HashSet<String>) rights.get(key);
                    for (int m = 0; m < temp.length; m++)
                    {
                        hs.add(temp[m]);
                    }
                    rights.set(key, hs);
                }
                else
                {
                    HashSet<String> hs = new HashSet<String>();
                    hs.add("list");//默认拥有显示权限
                    for (int m = 0; m < temp.length; m++)
                    {
                        hs.add(temp[m]);
                    }
                    rights.set(key, hs);
                }
            }
        }
        return rights;
    }
    
    @Override
    public HashSet<String> findUserRights(int userId, String siteNo)
    {
        List<Object> list = this.getJdbcTemplate().query(FIND_USER_RIGHTS, new Object[] { siteNo, new Integer(userId) });
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Role_Right roleRight = new Role_Right();
            roleRight.fromMap(dr);
            String[] strRight = StringHelper.split(roleRight.getCatalogNoList(), "|");
            for (int k = 0; k < strRight.length; k++)
            {
                hs.add(strRight[k]);
            }
        }
        return hs;
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
        String sql = "SELECT ROLE_ID,ROLENO,NAME FROM T_ROLE WHERE ROLE_ID IN (SELECT ROLE_ID FROM T_ROLE_USER WHERE USER_ID = ?)";
        return getJdbcTemplate().query(sql, new Object[] { new Integer(userId) });
    }
    
    @Override
    public HashSet<String> findUserSiteRights(int userId)
    {
        List<Object> list = this.getJdbcTemplate().query(FIND_USER_SITE_RIGHTS, new Object[] { new Integer(userId) });
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Role_Site_Right roleRight = new Role_Site_Right();
            roleRight.fromMap(dr);
            String[] strRight = StringHelper.split(roleRight.getSiteNoList(), "|");
            for (int k = 0; k < strRight.length; k++)
            {
                hs.add(strRight[k]);
            }
        }
        return hs;
    }
    
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int roleId, String keyword)
    {
        DBPage page = null;
        
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_USER where id in (select user_id from T_ROLE_USER where role_id=? )");
        argList.add(new Integer(roleId));
        
        if (!StringHelper.isEmpty(keyword))
        {
            sqlBuffer.append(" and (name like ? or uid2 like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        
        sqlBuffer.append(" order by id desc ");
        
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                User user = new User();
                DataRow row = (DataRow) iter.next();
                user.fromMap(row);
                newDataList.add(user);
            }
            page.setData(newDataList);
        }
        
        return page;
    }
    
    /**
    * 以分页方式返回某角色的用户
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword
    * @return
    */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage page = null;
        
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_ROLE where 1=1 ");
        if (!StringHelper.isEmpty(siteNo))
        {
            sqlBuffer.append(" and siteno like ? ");
            argList.add("%" + siteNo + "%");
        }
        if (!StringHelper.isEmpty(keyword))
        {
            sqlBuffer.append(" and (roleno like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        sqlBuffer.append(" order by role_id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Role role = new Role();
                DataRow row = (DataRow) iter.next();
                role.fromMap(row);
                newDataList.add(role);
            }
            page.setData(newDataList);
        }
        
        return page;
    }
    
    @Override
    public List<Object> getRightFunctionAll()
    {
        List<Object> list = this.getJdbcTemplate().query(FIND_RIGHT_MODULE_FUNCTION_ALL);
        ArrayList<Object> drList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Right_Function right_function = new Right_Function();
            right_function.fromMap(dr);
            drList.add(right_function);
        }
        return drList;
    }
    
    @Override
    public List<Object> getRightFunctionAll(String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_RIGHT_FUNCTION";
        if (StringHelper.isNotEmpty(siteno))
        {
            sql += " where siteno=?";
            argList.add(siteno);
        }
        sql += " order by function_code";
        List<Object> list = this.getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Object> drList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Right_Function right_function = new Right_Function();
            right_function.fromMap(dr);
            drList.add(right_function);
        }
        return drList;
    }
    
    @Override
    public List<Object> getRightModuleAll()
    {
        List<Object> list = this.getJdbcTemplate().query(FIND_RIGHT_MODULE_ALL);
        ArrayList<Object> drList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Right_Module right_module = new Right_Module();
            right_module.fromMap(dr);
            drList.add(right_module);
        }
        return drList;
    }
    
    //"select * from T_RIGHT_MODULE where enable=1 order by module_code"
    @Override
    public List<Object> getRightModuleAll(String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        //        String sql = "select * from T_RIGHT_MODULE where enable=1";
        //        if(StringHelper.isNotEmpty(siteno))
        //        {
        //            sql += " and siteno=?";
        //            argList.add(siteno);
        //        }
        //        sql += " order by module_code";
        String sql = "SELECT * FROM T_RIGHT_MODULE A,T_MANAGE_CATALOG B WHERE A.MODULE_CODE = B.CATALOG_ID";
        if (StringHelper.isNotEmpty(siteno))
        {
            sql += " AND A.SITENO = ?";
            argList.add(siteno);
        }
        sql += " AND B.STATE = 1 ORDER BY B.PARENT_ID,B.ORDERLINE";
        
        List<Object> list = this.getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Object> drList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            DataRow dr = (DataRow) list.get(i);
            Right_Module right_module = new Right_Module();
            right_module.fromMap(dr);
            drList.add(right_module);
        }
        return drList;
    }
    
    @Override
    public Role_Catalog_Right getRoleCatalogRight_by_id(int roleId, String siteNo)
    {
        DataRow dr = this.getJdbcTemplate().queryMap(FIND_ROLE_CATALOG_RIGHT_BY_ID, new Object[] { new Integer(roleId), siteNo });
        if (dr != null)
        {
            Role_Catalog_Right role_right = new Role_Catalog_Right();
            role_right.fromMap(dr);
            return role_right;
        }
        return null;
    }
    
    @Override
    public Role_Right getRoleRight_by_Id(int roleId)
    {
        DataRow dr = this.getJdbcTemplate().queryMap(FIND_ROLE_RIGHT_BY_ID, new Object[] { new Integer(roleId) });
        if (dr != null)
        {
            Role_Right role_right = new Role_Right();
            role_right.fromMap(dr);
            return role_right;
        }
        return null;
    }
    
    @Override
    public Role_Right getRoleRight_by_Id(int roleId, String siteNo)
    {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        String sql = "select * from T_ROLE_RIGHT where role_id=?";
        arrayList.add(new Integer(roleId));
        if (StringHelper.isNotEmpty(siteNo))
        {
            sql += " and siteno=?";
            arrayList.add(siteNo);
        }
        DataRow dr = this.getJdbcTemplate().queryMap(sql, arrayList.toArray());
        if (dr != null)
        {
            Role_Right role_right = new Role_Right();
            role_right.fromMap(dr);
            return role_right;
        }
        return null;
    }
    
    @Override
    public Role_Site_Right getRoleSiteRight_by_id(int roleId)
    {
        DataRow dr = this.getJdbcTemplate().queryMap(FIND_ROLE_SITE_RIGHT_BY_ID, new Object[] { new Integer(roleId) });
        if (dr != null)
        {
            Role_Site_Right role_right = new Role_Site_Right();
            role_right.fromMap(dr);
            return role_right;
        }
        return null;
    }
    
    @Override
    public List<Object> getRoleUser(int roleId, String siteNo)
    {
        List<Object> dataList = getJdbcTemplate().query(
                "select * from T_USER where id in (select user_id from T_ROLE_USER where role_id = ? and siteno = ?)",
                new Object[] { new Integer(roleId), siteNo });
        ArrayList<Object> newDataList = new ArrayList<Object>();
        
        if (dataList != null)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow row = (DataRow) iter.next();
                User user = new User();
                user.fromMap(row);
                newDataList.add(user);
            }
        }
        return newDataList;
    }
    
    @Override
    public int[] getUserRole(int userId)
    {
        String sql = "select role_id from T_ROLE_USER where user_id=" + userId;
        return getJdbcTemplate().queryIntArray(sql);
    }
    
    @Override
    public void updateModule(Right_Module right_module)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(right_module.toMap());
        getJdbcTemplate().update("t_right_module", dataRow, "module_code", new Integer(right_module.getModule_code()));
    }
    
    @Override
    public void updateRole(Role role)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(role.toMap());
        getJdbcTemplate().update("T_ROLE", dataRow, "id", new Integer(role.getId()));
    }
}
