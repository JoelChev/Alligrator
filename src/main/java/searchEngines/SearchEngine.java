package searchEngines;

import java.util.ArrayList;

/**
 * @author JoelChev
 * 
 * An interface to abstract the different SearchEngines to be used.
 * This will allow for polymorphism when dealing with all of the SearchEngines.
 *
 */
public interface SearchEngine {

	/**
	 * @return an ArrayList of QueryResults to serialize as a JSON file.
	 */
	ArrayList<QueryResult> getQueryResults();
	
	/**
	 * @param pQueryResults the ArrayList of queryResults for this SearchEngine.
	 */
	void setQueryResults(ArrayList<QueryResult> pQueryResults);
	
	/**
	 * @return the name of this SearchEngine.
	 */
	public String getName();
	
	
	/**
	 * @param pQuery the query to search for in this SearchEngine.
	 */
	public void search(String pQuery);

}
