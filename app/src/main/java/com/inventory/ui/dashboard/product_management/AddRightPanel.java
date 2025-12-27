package com.inventory.ui.dashboard.product_management;

import javax.swing.*;
import java.awt.*;

// Separate panel on the right for adding products button
public class AddRightPanel extends JPanel {

    private final ManageProducts product_manager;
    private JButton refreshProducts = new JButton("Refresh");

    public AddRightPanel(ManageProducts product_manager) {
        this.product_manager = product_manager;

        // Creates a layout manager that will lay out components along the given axis
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Creates an empty border that takes up space but which does no drawing,
        // specifying the width of the top,left, bottom, and right sides.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addProductButton = new JButton("Add New Product");
        addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductButton.addActionListener(e -> addNewProduct());

        refreshProducts.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshProducts.addActionListener(e -> this.product_manager.refreshTableData());

        add(addProductButton);
        add(refreshProducts);
    }

    private void addNewProduct() {
        // add new product dialog pops up.
        // getting the dashboard (ancestor) so that dialog blocks the usage of the dashboard
        // until dialog closes.
        Window window = SwingUtilities.getWindowAncestor(this);
        AddNewProductDialog dialog = new AddNewProductDialog((JFrame) window);
        dialog.setVisible(true);

        // once added, refresh the list
    }
}