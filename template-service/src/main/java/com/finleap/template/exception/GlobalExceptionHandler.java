package com.finleap.template.exception;

import com.finleap.template.resp.ErrorStatus;
import com.finleap.template.resp.ErrorStatus.ErrorType;
import com.finleap.template.resp.Response;
import com.finleap.template.resp.ResponseToClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.CompletableFuture;

/**
 * The type Global exception handler.
 *
 * @author Kalidass Mahalingam
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handle exception response.
	 *
	 * @param e the e
	 * @return the response
	 */
	@ExceptionHandler(value = Exception.class)
	public  Response<Object> handleException(Exception e) {
		logger.info("exception ................"+e.getMessage());
		e.printStackTrace();
		Response<Object> errorResponse = new Response<>();
		CompletableFuture future = new CompletableFuture();
		errorResponse.setStatus(new ErrorStatus(ErrorType.INTERNAL_ERROR));
		future.complete(ResponseToClient.objectToClient(errorResponse));
		return errorResponse;
	}

}