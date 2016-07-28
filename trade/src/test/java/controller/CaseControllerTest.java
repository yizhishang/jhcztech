package controller;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jhcz.trade.fundManage.contorller.FundController;

public class CaseControllerTest
{
    
    private static final Log logger = LogFactory.getLog(CaseControllerTest.class);
    
    private FundController fundController = null;
    
    private XmlWebApplicationContext ctx;
    
    @Before
    public void setUp() throws Exception
    {
        String[] paths = { "classpath:spring/applicationContext.xml", "classpath:spring/biz-mvc.xml", "classpath:spring/applicationContext-quartz.xml", "classpath:spring/applicationContext-freemarker.xml"};
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
    public void listAllFund()
    {
    	fundController = (FundController) ctx.getBean("fundController");
    	logger.info("*****insertMessage start**********");
    	MockHttpServletRequest request = new MockHttpServletRequest("POST", "/fund/listAllFund");
    	MockHttpServletResponse response = new MockHttpServletResponse();
    	request.addParameter("pageSize", "10");
    	request.addParameter("pageIndex", "2");
    	fundController.listAllFund(request,response);
    	logger.info("*****insertMessage end**********");
    }
}
