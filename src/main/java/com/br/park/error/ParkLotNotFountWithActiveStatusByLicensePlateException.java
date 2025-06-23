package com.br.park.error;

import org.springframework.http.HttpStatus;


public class ParkLotNotFountWithActiveStatusByLicensePlateException extends CustomException {

    private static final String message = "There is a no park lot register for license plate with 'ACTIVE' status";

    public ParkLotNotFountWithActiveStatusByLicensePlateException() {
        super(message, HttpStatus.NOT_FOUND);
    }

}
