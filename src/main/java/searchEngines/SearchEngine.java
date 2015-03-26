package searchEngines;

import java.util.ArrayList;

/**
 * @author JoelChev
 * 
 * An interface to abstract the different SearchEngines to be used.
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
	 * @param pName the Name to give the SearchEngine.
	 */
	void setName(String pName);
	
	/**
	 * @return the name of this SearchEngine.
	 */
	public String getName();

}
