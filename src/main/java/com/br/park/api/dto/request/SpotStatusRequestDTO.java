package com.br.park.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotStatusRequestDTO {

    @JsonProperty(value = "lat")
    private String lat;
    @JsonProperty(value = "lng")
    private String lng;
}
