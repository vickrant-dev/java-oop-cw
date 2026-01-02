package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;
import com.inventory.domain.Product;
import com.inventory.domain.Supplier;
import com.inventory.utils.handleValidateFields;
import com.inventory.utils.loadAllSuppliers;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;

public class AddNewProductDialog extends JDialog {

    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtCategory;
    private JFormattedTextField txtPrice;
    private JSpinner spnStock;
    private JComboBox<Supplier> cmbSupplier;
    private final ManageProducts manageProd;

    public AddNewProductDialog(JFrame dashboard, ManageProducts manageProd) {
        super(dashboard, "Add New Product", true);
        this.manageProd = manageProd;
        setSize(400, 400);
        // set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            Image icon = new ImageIcon(iconURL).getImage();
            setIconImage(icon);
        }
        setLocationRelativeTo(dashboard);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        DialogUI();
    }

    private void DialogUI() {
        JPanel formPanel = new JPanel(new GridBagLayout()); // layout manager
        GridBagConstraints gbc = new GridBagConstraints(); // helps to place components inside grid bag layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;



        int row = 0;

        txtProductId = new JTextField(15);
        addRow(formPanel, gbc, row++, "Product ID:", txtProductId);

        txtProductName = new JTextField(15);
        addRow(formPanel, gbc, row++, "Product Name:", txtProductName);

        txtCategory = new JTextField(15);
        addRow(formPanel, gbc, row++, "Category:", txtCategory);

        NumberFormat currency = NumberFormat.getNumberInstance();
        txtPrice = new JFormattedTextField(currency);
        txtPrice.setColumns(15);
        addRow(formPanel, gbc, row++, "Price:", txtPrice);

        spnStock = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
        addRow(formPanel, gbc, row++, "Stock:", spnStock);

        // flexible list of suppliers
        List<Supplier> rows = new loadAllSuppliers().getSupplierList();

        // contains the elements in the specified array
        // ComboBox explicitly mentions that it expects a Supplier type.
        cmbSupplier = new JComboBox<>();
        cmbSupplier.setModel(new DefaultComboBoxModel<>(
                // Convert the List<Supplier> to a Supplier[] array explicitly
                // as it can only take any specified array and not a list array.
                // Supplier[] is a fixed size row of suppliers of type Supplier.
                rows.toArray(new Supplier[0])
        ));

        addRow(formPanel, gbc, row++, "Supplier:", cmbSupplier);

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));

        JButton btnSave = new JButton("Add");

        // Normal and hover colors for btnsave
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(new Color(100, 160, 210));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(new Color(193, 213, 232));
            }
        });

        JButton btnCancel = new JButton("Cancel");

        // Normal and hover colors for btnCancel
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancel.setBackground(new Color(100, 160, 210));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancel.setBackground(new Color(193, 213, 232));
            }
        });

        btnSave.addActionListener(e -> onSave());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row,
                        String labelText, JComponent field) {
        // label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        // input field
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void onSave() {
        // collect info from fields
        // create product obj

        String errorMsg = new handleValidateFields().validateFields(txtProductId, txtProductName, txtCategory,
                txtPrice, spnStock, cmbSupplier);

        if (!errorMsg.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please correct the following: \n"
                    + errorMsg, "Input invalid", JOptionPane.WARNING_MESSAGE);
        }
        else {
            Object price_value = txtPrice.getValue(); // extracting price value and it's an obj type.
            Object stock_value = spnStock.getValue();

            Product newProduct = new Product(
                txtProductId.getText(),
                txtProductName.getText(),
                txtCategory.getText(),
                ((Number) price_value).doubleValue(), // cast it as number and convert to double.
                ((Number) stock_value).intValue(),
                (Supplier)cmbSupplier.getSelectedItem() // type casting
            );

            // call controller to create product
            ProductController productController = new ProductController();
            String create_prod_res = productController.createProduct(newProduct);

            // success/failure handling
            if (create_prod_res.equals("200")) {
                // auto refresh table
                manageProd.refreshTableData();
                dispose();
            }
            else {
                // failure dialog
                JOptionPane.showMessageDialog(this,
                        "Failed to create product. " +
                                JOptionPane.WARNING_MESSAGE);
                dispose();
            }
        }

    }
}
