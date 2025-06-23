package com.br.park.infrastructure.repository.parklot;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_park_lot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idt_park_lot")
    private Long id;

    @Column(name = "des_license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "dtt_parked_time")
    private LocalDateTime parkedTime;

    @Column(name = "dtt_exit_time")
    private LocalDateTime exitTime;

    @Column(name = "cod_spot")
    private Integer spotId;

    @Column(name = "num_total_minutes")
    private Integer totalMinutes;

    @Column(name = "num_amount_charge")
    private BigDecimal amountCharge;

    @Enumerated(EnumType.STRING)
    @Column(name = "des_type_charge_apply")
    private TypeChargeApplyEnum typeChargeApply;

    @Column(name = "num_final_price")
    private BigDecimal finalPrice;

    @Column(name = "num_base_price")
    private BigDecimal basePrice;

    @Column(name = "num_duration_limit_minutes")
    private Integer durationLimitMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "des_status")
    private ParkLotStatusEnum status;
}
