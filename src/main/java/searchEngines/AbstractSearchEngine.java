package searchEngines;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class AbstractSearchEngine implements SearchEngine {
	
	private String aName;
	
	private ArrayList<QueryResult> aQueryResults;
	
	
	public AbstractSearchEngine(String pName)
	{
		aName = pName;
	}

	@Override
	public ArrayList<QueryResult> getQueryResults() {
		return aQueryResults;
	}

	@Override
	public void setQueryResults(ArrayList<QueryResult> pQueryResults) {
		aQueryResults = pQueryResults;
		
	}

	@Override
	public void setName(String pName) {
		aName = pName;
		
	}

	@Override
	public String getName() {
		return aName;
	}
	
	/**
	 * @param pQuery the Query to search for.
	 * @return the JSON version of the results.
	 */
	public abstract String search(String pQuery) throws JsonProcessingException;
	
	

}
