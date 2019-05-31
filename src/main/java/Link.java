public class Link{

	// Private variables
	private int source;
	private int destination;
	private boolean undirected;
	
	// Constructor
	public Link(int src, int dest, boolean flag){
		source = src;
		destination = dest;
		undirected = flag;
	}
	
	// Get destination
	public int getDestination(){
		return destination;
	}
	
	// Set destination
	public void setDestination(int dest){
		destination = dest;
	}
	
	// Get Source
	public int getSource(){
		return source;
	}
	
	// Set Source
	public void setSource(int src){
		source = src;
	}
	
	// Check if the link is undirected
	public boolean isUndirected(){
		return undirected;
	}
	
	// Define equals method
	public boolean equals(Link link) {
		
		if (link.getDestination() == destination && link.getSource() == source){
			return true;
		}
		
		// If it is undirected we check the other possibility
		if (undirected && link.getDestination() == source && link.getSource() == destination){
			return true;
		}

		return false;

	}
	
	// Define equals method
	public String toString() { 
		if (source < destination){
			return source + " - " + destination; 
		} else {
			return destination + " - " + source; 
		}
    } 
    
    // Define hashCode method
	public int hashCode() {
		/*return this.toString().hashCode();*/
		try{
			if (source < destination){
				return (source + "" + destination).hashCode();
			} else {
				return (destination + "" + source).hashCode();
			}
		} catch (Exception e){
			System.out.println("Error in Hash of Links");
			return 0;
		}
	}
}
