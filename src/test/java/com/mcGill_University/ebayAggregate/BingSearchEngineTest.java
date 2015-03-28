package com.mcGill_University.ebayAggregate;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import searchEngines.BingSearchEngine;
import searchEngines.QueryResult;

/**
 * @author JoelChev
 * 
 * A JUnit test class for the BingSearchEngine class
 *
 */
public class BingSearchEngineTest {
	
	private BingSearchEngine aBingSearchEngine;
	private String aQuery;
	
	
	/**
	 * This tests the BingSearchEngine with the query Variety show. There are lots of interesting and foreign results
	 * obtained from this query, which allowed me to ensure that my JSON output could handle odd/foreign UNICODE characters
	 * well.
	 */
	@Test
	public void testBingSearch()
	{
		aBingSearchEngine = new BingSearchEngine();
		aQuery = "Variety+show";
		aBingSearchEngine.search(aQuery);
		ArrayList<QueryResult> aQueryResults = aBingSearchEngine.getQueryResults();
		QueryResult toTest = aQueryResults.get(0);
		assertTrue(toTest.getTitle().equals("Variety show - Wikipedia, the free encyclopedia"));
		assertTrue(toTest.getLink().equals("en.wikipedia.org/wiki/Variety_show"));
		assertTrue(toTest.getSummary().equals("A variety show, also known as variety arts or variety entertainment, is entertainment made up of a variety of acts (hence the name), especially musical performances ..."));
		assertTrue(toTest.getSearchEngineName().equals("Bing"));
		
	}

}
