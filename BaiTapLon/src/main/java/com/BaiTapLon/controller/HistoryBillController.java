package com.BaiTapLon.controller;

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

        for(Bill b : bills){

            BillDTO billDTO = new BillDTO();

            List<Integer> ticketId = ticketService.getTicketId(b.getBillId());

            List<Integer> foodId = billFoodService.getFoodId(b.getBillId());

            billDTO.setBillId(b.getBillId());
            billDTO.setCustormerId(b.getCustormer().getCustormerId());
            billDTO.setPromotionId(b.getPromotion().getPromotionId());
            billDTO.setBuyDate(b.getBuyDate());
            billDTO.setTicketId(ticketId);
            billDTO.setFoodId(foodId);
            billDTO.setTotalMoney(b.getTotalMoney());

            billDTOs.add(billDTO);
        }

        model.addAttribute("Bill", billDTOs);

        return "ManageHistoryTicket";
    }

}
