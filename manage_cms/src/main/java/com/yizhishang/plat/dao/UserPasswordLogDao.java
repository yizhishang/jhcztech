package com.yizhishang.plat.dao;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.plat.domain.UserPasswordLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-12
 * 创建时间: 15:42:54
 */
@Repository
public class UserPasswordLogDao extends BaseDao
{

    public void addLog(UserPasswordLog log)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(log.toMap());
        getJdbcTemplateUtil().insert("T_USER_PASSWORD_LOG", dataRow);
    }

    public void deleteLog(int id)
    {
        getJdbcTemplateUtil().delete("T_USER_PASSWORD_LOG", "LOG_ID", new Integer(id));
    }

    /**
     * @param loginId 用户名
     * @return String 最后一次创建时间
     * @描述：查询密码最后更新时间
     * @作者：袁永君
     * @时间：2015-12-12 下午06:30:10
     */
    public String getPwdValidity(String loginId)
    {
        String sql = "SELECT CREATE_DATE FROM T_USER_PASSWORD_LOG WHERE CREATE_BY = ? ORDER BY CREATE_DATE DESC";
        return getJdbcTemplateUtil().queryString(sql, new Object[] {loginId});
    }

    public List<DynaModel> queryLogByUserId(String uid)
    {
        return getJdbcTemplateUtil().queryForList("SELECT LOG_ID FROM T_USER_PASSWORD_LOG WHERE CREATE_BY = ? ", new
                Object[] {uid});
    }
}
