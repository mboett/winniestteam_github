package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Author;
import it.unipd.dei.clef.resource.Paper;
import it.unipd.dei.clef.resource.CoauthorsStatistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;


public final class FindCoauthors {

	/*
	 * The SQL statement to be executed
	 */
	
	

	private static final String GET_COAUT_OC = "SELECT CA.AuthorID, CA.Name, COUNT(*) FROM Author AS CA INNER JOIN Write AS W2 ON CA.AuthorID = W2.AuthorID INNER JOIN Paper AS P ON W2.PaperID = P.PaperID INNER JOIN Write AS W1 ON P.PaperID = W1.PaperID INNER JOIN Author AS SI ON W1.AuthorID = SI.AuthorID WHERE SI.AuthorID = ? AND CA.AuthorID <> SI.AuthorID GROUP BY CA.AuthorID";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private static final int MAX_CO = 5;
	
	private final int authorID;

	
	public FindCoauthors (final Connection con, final int id) {
		this.con = con;
		this.authorID = id;
	}

	
	public CoauthorsStatistic getCoauthors() throws SQLException {
	

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// The results of the search
		ArrayList<Author> coautArray = new ArrayList<>();
		Author curCoaut = null;
		int coaID = 0;
		String coautName = null;
		int occ = 0;
		


		try {
			pstmt = con.prepareStatement(GET_COAUT_OC);
			pstmt.setInt(1, authorID);

			rs = pstmt.executeQuery();
			
			

			// Get coauthors with occuurencies
			while (rs.next()) {

				coaID = rs.getInt(1);
				coautName = rs.getString(2);
				occ = rs.getInt(3);
				curCoaut = new Author(authorID, coautName, occ);
				coautArray.add(curCoaut);
			}




		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		
		// Create the final object to be sent
		CoauthorsStatistic stat = new CoauthorsStatistic(coautArray);
		return stat;
	}
}