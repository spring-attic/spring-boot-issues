package demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log extends HttpServlet {
                private final static Logger LOGGER = LoggerFactory.getLogger(Log.class);
                
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException

  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      LOGGER.debug("Ping!");
      out.println("Ping!");
  }
}