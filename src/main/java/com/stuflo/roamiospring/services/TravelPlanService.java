package com.stuflo.roamiospring.services;

import com.stuflo.roamiospring.dtos.TravelPlanDto;
import com.stuflo.roamiospring.models.TravelPlan;
import com.stuflo.roamiospring.repositories.TravelPlanRepository;
import com.stuflo.roamiospring.responses.TravelPlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPlanService {
    private final TravelPlanRepository travelPlanRepository;

    public TravelPlanService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    public TravelPlanResponse createTravelPlan(TravelPlanDto travelPlan) {
        TravelPlan tp = travelPlanRepository.save(new TravelPlan(travelPlan));

        return new TravelPlanResponse(tp);
    }

    public TravelPlanResponse findById(Long id) {
        TravelPlan tp = travelPlanRepository.findById(id).orElse(null);

        if (tp == null) {
            return null;
        }

        return new TravelPlanResponse(tp);
    }

    public List<TravelPlanResponse> findAll() {
        List<TravelPlan> tps = travelPlanRepository.findAll();

        return tps.stream().map(TravelPlanResponse::new).toList();
    }

    public TravelPlanResponse updateTravelPlan(TravelPlanDto travelPlan, Long id) {
        TravelPlan newPlan = travelPlanRepository.save(new TravelPlan(travelPlan, id));

        return new TravelPlanResponse(newPlan);
    }

    public void deleteById(Long id) {
        travelPlanRepository.deleteById(id);
    }
}
