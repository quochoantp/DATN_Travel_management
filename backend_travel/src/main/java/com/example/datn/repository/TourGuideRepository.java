package com.example.datn.repository;

import com.example.datn.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide,Long> {

    @Transactional
    void deleteByTourId(Long tourId);

    Optional<TourGuide> findByTourId(Long tourId);
}
