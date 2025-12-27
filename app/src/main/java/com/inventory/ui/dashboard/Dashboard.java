package com.inventory.ui.dashboard;

import com.inventory.ui.dashboard.customer_management.CustomerPanel;
import com.inventory.ui.dashboard.product_management.ProductPanel;
import com.inventory.ui.dashboard.supplier_management.SupplierPanel;
import com.inventory.ui.dashboard.transactions.TransactionsPanel;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel contentPanel;

    public Dashboard() {
        setTitle("Inventory Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // this is done to prevent unresponsive app window.
        setLayout(new BorderLayout());

        // import sidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST); // positioning it to the left

        // content section
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER); // positioning the content in the center

        // default view being managing products
        showPanel(new ProductPanel());

        // sidebar item actions

        // Typically we use addActionListener to perform an event when a button is clicked.
        // But, from newer java versions we can use something called "the lambda syntax" only because
        // addActionListener consists of only ONE method inside it. The addActionListener is actually
        // an interface and an interface with a single method is called a "functional interface" where
        // instead of writing it all, we could simply call the fn using a lambda syntax like the following:
        // "e -> callSomeFunction( ...params )"

        sidebar.setProductButtonAction(e -> showPanel(new ProductPanel()));
        sidebar.setCustomerButtonAction(e -> showPanel(new CustomerPanel()));
        sidebar.setSupplierButtonAction(e -> showPanel(new SupplierPanel()));
        sidebar.setTransactionButton(e -> showPanel(new TransactionsPanel()));
    }

    private void showPanel(JPanel panel) {
        // panel here is the panel we want to show when we click on any item on the sidebar list
        contentPanel.removeAll(); // emptying component list before adding anything.
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate(); // similar to refreshing the layout to render the newly added components.
        contentPanel.repaint(); // responsible for refreshing state to show the new data from components.
    }
}
