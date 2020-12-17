package org.personal.exceptions;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {ResourceNotFoundException.class, ExceptionHandler.class})
public class ResourceNotFoundExceptionHandler implements ExceptionHandler<ResourceNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, ResourceNotFoundException exception) {
        return HttpResponse.badRequest(new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception));
    }
}
