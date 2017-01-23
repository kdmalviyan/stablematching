package com.stablematching.services;

import java.util.List;

import com.stablematching.client.Tenant;
import com.stablematching.constants.Constant;
import com.stablematching.helper.RankHelper;
import com.stablematching.helper.Thresholds;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class RankOneService implements RankService {

    @Override
    public Rank generate(Tenant tenant, List<Rank> ranks) {
        Rank rank = new Rank();
        double availability = tenant.getAvailability();
        double responseTime = tenant.getResponseTime();
        if ((availability >= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, "availability"))) {
            RankHelper.createRank("availability", rank, Constant.NUMBERS.ONE);
        } else

        if (responseTime <= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, "responseTime")) {
            RankHelper.createRank("responseTime", rank, Constant.NUMBERS.ONE);
        } else if (RankHelper.canSetRank(ranks, "cost")) {
            RankHelper.createRank("cost", rank, Constant.NUMBERS.ONE);
        }
        tenant.getRanks().add(rank);
        return rank;
    }

    @Override
    public void generate(ServiceResource service, List<Rank> ranks) {
        Rank rank = new Rank();
        double availability = service.getAvailability();
        double responseTime = service.getResponseTime();
        if ((availability >= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, "availability"))) {
            RankHelper.createRank("availability", rank, Constant.NUMBERS.ONE);
        } else

        if (responseTime <= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, "responseTime")) {
            RankHelper.createRank("responseTime", rank, Constant.NUMBERS.ONE);
        } else if (RankHelper.canSetRank(ranks, "cost")) {
            RankHelper.createRank("cost", rank, Constant.NUMBERS.ONE);
        }
        service.getRanks().add(rank);
    }
}