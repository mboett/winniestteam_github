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
 * Searches authors by their ID.
 * 
 * @author Winniest Team
 * @version 1.00
 * @since 1.00
 */
public final class SearchAuthorByIDDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT Author.AuthorID, Paper.PaperID, Author.Name, Paper.Title, Paper.Year, Paper.MDate, Paper.Key, Paper.DOI FROM Author INNER JOIN write ON Author.AuthorID = Write.AuthorID INNER JOIN Paper on Write.PaperID = Paper.PaperID WHERE Author.AuthorID = ? ORDER BY Paper.Year ASC";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The AuthorID of the employee
	 */
	private final int authorID;

	/**
	 * Creates a new object for searching authors by ID.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param id
	 *            the id of the author.
	 */
	public SearchAuthorByIDDatabase(final Connection con, final int id) {
		this.con = con;
		this.authorID = id;
	}

	/**
	 * Searches authors by their ID.
	 * 
	 * @return an author with all the papers that he has wrote.
	 * 
	 * @throws SQLException
	 *             if any error occurs while searching for authors.
	 */
	public Author searchAuthorByID() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// The results of the search
		Author author = null;
		boolean firstRead = true;
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, authorID);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (firstRead){
					// Create the author
					author = new Author(rs.getInt("AuthorID"), rs.getString("Name"));
					firstRead = false;
				}
				
				// Add Paper to the author
				author.addPub(new Paper(rs.getInt("PaperID"), rs.getString("Title"),
										rs.getString("Year"), rs.getString("MDate"),
										rs.getString("Key"), rs.getString("DOI")));
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

		return author;
	}
}
