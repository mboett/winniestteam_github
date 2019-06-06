package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class DisLikeAuthorDatabase {

	private static final String DELETE_LIKE = "DELETE FROM Likes WHERE AuthorID = ? AND Email = ?";
	private static final String COUNT_LIKES = "SELECT COUNT(*) FROM Likes WHERE AuthorID = ?";
	//private static final String SELECT_AUTHOR = "SELECT AuthorID FROM Author WHERE Name = ?";

	private final Connection con;

	private final Likes likes;

	public DisLikeAuthorDatabase(final Connection con, final Likes likes) {
		this.con = con;
		this.likes = likes;
	}

	public Likes disLikeAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;
		PreparedStatement pstmt_count_like = null;
		ResultSet rs = null;
		int countL = -1;
		
		// Result
		final Likes resultLike;
		
		try {

			pstmt_like = con.prepareStatement(DELETE_LIKE);
			pstmt_like.setInt(1, likes.getAuthorID());
			pstmt_like.setString(2, likes.getEmail());

			pstmt_like.execute();
			
			pstmt_count_like = con.prepareStatement(COUNT_LIKES);
			pstmt_count_like.setInt(1,likes.getAuthorID());
			
			rs = pstmt_count_like.executeQuery();
			
			// Get number of likes
			while (rs.next()) {
				
				countL = rs.getInt(1);
			}
			
			// Build result
			resultLike = new Likes(likes.getAuthorID(), countL);

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}
		
		return resultLike;

	}
}