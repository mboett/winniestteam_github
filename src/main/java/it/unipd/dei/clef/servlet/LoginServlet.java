package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.LoginDatabase;
import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import it.unipd.dei.clef.resource.ClefUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends AbstractDatabaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      request.getSession().setAttribute("email", request.getParameter("email"));
      request.getSession().setAttribute("password", request.getParameter("password"));

      ClefUser clefUser = new ClefUser(request.getParameter("email"), request.getParameter("password"));

      try {
        LoginDatabase log_db = new LoginDatabase(getDataSource().getConnection(), clefUser);
        request.getSession().setAttribute("log", log_db.Login());
        request.getSession().setAttribute("email", log_db.getUserEmail());
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
      }
      catch (SQLException ex) {
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
      }

  }

}
