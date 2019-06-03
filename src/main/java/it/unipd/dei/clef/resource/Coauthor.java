package it.unipd.dei.clef.resource;

import java.util.ArrayList;

import com.fasterxml.jackson.core.*;

import java.io.*;

public class Coauthor extends Resource {

	private final String name;
	
	private final int id;

	private final int numCollaborations;
	
	// Constructor
	public Coauthor(final int id, final String name, final int coll) {
		
		this.id = id;
		this.name = name;
		this.numCollaborations = coll;
	}

	public final int getNumCollaborations() {

		return numCollaborations;
	}

	// Return author name
	public final String getName() {

		return name;
	}
	
	// Return the author's ID
	public int getID(){
		return id;
	}
	
	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeStringField("coauthor", name);

		jg.writeNumberField("num", numCollaborations);

		jg.writeNumberField("id", id);
		
		jg.writeEndObject();

		jg.flush();
	}
	

	// Define equals method
	public boolean equals(Object other) {

		if (!(other instanceof Coauthor))
			return false;

		return (name.equals(((Coauthor)other).getName()));

	}

	// Define hashCode method
	public int hashCode() {

		return name.hashCode();
	}
}
