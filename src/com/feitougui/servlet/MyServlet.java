package com.feitougui.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/myServlet")
public class MyServlet implements Servlet {
//    tomcat会自动通过反射机制调用无参构造方法创建对象
    public MyServlet(){
        System.out.println("Servlet容器创建了Servlet对象！");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(servletConfig.getServletName());
        System.out.println("正在初始化");
    }

    @Override
    public ServletConfig getServletConfig() {

        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        在地址栏输入？id=1进行请求传参
        String id=servletRequest.getParameter("id");
        System.out.println("在Web.xml中配置了映射文件后就可以路径访问到我了！访问id是"+id);
//        避免乱码，设置字符类型
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("客户端，我已接收到你的请求！访问id是"+id);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("正在摧毁servlet!");
    }
}
