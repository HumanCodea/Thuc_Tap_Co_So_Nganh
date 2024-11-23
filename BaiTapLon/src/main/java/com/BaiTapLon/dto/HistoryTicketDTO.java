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
public class HistoryTicketDTO {
    
    private int billId;
    private String buyDate;
    private List<Integer> ticketId;
    private List<String> seats;
    private String nameMovie;
    private String promotionName;
    private List<String> nameFood;
    private int totalMoney;

}
