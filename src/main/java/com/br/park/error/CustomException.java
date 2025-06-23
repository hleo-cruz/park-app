package com.br.park.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private String message;
    private HttpStatus status;
    private Exception exception;

    public CustomException(String message, HttpStatus status, Exception exception) {
        super(exception);
        this.message = message;
        this.status = status;
        this.exception = exception;
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
        this.exception = this;
    }
}
