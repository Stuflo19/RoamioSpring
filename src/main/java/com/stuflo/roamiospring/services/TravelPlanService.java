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

    public TravelPlanResponse createTravelPlan(TravelPlanDto travelPlan, Long userId) {
        TravelPlan tp = travelPlanRepository.save(new TravelPlan(travelPlan, userId));

        return new TravelPlanResponse(tp);
    }

    public TravelPlanResponse findById(Long userId, Long id) {
        TravelPlan tp = travelPlanRepository.findByUserIdAndId(userId, id).orElse(null);

        if (tp == null) {
            return null;
        }

        return new TravelPlanResponse(tp);
    }

    public List<TravelPlanResponse> findAll(Long userId) {
        List<TravelPlan> tps = travelPlanRepository.findAllByUserId(userId);

        return tps.stream().map(TravelPlanResponse::new).toList();
    }

    public TravelPlanResponse updateTravelPlan(TravelPlanDto travelPlan, Long userId, Long id) {
        TravelPlan tp = travelPlanRepository.findByUserIdAndId(userId, id).orElse(null);

        if (tp == null) {
            return null;
        }

        tp = tp.updateTravelPlan(travelPlan);

        travelPlanRepository.save(tp);

        return new TravelPlanResponse(tp);
    }

    public void deleteById(Long id) {
        travelPlanRepository.deleteById(id);
    }
}
