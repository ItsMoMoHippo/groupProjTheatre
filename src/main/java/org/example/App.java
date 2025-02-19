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

    // Connection with Admin creds
    MySQLConnector db = new MySQLConnector("in2033t07_a", "Uys45GIFOX8");

    try {
      db.connect();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      db.close();
    }

    launch(args);
  }

}
