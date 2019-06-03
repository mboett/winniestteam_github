package it.unipd.dei.clef.database;

import it.unipd.dei.clef.resource.ClefUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class LoginDatabase {

	private static final String STATEMENT = "SELECT Email, Password FROM ClefUser WHERE Email = ? AND Password = ? ;";

	private final Connection con;

	private final ClefUser clefUser;

	public LoginDatabase(final Connection con, final ClefUser clefUser) {
		this.con = con;
		this.clefUser = clefUser;
	}

	public boolean Login() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, clefUser.getEmail());
			pstmt.setString(2, clefUser.getPassword());

			rs = pstmt.executeQuery();

			while (rs.next()) {
					if(rs.getString("Email") != null) {
							return true;
					}
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

		return false;
	}

	public Strinf getUserEmail() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_email = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, clefUser.getEmail());
			pstmt.setString(2, clefUser.getPassword());

			rs = pstmt.executeQuery();

			while (rs.next()) {
					if(rs.getString("Email") != null) {
							user_email = rs.getString("Email");
					}
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

	}

}
