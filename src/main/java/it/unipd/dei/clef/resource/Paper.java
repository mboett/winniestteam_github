package it.unipd.dei.clef.resource;

/**
 * Represents the data about an author.
 * 
 * @author Winniest Team
 * @version 1.00
 * @since 1.00
 */

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Paper {

	private final String title;
	private final String year;
	private final String mdate;
	private final String key;
	private final String ee;
	private final int id;
	
	// Optional use
	private final ArrayList<Author> authors;


	public Paper(final int id, final String title, final String year, final String mdate, final String key, final String ee) {

		this.id = id;
		this.title = title;
		authors = new ArrayList<>();
		this.year = year;
		this.mdate = mdate;
		this.key = key;
		this.ee = ee;

	}
	
	public Paper(final int id, final String title, final String year, final String mdate, final String key, final String ee, final ArrayList<Author> l) {

		this.id = id;
		this.title = title;
		authors = new ArrayList<>(l.size());
		for (int i = 0; i < l.size(); i++)
			authors.add(l.get(i));
		this.year = year;
		this.mdate = mdate;
		this.key = key;
		this.ee = ee;

	}

	public final void addAut(Author a) {

		authors.add(a);
	}

	public final int getID() {

		return id;
	}


	public final int getAutNum() {

		return authors.size();
	}

	public final String getTitle() {

		return title;
	}

	public final ArrayList<Author> getAuthors() {

		return authors;
	}
	
	public final String getYear(){
		
		return year;
	}

	public final String getMdate() {

		return mdate;
	}

	public final String getKey() {

		return key;
	}

	public final String getEe() {

		return ee;
	}


	// Define equals method
	public boolean equals(Object other) {

		if (!(other instanceof Paper))
			return false;

		return (title.equals(((Paper)other).getTitle()) && key.equals(((Paper)other).getKey()));

	}

	// Define hashCode method
	public int hashCode() {

		return title.hashCode();
	}


}
