import java.util.ArrayList;

public class Book extends Paper {

	private String publisher;

	public Book(String t, String y, String m, String k, String p, String e, String[] l) {

		super(t, y, m, k, e, l);
		pType = "book";
		publisher = p;
	}

	public String getPublisher() {

		return publisher;
	}

}