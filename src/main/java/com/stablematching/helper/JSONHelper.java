package com.stablematching.helper;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stablematching.client.Tenant;
import com.stablematching.constants.Constant;
import com.stablematching.model.Rank;
import com.stablematching.resources.ServiceResource;

public class JSONHelper {
    public static JSONObject readJsonFromFile(String fileName) {
        InputStream inputStream = JSONHelper.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        while (true) {
            try {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                content.append(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new JSONObject(content.toString());
    }

    /** @param tenantJson
     * @param tenants */
    public static void getTenantList(JSONObject tenantJson, List<Tenant> tenants) {
        JSONArray tenantArray = tenantJson.getJSONArray(Constant.TENANTS);
        for (int i = 0; i < tenantArray.length(); i++) {
            JSONObject tenatObject = tenantArray.getJSONObject(i);
            Tenant tenant = new Tenant();
            tenant.setName(tenatObject.getString(Constant.NAME));
            tenant.setAvailability(tenatObject.getDouble(Constant.AVAILABILITY));
            tenant.setResponseTime(tenatObject.getDouble(Constant.RESPONSE_TIME));
            tenant.setCost(tenatObject.getDouble(Constant.COST));
            tenants.add(tenant);
        }
    }

    /** @param serviceResourceJson
     * @param serviceResources */
    public static void getServiceResourceList(JSONObject serviceResourceJson, List<ServiceResource> serviceResources) {
        JSONArray serviceResourceArray = serviceResourceJson.getJSONArray(Constant.SERVICES);
        for (int i = 0; i < serviceResourceArray.length(); i++) {
            JSONObject serviceResourceObject = serviceResourceArray.getJSONObject(i);
            ServiceResource serviceResource = new ServiceResource();
            serviceResource.setName(serviceResourceObject.getString(Constant.NAME));
            serviceResource.setAvailability(serviceResourceObject.getDouble(Constant.AVAILABILITY));
            serviceResource.setResponseTime(serviceResourceObject.getDouble(Constant.RESPONSE_TIME));
            serviceResource.setCost(serviceResourceObject.getDouble(Constant.COST));
            serviceResources.add(serviceResource);
        }
    }

    /** @param tenants
     * @return */
    public static JSONObject createTenantRankJson(List<Tenant> tenants) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Tenant tenant : tenants) {
            JSONObject object = createRankObject(tenant.getRanks(), tenant.getName());
            jsonArray.put(object);
        }
        jsonObject.put(Constant.TENANTS, jsonArray);
        return jsonObject;
    }

    /** @param services
     * @return */
    public static JSONObject createServicesRankJson(List<ServiceResource> services) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (ServiceResource service : services) {
            JSONObject object = createRankObject(service.getRanks(), service.getName());
            jsonArray.put(object);
        }
        jsonObject.put(Constant.SERVICES, jsonArray);
        return jsonObject;
    }

    /** @param ranks
     * @param name
     * @return */
    private static JSONObject createRankObject(List<Rank> ranks, String name) {
        JSONObject rankObj = new JSONObject();
        rankObj.put(Constant.NAME, name);
        rankObj.put(Constant.COST, getValue(ranks, Constant.COST));
        rankObj.put(Constant.AVAILABILITY, getValue(ranks, Constant.AVAILABILITY));
        rankObj.put(Constant.RESPONSE_TIME, getValue(ranks, Constant.RESPONSE_TIME));
        return rankObj;
    }

    /** @param ranks
     * @param name
     * @return */
    private static Integer getValue(List<Rank> ranks, String name) {
        for (Rank rank : ranks) {
            if (rank.getName().equals(name)) {
                return rank.getValue();
            }
        }
        return null;
    }

    public static void saveJsonToFile(JSONObject rankJson, String fileName) {
        FileWriter jsonFileWriter;
        try {
            jsonFileWriter = new FileWriter(fileName);
            jsonFileWriter.write(rankJson.toString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
