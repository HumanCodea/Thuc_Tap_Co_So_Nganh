package com.BaiTapLon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.dto.BillDTO;
import com.BaiTapLon.dto.HistoryTicketDTO;
import com.BaiTapLon.model.Bill;
import com.BaiTapLon.model.Food;
import com.BaiTapLon.model.ScreeningMovie;
import com.BaiTapLon.service.BillFoodService;
import com.BaiTapLon.service.BillService;
import com.BaiTapLon.service.FoodService;
import com.BaiTapLon.service.PromotionService;
import com.BaiTapLon.service.ScreenMovieService;
import com.BaiTapLon.service.SeatService;
import com.BaiTapLon.service.TicketService;

@Controller
@RequestMapping(path = "")
public class HistoryBillController {

    @Autowired
    private CustormerService custormerService;

    @Autowired
    private BillService billService;

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private BillFoodService billFoodService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ScreenMovieService screenMovieService;

    @Autowired
    private FoodService foodService;
    
    @GetMapping("/historyBill/{id}")
    public String HistoryBill(@ModelAttribute("id") int id,Model model){

        List<Bill> bills = billService.getAllBills(id);

        List<BillDTO> billDTOs = new ArrayList<>();

        List<Integer> foodId = new ArrayList<>();

        for(Bill b : bills){

            BillDTO billDTO = new BillDTO();

            List<Integer> ticketId = ticketService.getTicketId(b.getBillId());

            if(billFoodService.getFoodId(b.getBillId()).isEmpty()){
                foodId = null;
            } else{
                foodId = billFoodService.getFoodId(b.getBillId());
            }

            billDTO.setBillId(b.getBillId());
            billDTO.setCustormerId(b.getCustormer().getCustormerId());

            if(b.getPromotion().getPromotionId() != 0){
                billDTO.setPromotionId(b.getPromotion().getPromotionId());
            } else {
                billDTO.setPromotionId(0);
            }

            billDTO.setBuyDate(b.getBuyDate());
            billDTO.setTicketId(ticketId);
            billDTO.setFoodId(foodId);
            billDTO.setTotalMoney(b.getTotalMoney());

            billDTOs.add(billDTO);
        }

        model.addAttribute("Bill", billDTOs);

        return "ManageHistoryTicket";
    }

    @GetMapping("/historyTicket")
    public String HistoryTicket(Model model){

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        int custormerId = getCustormerId();

        List<Bill> bills = billService.getAllBills(custormerId);

        int screeningId = 0;

        List<HistoryTicketDTO> historyTicketDTOs = new ArrayList<>();

        for(Bill b : bills){

            List<String> seats = new ArrayList<>();

            List<Integer> foodId = new ArrayList<>();

            List<String> foodName = new ArrayList<>();

            HistoryTicketDTO historyTicketDTO = new HistoryTicketDTO();

            List<Integer> ticketId = ticketService.getTicketId(b.getBillId());

            historyTicketDTO.setBillId(b.getBillId());
            historyTicketDTO.setBuyDate(b.getBuyDate());
            historyTicketDTO.setTicketId(ticketId);

            for(int t : ticketId){
                int seatId = ticketService.findSeatIdByTicketId(t);
                String nameSeat = seatService.findSeatNameBySeatId(seatId);
                screeningId = ticketService.findScreeningIdByTicketId(t);
                seats.add(nameSeat);
            }

            historyTicketDTO.setSeats(seats);

            ScreeningMovie screeningMovie =screenMovieService.findScreeningMovieById(screeningId);

            historyTicketDTO.setNameMovie(screeningMovie.getMovie().getNameMovie()); 

            if(billFoodService.getFoodId(b.getBillId()).isEmpty()){
                
                foodName.add("Không có đồ ăn");
                historyTicketDTO.setNameFood(foodName);

            } else{
                
                foodId = billFoodService.getFoodId(b.getBillId());

                for(int f : foodId){
                    Food food = foodService.findFoodById(f);
                    foodName.add(food.getNameFood());
                }

                historyTicketDTO.setNameFood(foodName);

            }

            if(b.getPromotion().getPromotionId() != 0){
                historyTicketDTO.setPromotionName(promotionService.findNamePromotionByPromotionId(b.getPromotion().getPromotionId()));
            } else {
                historyTicketDTO.setPromotionName("Không có KM");
            }

            historyTicketDTO.setTotalMoney(b.getTotalMoney());

            historyTicketDTOs.add(historyTicketDTO);
        }

        model.addAttribute("History", historyTicketDTOs);

        model.addAttribute("name", username);

    
        return "HistoryTicket";
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

    public String getUsername(){
        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            email = userDetails.getUsername();

        }

        return email;

    }

}
