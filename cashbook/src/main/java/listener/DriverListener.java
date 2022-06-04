package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DriverListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce)  { 
	 sce.getServletContext().setAttribute("currentCount", 0);// 세션의 개수가 나온다. 
 	System.out.println("db드라이브 로딩...");
      try {
     	 Class.forName("org.mariadb.jdbc.Driver");
      } catch(ClassNotFoundException e) {
     	 e.printStackTrace();
      }
    }
	
}
