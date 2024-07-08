package fxml_helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DonationController {

    @FXML
    private CheckBox checkBox25;

    @FXML
    private CheckBox checkBox50;

    @FXML
    private CheckBox checkBox100;

    @FXML
    private TextField customAmountField;

    @FXML
    private CheckBox verifyCheckBox;

    @FXML
    private Button donateButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        donateButton.setOnAction(event -> handleDonateButton());
        cancelButton.setOnAction(event -> handleCancelButton());
    }

    private void handleDonateButton() {
        if (!verifyCheckBox.isSelected()) {
            System.out.println("Please verify your donation.");
            return;
        }

        double donationAmount = 0;

        if (checkBox25.isSelected()) {
            donationAmount = 25;
        } else if (checkBox50.isSelected()) {
            donationAmount = 50;
        } else if (checkBox100.isSelected()) {
            donationAmount = 100;
        } else {
            try {
                donationAmount = Double.parseDouble(customAmountField.getText());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
                return;
            }
        }

        saveDonationToCsv(donationAmount);
    }

    private void handleCancelButton() {
        // Add your cancel button logic here
        System.out.println("Donation cancelled.");
    }

    private void saveDonationToCsv(double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("donations.csv", true))) {
            String donationEntry = LocalDateTime.now() + "," + amount;
            writer.write(donationEntry);
            writer.newLine();
            System.out.println("Donation saved: " + donationEntry);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the donation.");
            e.printStackTrace();
        }
    }
}
