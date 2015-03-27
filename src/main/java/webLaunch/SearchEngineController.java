package webLaunch;

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

@RestController
public class SearchEngineController {

    private static final SearchEngineAggregator aSearchEngineAggregator = SearchEngineAggregator.getInstance();
    
    protected Logger logger;
    
    public SearchEngineController() {
		logger = LoggerFactory.getLogger(getClass());
	}

    @RequestMapping("/search")
    public List<QueryResult> queryResults(@RequestParam(value="query", defaultValue="Joel+Cheverie") String name){
        aSearchEngineAggregator.search(name);
        List<QueryResult> aQueryResults = aSearchEngineAggregator.getQueryResults();
        if(aQueryResults.isEmpty())
        {
        	throw new EmptyResultsException();
        }
        return aQueryResults;
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
      logger.error("Request: " + req.getRequestURL() + " raised " + exception);

      ModelAndView mav = new ModelAndView();
      mav.addObject("EmptyResultsException", exception);
      mav.addObject("status", 500);
      mav.addObject("url", req.getRequestURL());
      mav.setViewName("error");
      return mav;
    }
}