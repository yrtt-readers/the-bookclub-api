package com.readers.thebookclub.model;

public class ActiveRegion {

    private String regionId;
    private String addressId;
    private String postCode;
    private String regionName;


    public ActiveRegion() {
    }


    public ActiveRegion(String regionId, String addressId, String postCode, String regionName) {
        this.regionId = regionId;
        this.addressId = addressId;
        this.postCode = postCode;
        this.regionName = regionName;
    }


    public String getRegionId() {
        return this.regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }    
}
