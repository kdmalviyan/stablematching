package com.stablematching.services;

import java.util.List;

import com.stablematching.client.Tenant;
import com.stablematching.constants.Constant;
import com.stablematching.helper.RankHelper;
import com.stablematching.helper.Thresholds;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class RankTwoService implements RankService {
    @Override
    public Rank generate(Tenant tenant, List<Rank> ranks) {
        Rank rank = new Rank();
        double availability = tenant.getAvailability();
        double responseTime = tenant.getResponseTime();
        if ((availability >= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, Constant.AVAILABILITY))) {
            RankHelper.createRank(Constant.AVAILABILITY, rank, Constant.NUMBERS.TWO);
            tenant.getRanks().add(rank);
        } else if (responseTime <= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, Constant.RESPONSE_TIME)) {
            RankHelper.createRank(Constant.RESPONSE_TIME, rank, Constant.NUMBERS.TWO);
            tenant.getRanks().add(rank);
        } else if (RankHelper.canSetRank(ranks, Constant.COST)) {
            RankHelper.createRank(Constant.COST, rank, Constant.NUMBERS.TWO);
            tenant.getRanks().add(rank);
        } else if ((availability <= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, Constant.AVAILABILITY))
                || (responseTime >= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, Constant.RESPONSE_TIME))) {
            RankHelper.createRank(Constant.AVAILABILITY, rank, Constant.NUMBERS.TWO);
            tenant.getRanks().add(rank);
            rank = new Rank();
            RankHelper.createRank(Constant.RESPONSE_TIME, rank, Constant.NUMBERS.THREE);
            tenant.getRanks().add(rank);
        }
        return rank;
    }

    @Override
    public void generate(ServiceResource service, List<Rank> ranks) {

        Rank rank = new Rank();
        double availability = service.getAvailability();
        double responseTime = service.getResponseTime();
        if ((availability >= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, Constant.AVAILABILITY))) {
            RankHelper.createRank(Constant.AVAILABILITY, rank, Constant.NUMBERS.TWO);
            service.getRanks().add(rank);
        } else if (responseTime <= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, Constant.RESPONSE_TIME)) {
            RankHelper.createRank(Constant.RESPONSE_TIME, rank, Constant.NUMBERS.TWO);
            service.getRanks().add(rank);
        } else if (RankHelper.canSetRank(ranks, Constant.COST)) {
            RankHelper.createRank(Constant.COST, rank, Constant.NUMBERS.TWO);
            service.getRanks().add(rank);
        } else if ((availability <= Thresholds.AVAILABILITY.getValue() && RankHelper.canSetRank(ranks, Constant.AVAILABILITY))
                || (responseTime >= Thresholds.RESPONSETIME.getValue() && RankHelper.canSetRank(ranks, Constant.RESPONSE_TIME))) {
            RankHelper.createRank(Constant.AVAILABILITY, rank, Constant.NUMBERS.TWO);
            service.getRanks().add(rank);
            rank = new Rank();
            RankHelper.createRank(Constant.RESPONSE_TIME, rank, Constant.NUMBERS.THREE);
            service.getRanks().add(rank);
        }
    }
}
