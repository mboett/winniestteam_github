import java.util.ArrayList;

public class Incollection extends Paper {

	private String publisher;
	private String booktitle;

	public Incollection(String t, String y, String m, String k, String b, String p, String e, String[] l) {

		super(t, y, m, k, e, l);
		pType = "incollection";
		publisher = p;
		booktitle = b;
	}

	public String getPublisher() {

		return publisher;
	}

	public String getBooktitle() {

		return booktitle;
	}

}