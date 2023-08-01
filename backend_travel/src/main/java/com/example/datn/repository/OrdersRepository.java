package com.example.datn.repository;

import com.example.datn.dto.ContractInfoDTO;
import com.example.datn.dto.OrderTransactionMailDTO;
import com.example.datn.dto.OrdersHistoryDTO;
import com.example.datn.entity.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query("select distinct new com.example.datn.entity.Orders(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.address,o.adultCount,o.childrenCount,o.kidCount,o.createdDate,o.updatedDate,o.status,o.priceId,o.sumPrice,o.orderCode,o.identityCard) from Orders o where o.priceId = ?1")
    public Optional<List<Orders>> listOrdersByPriceId(Long id);

    @Query("select distinct new com.example.datn.entity.Orders(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.address,o.adultCount,o.childrenCount,o.kidCount,o.createdDate,o.updatedDate,o.status,o.priceId,o.sumPrice,o.orderCode,o.identityCard) from Orders o where ((o.createdDate >= ?1) or(?1 = '')) and ((o.createdDate <= ?2) or(?2 = ''))")
    public Optional<List<Orders>> listAllOrders(Date fromDate, Date toDate);

    @Query("select distinct new com.example.datn.dto.OrdersHistoryDTO(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.address,o.adultCount,o.childrenCount,o.kidCount,o.createdDate,o.status,o.priceId,o.sumPrice,t.name) from Orders o, Price p, Tour t where o.priceId = p.id and p.tourId = t.id and o.userId = ?1")
    public Optional<List<OrdersHistoryDTO>> getOrderHistoryByPriceId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders o set o.status = ?2, o.order_code = ?3 where o.id = ?1",nativeQuery = true)
    public void updateStatus(Long id, String status, String orderCode);


    @Query("select distinct new com.example.datn.dto.OrderTransactionMailDTO(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.sumPrice,t.name) from Orders o, Price p, Tour t where o.priceId = p.id and p.tourId = t.id and o.id = ?1")
    public Optional<OrderTransactionMailDTO> getOrderSendMail(Long id);

    @Query("select distinct new com.example.datn.dto.ContractInfoDTO(o.fullname,o.email,o.phoneNumber,o.address,o.identityCard,o.adultCount,o.childrenCount,o.kidCount,o.sumPrice,o.orderCode,t.name,t.startTime,t.period,tp.adult,tp.children,tp.kid) from Orders o, Price p, Tour t, TourPrice tp where o.priceId = p.id and p.tourId = t.id and tp.id = p.tourPriceId and o.id = ?1")
    public Optional<ContractInfoDTO> getInfoOrderToSendContract(Long id);


}
