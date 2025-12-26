package com.inventory.ui.dashboard.customer_management;

import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel {
    public CustomerPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Customer Management", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
