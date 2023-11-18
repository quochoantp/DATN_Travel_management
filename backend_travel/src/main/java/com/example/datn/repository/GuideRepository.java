package com.example.datn.repository;

import com.example.datn.dto.GuideSelectBoxDTO;
import com.example.datn.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide,Long> {

    @Query("select distinct new com.example.datn.entity.Guide(g.id,g.name,g.phone,g.identityCard,g.address,g.status,g.createdDate,g.createdBy,g.updatedDate,g.updatedBy) from Guide g where ((lower(g.name) like lower(concat('%',?1,'%')))or(?1 = '' )) and ((lower(g.phone) like lower(concat('%',?2,'%')))or(?2 = '' ))")
    public Optional<List<Guide>> listGuide(String name, String phone);

    @Query(value = "SELECT MAX(ID) FROM guide",nativeQuery = true)
    public Integer getMaxId();

    @Query("select distinct new com.example.datn.dto.GuideSelectBoxDTO(g.id,g.name,g.phone,g.address) from Guide g where g.status = 1")
    public Optional<List<GuideSelectBoxDTO>> getListGuideSelectbox();

    @Query(value = "select guide_id  from tour_guide where tour_id = ?1",nativeQuery = true)
    public Optional<List<Long>> getListGuideByTourId(Long tourId);
}
