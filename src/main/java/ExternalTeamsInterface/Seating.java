package ExternalTeamsInterface;

import java.util.List;

public interface Seating {
  public List<String> retrieveSeatingPlan(String showType);

  public List<String> checkUnavailableSeats(String showID);

  public List<String> getAvailableSeatsPerSection(String showID, String section);

  public void holdWheelchairRow(String showID);
}
