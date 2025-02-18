package ExternalTeamsInterface;

import DBIntegration.MySQLConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerReviewManager extends ReviewManager implements CustomerEngagement {
    private MySQLConnector dbConnector;
    private ObjectNode reviewData;

    public CustomerReviewManager(String customerName, String customerEmail, MySQLConnector dbConnector) {
        super(customerName, customerEmail);
        this.dbConnector = dbConnector;
    }

    /**
     * Updates database on physical reviews collected
     * @param customerName customer name
     * @param customerEmail email
     * @param review customer review data
     * @param showName Name of show related to review
     */
    @Override
    public void capturePhysicalReview(String customerName, String customerEmail, String review, String showName) throws SQLException {
        dbConnector.connect();
        try {
            dbConnector.executeQuery("INSERT INTO PhysicalReviews (Name, Email, Review, ShowName) VALUES ('"
                    + customerName + "', '" + customerEmail + "', '" + review + "', '" + showName + "')");
            System.out.println("Physical review captured for " + customerName + " on " + showName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnector.close();
    }

    /**
     * Updates database on online reviews
     * @param customerName customer name
     * @param customerEmail email
     * @param review customer review data
     * @param showName Name of show related to review
     */
    @Override
    public void captureOnlineReview(String customerName, String customerEmail, String review, String showName) {
        try {
            dbConnector.executeQuery("INSERT INTO OnlineReviews (Name, Email, Review, ShowName) VALUES ('"
                    + customerName + "', '" + customerEmail + "', '" + review + "', '" + showName + "')");
            System.out.println("Online review captured for " + customerName + " on " + showName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries database for all reviews
     * @return Array of reviews in JSON format
     */
    @Override
    public ArrayNode getAllReviews() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode reviewsArray = objectMapper.createArrayNode();
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT * FROM PhysicalReviews UNION SELECT * FROM OnlineReviews");
            while (rs.next()) {
                ObjectMapper objectMapperNode = new ObjectMapper();
                ObjectNode reviewNode = objectMapperNode.createObjectNode();
                reviewNode.put("ReviewID", rs.getInt("ReviewID"));
                reviewNode.put("Name", rs.getString("Name"));
                reviewNode.put("Email", rs.getString("Email"));
                reviewNode.put("Review", rs.getString("Review"));
                reviewNode.put("ShowName", rs.getString("ShowName"));
                reviewsArray.add(reviewNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewsArray;
    }

    /**
     * Queries database for all reviews of the filtered show
     * @param showName Name of show to filter reviews
     * @return Array of reviews by filtered show in JSON format
     */
    @Override
    public ArrayNode getReviewsByShow(String showName) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode reviewsArray = objectMapper.createArrayNode();
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT * FROM physical_reviews WHERE show_name = '" + showName + "' "
                    + "UNION SELECT * FROM online_reviews WHERE show_name = '" + showName + "'");
            while (rs.next()) {
                ObjectMapper objectMapperNode = new ObjectMapper();
                ObjectNode reviewNode = objectMapperNode.createObjectNode();
                reviewNode.put("ReviewID", rs.getInt("ReviewID"));
                reviewNode.put("Name", rs.getString("Name"));
                reviewNode.put("Email", rs.getString("Email"));
                reviewNode.put("Review", rs.getString("Review"));
                reviewNode.put("ShowName", rs.getString("ShowName"));
                reviewsArray.add(reviewNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewsArray;
    }

    /**
     * Default review submission
     * @param review Review information
     * @param showName Name of show to review
     */
    @Override
    public void submitReview(String review, String showName) {
        // By default, assuming online review submission.
        captureOnlineReview(customerName, customerEmail, review, showName);
    }
}
