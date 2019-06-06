package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.LoginDatabase;
import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import it.unipd.dei.clef.resource.ClefUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.net.URL;

public class LoginServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

      response.setContentType("text/html");
      request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

      request.getSession().setAttribute("email", request.getParameter("email"));
      request.getSession().setAttribute("password", request.getParameter("password"));

      boolean log = false;
      String email = null;

	    //String referrer = request.getHeader("Referer");
	    //URL ref = new URL(referrer);
      //referrer = ref.getPath().substring(request.getContextPath().length());

      ClefUser clefUser = new ClefUser(request.getParameter("email"), request.getParameter("password"));

      try {
        log = new LoginDatabase(getDataSource().getConnection(), clefUser).Login();
        email = new LoginDatabase(getDataSource().getConnection(), clefUser).getUserEmail();
      }
      catch (SQLException ex) {
        response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        return ;
      }


      request.getSession().setAttribute("log", log);
      request.getSession().setAttribute("email", email);
      response.sendRedirect(request.getHeader("referer"));
      //request.getRequestDispatcher(referrer).forward(request, response);
      //request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
      //response.sendRedirect(request.getRequestURI());
  }

}
