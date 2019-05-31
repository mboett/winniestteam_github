import java.util.ArrayList;

public class Phdthesis extends Paper {

	private String school;


	public Phdthesis(String t, String y, String m, String k, String s, String e, String[] l) {

		super(t, y, m, k, e, l);
		pType = "phdthesis";
		school = s;
	}

	public String getSchool() {

		return school;
	}

}