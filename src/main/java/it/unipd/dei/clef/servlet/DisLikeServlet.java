package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.DisLikeAuthorDatabase;
import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import it.unipd.dei.clef.resource.Likes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DisLikeServlet extends AbstractDatabaseServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		  int authorID = -1;

      try {

			     HttpSession session = request.getSession();
			     //Enumeration attributeNames = session.getAttributeNames();
			     //String name = (String) attributeNames.nextElement();

			     authorID = Integer.parseInt(request.getParameter("id"));

           //DisLikeAuthorDatabase check = new DisLikeAuthorDatabase(getDataSource().getConnection(), likes, email, authorID);
           //request.setAttribute("favorite", check.falseLikedAuthor());

				   new DisLikeAuthorDatabase(getDataSource().getConnection(), authorID).disLikeAuthor();

           request.getRequestDispatcher("/jsp/search-author?id="+Integer.toString(authorID)).forward(request, response);

        } catch (SQLException ex) {
              //request.getRequestDispatcher("/jsp/papers.jsp").forward(request, response);
              response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
              return ;
            }
    }
}
