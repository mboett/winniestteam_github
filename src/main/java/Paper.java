import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Paper {

	private final String title;
	private final String year;
	private ArrayList<String> authors;
	private int autCount;
	protected String pType;
	private String mdate;
	private String key;
	protected String ee;
	private int id;


	public Paper(String t, String y, String m, String k, String e, String[] l) {

		title = t;
		authors = new ArrayList<>(2);
		for (int i=0; i<l.length; i++)
			authors.add(l[i]);
		year = y;
		mdate = m;
		key = k;
		ee = e;
		pType = "paper";
		id = -1;

	}

	public void addAut(String s) {

		authors.add(s);
	}

	public void setID(int i) {

		id = i;
	}

	public int getID() {

		return id;
	}


	public int getAutNum() {

		return authors.size();
	}

	public String getTitle() {

		return title;
	}

	public String getPType() {

		return pType;
	}

	public ArrayList<String> getAuthors() {

		return authors;
	}
	
	public String getYear(){
		
		return year;
	}

	public String getMdate() {

		return mdate;
	}

	public String getKey() {

		return key;
	}

	public String getEe() {

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
