package com.mcGill_University.ebayAggregate;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import searchEngines.YahooSearchEngine;
import searchEngines.QueryResult;

/**
 * @author JoelChev
 * 
 * A JUnit test class for the YahooSearchEngine.
 *
 */
public class YahooSearchEngineTest {
	
	private YahooSearchEngine aYahooSearchEngine;
	
	private String aQuery;
	
	
	/**
	 * This tests the YahooSearchEngine class with a fairly obscure topic of Insatiability. This was a useful query as 
	 * it allowed me to ensure that it was not getting sucked into ads or other garbage in the results.
	 */
	@Test
	public void testYahooSearch()
	{
		aYahooSearchEngine = new YahooSearchEngine();
		aQuery = "Insatiability";
		aYahooSearchEngine.search(aQuery);
		ArrayList<QueryResult> aQueryResults = aYahooSearchEngine.getQueryResults();
		QueryResult toTest = aQueryResults.get(4);
		assertTrue(toTest.getTitle().equals("Insatiability Synonyms, Insatiability Antonyms | Thesaurus.com"));
		assertTrue(toTest.getLink().equals("www.thesaurus.com/browse/insatiability"));
		assertTrue(toTest.getSummary().equals("Synonyms for insatiability at Thesaurus.com with free online thesaurus, antonyms, and definitions. Dictionary and Word of the Day."));
		assertTrue(toTest.getSearchEngineName().equals("Yahoo"));
		
	}

}