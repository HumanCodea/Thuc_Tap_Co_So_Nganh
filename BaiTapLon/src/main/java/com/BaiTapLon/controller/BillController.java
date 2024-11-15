package com.BaiTapLon.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.dto.FoodItem;
import com.BaiTapLon.dto.OrderData;
import com.BaiTapLon.model.Bill;
import com.BaiTapLon.model.Bill_Food;
import com.BaiTapLon.model.Food;
import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.model.ScreeningMovie;
import com.BaiTapLon.model.ScreeningRoom;
import com.BaiTapLon.model.Seats;
import com.BaiTapLon.model.Ticket;
import com.BaiTapLon.service.BillFoodService;
import com.BaiTapLon.service.BillService;
import com.BaiTapLon.service.FoodService;
import com.BaiTapLon.service.PromotionService;
import com.BaiTapLon.service.RoomService;
import com.BaiTapLon.service.ScreenMovieService;
import com.BaiTapLon.service.SeatService;
import com.BaiTapLon.service.TicketService;

@RestController
@RequestMapping()
public class BillController {
    
    @Autowired
    private BillService billService;

    @Autowired
    private CustormerService custormerService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ScreenMovieService screenMovieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BillFoodService billFoodService;

    @PostMapping("/saveBill")
    public void SaveBill(@RequestBody OrderData orderData){
        Bill bill = new Bill();
        int screeningId = orderData.getScreeningId();
        int promotionId = orderData.getPromotionId();
        int quanityTicket = orderData.getQuanityTicket();
        List<String> selectedSeats = orderData.getSelectedSeats();
        List<FoodItem> foodItems = orderData.getFoodItems();
        int custormerId = getCustormerId();
        int valueTicket = 99000;
        int totalMoney = 0;
        int totalFood = 0;
        int valueFood = 0;
        int pricePromotion = 0;
        int totalResult = 0;

        if (foodItems.isEmpty()) {
            System.out.println("Khach hang khong chon do an");
        } else{
            for(FoodItem f : foodItems){
                valueFood = foodService.getPriceFood(f.getFoodId());
                totalFood += f.getQuantity() * valueFood;
            }
        }

        if(promotionId == 0){
            System.out.println("Khach hang khong chon khuyen mai");
        } else {
            Promotion promotion = promotionService.findPromotionById(promotionId);
            pricePromotion = promotion.getPricePromotion();
            bill.setPromotion(promotion);
        }

        totalMoney = valueTicket * quanityTicket + totalFood;
        totalResult = totalMoney - totalMoney * (pricePromotion / 100);

        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTimeString = time.format(formatter);
        ScreeningMovie screeningMovie = screenMovieService.findScreeningMovieById(screeningId);
        ScreeningRoom screeningRoom = roomService.findRoomById(screeningMovie.getScreeningRoom().getRoomId());

        Custormer custormer = custormerService.findCustormerById(custormerId);
        
        bill.setBuyDate(dateTimeString);
        bill.setTotalMoney(totalResult);
        bill.setQuantityTicket(quanityTicket);
        bill.setCustormer(custormer);
        billService.StoreBill(bill);

        for(String s : selectedSeats){
            // luu seat
            Seats seats = new Seats();
            seats.setChairName(s);
            seats.setChair_status("ĐĐ");
            seats.setScreeningRoom(screeningRoom);
            seatService.saveSeat(seats);

            // luu ticket
            Ticket ticket = new Ticket();
            ticket.setValue(valueTicket);
            ticket.setBill(bill);
            ticket.setScreeningMovie(screeningMovie);
            ticket.setSeats(seats);
            ticketService.saveTicket(ticket);
        }

        if(!foodItems.isEmpty()){
            for(FoodItem f : foodItems){
                //luu bill food
                Food food = foodService.findFoodById(f.getFoodId());
                Bill_Food bill_Food = new Bill_Food();
                bill_Food.setQuantityFood(f.getQuantity());
                bill_Food.setFood(food);
                bill_Food.setBill(bill);
                billFoodService.saveBillFood(bill_Food);
            }
        }

    }

    public int getCustormerId(){

        String email = null;
        int custormerId;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            email = userDetails.getUsername();

        }

        custormerId = custormerService.findCustormerIdByUsername(email);

        return custormerId;
    }

}
