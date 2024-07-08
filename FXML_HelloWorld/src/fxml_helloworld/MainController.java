package fxml_helloworld;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private Button searchButton;

    @FXML
    private Button DonateButton;

    @FXML
    private Button profileButton;

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        try {
            Parent searchPageParent = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
            Scene searchPageScene = new Scene(searchPageParent);
            
            // Get the current stage (window) and set the new scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(searchPageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDonateButtonAction(ActionEvent event) {
        try {
            Parent DonationPageParent = FXMLLoader.load(getClass().getResource("Donation.fxml"));
            Scene donationPageScene = new Scene(DonationPageParent);
            
            // Get the current stage (window) and set the new scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(donationPageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleProfileButtonAction(ActionEvent event) {
        try {
            Parent ProfilePageParent = FXMLLoader.load(getClass().getResource("Profilepage.fxml"));
            Scene profilePageScene = new Scene(ProfilePageParent);
            
            // Get the current stage (window) and set the new scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(profilePageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
