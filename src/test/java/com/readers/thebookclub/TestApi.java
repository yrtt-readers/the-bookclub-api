package com.readers.thebookclub;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class TestApi {

    private ArrayList<String> isbn = new ArrayList<String>();
    private ArrayList<String> bookName = new ArrayList<String>();
    private ArrayList<String> bookAuthors = new ArrayList<String>();
    private ArrayList<String> thumbnail = new ArrayList<String>();
    private ArrayList<String> bookDescription = new ArrayList<String>();

    private void validateStocks(List<Object> list) {
        for (Object param : list) {
            OpenlibraryApiGateway og =
                    new OpenlibraryApiGateway(param);
            if (og.getStatusCode() != 200)
                System.out.println(ErrorHandler.message.get(og.getStatusCode()));
            else {
                int count = 0;
                for (Stock stock : og.getStocks()) {
                    if (og.getStatusCode() == 200 && stock != null) {
                        assertEquals(isbn.get(count), stock.getIsbn());
                        assertEquals(bookName.get(count), stock.getBookName());
                        assertEquals(bookAuthors.get(count), stock.getBookAuthors());
                        assertEquals(thumbnail.get(count), stock.getThumbnail());
                        assertEquals(bookDescription.get(count), stock.getBookDescription());
                        count++;
                    }
                }
            }
        }
    }

    @Test
    public void testSetup() {
    }

    @Test
    public void checkStocksByIsbn() {

        List<Object> list = new ArrayList<Object>();
        list.add("{\"param\":\"9780060217860\"}");
        // list.add("{\"param\":\"9780789411464\"}");
        // list.add("{\"param\":\"9780806919317\"}");
        // list.add("{\"param\":\"9780875349343\"}");
        // list.add("{\"param\":\"9780893751159\"}");
        // list.add("{\"param\":\"9780689853944\"}");
        // list.add("{\"param\":\"9780866228312\"}");
        // list.add("{\"param\":\"9780911981568\"}");
        // list.add("{\"param\":\"9780816741342\"}");

        isbn.clear();
        bookName.clear();
        bookAuthors.clear();
        thumbnail.clear();
        bookDescription.clear();

        isbn.add("9780060217877");
        bookName.add("Wider than the sky");
        bookAuthors.add("Scott Elledge");
        thumbnail.add("");
        bookDescription.add("");

        isbn.add("9780060217860");
        bookName.add("Wider than the sky");
        bookAuthors.add("Scott Elledge");
        thumbnail.add("");
        bookDescription.add("");

        validateStocks(list);
    }

    @Test
    public void searchBookByIsbn() {

        List<Object> list = new ArrayList<Object>();
        list.add("{\"param\":\"9780747575443\"}");
        validateStocks(list);
    }

    @Test
    public void searchBook() {
        List<Object> list = new ArrayList<Object>();
        list.add("{\"param\":\"Charlie and the chocolate factory\"}");
        list.add("{\"param\":\"Alice's Adventures in Wonderland\"}");
        list.add("{\"param\":\"Harry Potter\"}");
        validateStocks(list);
    }
}