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

/**
 * Manages the REST API for the Statistic resource.
 */
public final class StatisticRestResource extends RestResource {

	/**
	 * Creates a new REST resource for managing statistic resources.
	 */
	public StatisticRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(req, res, con);
	}

	/**
	 * Creates the statistics of an athor from the database.
	 *
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void getCoauthorsStatistic() throws IOException {

		List<Coauthor> stat  = null;
		Message m = null;

		try{
			// Parse the URI path to extract the id
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("coauthors") + 9);

			final int id = Integer.parseInt(path.substring(1));

			// Creates a new object for accessing the database and reads the coauthors
			stat = new FindCoauthors(con, id).getCoauthors();

			if(stat != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(stat).toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("Coauthor statistic %d not found.", id), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot read coauthors statistic: unexpected error.", "E5A1", t.getMessage());
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
	public void getYearStatistic() throws IOException {

		List<YearOccurence> stat  = null;
		Message m = null;

		try{
			// parse the URI path to extract the author id
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("years") + 5);

			final int id = Integer.parseInt(path.substring(1));
			
			// creates a new object for accessing the database and reads the statistic
			stat = new FindYears(con, id).getYears();
			
			if(stat != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(stat).toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("Year statistic %d not found.", id), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
			
		} catch (Throwable t) {
			m = new Message("Cannot read years statistic: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}
}
