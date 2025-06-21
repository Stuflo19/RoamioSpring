package com.stuflo.roamiospring.models;

import com.stuflo.roamiospring.dtos.TravelPlanDto;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class TravelPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long departureDate;

//    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TravelPlan() {}

    public TravelPlan(String name, Long departureDate, User user) {
        this.name = name;
        this.departureDate = departureDate;
        this.user = user;
    }

    public TravelPlan(TravelPlanDto travelPlanDto) {
        User emptyUser = new User();
        emptyUser.setId(travelPlanDto.getUserId());

        this.name = travelPlanDto.getName();
        this.departureDate = travelPlanDto.getDepartureDate();
        this.user = emptyUser;
    }

    public TravelPlan(TravelPlanDto travelPlanDto, Long id) {
        User emptyUser = new User();
        emptyUser.setId(travelPlanDto.getUserId());

        this.name = travelPlanDto.getName();
        this.departureDate = travelPlanDto.getDepartureDate();
        this.user = emptyUser;
        this.id = id;
    }
}
