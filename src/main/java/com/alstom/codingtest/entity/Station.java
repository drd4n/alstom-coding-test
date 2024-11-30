package com.alstom.codingtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stations")
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StationContactInformation> contactInformations;


}
