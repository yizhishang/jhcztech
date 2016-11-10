package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述: 对T_B_BRANCH表进行操作的服务类
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-27
 * 创建时间: 下午10:59:11
 */
public interface BranchService
{

    /**
    * 添加营业网点信息
    * @param data
    */
    public void add(DataRow data);

    /**
    * 删除营业部信息
    * @param branchId
    */
    public void delte(int branchId);

    /**
    * 根据营业部ID，查找相应的营业部信息
    * @param branchId
    * @return
    */
    public DataRow findBranchById(int branchId);

    /**
    *  根据营业部ID，查找相应的营业部信息
    * @param branchId
    * @param siteNo
    * @return
    */
    public DataRow findBranchById(int branchId, String siteNo);

    /**
    * 根据营业部编号，查找相应的营业部信息
    * @param branchNo
    * @return
    */
    public DataRow findBranchByNo(String branchNo);

    /**
    * 描述：根据营业部编号和站点编号查找营业部信息
    * @param branchNo 营业部编号
    * @param siteNo   站点编号
    * @return
    */
    public DataRow findBranchByNo(String branchNo, String siteNo);

    /**
    * 描述:  信息类型
    * 作者:	 
    * 创建日期: 2009-3-31
    * 创建时间: 下午12:51:24
    * @return
    */
    public List<Object> findInfoType();

    /**
    * 查询所有的营业网点信息
    * @return
    */
    public List<Object> getAll();

    /**
    * 分页查询营业网点信息
    * @param curPage    当前第几页
    * @param rowOfPage  每页多少条记录
    * @param branchName 网点名称，模糊匹配
    * @return
    */
    public DBPage getPageData(int curPage, int rowOfPage, String branchName, String branchNo, String siteNo);

    /**
    * 判断营业部编号是否已经存在
    * @param branchNo
    * @return
    */
    public boolean isBranchNoExist(String branchNo);

    /**
    * 描述：判断营业部编号是否已经存在
    * @param branchNo
    * @param siteNo
    * @return
    */
    public boolean isBranchNoExist(String branchNo, String siteNo);

    /**
    * 更新营业网点信息
    * @param data
    */
    public void update(DataRow data);
}
