package Seating;

public class Show {
  private final String date;
  private final String show;
  private final String showID;

  /**
   * Test Constructor
   */
  public Show() {
    this.date = "01/01/1970";
    this.show = "TestShow";
    this.showID = "show123";
  }

  /**
   * Constructor
   * 
   * @param date the day of the show
   * @param show the show being played
   */
  public Show(String date, String show, String showID) {
    this.date = date;
    this.show = show;
    this.showID = showID;
  }

  /**
   * @return date
   */
  public String getDate() {
    return date;
  }

  /**
   * @return show
   */
  public String getShow() {
    return show;
  }

  /**
   * @return showID
   */
  public String getShowID() {
    return showID;
  }

  /**
   * Prints the info of a Show
   */
  public void printInfo() {
    System.out.println("{" + date + ", " + show + ", " + showID + "}");
  }
}
