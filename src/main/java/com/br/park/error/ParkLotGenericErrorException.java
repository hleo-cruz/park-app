package com.br.park.error;

import org.springframework.http.HttpStatus;


public class ParkLotGenericErrorException extends CustomException {

    private static final String message = "There is a not mapped error in Park Lot context";

    public ParkLotGenericErrorException(Exception cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
