package com.alstom.codingtest.repository;

import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {

    @Query(value = "SELECT max(t.id) FROM trains t WHERE t.origin_station_id = :originStationId AND t.destination_station_id = :destinationStationId", nativeQuery = true)
    String countByOriginAndDestination(long originStationId, long destinationStationId);
}
