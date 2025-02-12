package Seating;

public class Ticket {
  String ID;
  Seat seat;
  String firstName;
  String lastName;
  String email;
  String event;
  double price;

  /**
   * Default constructor that gives test information
   */
  public Ticket() {
    this.ID = "ID123";
    this.seat = new Seat();
    this.firstName = "john";
    this.lastName = "doe";
    this.email = "johndoe@email.com";
    this.event = "event123";
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
   * @param event     the event the ticket is for
   * @param price     the price of the ticket (discount applied if the seat
   *                  view is restricted)
   */
  public Ticket(String id, Seat seat, String firstName, String lastName, String email, String event, double price) {
    this.ID = id;
    this.seat = seat;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.event = event;
    // apply discount if seat has restrictedView
    if (seat.restrictedView == true) {
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
    System.out.println("{" + seat.position + "," + seat.restrictedView + "}");
    System.out.println(firstName + " " + lastName);
    System.out.println(email);
    System.out.println(event);
    System.out.println(price);
  }
}
