package com.alstom.codingtest.repository;

import com.alstom.codingtest.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}