import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jhcz.cms.controller.HelloController;

public class CaseControllerTest
{
    
    private static final Log logger = LogFactory.getLog(CaseControllerTest.class);
    
    private HelloController controller = null;
    
    private XmlWebApplicationContext ctx;
    
    @Before
    public void setUp() throws Exception
    {
        String[] paths = { "classpath:spring-context.xml" };
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
    	controller = (HelloController) ctx.getBean("helloController");
        logger.info("*****testloginValidate start**********");
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/admin/loginAdmin/loginValidate.action");
        request.setRequestURI("/admin/loginAdmin/loginValidate.action");
        request.addParameter("name", "name");
        controller.greeting();
        logger.info("*****testloginValidate end**********");
    }
    
    //	@Test
    //	public void testList()
    //	{
    //		logger.info("*****testList start**********");
    //		MockHttpServletResponse response = new MockHttpServletResponse();
    //		MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
    //		request.setRequestURI("/case/list");
    //		request.addParameter("name", "name");
    //		String returnStr = controller.list(request, response);
    //		List<String> result = (List<String>) request.getAttribute("result");
    //		logger.info("*****result:" + result);
    //		logger.info("*****returnStr:" + returnStr);
    //		logger.info("*****testList end**********");
    //	}
}
