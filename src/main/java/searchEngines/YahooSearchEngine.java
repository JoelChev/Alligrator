package searchEngines;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonProcessingException;

import util.JSONConverter;

public class YahooSearchEngine extends AbstractSearchEngine{
	
private final String aUrl = "https://ca.search.yahoo.com/search?p=";
	
	private final String aUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168";
	
	public YahooSearchEngine()
	{
		super("Yahoo");
	}
	
	public String search(String pQuery) throws JsonProcessingException
	{
		//For now do not process null queries. Refactor this into an expression later.
		if(pQuery== null)
		{
			return null;
		}
		try{
			String aSearchEngineName = getName();
			ArrayList<QueryResult> searchResults = new ArrayList<QueryResult>();
			String fullUrl = aUrl + pQuery;
		    Document doc = Jsoup.connect(fullUrl).userAgent(aUserAgent).get(); 
		    Elements resultDiv = doc.select("ol");
		    Elements resultSection = resultDiv.select("[start]");
		    Elements resultList= resultSection.select("li");
		    for(Element resultBlock: resultList)
		    {
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
		    return JSONConverter.getInstance().convert(searchResults);
		    
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
