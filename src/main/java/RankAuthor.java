public class RankAuthor implements Comparable<RankAuthor>{
	
	// Private variable
	private Author author;
	private double rank;
	private int cluster;
	
	// Constructor
	public RankAuthor(Author a, double val, int com){
	
		author = a;
		rank = val;	
		cluster = com;
	}
	
	// Constructor
	public RankAuthor(Author a, double val){
	
		author = a;
		rank = val;	
	}
	
	// Constructor
	public RankAuthor(Author a){
	
		author = a;
	}
	
	// Set Author
	public void setAuthor(Author a){
		author = a;
	}
	
	// Get Author
	public Author getAuthor(){
		return author;
	}
	
	// Set Rank
	public void setRank(double val){
		rank = val;
	}
	
	// Get Rank
	public double getRank(){
		return rank;
	}
	
	// Set Cluster
	public void setCluster(int com){
		cluster = com;
	}
	
	// Get Cluster
	public int getCluster(){
		return cluster;
	}
	
	// Implementation of Comparable
	public int compareTo(RankAuthor author){
		if (rank < author.getRank()){
			return -1;
		} else if (rank == author.getRank()){
			return 0;
		} else {
			return 1;
		}
	}
	
	// Overwrite toStrig
	public String toString() { 
        return author.getName() + " - " + rank; 
    } 
}
