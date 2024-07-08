package fxml_helloworld;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class CreateAccountController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField registerAsField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private void handleCreateAccountButtonAction() {
        // Dapatkan data dari field
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String registerAs = registerAsField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || registerAs.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showErrorDialog("All fields are required.");
            return;
        }

        // Simpan data ke CSV
        String[] userData = { firstName, lastName, registerAs, email, password };
        CsvUtils.writeToCsv(userData);

        showInformationDialog("Account created successfully.");
        navigateToLogin();
    }

    private void navigateToLogin() {
        try {
            System.out.println("Navigating to Login.fxml...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_helloworld/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Navigation to Login.fxml successful.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Login.fxml: " + e.getMessage());
        }
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInformationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
