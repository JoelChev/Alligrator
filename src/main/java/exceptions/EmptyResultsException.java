package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.NO_CONTENT, reason="No Results Found")
@SuppressWarnings("serial")
public class EmptyResultsException extends RuntimeException {
	
	public EmptyResultsException()
	{
	}

}
