package com.example.datn.repository;

import com.example.datn.dto.RateUserDTO;
import com.example.datn.entity.UserRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRateRepository extends JpaRepository<UserRate,Long> {
    Optional<UserRate> findByRateIdAndUserId(Long rateId,Long userId);

    @Query("select distinct new com.example.datn.dto.RateUserDTO(u.fullname,ur.rateUser,ur.comment) from Rate r, UserRate ur, User u where r.id = ur.rateId and ur.userId = u.id and r.id = ?1")
    Optional<List<RateUserDTO>> findByRateId(Long rateId);

    @Query(value ="select count(*) from user_rate where rate_id = ?1",nativeQuery = true)
    Integer countTotalComment(Long rateId);
}
