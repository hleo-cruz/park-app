package com.br.park.services;

import com.br.park.error.ParkLotNotFountWithActiveStatusByLicensePlateException;
import com.br.park.error.ParkLotGenericErrorException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.parklot.ParkLotRepository;
import com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkLotService {

    private final ParkLotRepository repository;
    private final SpotService spotService;

    public ParkLot save(ParkLot parkLot) {

        try {
            log.info("Saving or updating a new data of a parking");
            return repository.save(parkLot);
        } catch (Exception e) {
            throw new ParkLotGenericErrorException(e);
        }
    }

    public Optional<ParkLot> findByPlateAndStatus(String plate, ParkLotStatusEnum status) {

        try {

            log.info("Finding a parking plate:{}, status{} ", plate, status);

            return repository.findByLicensePlateAndStatus(plate, status);

        } catch (Exception exception) {
            throw new ParkLotGenericErrorException(exception);
        }
    }
}
