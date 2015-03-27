package searchEngines;

/**
 * @author JoelChev
 * 
 * A representation of a given QueryResult to store later as a JSON file.
 *
 */
public class QueryResult {
	
	private String aSearchEngineName;

	private String aTitle;
	
	private String aLink;
	
	private String aSummary;
	
	/**
	 * @param pQuery the Query for this QueryResult
	 * @param pTitle the Title for this QueryResult
	 * @param pLink the web address link for this QueryResult
	 * @param pSummary the summary returned for this QueryResult
	 * 
	 * This is a constructor that initializes all the fields of a QueryResult
	 */
	public QueryResult(String pSearchEngineName, String pTitle, String pLink, String pSummary)
	{
		aSearchEngineName = pSearchEngineName;
		aTitle = pTitle;
		aLink = pLink;
		aSummary = pSummary;
	}
	
	/**
	 * @return the title of this QueryResult.
	 */
	public String getTitle()
	{
		return aTitle;
	}
	
	/**
	 * @return the search engine that got this result.
	 */
	public String getSearchEngineName()
	{
		return aSearchEngineName;
	}
	
	/**
	 * @return the web address link for this query result.
	 */
	public String getLink()
	{
		return aLink;
	}
	
	/**
	 * @return the summary given for this query result by the search engine.
	 */
	public String getSummary()
	{
		return aSummary;
	}
	
}
