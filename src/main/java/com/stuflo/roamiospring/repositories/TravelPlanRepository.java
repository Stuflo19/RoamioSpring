package com.stuflo.roamiospring.repositories;

import com.stuflo.roamiospring.models.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findAllByUserId(Long userId);

    Optional<TravelPlan> findByUserIdAndId(Long userId, Long planId);

//    @Query("UPDATE TravelPlan t set t.name=:name, t.departureDate=:departureDate where t.id=:id and t.user.id=:userId")
//    Optional<TravelPlan> setTravelPlanByUserId(
//            @Param("name") String name,
//            @Param("departureDate") Long departureDate,
//            @Param("id") Long id,
//            @Param("userId") Long userId
//    );
}
