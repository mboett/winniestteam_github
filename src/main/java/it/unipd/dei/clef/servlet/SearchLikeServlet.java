package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.SearchLikeDatabase;
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

public class SearchLikeServlet extends AbstractDatabaseServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String email = null;
		Likes likes = null;
		int authorID = -1;
		boolean fav = false;
		String temp = null;

		try {
			HttpSession session = request.getSession();
			 //Enumeration attributeNames = session.getAttributeNames();
			 //String name = (String) attributeNames.nextElement();
			email = (String) session.getAttribute("email");
			authorID = Integer.parseInt(request.getParameter("id"));
			 
			likes = new Likes(email, authorID);

			fav = new SearchLikeDatabase(getDataSource().getConnection(), likes).searchAuthor();	

        } catch (SQLException ex) {
              //request.getRequestDispatcher("/jsp/papers.jsp").forward(request, response);
              response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
              return ;
            }
						
		request.setAttribute("fav", fav);
					
		request.getRequestDispatcher("/jsp/author.jsp").forward(request, response);
		
		//request.getRequestDispatcher("search-author?id="+Integer.toString(authorID)).forward(request, response);
		
		//response.sendRedirect("search-author?id="+Integer.toString(authorID));
    }
}