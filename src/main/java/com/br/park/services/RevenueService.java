package com.br.park.services;

import com.br.park.api.dto.request.RevenueRequestDTO;
import com.br.park.api.dto.response.RevenueResponseDTO;
import com.br.park.error.RevenueNotFoundErrorException;
import com.br.park.infrastructure.repository.revenue.Revenue;
import com.br.park.infrastructure.repository.revenue.RevenueRepository;
import com.br.park.infrastructure.repository.spotStatus.SpotStatus;
import com.br.park.mapper.RevenueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueRepository repository;

    public RevenueResponseDTO findByDateAndSector(RevenueRequestDTO dto) {

        log.info("Start searching revenue date:{}, sector:{}", dto.getDate(), dto.getSector());

        List<Revenue> revenues = findRevenues(dto);

        BigDecimal finalPrice = revenues
                .stream().map(Revenue::getFinalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return RevenueMapper.toResponse(finalPrice, dto.getDate());

    }

    private List<Revenue> findRevenues(RevenueRequestDTO dto) {

        LocalDate date = dto.getDate();
        String codSector = dto.getSector();

        List<Revenue> revenues = repository.findByDateAndSector(codSector, date);

        if (revenues == null || revenues.isEmpty()) {
            throw new RevenueNotFoundErrorException();
        }

        return revenues;
    }

}
