package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JoelChev
 * 
 * This exception will be thrown if no results are found for a given query. It will give the user
 * a warning message and inform them that their query found nothing.
 *
 */
@ResponseStatus(value =HttpStatus.NO_CONTENT, reason="No Results Found")
@SuppressWarnings("serial")
public class EmptyResultsException extends RuntimeException {
	
	public EmptyResultsException()
	{
	}

}
