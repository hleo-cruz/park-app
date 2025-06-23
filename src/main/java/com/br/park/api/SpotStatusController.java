package com.br.park.api;

import com.br.park.api.dto.request.RevenueRequestDTO;
import com.br.park.api.dto.request.SpotStatusRequestDTO;
import com.br.park.api.dto.response.RevenueResponseDTO;
import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.services.ParkLotService;
import com.br.park.services.SpotStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpotStatusController {

    private final SpotStatusService service;

    @PostMapping("spot-status")
    @ResponseStatus(HttpStatus.OK)
    public SpotStatusResponseDTO processEvent(@RequestBody SpotStatusRequestDTO dto) {
        return service.findByLatLng(dto);
    }

}
