package com.jhcz.plat.service;

import java.util.ArrayList;
import java.util.List;

import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;

public class UserExcelServeice extends BaseService
{

	public List<Object> getUser(String keyword, String loanNo)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_USER where 1=1 ");
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (uid2 like ? or name like ? ) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		if (!StringHelper.isEmpty(loanNo))
		{
			sqlBuffer.append(" order by login_times desc ");
		}
		else
		{
			sqlBuffer.append(" order by id desc ");
		}
		return getJdbcTemplate().query(sqlBuffer.toString(), argList.toArray());
	}
	
}
