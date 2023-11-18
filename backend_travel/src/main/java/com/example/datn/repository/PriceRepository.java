package com.example.datn.repository;

import com.example.datn.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {
   @Query("select distinct new com.example.datn.entity.Price(p.id,p.tourId,p.tourPriceId,p.discountId) from Price p, Tour t where t.id = p.tourId and t.id = ?1")
    public Optional<Price> getPriceById(Long id);

    @Query(value = "SELECT MAX(ID) FROM price",nativeQuery = true)
    public Long getMaxId();

    @Transactional
    @Modifying
    @Query(value = "update price set discount_id = ?2 where discount_id = ?1",nativeQuery = true)
    void updateDiscountForTour(Long discountIdOld,Long discountId);
}
