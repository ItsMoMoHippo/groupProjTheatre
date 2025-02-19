package ExternalTeamsInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import DBIntegration.ExternalTeamDB.ExternalMySQLConnector;
import org.hibernate.annotations.processing.SQL;

public class TicketManager implements TicketSales {
    private ExternalMySQLConnector dbConnector;

    public TicketManager(ExternalMySQLConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Records ticket sale on database, and returns JSON fetch for update
     *
     * @param customerName customer name
     * @param seatNumber seat number
     * @param venue venue of event
     * @param dateTime date and time of event
     */
    @Override
    public void recordTicketSale(String customerName, String seatNumber, float price, String venue, String dateTime) throws SQLException {
        try {
            ResultSet rs = dbConnector.executeQuery("INSERT INTO tickets (Name, SeatNumber, Price, Venue, DateTime) VALUES ('"
                    + customerName + "', '" + seatNumber + "','" + price + "', '" + venue + "', '" + dateTime + "')");
            System.out.println("Ticket Recorded for" + customerName);
            fetchTicketsAsJson();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates ticket cancellation on database, and returns JSON fetch for update
     *
     * @param customerName name of customer to query
     * @param seatNumber seat number
     */
    @Override
    public void updateCancellation(String customerName, String seatNumber) throws SQLException {
        try {
            ResultSet rs = dbConnector.executeQuery("UPDATE tickets SET cancelled = TRUE WHERE Name = '"
                    + customerName + "' AND SeatNumber = '" + seatNumber + "'");
            System.out.println("Cancellation updated for " + customerName);
            fetchTicketsAsJson();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates customer special needs on database
     *
     * @param customerName name of customer to query
     * @param specialNeeds description of special need to update table
     */
    @Override
    public void updateSpecialNeeds(String customerName, String specialNeeds) throws SQLException {
        try {
            ResultSet rs = dbConnector.executeQuery("UPDATE tickets SET SpecialNeeds = '" + specialNeeds
                    + "' WHERE Name = '" + customerName + "'");
            System.out.println("Special needs updated for " + customerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all ticket data from database as JSON
     *
     * @return JSON data on ticket information
     */
    @Override
    public ArrayNode fetchTicketsAsJson() throws SQLException {
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT * FROM tickets");
            return dbConnector.resultSetToJSON(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ObjectMapper().createArrayNode();
        }
    }

    /**
     * Shows how many tickets are sold
     *
     * @return ticketSold       the amount of tickets sold
     * */
    @Override
    public int fetchTicketsSold() throws SQLException {
        try {
            ResultSet rs = dbConnector.executeQuery("SELECT COUNT(*) FROM tickets");
            int ticketsSold = 0;
            if (rs.next()) {
                ticketsSold = rs.getInt(1);
            }
            rs.close();
            return ticketsSold;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
