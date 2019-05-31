import java.util.ArrayList;

public class Article extends Paper {

	private String journal;
	private String volume;

	public Article(String t, String y, String m, String k, String j, String v, String e, String[] l) {

		super(t, y, m, k, e, l);
		pType = "article";
		journal = j;
		volume = v;
	}

	public String getJour() {

		return journal;
	}

	public String getVol() {

		return volume;
	}

}
