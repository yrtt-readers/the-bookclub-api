package com.readers.thebookclub.model;

public class RequestStock {

    private String isbn;
    private String title;
    private String author;
    private String summary;
    private String thumbnail;
    private String regionId;
    private String addressId;
    private String postCode;
    private String regionName;

    public RequestStock() {
    }


    public RequestStock(String isbn, String title, String author, String summary, String thumbnail, String regionId, String addressId, String postCode, String regionName) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.regionId = regionId;
        this.addressId = addressId;
        this.postCode = postCode;
        this.regionName = regionName;
    }
    

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
