package org.helmo.HolyD.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vacance")
public class Vacance {

    @Id
    @Column(name = "vacance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
