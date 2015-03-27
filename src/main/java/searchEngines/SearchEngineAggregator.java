package searchEngines;

import java.util.ArrayList;

public final class SearchEngineAggregator {
	
	private ArrayList<SearchEngine> aSearchEngineList;
	
	private ArrayList<QueryResult> aQueryResults;
	
	private static SearchEngineAggregator instance = new SearchEngineAggregator();
	
	private SearchEngineAggregator()
	{
		aQueryResults = new ArrayList<QueryResult>();
		aSearchEngineList = new ArrayList<SearchEngine>();
		aSearchEngineList.add(new GoogleSearchEngine());
		aSearchEngineList.add(new YahooSearchEngine());
		aSearchEngineList.add(new BingSearchEngine());
	};
	
	public static SearchEngineAggregator getInstance()
	{
		return instance;
	}
	
	public void search(String pQuery)
	{
		//Get rid of the old results.
		aQueryResults.clear();
		for(SearchEngine aSearchEngine: aSearchEngineList)
		{
			aSearchEngine.search(pQuery);
			aQueryResults.addAll(aSearchEngine.getQueryResults());
		}
	}
	
	public ArrayList<QueryResult> getQueryResults()
	{
		return aQueryResults;
	}

}
