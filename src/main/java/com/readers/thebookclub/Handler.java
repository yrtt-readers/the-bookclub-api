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

		if(context.getFunctionName().equals("the-bookclub-op-api-dev-searchBooks")){

			og = new OpenlibraryApiGateway(input.get("pathParameters"));

			return ApiGatewayResponse.builder()
				.setObjectBody(og.getStocks())
				.setStatusCode(og.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-op-api-dev-testStock")){

			og = new OpenlibraryApiGateway(("{\"param\":\"9780001006874\"}"));
			
			return ApiGatewayResponse.builder()
				.setObjectBody(og.getStocks())
				.setStatusCode(og.getStatusCode())
				.build();
		}
		else if(context.getFunctionName().equals("the-bookclub-op-api-dev-testInput")){
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
