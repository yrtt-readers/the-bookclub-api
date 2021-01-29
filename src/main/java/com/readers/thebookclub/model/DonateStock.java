package com.readers.thebookclub.model;

public class DonateStock {

    private String isbn;
    private String title;
    private String author;
    private String summary;
    private String thumbnail;

    public DonateStock() {
    }


    public DonateStock(String isbn, String title, String author, String summary, String thumbnail) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.thumbnail = thumbnail;
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




    
}
