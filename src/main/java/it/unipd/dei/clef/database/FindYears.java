package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Author;
import it.unipd.dei.clef.resource.Paper;
import it.unipd.dei.clef.resource.YearOccurence;

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


public class FindYears {

	private static final String GET_YEAR_OC = "SELECT Paper.Year, COUNT(*) FROM Paper INNER JOIN Write ON Paper.PaperID = Write.PaperID INNER JOIN Author ON Write.AuthorID = Author.AuthorID WHERE Author.AuthorID = ? GROUP BY Paper.year";

	private final Connection con;
	
	private final int authorID;

	
	public FindYears (final Connection con, final int id) {
		this.con = con;
		this.authorID = id;
	}
	
	public List<YearOccurence> getYears() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// The results of the search
		final List<YearOccurence> yearArray = new ArrayList<>();
		
		YearOccurence curYear = null;
		int year = -1;
		int occ = -1;
		int checkYear = -1;

		try {
			pstmt = con.prepareStatement(GET_YEAR_OC);
			pstmt.setInt(1, authorID);

			rs = pstmt.executeQuery();
			
			boolean firstYear = true;

			// Get paperID of the author publications
			while (rs.next()) {

				// Retrive informations from database
				year = rs.getInt(1);
				occ = rs.getInt(2);
				
				if (firstYear){
					firstYear = false;
					checkYear = year + 1;
				}
				
				// Insert years with zero pubblications
				while (year > checkYear){
					yearArray.add(new YearOccurence(checkYear, 0));
					checkYear++;
				}
				
				// Add the current year
				yearArray.add(new YearOccurence(year, occ));
				
				// Update CheckYear
				checkYear++;
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
		
		return yearArray;
	}
}



