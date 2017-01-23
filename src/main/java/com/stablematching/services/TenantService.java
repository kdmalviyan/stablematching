package com.stablematching.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.stablematching.client.Tenant;
import com.stablematching.constants.Constant;
import com.stablematching.factory.RankServiceFactory;
import com.stablematching.helper.JSONHelper;
import com.stablematching.helper.RankHelper;
import com.stablematching.model.Rank;

public class TenantService {

    /** @param fileName
     * @return */
    public List<Tenant> prepareTenantData(String fileName) {
        JSONObject tenantJson = JSONHelper.readJsonFromFile(fileName);
        List<Tenant> tenants = new ArrayList<Tenant>();
        JSONHelper.getTenantList(tenantJson, tenants);
        return tenants;
    }

    /** @param tenants */
    public void generateRank(List<Tenant> tenants) {
        for (Tenant tenant : tenants) {
            List<Rank> ranks = tenant.getRanks();
            if (ranks == null) {
                ranks = new ArrayList<Rank>();
                tenant.setRanks(ranks);
            }
            createRank(tenant, ranks);
        }
    }

    private void createRank(Tenant tenant, List<Rank> ranks) {
        RankGenerator generator;
        while (ranks.size() != Constant.CONFIGURATION.PARAMATER_SIZE) {
            if (RankHelper.getRank(Constant.NUMBERS.ONE, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.ONE));
                generator.generate(tenant, ranks);
                continue;
            }
            if (RankHelper.getRank(Constant.NUMBERS.TWO, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.TWO));
                generator.generate(tenant, ranks);
                continue;
            }
            if (RankHelper.getRank(Constant.NUMBERS.THREE, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.THREE));
                generator.generate(tenant, ranks);
                continue;
            }
        }
    }
}
