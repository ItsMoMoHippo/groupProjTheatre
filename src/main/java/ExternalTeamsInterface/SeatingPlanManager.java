package ExternalTeamsInterface;

import DBIntegration.ExternalTeamDB.ExternalMySQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class SeatingPlanManager implements Seating {
  private ExternalMySQLConnector externalDBConnector;

  public SeatingPlanManager(ExternalMySQLConnector externalDBConnector) {
    this.externalDBConnector = externalDBConnector;
  }

  /**
   * Retrieves the seating plan
   *
   * @param showType the type of show
   * @return seatingPlan the seating plan
   */
  @Override
  public List<String> retrieveSeatingPlan(String showType) {
    List<String> seatingPlan = new ArrayList<>();
    try {
      ResultSet rs = externalDBConnector
          .executeQuery("SELECT SeatNumber FROM seating_plans WHERE ShowType = '" + showType + "'");
      while (rs.next()) {
        seatingPlan.add(rs.getString("SeatNumber"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return seatingPlan;
  }

  /**
   *
   * */
  @Override
  public List<String> checkUnavailableSeats(String showID) {
    List<String> unavailableSeats = new ArrayList<>();
    try {
      ResultSet rs = externalDBConnector.executeQuery(
          "SELECT SeatNumber FROM seats WHERE ShowID  = '" + showID + "' AND (isBooked = TRUE OR isHeld = TRUE)");
      while (rs.next()) {
        unavailableSeats.add(rs.getString("SeatNumber"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return unavailableSeats;
  }

  @Override
  public List<String> getAvailableSeatsPerSection(String showID, String section) {
    List<String> availableSeats = new ArrayList<>();
    try {
      ResultSet rs = externalDBConnector.executeQuery("SELECT SeatNumber FROM seats WHERE ShowID = '" + showID
          + "' AND Section = '" + section + "' AND isBooked = FALSE");
      while (rs.next()) {
        availableSeats.add(rs.getString("SeatNumber"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return availableSeats;
  }

  @Override
  public void holdWheelchairRow(String showID) {
    try {
      externalDBConnector
          .executeQuery("UPDATE seats SET isHeld = TRUE WHERE ShowID = '" + showID + "' AND RowType = 'Wheelchair'");
      System.out.println("Wheelchair row is held for " + showID);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
