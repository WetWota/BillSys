/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



import javax.swing.table.DefaultTableModel;

public class BillingSystem1 {
    public static void main(String[] args) {
        String csvFilePath = "Data/product.csv";
        DefaultTableModel model = TableModelHelper.createTableModelFromCSV(csvFilePath);
        
        if (model != null) {
            // Pass the model to the UI constructor
            new BillingUI(model).setVisible(true);
        } else {
            System.out.println("Error creating table model from CSV.");
        }
    }
}
