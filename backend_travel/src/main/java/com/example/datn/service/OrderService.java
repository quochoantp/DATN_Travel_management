package com.example.datn.service;

import com.example.datn.dto.*;
import com.example.datn.entity.Orders;
import com.example.datn.entity.TouristOfOrders;
import com.example.datn.repository.OrdersRepository;
import com.example.datn.repository.TouristOfOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private TouristOfOrderRepository touristOfOrderRepository;

    @Autowired
    private SendMailService sendMailService;

    public List<Orders> getAllOrders(String fromDate, String toDate) throws ParseException {
        List<Orders> ordersList = new ArrayList<>();
        if(fromDate == null || fromDate.equals("null")){
            fromDate = "0/1/1";
        }
        Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(fromDate);
        if(toDate == null || toDate.equals("null")){
            toDate = "9999/1/1";
        }
        Date date2 =new SimpleDateFormat("yyyy/MM/dd").parse(toDate);
        if(ordersRepository.listAllOrders(date1,date2).isPresent()){
            return ordersRepository.listAllOrders(date1,date2).get();
        }
        return ordersList;
    }

    public Orders bookTour (ResponseOrderDTO order) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Orders orders = new Orders();
        if(order.getOrderInfoDTO().getUserId() != null){
            orders.setUserId(Long.valueOf(order.getOrderInfoDTO().getUserId()));
        }
        orders.setFullname(order.getOrderInfoDTO().getFullname());
        orders.setEmail(order.getOrderInfoDTO().getEmail());
        orders.setPhoneNumber(order.getOrderInfoDTO().getPhoneNumber());
        orders.setAddress(order.getOrderInfoDTO().getAddress());
        orders.setAdultCount(Integer.valueOf(order.getOrderInfoDTO().getAdultCount()));
        orders.setChildrenCount(Integer.valueOf(order.getOrderInfoDTO().getChildrenCount()));
        orders.setKidCount(Integer.valueOf(order.getOrderInfoDTO().getKidCount()));
        orders.setCreatedDate(new Date());
        if(order.getIsPaymentOnline().equals("false")){
            orders.setStatus("0");
        } else {
            orders.setStatus("1");
        }
        orders.setPriceId(order.getOrderInfoDTO().getPriceId());
        orders.setSumPrice(order.getOrderInfoDTO().getSumPrice().intValue());

        Orders ordersSave = ordersRepository.save(orders);

        if(!order.getTouristDTOList().isEmpty()){
            for (TouristDTO touristDTO : order.getTouristDTOList()){
                TouristOfOrders touristOfOrders = new TouristOfOrders();
                touristOfOrders.setFullname(touristDTO.getFullName());
                touristOfOrders.setGender(touristDTO.getGender());
                touristOfOrders.setDob(formatter.parse(touristDTO.getDob()));
                touristOfOrders.setOrderId(ordersSave.getId());
                touristOfOrderRepository.saveAndFlush(touristOfOrders);
            }

        }
        return ordersSave;
    }

    public void updateStatusTransaction(Long id,String orderCode) throws MessagingException {
        ordersRepository.updateStatus(id,"2",orderCode);
        Optional<OrderTransactionMailDTO> optionalOrderSendMail = ordersRepository.getOrderSendMail(id);
        String email = null;
        String subject = null;
        String htmlContent = null;
        if(optionalOrderSendMail.isPresent()){
            email = optionalOrderSendMail.get().getEmail();
            subject = "Lửa Việt - Xác nhận đăng ký thành công " + optionalOrderSendMail.get().getNameTour();
            htmlContent = "<h4>Kính gửi a/c " + optionalOrderSendMail.get().getFullname()+ "!</h4>";
            htmlContent += "<div>Thay mặt Lửa Việt, chúng tôi gửi đến a/c lời chúc sức khỏe và lời cảm ơn chân thành vì đã quan tâm sử dụng dịch vụ của chúng tôi. Lửa Việt xác nhận a/c đã đăng ký tour thành công. Sau khi nhân viên xác nhận thanh toán sẽ gửi hợp đồng cho quý khách. </div>";
            htmlContent += "<h5>Chi tiết đặt tour</h5>";
            htmlContent += "<table style = \"border:1px solid black;border-collapse:collapse; margin: 10px\">\n" +
                    "    <tr style=\"border:1px solid black;\">\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">Mã tour</td>\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">";
            htmlContent += optionalOrderSendMail.get().getNameTour();
            htmlContent += "</td>\n" +
                    "</tr>";
            htmlContent += "<tr style=\"border:1px solid black;\">\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">Tổng tiền thanh toán </td>\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">";
            htmlContent += optionalOrderSendMail.get().getSumPrice();
            htmlContent += "</td>\n" +
                    "</tr>";
            htmlContent += "<tr style=\"border:1px solid black;\">\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">Hình thức thanh toán </td>\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">";
            htmlContent += "Thanh toán online qua VNPay</td>\n" +
                    "</tr>";
            htmlContent += "<tr style=\"border:1px solid black;\">\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">Tình trạng</td>\n" +
                    "    <td style=\"border:1px solid black; padding:10px\">";
            htmlContent += "Đã thanh toán</td>\n" +
                    "</tr></table>";
            htmlContent += "<div> Cảm ơn quý khách đã sử dụng dịch vụ của công ty chúng tôi</div>\n" +
                    " <div> Mọi yêu cầu hay thắc mắc cần giải đáp xin vui lòng liên hệ tới hotline : 1900.6420</div>\n" +
                    " <br>\n" +
                    "\n" +
                    " <h4>Lửa Việt Tour</h4>";
            sendMailService.sendMail(email,subject,htmlContent);
        }
    }

    public ContractInfoDTO sendContract(Long orderId){
        Optional<ContractInfoDTO> contractInfoDTOOpt = ordersRepository.getInfoOrderToSendContract(orderId);
        if(contractInfoDTOOpt.isPresent()){
            Optional<List<TouristOfOrders>> touristOfOrdersOtp = touristOfOrderRepository.findByOrOrderId(orderId);
            if(touristOfOrdersOtp.isPresent()){
                contractInfoDTOOpt.get().setTouristDTOList(touristOfOrdersOtp.get());
            }
        }
        return contractInfoDTOOpt.get();
    }

    public List<OrdersHistoryDTO> getHistoryOrders(Long userId){
        List<OrdersHistoryDTO> arrList = new ArrayList<>();
        if(ordersRepository.getOrderHistoryByPriceId(userId).isPresent()){
            return ordersRepository.getOrderHistoryByPriceId(userId).get();
        }
        return arrList;
    }
}
