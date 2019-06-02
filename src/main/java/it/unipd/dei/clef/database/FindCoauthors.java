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

	/**
	 * The SQL statement to be executed
	 */
	private static final String GET_PAPERS = "SELECT Paper.PaperID, Author.Name FROM Paper INNER JOIN Write ON Paper.PaperID = Write.PaperID INNER JOIN Author ON Write.AuthorID = Author.AuthorID WHERE Author.AuthorID = ?";
	private static final String GET_COAUT = "SELECT Author.Name, Author.AuthorID FROM Author INNER JOIN Write ON Author.AuthorID = Write.AuthorID INNER JOIN Paper ON Write.PaperID = Paper.PaperID WHERE Paper.PaperID = ?";
	

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

		PreparedStatement paperPstmt = null;
		PreparedStatement coautPstmt = null;
		ResultSet rs = null;

		// The results of the search

		String autName = null;
		Author coauthor = null;
		int coautID;
		String coautName = null;
		ArrayList<Author> coauthorArray = new ArrayList<>();
		int temp = 0;
		HashMap<Author, Integer> authorTable = null;
		ArrayList<Integer> pubList = new ArrayList<>();
		boolean firstRead = true;
		try {
			paperPstmt = con.prepareStatement(GET_PAPERS);
			paperPstmt.setInt(1, authorID);

			rs = paperPstmt.executeQuery();
			
			// Get paperID of the author publications
			while (rs.next()) {

				if (firstRead) {
					autName = rs.getString("Author.Name");
					firstRead = false;
				}

				pubList.add(rs.getInt("Paper.PaperID"));

			}


			coautPstmt = con.prepareStatement(GET_COAUT);

			authorTable = new HashMap<>();
    		

			// Get all coaut for each publication
			for (int i=0; i<pubList.size(); i++) {

				coautPstmt.setInt(1, pubList.get(i));
				rs = paperPstmt.executeQuery();

				while (rs.next()) {

					coautID = rs.getInt("Author.AuthorID");
					

					if (coautID != authorID) {

						coautName = rs.getString("Author.Name");
						coauthor = new Author(coautID, coautName, -1);
						temp = authorTable.getOrDefault(coauthor, 0);
						authorTable.put(coauthor, temp + 1);

					}



				}
			}
			List<Map.Entry<Author, Integer>> sortedTable = new ArrayList<>(authorTable.entrySet());
			sortedTable.sort(Map.Entry.comparingByValue());


			Map.Entry<Author, Integer> e;

			for (int i=1; i<=MAX_CO; i++) {

				if (i<=sortedTable.size())	{

		    		e = sortedTable.get(sortedTable.size()-i);
					
					coautName = e.getKey().getName();
					coautID = e.getKey().getID();
					temp = e.getValue();
					coauthor = new Author(coautID, coautName, temp);
					coauthorArray.add(coauthor);
				}
				
			}


		} finally {
			if (rs != null) {
				rs.close();
			}

			if (paperPstmt != null && coautPstmt != null) {
				paperPstmt.close();
				coautPstmt.close();
			}

			con.close();
		}
		
		// Build the result to be sent
		CoauthorsStatistic stat = new CoauthorsStatistic(coauthorArray);
		return stat;
	}
}