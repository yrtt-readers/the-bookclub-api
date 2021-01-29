package com.readers.thebookclub.model;

public class Region {
    private String regionId;
    private String regionName;
    private String bookCollectionMessage;
    private String bookDonationMessage;
    private String addressId;
    private String houseNumber;
    private String street;
    private String city;
    private String county;
    private String country;
    private String postCode;


    public Region() {
    }


    public Region(String regionId, String regionName, String bookCollectionMessage, String bookDonationMessage, String addressId, String houseNumber, String street, String city, String county, String country, String postCode) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.bookCollectionMessage = bookCollectionMessage;
        this.bookDonationMessage = bookDonationMessage;
        this.addressId = addressId;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.county = county;
        this.country = country;
        this.postCode = postCode;
    }


    public String getRegionId() {
        return this.regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getBookCollectionMessage() {
        return this.bookCollectionMessage;
    }

    public void setBookCollectionMessage(String bookCollectionMessage) {
        this.bookCollectionMessage = bookCollectionMessage;
    }

    public String getBookDonationMessage() {
        return this.bookDonationMessage;
    }

    public void setBookDonationMessage(String bookDonationMessage) {
        this.bookDonationMessage = bookDonationMessage;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }



    
}
