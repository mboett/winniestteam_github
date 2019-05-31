import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;

public class StaxParser {
	
	// Hashmap
	private HashMap<String, Author> authorTable = new HashMap<String, Author>();
	
    // ArrayList
    private ArrayList<Author> authorList;
    private ArrayList<Paper> paperList = new ArrayList<>();
    private ArrayList<Www> webList = new ArrayList<>();
    private String filePath;
    private long MaxNumLines;

    public StaxParser(String fP, long max) throws Exception {
		
		MaxNumLines = max;
        filePath = fP;
        doParsing();
    }

    // Auto-resizing append function (for authors)
    private static String[] appendAuthor(String[] array, int currentItem, String value) {

            if (array.length == currentItem) {
                // We want no empty space in the array
                String[] newArray = new String[array.length +1];
                System.arraycopy(array, 0, newArray, 0, array.length);
                array = newArray;
            }

            array[currentItem] = value;

            return array;
        }


    // Search for an author, if it finds him updates his publication, otherwise add him to the list
    private void updateAuthor(String aut, Paper paper) {
        
		// Get the corresponding count
		Author author = authorTable.getOrDefault(aut, new Author(aut));
		
		// Add the new paper
		author.addPub(paper);
		
		// Update the HashMap
		authorTable.put(aut, author);

    }

    /*
		Sort the arrayList class with respect to the number of their publications
	*/
	private void sortRankAuthorList(boolean ascending){
		if(authorList == null){
			// Error return
			System.out.println("Empty Author list");
			return;
		}
		System.out.println("Sorting authors...");
		// Sorting
		if (ascending){
			Collections.sort(authorList, new Comparator<Author>() {
					public int compare(Author author1, Author author2)
					{
						return  author1.compareTo(author2);
					}
				});
		} else {
			Collections.sort(authorList, new Comparator<Author>() {
					public int compare(Author author1, Author author2)
					{
						return  -1 * author1.compareTo(author2);
					}
				});
		}
		System.out.println("End sorting authors.");
	}

    private void doParsing() throws Exception {

        // Control data (row in the xml)
        int line = 0;
        String author = "";
        String target = "800A";
        String replacement = "&";
        boolean isClef = false;
        String clef = "conf/clef";
        int pID = 0;

        // Common entries to all type of Paper
        String mdate = "";
        String key = "";

        String ee = "";

        String suf = "";
        String title = "";

        String year = "";

        String[] authors = new String[1];
        int authorCount = 0;

        boolean stop = false;

        // Booleans to define the type of Element (Paper) the entries belong to

        // Article
        // Mandatory entries
        // journal  volume

        String journal = "";
        String volume = "";

        // Proceedings
        // Mandatory entries
        // No others

        // Inproceedings
        // Mandatory entries
        // booktitle
        String booktitle = "";

        // Book
        // Mandatory entries
        // publisher
        String publisher = "";

        // Incollection
        // Mandatory entries
        // booktitle, publisher

        // Phdthesis
        // Mandatory entries
        // school
        String school = "";

        // Masterthesis
        // Mandatory entries
        // school

        // www
        // Mandatory entries
        // url (no year)
        String url = "";




        // obtain an instance of the factory for creating StAX parsers
        XMLInputFactory xif = XMLInputFactory.newInstance();


        // obtain an instance of a StAX parser
        XMLStreamReader parser = xif.createXMLStreamReader(new FileInputStream(new File(filePath)));

        System.out.println("\n**** PARSING XML ****\n");
        // while there are events in the stream
        while (parser.hasNext() && !(stop)) {

            // advance to the next event in the stream
            parser.next();

            // a new element is starting
            if (parser.getEventType() == XMLStreamConstants.START_ELEMENT) {

                line++;

                // Define the starting of a Paper-type element
                if ("article".equals(parser.getLocalName())) {
                    // we reached the start of an article

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("proceedings".equals(parser.getLocalName())) {

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("inproceedings".equals(parser.getLocalName())) {

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("book".equals(parser.getLocalName())) {

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("incollection".equals(parser.getLocalName())) {

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("phdthesis".equals(parser.getLocalName())) {


                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if ("mastersthesis".equals(parser.getLocalName())) {

                    mdate = parser.getAttributeValue(0);
                    key = parser.getAttributeValue(1);
                    isClef = key.contains(clef);

                } else if (isClef) {

                    if ("www".equals(parser.getLocalName())) {


                        key = parser.getAttributeValue(0);
                        isClef = key.contains(clef);
                    } else if ("title".equals(parser.getLocalName())) {

                        parser.next();

                        // Save HTML element in the text
                        while (!(parser.getEventType() == XMLStreamConstants.END_ELEMENT && "title".equals(parser.getLocalName()))) {


                            if (parser.getEventType() == XMLStreamConstants.START_ELEMENT) {
                                suf = suf + "<" + parser.getLocalName() + ">";

                            } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT) {
                                suf = suf + "</" + parser.getLocalName() + ">";

                            } else {
                                suf = suf + parser.getText();
                            }

                            parser.next();
                        }

                        title = suf;
                        suf = "";

                    } else if ("author".equals(parser.getLocalName())) {

                        parser.next();

                        // The parser can't read & char, in the file they have been previously replaced by the string 800A
                        author = parser.getText();
                        author = author.replaceAll(target, replacement);
                        authors = appendAuthor(authors, authorCount, author);
                        authorCount++;

                    } else if ("volume".equals(parser.getLocalName())) {

                        parser.next();

                        volume = parser.getText();

                    } else if ("journal".equals(parser.getLocalName())) {

                        parser.next();

                        journal = parser.getText();

                    } else if ("year".equals(parser.getLocalName())) {

                        parser.next();

                        year = parser.getText();

                    } else if ("booktitle".equals(parser.getLocalName())) {

                        parser.next();

                        // Save HTML element in the text
                        while (!(parser.getEventType() == XMLStreamConstants.END_ELEMENT && "booktitle".equals(parser.getLocalName()))) {


                            if (parser.getEventType() == XMLStreamConstants.START_ELEMENT) {
                                suf = suf + "<" + parser.getLocalName() + ">";

                            } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT) {
                                suf = suf + "</" + parser.getLocalName() + ">";

                            } else {
                                suf = suf + parser.getText();
                            }

                            parser.next();
                        }

                        booktitle = suf;
                        suf = "";

                    } else if ("publisher".equals(parser.getLocalName())) {

                        parser.next();

                        publisher = parser.getText();


                    } else if ("school".equals(parser.getLocalName())) {

                        parser.next();

                        school = parser.getText();

                    } else if ("ee".equals(parser.getLocalName())) {

                        parser.next();

                        ee = parser.getText();

                    } else if ("url".equals(parser.getLocalName())) {

                        parser.next();

                        url = parser.getText();

                    }

                }

            } else if (isClef) {

                if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "article".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    // we reached the end of an article
                    Article currentPaper = new Article(title, year, mdate, key, journal, volume, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);


                    //System.out.println(title);

                    for (int i = 0; i < authorCount; i++) {
                        updateAuthor(authors[i], currentPaper);
                    }

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "proceedings".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Proceedings currentPaper = new Proceedings(title, year, mdate, key, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)
                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "inproceedings".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Inproceedings currentPaper = new Inproceedings(title, year, mdate, key, booktitle, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)
                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "book".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Book currentPaper = new Book(title, year, mdate, key, publisher, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)

                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "incollection".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Incollection currentPaper = new Incollection(title, year, mdate, key, booktitle, publisher, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)

                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "phdthesis".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Phdthesis currentPaper = new Phdthesis(title, year, mdate, key, school, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)

                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "masterthesis".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Masterthesis currentPaper = new Masterthesis(title, year, mdate, key, school, ee, authors);
                    currentPaper.setID(pID);
                    pID++;
                    paperList.add(currentPaper);

                    for (int i = 0; i < authorCount; i++)

                        updateAuthor(authors[i], currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                } else if (parser.getEventType() == XMLStreamConstants.END_ELEMENT && "www".equals(parser.getLocalName()) && isClef) {

                    isClef = false;
                    Www currentPaper = new Www(title, url, key, authors);
                    webList.add(currentPaper);

                    authorCount = 0;
                    authors = new String[1];


                    if (line > MaxNumLines)
                        stop = true;

                }
            }
        }

        // close the parser
        parser.close();
        System.out.println("Parsing completed.");
    }

    public ArrayList<Author> getAuthorArrayList() {
		if (authorList == null){
			
			// Create the arrayList
			authorList = new ArrayList<Author>();
			Collection<Author> coll = authorTable.values();
			authorList.addAll(coll);
		}
		
		// DEBUG
		System.out.println("Total Number of authors: " + authorList.size());
		
		// Sort array
		sortRankAuthorList(false);
        return authorList;
    }

    public ArrayList<Paper> getPaperArrayList() {

        // DEBUG
        System.out.println("Total Number of papers: " + paperList.size());
        return paperList;
    }

    public ArrayList<Www> getWwwArrayList() {

        return webList;
    }

}
