package com.stablematching;

import java.util.List;

import org.json.JSONObject;

import com.stablematching.client.Tenant;
import com.stablematching.helper.JSONHelper;
import com.stablematching.resources.ServiceResource;
import com.stablematching.services.ResourceService;
import com.stablematching.services.TenantService;

public class AppDriver {
    private TenantService tenantService = new TenantService();
    private ResourceService resourceService = new ResourceService();

    public static void main(String[] args) {
        AppDriver appDriver = new AppDriver();
        List<Tenant> tenants = appDriver.tenantService.prepareTenantData("tenant.json");
        appDriver.generateRanksForTenants(tenants);
        List<ServiceResource> services = appDriver.resourceService.prepareServiceResourcesData("service.json");
        appDriver.generateRanksForServices(services);
    }

    private void generateRanksForTenants(List<Tenant> tenants) {
        tenantService.generateRank(tenants);
        JSONObject rankJson = JSONHelper.createTenantRankJson(tenants);
        JSONHelper.saveJsonToFile(rankJson, "tenants_ranks.json");
    }

    private void generateRanksForServices(List<ServiceResource> services) {
        resourceService.generateRank(services);
        JSONObject rankJson = JSONHelper.createServicesRankJson(services);
        JSONHelper.saveJsonToFile(rankJson, "services_ranks.json");
    }
}
