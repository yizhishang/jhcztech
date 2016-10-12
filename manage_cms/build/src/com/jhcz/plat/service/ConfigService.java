package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.domain.Config;
import com.jhcz.base.jdbc.DBPage;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 16:37:25
 */
public interface ConfigService
{
    
    /**
     * 添加新的配置信息
     *
     * @param config
     * @return
     */
    public void addConfig(Config config);

    /**
     * 删除配置信息
     *
     * @param id
     */
    public void deleteConfig(int id);

    /**
     * 根据ID，得到某一个配置的详细信息
     *
     * @param id 配置的ID值
     * @return
     */
    public Config findConfigById(int id);

    /**
     * 获得所有的配置信息，返回列表中的每一项都是一个config对象
     *
     * @return
     */
    public List<Config> getAllSysConfig();

    /**
     * 获得分页数据
     *
     * @return
     */
    public DBPage getPageData(int curPage, int numPerPage, String keyword, String siteNo);

    /**
     * 读取权限URL
     */
    public List<Object> loadRight();

    /**
     * 查询加载权限信息
     * @param isteno
     * @return
     */
    public List<Object> loadRight(String isteno);

    /**
     * 更新配置信息
     * @param config
     * @return
     */
    public void updateConfig(Config config);

}
