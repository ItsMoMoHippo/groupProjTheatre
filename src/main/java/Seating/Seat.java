package Seating;

public class Seat {
  String position;
  boolean restrictedView;

  /**
   * Test constructor
   */
  public Seat() {
    this.position = "A1";
    this.restrictedView = false;
  }

  /**
   * Actual Constructor
   *
   * @param position       the position where the seat is in the venue
   * @param restrictedView the seat has a restricted view (will have a discount
   *                       applied)
   */
  public Seat(String position, boolean restrictedView) {
    this.position = position;
    this.restrictedView = restrictedView;
  }

  /**
   * prints seat information
   * */
  public void printSeat(){
    System.out.println(position);
    System.out.println(restrictedView);
  }
}
