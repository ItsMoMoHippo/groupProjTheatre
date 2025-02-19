package Seating;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SeatingTest {

  /* Show testing */
  @Test
  void testShowDefaultConstructor() {
    Show show = new Show();
    assertEquals("01/01/1970", show.getDate());
    assertEquals("TestShow", show.getShow());
  }

  @Test
  void testShowParameterisedConstructor() {
    Show show = new Show("15/02/2025", "Hamlet");
    assertEquals("15/02/2025", show.getDate());
    assertEquals("Hamlet", show.getShow());
  }

  // --------------------------------------------------------------------
  /* Seat testing */
  @Test
  void testSeatDefaultConstructor() {
    Seat seat = new Seat();
    assertEquals('A', seat.returnRow());
    assertEquals(1, seat.returnNumber());
    assertEquals(false, seat.returnRestrictedView());
  }

  @Test
  void testSeatParameterisedConstructor() {
    Seat seat = new Seat('F', 7, true);
    assertEquals('F', seat.returnRow());
    assertEquals(7, seat.returnNumber());
    assertEquals(true, seat.returnRestrictedView());
  }

  // --------------------------------------------------------------------
  /* Ticket testing */
  @Test
  void testTicketDefaultConstructor() {
    Ticket ticket = new Ticket();
    assertEquals("ID123",ticket.returnID());
    assertNotNull(ticket.returnSeat());
    assertEquals('A',ticket.returnSeat().returnRow());
    assertEquals(1,ticket.returnSeat().returnNumber());
    assertEquals(false,ticket.returnSeat().returnRestrictedView());
    assertEquals("john",ticket.returnFirstName());
    assertEquals("doe",ticket.returnLastName());
    assertEquals("johndoe@email.com",ticket.returnEmail());
    assertNotNull(ticket.returnShow());
    assertEquals("01/01/1970",ticket.returnShow().getDate());
    assertEquals("TestShow",ticket.returnShow().getShow());
    assertEquals(0.0,ticket.returnPrice());
    assertEquals("01/01/1970",ticket.returnDatePurchased());
    assertEquals("00:00:00", ticket.returnTimePurchased());
  }

  @Test
  void testTicketParameterisedConstructor() {
    Ticket ticket = new Ticket("1d0ij", new Seat('F', 7, true), "Dave", "Jones", "DaveJones@email.com", "15/02/2025",
        "Hamlet", 10.0, "10/02/2025", "12:20:32");
    assertEquals("1d0ij", ticket.returnID());
    assertNotNull(ticket.returnSeat());
    assertEquals('F', ticket.returnSeat().returnRow());
    assertEquals(7, ticket.returnSeat().returnNumber());
    assertEquals(true, ticket.returnSeat().returnRestrictedView());
    assertEquals("Dave", ticket.returnFirstName());
    assertEquals("Jones", ticket.returnLastName());
    assertEquals("DaveJones@email.com", ticket.returnEmail());
    assertNotNull(ticket.returnShow());
    assertEquals("15/02/2025", ticket.returnShow().getDate());
    assertEquals("Hamlet", ticket.returnShow().getShow());
    assertEquals(7.00, ticket.returnPrice());
    assertEquals("10/02/2025", ticket.returnDatePurchased());
    assertEquals("12:20:32", ticket.returnTimePurchased());
  }
}
