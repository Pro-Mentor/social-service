package com.github.promentor.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;

/**
 * This is a Global Exception Mapper to Centralize error handling
 * return error with response with following format
 *  {
 *     "message": "String",
 *     "errorStatus": "String",
 *     "errorCode": "String"
 *  }
 *          errorCode  The application specific {@link ErrorCode}.
 *          message     The reason for the error
 *          errorStatus The {@link ErrorCode} that should be sent.
 */
@Provider
public class ErrorPageResponseExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Log.error("From Global Exception Mapper : " + exception);
        Log.debug(Arrays.toString(exception.getStackTrace()));

        return mapExceptionToResponse(exception);
    }

    // format the response error message
    private Response mapExceptionToResponse(Exception exception) {
        Log.debug("In the global exception mapper");

        // Use response from ApiException as they are
        if (exception instanceof CustomException originalErrorResponse) {
            Log.debug("match with ApiException");

            // Overwrite error message
            Log.error("sending the error response: " + originalErrorResponse.getErrorCode());
            Log.debug("error response: " + new ErrorMessage(originalErrorResponse.getReason(), originalErrorResponse.getErrorCode()));

            return Response
                    .status(originalErrorResponse.getHttpStatus())
                    .entity(new ErrorMessage(originalErrorResponse.getReason(), originalErrorResponse.getErrorCode()))
                    .build();
        }

        // use fpr WebApplicationExceptions
        if (exception instanceof WebApplicationException webApplicationException) {
            Log.debug("match with WebApplicationException");

            Log.error("sending the error response: " + webApplicationException.getMessage());
            Log.debug("error response: " + new ErrorMessage(webApplicationException.getMessage(), ErrorCode.UNKNOWN));

            return Response
                    .status(webApplicationException.getResponse().getStatus())
                    .entity(new ErrorMessage(webApplicationException.getMessage(), ErrorCode.UNKNOWN))
                    .build();

        }

        Log.info("send the error response " + ErrorCode.UNKNOWN);
        Log.debug("not caught error, error response: " + new ErrorMessage("Internal Server Error", ErrorCode.UNKNOWN));

        // not caught errors
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage("Internal Server Error", ErrorCode.UNKNOWN))
                .build();

    }

}
