package com.br.park.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MandatoryParamsNotInformedException extends CustomException {

    public MandatoryParamsNotInformedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
