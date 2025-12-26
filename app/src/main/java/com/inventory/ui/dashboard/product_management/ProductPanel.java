package com.inventory.ui.dashboard.product_management;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {
    public ProductPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Product Management", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
