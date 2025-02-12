package Seating;

public class Seat {
  String position;
  boolean restrictedView;

  /*
   * Test constructor
   */
  public Seat() {
    this.position = "A1";
    this.restrictedView = false;
  }

  /*
   * Actual constructor
   */
  public Seat(String position, boolean restrictedView) {
    this.position = position;
    this.restrictedView = restrictedView;
  }
}
