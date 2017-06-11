package com.hanhan.test;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.swing.*;
import java.io.IOException;

@SuppressWarnings("serial")
/**
 * 我们自己实现的一个代理类用于将Servlet转为Spring管理的Servlet Bean
 */
//implements SingleThreadModel可以实现单线程,一次只能有一个线程走这个类
public class ServletToBeanProxy extends GenericServlet {
//为了线程安全,不用全局变量
//    private String targetBean;//当前客户端请求的Servlet名字
    private Servlet proxy;//代理Servlet

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //初始化Spring容器
        String targetBean= getServletName();

            this.proxy = (Servlet) wac.getBean(targetBean);//调用ServletBean
            proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean


    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1)
            throws ServletException, IOException {
        proxy.service(arg0, arg1);//在service方法中调用servletbean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
    }
}