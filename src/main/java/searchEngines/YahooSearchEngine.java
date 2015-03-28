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
 * This class executes Yahoo searches for queries. 
 *
 */
public class YahooSearchEngine extends AbstractSearchEngine{
	
private final String aUrl = "https://ca.search.yahoo.com/search?p=";
	
	private final String aUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168";
	
	public YahooSearchEngine()
	{
		super("Yahoo");
	}
	
	public void search(String pQuery)
	{
		//For now do not process null queries. Refactor this into an expression later.
		if(pQuery== null)
		{
			return;
		}
		try{
			String aSearchEngineName = getName();
			ArrayList<QueryResult> searchResults = new ArrayList<QueryResult>();
			String fullUrl = aUrl + pQuery;
		    Document doc = Jsoup.connect(fullUrl).userAgent(aUserAgent).get(); 
		    //This gets the list of results for the query to parse.
		    Elements resultDiv = doc.select("ol");
		    //The next two selections are simply filters to ensure that only 
		    //valid results (not ads or non result blocks) are parsed.
		    Elements resultSection = resultDiv.select("[start]");
		    Elements resultList= resultSection.select("li");
		    for(Element resultBlock: resultList)
		    {
		    	//If there is no easily parsable summary for a given result, it is skipped. This usually involves
		    	//a weird article being sponsored from something like National Geographic which does
		    	//not follow the normal article format.
		    	if(resultBlock.select(".abstr").first()==null)
		    	{
		    		continue;
		    	}
		    	String resultTitle = resultBlock.select("h3").first().text();
			    String resultLink = resultBlock.select("span").first().text();
			    String resultSummary = resultBlock.select(".abstr").first().text();
			    QueryResult aQueryResult = new QueryResult(aSearchEngineName, resultTitle, resultLink, resultSummary);
			    searchResults.add(aQueryResult);
			    
		    }
		    setQueryResults(searchResults);
		    
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
