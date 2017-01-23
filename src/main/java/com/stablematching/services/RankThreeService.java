package com.stablematching.services;

import java.util.List;

import com.stablematching.client.Tenant;
import com.stablematching.constants.Constant;
import com.stablematching.helper.RankHelper;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class RankThreeService implements RankService {

    @Override
    public Rank generate(Tenant tenant, List<Rank> ranks) {
        Rank rank = new Rank();
        if (RankHelper.canSetRank(ranks, "availability")) {
            RankHelper.createRank("availability", rank, Constant.NUMBERS.THREE);
        } else if (RankHelper.canSetRank(ranks, "responseTime")) {
            RankHelper.createRank("responseTime", rank, Constant.NUMBERS.THREE);
        } else if (RankHelper.canSetRank(ranks, "cost")) {
            RankHelper.createRank("cost", rank, Constant.NUMBERS.THREE);
        }
        tenant.getRanks().add(rank);
        return rank;
    }

    @Override
    public void generate(ServiceResource service, List<Rank> ranks) {
        Rank rank = new Rank();
        if (RankHelper.canSetRank(ranks, "availability")) {
            RankHelper.createRank("availability", rank, Constant.NUMBERS.THREE);
        } else if (RankHelper.canSetRank(ranks, "responseTime")) {
            RankHelper.createRank("responseTime", rank, Constant.NUMBERS.THREE);
        } else if (RankHelper.canSetRank(ranks, "cost")) {
            RankHelper.createRank("cost", rank, Constant.NUMBERS.THREE);
        }
        service.getRanks().add(rank);
    }
}
