package com.readers.thebookclub;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: {}", input);

		// Response responseBody = new Response("Hello Readers!!", input);

		Stock s1 = new Stock("9780060217860",
		3,
		"Wider Than the Sky",
		"Scott Elledge",
		"CR0 0ZW");
		Stock s2 = new Stock("9780789411464",
		2,
		"Mad Jack",
		"Susan Mayes",
		"RH4 1EW");

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(s1);
		stocks.add(s2);

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(stocks)
				// .setObjectBody(responseBody)
				// .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}
}
