package ExternalTeamsInterface;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.SQLException;

public interface RoomBookings {
    void bookRoom(String roomType, String customerName, String date, String duration) throws SQLException;
    void cancelRoomBooking(String customerName, String roomType, String date) throws SQLException;
    ArrayNode fetchAvailableRoomsAsJSON(String date) throws SQLException;
    ArrayNode fetchBookingsAsJSON() throws SQLException;
    int returnRoomBookingCost(String roomType) throws SQLException;
}
