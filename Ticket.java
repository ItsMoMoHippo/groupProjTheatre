public class Ticket {
    String firstName;
    String lastName;
    String email;
    String seatPos;
    String ticketID;

    Ticket(String firstName,String lastName, String email, String seatPos, String ticketID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.seatPos = seatPos;
        this.ticketID = ticketID;
    }

    void printInfo(){
        System.out.println(firstName+" "+lastName);
        System.out.println(email);
        System.out.println(seatPos);
        System.out.println(ticketID);
    }
}
