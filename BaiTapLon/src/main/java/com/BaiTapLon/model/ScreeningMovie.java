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
public class ScreeningMovie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sreeningId;

    @Column(nullable = false)
    @NotBlank(message = "This dates field can not be blank")
    private String dates;

    @Column(nullable = false)
    @NotBlank(message = "This hour field can not be blank")
    private String hour;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private ScreeningRoom screeningRoom;

    @OneToMany(mappedBy = "screeningMovie")
    private List<Ticket> tickets;

}
