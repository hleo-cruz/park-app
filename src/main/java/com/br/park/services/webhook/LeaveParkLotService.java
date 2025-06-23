package com.br.park.services.webhook;


import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.error.MandatoryParamsNotInformedException;
import com.br.park.error.ParkLotNotFountWithActiveStatusByLicensePlateException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.services.ParkLotService;
import com.br.park.services.calculateprice.CalculatePriceDTO;
import com.br.park.services.calculateprice.CalculatePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum.EXITED;
import static com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum.PARKED;

@Service
@RequiredArgsConstructor
public class LeaveParkLotService implements WebhookService{

    private final ParkLotService service;
    private final CalculatePriceService calculatePriceService;

    @Override
    public void process(ParkLotRequestDTO paklot) {

        ParkLot parkLot = findByPlateAndStatusParked(paklot.getLicensePlate());

        parkLot.setStatus(EXITED);
        parkLot.setExitTime(parkLot.getExitTime());
        parkLot.setTotalMinutes(calculateParkingTime(paklot));
        CalculatePriceDTO calculatePrice = build(parkLot);

        calculatePriceService.calculatePrice(calculatePrice);

        service.save(parkLot);
    }

    public Integer calculateParkingTime(ParkLotRequestDTO parkLot) {

        if (parkLot.getEntryTime() == null || parkLot.getExitTime() == null) {
            throw new MandatoryParamsNotInformedException("Mandatory params not informed param: exitTime");
        }

        return (int) Duration.between(parkLot.getEntryTime(), parkLot.getExitTime()).toMinutes();
    }

    private CalculatePriceDTO build(ParkLot parkLot) {
        return CalculatePriceDTO.builder()
                .entryTime(parkLot.getParkedTime())
                .exitTime(parkLot.getExitTime())
                .basePrice(parkLot.getBasePrice())
                .durationLimitMinutes(parkLot.getDurationLimitMinutes())
                .typeChargeApply(parkLot.getTypeChargeApply())
                .build();
    }

    private ParkLot findByPlateAndStatusParked(String plate) {
        return service.findByPlateAndStatus(plate, PARKED).orElseThrow(ParkLotNotFountWithActiveStatusByLicensePlateException::new);
    }
}