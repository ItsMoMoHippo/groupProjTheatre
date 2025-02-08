import UI.*;

public class Main {
    public static void main(String[] args){
        Ticket ticket = new Ticket("John", "Smith", "BLHA@gsoadns.com", "3z", "98yas9hdub");
        System.out.println(ticket.firstName+" " +ticket.lastName);
        System.out.println(ticket.email);
        System.out.println(ticket.seatPos);
        System.out.println(ticket.ticketID);
    }
}
