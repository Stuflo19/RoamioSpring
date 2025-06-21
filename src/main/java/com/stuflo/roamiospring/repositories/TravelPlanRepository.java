package com.stuflo.roamiospring.repositories;

import com.stuflo.roamiospring.models.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> { }
