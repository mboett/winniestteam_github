import java.util.ArrayList;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;





public class DatabasePopulation {

	

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String DATABASE = "jdbc:postgresql://localhost/clef";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String PATH_TO_FILE = "src\\main\\database\\db_population.sql";
	private static final String FORMAT_PATH_TO_FILE = "src\\main\\database\\db_population";

	private static final String INSERT_AUTHOR = "INSERT INTO Author (AuthorID, Name) VALUES (?, ?)";
	private static final String INSERT_PAPER = "INSERT INTO Paper (PaperID, Title, Year, MDate, Key, DOI) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String INSERT_RELATION = "INSERT INTO Write (AuthorID, PaperID) VALUES (?, ?)";

	public static void populateDb(ArrayList<Author> list) {



		

		int cFile = 1;
		int cmdLimit = 1000;
		int c = 0;
		
		// The connection to the DBMS
		Connection con = null;

		// The SQL statement to be executed
		PreparedStatement authorPstmt = null;
		PreparedStatement paperPstmt = null;
		PreparedStatement relationPstmt = null;

		// Start time of a statement
		long start;

		// End time of a statement
		long end;


		Author currAut = null;
		ArrayList<Paper> papList = null;
		Paper currPap = null;
		int autID = -1;
		int papID = -1;
		String autName = null;
		String papTitle = null;
		String year = null;
		String mDate = null;
		String key = null;
		String doi = null;
		String sqlCmd = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.sql.Date sqlDate = null;
		
	    try {
	    	// append = false
	    	FileWriter fw =new FileWriter(PATH_TO_FILE, false);
	    	fw.write("");
	    	fw.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } 


    	try {
	    	// append = false
	    	FileWriter fw =new FileWriter(FORMAT_PATH_TO_FILE+cFile+".sql", false);
	    	fw.write("");
	    	fw.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } 
	    appCmd("\\c clef\n");
	    //System.in.read();





		try {
			// Register the JDBC driver
			Class.forName(DRIVER);

			System.out.printf("Driver %s successfully registered.%n", DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.printf(
					"Driver %s not found: %s.%n", DRIVER, e.getMessage());

			// Terminate with a generic error code
			System.exit(-1);
		}

		try {

			// Connect to the database
			start = System.currentTimeMillis();			
			
			con = DriverManager.getConnection(DATABASE, USER, PASSWORD);								
			
			end = System.currentTimeMillis();

			System.out.printf(
					"Connection to database %s successfully established in %,d milliseconds.%n",
					DATABASE, end-start);

			// Create the statements for inserting the data
			start = System.currentTimeMillis();
			
			authorPstmt = con.prepareStatement(INSERT_AUTHOR);
			paperPstmt = con.prepareStatement(INSERT_PAPER);
			relationPstmt = con.prepareStatement(INSERT_RELATION);

			end = System.currentTimeMillis();
			
			System.out.printf(
					"Statements successfully created in %,d milliseconds.%n",
					end-start);

		} catch (SQLException e) {
			System.out.printf("Connection error:%n");

			// Get all the exceptions
			while (e != null) {
				System.out.printf("Message: %s%n", e.getMessage());
				System.out.printf("SQL status code: %s%n", e.getSQLState());
				System.out.printf("SQL error code: %s%n", e.getErrorCode());
				System.out.printf("%n");
				e = e.getNextException();
			}
			
			// Terminate with a generic error code
			System.exit(-1);
		}

	
		try {
			
			// While the are lines to be read from the input file
			for (int i=0; i<list.size(); i++) {
				


				//System.out.printf("%n--------------------------------------------------------%n");
				
				// Read one line from the input file
				start = System.currentTimeMillis();
				
				currAut = list.get(i);
				autID = currAut.getID();
				autName = currAut.getName();
				//"INSERT INTO Author (AuthorID, Name) VALUES (?, ?)"
				sqlCmd = "INSERT INTO Author (AuthorID, Name) VALUES (" + autID +", '" + autName.replaceAll("'", "''") +"');\n";
				
				try {
					authorPstmt.setInt(1, autID);
					authorPstmt.setString(2, autName);
					authorPstmt.execute();
					appCmd(sqlCmd);
					appSnip(sqlCmd, FORMAT_PATH_TO_FILE+cFile+".sql");
					c++;

				} catch (Exception e) {

					System.out.println("Exception while populating authors");
				}




				papList = currAut.getPubList();


				for (int j=0; j<papList.size(); j++) {

					if (c>cmdLimit) {

						cmdLimit += 1000;
						cFile++;
						try {
					    	// append = false
					    	FileWriter fw =new FileWriter(FORMAT_PATH_TO_FILE+cFile+".sql", false);
					    	fw.write("");
					    	fw.close();
					    } catch (IOException e) {
					    	e.printStackTrace();
					    } 



					}

					currPap = papList.get(j);
					papID = currPap.getID();
					papTitle = currPap.getTitle();
					year = currPap.getYear();
					mDate = currPap.getMdate();


					try {

						date = sdf.parse(mDate);
						sqlDate = new java.sql.Date(date.getTime());

					} catch (Exception e) {

						System.out.println("Exception while converting the year");
					}

					key = currPap.getKey();
					doi = currPap.getEe();

					//"INSERT INTO Paper (PaperID, Title, Year, MDate, Key, DOI) VALUES (?, ?, ?, ?, ?, ?)"
					sqlCmd = "INSERT INTO Paper (PaperID, Title, Year, MDate, Key, DOI) VALUES (" + papID + ", '" + papTitle.replaceAll("'", "''")
												+ "', " + year + ", '" + mDate + "', '" + key + "', '" + doi + "');\n";
					try {
						paperPstmt.setInt(1, papID);
						paperPstmt.setString(2, papTitle);
						paperPstmt.setInt(3, Integer.parseInt(year));
						paperPstmt.setDate(4, sqlDate);
						paperPstmt.setString(5, key);
						paperPstmt.setString(6, doi);
						paperPstmt.execute();
						appCmd(sqlCmd);
						appSnip(sqlCmd, FORMAT_PATH_TO_FILE+cFile+".sql");
						c++;

					} catch (SQLException e) {

						
						while (e != null) {
							
							if (!(e.getSQLState().equals("23505"))) {
								System.out.printf("Message: %s%n", e.getMessage());
								System.out.printf("SQL status code: %s%n", e.getSQLState());
								System.out.printf("SQL error code: %s%n", e.getErrorCode());
								System.out.printf("%n");
							}
							e = e.getNextException();
						}
					}
					//"INSERT INTO Write (AuthorID, PaperID) VALUES (?, ?)"
					sqlCmd = "INSERT INTO Write (AuthorID, PaperID) VALUES (" + autID +", " + papID +");\n";
					try {
						relationPstmt.setInt(1, autID);
						relationPstmt.setInt(2, papID);
						relationPstmt.execute();
						appCmd(sqlCmd);
						appSnip(sqlCmd, FORMAT_PATH_TO_FILE+cFile+".sql");
						c++;

					} catch (Exception e) {

						System.out.println("Exception while populating relation");
					}

				}


				
				end = System.currentTimeMillis();
				
				/*System.out.printf(
						"Author %,d successfully inserted into the database in %,d milliseconds.%n%n",
						autID, end-start);*/

			}
		} finally {

		

			
			System.out.printf("%nPopulation successfully performed.%n");
		}

		try {

			// Close the statements
			if (authorPstmt != null &&
				paperPstmt != null &&
				relationPstmt != null) {						
				
				start = System.currentTimeMillis();
					
				authorPstmt.close();
				paperPstmt.close();
				relationPstmt.close();
				
				
				end = System.currentTimeMillis();

				System.out
					.printf("Prepared statements successfully closed in %,d milliseconds.%n",
							end-start);
			
			}

			// Close the connection to the database
			if(con != null) {
				
				start = System.currentTimeMillis();
				
				con.close();
				
				end = System.currentTimeMillis();
				
				System.out
				.printf("Connection successfully closed in %,d milliseconds.%n",
						end-start);
				
			}
			
			System.out.printf("Resources successfully released.%n");

		} catch (SQLException e) {
			System.out.printf("Error while releasing resources:%n");

			// Get all the exceptions
			while (e != null) {
				System.out.printf("Message: %s%n", e.getMessage());
				System.out.printf("SQL status code: %s%n", e.getSQLState());
				System.out.printf("SQL error code: %s%n", e.getErrorCode());
				System.out.printf("%n");
				e = e.getNextException();
			}
		}

	}

	private static void appCmd(String str) 
    { 
        try { 
  
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter(PATH_TO_FILE, true)); 
            out.write(str); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        } 
    } 

    private static void appSnip(String str, String path) 
    { 
        try { 
  
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter(path, true)); 
            out.write(str); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        } 
    } 

}
