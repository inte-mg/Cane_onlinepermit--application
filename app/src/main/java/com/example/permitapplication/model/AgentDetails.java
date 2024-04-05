package com.example.permitapplication.model;
import java.io.Serializable;


public class AgentDetails implements Serializable{
    String agentName;
    String mobileNo;
    String emailAddress;
    String idNo;
    String county;
    String subCounty;
    String location;

    public AgentDetails() {
    }

    public AgentDetails(String agentName, String mobileNo, String emailAddress, String idNo, String county,
                        String subCounty, String location)
    {
        this.agentName = agentName;
        this.mobileNo = mobileNo;
        this.emailAddress = emailAddress;
        this.idNo = idNo;
        this.county = county;
        this.subCounty = subCounty;
        this.location = location;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
}
