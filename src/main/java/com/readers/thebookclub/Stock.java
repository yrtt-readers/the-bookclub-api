package com.readers.thebookclub;

public class Stock {

    private String isbn;
    private int qty;
    private String bookName;
    private String bookAuthors;
    private String postCode;

    public Stock(String isbn, 
                int qty, 
                String bookName, 
                String bookAuthors, 
                String postCode){
        this.isbn = isbn;
        this.qty = qty;
        this.bookName = bookName;
        this.bookAuthors = bookAuthors;
        this.postCode = postCode;
    }

    public String getIsbn(){
        return isbn;
    }
    public int getQty(){
        return qty;
    }
    public String getBookName(){
        return bookName;
    }
    public String getBookAuthors(){
        return bookAuthors;
    }
    public String getPostCode(){
        return postCode;
    }
}