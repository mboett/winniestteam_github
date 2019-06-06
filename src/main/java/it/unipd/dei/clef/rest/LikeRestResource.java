package it.unipd.dei.clef.rest;

import it.unipd.dei.clef.database.*;
import it.unipd.dei.clef.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Manages the REST API for the Statistic resource.
 */
public final class LikeRestResource extends RestResource {

	/**
	 * Creates a new REST resource for managing statistic resources.
	 */
	public LikeRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(req, res, con);
	}

	/**
	 * Add a like to the the database.
	 */
	public void addLikeToAuthor() throws IOException {

		Likes like = null;
		Message m = null;

		try{
			// Parse the URI path to extract the id
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("like") + 4);

			final int id = Integer.parseInt(path.substring(1));
			
			// Get email from session

			HttpSession session = req.getSession();
			final String email = (String) session.getAttribute("email");
			
			// Creates a new object for accessing the database and reads number of likes of the author
			like = new LikeAuthorDatabase(con, new Likes(email,id)).likeAuthor();
				
			if(like != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				like.toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("AuthorID for like %d not found.", id), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot add like to author: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}

	/**
	 * Creates the statistics of an athor from the database.
	 *
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void removeLikeToAuthor() throws IOException {

		Likes like = null;
		Message m = null;
		int trial = -1;
		String mam = "";

		try{
			// Parse the URI path to extract the id
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("like") + 4);

			final int id = Integer.parseInt(path.substring(1));
			trial = id;
			
			HttpSession session = req.getSession();
			final String email = (String) session.getAttribute("email");
			mam = email;
			
			// Creates a new object for accessing the database and reads number of likes of the author
			like = new DisLikeAuthorDatabase(con, new Likes(email,id)).disLikeAuthor();
				
			if(like != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				like.toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("AuthorID for unlike %d not found.", id), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot remove like to author: unexpected error. " + trial + "-" + mam, "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}
}
