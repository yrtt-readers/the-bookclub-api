package com.readers.thebookclub;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.URL;
import java.net.URI;

import org.apache.hc.client5.http.*;
// import org.apache.hc.client5.http.HttpRequest;
// import org.apache.hc.client5.http.HttpResponse;
// import org.apache.hc.client5.http.HttpResponse.BodyHandlers;

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

    private OpenlibraryApiGateway (){

        this.prefix = "https://openlibrary.org/api/books?bibkeys=";
        this.searchPrefix = "http://openlibrary.org/search.json?q=";
        this.suffix = "&jscmd=data&format=json";
        this.detailSuffix = "&jscmd=details&format=json";
        this.statusCode = 200;
    }
    public OpenlibraryApiGateway (Object param){

        this();
        isbnList = new ArrayList<String>();

        try{
            this.param = new ObjectMapper()
                            .readTree(String.valueOf(param))
                            .get("param").textValue();
            if(this.param.matches("[0-9]+") && this.param.length()==13)
                isbnList.add(this.param);
            // else{
    		// 	URI uri = new URI(searchPrefix);
	    	//     HttpClient client = HttpClient.newHttpClient();
		    //     HttpRequest request = HttpRequest.newBuilder(
			//     	new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),
			// 		"{\"query\":\"" + this.param + "\"}",uri.getFragment()))
          	// 		.build();											   
		    //     HttpResponse<InputStream> response = client.send(request,BodyHandlers.ofInputStream());
    		//     BufferedReader br = new BufferedReader(new InputStreamReader(response.body()));
            //     if(response.statusCode()==200){
			// 	System.out.println(String.valueOf(response.body()));
    	    // 		while(br.readLine()!=null)
        	//     		isbnList.add(br.readLine());
            //     }
            //     else
            //         this.statusCode = 503;
            // }
            
		    }catch(Exception e){
                if(this.statusCode!=401)
                    this.statusCode = 503; 
            }
    }

	public List<Stock> getStocks(){

        List<Stock> stocks = new ArrayList<Stock>();

        for(String isbn : isbnList){
            stocks.add(getStock(isbn));
        } 
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
            bookDetail = new ObjectMapper().readTree(this.detailUrl).get(this.isbnKey);
			
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
            }catch(Exception e){
                if(this.statusCode!=400)
                    this.statusCode=400;
            }finally{return stock;}
	}

    public int getStatusCode(){
        return statusCode;
    }

}
