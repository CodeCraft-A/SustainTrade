package fxml_helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class ProfilePageController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    private String loggedInUserEmail;

    public void initialize() {
        // This would be set when the user logs in
        loggedInUserEmail = "natalieputri1@gmail.com"; // Example logged-in user email

        populateUserData();
    }

    private void populateUserData() {
        List<String[]> users = CsvUtils.readFromCsv();
        Optional<String[]> loggedInUser = users.stream()
                .filter(user -> user[3].equals(loggedInUserEmail))
                .findFirst();

        if (loggedInUser.isPresent()) {
            String[] userData = loggedInUser.get();
            firstNameField.setText(userData[0]);
            lastNameField.setText(userData[1]);
            emailField.setText(userData[3]);
            phoneField.setText(""); // Assuming phone number is not stored in the CSV
        }
    }
}
