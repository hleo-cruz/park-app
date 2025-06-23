package com.br.park.error;

import org.springframework.http.HttpStatus;


public class LicensePlateStatusGenericErrorException extends CustomException {

    private static final String message = "There is a not mapped error in license status context";

    public LicensePlateStatusGenericErrorException(Exception cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
