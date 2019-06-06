package it.unipd.dei.clef.resource;

import it.unipd.dei.clef.resource.*;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.*;

public class Likes extends Resource{

	private final String email;

	private final int authorID;
	
	private final int count;

	public Likes(final String email, final int authorID) {
		this.email = email;
		this.authorID = authorID;
		this.count = -1;
	}
	
	public Likes(final int authorID, final int count) {
		this.email = null;
		this.authorID = authorID;
		this.count = count;
	}

	public final String getEmail() {
		return email;
	}

	public final int getAuthorID() {
		return authorID;
	}
	
	public final int getCount() {
		return count;
	}
	
	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("likes");

		jg.writeStartObject();

		jg.writeNumberField("authorID", authorID);

		jg.writeNumberField("count", count);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

}
