package com.br.park.infrastructure.repository.spot;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_spot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

    @Id
    @Column(name = "idt_spot")
    private Integer id;

    @Column(name = "cod_sector")
    private String sector;

    @Column(name = "num_lat")
    private String lat;

    @Column(name = "num_lng")
    private String lng;

    @Column(name = "bol_occupied")
    private Boolean occupied;
}
