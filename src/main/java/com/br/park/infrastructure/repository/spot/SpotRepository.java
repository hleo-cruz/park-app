package com.br.park.infrastructure.repository.spot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, Integer> {

    public Optional<Spot> findFirstByOccupied(Boolean occupied);
    public Integer countByOccupied(Boolean occupied);
    public List<Spot> findBySector(String sector);
}
