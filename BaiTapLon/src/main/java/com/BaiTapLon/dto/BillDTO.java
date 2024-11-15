package com.BaiTapLon.dto;

import java.util.List;

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
    private List<Integer> ticketId;
    private int promotionId;
    private List<Integer> foodId;
    private int totalMoney;

}
