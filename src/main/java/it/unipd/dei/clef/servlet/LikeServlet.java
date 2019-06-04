package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.LikeAuthorDatabase;
import it.unipd.dei.clef.database.SearchAuthorByNameDatabase;
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

public class LikeServlet extends AbstractDatabaseServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

      String email = null;
		  String author_name = null;
      Likes likes = null;
		  int authorID = -1;
		  LikeAuthorDatabase likeObj = null;

      try {

			     HttpSession session = request.getSession();
			     //Enumeration attributeNames = session.getAttributeNames();
			     //String name = (String) attributeNames.nextElement();
			     email = (String) session.getAttribute("email");
			     author_name = request.getParameter("name");

			     authorID = new SearchAuthorByNameDatabase(getDataSource().getConnection(), author_name).searchAuthorByName();

			     likes = new Likes(email, authorID);

           new LikeAuthorDatabase(getDataSource().getConnection(), likes).likeAuthor();

			     request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);

        } catch (SQLException ex) {
              //request.getRequestDispatcher("/jsp/papers.jsp").forward(request, response);
              response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
              return ;
            }
    }
}
