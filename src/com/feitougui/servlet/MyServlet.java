package com.feitougui.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/myServlet")
public class MyServlet implements Servlet {
//    tomcat���Զ�ͨ��������Ƶ����޲ι��췽����������
    public MyServlet(){
        System.out.println("Servlet����������Servlet����");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(servletConfig.getServletName());
        System.out.println("���ڳ�ʼ��");
    }

    @Override
    public ServletConfig getServletConfig() {

        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        �ڵ�ַ�����룿id=1�������󴫲�
        String id=servletRequest.getParameter("id");
        System.out.println("��Web.xml��������ӳ���ļ���Ϳ���·�����ʵ����ˣ�����id��"+id);
//        �������룬�����ַ�����
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("�ͻ��ˣ����ѽ��յ�������󣡷���id��"+id);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("���ڴݻ�servlet!");
    }
}
