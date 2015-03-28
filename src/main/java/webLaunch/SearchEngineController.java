package webLaunch;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import exceptions.EmptyResultsException;
import searchEngines.QueryResult;
import searchEngines.SearchEngineAggregator;

/**
 * @author JoelChev
 * 
 * This class handles the REST side of the Search Engine aggregator. 
 * It maps queries entered in at the server as query parameters
 * to actual queries to search for online.
 *
 */
@RestController
public class SearchEngineController {

    private static final SearchEngineAggregator aSearchEngineAggregator = SearchEngineAggregator.getInstance();
    
    //This logger allows for error messages.
    protected Logger logger;
    
    public SearchEngineController() {
		logger = LoggerFactory.getLogger(getClass());
	}

    /**
     * @param pQuery the query that is taken in as a request parameter.
     * @return the ArrayList of QueryResults that are combined from all 3 implementations of SearchEngines. 
     *Spring allows this to be returned in a JSON format without any explicit conversion.
     */
    @RequestMapping("/search")
    public List<QueryResult> queryResults(@RequestParam(value="query", defaultValue="Joel+Cheverie") String pQuery){
        aSearchEngineAggregator.search(pQuery);
        List<QueryResult> aQueryResults = aSearchEngineAggregator.getQueryResults();
        if(aQueryResults.isEmpty())
        {
        	throw new EmptyResultsException();
        }
        return aQueryResults;
    }
    
    /**
     * @param req the request to the Servlet that triggered the exception.
     * @param exception the actual exception that is being raised here.
     * @return a ModelAndView to display to a user that an error explicitly occurred.
     */
    @ExceptionHandler(EmptyResultsException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
      logger.error("Request: " + req.getRequestURL() + " raised " + exception);

      ModelAndView mav = new ModelAndView();
      mav.addObject("exception", exception);
      mav.addObject("url", req.getRequestURL());
	  mav.addObject("timestamp", new Date().toString());
	  mav.addObject("status", 500);
	  mav.setViewName("support");
      return mav;
    }
}