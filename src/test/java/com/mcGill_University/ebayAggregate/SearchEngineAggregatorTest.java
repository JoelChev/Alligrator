package com.mcGill_University.ebayAggregate;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import searchEngines.QueryResult;
import searchEngines.SearchEngineAggregator;

/**
 * @author JoelChev
 * 
 * A JUnit test class for the SearchEngineAggregator class. 
 *
 */
public class SearchEngineAggregatorTest {

	private SearchEngineAggregator aSearchEngineAggregator;
	
	private String aGoodQuery; //a Query to test desirable functionality.
	
	private String aBadQuery; // a Query to test undesirable functionality.
	
	@Before
	public void setup()
	{
		aSearchEngineAggregator = SearchEngineAggregator.getInstance();
		aGoodQuery = "McGill+University";
		aBadQuery = "afblkjdflkasjdflkajsfjaskldjflskdjflasdkjflkdjlfdf";
	}
	
	/**
	 * Executes the Query of McGill+University in all the SearchEngines. The results are then aggregated
	 * in the SearchEngineAggregator. I can then make sure the proper values are being stored in each field
	 * and that everything is working properly for the main logical abstraction of my application.
	 */
	@Test
	public void testGoodQueryAllSearchEngines()
	{
		aSearchEngineAggregator.search(aGoodQuery);
		List<QueryResult> aCombinedResults = aSearchEngineAggregator.getQueryResults();
		QueryResult aGoogleResult = aCombinedResults.get(0);
		assertEquals("Google",aGoogleResult.getSearchEngineName());
		assertEquals("McGill University: Home Page", aGoogleResult.getTitle());
		assertEquals("www.mcgill.ca/", aGoogleResult.getLink());
		assertEquals("An English-language university located in Montreal, Quebec, Canada.", aGoogleResult.getSummary());
		
		//Get the value in the very middle of the query.
		QueryResult aYahooResult = aCombinedResults.get(12);
		assertEquals("Yahoo",aYahooResult.getSearchEngineName());
		assertEquals("McGill University", aYahooResult.getTitle());
		assertEquals("www.physics.mcgill.ca", aYahooResult.getLink());
		assertEquals("Ernest Rutherford Physics Building McGill University 3600 rue University Montréal, QC Canada H3A 2T8 General inquiries: Undergraduate affairs:", aYahooResult.getSummary());
		
		//Get the very last query result.
		QueryResult aBingResult = aCombinedResults.get(15);
		assertEquals("Bing",aBingResult.getSearchEngineName());
		assertEquals("McGill University - Official Site", aBingResult.getTitle());
		assertEquals("www.mcgill.ca", aBingResult.getLink());
		assertEquals("McGill University. Our 11 faculties and 11 professional schools offer more than 300 programs to some 39,500 graduate, undergraduate and continuing studies students.", aBingResult.getSummary());
	}
	
	/**
	 * This test supplies complete gibberish to each of the SeachEngines. I have this test
	 * to ensure that none of them produce results for this query.
	 * On the server side I throw an EmptyResultsException when this happens, but on the logic end I just want to make 
	 * sure that things are behaving properly.
	 */
	@Test
	public void testBadQueryAllSearchEngines()
	{
		aSearchEngineAggregator.search(aBadQuery);
		List<QueryResult> aCombinedResults = aSearchEngineAggregator.getQueryResults();
		assertEquals(0, aCombinedResults.size());
	}
}
