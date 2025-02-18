package ExternalTeamsInterface;

import DBIntegration.MySQLConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomManager implements RoomBookings{
    private MySQLConnector dbConnector;

    public RoomManager(MySQLConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Updates database on new booking, and calls JSON fetch of bookings as an update
     * @param roomType room type to be booked
     * @param customerName customer name
     * @param date date of booking
     * @param duration duration of booking
     */
    @Override
    public void bookRoom(String roomType, String customerName, String date, String duration) {
        try {
            dbConnector.executeQuery("INSERT INTO roomBookings (roomType, Name, date, duration) VALUES ('"
                    + roomType + "', '" + customerName + "', '" + date + "', " + duration + ")");
            System.out.println("Room booked for " + customerName);
            fetchBookingsAsJSON();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates database on room cancellation, and will call JSON fetch of available rooms as an update
     * @param customerName customer name
     * @param roomType type of room
     * @param date date of booking
     */
    @Override
    public void cancelRoomBooking(String customerName, String roomType, String date) {
        try {
            dbConnector.executeQuery("DELETE FROM roomBookings WHERE Name = '"
                    + customerName + "' AND roomType = '" + roomType + "' AND Date = '" + date + "'");
            System.out.println("Booking for " + customerName + " cancelled.");
            fetchAvailableRoomsAsJSON(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all available rooms from database
     * @return JSON result of available rooms
     */
    @Override
    public ArrayNode fetchAvailableRoomsAsJSON(String date) {
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT * FROM rooms WHERE roomID NOT IN "
                    + "(SELECT roomID FROM roomBookings WHERE date = '" + date + "')");
            return dbConnector.resultSetToJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ObjectMapper().createArrayNode();
        }
    }

    /**
     * Returns all bookings from database
     * @return JSON result of bookings
     */
    @Override
    public ArrayNode fetchBookingsAsJSON() {
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT * FROM room_bookings");
            return dbConnector.resultSetToJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ObjectMapper().createArrayNode();
        }
    }
}
