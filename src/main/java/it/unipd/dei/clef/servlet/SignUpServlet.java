package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.LoginDatabase;
import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import it.unipd.dei.clef.resource.ClefUser;
import it.unipd.dei.clef.database.SignUpDatabase;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends AbstractDatabaseServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ClefUser clefUser = new ClefUser(request.getParameter("email"), request.getParameter("password"));

        try {
			new SignUpDatabase(getDataSource().getConnection(), clefUser).addUser();
			LoginDatabase log_db = new LoginDatabase(getDataSource().getConnection(), clefUser);
	        request.getSession().setAttribute("log", log_db.Login());
			request.getSession().setAttribute("email", email);
			request.getRequestDispatcher("/jsp/signupsuccess.jsp").forward(request, response);
        }
        catch (SQLException ex) {
          request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
            }
    }
}
