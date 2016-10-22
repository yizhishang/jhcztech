package com.jhcz.web.controller;
import java.util.Arrays;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhcz.base.mybatis.util.BeanUtil;
import com.jhcz.base.mybatis.util.PagedResult;
import com.jhcz.base.pojo.Site;
import com.jhcz.web.dao.SiteDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jhcz.base.mybatis.dao.SequenceDao;
import com.jhcz.base.pojo.Sequence;
import com.jhcz.web.exception.BusinessException;

public class CaseControllerTest
{
    
    private static final Log logger = LogFactory.getLog(CaseControllerTest.class);
    
    private RegisterController registerController = null;
    
    private HelloController helloController = null;
    
    private XmlWebApplicationContext ctx;
    
    @Before
    public void setUp() throws Exception
    {
        String[] paths = { "classpath:spring-context.xml","classpath:spring-servlet.xml" };
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
    public void insertMessage() throws BusinessException
    {
    	registerController = (RegisterController) ctx.getBean("registerController");
    	logger.info("*****insertMessage start**********");
    	MockHttpServletRequest request = new MockHttpServletRequest("POST", "/loginValidate.action");
    	request.addParameter("name", "Jack");
    	request.addParameter("mobileNo", "13088840046");
    	request.addParameter("message", "hello world");
    	registerController.insertMessage(request);
    	logger.info("*****insertMessage end**********");
    }
    
    @Test
    public void queryArticlePageByCatalogId() throws BusinessException
    {
    	helloController = (HelloController) ctx.getBean("helloController");
        logger.info("*****insertMessage start**********");
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/loginValidate.action");
        request.addParameter("name", "Jack");
        request.addParameter("mobileNo", "13088840046");
        request.addParameter("message", "hello world");
        helloController.queryArticlePageByCatalogId(2915, 1, 3);
        logger.info("*****insertMessage end**********");
    }
    
    @Test
    public void getNextSequence()
    {
        SequenceDao sequenceDao = (SequenceDao) ctx.getBean("sequenceDao");
        Sequence sequence = sequenceDao.getNextSequence("T_WS_BUSINESS_REGISTER");
        System.out.println(sequence);
    }

    @Test
    public void getSite()
    {
        SiteDao siteDao = (SiteDao) ctx.getBean("siteDao");
        List<Site> list = siteDao.getAllSite();
        System.out.println(list.size());
		PageHelper.startPage(1,1);
		PagedResult<Site> page = BeanUtil.toPagedResult(list);
		System.out.println(page.getPageSize());
	}
}
