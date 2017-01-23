package com.stablematching.services;

import java.util.List;

import com.stablematching.client.Tenant;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public interface RankService {
    public Rank generate(Tenant tenant, List<Rank> ranks);

    public void generate(ServiceResource service, List<Rank> ranks);
}
