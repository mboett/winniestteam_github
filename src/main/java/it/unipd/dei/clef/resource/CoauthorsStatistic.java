package it.unipd.dei.clef.resource;

import java.util.ArrayList;
import it.unipd.dei.clef.resource.*;
import com.fasterxml.jackson.core.*;

import java.io.*;

/**
 * Represents the data about the coauthors statistic.
 */
public class CoauthorsStatistic extends Resource {

	/**
	 * The coathors statistic
	 */
	private final ArrayList<Author> statistic;

	/**
	 * Creates a new statistic
	 */
	public CoauthorsStatistic(final ArrayList<Author> statistic) {
		this.statistic = statistic;
	}

	/**
	 * Returns the statistic.
	 */
	public final ArrayList<Author> getStatistic() {
		return statistic;
	}

	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);
		
		// write statistic to JSON
		jg.writeStartObject();

		jg.writeFieldName("coauthorstat");
		
		// print array
		jg.writeStartArray();

		jg.flush();				
			
		boolean firstElement = true;

	    for (final Author r : statistic) {

	        // very bad work-around to add commas between resources
	        if (firstElement) {

	        	jg.writeStartObject();

				jg.writeFieldName("stat");

	            jg.writeStartObject();
		
				jg.writeStringField("coauthor", r.getName());
				
				jg.writeNumberField("num", r.getCollab());
				
				jg.writeNumberField("id", r.getID());
				
				jg.writeEndObject();

				jg.writeEndObject();

	            jg.flush();

	            jg.flush();

	            firstElement = false;
	        } else {
	            jg.writeRaw(',');

	            jg.flush();

	            jg.writeStartObject();

				jg.writeFieldName("stat");

				jg.writeStartObject();

				jg.writeStringField("coauthor", r.getName());
				
				jg.writeNumberField("num", r.getCollab());
				
				jg.writeNumberField("id", r.getID());
				
				jg.writeEndObject();

				jg.writeEndObject();

	            jg.flush();

	            jg.flush();
	        }
	    }

		
		
		// close array
		jg.writeEndArray();

		jg.writeEndObject();

		jg.flush();

		jg.close();
	}
}
