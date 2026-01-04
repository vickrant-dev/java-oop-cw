package com.inventory.ui.dashboard.customer_management;

import javax.swing.*;
import java.awt.*;

public abstract class BaseCustomerForm extends JDialog {
    protected final JTextField nameField = new JTextField(20);
    protected final JTextField contactField = new JTextField(20);
    protected final JButton btnAction = new JButton();
    protected final JButton btnCancel = new JButton("Cancel");

    public BaseCustomerForm(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);

        setLayout(new BorderLayout());
        setSize(380, 220);
        setLocationRelativeTo(owner);

        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0;
        pnl.add(new JLabel("Name:"), g);
        g.gridx = 1; g.weightx = 1;
        pnl.add(nameField, g);

        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        pnl.add(new JLabel("Contact:"), g);
        g.gridx = 1; g.weightx = 1;
        pnl.add(contactField, g);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(btnAction);
        footer.add(btnCancel);

        add(pnl, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
    }
}