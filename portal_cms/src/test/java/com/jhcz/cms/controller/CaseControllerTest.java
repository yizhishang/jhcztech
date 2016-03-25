package com.jhcz.cms.controller;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class CaseControllerTest
{
    
    private static final Log logger = LogFactory.getLog(CaseControllerTest.class);
    
    private LoginController controller = null;
    
    private XmlWebApplicationContext ctx;
    
    @Before
    public void setUp() throws Exception
    {
        String[] paths = { "classpath:spring-context.xml","classpath:spring-mvc.xml" };
        ctx = new XmlWebApplicationContext();
        ctx.setConfigLocations(paths);
        ctx.setServletContext(new MockServletContext(""));
        ctx.refresh();
        String[] names = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names));
    }
    
    @After
    public void tearDown() throws Exception
    {
        ctx.close();
    }
    
    @Test
    public void loginValidate()
    {
    	controller = (LoginController) ctx.getBean("loginController");
        logger.info("*****testloginValidate start**********");
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/login.action");
        request.setRequestURI("/login.action");
//        request.addParameter("name", "name");
        controller.doLogin();
        logger.info("*****testloginValidate end**********");
    }
}
