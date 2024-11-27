

import scam.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    // Method to read CSV and return List of ArrayList<Object> to handle mixed data types with row numbers
    public static List<ArrayList<Object>> readCSV(String filePath) {
        List<ArrayList<Object>> data = new ArrayList<>();
        int rowIndex = 1;  // Start list numbering from 1

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip the header line

            if (line == null) {
                return null; // No data in file
            }

            // Read the rest of the CSV file (rows)
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                ArrayList<Object> rowList = new ArrayList<>();
                rowList.add(rowIndex++);

                    // Prints data      //Assuming columns are: Product (String), Price (double), Quantity (int)
                    rowList.add(row[1]);
                    double price = Double.parseDouble(row[2]);
                    rowList.add(price + " Petot");
                    int quantity = Integer.parseInt(row[3]);
                    rowList.add(quantity);
                    int ID = Integer.parseInt(row[0]);
                    rowList.add(ID);

                // Add row to the data list
                data.add(rowList);
            }
        } catch (IOException e) {
        }

        return data;
    }
}
