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
public class OrderData {
    
    int screeningId;           // ID suất chiếu
    List<String> selectedSeats; // Danh sách ghế đã chọn
    int promotionId;           // Mã khuyến mãi đã chọn (nếu có)
    List<FoodItem> foodItems;     // Danh sách món ăn đã chọn
    int quanityTicket; 

}
