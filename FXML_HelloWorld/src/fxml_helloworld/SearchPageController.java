package fxml_helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SearchPageController {

    @FXML
    private void handleDonateButtonAction1(ActionEvent event) throws IOException {
        loadDonationPage(event);
    }

    @FXML
    private void handleDonateButtonAction2(ActionEvent event) throws IOException {
        loadDonationPage(event);
    }

    // Add more methods for other buttons as needed

    private void loadDonationPage(ActionEvent event) throws IOException {
        Parent donationPage = FXMLLoader.load(getClass().getResource("Donation.fxml"));
        Scene donationScene = new Scene(donationPage);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(donationScene);
        window.show();
    }
}
