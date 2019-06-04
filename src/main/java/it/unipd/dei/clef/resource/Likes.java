package it.unipd.dei.clef.resource;

public class Likes {

	private final String email;

	private final int authorID;


	public Likes(final String email, final int authorID) {
		this.email = email;
		this.authorID = authorID;
	}

	public final String getEmail() {
		return email;
	}

	public final int getAuthorID() {
		return authorID;
	}

}
