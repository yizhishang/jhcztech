package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.plat.domain.UserPasswordLog;

public interface UserPasswordLogDao
{
    
    public void addLog(UserPasswordLog log);
    
    public void deleteLog(int id);
    
    /**
     * @描述：查询密码最后更新时间 
     * @作者：袁永君
     * @时间：2015-12-12 下午06:30:10
     * @param loginId 用户名
     * @return String 最后一次创建时间
     */
    public String getPwdValidity(String loginId);
    
    public List<Object> queryLogByUserId(String uid);
}
