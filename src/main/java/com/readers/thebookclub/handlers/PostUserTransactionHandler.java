package com.readers.thebookclub.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readers.thebookclub.model.Transaction;

public class PostUserTransactionHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LogManager.getLogger(PostUserTransactionHandler.class);

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");
        int userId = Integer.parseInt(request.getPathParameters().get("userId"));
    
        String requestBody = request.getBody();

        ObjectMapper objMapper = new ObjectMapper();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        Map<String, String> headers = new HashMap<>();
		headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);
        
        try {
            Transaction t = objMapper.readValue(requestBody, Transaction.class);
           
            LOG.debug("Connecting to database");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));
            LOG.debug("CONNECTION SUCESSFUL");
            preparedStatement = connection.prepareStatement("INSERT INTO User_Request (user_id, isbn2, region_id3, req_type_id, status_id3) VALUES (?, ?, ?, ?, ?)");
            
            LOG.debug("Prepared statements OK");
            preparedStatement.setInt(1, userId);
            LOG.debug("Prepared statements OK");
            preparedStatement.setString(2, t.getIsbn());
            LOG.debug("Prepared statements OK");
            preparedStatement.setInt(3, t.getRegionId());
            LOG.debug("Prepared statements OK");
            preparedStatement.setInt(4, t.getRequestType());
            LOG.debug("Prepared statements OK");
            preparedStatement.setInt(5, 1);
            LOG.debug("Prepared statements OK");
            resultSet = preparedStatement.executeQuery();
            LOG.debug("Prepared statements OK");
            // while (resultSet.next()) {
            //     Transaction transaction = new Transation(resultSet.getString("region_id"));
            // }
        }
        catch (Exception e) {
            LOG.error("Unable to query db for user transaction");
        }            
        finally {
            closeConnection();
        }

        return response;
    }
    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            LOG.error("Unable to close connections to MySQL -{}", e.getMessage());
        }
    }
}
