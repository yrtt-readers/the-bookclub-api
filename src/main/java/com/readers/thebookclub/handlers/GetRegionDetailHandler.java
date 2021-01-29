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
import com.readers.thebookclub.model.Region;

public class GetRegionDetailHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LogManager.getLogger(GetRegionDetailHandler.class);

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");
        
        String regionId = request.getPathParameters().get("regionId");
        //System.out.println(userId);
        List<Region> regions = new ArrayList<>();
        try {
           
            LOG.debug("Connecting to database");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));
            LOG.debug("CONNECTION SUCESSFUL");
            preparedStatement = connection.prepareStatement("SELECT" +
                                                                " Region.region_id," +
                                                                " Region.region_name," +
                                                                " Region.book_collection_message, " +
                                                                " Region.book_donation_message, " +
                                                                " Address.address_id, " +
                                                                " Address.house_number, " +
                                                                " Address.street, " +
                                                                " Address.city, " +
                                                                " Address.county, " +
                                                                " Address.country, " +
                                                                " Address.post_code " +
                                                            " FROM Address" +
                                                            " INNER JOIN Region ON Region.address_id2 = Address.address_id" +
                                                            " WHERE Region.status_id2 = 1 AND Region.region_id = ?");
            preparedStatement.setString(1, regionId);                                   
            resultSet = preparedStatement.executeQuery();

            LOG.debug("Prepared statements OK");
            while (resultSet.next()) {
                Region region = new Region(resultSet.getString("region_id"),
                                                resultSet.getString("region_name"),
                                                resultSet.getString("book_collection_message"),
                                                resultSet.getString("book_donation_message"),
                                                resultSet.getString("address_id"),
                                                resultSet.getString("house_number"),
                                                resultSet.getString("street"),
                                                resultSet.getString("city"),
                                                resultSet.getString("county"),
                                                resultSet.getString("country"),
                                                resultSet.getString("post_code"));

                regions.add(region);
            }
        }
        catch (Exception e) {
            LOG.error("Unable to query db for region detail");
        }            
        finally {
            closeConnection();
        }

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String responseBody = objectMapper.writeValueAsString(regions);
            response.setBody(responseBody);
        } catch (JsonProcessingException e) {
            LOG.error("Unable to marshall regions array", e);
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
