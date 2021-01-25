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