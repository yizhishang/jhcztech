package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 对T_B_BRANCH表进行操作的服务类
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-27
 * 创建时间: 下午10:59:11
 */
@Service
public class BranchService extends BaseService
{

    private static Logger logger = LoggerFactory.getLogger(BranchService.class);

    /**
     * 添加营业网点信息
     *
     * @param data
     */
    public void add(DynaModel data)
    {
        String branchId = getSeqValue("T_B_BRANCH");
        data.set("branchid", branchId);
        getJdbcTemplateUtil().insert("T_B_BRANCH", data);
    }

    /**
     * 删除营业部信息
     *
     * @param branchId
     */
    public void delte(int branchId)
    {
        getJdbcTemplateUtil().delete("T_B_BRANCH", "BranchId", new Integer(branchId));
    }

    /**
     * 根据营业部ID，查找相应的营业部信息
     *
     * @param branchId
     * @return
     */
    public DynaModel findBranchById(int branchId)
    {
        String sql = "select * from T_B_BRANCH where BranchId=?";
        ArrayList<Integer> argList = new ArrayList<Integer>();
        argList.add(new Integer(branchId));
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 根据营业部ID，查找相应的营业部信息
     *
     * @param branchId
     * @param siteNo
     * @return
     */
    public DynaModel findBranchById(int branchId, String siteNo)
    {
        String sql = "select * from T_B_BRANCH where 1=1";
        ArrayList<Object> argList = new ArrayList<Object>();
        if (branchId != 0) {
            sql += " and BranchId=?";
            argList.add(new Integer(branchId));
        }

        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno=?";
            argList.add(siteNo);
        }
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 根据营业部编号，查找相应的营业部信息
     *
     * @param branchNo
     * @return
     */
    public DynaModel findBranchByNo(String branchNo)
    {
        String sql = "select * from T_B_BRANCH where BranchNo=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(branchNo);
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 描述：根据营业部编号和站点编号查找营业部信息
     *
     * @param branchNo 营业部编号
     * @param siteNo   站点编号
     * @return
     */
    public DynaModel findBranchByNo(String branchNo, String siteNo)
    {
        String sql = "select * from T_B_BRANCH where 1=1";
        ArrayList<Object> argList = new ArrayList<Object>();
        if (StringHelper.isNotEmpty(branchNo)) {
            sql += " and branchno=?";
            argList.add(branchNo);
        }

        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno=?";
            argList.add(siteNo);
        }
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 描述:  信息类型
     * 作者:
     * 创建日期: 2009-3-31
     * 创建时间: 下午12:51:24
     *
     * @return
     */
    public List<DynaModel> findInfoType()
    {
        List<DynaModel> rstList = null;
        try {
            String sql = "SELECT ID,NAME FROM T_B_BRANCH_INFOTYPE ORDER BY ID ASC";
            rstList = getJdbcTemplateUtil().queryForList(sql);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return rstList;
    }

    /**
     * 查询所有的营业网点信息
     *
     * @return
     */
    public List<DynaModel> getAll()
    {
        String sql = "select * from T_B_BRANCH order by Branchno asc";
        return getJdbcTemplateUtil().queryForList(sql);
    }

    /**
     * 分页查询营业网点信息
     *
     * @param curPage    当前第几页
     * @param rowOfPage  每页多少条记录
     * @param branchName 网点名称，模糊匹配
     * @return
     */
    public DBPage<DynaModel> getPageData(int curPage, int rowOfPage, String branchName, String branchNo, String siteNo)
    {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select A.*,B.REGIONNAME,B.AREA from T_B_BRANCH A LEFT JOIN T_B_REGION B ON A.REGION = B.ID  "
                + "WHERE 1 = 1");
        ArrayList<Object> argList = new ArrayList<Object>();
        if (!StringHelper.isEmpty(branchName)) //若为空则查全部
        {
            sqlBuf.append(" and A.BranchName like ? ");
            argList.add("%" + branchName + "%");
        }

        if (StringHelper.isNotEmpty(siteNo)) {
            sqlBuf.append(" and A.siteno=?");
            argList.add(siteNo);
        }

        if (StringHelper.isNotEmpty(branchNo)) {
            sqlBuf.append(" and A.branchno=?");
            argList.add(String.valueOf(branchNo));
        }
        sqlBuf.append(" order by A.ORDERNUM ASC ");
        return getJdbcTemplateUtil().queryPage(sqlBuf.toString(), argList.toArray(), curPage, rowOfPage);
    }

    /**
     * 判断营业部编号是否已经存在
     *
     * @param branchNo
     * @return
     */
    public boolean isBranchNoExist(String branchNo)
    {
        String sql = "select BranchNo from T_B_BRANCH where BranchNo=?";
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(branchNo);
        try {
            return (getJdbcTemplateUtil().queryMap(sql, argList.toArray()) != null);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 描述：判断营业部编号是否已经存在
     *
     * @param branchNo
     * @param siteNo
     * @return
     */
    public boolean isBranchNoExist(String branchNo, String siteNo)
    {
        String sql = "select BranchNo from T_B_BRANCH where 1=1";
        ArrayList<Object> argList = new ArrayList<Object>();
        if (StringHelper.isNotEmpty(branchNo)) {
            sql += " and BranchNo=?";
            argList.add(branchNo);
        }

        if (StringHelper.isNotEmpty(siteNo)) {
            sql += " and siteno=?";
            argList.add(siteNo);
        }
        try {
            return (getJdbcTemplateUtil().queryMap(sql, argList.toArray()) != null);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 更新营业网点信息
     *
     * @param data
     */
    public void update(DynaModel data)
    {
        int branchId = data.getInt("branchid");
        getJdbcTemplateUtil().update("T_B_BRANCH", data, "BranchId", new Integer(branchId));
    }
}
