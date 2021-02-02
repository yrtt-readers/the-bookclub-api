package com.readers.thebookclub.model;

public class Transaction {
    private int regionId;
    private int requestType;
    private String isbn;

    public Transaction() {
    }


    public Transaction(int regionId, int requestType, String isbn) {
        this.regionId = regionId;
        this.requestType = requestType;
        this.isbn = isbn;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getRequestType() {
        return this.requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}
