package Seating;

public class Show {
  private final String date;
  private final String show;

  /**
   * Test Constructor
   */
  public Show() {
    this.date = "01/01/1970";
    this.show = "TestShow";
  }

  /**
   * Constructor
   * 
   * @param date the day of the show
   * @param show the show being played
   */
  public Show(String date, String show) {
    this.date = date;
    this.show = show;
  }

  /**
   * Prints the info of a Show
   */
  public void printInfo() {
    System.out.println("{" + date + ", " + show + "}");
  }
}
