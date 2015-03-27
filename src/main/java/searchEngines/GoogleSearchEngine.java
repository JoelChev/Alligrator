package searchEngines;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GoogleSearchEngine extends AbstractSearchEngine {
	
	private final String aUrl = "http://www.google.com/search?q=";
	
	private final String aUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168";
	
	public GoogleSearchEngine()
	{
		super("Google");
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
		    Document doc = Jsoup.connect(aUrl +pQuery).userAgent(aUserAgent).get();  
  		    for(int j=0; j<doc.select("div.srg").size(); j++)
  		    {
  		    	//This section gets around the ads that are displayed.
  		    	Element resultDiv = doc.select("div.srg").get(j);
				for(Element resultList : resultDiv.select("li"))
				{
					Element resultBlock = resultList.select("div").first();
					if(resultBlock == null)	
					{	
						continue;
					}	
				    Element resultContent = resultBlock.select("div").first();
				    String resultTitle = resultBlock.select("h1,h2,h3,h4,h5,h6").first().text();
					String resultLink = resultContent.select("cite").first().text();
					String resultSummary = resultContent.select("span").text();
					QueryResult aResult = new QueryResult(aSearchEngineName, resultTitle, resultLink, resultSummary);
					searchResults.add(aResult);
				}
  		    }
	        setQueryResults(searchResults);
		  
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public String getUrl()
	{
		return aUrl;
	}
	
	public String getUserAgent()
	{
		return aUserAgent;
	}
	

}
