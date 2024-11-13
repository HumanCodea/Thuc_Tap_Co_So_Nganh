package com.BaiTapLon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @NotBlank(message = "This ticket status field can not be blank")
    private String ticketStatus;

    private int value;

    @ManyToOne
    private ScreeningMovie screeningMovie;

    @ManyToOne
    private Seats seats;

    @ManyToOne
    private Bill bill;
}
