package scam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class TableModelHelper {

    public static List<String[]> getCSVData(String csvFilePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine(); // Read header, skip the first line

            if (line == null) {
                return null; // No data in file
            }

            // Read the rest of the CSV file (rows)
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
        }

        return data;
    }

    // Updated method to return a DefaultTableModel
    public static DefaultTableModel createTableModelFromCSV(String csvFilePath) {
    List<String[]> csvData = getCSVData(csvFilePath);

    if (csvData == null || csvData.isEmpty()) {
        return null;
    }

    // Use ArrayList<ArrayList<Object>> to handle mixed types
    ArrayList<ArrayList<Object>> dataList = new ArrayList<>();

    for (int i = 0; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        ArrayList<Object> rowList = new ArrayList<>();

        // Add the row index (starting from 1)
        rowList.add(i + 1); // Row index
        rowList.add(row[1]); // Product (String)

        // Convert price to double
        double price = Double.parseDouble(row[2]);
        rowList.add("P " + price); // Price (double)

        // Convert quantity to int
        int quantity = Integer.parseInt(row[3]);
        rowList.add(quantity); // Quantity (int)

        int ID = Integer.parseInt(row[0]);
        rowList.add(ID); 

        dataList.add(rowList);
    }

    // Column names, including the index column
    String[] columnNames = {"#", "Product", "Price", "Quantity", "ID"};

    // Create DefaultTableModel
    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Make cells uneditable
        }

        // Handle the mixed types in the table
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) return Integer.class; // Index is Integer
            if (columnIndex == 2) return Double.class ; // Price is double
            if (columnIndex == 3) return Integer.class; // Quantity is int
            return String.class; // Product is String
        }
    };

    // Add rows to the model
    for (ArrayList<Object> rowData : dataList) {
        model.addRow(rowData.toArray(Object[]::new));
    }

    return model;
}
    
}
