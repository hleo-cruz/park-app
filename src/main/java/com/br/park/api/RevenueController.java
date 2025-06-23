package com.br.park.api;

import com.br.park.api.dto.request.RevenueRequestDTO;
import com.br.park.api.dto.request.SpotStatusRequestDTO;
import com.br.park.api.dto.response.RevenueResponseDTO;
import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.services.RevenueService;
import com.br.park.services.SpotStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("revenue")
public class RevenueController {

    private final RevenueService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public RevenueResponseDTO processEvent(@RequestBody RevenueRequestDTO dto) {
        return service.findByDateAndSector(dto);
    }

}
