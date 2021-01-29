package com.readers.thebookclub.model;

public class BookStock {

	private int regionId2;
    private String isbn;
    private int stockQty;
    private int maxCount;

    public BookStock() {

    }

    public BookStock(int regionId2, String isbn, int stockQty, int maxCount) {
        this.regionId2 = regionId2;
        this.isbn = isbn;
        this.stockQty = stockQty;
        this.maxCount = maxCount;
    }
    
    public int getRegionId2() {
        return this.regionId2;
    }

    public void setRegionId2(int regionId2) {
        this.regionId2 = regionId2;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStockQty() {
        return this.stockQty;
    }

    public void setStocQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }


}
