package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class SearchLikeDatabase {

	private static final String SELECT_AUTHOR = "SELECT AuthorID FROM Likes WHERE Email = ? AND AuthorID = ?";

	private final Connection con;

	private final Likes likes;

	public SearchLikeDatabase(final Connection con, final Likes likes) {
		this.con = con;
		this.likes = likes;
	}

	public boolean searchAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;
		ResultSet rs = null;
		int id = 0;

		try {

			pstmt_like = con.prepareStatement(SELECT_AUTHOR);
			pstmt_like.setString(1, likes.getEmail());
			pstmt_like.setInt(2, likes.getAuthorID());
			rs = pstmt_like.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("AuthorID");
			}
			
			/*if(id != 0)	{
				return true;
			}
			else
				return false;*/
			
			return true;

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}

	}

/*
	public int findAuthor() throws SQLException {
		PreparedStatement pstmt_author = null;
		ResultSet rs_author = null;
		int authorID = -1;
		try {
			pstmt_author = con.prepareStatement(SELECT_AUTHOR);
			rs_author = pstmt_author.executeQuery();
			pstmt_author.setString(1,  author_name);
			while (rs_author.next()) {
					authorID = rs_author.getInt("AuthorID");
			}
			pstmt_author.execute();
		} finally {
			if (rs_author != null) {
				rs_author.close();
			}
			if (pstmt_author != null) {
				pstmt_author.close();
			}
			con.close();
		}
		return authorID;
	}*/
}