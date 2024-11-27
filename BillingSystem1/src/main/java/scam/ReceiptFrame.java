package scam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReceiptFrame extends JFrame {
    private JTextArea receiptTextArea;
    
    public ReceiptFrame(List<Product> billDataList, double totalAmount, double cashReceived, double change) {
        setTitle("Receipt");
        setSize(450, 540);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        
        receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(false);
        receiptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Monospaced font for better alignment
        
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Receipt\n");
        receiptBuilder.append("--------------------------------------------------------\n");
        
        for (Product product : billDataList) {
            receiptBuilder.append(String.format("%-10s %-20s %5.2f %5d %10.2f\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getTotalPrice()));
        }
        
        receiptBuilder.append("--------------------------------------------------------\n");
        receiptBuilder.append(String.format("Total Amount: %.2f\n", totalAmount));
        receiptBuilder.append(String.format("Cash Received: %.2f\n", cashReceived));
        receiptBuilder.append(String.format("Change: %.2f\n", change));
        receiptBuilder.append("--------------------------------------------------------\n");
        receiptBuilder.append("Thank you for your purchase!\n");
        
        receiptTextArea.setText(receiptBuilder.toString());
        
        JScrollPane scrollPane = new JScrollPane(receiptTextArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });
        
        // Add the button to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}