package com.alstom.codingtest.repository;

import com.alstom.codingtest.entity.StationContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationContactInformationRepository extends JpaRepository<StationContactInformation, Long> {
    void deleteAllByStationId(long stationId);
}
