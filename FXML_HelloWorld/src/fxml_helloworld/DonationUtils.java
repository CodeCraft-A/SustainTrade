package fxml_helloworld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DonationUtils {

    private static final String DONATION_FILE = "donations.csv";

    public static void saveDonation(String amount, boolean verified, String paymentMethod) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATION_FILE, true))) {
            writer.write(amount + "," + verified + "," + paymentMethod);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readDonations() {
        List<String[]> donations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DONATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] donation = line.split(",");
                donations.add(donation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donations;
    }
}
