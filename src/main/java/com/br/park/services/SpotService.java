package com.br.park.services;

import com.br.park.error.SpotGenericErrorException;
import com.br.park.error.SpotsNotAvailableException;
import com.br.park.infrastructure.repository.spot.Spot;
import com.br.park.infrastructure.repository.spot.SpotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotService {

    @Value("${spots.total}")
    private Integer totalSpots;

    private final SpotRepository spotRepository;

    public Optional<Spot> findNextAvailableSpot() {

        try {

            log.info("Searching for available spots");
            return spotRepository.findFirstByOccupied(false);

        }  catch (Exception exception) {
            throw new SpotGenericErrorException(exception);
        }
    }

    public void saveSpot(Spot spot) {
        try {
            log.info("Pesist data of table: {}", "spot");
            this.spotRepository.save(spot);
        } catch (Exception e) {
            throw new SpotGenericErrorException(e);
        }
    }

    public Integer countPercentAvailableSpots() {

        try {

            log.info("Calculate percentage available spots");
            return spotRepository.countByOccupied(false) / totalSpots;

        } catch (Exception exception) {
            throw new SpotsNotAvailableException(exception);
        }
    }

    public void updateSpotToOccupied(Spot spot) {

        try {

            log.info("Updating spot to occupied");
            spot.setOccupied(true);
            spotRepository.save(spot);

        } catch (Exception exception) {
            throw new SpotGenericErrorException(exception);
        }
    }

}
