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
	 * An empty constructor for a QueryResult. Allows for an empty QueryResult to be constructed.
	 */
	public QueryResult()
	{
	}
	
	/**
	 * @return the title of this QueryResult.
	 */
	public String getTitle()
	{
		return aTitle;
	}
	
	/**
	 * @param pTitle the title to set for this QueryResult.
	 */
	public void setTitle(String pTitle)
	{
		aTitle = pTitle;
	}
	
	/**
	 * @return the search engine that got this result.
	 */
	public String getSearchEngineName()
	{
		return aSearchEngineName;
	}
	
	/**
	 * @param pSearchEngineName the search engine that returned this result.
	 */
	public void setQuery(String pSearchEngineName)
	{
		aSearchEngineName = pSearchEngineName;
	}
	
	/**
	 * @return the web address link for this query result.
	 */
	public String getLink()
	{
		return aLink;
	}
	
	/**
	 * @param pLink the web address link for this query result.
	 */
	public void setLink(String pLink)
	{
		aLink = pLink;
	}
	
	/**
	 * @return the summary given for this query result by the search engine.
	 */
	public String getSummary()
	{
		return aSummary;
	}
	
	/**
	 * @param pSummary the summary to give this query result from the search engine.
	 */
	public void setSummary(String pSummary)
	{
		aSummary = pSummary;
	}
}
