package com.alstom.codingtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "tickets")
@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String passengerName;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private LocalDate departureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "id" , nullable = false)
    private Train train;
}
