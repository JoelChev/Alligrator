package searchEngines;

import java.util.ArrayList;


/**
 * @author JoelChev
 *
 *An abstract implementation of Search Engines which provides some basic default behaviour for each kind
 *of Search Engine. 
 *
 */
public abstract class AbstractSearchEngine implements SearchEngine {
	
	private String aName;
	
	private ArrayList<QueryResult> aQueryResults;
	
	
	public AbstractSearchEngine(String pName)
	{
		aName = pName;
	}


	public ArrayList<QueryResult> getQueryResults() {
		return aQueryResults;
	}


	public void setQueryResults(ArrayList<QueryResult> pQueryResults) {
		aQueryResults = pQueryResults;
		
	}


	public void setName(String pName) {
		aName = pName;
		
	}


	public String getName() {
		return aName;
	}
	
	
	

}
