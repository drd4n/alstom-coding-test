package com.alstom.codingtest.entity;

import com.alstom.codingtest.entity.enums.StationContactInformationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "station_contact_informations")
@Getter
@Setter
public class StationContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StationContactInformationType type;

    @Column(nullable = false)
    private String information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", referencedColumnName = "id" , nullable = false)
    private Station station;
}
