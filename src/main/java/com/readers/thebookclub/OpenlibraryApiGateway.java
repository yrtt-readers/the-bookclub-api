package com.readers.thebookclub;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.URL;
import java.net.URI;

// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public class OpenlibraryApiGateway{

    final private String prefix, searchPrefix, suffix, detailSuffix;
    private URL url, detailUrl;
    private String isbn, isbnKey, param;
    private List<String> isbnList;
    private int statusCode;

    public OpenlibraryApiGateway (){

        this.prefix = "https://openlibrary.org/api/books?bibkeys=";
        this.searchPrefix = "http://openlibrary.org/search?q=";
        this.suffix = "&jscmd=data&format=json";
        this.detailSuffix = "&jscmd=details&format=json";
        this.statusCode = 200;
    }
    public OpenlibraryApiGateway (String isbn){

        this();
        configUrl(isbn);
    }
    public OpenlibraryApiGateway (Object param){

        this();
        isbnList = new ArrayList<String>();

        try{
            configParam(param);
            if(this.statusCode!=200)
                throw new Exception();

			// URI uri = new URI(searchPrefix);
		    // HttpClient client = HttpClient.newHttpClient();
		    // HttpRequest request = HttpRequest.newBuilder(
			// 	new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),
			// 		"{\"query\":\"" + this.param + "\"}",uri.getFragment()))
          	// 		.build();											   
		    // HttpResponse<InputStream> response = client.send(request,BodyHandlers.ofInputStream());

    		// System.out.println(response.statusCode());
		    // BufferedReader br = new BufferedReader(new InputStreamReader(response.body()));
			// while(br.readLine()!=null)
    		// 	isbnList.add(br.readLine());

		    }catch(Exception e){
                if(this.statusCode!=401)
                    this.statusCode = 503; 
            }

            isbnList.add(this.param);
    }

    private void configParam(Object param){
        try{
            this.param = String.valueOf(
                new ObjectMapper().readTree(String.valueOf(param))
                                  .get("param"));
        }catch(Exception e){  this.statusCode = 401; }
    }

    private void configUrl(String isbn){
        try{
            if(!isbn.matches("[0-9]+") || isbn.length()!=13)
                throw new Exception();

            this.isbn = isbn;
            this.isbnKey = "ISBN:"+isbn;
            url = new URL(prefix + isbnKey + suffix);
            detailUrl = new URL(prefix + isbnKey + detailSuffix);

        }catch(Exception e){  this.statusCode = 400; }
    }

	public List<Stock> getStocks(){

        List<Stock> stocks = new ArrayList<Stock>();

        for(String isbn : isbnList){
            configUrl(isbn);
            stocks.add(getStock());
        }
 
        return stocks;
    }

	public Stock getStock(){

		Stock stock = null;

        try{
            if(this.statusCode!=200)
                throw new Exception();

            JsonNode book, bookDetail;
            String bookName="", bookAuthors="", thumbnail="", bookDescription="";
			book = new ObjectMapper().readTree(url).get(isbnKey);
            bookDetail = new ObjectMapper().readTree(detailUrl).get(isbnKey);
			
            if(book != null){
			    bookName = String.valueOf(book.get("title"));

			if(book.get("authors") != null && book.get("authors").isArray())
				for(JsonNode author : book.get("authors"))
					bookAuthors += String.valueOf(author.get("name")) + ", ";

            if(bookAuthors.length()>2)
                bookAuthors = bookAuthors.substring(0,bookAuthors.length()-2);

            if(book.get("cover") != null && book.get("cover").isObject()){
                if(book.get("cover").get("medium") != null)
					thumbnail = String.valueOf(book.get("cover").get("medium"));
                else if(book.get("cover").get("small") != null)
					thumbnail = String.valueOf(book.get("cover").get("small"));

                if(bookDetail.get("description") != null)
                    bookDescription = String.valueOf(bookDetail.get("description"));
            }	
                stock = new Stock(isbn,bookName,bookAuthors,thumbnail,bookDescription);
            }
            }catch(Exception e){}finally{return stock;}
	}

    public int getStatusCode(){
        return statusCode;
    }

}
