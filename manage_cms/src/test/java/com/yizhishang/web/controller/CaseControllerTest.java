package com.yizhishang.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.yizhishang.base.domain.Config;
import com.yizhishang.base.jdbc.JdbcTemplateUtil;
import com.yizhishang.base.service.ConfigService;
import com.yizhishang.plat.domain.EnumItem;

public class CaseControllerTest
{

	private static final Logger logger = LoggerFactory.getLogger(CaseControllerTest.class);

	private XmlWebApplicationContext ctx;

	@Before public void setUp() throws Exception
	{
		String[] paths = { "classpath:root-context.xml", "base-servlet.xml" };
		ctx = new XmlWebApplicationContext();
		ctx.setConfigLocations(paths);
		ctx.setServletContext(new MockServletContext(""));
		ctx.refresh();
		String[] names = ctx.getBeanDefinitionNames();
		logger.info(Arrays.toString(names));
	}

	@After public void tearDown() throws Exception
	{
		ctx.close();
	}

	public void getSite()
	{
		ConfigService configService = (ConfigService) ctx.getBean("configService");
		List<Config> list = configService.getAllSysConfig();
		System.out.println(list.size());
	}
	
	@Test
    public void testJdbc() throws IllegalAccessException, InvocationTargetException
    {
		String sql = "select A.*,B.ENUM_NAME,B.ENUM_VALUE from T_ENUM_VALUE A,T_ENUM_TYPE B where A.ENUM_TYPE = B.ENUM_VALUE  and A.enum_type='is_head'  order by A.enum_type desc,A.orderline desc ";
        JdbcTemplateUtil jdbc = (JdbcTemplateUtil) ctx.getBean("jdbcTemplateUtil");
        List<EnumItem> list = jdbc.queryForList(sql, EnumItem.class);
        System.out.println(list);
    }
}
