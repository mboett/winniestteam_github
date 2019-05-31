package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.Paper;
import it.unipd.dei.clef.resource.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class CreatePaperDatabase {

	private static final String INSERT_PAPER = "INSERT INTO Paper (PaperID, Title, Year, Mdate, Key, DOI) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String INSERT_AUTHORS = "INSERT INTO Author (AuthorID, Name) VALUES (?, ?)";
  private static final String MAX_PAPER_ID = "SELECT MAX(PaperID) as max FROM Paper";
  private static final String MAX_AUTHOR_ID = "SELECT MAX(AuthorID) as max FROM Author";
  private static final String INSERT_WRITE = "INSERT INTO Write (AuthorID, PaperID) VALUES (?, ?)";

	private final Connection con;

	private final Paper paper;

  private final Author author;

	public CreatePaperDatabase(final Connection con, final Paper paper, final Author author) {
		this.con = con;
		this.paper = paper;
    this.author = author;
	}

	public void addPaper() throws SQLException {

		PreparedStatement pstmt_paper = null;
    PreparedStatement pstmt_author = null;
    PreparedStatement pstmt_write = null;

		try {

			pstmt_paper = con.prepareStatement(INSERT_PAPER);
			pstmt_paper.setInt(1, paper.getID());
      pstmt_paper.setString(2, paper.getTitle());
      pstmt_paper.setString(3, paper.getYear());
      pstmt_paper.setString(4, paper.getKey());
			pstmt_paper.setString(5, paper.getMdate());
			pstmt_paper.setString(6, paper.getEe());

			pstmt_paper.execute();

      pstmt_author = con.prepareStatement(INSERT_AUTHORS);
      pstmt_author.setInt(1, author.getID());
      pstmt_author.setString(2, author.getName());

      pstmt_author.execute();

      pstmt_write = con.prepareStatement(INSERT_WRITE);
      pstmt_write.setInt(1, author.getID());
      pstmt_write.setInt(2, paper.getID());

      pstmt_write.execute();

		} finally {

			if (pstmt_paper != null) {
				pstmt_paper.close();
			}
      if (pstmt_author != null) {
				pstmt_author.close();
			}
      if (pstmt_write != null) {
				pstmt_write.close();
			}

			con.close();
		}

	}

  public int maxPaperId() throws SQLException {

    PreparedStatement pstmt_getMaxPaperID = null;
    ResultSet rs_paper = null;
    int newPaperID = -1;

    try {

      pstmt_getMaxPaperID = con.prepareStatement(MAX_PAPER_ID);
      rs_paper = pstmt_getMaxPaperID.executeQuery();

      while (rs_paper.next()) {
          newPaperID = rs_paper.getInt("max") + 1;
      }

    } finally {

      if (rs_paper != null) {
        rs_paper.close();
      }

      if (pstmt_getMaxPaperID != null) {
        pstmt_getMaxPaperID.close();
      }

      con.close();
    }

    return newPaperID;
  }

  public int maxAuthorId() throws SQLException {

    PreparedStatement pstmt_getMaxAuthorID = null;
    ResultSet rs_author = null;
    int newAuthorID = -1;

    try {

      pstmt_getMaxAuthorID = con.prepareStatement(MAX_AUTHOR_ID);
      rs_author = pstmt_getMaxAuthorID.executeQuery();

      while (rs_author.next()) {
          newAuthorID = rs_author.getInt("max") + 1;
      }

    } finally {

      if (rs_author != null) {
        rs_author.close();
      }

      if (pstmt_getMaxAuthorID != null) {
        pstmt_getMaxAuthorID.close();
      }
      con.close();
    }

    return newAuthorID;
  }
}
