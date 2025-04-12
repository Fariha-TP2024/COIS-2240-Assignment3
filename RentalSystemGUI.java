import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RentalSystemGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vehicle Rental System");

        // Create UI components
        Button rentButton = new Button("Rent Vehicle");
        Button returnButton = new Button("Return Vehicle");
        TextField licensePlateField = new TextField();
        TextField customerNameField = new TextField();
        ListView<String> vehicleList = new ListView<>();

        // Layout setup
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Vehicle License Plate: "), licensePlateField,
                new Label("Customer Name: "), customerNameField,
                vehicleList, rentButton, returnButton
        );

        // Rent vehicle button functionality
        rentButton.setOnAction(e -> rentVehicle(licensePlateField.getText(), customerNameField.getText()));

        // Return vehicle button functionality
        returnButton.setOnAction(e -> returnVehicle(licensePlateField.getText(), customerNameField.getText()));

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rentVehicle(String licensePlate, String customerName) {
        // Handle vehicle rental logic here
        System.out.println("Renting vehicle: " + licensePlate + " to customer: " + customerName);
    }

    private void returnVehicle(String licensePlate, String customerName) {
        // Handle vehicle return logic here
        System.out.println("Returning vehicle: " + licensePlate + " from customer: " + customerName);
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
