package com.mcGill_University.ebayAggregate;

import java.util.ArrayList;

import searchEngines.BingSearchEngine;
import searchEngines.GoogleSearchEngine;
import searchEngines.QueryResult;
import searchEngines.YahooSearchEngine;


public class Driver {

	public static void main(String[] args)
	{
		try{
			GoogleSearchEngine aGoogleSearchEngine = new GoogleSearchEngine();
			BingSearchEngine aBingSearchEngine = new BingSearchEngine();
			YahooSearchEngine aYahooSearchEngine = new YahooSearchEngine();
		    String pQuery = "Cat";
		    System.out.println(aGoogleSearchEngine.search(pQuery));
		    System.out.println(aBingSearchEngine.search(pQuery));
		    System.out.println(aYahooSearchEngine.search(pQuery));

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
