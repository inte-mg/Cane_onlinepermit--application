package com.example.permitapplication.model;
import java.io.Serializable;


public class FarmDetails implements Serializable{
    String farmerName;
    String mobileNo;
    String emailAddress;
    String idNo;
    String county;
    String subCounty;
    String location;
    String farmSize;
    String landOwnership;
    String waterSource;
    String sugarcaneVariety;

    public FarmDetails() {
    }

    public FarmDetails(String farmerName, String mobileNo, String emailAddress, String idNo, String county,
                       String subCounty, String location, String farmSize, String landOwnership,
                       String waterSource, String sugarcaneVariety)
    {
        this.farmerName = farmerName;
        this.mobileNo = mobileNo;
        this.emailAddress = emailAddress;
        this.idNo = idNo;
        this.county = county;
        this.subCounty = subCounty;
        this.location = location;
        this.farmSize = farmSize;
        this.landOwnership = landOwnership;
        this.waterSource = waterSource;
        this.sugarcaneVariety = sugarcaneVariety;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    public String getLandOwnership() {
        return landOwnership;
    }

    public void setLandOwnership(String landOwnership) {
        this.landOwnership = landOwnership;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public String getSugarcaneVariety() {
        return sugarcaneVariety;
    }

    public void setSugarcaneVariety(String sugarcaneVariety) {
        this.sugarcaneVariety = sugarcaneVariety;
    }
}
