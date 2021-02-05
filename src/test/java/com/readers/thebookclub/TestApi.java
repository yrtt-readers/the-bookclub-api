package com.readers.thebookclub;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class TestApi {

	private final String[] isbn = {"9780060217877","9780060217860"};
	private final String[] bookName = {"Wider than the sky","Wider than the sky"};
	private final String[] bookAuthors = {"Scott Elledge","Scott Elledge"};
	private final String isbn2 = "9780060217860";
	private final String bookName2 = "Wider than the sky";
	private final String bookAuthors2 = "Scott Elledge";

	private void printStocks(List<Object> list){
		for(Object param : list){
		OpenlibraryApiGateway og =
			new OpenlibraryApiGateway(param);
		if(og.getStatusCode()!=200)
			System.out.println(ErrorHandler.message.get(og.getStatusCode()));
		else{			
				int count = 0;
			for (Stock stock : og.getStocks()){
			if(og.getStatusCode()==200 && stock!=null){
				// if(count>0)
				// 	return;
		        assertEquals(isbn[count], stock.getIsbn());
        		assertEquals(bookName[count], stock.getBookName());
        		assertEquals(bookAuthors[count], stock.getBookAuthors());
				count++;
				}
			}

			// for (Stock stock : og.getStocks()){
			// if(og.getStatusCode()==200 && stock!=null){
				// System.out.println(stock.getIsbn());
				// System.out.println(stock.getBookName());
				// System.out.println(stock.getBookAuthors());
				// System.out.println(stock.getThumbnail());
				// System.out.println(stock.getBookDescription());
        // assertEquals(isbn1, stock.getIsbn());
        // assertEquals(bookName1, stock.getBookName());
        // assertEquals(bookAuthors1, stock.getBookAuthors());
        // assertEquals(, stock.get());
        // assertEquals(, stock.get());
        // assertEquals(, stock.get());
			// 	}
			// }
		}
		}
	}

    @Test
    public void testSetup() {}
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