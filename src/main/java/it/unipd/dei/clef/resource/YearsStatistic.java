package it.unipd.dei.clef.resource;

import java.util.ArrayList;
import it.unipd.dei.clef.resource.*;
import com.fasterxml.jackson.core.*;

import java.io.*;

/**
 * Represents the data about the year statistic.
 */
public class YearsStatistic extends Resource {

	/**
	 * The year statistic
	 */
	private final ArrayList<YearOccurence> statistic;

	/**
	 * Creates a new statistic
	 */
	public YearsStatistic(final ArrayList<YearOccurence> statistic) {
		this.statistic = statistic;
	}

	/**
	 * Returns the statistic.
	 */
	public final ArrayList<YearOccurence> getStatistic() {
		return statistic;
	}

	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);
		
		// write statistic to JSON
		jg.writeStartObject();

		jg.writeFieldName("yearstat");
		
		// print array
		jg.writeRaw("[");
		
		// print all the years result
		for (int i = 0; i < statistic.size(); i++){
			jg.writeStartObject();
			
			YearOccurence yearOcc = statistic.get(i);
			
			jg.writeNumberField("year", yearOcc.getYear());
			
			jg.writeNumberField("num", yearOcc.getOccurence());
			
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
