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
@Requires(classes = {TokenNotFoundException.class, ExceptionHandler.class})
public class TokenNotFoundExceptionHandler implements ExceptionHandler<TokenNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, TokenNotFoundException exception) {
        return HttpResponse.badRequest(new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception));
    }
}
