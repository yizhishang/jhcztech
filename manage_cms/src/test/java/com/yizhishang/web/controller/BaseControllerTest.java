package com.yizhishang.web.controller;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述:
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016/7/13 0013
 * 创建时间: 21:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:root-context.xml","classpath*:base-servlet.xml"})
public class BaseControllerTest {

    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    // 执行测试方法之前初始化模拟request,response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

}