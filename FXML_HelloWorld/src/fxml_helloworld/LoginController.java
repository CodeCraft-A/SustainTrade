    package fxml_helloworld;

    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.util.List;

    public class LoginController {

        @FXML
        private TextField emailField;
        @FXML
        private PasswordField passwordField;
        @FXML
        private Button signInButton;
        @FXML
        private Button signUpButton;

        @FXML
        private void handleSignInButtonAction() {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                showErrorDialog("Email and password are required.");
                return;
            }

            if (validateLogin(email, password)) {
                showInformationDialog("Login successful.");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainpage.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) emailField.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("SustainTrade Main Page");
                } catch (IOException e) {
                    showErrorDialog("Failed to load the main page. " + e.getMessage());
                }
            } else {
                showErrorDialog("Invalid email or password.");
            }
        }

        @FXML
        private void handleSignUpButtonAction() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("buatakun.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Create Account");
            } catch (IOException e) {
                showErrorDialog("Unable to load the create account page. Please try again later.");
            }
        }

        private boolean validateLogin(String email, String password) {
            List<String[]> users = CsvUtils.readFromCsv();

            for (String[] user : users) {
                if (user[3].equals(email) && user[4].equals(password)) {  // Assuming email is in index 3 and password is in index 4
                    return true;
                }
            }

            return false;
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
