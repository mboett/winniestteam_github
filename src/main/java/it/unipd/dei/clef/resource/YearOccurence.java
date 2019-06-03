package it.unipd.dei.clef.resource;

import java.util.ArrayList;
import it.unipd.dei.clef.resource.Resource;
import com.fasterxml.jackson.core.*;

import java.io.*;


public class YearOccurence extends Resource implements Comparable<YearOccurence>{

	private final int year;

	private final int occurence;


	public YearOccurence(final int year, final int occurence) {

		this.year = year;
		this.occurence = occurence;

	}

	final public int getYear() {

		return year;
	}

	final public int getOccurence() {

		return occurence;
	}
	
	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeNumberField("year", year);

		jg.writeNumberField("num", occurence);

		jg.writeEndObject();

		jg.flush();
	}

	public boolean equals(Object other) {

		if (!(other instanceof YearOccurence))
			return false;

		return (year == ((YearOccurence)other).getYear() && occurence == ((YearOccurence)other).getOccurence());

	}

	public int hashCode() {

		return (""+year+occurence).hashCode();
	}

	public int compareTo(YearOccurence other){
		if (year < other.getYear()) {
			return -1;
		} else if (year == other.getYear()) {
			 if (occurence < other.getOccurence()) {
			 	return -1;	
			 } else if (occurence == other.getOccurence()) {
			 	return 0;
			 } else {
			 	return 1;
			 }
		} else {
			return 1;
		}
	}
	


}