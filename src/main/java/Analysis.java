import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import org.ejml.simple.SimpleMatrix;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Analysis{
	
	// Constant 
	private static final double PAGE_RANK_C = 0.85;
	private static final int MAX_ITERATION = 25;
	
	// Name of the file for the JSON
	private static final String FILE_OUTPUT = "analysis.json";
	
	// Private variable of the class
	private ArrayList<Author> authorList;
	
	private ArrayList<Link> linkList;
	private ArrayList<RankAuthor> rankArray;
	
	private SimpleMatrix adjacencyMatrix;
	
	// Constuctor
	public Analysis(ArrayList<Author> authorList){
		this.authorList = authorList;
		linkList = null;
		adjacencyMatrix = null;
		rankArray = null;
	}
	
	/*
		This methods does the PageRank of the authors.
		If the recompute flag is set to true then the PageRank is recomputed
		otherwise it is used the previous one.
	*/
	public ArrayList<RankAuthor> getAuthorRank(boolean recompute){
		
		// If recompute: recompute everything
		if (recompute){
			
			// Create LinkList
			createLinkList();
			
			// Build the adjacency matrix of the graph
			buildMatrix();
			
			// PageRank
			doPageRank();
		
		// If it is called the first time just call doPageRank
		} else if (rankArray == null){
			
			// PageRank
			doPageRank();
		}
		
		System.out.println("getAuthorRank()");
		
		System.out.println("PageRank result: " + rankArray.size());
		
		return rankArray;
	}
	
	/*
		Sort the arrayList class with respect to their PageRank value
	*/
	public ArrayList<RankAuthor> sortRankAuthorList(boolean ascending){
		
		if (rankArray == null){
			// PageRank
			doPageRank();
		}
		
		// Sorting
		if (ascending){
			Collections.sort(rankArray, new Comparator<RankAuthor>() {
					public int compare(RankAuthor author1, RankAuthor author2)
					{
						return  author1.compareTo(author2);
					}
				});
		} else {
			Collections.sort(rankArray, new Comparator<RankAuthor>() {
					public int compare(RankAuthor author1, RankAuthor author2)
					{
						return  -1 * author1.compareTo(author2);
					}
				});
		}
		return rankArray;
	}
	
	/*
		This method creates the JSON Objetc to be sent to the application
	*/
	public JSONObject getRankAuthorJSON(int num, boolean ascending){
		
		if (rankArray == null){
			// PageRank
			doPageRank();
		}
		
		// Sort arrayList
		sortRankAuthorList(ascending);
		
		// Sublist 
		ArrayList<RankAuthor> listAuthor = new ArrayList<RankAuthor>();
		for (int k = 0; k < num && k < rankArray.size(); k++){
			listAuthor.add(rankArray.get(k));
		}
		ArrayList<Link> listLink = getLinkBetweenAuthors(listAuthor);
		
		JSONObject root = JSONBuilder.getAuthorGraph(listAuthor,listLink);
		JSONBuilder.printJSONToFile(root, FILE_OUTPUT);
        
        return root;
	}
	
	/*
		Return an array with the links between the authors inisde the Arralist passed to the method
	*/
	private ArrayList<Link> getLinkBetweenAuthors(ArrayList<RankAuthor> rank){
		
		if (adjacencyMatrix == null){
			buildMatrix();
		}
		
		ArrayList<Link> linkOut = new ArrayList<Link>();
		
		// Create the arrayList with all the links between the authors
		for (int i = 0; i < rank.size(); i++){
			
			int column = rank.get(i).getAuthor().getID();
			
			for (int j = 0; j < rank.size(); j++){
				
				int row = rank.get(j).getAuthor().getID();
				
				// I don't want duplicated links or self loops
				if (row < column){
					// Check if there is a link in the adjacency matrix
					if (adjacencyMatrix.get(row, column) == 1){
						
						// Add the link 
						linkOut.add(new Link(column, row, true));
					}
				}
			}
		}
		
		return linkOut;
	}
	
	/*
		This method creates a list with all the links between the authors
	*/
	private void createLinkList(){
		
		// Create an empty arrayList
		linkList = new ArrayList<Link>();
		
		// Print total number of authors
		System.out.println("Number of authors: " + authorList.size());
		
		// Author ID
		int IDcount = 0;
		
		// HashTable for the authors
		HashMap<Paper, ArrayList<Author>> authorTable = new HashMap<Paper, ArrayList<Author>>();
		
		// Find the coathors and build a bucket table
		System.out.println("\n**** SEARCH COAUTHORS ****\n");
		for (int i = 0; i < authorList.size(); i++){
			
			Author currentAuthor = authorList.get(i);
			
			// Assign ID to the author
			currentAuthor.setID(IDcount);
			ArrayList<Paper> authorPapers = currentAuthor.getPubList();
			
			// Put repeated copy of the author inside the hastable
			for (int j = 0; j < authorPapers.size(); j++){
				
				// Get the corresponding array
				ArrayList<Author> array = authorTable.getOrDefault(authorPapers.get(j), new ArrayList<Author>());
				
				// Add the new entry
				array.add(currentAuthor);
				
				// Update the HashMap
				authorTable.put(authorPapers.get(j), array);
			}
			
			// Increment ID
			IDcount++;
		} 
		
		
		// Create the arrayList with all the links between the authors
		System.out.println("\n**** CREATE LIST OF LINK ****\n");
		
		// HashTable for the links
		HashMap<Link, Integer> linkTable = new HashMap<Link, Integer>();
		
		for (Map.Entry<Paper, ArrayList<Author>> e : authorTable.entrySet()){
			
			ArrayList<Author> list = e.getValue();
			
			for (int i = 0; i < list.size(); i++){
				Author authorSrc = list.get(i);
				
				// Create all the links
				for (int j = i+1; j < list.size(); j++){
					Author authorDest = list.get(j);
					Link curLink = new Link(authorSrc.getID(), authorDest.getID(), true);
			
					// Insert into the HashMap
					linkTable.put(curLink, 1);
				}
			}
		}
		
		// Copy links to the ArrayList
		System.out.println("\n**** COPY LINKS TO ARRAYLIST ****\n");
		
		linkList = new ArrayList();
		for (Map.Entry<Link, Integer> e : linkTable.entrySet()){
			
			Link link = e.getKey();
			linkList.add(link);
		}
		
		System.out.println("Number of not duplicated links: " + linkList.size());
	}
	
	/*
		This method prints data for gephi
	*/
	public void printToFile(String filenameAuthors, String filenameLinks){
		
		if (linkList == null){
			createLinkList();
		}
		
		System.out.println("\n**** PRINTING TO FILE ****\n");
		
		try {
			// Write Authors to the file
			FileWriter fileWriterAuthors = new FileWriter(filenameAuthors);
			
			System.out.println("\n**** PRINTING AUTHORS ****\n");
			
			PrintWriter printAuthor = new PrintWriter(fileWriterAuthors);
			
			// Print the header of the table
			printAuthor.print("Id,Label\n");
			
			// Print all the rows
			for (int i = 0; i < authorList.size(); i++){
				printAuthor.print(authorList.get(i).getID() + "," + authorList.get(i).getName() + "\n");
			}
			printAuthor.close();
			
			// Write Links to the file
			FileWriter fileWriterLinks = new FileWriter(filenameLinks);
			
			System.out.println("\n**** PRINTING LINKS ****\n");
			
			PrintWriter printLink = new PrintWriter(fileWriterLinks);
			
			// Print the header of the table
			printLink.print("Source,Target\n");
			
			// Print all the rows
			for (int i = 0; i < linkList.size(); i++){
				printLink.print(linkList.get(i).getSource() + "," + linkList.get(i).getDestination() + "\n");
			}
			printLink.close();
			
		} catch (IOException e){	
			System.out.println("Error while writing to the file");
		}
	}
	
	/*
		Builds the Adjacency matrix of the Authors
	*/
	private void buildMatrix(){
		
		if (linkList == null){
			createLinkList();
		}
		
		System.out.println("**** BUILDING MATRIX ****");
		System.out.println("buildMatrix()");
		
		int dim = authorList.size();
		
		// Create empty square matrix
		adjacencyMatrix = new SimpleMatrix(dim, dim);
		
		// Fill matrix
		for (int i = 0; i < linkList.size(); i++){
			Link currentLink = linkList.get(i);
			
			// Set to one the entries here there is a link
			adjacencyMatrix.set(currentLink.getSource(), currentLink.getDestination(), 1);
			adjacencyMatrix.set(currentLink.getDestination(), currentLink.getSource(), 1);
		}
	}
	
	/*
		Compute the PageRank of the authors
	*/
	private void doPageRank(){
		
		if (adjacencyMatrix == null){
			buildMatrix();
		}
		
		System.out.println("**** PAGERANK ****");
		System.out.println("doPageRank()");
		
		// Create the output arraylist
		rankArray = new ArrayList<RankAuthor>();
		
		// Get the first term
		System.out.println("Creating vectors ...");
		double[] vector = new double[adjacencyMatrix.numCols()];
		double[][] q = new double[adjacencyMatrix.numCols()][1];
		System.out.println("Filling vectors ...");
		for (int i = 0; i < adjacencyMatrix.numCols(); i++){
			
			int sum = 0;
			for (int j = 0; j < adjacencyMatrix.numCols(); j++){
				sum += adjacencyMatrix.get(j,i);
			}
			
			if (sum != 0){
				vector[i] = 1.0/sum;
			} else {
				vector[i] = 0;
			}
			q[i][0] = 1.0/adjacencyMatrix.numCols();
		}
		
		// Build Diagonal Matrix
		//System.out.println("Building diagonal matrix ...");
		/*
		SimpleMatrix diag = new SimpleMatrix(adjacencyMatrix.numCols(), adjacencyMatrix.numCols());
		for (int i = 0; i < adjacencyMatrix.numCols(); i++){
			diag.set(i,i,vector[i]);
		} 
		*/
		
		System.out.println("Multiplication between matrixes ...");
		// Do Multiplication
		SimpleMatrix mat = new SimpleMatrix(adjacencyMatrix.numRows(),adjacencyMatrix.numCols());
		for (int i = 0; i < adjacencyMatrix.numCols(); i++){
			
			for (int j = 0; j < adjacencyMatrix.numRows(); j++){
				double value = adjacencyMatrix.get(j,i)*vector[i];
				mat.set(i, j, value);
			}
			System.out.println("Column "+ i + " out of " + adjacencyMatrix.numCols());
		}
		// SimpleMatrix mat = adjacencyMatrix.mult(diag);
		System.out.println("Creating q vector ...");
		SimpleMatrix pMat = new SimpleMatrix(q);
		
		// Power Iteration
		System.out.println("\n**** POWER ITERATION ****\n");
		SimpleMatrix p0 = new SimpleMatrix(q);
		for (int iteration = 0; iteration < MAX_ITERATION; iteration++){
			System.out.println("Iteration "+ iteration + " out of " + MAX_ITERATION);
			p0 = mat.mult(p0).scale(PAGE_RANK_C).plus(pMat.scale(1-PAGE_RANK_C));
			p0 = p0.scale(1/p0.elementSum());
		}
		
		// p0 contains the result
		for (int i = 0; i < p0.numRows(); i++){
			rankArray.add(new RankAuthor(authorList.get(i), p0.get(i,0)));
			
			// DEBUG
			// System.out.println("p0[ " + i + " ] = " + p0.get(i,0));
		}
	}
	 	
}
