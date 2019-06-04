package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Author;
import it.unipd.dei.clef.resource.ClefUser;

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
public final class ShowFavouritesDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT Author.Name FROM Author INNER JOIN Likes ON Author.AuthorID = Likes.AuthorID INNER JOIN ClefUser on Likes.Email = ClefUser.Email WHERE ClefUser.Email = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The AuthorID of the employee
	 */
	private final String email;

	/**
	 * Creates a new object for searching authors by ID.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param email
	 *            the id of the author.
	 */
	public ShowFavouritesDatabase(final Connection con, final String email) {
		this.con = con;
		this.email = email;
	}

	/**
	 * Searches authors by their ID.
	 * 
	 * @return an author with all the papers that he has wrote.
	 * 
	 * @throws SQLException
	 *             if any error occurs while searching for authors.
	 */
	public ClefUser showFavourites() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// The results of the search
		ClefUser user = null;
		boolean firstRead = true;
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (firstRead){
					// Create the user
					user = new ClefUser(rs.getString("email"), rs.getString("password"));
					firstRead = false;
				}
				
				// Add Author to favourites
				user.addFav(new Author(rs.getInt("AuthorID"), rs.getString("Name")));
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

		return user;
	}
}
