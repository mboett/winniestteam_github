package it.unipd.dei.clef.resource;

public class Likes {

	private final String email;

	private final int authorID;

	private final boolean liked;


	public Likes(final String email, final int authorID, final boolean liked) {
		this.email = email;
		this.authorID = authorID;
		this.liked = liked;
	}

	public final String getEmail() {
		return email;
	}

	public final int getAuthorID() {
		return authorID;
	}

	public final boolean getLiked(){
		return liked;
	}
}
