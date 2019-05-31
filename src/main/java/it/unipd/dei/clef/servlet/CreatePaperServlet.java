package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.database.CreatePaperDatabase;
import it.unipd.dei.clef.resource.Paper;
import it.unipd.dei.clef.resource.Author;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CreatePaperServlet extends AbstractDatabaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String ee = request.getParameter("ee");
		String key = null;
		String mDate = null;

		Paper paper = null;
		Author author = null;

		try {

			CreatePaperDatabase lastAuthorID = new CreatePaperDatabase(getDataSource().getConnection(), paper, author);
			CreatePaperDatabase lastPaperID = new CreatePaperDatabase(getDataSource().getConnection(), paper, author);

			paper = new Paper(lastAuthorID.maxPaperId(), request.getParameter("title"), request.getParameter("year"), mDate, key, request.getParameter("ee"));
			author = new Author(lastPaperID.maxAuthorId(), request.getParameter("name"));

			new CreatePaperDatabase(getDataSource().getConnection(), paper, author).addPaper();
		  request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);

	} catch (SQLException ex){
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
  }
}

}
