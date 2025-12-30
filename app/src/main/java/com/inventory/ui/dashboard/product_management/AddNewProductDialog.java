package com.inventory.ui.dashboard.product_management;

import com.inventory.domain.Supplier;
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
    private JComboBox<String> cmbSupplier;
    private Image image;

    public AddNewProductDialog(JFrame dashboard) {
        super(dashboard, "Add New Product", true);
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
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
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
        JComboBox<Supplier> cmbSupplier = new JComboBox<>();
        cmbSupplier.setModel(new DefaultComboBoxModel<>(
                // Convert the List<Supplier> to a Supplier[] array explicitly
                // as it can only take any specified array and not a list array.
                // Supplier[] is a fixed size row of suppliers of type Supplier.
                rows.toArray(new Supplier[0])
        ));

        addRow(formPanel, gbc, row++, "Supplier:", cmbSupplier);

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));

        JButton btnSave = new JButton("Save");

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
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void onSave() {
        dispose();
    }
}
