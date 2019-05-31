import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public final class JSONBuilder{

	// JSON Constant
	private static final String PAPERS = "papers";
	private static final String PAPER_TITLE = "title";
	private static final String PAPER_AUTHORS = "authors";
	private static final String PAPER_YEAR = "year";
	private static final String PAPER_NUM = "num";
	private static final String PAPER_MDATE = "mdate";
	private static final String PAPER_KEY = "key";
	private static final String PAPER_PTYPE = "ptype";
	private static final String PAPER_EE= "ee";
	private static final String PAPER_YEAR_STAT = "yearstat";
	private static final String AUTHOR_NAME = "name";
	private static final String AUTHOR_ID = "id";
	private static final String COAUTHOR = "coauthor";
	private static final String AUTHOR_RANK = "rank";
	private static final String AUTHOR_COMMUNITY = "com";
	private static final String AUTHOR_PUBBLICATIONS = "publications";
	private static final String AUTHOR = "author";
	private static final String LINK = "link";
	private static final String LINK_FROM = "from";
	private static final String LINK_TO = "to";
	private static final String	PAPER_COAUTOR_STAT = "coauthorstat";
	private static final String	ID = "ID";

	private static final int MAX_CO = 5;
	
	
	// Name of the file for the JSON
	private static final String FILE_OUTPUT = "analysis.json";
	
	/*
		Create the JSON for the Author's statistics
	*/
	public static JSONObject getStatisticAboutPapers(Author author){
	
		// Create JSON objetcs
		JSONObject root = new JSONObject();
		JSONArray yearArray = new JSONArray();
		JSONArray coauthorArray = new JSONArray();

		// Get the list of all the papers of the author
		ArrayList<Paper> paperList = author.getPubList();
		HashMap<String, Integer> paperTable = new HashMap<>();
		String currentYear;
		int temp;


		for (int i=0; i<paperList.size(); i++) {

			currentYear = paperList.get(i).getYear();
			temp = paperTable.getOrDefault(currentYear, 0);
			paperTable.put(currentYear, temp + 1);
		}


		// Fill the table with years of 0 publications
		int minYear = Integer.parseInt(Collections.min(paperTable.keySet()));
		int maxYear = Integer.parseInt(Collections.max(paperTable.keySet()));

		for (int i=minYear+1; i<maxYear; i++) {

			temp = paperTable.getOrDefault(Integer.toString(i), 0);
			paperTable.put(Integer.toString(i), temp);

		}

		// Sort the table
		Map<String, Integer> sortedPT = new TreeMap<>(paperTable);

		for (Map.Entry<String, Integer> e : sortedPT.entrySet()){

			JSONObject yearStat = new JSONObject();
			yearStat.put(PAPER_YEAR, e.getKey());
			yearStat.put(PAPER_NUM, e.getValue());

			yearArray.add(yearStat);
		}

		// Add the array to the root
		root.put(PAPER_YEAR_STAT, yearArray);
		
    	ArrayList<String> currentAuthors; 
    	HashMap<String, Integer> authorTable = new HashMap<String, Integer>();
    	String coauthor;

    	for (int i=0; i<paperList.size(); i++) {
    		currentAuthors = paperList.get(i).getAuthors();

    		for (int j=0; j<currentAuthors.size(); j++) {

    			coauthor = currentAuthors.get(j);
    			if (!(coauthor.equals(author.getName()))) {
					temp = authorTable.getOrDefault(coauthor, 0);
					authorTable.put(coauthor, temp + 1);
				}
    		}
    	}

		List<Map.Entry<String, Integer>> list = new ArrayList<>(authorTable.entrySet());
		list.sort(Map.Entry.comparingByValue());

		Map.Entry<String, Integer> e;

    	for (int i=1; i<=MAX_CO; i++) {

    		if (i<=list.size())	{

	    		e = list.get(list.size()-i);
				JSONObject coauthorObj = new JSONObject();
				coauthorObj.put(COAUTHOR, e.getKey());
				coauthorObj.put(PAPER_NUM, e.getValue());

				coauthorArray.add(coauthorObj);
    		}
    		
		}

		// Add the array to the root
		root.put(PAPER_COAUTOR_STAT, coauthorArray);

        JSONObject authorObj = new JSONObject();
        JSONArray pubByAuthor = new JSONArray();
        JSONArray paperLinks = new JSONArray();

        for (int j=0; j<paperList.size(); j++) {
            pubByAuthor.add(paperList.get(j).getTitle());
            paperLinks.add(paperList.get(j).getEe());
        }

        authorObj.put(AUTHOR_NAME, author.getName());
        authorObj.put(AUTHOR_PUBBLICATIONS, pubByAuthor);
        authorObj.put(PAPER_EE, paperLinks);
        
        root.put(AUTHOR, authorObj);

		return root;
	}


	public static JSONObject getStatisticAboutPapers(Author author, ArrayList<Author> allAuthors){
	
		// Create JSON objetcs
		JSONObject root = new JSONObject();
		JSONArray yearArray = new JSONArray();
		JSONArray coauthorArray = new JSONArray();

		// Get the list of all the papers of the author
		ArrayList<Paper> paperList = author.getPubList();
		HashMap<String, Integer> paperTable = new HashMap<>();
		String currentYear;
		int temp;


		for (int i=0; i<paperList.size(); i++) {

			currentYear = paperList.get(i).getYear();
			temp = paperTable.getOrDefault(currentYear, 0);
			paperTable.put(currentYear, temp + 1);
		}


		// Fill the table with years of 0 publications
		int minYear = Integer.parseInt(Collections.min(paperTable.keySet()));
		int maxYear = Integer.parseInt(Collections.max(paperTable.keySet()));

		for (int i=minYear+1; i<maxYear; i++) {

			temp = paperTable.getOrDefault(Integer.toString(i), 0);
			paperTable.put(Integer.toString(i), temp);

		}

		// Sort the table
		Map<String, Integer> sortedPT = new TreeMap<>(paperTable);

		for (Map.Entry<String, Integer> e : sortedPT.entrySet()){

			JSONObject yearStat = new JSONObject();
			yearStat.put(PAPER_YEAR, e.getKey());
			yearStat.put(PAPER_NUM, e.getValue());

			yearArray.add(yearStat);
		}

		// Add the array to the root
		root.put(PAPER_YEAR_STAT, yearArray);
		
    	ArrayList<String> currentAuthors; 
    	HashMap<String, Integer> authorTable = new HashMap<String, Integer>();
    	String coauthor;

    	for (int i=0; i<paperList.size(); i++) {
    		currentAuthors = paperList.get(i).getAuthors();

    		for (int j=0; j<currentAuthors.size(); j++) {

    			coauthor = currentAuthors.get(j);
    			if (!(coauthor.equals(author.getName()))) {
					temp = authorTable.getOrDefault(coauthor, 0);
					authorTable.put(coauthor, temp + 1);
				}
    		}
    	}

		List<Map.Entry<String, Integer>> list = new ArrayList<>(authorTable.entrySet());
		list.sort(Map.Entry.comparingByValue());

		Map.Entry<String, Integer> e;
		int ind;
    	for (int i=1; i<=MAX_CO; i++) {

    		if (i<=list.size())	{

	    		e = list.get(list.size()-i);
				JSONObject coauthorObj = new JSONObject();
				coauthorObj.put(COAUTHOR, e.getKey());
				ind = allAuthors.indexOf(new Author(e.getKey()));
				temp = allAuthors.get(ind).getID();
				coauthorObj.put(ID, temp);
				coauthorObj.put(PAPER_NUM, e.getValue());

				coauthorArray.add(coauthorObj);
    		}
    		
		}

		// Add the array to the root
		root.put(PAPER_COAUTOR_STAT, coauthorArray);


		return root;
	}
	/*
		Build the JSON Object for the graph 
	*/
	public static JSONObject getAuthorGraph(ArrayList<RankAuthor> listAuthor, ArrayList<Link> listLink){
		
		// Object that contains all the message
		JSONObject root = new JSONObject();
		
		// Arrays to contains the authors and the links
		JSONArray authorArrayJSON = new JSONArray();
		JSONArray linkArrayJSON = new JSONArray();
		
		// Fill the authors array
        for (int i = 0; i < listAuthor.size()-50; i++) {
			
			// Object for the single author
			JSONObject authorJSON = new JSONObject();
			RankAuthor rank = listAuthor.get(i);
			
			// Insert data
			authorJSON.put(AUTHOR_ID, rank.getAuthor().getID());
			authorJSON.put(AUTHOR_NAME, rank.getAuthor().getName());
			authorJSON.put(AUTHOR_RANK, rank.getRank());
			authorJSON.put(AUTHOR_COMMUNITY, 0); // Clustering
			
			// Add it to the array
			authorArrayJSON.add(authorJSON);
        }
        
        // Fill the links array
        for (int i = 0; i < listLink.size(); i++) {
			
			// Object for the single Link
			JSONObject linkJSON = new JSONObject();
			Link link = listLink.get(i);
			
			// Insert data
			linkJSON.put(LINK_FROM, link.getSource());
			linkJSON.put(LINK_TO, link.getDestination());
			
			// Add it to the array
			linkArrayJSON.add(linkJSON);
        }

		// Add arrays to the root
        root.put(AUTHOR, authorArrayJSON);
        root.put(LINK, linkArrayJSON);
        
        return root;
	}
	
	/*
		Build JSON with all the authors informations
	*/
    public static JSONObject getAuthorList(ArrayList<Author> a) {
        JSONObject firstLevel = new JSONObject();
        JSONArray elementList = new JSONArray();
        JSONObject authorObj;
        JSONArray pubByAuthor;
        Author cur;
        ArrayList<Paper> pubList;

        for (int i=0; i<a.size(); i++) {

            authorObj = new JSONObject();
            pubByAuthor = new JSONArray();
            cur = a.get(i);
            pubList = cur.getPubList();
            
            /*for (int j=0; j<cur.getPubNum(); j++) {
                pubByAuthor.add(pubList.get(j).getTitle());
            }*/

            authorObj.put(AUTHOR_NAME, cur.getName());
            authorObj.put(ID, cur.getID());
            //authorObj.put(AUTHOR_PUBBLICATIONS, pubByAuthor);
            elementList.add(authorObj);
        }

        firstLevel.put(AUTHOR, elementList);
		return firstLevel;
    }

    /*
		Build JSON with all the papers common informations
	*/

    public static JSONObject getPaperInfo(ArrayList<Paper> pl) {

    	JSONObject allPaper = new JSONObject();
    	JSONArray list = new JSONArray();
		JSONObject paperObj;
		JSONArray allAuthor;
		Paper currentPaper;
		ArrayList<String> currentAuthors;

    	for (int i=0; i<pl.size(); i++) {

    		paperObj = new JSONObject();
    		currentPaper = pl.get(i);

    		paperObj.put(PAPER_TITLE, currentPaper.getTitle());
    		paperObj.put(PAPER_MDATE, currentPaper.getMdate());
    		paperObj.put(PAPER_KEY, currentPaper.getKey());
    		paperObj.put(PAPER_YEAR, currentPaper.getYear());
    		paperObj.put(PAPER_EE, currentPaper.getEe());
			paperObj.put(PAPER_PTYPE, currentPaper.getPType());

    		allAuthor = new JSONArray();
    		currentAuthors = currentPaper.getAuthors();

    		for (int j=0; j<currentAuthors.size(); j++) {

    			allAuthor.add(currentAuthors.get(j));
    		}

    		paperObj.put(PAPER_AUTHORS, allAuthor);

    		list.add(paperObj);

    	}

    	allPaper.put(PAPERS, list);

    	return allPaper;
    }

     public static JSONObject getPaperInfo(ArrayList<Paper> pl, ArrayList<Author> authorList) {

    	JSONObject allPaper = new JSONObject();
    	JSONArray list = new JSONArray();
		JSONObject paperObj;
		JSONArray allAuthor;
		JSONArray allID;
		Paper currentPaper;
		ArrayList<String> currentAuthors;
		String autName;
		int temp;
		int ind;

    	for (int i=0; i<pl.size(); i++) {

    		paperObj = new JSONObject();
    		currentPaper = pl.get(i);

    		paperObj.put(PAPER_TITLE, currentPaper.getTitle());
    		paperObj.put(PAPER_MDATE, currentPaper.getMdate());
    		paperObj.put(PAPER_KEY, currentPaper.getKey());
    		paperObj.put(PAPER_YEAR, currentPaper.getYear());
    		paperObj.put(PAPER_EE, currentPaper.getEe());
			paperObj.put(PAPER_PTYPE, currentPaper.getPType());

    		allAuthor = new JSONArray();
    		allID = new JSONArray();
    		currentAuthors = currentPaper.getAuthors();

    		for (int j=0; j<currentAuthors.size(); j++) {


    			autName = currentAuthors.get(j);
    			allAuthor.add(autName);
    			if (autName != null) {
					ind = authorList.indexOf(new Author(autName));
					temp = authorList.get(ind).getID();
	    			allID.add(temp);
    			}
    			
    			
				
    		}

    		paperObj.put(PAPER_AUTHORS, allAuthor);
    		paperObj.put(ID, allID);

    		list.add(paperObj);

    	}

    	allPaper.put(PAPERS, list);

    	return allPaper;
    }

	
	/*
		This method prints a JSONObject to a file
	*/
	public static void printJSONToFile(JSONObject object, String fileName){
		
        try {
        	
        	// Create file writer
			FileWriter file = new FileWriter(fileName);
			
			// Write to file
            file.write(object.toString());
            file.flush();
            
        } catch(IOException e) {
			
			System.out.println("There was a problem while printing the JSON to the file " + fileName);
            e.printStackTrace();
        } 
	}

}
