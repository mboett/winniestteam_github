package it.unipd.dei.clef.resource;

import java.util.ArrayList;


public class YearOccurence implements Comparable<YearOccurence>{

	private final int year;

	private final int occurence;


	public YearOccurence(final int year, final int occurence) {

		this.year = year;
		this.occurence = occurence;

	}

	public int getYear() {

		return year;
	}

	public int getOccurence() {

		return occurence;
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