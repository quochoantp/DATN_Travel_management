package com.example.datn.service;

import com.example.datn.dto.DashboardDTO;
import com.example.datn.dto.DashboardFilterDTO;
import com.example.datn.entity.Orders;
import com.example.datn.repository.OrdersRepository;
import com.example.datn.repository.TourRepository;
import com.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private UserRepository userRepository;

    public DashboardDTO getDashboardDTOInfo(Integer year){
        DashboardDTO dashboardDTO = new DashboardDTO();
        List<Integer> monthList = new ArrayList<>();
        List<Integer> quaterList = new ArrayList<>();
        String month1 = "01/01/" + year;
        String month2 = "01/02/" + year;
        String month3 = "01/03/" + year;
        String month4 = "01/04/" + year;
        String month5 = "01/05/" + year;
        String month6 = "01/06/" + year;
        String month7 = "01/07/" + year;
        String month8 = "01/08/" + year;
        String month9 = "01/09/" + year;
        String month10 = "01/10/" + year;
        String month11 = "01/11/" + year;
        String month12 = "01/12/" + year;
        String month13 = "01/01/" + (year+1);
        Integer orders1 = 0;
        if(ordersRepository.sumPriceByDay(month1,month2).isPresent()){
            orders1 = ordersRepository.sumPriceByDay(month1,month2).get();
        }
        Integer orders2 = 0;
        if(ordersRepository.sumPriceByDay(month2,month3).isPresent()){
            orders2 = ordersRepository.sumPriceByDay(month2,month3).get();
        }
        Integer orders3 = 0;
        if(ordersRepository.sumPriceByDay(month3,month4).isPresent()){
            orders3= ordersRepository.sumPriceByDay(month3,month4).get();
        }
        Integer orders4 = 0;
        if(ordersRepository.sumPriceByDay(month4,month5).isPresent()){
            orders4 = ordersRepository.sumPriceByDay(month4,month5).get();
        }
        Integer orders5 = 0;
        if(ordersRepository.sumPriceByDay(month5,month6).isPresent()){
            orders5 = ordersRepository.sumPriceByDay(month5,month6).get();
        }
        Integer orders6 = 0;
        if(ordersRepository.sumPriceByDay(month6,month7).isPresent()){
            orders6 = ordersRepository.sumPriceByDay(month6,month7).get();
        }
        Integer orders7 = 0;
        if(ordersRepository.sumPriceByDay(month7,month8).isPresent()){
            orders7 = ordersRepository.sumPriceByDay(month7,month8).get();
        }
        Integer orders8 = 0;
        if(ordersRepository.sumPriceByDay(month8,month9).isPresent()){
            orders8 = ordersRepository.sumPriceByDay(month8,month9).get();
        }
        Integer orders9 = 0;
        if(ordersRepository.sumPriceByDay(month9,month10).isPresent()){
            orders9 = ordersRepository.sumPriceByDay(month9,month10).get();
        }
        Integer orders10 = 0;
        if(ordersRepository.sumPriceByDay(month10,month11).isPresent()){
            orders10 = ordersRepository.sumPriceByDay(month10,month11).get();
        }
        Integer orders11 = 0;
        if(ordersRepository.sumPriceByDay(month11,month12).isPresent()){
            orders11 = ordersRepository.sumPriceByDay(month11,month12).get();
        }
        Integer orders12 = 0;
        if(ordersRepository.sumPriceByDay(month12,month13).isPresent()){
            orders12 = ordersRepository.sumPriceByDay(month12,month13).get();
        }
        monthList.add(orders1);
        monthList.add(orders2);
        monthList.add(orders3);
        monthList.add(orders4);
        monthList.add(orders5);
        monthList.add(orders6);
        monthList.add(orders7);
        monthList.add(orders8);
        monthList.add(orders9);
        monthList.add(orders10);
        monthList.add(orders11);
        monthList.add(orders12);

        quaterList.add(orders1+orders2+orders3);
        quaterList.add(orders4+orders5+orders6);
        quaterList.add(orders7+orders8+orders9);
        quaterList.add(orders10+orders11+orders12);

        dashboardDTO.setMonthlyRevenueList(monthList);
        dashboardDTO.setQuaterlyRevenueList(quaterList);

        dashboardDTO.setTotalTour(tourRepository.getTotalTour());
        dashboardDTO.setTotalMember(userRepository.getTotalMember());
     return  dashboardDTO;
    }

    public DashboardFilterDTO getFilterDashboard(Integer month, Integer year){
       DashboardFilterDTO dashboardFilterDTO = new DashboardFilterDTO();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
       List<Integer> countStatusOrderList = new ArrayList<>();
        List<String> dayList = new ArrayList<>();
        List<Integer> countOfDayList = new ArrayList<>();
       Optional<List<Orders>> optionalOrdersList = ordersRepository.getTotalOrderInMonth(month,year);
        Integer count0 = 0;
        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;
        Integer count4 = 0;
        Integer count5 = 0;
       if(optionalOrdersList.isPresent()){
          for(Orders orders : optionalOrdersList.get()){
              if(orders.getStatus().equals("0")){
                  count0++;
              }
              if(orders.getStatus().equals("1")){
                  count1++;
              }
              if(orders.getStatus().equals("2")){
                  count2++;
              }
              if(orders.getStatus().equals("3")){
                  count3++;
              }
              if(orders.getStatus().equals("4")){
                  count4++;
              }
              if(orders.getStatus().equals("5")){
                  count5++;
              }
          }
       }
        countStatusOrderList.add(count0);
        countStatusOrderList.add(count1);
        countStatusOrderList.add(count2);
        countStatusOrderList.add(count3);
        countStatusOrderList.add(count4);
        countStatusOrderList.add(count5);

        Optional<List<Tuple>> tupleList = ordersRepository.getDataDayTuple(month,year);
        if(tupleList.isPresent()){
            for(Tuple tuple : tupleList.get()){
                Date date = tuple.get("created_date", Date.class);
                String dateString = simpleDateFormat.format(date);
                BigInteger totalOrder = tuple.get("total_order", BigInteger.class);
                dayList.add(dateString);
                countOfDayList.add(totalOrder.intValue());
            }
        }
        dashboardFilterDTO.setStatusOrderCount(countStatusOrderList);
        dashboardFilterDTO.setDayList(dayList);
        dashboardFilterDTO.setCountOfDay(countOfDayList);
       return dashboardFilterDTO;
    }
}
