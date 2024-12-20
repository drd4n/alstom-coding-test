package com.alstom.codingtest.repository;

import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Train;
import com.alstom.codingtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
