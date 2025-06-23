package com.br.park.infrastructure.repository.licensePlateStatus;

import com.br.park.error.LicensePlateStatusGenericErrorException;
import com.br.park.error.LicensePlateStatusNotFoundErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LicensePlateStatusRepository {

    private final JdbcTemplate jdbcTemplate;
    private final LicensePlateStatusQuery queries;

    public Optional<LicensePlateStatus> findLicensePlateStatus(String licensePlate) {

        try {

        log.info("Find license plate status by license plate: {}", licensePlate);
        String query = queries.select(licensePlate);
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(LicensePlateStatus.class)));

        } catch (EmptyResultDataAccessException e) {
            throw new LicensePlateStatusNotFoundErrorException();
        } catch (Exception e) {
            throw new LicensePlateStatusGenericErrorException(e);
        }
    }

}
