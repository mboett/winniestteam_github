import java.util.ArrayList;

public class Author implements Comparable<Author>{

	private final String author_name;
	
	private int id;
	
	private ArrayList<Paper> publications;


	// Constructor
	public Author(String name) {
		
		id = -1; // Null ID
		author_name = name;
		publications = new ArrayList<>();
	}

	// Add a new publication to the list of the author's publications
	public void addPub(Paper pub) {

		publications.add(pub);
	}

	// Return author name
	public String getName() {

		return author_name;
	}

	// Return number of total publications
	public int getPubNum() {

		return publications.size();
	}

	// Return list of publications
	public ArrayList<Paper> getPubList() {

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
	
	public void setID(int i){
		id = i;
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
