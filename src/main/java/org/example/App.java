package org.example;

/* javafx imports */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/* mysql imports */

/* our own imports */
import DBIntegration.InternalTeamDB.*;;

public class App extends Application {
  @Override
  public void start(Stage stage) {
    Label label = new Label("Hello, JavaFX with Maven!");
    StackPane root = new StackPane(label);
    Scene scene = new Scene(root, 400, 300);

    stage.setTitle("JavaFX Maven App");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {

    // TODO: track number of tickets sold
    // TODO: record booking costs for meeting and rehersal rooms
    // TODO: immediates updates with JSON
    // TODO: collect customer reviews
    // TODO: room booking, info on meeting rehearsal booking(booking date duration room id)
    // TODO: recordRefund(ticketId): Records the refund of a ticket to update financial figures.
    // TODO: getSalesSummary(showId):Totals and returns a summary of ticket sales (total tickets sold, revenue, discounts) for a particular show.
    // TODO: anydb updates to be immediately shared
    // TODO: JSON
    //

    launch(args);
  }

}
