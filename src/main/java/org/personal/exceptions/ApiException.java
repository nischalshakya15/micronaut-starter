package org.personal.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.http.HttpStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiException {

    private HttpStatus status;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String debugMessage;

    public ApiException() {
        timestamp = LocalDateTime.now();
    }

    public ApiException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
