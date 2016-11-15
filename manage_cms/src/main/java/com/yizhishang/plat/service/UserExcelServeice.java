package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserExcelServeice extends BaseService
{

    public List<DynaModel> getUser(String keyword, String loanNo)
    {
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_USER where 1=1 ");
        if (!StringHelper.isEmpty(keyword)) {
            sqlBuffer.append(" and (uid2 like ? or name like ? ) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        if (!StringHelper.isEmpty(loanNo)) {
            sqlBuffer.append(" order by login_times desc ");
        } else {
            sqlBuffer.append(" order by id desc ");
        }
        return getJdbcTemplateUtil().queryForList(sqlBuffer.toString(), argList.toArray());
    }

}
