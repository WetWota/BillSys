package scam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductManager {
    private final String csvFilePath;

    public ProductManager(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public void updateProductQuantitiesInCSV(List<Product> billDataList) {
        List<String[]> data = TableModelHelper.getCSVData(csvFilePath); // Read existing data

        // Update quantities using streams
        billDataList.forEach(product -> {
            for (String[] row : data) {
                if (Integer.parseInt(row[0]) == product.getId()) { // Assuming ID is in the first column
                    int currentQuantity = Integer.parseInt(row[3]); // Assuming quantity is in the fourth column
                    row[3] = String.valueOf(currentQuantity - product.getQuantity()); // Update the quantity
                    break; // Exit the loop once the product is found
                }
            }
        });

        writeDataToCSV(data);
    }

    private void writeDataToCSV(List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error updating CSV file: " + e.getMessage(), e);
        }
    }
}