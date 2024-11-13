package com.BaiTapLon.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.dto.BillDTO;
import com.BaiTapLon.model.Bill;
import com.BaiTapLon.service.BillFoodService;
import com.BaiTapLon.service.BillService;
import com.BaiTapLon.service.TicketService;

@Controller
@RequestMapping(path = "")
public class HistoryBillController {

     @Autowired
    private BillService billService;

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private BillFoodService billFoodService;
    
    @GetMapping("/historyBill/{id}")
    public String HistoryBill(@ModelAttribute("id") int id,Model model){

        List<Bill> bills = billService.getAllBills(id);

        List<BillDTO> billDTOs = new ArrayList<>();

        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTimeString = time.format(formatter);

        for(Bill b : bills){

            BillDTO billDTO = new BillDTO();

            int ticketId = ticketService.getTicketId(b.getBillId());

            int foodId = billFoodService.getFoodId(b.getBillId());

            int quantityFood = billFoodService.getQualityFood(b.getBillId());

            billDTO.setBillId(b.getBillId());
            billDTO.setCustormerId(b.getCustormer().getCustormerId());
            billDTO.setPromotionId(b.getPromotion().getPromotionId());
            billDTO.setBuyDate(dateTimeString);
            billDTO.setTicketId(ticketId);
            billDTO.setQuantityFood(quantityFood);
            billDTO.setFoodId(foodId);
            billDTO.setTotalMoney(b.getTotalMoney());

            billDTOs.add(billDTO);
        }

        model.addAttribute("Bill", billDTOs);

        return "ManageHistoryTicket";
    }

}
