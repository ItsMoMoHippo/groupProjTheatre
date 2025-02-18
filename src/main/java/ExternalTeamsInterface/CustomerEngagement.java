package ExternalTeamsInterface;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.SQLException;

public interface CustomerEngagement {
    void capturePhysicalReview(String customerName, String customerEmail, String review, String showName) throws SQLException;
    void captureOnlineReview(String customerName, String customerEmail, String review, String showName);
    ArrayNode getAllReviews();
    ArrayNode getReviewsByShow(String showName);
}