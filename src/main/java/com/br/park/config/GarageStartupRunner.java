package com.br.park.config;

import com.br.park.infrastructure.client.GarageApiClient;
import com.br.park.infrastructure.client.GarageDTO;
import com.br.park.mapper.GarageMapper;
import com.br.park.services.SectorService;
import com.br.park.services.SpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GarageStartupRunner implements ApplicationRunner {

    private final GarageApiClient garageClient;
    private final SectorService sectorService;
    private final SpotService spotService;

    @Override
    public void run(ApplicationArguments args) {

        GarageDTO response = garageClient.findGarage();

        log.info("Persistindo setores...");
        response.getGarage().forEach(dto ->
                sectorService.saveSector(GarageMapper.toSector(dto)));

        log.info("Persistindo vagas...");
        response.getSpots().forEach(dto ->
                spotService.saveSpot(GarageMapper.toSpot(dto)));

        log.info("Dados da garagem carregados e persistidos com sucesso.");

    }
}
