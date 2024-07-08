package fxml_helloworld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private static final String CSV_FILE_PATH = "users.csv";

    public static void writeToCsv(String[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(String.join(",", data));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readFromCsv() {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                records.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static String[] getUserByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 3 && data[3].equals(email)) {
                    return data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
