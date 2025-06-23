package com.br.park.services;

import com.br.park.error.SectorGenericErrorException;
import com.br.park.error.SectorNotFoundErrorException;
import com.br.park.infrastructure.repository.sector.Sector;
import com.br.park.infrastructure.repository.sector.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;

    public void saveSector(Sector sector) {
        try {
            log.info("Start persitencce data table:{}", "sector");
            this.repository.save(sector);
        } catch (Exception e) {
            throw new SectorGenericErrorException(e);
        }
    }

    public Optional<Sector> findSectorById(String id) {
        try {
            log.info("Find sector data codSector:{}", id);
            return repository.findById(id);
        }  catch (Exception e) {
            throw new SectorGenericErrorException(e);
        }
    }

}
