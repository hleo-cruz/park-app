package com.br.park.infrastructure.repository.spotStatus;

import com.br.park.error.SpotStatusGenericErrorException;
import com.br.park.error.SpotStatusNotFoundErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SpotStatusRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SpotStatusQuery queries;

    public SpotStatus findByLatAndLng(String lat, String lng) {
        try {
            log.info("Find sector status by lat: {}, lng: {}", lat, lng);
            String query = queries.select(lat, lng);
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(SpotStatus.class));
        } catch (EmptyResultDataAccessException e) {
            throw new SpotStatusNotFoundErrorException();
        } catch (Exception e) {
            throw new SpotStatusGenericErrorException(e);
        }
    }

}
