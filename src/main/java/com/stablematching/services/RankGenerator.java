package com.stablematching.services;

import java.util.List;

import com.stablematching.client.Tenant;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class RankGenerator {
    private RankService rankService;

    public RankGenerator(RankService rankService) {
        this.rankService = rankService;
    }

    public Rank generate(Tenant tenant, List<Rank> ranks) {
        return rankService.generate(tenant, ranks);
    }

    public void generate(ServiceResource service, List<Rank> ranks) {
        rankService.generate(service, ranks);
    }
}
