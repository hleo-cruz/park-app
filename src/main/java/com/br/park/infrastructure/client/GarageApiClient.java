package com.br.park.infrastructure.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GarageApiClient {

    @Value("${garage.api.url}")
    private String garageApiUrl;

    private final RestTemplate restTemplate;

    public GarageDTO findGarage() {

        try {
            return restTemplate.getForObject(garageApiUrl, GarageDTO.class);
        } catch (Exception e) {
            return null;
        }

    }
}

