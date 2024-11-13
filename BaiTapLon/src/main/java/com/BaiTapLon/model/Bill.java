package com.BaiTapLon.model;

import java.util.List;

import com.BaiTapLon.auth.entities.Custormer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @Column(nullable = false)
    @NotBlank(message = "This buy date field can not be blank")
    private String buyDate;

    private int quantityTicket;

    private int totalMoney;

    @ManyToOne
    private Custormer custormer;

    @OneToMany(mappedBy = "bill")
    private List<Ticket> tickets;

    @ManyToOne
    private Promotion promotion;

    @OneToMany(mappedBy = "bill")
    private List<Bill_Food> bill_Foods;

}
