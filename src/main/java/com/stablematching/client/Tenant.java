package com.stablematching.client;

import java.util.List;

import com.stablematching.model.Rank;

public class Tenant {
    private String name;
    private double availability;
    private double responseTime;
    private double cost;
    private List<Rank> ranks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Tenant [name=" + name + ", availability=" + availability + ", responseTime=" + responseTime + ", cost=" + cost + "]";
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }
}
