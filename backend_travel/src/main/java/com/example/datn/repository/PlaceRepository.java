package com.example.datn.repository;

import com.example.datn.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {

    @Query("select distinct new com.example.datn.entity.Place(p.id,p.regionId,p.name) from Place p")
    Optional<List<Place>> placeList();
}
