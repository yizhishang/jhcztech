<%@page import="java.util.ArrayList"%>
<%@page import="com.jhcz.base.jdbc.DataRow"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.jhcz.base.jdbc.JdbcTemplate"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%
	String uSql = "";
	
	String querySql = "SELECT NAME,CURRENT_VALUE FROM T_SEQUENCE ORDER BY NAME";
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	List dataList = jdbcTemplate.query(querySql);
	List argList = new ArrayList();
	String name = "";
	for (Iterator iter = dataList.iterator(); iter.hasNext();)
	{
		uSql = "";
		DataRow data = (DataRow) iter.next();
		String tName = data.getString("name");
		int currentValue = data.getInt("current_value");
		name = "";
		if (tName.startsWith("T_"))
		{
			name = "SEQ_" + tName.substring(2);
			uSql = "create sequence " + name;
			uSql += " minvalue 1";
			uSql += " maxvalue 999999999999";
			uSql += " start with " + ((currentValue == 0) ? 1 : currentValue);
			uSql += " increment by 1";
			uSql += " nocache";
			jdbcTemplate.update(uSql, argList.toArray());
		}
	}
%>