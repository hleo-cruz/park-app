package com.br.park.services;

import com.br.park.api.dto.request.SpotStatusRequestDTO;
import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.infrastructure.repository.spotStatus.SpotStatus;
import com.br.park.infrastructure.repository.spotStatus.SpotStatusRepository;
import com.br.park.mapper.SpotStatusMapper;
import com.br.park.services.calculateprice.CalculatePriceDTO;
import com.br.park.services.calculateprice.CalculatePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SpotStatusService {

    private final SpotStatusRepository repository;
    private final CalculatePriceService calculatePriceService;

    public SpotStatusResponseDTO findByLatLng(SpotStatusRequestDTO dto) {

        String lat = dto.getLat();
        String lng = dto.getLng();

        SpotStatus spotStatus = repository.findByLatAndLng(lat, lng);
        BigDecimal price = calculatePrice(spotStatus);

        return SpotStatusMapper.toResponse(spotStatus, price);

    }

    private BigDecimal calculatePrice(SpotStatus spotStatus) {
        CalculatePriceDTO calculatePrice = build(spotStatus);
        return calculatePriceService.calculatePrice(calculatePrice);
    }

    private CalculatePriceDTO build(SpotStatus spotStatus) {
        return CalculatePriceDTO.builder()
                .charge(spotStatus.getCharge())
                .entryTime(spotStatus.getEntryTime())
                .exitTime(spotStatus.getTimeParked())
                .basePrice(spotStatus.getBasePrice())
                .durationLimitMinutes(spotStatus.getDuration())
                .typeChargeApply(spotStatus.getTypeChargeApply())
                .build();
    }
}
