package ExternalTeamsInterface;

public interface GroupBookings {
  public boolean checkAvailabilityForGroup(String showID, int groupSize);

  public void reserveGroupBooking(String groupDetails);

  public void finaliseGroupBooking(String bookingID);
}
