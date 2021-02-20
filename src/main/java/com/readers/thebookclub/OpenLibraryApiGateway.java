package com.readers.thebookclub;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpenLibraryApiGateway {

    final int isbnLength, maxStocks;
    final private String prefix, searchPrefix, suffix;
    private String param;
    private int statusCode;

    private OpenLibraryApiGateway() {
        this.prefix = "https://openlibrary.org/api/books?bibkeys=";
        this.searchPrefix = "http://openlibrary.org/search.json?q=";
        this.suffix = "&jscmd=data&format=json";
        this.statusCode = 200;
        this.isbnLength = 13;
        this.maxStocks = 2;
    }

    public OpenLibraryApiGateway(Object param) {
        this();
        this.param = String.valueOf(param).substring(7, String.valueOf(param).length() - 1);
        if (!this.param.matches("[0-9]+") || this.param.length() != this.isbnLength) {
            this.param = this.param.replaceAll("[ ]", "+");
            this.param = this.param.replaceAll("[^A-Za-z0-9+]", "");
        }
    }

    public List<Stock> getStocks() {

        List<Stock> stocks = new ArrayList<>();

        try {
            if (this.param.matches("[0-9]+") && this.param.length() == this.isbnLength)
                stocks.add(getStock(this.param));
            else {
                URL url = new URL(searchPrefix + this.param);
                Iterator<JsonNode> tree =
                        new ObjectMapper()
                                .readTree(url)
                                .get("docs").elements();

                while (tree.hasNext()) {
                    JsonNode node = tree.next();
                    Iterator<String> list = node.fieldNames();

                    while (list.hasNext()) {
                        String isbnName = list.next();

                        if (isbnName.equals("isbn")) {
                            Iterator<JsonNode> isbnTree =
                                    node.get(isbnName).elements();

                            while (isbnTree.hasNext()) {
                                JsonNode isbnNode = isbnTree.next();
                                String isbn = isbnNode.textValue();

                                if (isbn.matches("[0-9]+") &&
                                        isbn.length() == this.isbnLength &&
                                        stocks.size() <= this.maxStocks) {
                                    Stock stock = getStock(isbn);
                                    if (this.statusCode == 200 && stock != null)
                                        stocks.add(stock);
                                    if (stocks.size() == this.maxStocks)
                                        return stocks;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (this.statusCode != 401)
                this.statusCode = 503;
            e.printStackTrace();
        }

        if (stocks.size() == 0)
            this.statusCode = 500;
        return stocks;
    }

    private Stock getStock(String isbn) {

        Stock stock = null;

        try {
            if (this.statusCode != 200)
                throw new Exception();
            if (!isbn.matches("[0-9]+") || isbn.length() != 13) {
                this.statusCode = 400;
                throw new Exception();
            }

            URL url = new URL(prefix + "ISBN:" + isbn + suffix);

            JsonNode book;
            String bookName;
            StringBuilder bookAuthors = new StringBuilder();
            String thumbnail = "";
            book = new ObjectMapper().readTree(url).get("ISBN:" + isbn);

            if (book != null) {
                bookName = String.valueOf(book.get("title"))
                        .replaceAll("\"", "");

                if (book.get("authors") != null && book.get("authors").isArray())
                    for (JsonNode author : book.get("authors"))
                        bookAuthors
                                .append(String.valueOf(author.get("name"))
                                        .replaceAll("\"", "")).append(", ");

                if (bookAuthors.length() > 2)
                    bookAuthors = new StringBuilder(bookAuthors.substring(0, bookAuthors.length() - 2));

                if (book.get("cover") != null && book.get("cover").isObject()) {
                    if (book.get("cover").get("medium") != null)
                        thumbnail = String.valueOf(book.get("cover")
                                .get("medium")).replaceAll("\"", "");
                    else if (book.get("cover").get("small") != null)
                        thumbnail = String.valueOf(book.get("cover")
                                .get("small")).replaceAll("\"", "");
                }

                if (!bookName.equals("") &&
                        !bookAuthors.toString().equals("") &&
                        !thumbnail.equals(""))
                    stock = new Stock(isbn, bookName, bookAuthors.toString(), thumbnail);
            }
        } catch (Exception e) {
            if (this.statusCode != 400)
                this.statusCode = 400;
            System.out.println("param: " + this.param);
            e.printStackTrace();
        }
        return stock;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
