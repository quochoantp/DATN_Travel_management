package com.example.datn.repository;

import com.example.datn.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage,Long> {
    Optional<TourImage> findByTourId(Long id);

    @Modifying
    @Transactional
    @Query(value = "update tour_image set image100 = ?2, image50 = ?3, image251 = ?4, image252 = ?5 where tour_id = ?1 ",nativeQuery = true)
    void updateTourImageByTourId(Long id,String image100,String image50,String image251,String image252);
}
