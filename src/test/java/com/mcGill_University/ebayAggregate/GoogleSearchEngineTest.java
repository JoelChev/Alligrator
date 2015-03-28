package com.mcGill_University.ebayAggregate;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import searchEngines.GoogleSearchEngine;
import searchEngines.QueryResult;

/**
 * @author JoelChev
 * 
 * A JUnit Test class for the GoogleSearchEngine class.
 *
 */
public class GoogleSearchEngineTest {
	
	private GoogleSearchEngine aGoogleSearchEngine;
	
	private String aQuery;
	
	private String aSummary;
	
	/**
	 * tests the Google Search class with the String "Colomb's law. This is a pretty technical query and allows me to make
	 * sure that unrelated and non-relevant results are not stored.
	 */
	@Test
	public void testGoogleSearch()
	{
		aGoogleSearchEngine = new GoogleSearchEngine();
		aQuery = "Coulombs+law";
		aSummary = "Note that this satisfies Newton's third law because it implies that exactly the same magnitude of force acts on q2 . Coulomb's law is a vector equation and ...";
		aGoogleSearchEngine.search(aQuery);
		ArrayList<QueryResult> aQueryResults = aGoogleSearchEngine.getQueryResults();
		QueryResult toTest = aQueryResults.get(2);
		assertTrue(toTest.getTitle().equals("Coulomb's Law - HyperPhysics"));
		assertTrue(toTest.getLink().equals("hyperphysics.phy-astr.gsu.edu/hbase/electric/elefor.html"));
		assertTrue(toTest.getSummary().equals(aSummary));
		assertTrue(toTest.getSearchEngineName().equals("Google"));
		
	}

}
