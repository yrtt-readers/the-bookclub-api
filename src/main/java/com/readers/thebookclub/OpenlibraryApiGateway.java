package com.readers.thebookclub;

import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class OpenlibraryApiGateway{

    final private String prefix, searchPrefix, suffix, detailSuffix;
    final int isbnLength, maxIsbn;
    private URL url, detailUrl, searchUrl;
    private String isbn, isbnKey, param;
    private List<String> isbnList;
    private int statusCode;

    private OpenlibraryApiGateway (){

        this.prefix = "https://openlibrary.org/api/books?bibkeys=";
        this.searchPrefix = "http://openlibrary.org/search.json?q=";
        this.suffix = "&jscmd=data&format=json";
        this.detailSuffix = "&jscmd=details&format=json";
        this.statusCode = 200;
        this.isbnLength = 13;
        this.maxIsbn = 2;
    }
    public OpenlibraryApiGateway (Object param){

        this();
        isbnList = new ArrayList<String>();

        try{
            this.param = String.valueOf(param).substring(7,String.valueOf(param).length()-1);
            if(this.param.matches("[0-9]+") && this.param.length()==this.isbnLength)
                isbnList.add(this.param);
            else{
                this.param = this.param.replaceAll("[^A-Za-z0-9]", "+");
                this.searchUrl = new URL(searchPrefix + this.param);                
                Iterator<JsonNode> tree = 
                    new ObjectMapper()
                        .readTree(this.searchUrl)
                        .get("docs").elements();

                while(tree.hasNext()){
                    JsonNode node = tree.next();
                    Iterator<String> list = node.fieldNames();

                    while(list.hasNext()){
                        String isbnName = list.next();

                        if(isbnName.equals("isbn")){
                            Iterator<JsonNode> isbnTree = 
                                node.get(isbnName).elements();

                                while(isbnTree.hasNext()){
                                    JsonNode isbnNode = isbnTree.next();
                                    String isbn = isbnNode.textValue();

                                    if(isbn.matches("[0-9]+") && isbn.length()==
                                        this.isbnLength && isbnList.size()<=this.maxIsbn){
                                        isbnList.add(isbn);
                                        
                                        if(isbnList.size()==this.maxIsbn)
                                            return;                                        
                                    }
                                }
                        }
                    }
                }
            }
		    }catch(Exception e){
                if(this.statusCode!=401)
                    this.statusCode = 503; 
				e.printStackTrace();
            }
    }



	public List<Stock> getStocks(){

        List<Stock> stocks = new ArrayList<Stock>();

        for(String isbn : isbnList)
            stocks.add(getStock(isbn));

        if (stocks.size()==0)
            this.statusCode = 500;
        return stocks;
    }

	private Stock getStock(String isbn){

		Stock stock = null;

        try{
            if(this.statusCode!=200)
                throw new Exception();
            if(!isbn.matches("[0-9]+") || isbn.length()!=13){
                this.statusCode=400;
                throw new Exception();
            }

            this.isbn = isbn;
            this.isbnKey = "ISBN:"+isbn;
            this.url = new URL(prefix + this.isbnKey + suffix);
            this.detailUrl = new URL(prefix + this.isbnKey + detailSuffix);

            JsonNode book, bookDetail;
            String bookName="", bookAuthors="", thumbnail="", bookDescription="";
			book = new ObjectMapper().readTree(url).get(this.isbnKey);
            // bookDetail = new ObjectMapper().readTree(this.detailUrl).get(this.isbnKey);
			
            if(book != null){
			    bookName = String.valueOf(book.get("title")).replaceAll("\"", "");

			if(book.get("authors") != null && book.get("authors").isArray())
				for(JsonNode author : book.get("authors"))
					bookAuthors += String.valueOf(author.get("name")).replaceAll("\"", "") + ", ";

            if(bookAuthors.length()>2)
                bookAuthors = bookAuthors.substring(0,bookAuthors.length()-2);

            if(book.get("cover") != null && book.get("cover").isObject()){
                if(book.get("cover").get("small") != null)
					thumbnail = String.valueOf(book.get("cover").get("small")).replaceAll("\"", "");
                else if(book.get("cover").get("medium") != null)
					thumbnail = String.valueOf(book.get("cover").get("medium")).replaceAll("\"", "");

                // if(bookDetail.get("description") != null)
                //     bookDescription = String.valueOf(bookDetail.get("description")).replaceAll("\"", "");
            }	
                stock = new Stock(isbn,bookName,bookAuthors,thumbnail,bookDescription);
            }
            }catch(Exception e){
                if(this.statusCode!=400)
                    this.statusCode=400;
                System.out.println("param: "+this.param);
				e.printStackTrace();
            }finally{return stock;}
	}

    public int getStatusCode(){
        return statusCode;
    }

}
