package com.BaiTapLon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BillDTO {
    
    private int billId;
    private String buyDate;
    private int custormerId;
    private int ticketId;
    private int promotionId;
    private int foodId;
    private int quantityFood;
    private int totalMoney;

}
