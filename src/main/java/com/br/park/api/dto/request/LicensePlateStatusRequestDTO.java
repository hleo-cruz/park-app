package com.br.park.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicensePlateStatusRequestDTO {

    @JsonProperty(value = "license_plate")
    private String licensePlate;
}
