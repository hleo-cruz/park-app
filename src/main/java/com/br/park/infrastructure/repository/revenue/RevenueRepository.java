package com.br.park.infrastructure.repository.revenue;

import com.br.park.error.RevenueGenericErrorException;
import com.br.park.error.RevenueNotFoundErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RevenueRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RevenueQuery queries;

    public List<Revenue> findByDateAndSector(String codSector, LocalDate date) {

        try {

            log.info("Search Revenues  date:{}, sector:{}", date, codSector);
            String query = queries.select(codSector, date);
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Revenue.class));

        } catch (EmptyResultDataAccessException e) {
            throw new RevenueNotFoundErrorException();
        } catch (Exception e) {
            throw new RevenueGenericErrorException(e);
        }
    }

}
