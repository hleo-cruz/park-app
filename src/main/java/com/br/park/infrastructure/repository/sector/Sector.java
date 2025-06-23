package com.br.park.infrastructure.repository.sector;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "tb_sector")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sector {

    @Id
    @Column(name = "idt_sector", length = 1)
    private String sector;

    @Column(name = "num_base_price")
    private BigDecimal basePrice;

    @Column(name = "num_max_capacity")
    private Integer maxCapacity;

    @Column(name = "hor_open_hour")
    private LocalTime openHour;

    @Column(name = "hor_close_hour")
    private LocalTime closeHour;

    @Column(name = "num_duration_limit_minutes")
    private Integer durationLimitMinutes;
}