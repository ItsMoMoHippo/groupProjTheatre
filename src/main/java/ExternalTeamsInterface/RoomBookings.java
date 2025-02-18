package ExternalTeamsInterface;

import com.fasterxml.jackson.databind.node.ArrayNode;

public interface RoomBookings {
    void bookRoom(String roomType, String customerName, String date, String duration);
    void cancelRoomBooking(String customerName, String roomType, String date);
    ArrayNode fetchAvailableRoomsAsJSON(String date);
    ArrayNode fetchBookingsAsJSON();
}
