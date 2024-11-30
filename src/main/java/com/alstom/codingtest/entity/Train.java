package com.alstom.codingtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Table(name = "trains")
@Entity
@Getter
@Setter
public class Train {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_station_id", referencedColumnName = "id" , nullable = false)
    private Station originStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_station_id", referencedColumnName = "id" , nullable = false)
    private Station destinationStation;

    @Column(nullable = false)
    private int seatCapacity;
}
