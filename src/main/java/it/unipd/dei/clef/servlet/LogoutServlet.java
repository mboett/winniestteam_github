package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class LogoutServlet extends AbstractDatabaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().setAttribute("log", false);
        request.getSession().invalidate();
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

}
