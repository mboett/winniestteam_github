package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class LikeAuthorDatabase {

	private static final String INSERT_LIKE = "INSERT INTO Likes (Email, AuthorID) VALUES (?, ?)";
	private static final String SELECT_AUTHOR = "SELECT AuthorID FROM Author WHERE Name = ?";

	private final Connection con;

  private final Likes likes;

	private final String author_name;

	public LikeAuthorDatabase(final Connection con, final Likes likes, final String author_name) {
		this.con = con;
		this.likes = likes;
		this.author_name = author_name;
	}

	public void likeAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;

		try {

			pstmt_like = con.prepareStatement(INSERT_LIKE);
			pstmt_like.setString(1, likes.getEmail());
			pstmt_like.setInt(1, likes.getAuthorID());

			pstmt_like.execute();

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}

	}

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

	}
}
