package com.jhcz.base.dao;

import com.jhcz.base.jdbc.JdbcTemplate;


public interface BaseDao
{
	
	String datasource = "web";
	
	public JdbcTemplate getJdbcTemplate();
}
