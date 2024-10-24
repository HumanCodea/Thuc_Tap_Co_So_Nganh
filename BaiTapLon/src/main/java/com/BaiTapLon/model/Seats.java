package com.BaiTapLon.model;

import java.util.List;

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
public class Seats {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @NotBlank(message = "This chair name field can be not blank")
    @Column(nullable = false)
    private String chairName;

    @NotBlank(message = "This chair status field can be not blank")
    @Column(nullable = false)
    private String chair_status;

    @OneToMany(mappedBy = "seats")
    private List<Ticket> tickets;

    @ManyToOne
    private ScreeningRoom screeningRoom;
    
}
