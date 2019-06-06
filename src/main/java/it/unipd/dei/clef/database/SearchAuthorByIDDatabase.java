package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Author;
import it.unipd.dei.clef.resource.Paper;
import it.unipd.dei.clef.resource.Likes;

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
	private static final String SELECT_AUTHOR = "SELECT AuthorID FROM Likes WHERE Email = ? AND AuthorID = ?";
	private static final String COUNT_LIKES = "SELECT COUNT(*) FROM Likes WHERE AuthorID = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The AuthorID of the author
	 */
	private final int authorID;
	
	/**
	 * The Like object which stores email of the user and AuthorID of the author
	 */
	private final Likes likes;
	
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
		this.likes = null;
	}

	/**
	 * Creates a new object for searching authors by ID.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param id
	 *            the id of the author.
	 * @param likes
	 *            the like object in which the email of the user and the authorid of the author are stored.
	 */
	public SearchAuthorByIDDatabase(final Connection con, final int id, Likes likes) {
		this.con = con;
		this.authorID = id;
		this.likes = likes;
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
	
	/**
	 * Checks if the user has already liked the author.
	 * 
	 * @return a boolean variable which is true only if the user has liked the author.
	 * 
	 * @throws SQLException
	 *             if any error occurs while searching for authors.
	 */
	public boolean searchAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;
		ResultSet rs = null;
		int id = -1;

		try {

			pstmt_like = con.prepareStatement(SELECT_AUTHOR);
			pstmt_like.setString(1, likes.getEmail());
			pstmt_like.setInt(2, likes.getAuthorID());
			rs = pstmt_like.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("AuthorID");
			}
			
			if(id >= 0)	{
				return true;
			}
			else
				return false;

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}

	}
	
	/**
	 * Counts how many likes an author has.
	 * 
	 * @return the number of likes.
	 * 
	 * @throws SQLException
	 *             if any error occurs while searching for authors.
	 */
	public int countLikes() throws SQLException {

		PreparedStatement pstmt_count = null;
		ResultSet rs = null;
		int count = -1;

		try {

			pstmt_count = con.prepareStatement(COUNT_LIKES);
			pstmt_count.setInt(1, likes.getAuthorID());
			rs = pstmt_count.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("count");
			}
			
			return count;

		} finally {

			if (pstmt_count != null) {
				pstmt_count.close();
			}

			con.close();
		}

	}
}
