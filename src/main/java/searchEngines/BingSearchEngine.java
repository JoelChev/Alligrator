package searchEngines;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author JoelChev
 *
 *This executes Bing searches for queries. It returns an ArrayList of QueryResults that can be aggregated.
 */
public class BingSearchEngine extends AbstractSearchEngine{
	
	private final String aUrl = "http://www.bing.com/search?q=";
	
	private final String aUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168";
	
	public BingSearchEngine()
	{
		super("Bing");
	}
	
	public void search(String pQuery)
	{
		try{
			String aSearchEngineName = getName();
			ArrayList<QueryResult> searchResults = new ArrayList<QueryResult>();
			String fullUrl = aUrl + pQuery;
		    Document doc = Jsoup.connect(fullUrl).userAgent(aUserAgent).get(); 
		    //Bing makes it very simple to parse for search results. They are listed in
		    //the li.b_algo elements, so no further parsing is needed from here.
		    Elements resultDiv = doc.select("li.b_algo");
		    for(int i =0; i<resultDiv.size(); i++)
		    {
		    	Element resultBlock = resultDiv.get(i);
			    String resultTitle = resultBlock.select("h2").first().text();
			    String resultLink = resultBlock.select("cite").first().text();
			    String resultSummary = resultBlock.select("p").first().text();
			    QueryResult aResult = new QueryResult(aSearchEngineName, resultTitle, resultLink, resultSummary);
			    searchResults.add(aResult);
		    }
		    setQueryResults(searchResults);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
