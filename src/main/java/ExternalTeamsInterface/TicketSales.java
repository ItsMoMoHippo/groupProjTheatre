package ExternalTeamsInterface;

import com.fasterxml.jackson.databind.node.ArrayNode;

public interface TicketSales {
    void recordTicketSale(String customerName, String seatNumber, float price, String venue, String dateTime);
    void updateCancellation(String customerName, String seatNumber);
    void updateSpecialNeeds(String customerName, String specialNeeds);
    ArrayNode fetchTicketsAsJson();
}