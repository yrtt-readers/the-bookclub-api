package com.readers.thebookclub.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readers.thebookclub.model.ActiveRegion;

public class GetActiveRegionsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LogManager.getLogger(GetActiveRegionsHandler.class);

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");
        // String userId = request.getPathParameters().get("userId");
        //System.out.println(userId);
        List<ActiveRegion> activeRegions = new ArrayList<>();
        try {
           
            LOG.debug("Connecting to database");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));
            LOG.debug("CONNECTION SUCESSFUL");
            preparedStatement = connection.prepareStatement("SELECT Region.region_id, Address.address_id, Address.post_code, Region.region_name" +
                                                            " FROM Address INNER JOIN Region ON Region.address_id2=Address.address_id" +
                                                            " WHERE Region.status_id2= 1");
            resultSet = preparedStatement.executeQuery();
            LOG.debug("Prepared statements OK");
            while (resultSet.next()) {
                ActiveRegion activeRegion = new ActiveRegion(resultSet.getString("region_id"),
                                                             resultSet.getString("address_id"),
                                                             resultSet.getString("post_code"),
                                                             resultSet.getString("region_name"));

                activeRegions.add(activeRegion);
            }
        }
        catch (Exception e) {
            LOG.error("Unable to query db for active regions");
        }            
        finally {
            closeConnection();
        }

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String responseBody = objectMapper.writeValueAsString(activeRegions);
            response.setBody(responseBody);
        } catch (JsonProcessingException e) {
            LOG.error("Unable to marshall activeRegions array", e);
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
