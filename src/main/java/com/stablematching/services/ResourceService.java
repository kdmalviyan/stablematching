package com.stablematching.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.stablematching.constants.Constant;
import com.stablematching.factory.RankServiceFactory;
import com.stablematching.helper.JSONHelper;
import com.stablematching.helper.RankHelper;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class ResourceService {
    public List<ServiceResource> prepareServiceResourcesData(String fileName) {
        JSONObject serviceResourceJson = JSONHelper.readJsonFromFile(fileName);
        List<ServiceResource> serviceResources = new ArrayList<ServiceResource>();
        JSONHelper.getServiceResourceList(serviceResourceJson, serviceResources);
        return serviceResources;
    }

    public void generateRank(List<ServiceResource> services) {

        for (ServiceResource service : services) {
            List<Rank> ranks = service.getRanks();
            if (ranks == null) {
                ranks = new ArrayList<Rank>();
                service.setRanks(ranks);
            }
            createRank(service, ranks);
        }
    
    }
    
    private void createRank(ServiceResource service, List<Rank> ranks) {
        RankGenerator generator;
        while (ranks.size() != Constant.CONFIGURATION.PARAMATER_SIZE) {
            if (RankHelper.getRank(Constant.NUMBERS.ONE, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.ONE));
                generator.generate(service, ranks);
                continue;
            }
            if (RankHelper.getRank(Constant.NUMBERS.TWO, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.TWO));
                generator.generate(service, ranks);
                continue;
            }
            if (RankHelper.getRank(Constant.NUMBERS.THREE, ranks) == null) {
                generator = new RankGenerator(RankServiceFactory.getRankService(Constant.NUMBERS.THREE));
                generator.generate(service, ranks);
                continue;
            }
        }
    }
}
