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
		OpenlibraryApiGateway og;
		DatabaseApiGateway dg;

		if(context.getFunctionName().equals("the-bookclub-api-dev-getStocks")){

			og = new OpenlibraryApiGateway(input.get("pathParameters"));

			return ApiGatewayResponse.builder()
				.setObjectBody(og.getStocks())
				.setStatusCode(og.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-api-dev-setStocks")){

			dg = new DatabaseApiGateway(input.get("pathParameters"));

			return ApiGatewayResponse.builder()
				.setObjectBody(dg)
				.setStatusCode(dg.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-api-dev-searchBooks")){

			og = new OpenlibraryApiGateway(input.get("pathParameters"));

			return ApiGatewayResponse.builder()
				.setObjectBody(og.getStocks())
				.setStatusCode(og.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-api-dev-testStock")){

			og = new OpenlibraryApiGateway(("{\"param\":\"9780689853944\"}"));
			
			return ApiGatewayResponse.builder()
				.setObjectBody(og.getStocks())
				.setStatusCode(og.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-api-dev-testInput")){
			return ApiGatewayResponse.builder()
				.setObjectBody(String.valueOf(input.get("pathParameters")))
				.setStatusCode(200)
				.build();
		}
		else {
			return ApiGatewayResponse.builder()
				.setObjectBody("other")
				.setStatusCode(200)
				.build();
		}
	}
}
