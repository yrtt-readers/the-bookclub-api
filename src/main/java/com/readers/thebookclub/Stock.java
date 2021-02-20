package com.readers.thebookclub;

public class Stock {

    private String isbn;
    private int qty;
    private String bookName;
    private String bookAuthors;
    private String postCode;
    private String thumbnail;
    private String bookDescription;

    public Stock(String isbn,
                 int qty,
                 String bookName,
                 String bookAuthors,
                 String postCode,
                 String thumbnail,
                 String bookDescription) {
        this.isbn = isbn;
        this.qty = qty;
        this.bookName = bookName;
        this.bookAuthors = bookAuthors;
        this.postCode = postCode;
        this.thumbnail = thumbnail;
        this.bookDescription = bookDescription;
    }

    public Stock(String isbn,
                 String bookName,
                 String bookAuthors,
                 String thumbnail) {
        this(isbn, 0, bookName, bookAuthors, null, thumbnail, null);
    }

    public Stock(String isbn,
                 int qty,
                 String postCode) {
        this(isbn, qty, null, null, postCode, null, null);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(String bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}