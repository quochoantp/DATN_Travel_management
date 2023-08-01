package com.example.datn.repository;

import com.example.datn.entity.TouristOfOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TouristOfOrderRepository extends JpaRepository<TouristOfOrders,Long> {

    Optional<List<TouristOfOrders>> findByOrOrderId(Long orderId);
}
