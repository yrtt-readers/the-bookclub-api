package com.readers.thebookclub;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class TestApi {

	private void printStocks(List<Object> list){
		for(Object param : list){
		OpenlibraryApiGateway og =
			new OpenlibraryApiGateway(param);
		if(og.getStatusCode()!=200)
			System.out.println(ErrorHandler.message.get(og.getStatusCode()));
		else{
			for (Stock stock : og.getStocks()){
			if(og.getStatusCode()==200 && stock!=null){
				System.out.println(stock.getIsbn());
				System.out.println(stock.getBookName());
				System.out.println(stock.getBookAuthors());
				System.out.println(stock.getThumbnail());
				System.out.println(stock.getBookDescription());
			}
		}
		}
		}
	}

    @Test
    public void testSetup() {}
    @Test
    public void checkStocksByIsbn() {

		List<Object> list = new ArrayList<Object>();
		list.add("{\"param\":\"9780060217860\"}");
		list.add("{\"param\":\"9780789411464\"}");
		list.add("{\"param\":\"9780806919317\"}");
		list.add("{\"param\":\"9780875349343\"}");
		list.add("{\"param\":\"9780893751159\"}");
		list.add("{\"param\":\"9780689853944\"}");
		list.add("{\"param\":\"9780866228312\"}");
		list.add("{\"param\":\"9780911981568\"}");
		list.add("{\"param\":\"9780816741342\"}");
		printStocks(list);
    }

    @Test
    public void searchBookByIsbn() {

		List<Object> list = new ArrayList<Object>();
		list.add("{\"param\":\"9780747575443\"}");
		printStocks(list);
    }

	@Test
    public void searchBook() {
		List<Object> list = new ArrayList<Object>();
		list.add("{\"param\":\"Charlie and the chocolate factory\"}");
		list.add("{\"param\":\"Alice's Adventures in Wonderland\"}");
		list.add("{\"param\":\"Harry Potter\"}");
		printStocks(list);
	}
}