package Seating;

public class Seat {
  private final char row;
  private final int number;
  private final boolean restrictedView;

  /**
   * Test Constructor
   */
  public Seat() {
    this.row = 'A';
    this.number = 1;
    this.restrictedView = false;
  }

  /**
   * Constructor
   *
   * @param row            the row for the seat
   * @param number         the seat number in a row
   * @param restrictedView the seat has a restricted view (will have a discount
   *                       applied)
   */
  public Seat(char row, int number, boolean restrictedView) {
    this.row = row;
    this.number = number;
    this.restrictedView = restrictedView;
  }

  /**
   * @return restrictedView
   */
  public boolean returnRestrictedView() {
    return restrictedView;
  }

  /**
   * prints info
   */
  public void printInfo() {
    System.out.println("{" + row + number + ", " + restrictedView + "}");
  }
}
