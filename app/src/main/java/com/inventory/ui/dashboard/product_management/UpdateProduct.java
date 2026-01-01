package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;
import com.inventory.domain.Product;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UpdateProduct extends JFrame {

    private JTextField Productidfield, ProductNamefield, Categoryfield, Pricefield, StockField;
    private Product localProduct;
    private ManageProducts parentPanel;

    public UpdateProduct(Product product, ManageProducts parent) {
        this.localProduct = product;
        this.parentPanel = parent;

        setTitle("Update Product");
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) { setIconImage(new ImageIcon(iconURL).getImage()); }

        setSize(450, 390);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // labels and fields
        JLabel UpdateLabel = new JLabel("Update Product");
        UpdateLabel.setBounds(170, 5, 200, 40);
        UpdateLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        UpdateLabel.setForeground(new Color(0, 70, 0));
        add(UpdateLabel);

        addLabelAndField("Product ID", 55, Productidfield = new JTextField(), true);
        addLabelAndField("Product Name", 107, ProductNamefield = new JTextField(), true);
        addLabelAndField("Category", 157, Categoryfield = new JTextField(), true);
        addLabelAndField("Price", 207, Pricefield = new JTextField(), true);
        addLabelAndField("Stock", 257, StockField = new JTextField(), true);

        // prefill data using the object
        Productidfield.setText(localProduct.getProductId());
        ProductNamefield.setText(localProduct.getProductName());
        Categoryfield.setText(localProduct.getProductCategory());
        Pricefield.setText(String.valueOf(localProduct.getProductPrice()));
        StockField.setText(String.valueOf(localProduct.getProductStockQuantity()));

        // btns
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton Updatebutton = new JButton("Update");
        JButton Cancelbutton = new JButton("Cancel");

        Cancelbutton.addActionListener(e -> dispose());

        Updatebutton.addActionListener(e -> {
            // use setters to update fields locally
            localProduct.updateDetails(
                    Productidfield.getText(),
                    ProductNamefield.getText(),
                    Categoryfield.getText(),
                    Double.parseDouble(Pricefield.getText()),
                    Integer.parseInt(StockField.getText())
            );

            // passing updated local prod object to controller
            ProductController update_prod_controller = new ProductController();
            update_prod_controller.updateProductDetails(localProduct);

            parentPanel.refreshTableData();
            dispose();
        });

        buttonPanel.add(Updatebutton);
        buttonPanel.add(Cancelbutton);
        buttonPanel.setBounds(0, 307, 450, 50);
        add(buttonPanel);

        setVisible(true);
    }

    private void addLabelAndField(String text, int y, JTextField field, boolean editable) {
        JLabel label = new JLabel(text);
        label.setBounds(10, y, 120, 25);
        label.setForeground(new Color(0, 70, 0));
        field.setBounds(130, y, 250, 25);
        field.setEditable(editable);
        add(label);
        add(field);
    }
}