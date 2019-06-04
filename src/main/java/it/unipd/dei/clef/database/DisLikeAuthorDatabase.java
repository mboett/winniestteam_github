package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class DisLikeAuthorDatabase {

	private static final String DELETE_LIKE = "DELETE FROM Likes WHERE AuthorID = ?";
	private static final String SELECT_DISLIKE = "SELECT Email, AuthorID AS check FROM Likes WHERE Email = ? AND AuthorID = ?";
	//private static final String SELECT_AUTHOR = "SELECT AuthorID FROM Author WHERE Name = ?";

	private final Connection con;

	private final Likes likes;

	private final String email;

	private final int ID;

	public DisLikeAuthorDatabase(final Connection con, final Likes likes, final String email, final int ID) {
		this.con = con;
		this.likes = likes;
		this.email = email;
		this.ID = ID;
	}

	public void disLikeAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;

		try {

			pstmt_like = con.prepareStatement(DELETE_LIKE);
			pstmt_like.setInt(1, likes.getAuthorID());

			pstmt_like.execute();

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}

	}

	public boolean falseLikedAuthor() throws SQLException {

		PreparedStatement pstmt_disliked = null;
    ResultSet rs_disliked = null;

    try {

      pstmt_disliked = con.prepareStatement(SELECT_DISLIKE);
			pstmt_disliked.setString(1, email);
			pstmt_disliked.setInt(2, ID);
      rs_disliked = pstmt_disliked.executeQuery();

      while (rs_disliked.next()) {
        if (rs_disliked.getInt("check") == 1) {
					rs_disliked.close();
					return false;
				}
      }

    } finally {

      if (rs_disliked != null) {
        rs_disliked.close();
      }

      if (pstmt_disliked != null) {
        pstmt_disliked.close();
      }

      con.close();
    }

    return true;
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
