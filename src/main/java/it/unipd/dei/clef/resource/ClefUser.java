package it.unipd.dei.clef.resource;

public class ClefUser {

	private final String email;

	private final String password;


	public ClefUser(final String email, final String password) {
		this.email = email;
		this.password = password;
	}

	public final String getEmail() {
		return email;
	}

	public final String getPassword() {
		return password;
	}
}
