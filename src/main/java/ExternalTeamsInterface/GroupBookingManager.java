package ExternalTeamsInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

import DBIntegration.ExternalTeamDB.ExternalMySQLConnector;

public abstract class GroupBookingManager implements GroupBookings {
  private ExternalMySQLConnector externalDBConnector;

  public GroupBookingManager(ExternalMySQLConnector externalDBConnector) {
    this.externalDBConnector = externalDBConnector;
  }

  @Override
  public boolean checkAvailabilityForGroup(String showID, int groupSize) {
    try {
      ResultSet rs = externalDBConnector.executeQuery(
          "SELECT COUNT(*) AS availableSeats FROM seats WHERE ShowID = '" + showID + "' AND isBooked = FALSE");
      if (rs.next()) {
        return rs.getInt("availableSeats") >= groupSize;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void reserveGroupBooking(String groupDetails) {
    try {
      externalDBConnector.executeQuery("INSERT INTO group_bookings (GroupDetails) VALUES ('" + groupDetails + "')");
      System.out.println("Group bookings reserved: " + groupDetails);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void finaliseGroupBooking(String bookingID) {
    try {
      externalDBConnector
          .executeQuery("UPDATE group_bookings SET Confirmed = TRUE WHERE BookingId = '" + bookingID + "'");
      System.out.println("Group booking finalised for Booking ID: " + bookingID);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
