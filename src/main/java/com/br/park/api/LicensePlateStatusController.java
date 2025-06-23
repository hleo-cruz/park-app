package com.br.park.api;

import com.br.park.api.dto.request.LicensePlateStatusRequestDTO;
import com.br.park.api.dto.response.LicensePlateStatusResponseDTO;
import com.br.park.services.LicensePlateStatusService;
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
public class LicensePlateStatusController {

    private final LicensePlateStatusService service;

    @PostMapping("plate-status")
    @ResponseStatus(HttpStatus.OK)
    public LicensePlateStatusResponseDTO processEvent(@RequestBody LicensePlateStatusRequestDTO dto) {
        return service.findByLicensePlate(dto);
    }

}
