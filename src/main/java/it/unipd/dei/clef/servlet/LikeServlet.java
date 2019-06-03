package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.LikeAuthorDatabase;
import it.unipd.dei.clef.servlet.AbstractDatabaseServlet;
import it.unipd.dei.clef.resource.Likes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeServlet extends AbstractDatabaseServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String email = request.getParameter("email");
        String author_name = request.getParameter("author_name");

        Likes likes = null;

        try {

          LikeAuthorDatabase authorID = new LikeAuthorDatabase(getDataSource().getConnection(), likes, author_name);

          likes = new Likes(request.getParameter("email"), authorID.findAuthor());
          request.getRequestDispatcher("/jsp/author.jsp").forward(request, response);


        }
        catch (SQLException ex) {
              request.getRequestDispatcher("/jsp/author.jsp").forward(request, response);
            }
    }
}
