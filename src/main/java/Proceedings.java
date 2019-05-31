import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Proceedings extends Paper {

	public Proceedings(String t, String y, String m, String k, String e, String[] l) {

		super(t, y, m, k, e, l);
		pType = "proceedings";
	}

}