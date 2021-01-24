package com.readers.thebookclub;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class TestApi {
 
    @Test
    public void testSetup() {}
    @Test
    public void checkStocksByIsbn() {

		Stock stock;

		List<String> list = new ArrayList<String>();
		list.add("9780060217860");
		list.add("9780789411464");
		list.add("9780806919317");
		list.add("9780875349343");
		list.add("9780893751159");
		list.add("9780689853944");
		list.add("9780866228312");
		list.add("9780911981568");
		list.add("9780816741342");

		for (String isbn : list){
			stock = new OpenlibraryApiGateway(isbn).getStock();
			System.out.println(stock.getIsbn());
			System.out.println(stock.getBookName());
			System.out.println(stock.getBookAuthors());
			System.out.println(stock.getThumbnail());
			System.out.println(stock.getBookDescription());
		}
    }

    @Test
    public void searchBookByIsbn() {

		List<Object> list = new ArrayList<Object>();
		list.add("{\"param\":\"9780747575443\"}");

		for(Object param : list)
		for (Stock stock : new OpenlibraryApiGateway(param).getStocks()){
			System.out.println(stock.getIsbn());
			System.out.println(stock.getBookName());
			System.out.println(stock.getBookAuthors());
			System.out.println(stock.getThumbnail());
			System.out.println(stock.getBookDescription());
		}
    }

	@Test
    public void checkSearch() {
	}
}