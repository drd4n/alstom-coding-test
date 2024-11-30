package com.alstom.codingtest.repository;

import com.alstom.codingtest.entity.Ticket;
import com.alstom.codingtest.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByTrainAndDepartureDate(Train train, LocalDate departureDate);
}
