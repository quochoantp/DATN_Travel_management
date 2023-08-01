package com.example.datn.controller;

import com.example.datn.dto.OrdersHistoryDTO;
import com.example.datn.dto.ResponseOrderDTO;
import com.example.datn.entity.Orders;
import com.example.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders-all")
    public List<Orders> getAllOrders(@RequestParam(name = "fromDate" , required = false) String fromDate,
                                     @RequestParam(name = "toDate", required = false) String toDate) throws ParseException {
        return orderService.getAllOrders(fromDate,toDate);
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> bookTour(@RequestBody ResponseOrderDTO order) throws ParseException {
        return new ResponseEntity<>(orderService.bookTour(order), HttpStatus.CREATED);
    }

    @GetMapping("/transaction")
    public void bookTour(HttpServletRequest req, HttpServletResponse res) throws ParseException, IOException, MessagingException {
        Long id = Long.valueOf(req.getParameter("vnp_OrderInfo"));
        orderService.updateStatusTransaction(id,req.getParameter("vnp_TransactionNo"));
        res.sendRedirect("http://localhost:3000");
    }

    @GetMapping("/order-history/{userId}")
    public List<OrdersHistoryDTO> getHistoryOrder(@PathVariable Long userId){
        return orderService.getHistoryOrders(userId);
    }

    @GetMapping("/order/send-contract/{orderId}")
    public void sendContractOrder(@PathVariable Long orderId){
         orderService.sendContract(orderId);
    }
}
