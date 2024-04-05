package com.example.permitapplication.model;

public class GrantedPermit {
    String farmerName;
    String farmName;
    String farmArea;
    String subCounty;
    String county;
    String agentName;
    String amount;
    String expireDate;
    String uid;

    public GrantedPermit() {
    }

    public GrantedPermit(String farmerName, String farmName, String farmArea, String subCounty, String county, String agentName , String amount , String expireDate) {
        this.farmerName = farmerName;
        this.farmName = farmName;
        this.farmArea = farmArea;
        this.subCounty = subCounty;
        this.county = county;
        this.agentName = agentName;
        this.amount  = amount;
        this.expireDate = expireDate;

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmArea() {
        return farmArea;
    }

    public void setFarmArea(String farmArea) {
        this.farmArea = farmArea;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
