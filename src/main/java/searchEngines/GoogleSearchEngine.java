package searchEngines;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * @author JoelChev
 * 
 * This executes Google searches for queries. There is a lot more subtlety and things going on under the hood with Google
 * searches, so this class is slightly beefier than the other two Search Engine implementation classes.
 *
 */
public class GoogleSearchEngine extends AbstractSearchEngine {
	
	private final String aUrl = "http://www.google.com/search?q=";
	
	private final String aUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168";
	
	public GoogleSearchEngine()
	{
		super("Google");
	}
	
	public void search(String pQuery)
	{
		try{
			ArrayList<QueryResult> searchResults = new ArrayList<QueryResult>();
			String fullUrl = aUrl + pQuery;
		    Document aDocument = Jsoup.connect(fullUrl).userAgent(aUserAgent).get();
		    getSuggestedResults(searchResults, aDocument);
		    getDivSRGResults(searchResults, aDocument);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param searchResults The ArrayList to add QueryResults to.
	 * @param pDocument the Current Document that is being parsed.
	 * This method does a first parse over the results. If a concrete entity like McGill University
	 * is the query, Google will actually find the portal for that entity and display it in a special way at the 
	 * top of the results. A different format of parsing is consequently needed to parse these suggested results.
	 */
	public void getSuggestedResults(ArrayList<QueryResult> searchResults, Document pDocument)
	{
		//Determines if a preview box has been rendered or not. If one has, skip this method as 
		//no useful queries will come from it. These preview boxes tend to be generated
		//when things like Scientific queries (like Coulomb's law) are entered as queries, but
		//in general they do not appear.
	    Element aPreviewBox = pDocument.select("div._Pre").first();
	    if(aPreviewBox != null)
	    {	
		    return;
	    }    
	    if(pDocument.select("div.rc").first()!=null)
	    {
	    	//Gets the relevant Div of the result to parse.
	    	Element relevantDiv = pDocument.select("div.rc").first();
	    	String resultTitle = relevantDiv.select("h1,h2,h3,h4,h5,h6").first().text();
			String resultLink = relevantDiv.select("cite").first().text();
			String resultSummary = processSummary(relevantDiv.select("span.st").text());
			QueryResult aResult = new QueryResult(getName(), resultTitle, resultLink, resultSummary);
			searchResults.add(aResult);
	    }
	}
	
	/**
	 * @param searchResults the ArrayList in which to add the new QueryResults.
	 * @param pDocument the Document that is being parsed over.
	 * 
	 * This method parses the general results section of the page. In general, most 
	 * results will be found in the SRG section of the page, so this handles the majority of results.
	 * In most cases, it is the only method that is actually needed.
	 */
	public void getDivSRGResults(ArrayList<QueryResult> searchResults, Document pDocument)
	{
		for(int j=0; j<pDocument.select("div.srg").size(); j++)
		{
		    //This section gets around the ads that are displayed. Some results found in the SRG
			//are not desirable results, so they are skipped.
		    Element resultDiv = pDocument.select("div.srg").get(j);
		    //iterates over the list in the results Div.
			for(Element resultList : resultDiv.select("li"))
			{
				//ignore the empty case.
				Element resultBlock = resultList.select("div").first();
				if(resultBlock == null)	
				{	
					continue;
				}	
			    Element resultContent = resultBlock.select("div").first();
			    String resultTitle = resultBlock.select("h1,h2,h3,h4,h5,h6").first().text();
				String resultLink = resultContent.select("cite").first().text();
				String resultSummary = processSummary(resultContent.select("span").text());
				QueryResult aResult = new QueryResult(getName(), resultTitle, resultLink, resultSummary);
				searchResults.add(aResult);
			}
		}
        setQueryResults(searchResults);
	}
	
	/**
	 * @param pSummaryToProcess the Summary that needs processing
	 * @return a summary of a result that can be tested for equality more easily
	 * 
	 * The summaries in Google need to be parsed as weird Unicode characters are being stored
	 * at the end of a String if there is a ... at the end of them. This breaks my testing suite, so I'm
	 * modifying the Strings to end in a predictable way if necessary.
	 */
	public String processSummary(String pSummaryToProcess)
	{
		int length = pSummaryToProcess.length();
		if(pSummaryToProcess.length()>3 && pSummaryToProcess.substring(length-3).equals("..."))
		{
			 pSummaryToProcess = new String(pSummaryToProcess.substring(0,length-4)+" ...");
		}
		return pSummaryToProcess;
		
	}
}
