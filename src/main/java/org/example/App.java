package org.example;

/* javafx imports */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import DBIntegration.*;

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

    // TODO: immediates updates with JSON
    // TODO: collect customer reviews
    // TODO: recordRefund(ticketId): Records the refund of a ticket to update financial figures.
    // TODO: anydb updates to be immediately shared

    launch(args);
  }

}
