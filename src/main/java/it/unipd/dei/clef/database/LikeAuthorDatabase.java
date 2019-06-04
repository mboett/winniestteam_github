package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class LikeAuthorDatabase {

	private static final String INSERT_LIKE = "INSERT INTO Likes (Email, AuthorID) VALUES (?, ?)";
	private static final String SELECT_LIKE = "SELECT Email, AuthorID AS check FROM Likes WHERE Email = ? AND AuthorID = ?";

	private final Connection con;

	private final Likes likes;

	private final String email;

	private final int ID;

	public LikeAuthorDatabase(final Connection con, final Likes likes, final String email, final int ID) {
		this.con = con;
		this.likes = likes;
		this.email = email;
		this.ID = ID;
	}

	public void likeAuthor() throws SQLException {

		PreparedStatement pstmt_like = null;

		try {

			pstmt_like = con.prepareStatement(INSERT_LIKE);
			pstmt_like.setString(1, likes.getEmail());
			pstmt_like.setInt(2, likes.getAuthorID());

			pstmt_like.execute();

		} finally {

			if (pstmt_like != null) {
				pstmt_like.close();
			}

			con.close();
		}

	}

	public boolean trueLikedAuthor() throws SQLException {

		PreparedStatement pstmt_liked = null;
    ResultSet rs_liked = null;

    try {

      pstmt_liked = con.prepareStatement(SELECT_LIKE);
			pstmt_liked.setString(1, email);
			pstmt_liked.setInt(2, ID);
      rs_liked = pstmt_liked.executeQuery();

      while (rs_liked.next()) {
        if (rs_liked.getInt("check") == 1) {
					rs_liked.close();
					return true;
				}
      }

    } finally {

      if (rs_liked != null) {
        rs_liked.close();
      }

      if (pstmt_liked != null) {
        pstmt_liked.close();
      }

      con.close();
    }

    return false;
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
