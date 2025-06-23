package com.br.park.services.webhook;


import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.error.ParkLotNotFountWithActiveStatusByLicensePlateException;
import com.br.park.error.SectorNotFoundErrorException;
import com.br.park.error.SpotsNotAvailableException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.sector.Sector;
import com.br.park.infrastructure.repository.spot.Spot;
import com.br.park.services.ParkLotService;
import com.br.park.services.SectorService;
import com.br.park.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum.ACTIVE;

@Service
@RequiredArgsConstructor
public class EntrySpotService implements WebhookService {

    private final ParkLotService service;
    private final SpotService spotService;
    private final SectorService sectorService;

    @Override
    public void process(ParkLotRequestDTO paklot) {

        Spot spot = findNextAvailableSpot();
        Sector sector = findSectorById(spot.getSector());
        ParkLot parkLot = findByPlateAndStatusActive(paklot.getLicensePlate());

        parkLot.setSpotId(spot.getId());
        parkLot.setBasePrice(sector.getBasePrice());
        parkLot.setDurationLimitMinutes(sector.getDurationLimitMinutes());

        service.save(parkLot);
        spotService.updateSpotToOccupied(spot);
    }

    private Spot findNextAvailableSpot() {
        return spotService.findNextAvailableSpot().orElseThrow(SpotsNotAvailableException::new);
    }

    private Sector findSectorById(String id) {
        return sectorService.findSectorById(id).orElseThrow(SectorNotFoundErrorException::new);
    }

    private ParkLot findByPlateAndStatusActive(String plate) {
        return service.findByPlateAndStatus(plate, ACTIVE).orElseThrow(ParkLotNotFountWithActiveStatusByLicensePlateException::new);
    }
}