package Seating;

public class Ticket {
  private final String ID;
  private final Seat seat;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final Show show;
  private final double price;

  /**
   * Test Constructor
   */
  public Ticket() {
    this.ID = "ID123";
    this.seat = new Seat();
    this.firstName = "john";
    this.lastName = "doe";
    this.email = "johndoe@email.com";
    this.show = new Show();
    this.price = 0.0;
  }

  /**
   * Constructs Tickets with given parameters
   * 
   * @param id        the ticket id
   * @param seat      the seat for the ticket
   * @param firstName the forename of customer
   * @param lastName  the surname of customer
   * @param email     the associated email of the customer
   * @param show      the show for the ticket (date and what show)
   * @param price     the price of the ticket (discount applied if the seat
   *                  view is restricted)
   */
  public Ticket(String id, Seat seat, String firstName, String lastName, String email, String dateOfShow, String show,
      double price) {
    this.ID = id;
    this.seat = seat;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.show = new Show(dateOfShow, show);
    // apply discount if seat has restrictedView
    if (seat.returnRestrictedView()) {
      this.price = (price) * (0.7);
    } else {
      this.price = price;
    }
  }

  /**
   * prints information of a ticket
   */
  public void printTicket() {
    System.out.println(ID);
    seat.printInfo();
    System.out.println(firstName + " " + lastName);
    System.out.println(email);
    show.printInfo();
    System.out.println(price);
  }
}
