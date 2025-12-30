package com.inventory.ui.dashboard.product_management;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {
    public ProductPanel() {
        setLayout(new BorderLayout());
        //add(new JLabel("Product Management", SwingConstants.CENTER), BorderLayout.CENTER);


        JLabel title = new JLabel("Product Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(0, 70, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);
        add(new ManageProducts(), BorderLayout.CENTER);



    }
}
