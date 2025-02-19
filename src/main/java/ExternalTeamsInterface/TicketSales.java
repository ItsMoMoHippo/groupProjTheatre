package ExternalTeamsInterface;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.SQLException;

public interface TicketSales {
    void recordTicketSale(String customerName, String seatNumber, float price, String venue, String dateTime) throws SQLException;
    void updateCancellation(String customerName, String seatNumber) throws SQLException;
    void updateSpecialNeeds(String customerName, String specialNeeds) throws SQLException;
    ArrayNode fetchTicketsAsJson() throws SQLException;
    int fetchTicketsSold() throws SQLException;
}