package com.jhcz.base.dao.impl;

import com.jhcz.base.dao.BaseDao;
import com.jhcz.base.jdbc.JdbcTemplate;

public class BaseDaoImpl implements BaseDao
{

	@Override
	public JdbcTemplate getJdbcTemplate()
	{
		return new JdbcTemplate(datasource);
	}
}
