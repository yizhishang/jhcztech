package com.yizhishang.plat.dao;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
public class RoleDao extends BaseDao
{

    private static String FIND_ROLE_BY_ROLENO = "select * from T_ROLE where roleno = ?";

    private static String FIND_ROLE_RIGHT_BY_ID = "select * from T_ROLE_RIGHT where role_id=?";

    private static String FIND_ROLE_SITE_RIGHT_BY_ID = "select * from T_ROLE_SITE_RIGHT where role_id=?";

    private static String FIND_ROLE_CATALOG_RIGHT_BY_ID = "select * from T_ROLE_CATALOG_RIGHT where role_id=? and " +
            "siteno = ?";

    private static String FIND_RIGHT_MODULE_ALL = "select * from T_RIGHT_MODULE where enable=1 order by module_code";

    private static String FIND_RIGHT_MODULE_FUNCTION_ALL = "select * from T_RIGHT_FUNCTION order by function_code";

    private static String UPDATE_ROLE_RIGHT = "update T_ROLE_RIGHT set CATALOG_NO_LIST=? where ROLE_ID=? AND SITENO " +
            "=" + " ?";

    private static String UPDATE_CATALOG_ROLE_RIGHT = "update T_ROLE_CATALOG_RIGHT set CATALOG_ID_LIST=? where " +
            "ROLE_ID=? AND SITENO = ?";

    private static String UPDATE_SITE_ROLE_RIGHT = "update T_ROLE_SITE_RIGHT set SITE_NO_LIST=? where ROLE_ID=?";

    private static String FIND_USER_RIGHTS = "select * from T_ROLE_RIGHT where SITENO = ? AND ROLE_ID in(select " +
            "ROLE_ID from T_ROLE_USER where USER_ID=?)";

    private static String FIND_USER_SITE_RIGHTS = "select * from T_ROLE_SITE_RIGHT where ROLE_ID in(select ROLE_ID "
            + "from T_ROLE_USER where USER_ID=?)";

    private static String FIND_USER_CATALOG_RIGHTS = "select * from T_ROLE_CATALOG_RIGHT where SITENO = ? AND " +
            "ROLE_ID" + " in(select ROLE_ID from T_ROLE_USER where USER_ID=?)";

    public void addModule(Right_Module right_module)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(right_module.toMap());
        getJdbcTemplateUtil().insert("t_right_module", dataRow);
    }

    public void addRightFunction(Right_Function right_function)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(right_function.toMap());
        getJdbcTemplateUtil().insert("t_right_function", dataRow);
    }

    public void addRightUrl(Right_Url right_url)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(right_url.toMap());
        getJdbcTemplateUtil().insert("t_right_url", dataRow);
    }

    public void addRole(Role role)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(role.toMap());
        getJdbcTemplateUtil().insert("T_ROLE", dataRow);

        addRoleRight(role.getId(), "", role.getSiteNo());
        addRoleCatalogRight(role.getId(), "", role.getSiteNo());

    }

    public void addRoleCatalogRight(int role_id, String catalog_no_list, String siteNo)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_ROLE_CATALOG_RIGHT");

        DynaModel dataRowRight = new DynaModel();
        Role_Catalog_Right roleCatalogRight = new Role_Catalog_Right();

        roleCatalogRight.setRole_id(role_id);
        roleCatalogRight.setId(Integer.parseInt(id));
        roleCatalogRight.setSiteNo(siteNo);
        roleCatalogRight.setCatalogIdList(catalog_no_list);
        dataRowRight.putAll(roleCatalogRight.toMap());
        this.getJdbcTemplateUtil().insert("T_ROLE_CATALOG_RIGHT", dataRowRight);
    }

    public void addRoleRight(int role_id, String catalog_no_list, String siteNo)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_GROUP_USER");

        DynaModel dataRowRight = new DynaModel();

        Role_Right roleRight = new Role_Right();
        roleRight.setRole_id(role_id);
        roleRight.setId(Integer.parseInt(id));
        roleRight.setSiteNo(siteNo);
        roleRight.setCatalogNoList(catalog_no_list);
        dataRowRight.putAll(roleRight.toMap());
        this.getJdbcTemplateUtil().insert("T_ROLE_RIGHT", dataRowRight);
    }

    public void addRoleRights(DynaModel data)
    {
        getJdbcTemplateUtil().insert("T_ROLE_RIGHT", data);
    }

    public void addRoleSiteRight(int role_id, String site_no_list)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_ROLE_SITE_RIGHT");

        DynaModel dataRowRight = new DynaModel();
        Role_Site_Right roleCatalogRight = new Role_Site_Right();

        roleCatalogRight.setRole_id(role_id);
        roleCatalogRight.setId(Integer.parseInt(id));
        roleCatalogRight.setSiteNoList(site_no_list);
        dataRowRight.putAll(roleCatalogRight.toMap());
        this.getJdbcTemplateUtil().insert("T_ROLE_SITE_RIGHT", dataRowRight);
    }

    public void addRoleUser(int roleId, int userId, String siteNo)
    {
        String sql = "insert into T_ROLE_USER(role_id,user_id,siteno) values(?,?,?)";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(roleId));
        argList.add(new Integer(userId));
        argList.add(siteNo);
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }

    public void deleteRole(int id)
    {
        getJdbcTemplateUtil().delete("T_ROLE", "role_id", new Integer(id));
        this.getJdbcTemplateUtil().delete("T_ROLE_RIGHT", "ROLE_ID", new Integer(id));
        this.getJdbcTemplateUtil().delete("T_ROLE_CATALOG_RIGHT", "ROLE_ID", new Integer(id));
    }

    public void deleteRoleModule(int module_code, String siteno)
    {
        String sql = "delete from t_right_module where module_code=? and siteno=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }

    public void deleteRoleRight(int roleId)
    {
        getJdbcTemplateUtil().update("delete from T_ROLE_RIGHT where role_id = ?", new Object[] {new Integer(roleId)});
    }

    public void deleteRoleUser(int roleId)
    {
        getJdbcTemplateUtil().update("delete from T_ROLE_USER where role_id = ?", new Object[] {new Integer(roleId)});
    }

    public void deleteRoleUser(int roleId, int userId)
    {
        String sql = "delete from T_ROLE_USER where role_Id=? and user_Id=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(roleId));
        argList.add(new Integer(userId));
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }

    public void delRightFunction(int module_code, String siteno)
    {
        String sql = "delete from t_right_function where module_code=? and siteno=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }

    public void delRightUrl(int module_code, String siteno)
    {
        String sql = "delete from t_right_url where function_code_list in (select function_code from " +
                "t_right_function" + " where module_code=? and siteno=?)";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(module_code));
        argList.add(siteno);
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }

    public void editCatalogRoleRights(String editString, int role_id, String siteNo)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        list.add(siteNo);
        this.getJdbcTemplateUtil().update(UPDATE_CATALOG_ROLE_RIGHT, list.toArray());
    }

    public void editRoleRights(String editString, int role_id, String siteNo)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        list.add(siteNo);
        this.getJdbcTemplateUtil().update(UPDATE_ROLE_RIGHT, list.toArray());
    }

    public void editSiteRoleRights(String editString, int role_id)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(editString);
        list.add(new Integer(role_id));
        this.getJdbcTemplateUtil().update(UPDATE_SITE_ROLE_RIGHT, list.toArray());
    }

    public List<DynaModel> findModuleByParentId(int parentId, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_right_module where 1=1");
        if (parentId != 0) {
            sqlBuf.append(" and module_code=?");
            argList.add(new Integer(parentId));
        }

        if (StringHelper.isNotEmpty(siteno)) {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        return getJdbcTemplateUtil().queryForList(sqlBuf.toString(), DynaModel.class, argList.toArray());
    }

    public List<DynaModel> findRightFunction(int module_code, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select a.*, b.right_url from t_right_function a, t_right_url b where 1=1");
        if (module_code != 0) {
            sqlBuf.append(" and a.module_code=?");
            argList.add(new Integer(module_code));
        }

        if (StringHelper.isNotEmpty(siteno)) {
            sqlBuf.append("  and a.siteno=?");
            argList.add(siteno);
        }
        sqlBuf.append(" and a.function_code=b.function_code_list order by a.function_code");

        return getJdbcTemplateUtil().queryForList(sqlBuf.toString(), DynaModel.class, argList.toArray());
    }

    public Role findRoleById(int id, String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_ROLE where id = ?";
        argList.add(new Integer(id));
        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno = ?";
            argList.add(siteNo);
        }
        try {
            return getJdbcTemplateUtil().queryMap(sql, Role.class, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Role findRoleByRoleNo(String roleNo)
    {
        try {
            return getJdbcTemplateUtil().queryMap(FIND_ROLE_BY_ROLENO, Role.class, new Object[] {roleNo});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 描述：
     * 作者：袁永君
     * 时间：May 23, 2013 6:34:18 PM
     *
     * @param siteno
     * @return
     */
    public List<Role> findRoleBySiteno(String siteno)
    {
        String sql = "SELECT ID,ROLENO,NAME,IS_SYSTEM FROM T_ROLE WHERE SITENO = ? ORDER BY ID DESC";
        return getJdbcTemplateUtil().queryForList(sql, Role.class, new Object[] {siteno});
    }

    public List<Role> findRoleBySiteNo(String siteNo)
    {
        List<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_ROLE where 1 = 1 ";
        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno = ? ";
            argList.add(siteNo);

        }
        sql += " order by id";

        List<Role> dataList = getJdbcTemplateUtil().queryForList(sql, Role.class, argList.toArray());
        return dataList;
    }

    public List<DynaModel> findUserCatalogRightList(int userId)
    {
        return getJdbcTemplateUtil().queryForList(FIND_USER_CATALOG_RIGHTS, DynaModel.class, new Object[] {new
                Integer(userId)});
    }

    public DynaModel findUserCatalogRights(int userId, String siteNo)
    {
        List<Role_Catalog_Right> list = this.getJdbcTemplateUtil().queryForList(FIND_USER_CATALOG_RIGHTS,
                Role_Catalog_Right.class, new Object[] {siteNo, new Integer(userId)});

        DynaModel rights = new DynaModel();
        String key = "";
        String value = "";
        for (int i = 0; i < list.size(); i++) {
            Role_Catalog_Right roleRight = list.get(i);
            String[] strRight = StringHelper.split(roleRight.getCatalogIdList(), "|");
            for (int k = 0; k < strRight.length; k++) {
                if (strRight[k].indexOf("[") <= 0) {
                    key = strRight[k];
                } else {
                    key = strRight[k].substring(0, strRight[k].indexOf("["));
                    value = strRight[k].substring(strRight[k].indexOf("[") + 1, strRight[k].indexOf("]"));
                }
                String[] temp = StringHelper.split(value, ":");
                if (rights.containsKey(key)) {
                    @SuppressWarnings("unchecked") HashSet<String> hs = (HashSet<String>) rights.get(key);
                    for (int m = 0; m < temp.length; m++) {
                        hs.add(temp[m]);
                    }
                    rights.set(key, hs);
                } else {
                    HashSet<String> hs = new HashSet<String>();
                    hs.add("list");//默认拥有显示权限
                    for (int m = 0; m < temp.length; m++) {
                        hs.add(temp[m]);
                    }
                    rights.set(key, hs);
                }
            }
        }
        return rights;
    }

    public HashSet<String> findUserRights(int userId, String siteNo)
    {
        List<Role_Right> list = this.getJdbcTemplateUtil().queryForList(FIND_USER_RIGHTS, Role_Right.class, new
                Object[] {siteNo, new Integer(userId)});
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < list.size(); i++) {
            Role_Right roleRight = list.get(i);
            String[] strRight = StringHelper.split(roleRight.getCatalogNoList(), "|");
            for (int k = 0; k < strRight.length; k++) {
                hs.add(strRight[k]);
            }
        }
        return hs;
    }

    /**
     * 描述：根据用户ID查询用户所在的角色
     * 作者：袁永君
     * 时间：May 23, 2013 5:34:35 PM
     *
     * @param userId
     * @return
     */
    public List<DynaModel> findUserRoleById(int userId)
    {
        String sql = "SELECT ROLE_ID,ROLENO,NAME FROM T_ROLE WHERE ROLE_ID IN (SELECT ROLE_ID FROM T_ROLE_USER WHERE " +
                "" + "USER_ID = ?)";
        return getJdbcTemplateUtil().queryForList(sql, new Object[] {new Integer(userId)});
    }

    public HashSet<String> findUserSiteRights(int userId)
    {
        List<Role_Site_Right> list = this.getJdbcTemplateUtil().queryForList(FIND_USER_SITE_RIGHTS, Role_Site_Right
                .class, new Object[] {new Integer(userId)});
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < list.size(); i++) {
            Role_Site_Right roleRight = list.get(i);
            String[] strRight = StringHelper.split(roleRight.getSiteNoList(), "|");
            for (int k = 0; k < strRight.length; k++) {
                hs.add(strRight[k]);
            }
        }
        return hs;
    }

    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String siteNo, int roleId, String keyword)
    {
        DBPage<DynaModel> page = null;

        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_USER where id in (select user_id from T_ROLE_USER where role_id=? )");
        argList.add(new Integer(roleId));

        if (!StringHelper.isEmpty(keyword)) {
            sqlBuffer.append(" and (name like ? or uid2 like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }

        sqlBuffer.append(" order by id desc ");

        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null) {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext(); ) {
                User user = new User();
                DynaModel row = (DynaModel) iter.next();
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
    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage<DynaModel> page = null;

        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_ROLE where 1=1 ");
        if (!StringHelper.isEmpty(siteNo)) {
            sqlBuffer.append(" and siteno like ? ");
            argList.add("%" + siteNo + "%");
        }
        if (!StringHelper.isEmpty(keyword)) {
            sqlBuffer.append(" and (roleno like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        sqlBuffer.append(" order by role_id desc ");
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null) {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext(); ) {
                Role role = new Role();
                DynaModel row = (DynaModel) iter.next();
                role.fromMap(row);
                newDataList.add(role);
            }
            page.setData(newDataList);
        }

        return page;
    }

    public List<DynaModel> getRightFunctionAll()
    {
        List<DynaModel> list = this.getJdbcTemplateUtil().queryForList(FIND_RIGHT_MODULE_FUNCTION_ALL, DynaModel.class);
        ArrayList<DynaModel> drList = new ArrayList<DynaModel>();
        for (int i = 0; i < list.size(); i++) {
            DynaModel dr = (DynaModel) list.get(i);
            Right_Function right_function = new Right_Function();
            right_function.fromMap(dr);
            drList.add(right_function);
        }
        return drList;
    }

    public List<DynaModel> getRightFunctionAll(String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_RIGHT_FUNCTION";
        if (StringHelper.isNotEmpty(siteno)) {
            sql += " where siteno=?";
            argList.add(siteno);
        }
        sql += " order by function_code";
        List<DynaModel> list = this.getJdbcTemplateUtil().queryForList(sql, argList.toArray());
        ArrayList<DynaModel> drList = new ArrayList<DynaModel>();
        for (int i = 0; i < list.size(); i++) {
            DynaModel dr = (DynaModel) list.get(i);
            Right_Function right_function = new Right_Function();
            right_function.fromMap(dr);
            drList.add(right_function);
        }
        return drList;
    }

    public List<DynaModel> getRightModuleAll()
    {
        List<DynaModel> list = this.getJdbcTemplateUtil().queryForList(FIND_RIGHT_MODULE_ALL);
        ArrayList<DynaModel> drList = new ArrayList<DynaModel>();
        for (int i = 0; i < list.size(); i++) {
            DynaModel dr = (DynaModel) list.get(i);
            Right_Module right_module = new Right_Module();
            right_module.fromMap(dr);
            drList.add(right_module);
        }
        return drList;
    }

    public List<DynaModel> getRightModuleAll(String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "SELECT * FROM T_RIGHT_MODULE A,T_MANAGE_CATALOG B WHERE A.MODULE_CODE = B.CATALOG_ID";
        if (StringHelper.isNotEmpty(siteno)) {
            sql += " AND A.SITENO = ?";
            argList.add(siteno);
        }
        sql += " AND B.STATE = 1 ORDER BY B.PARENT_ID,B.ORDERLINE";

        List<DynaModel> list = this.getJdbcTemplateUtil().queryForList(sql, argList.toArray());
        ArrayList<DynaModel> drList = new ArrayList<DynaModel>();
        for (int i = 0; i < list.size(); i++) {
            DynaModel dr = (DynaModel) list.get(i);
            Right_Module right_module = new Right_Module();
            right_module.fromMap(dr);
            drList.add(right_module);
        }
        return drList;
    }

    public Role_Catalog_Right getRoleCatalogRight_by_id(int roleId, String siteNo)
    {
        try {
            return this.getJdbcTemplateUtil().queryMap(FIND_ROLE_CATALOG_RIGHT_BY_ID, Role_Catalog_Right.class, new
                    Object[] {new Integer(roleId), siteNo});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Role_Right getRoleRight_by_Id(int roleId)
    {
        try {
            return this.getJdbcTemplateUtil().queryMap(FIND_ROLE_RIGHT_BY_ID, Role_Right.class, new Object[] {new
                    Integer(roleId)});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Role_Right getRoleRight_by_Id(int roleId, String siteNo)
    {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        String sql = "select * from T_ROLE_RIGHT where role_id=?";
        arrayList.add(new Integer(roleId));
        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno=?";
            arrayList.add(siteNo);
        }
        try {
            return this.getJdbcTemplateUtil().queryMap(sql, Role_Right.class, arrayList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Role_Site_Right getRoleSiteRight_by_id(int roleId)
    {
        try {
            return this.getJdbcTemplateUtil().queryMap(FIND_ROLE_SITE_RIGHT_BY_ID, Role_Site_Right.class, new
                    Object[] {new Integer(roleId)});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<DynaModel> getRoleUser(int roleId, String siteNo)
    {
        List<DynaModel> dataList = getJdbcTemplateUtil().queryForList("select * from T_USER where id in (select " +
                "user_id from T_ROLE_USER where role_id = ? and siteno = ?)", new Object[] {new Integer(roleId),
                siteNo});
        ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();

        if (dataList != null) {
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext(); ) {
                DynaModel row = (DynaModel) iter.next();
                User user = new User();
                user.fromMap(row);
                newDataList.add(user);
            }
        }
        return newDataList;
    }

    public Integer[] getUserRole(int userId)
    {
        String sql = "select role_id from T_ROLE_USER where user_id=" + userId;
        return getJdbcTemplateUtil().queryIntArray(sql);
    }

    public void updateModule(Right_Module right_module)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(right_module.toMap());
        getJdbcTemplateUtil().update("t_right_module", dataRow, "module_code", new Integer(right_module
                .getModule_code()));
    }

    public void updateRole(Role role)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(role.toMap());
        getJdbcTemplateUtil().update("T_ROLE", dataRow, "id", new Integer(role.getId()));
    }
}
