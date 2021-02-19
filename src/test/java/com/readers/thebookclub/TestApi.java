package com.readers.thebookclub;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class TestApi {

    private ArrayList<String> isbn = new ArrayList<>();
    private ArrayList<String> bookName = new ArrayList<>();
    private ArrayList<String> bookAuthors = new ArrayList<>();
    private ArrayList<String> thumbnail = new ArrayList<>();
    private ArrayList<String> bookDescription = new ArrayList<>();

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
                        System.out.println(stock.getIsbn());
                        System.out.println(stock.getBookName());
                        System.out.println(stock.getBookAuthors());
                        System.out.println(stock.getThumbnail());
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
    public void searchBookByIsbn() {
        isbn.clear();
        bookName.clear();
        bookAuthors.clear();
        thumbnail.clear();

        isbn.add("9780756982133");
        bookName.add("Charlie and the Chocolate Factory");
        bookAuthors.add("Roald Dahl");
        thumbnail.add("https://covers.openlibrary.org/b/id/8672180-M.jpg");

        List<Object> list = new ArrayList<>();
        list.add("{\"param\":\"9780756982133\"}");
        validateStocks(list);
    }

    @Test
    public void searchBook() {
        isbn.clear();
        bookName.clear();
        bookAuthors.clear();
        thumbnail.clear();

        isbn.add("9781782011057");
        bookName.add("Elucidating Alice: A Textual Commentary on Alice's Adventures in Wonderland");
        bookAuthors.add("Lewis Carroll");
        thumbnail.add("https://covers.openlibrary.org/b/id/8788680-M.jpg");

        isbn.add("9781598188844");
        bookName.add("Alice in Wonderland");
        bookAuthors.add("Lewis Carroll");
        thumbnail.add("https://covers.openlibrary.org/b/id/2003152-M.jpg");

        List<Object> list = new ArrayList<>();
        list.add("{\"param\":\"Alice's Adventures in Wonderland\"}");

        validateStocks(list);
    }
}