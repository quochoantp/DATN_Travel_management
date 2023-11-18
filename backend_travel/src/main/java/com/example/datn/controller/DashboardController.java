package com.example.datn.controller;

import com.example.datn.dto.DashboardDTO;
import com.example.datn.dto.DashboardFilterDTO;
import com.example.datn.entity.Orders;
import com.example.datn.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/get-data/{year}")
    public DashboardDTO getAllOrders(@PathVariable Integer year)  {
       return dashboardService.getDashboardDTOInfo(year);
    }

    @GetMapping("/get-data-filter")
    public DashboardFilterDTO getAllOrders(@RequestParam("month") Integer month, @RequestParam("year") Integer year)  {
        return dashboardService.getFilterDashboard(month, year);
    }
}
