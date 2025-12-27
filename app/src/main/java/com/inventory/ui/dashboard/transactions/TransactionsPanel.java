package com.inventory.ui.dashboard.transactions;

import javax.swing.*;
import java.awt.*;

public class TransactionsPanel extends JPanel {
    public TransactionsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Transactions", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
