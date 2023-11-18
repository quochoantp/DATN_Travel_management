package com.example.datn.repository;

import com.example.datn.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
    Optional<Rate> findByTourCode(String tourCode);
}
