package com.br.park.services;

import com.br.park.api.dto.request.LicensePlateStatusRequestDTO;
import com.br.park.api.dto.response.LicensePlateStatusResponseDTO;
import com.br.park.error.LicensePlateStatusNotFoundErrorException;
import com.br.park.infrastructure.repository.licensePlateStatus.LicensePlateStatus;
import com.br.park.infrastructure.repository.licensePlateStatus.LicensePlateStatusRepository;
import com.br.park.mapper.LicensePlateStatusMapper;
import com.br.park.services.calculateprice.CalculatePriceDTO;
import com.br.park.services.calculateprice.CalculatePriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicensePlateStatusService {

    private final LicensePlateStatusRepository repository;
    private final CalculatePriceService calculatePriceService;

    public LicensePlateStatusResponseDTO findByLicensePlate(LicensePlateStatusRequestDTO dto) {

        log.info("Start searching status licensePlate:{}", dto.getLicensePlate());

        LicensePlateStatus licensePlateStatus = repository
                    .findLicensePlateStatus(dto.getLicensePlate())
                    .orElseThrow(LicensePlateStatusNotFoundErrorException::new);

        BigDecimal price = calculatePrice(licensePlateStatus);

        return LicensePlateStatusMapper.toResponse(licensePlateStatus, price);
    }

    private BigDecimal calculatePrice(LicensePlateStatus licensePlateStatus) {
        CalculatePriceDTO calculatePrice = build(licensePlateStatus);
        return calculatePriceService.calculatePrice(calculatePrice);
    }

    private CalculatePriceDTO build(LicensePlateStatus licensePlateStatus) {
        return CalculatePriceDTO.builder()
                .charge(licensePlateStatus.getCharge())
                .entryTime(licensePlateStatus.getEntryTime())
                .exitTime(licensePlateStatus.getTimeParked())
                .basePrice(licensePlateStatus.getBasePrice())
                .durationLimitMinutes(licensePlateStatus.getDuration())
                .typeChargeApply(licensePlateStatus.getTypeChargeApply())
                .build();
    }
}
