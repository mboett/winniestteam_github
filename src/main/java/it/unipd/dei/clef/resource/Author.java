package it.unipd.dei.clef.resource;

/**
 * Represents the data about an author.
 * 
 * @author Winniest Team
 * @version 1.00
 * @since 1.00
 */

import java.util.ArrayList;

public class Author implements Comparable<Author>{

	private final String author_name;
	
	private final int id;
	
	private final ArrayList<Paper> publications;

	private final int collab;


	// Constructor
	public Author(final int id, final String name) {
		
		this.id = id;
		this.author_name = name;
		this.collab = 0;
		this.publications = new ArrayList<>();
	}
	
	// Constructor
	public Author(final int id, final String name, final int coll) {
		
		this.id = id;
		this.author_name = name;
		this.collab = coll;
		this.publications = new ArrayList<>();
	}

	public int getCollab() {

		return collab;
	}
	// Add a new publication to the list of the author's publications
	public final void addPub(Paper pub) {

		publications.add(pub);
	}

	// Return author name
	public final String getName() {

		return author_name;
	}

	// Return number of total publications
	public final int getPubNum() {

		return publications.size();
	}

	// Return list of publications
	public final ArrayList<Paper> getPubList() {

		return publications;
	}

	// Define equals method
	public boolean equals(Object other) {

		if (!(other instanceof Author))
			return false;

		return (author_name.equals(((Author)other).getName()));

	}

	// Define hashCode method
	public int hashCode() {

		return author_name.hashCode();
	}
	
	public int getID(){
		return id;
	}

	// Implementation of Comparable
	public int compareTo(Author author){
		if ( publications.size() < author.getPubNum()){
			return -1;
		} else if (publications.size() == author.getPubNum()){
			return 0;
		} else {
			return 1;
		}
	}

}
