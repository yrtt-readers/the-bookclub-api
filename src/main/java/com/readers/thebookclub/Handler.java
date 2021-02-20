package com.readers.thebookclub;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		LOG.info("received: {}", input);
		OpenLibraryApiGateway og;

		switch (context.getFunctionName()){
			case "the-bookclub-op-api-dev-searchBooks":
				og = new OpenLibraryApiGateway(input.get("pathParameters"));
				return ApiGatewayResponse.builder()
						.setObjectBody(og.getStocks())
						.setStatusCode(og.getStatusCode())
						.build();
			case "the-bookclub-op-api-dev-testStock":
				og = new OpenLibraryApiGateway(("{\"param\":\"9780001006874\"}"));
				return ApiGatewayResponse.builder()
						.setObjectBody(og.getStocks())
						.setStatusCode(og.getStatusCode())
						.build();
			case "the-bookclub-op-api-dev-testInput":
				return ApiGatewayResponse.builder()
						.setObjectBody(String.valueOf(input.get("pathParameters")))
						.setStatusCode(200)
						.build();
			default:
				return ApiGatewayResponse.builder()
						.setObjectBody("other")
						.setStatusCode(200)
						.build();
		}
	}
}
