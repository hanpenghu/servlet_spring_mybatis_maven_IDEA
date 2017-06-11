package com.hanhan.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@SuppressWarnings("serial")
@Controller
//下面这个注解是为了把HelloServlet变成helloServlet这种bean
//然后匹配web.xml中的<servlet-name>中的helloServlet从而匹配到网址url中的<url-pattern>
//<servlet>
//<servlet-name>helloServlet</servlet-name>
//<servlet-class>com.hanhan.test.ServletToBeanProxy</servlet-class>
//</servlet>

//<servlet-mapping>
//<servlet-name>helloServlet</servlet-name>
//<url-pattern>/HelloServlet</url-pattern>
//</servlet-mapping>
@Scope("prototype")
public class HelloServlet extends HttpServlet {

    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Post");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet"));
    }

}