package com.br.park.infrastructure.repository.parklot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkLotRepository extends JpaRepository<ParkLot, Long> {

    public Optional<ParkLot> findByLicensePlateAndStatus(String licensePlate, ParkLotStatusEnum status);
}
