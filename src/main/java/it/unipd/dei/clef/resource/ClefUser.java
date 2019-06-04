package it.unipd.dei.clef.resource;

import java.util.ArrayList;

public class ClefUser {

	private final String email;

	private final String password;
	
	private final ArrayList<Author> favs;


	public ClefUser(final String email, final String password) {
		this.email = email;
		this.password = password;
		this.favs = new ArrayList<>();
	}

	public final String getEmail() {
		return email;
	}

	public final String getPassword() {
		return password;
	}
	
	// Add a new author to the list of the user's favourites
	public final void addFav(Author aut) {

		favs.add(aut);
	}
	
	// Return list of favourites
	public final ArrayList<Author> getFavList() {

		return favs;
	}
}
