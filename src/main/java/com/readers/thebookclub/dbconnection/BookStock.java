package com.readers.thebookclub.dbconnection;

public class BookStock {

	private int region_id2;
    private String isbn;
    private int stock_qty;
    private int max_count;

    public BookStock() {

    }

    public BookStock(int region_id2, String isbn, int stock_qty, int max_count) {
        this.region_id2 = region_id2;
        this.isbn = isbn;
        this.stock_qty = stock_qty;
        this.max_count = max_count;
    }
    
    public int getRegion_id2() {
        return this.region_id2;
    }

    public void setRegion_id2(int region_id2) {
        this.region_id2 = region_id2;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock_qty() {
        return this.stock_qty;
    }

    public void setStock_qty(int stock_qty) {
        this.stock_qty = stock_qty;
    }

    public int getMax_count() {
        return this.max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }


}
