package searchEngines;

import java.util.ArrayList;

/**
 * @author JoelChev
 * 
 * This Singleton class aggregates the three different SearchEngine implementations. It also has an ArrayList
 * that stores the combined results from each of the search engines. 
 *
 */
public final class SearchEngineAggregator {
	
	private ArrayList<SearchEngine> aSearchEngineList;
	
	private ArrayList<QueryResult> aQueryResults;
	
	private static SearchEngineAggregator instance = new SearchEngineAggregator();
	
	/**
	 * This constructor is private to enforce the Singleton design pattern.
	 */
	private SearchEngineAggregator()
	{
		aQueryResults = new ArrayList<QueryResult>();
		aSearchEngineList = new ArrayList<SearchEngine>();
		aSearchEngineList.add(new GoogleSearchEngine());
		aSearchEngineList.add(new YahooSearchEngine());
		aSearchEngineList.add(new BingSearchEngine());
	};
	
	/**
	 * @return instance the only possible intance of this class.
	 */
	public static SearchEngineAggregator getInstance()
	{
		return instance;
	}
	
	/**
	 * @param pQuery the query for each of the Search Engines to use.
	 * 
	 * This method clears and adds new QueryResults to the ArrayList of Query Results on execution.
	 */
	public void search(String pQuery)
	{
		//Before finding new results for a given query, this gets rid of the old results.
		aQueryResults.clear();
		for(SearchEngine aSearchEngine: aSearchEngineList)
		{
			aSearchEngine.search(pQuery);
			aQueryResults.addAll(aSearchEngine.getQueryResults());
		}
	}
	
	
	/**
	 * @return aQueryResults the ArrayList of combined QueryResults that will later
	 * be formatted as a JSON file.
	 */
	public ArrayList<QueryResult> getQueryResults()
	{
		return aQueryResults;
	}

}
