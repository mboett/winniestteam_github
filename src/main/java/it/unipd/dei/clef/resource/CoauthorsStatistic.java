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
		jg.writeRaw("[");
		
		// print all the coauthors
		for (int i = 0; i < statistic.size(); i++){
			jg.writeStartObject();
			
			Author coathorOcc = statistic.get(i);
			
			jg.writeStringField("coauthor", coathorOcc.getName());
			
			jg.writeNumberField("num", coathorOcc.getCollab());
			
			jg.writeNumberField("id", coathorOcc.getID());
			
			jg.writeEndObject();
			
			if (i != statistic.size() - 1 ){
				jg.writeRaw(",");
			}
		}
		
		// close array
		jg.writeRaw("]");

		jg.writeEndObject();

		jg.flush();
	}
}
