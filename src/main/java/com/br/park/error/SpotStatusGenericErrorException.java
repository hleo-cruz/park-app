package com.br.park.error;

import org.springframework.http.HttpStatus;


public class SpotStatusGenericErrorException extends CustomException {

    private static final String message = "There is a not mapped error in spot status context";

    public SpotStatusGenericErrorException(Exception cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
