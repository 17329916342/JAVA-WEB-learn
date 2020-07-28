# NOTE

- #### 什么是Servlet

  ​	Servlet是Java Web开发的基石，与平台无关的服务器组件，它是运行在Servlet容器/web应用服务器/Tomcat，负责与客户端进行通信。

  ​	Servlet的功能：

  1. 创建并返回基于客户请求的动态HTML页面。
  2. 与数据库进行通信。

    <<<<<<< HEAD
    =======

- #### 如何使用Servlet？

  ​	servlet本身就是一组接口，自定义一个类，并且实现Servlet接口，这个类具备了接受客户请求以及做出响应的功能。

  ```java
  public class MyServlet implements Servlet {
  
      @Override
      public void init(ServletConfig servletConfig) throws ServletException {
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
      }
  }
  
  ```
  
  ​	其实servlet的拷贝是放在Tomcat的WEB-INF文件内的，所以客户端是没有访问的权限，因此只能通过web.xml配置文件配置映射才能够访问
  
  

- #### Servlet访问方式(基于xml的配置映射方式，基于注解方式)

  因为项目部署到tomcat中的时候，Servlet是放在web-inf文件夹中的，客户端无权直接访问，所以需要配置映射来间接访问。

  1. 基于xml文件的配置方式（在web.xml文件中配置）

     ```java
     <?xml version="1.0" encoding="UTF-8"?>
     <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
              version="4.0">
         <servlet>
             <servlet-name>myservlet</servlet-name>
             <servlet-class>com.feitougui.servlet.MyServlet</servlet-class>
         </servlet>
         <servlet-mapping>
             <!--通过name来关联相关的servlet-->
             <servlet-name>myservlet</servlet-name>
             <url-pattern>/myServlet</url-pattern>
         </servlet-mapping>
     </web-app>
     ```

  2. 基于注解的方式(更加简洁方便,一般只考虑用注解的方式)

     在自定义的servlet类头加@WebServlet("/myServlet(mappting-url-partern)"),不需要在web.xml中配置任何servlet映射。

     ```java
     @WebServlet("/myServlet")
     public class MyServlet implements Servlet {
     }
     ```

     

- #### servlet的生命周期


1. 当浏览器访问Servlet的时候，Tomcat会查询当前Servlet的实例化对象是否存在，如果不存在，则通过反射机制动态创建对象，如果存在，直接执行第三步。
2. 调用init方法完成初始化操作。
3. 调用service方法完成业务逻辑操作。
4. 关闭tomcat，会调用destroy方法，释放当前对象所占用的资源。

Servlet的生命周期方法：无参构造函数、init、service、destroy

1. 无参构造函数只调用一次，创建对象。
2. init只调用一次，初始化对象。
3. service调用N次，执行业务方法。
4. destory只调用一次，卸载对象。

- #### ServletConfig

  该接口用来描述Servlet的基本信息。

  getServletName() 返回Servlet的全类名（即包括根目录的类名 ） 

  getInitParameter(String key)	获取init参数的值，在web.xml中配置

  