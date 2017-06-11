package com.hanhan.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")//这个注解是为了把HelloServlet1变成helloServlet1这种bean
//然后匹配web.xml中的<servlet-name>中的helloServlet1从而匹配到网址url中的<url-pattern>
//<servlet>
//<servlet-name>helloServlet1</servlet-name>
//<servlet-class>com.hanhan.test.ServletToBeanProxy</servlet-class>
//</servlet>

//<servlet-mapping>
//<servlet-name>helloServlet1</servlet-name>
//<url-pattern>/HelloServlet1</url-pattern>
//</servlet-mapping>
public class HelloServlet1 extends HttpServlet {

    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet1"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Post");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet1"));
    }

}