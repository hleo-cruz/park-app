package com.br.park.error;

import org.springframework.http.HttpStatus;


public class RevenueGenericErrorException extends CustomException {

    private static final String message = "There is a not mapped error in revenue context";

    public RevenueGenericErrorException(Exception cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
