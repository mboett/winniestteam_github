package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Author;
import it.unipd.dei.clef.resource.Paper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches authors by their name.
 * 
 * @author Winniest Team
 * @version 1.00
 * @since 1.00
 */
public final class SearchAuthorByNameDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT Author.AuthorID FROM Author WHERE Author.Name = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The AuthorID of the employee
	 */
	private final String name;

	/**
	 * Creates a new object for searching authors by ID.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param authorName
	 *            the name of the author.
	 */
	public SearchAuthorByNameDatabase(final Connection con, final String authorName) {
		this.con = con;
		this.name = authorName;
	}

	/**
	 * Searches authors by their ID.
	 * 
	 * @return an author with all the papers that he has wrote.
	 * 
	 * @throws SQLException
	 *             if any error occurs while searching for authors.
	 */
	public int searchAuthorByName() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// The results of the search
		int id = 0;
		boolean firstRead = true;
		
		try {
			
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			
			rs.next();
			id = rs.getInt("AuthorID");
			
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

		return id;
	}
}
