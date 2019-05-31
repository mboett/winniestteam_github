package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.ClefUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class SignUpDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO ClefUser (email, password) VALUES (?, ?)";

	private final Connection con;

	private final ClefUser clefUser;

  public SignUpDatabase(final Connection con, final ClefUser clefUser) {
		this.con = con;
		this.clefUser = clefUser;
	}

	public void addUser() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, clefUser.getEmail());
			pstmt.setString(2, clefUser.getPassword());

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}
}
