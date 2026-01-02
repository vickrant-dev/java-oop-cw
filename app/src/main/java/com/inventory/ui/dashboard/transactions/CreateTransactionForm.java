package com.inventory.ui.dashboard.transactions;

import com.inventory.controller.*;
import com.inventory.domain.*;
import com.inventory.utils.handleValidateFields;
import com.inventory.utils.sessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateTransactionForm extends JDialog {

    // UI
    private final JComboBox<Customer> cmbCustomers = new JComboBox<>();
    private final JComboBox<Product> cmbProducts = new JComboBox<>();
    private final JTextField txtCustomerName = new JTextField(15);
    private final JTextField txtContactInfo = new JTextField(15);
    private final JSpinner spnQty = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    private final JFormattedTextField txtPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
    private final JComboBox<String> cmbPayment = new JComboBox<>(new String[]{"Cash", "Card", "Transfer"});

    private DefaultTableModel detailsModel;
    private JTable tblDetails;

    // controllers
    private final ProductController productController = new ProductController();
    private final TransactionController transactionController = new TransactionController();
    private final CustomerController customerController = new CustomerController();

    private String selectedCustomerId = null;

    public CreateTransactionForm(Window owner) {
        super(owner, "New Transaction", ModalityType.APPLICATION_MODAL);

        setupWindow();
        initUI();

        // get data into the dropdowns
        refreshData();
    }

    private void setupWindow() {
        setSize(800, 650);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));
    }

    private void initUI() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        addFormRow(topPanel, "Existing Customer:", cmbCustomers, c, 0);
        addFormRow(topPanel, "Full Name:", txtCustomerName, c, 1);
        addFormRow(topPanel, "Contact Info:", txtContactInfo, c, 2);
        addFormRow(topPanel, "Product:", cmbProducts, c, 3);
        addFormRow(topPanel, "Quantity:", spnQty, c, 4);
        addFormRow(topPanel, "Unit Price:", txtPrice, c, 5);
        addFormRow(topPanel, "Method:", cmbPayment, c, 6);

        txtCustomerName.setEditable(false);
        txtCustomerName.setBackground(new Color(240, 240, 240));
        txtContactInfo.setEditable(false);
        txtContactInfo.setBackground(new Color(240, 240, 240));

        setupRenderers();

        // action buttons for the list
        JPanel itemActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdd = new JButton("Add Item");
        JButton btnDelete = new JButton("Remove Item");
        btnDelete.setForeground(Color.RED);
        itemActions.add(btnAdd);
        itemActions.add(btnDelete);

        c.gridx = 1; c.gridy = 7;
        topPanel.add(itemActions, c);
        add(topPanel, BorderLayout.NORTH);

        // table
        String[] header = {"Product ID", "Name", "Qty", "Price", "Total"};
        detailsModel = new DefaultTableModel(header, 0) {
            @Override public boolean isCellEditable(int r, int col) { return false; }
        };
        tblDetails = new JTable(detailsModel);
        add(new JScrollPane(tblDetails), BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Create");
        JButton btnCancel = new JButton("Cancel");
        footer.add(btnSave);
        footer.add(btnCancel);
        add(footer, BorderLayout.SOUTH);

        bindActions(btnAdd, btnDelete, btnSave, btnCancel);
    }

    private void addFormRow(JPanel p, String label, JComponent comp, GridBagConstraints c, int row) {
        c.gridy = row;
        c.gridx = 0; p.add(new JLabel(label), c);
        c.gridx = 1; p.add(comp, c);
    }

    private void setupRenderers() {
        cmbCustomers.setRenderer((list, val, idx, sel, focus) -> {
            DefaultListCellRenderer r = new DefaultListCellRenderer();
            Component cp = r.getListCellRendererComponent(list, val, idx, sel, focus);
            if (val != null) ((JLabel)cp).setText(((Customer) val).getCustomerName());
            return cp;
        });

        cmbProducts.setRenderer((list, val, idx, sel, focus) -> {
            DefaultListCellRenderer r = new DefaultListCellRenderer();
            Component cp = r.getListCellRendererComponent(list, val, idx, sel, focus);
            if (val != null) ((JLabel)cp).setText(((Product) val).getProductName());
            return cp;
        });
    }

    private void bindActions(JButton btnAdd, JButton btnDelete, JButton btnSave, JButton btnCancel) {
        // dropdown listener
        cmbCustomers.addActionListener(e -> updateCustomerFields((Customer) cmbCustomers.getSelectedItem()));

        // adding a row to the table
        btnAdd.addActionListener(e -> {
            Product p = (Product) cmbProducts.getSelectedItem();
            if (p == null) return;

            int qty = (int) spnQty.getValue();
            double price = (txtPrice.getValue() != null) ? ((Number) txtPrice.getValue()).doubleValue() : 0.0;

            detailsModel.addRow(new Object[]{ p.getId(), p.getProductName(), qty, price, (qty * price) });
        });

        btnDelete.addActionListener(e -> {
            int row = tblDetails.getSelectedRow();
            if (row != -1) detailsModel.removeRow(row);
        });

        btnSave.addActionListener(e -> processTransaction());
        btnCancel.addActionListener(e -> dispose());
    }

    private void refreshData() {
        try {
            // customers
            List<Customer> customers = customerController.getAllCustomers();
            if (customers != null && !customers.isEmpty()) {
                cmbCustomers.setModel(new DefaultComboBoxModel<>(customers.toArray(new Customer[0])));
                updateCustomerFields(customers.get(0));
            }

            // products
            List<Product> products = productController.fetchAllProducts();
            if (products != null) {
                cmbProducts.setModel(new DefaultComboBoxModel<>(products.toArray(new Product[0])));
            }
        } catch (Exception e) {
            System.err.println("Failed to load dropdown data: " + e.getMessage());
        }
    }

    private void updateCustomerFields(Customer c) {
        if (c != null) {
            selectedCustomerId = c.getCustomerId();
            txtCustomerName.setText(c.getCustomerName());
            txtContactInfo.setText(c.getCustomerContactInfo() != null ? c.getCustomerContactInfo() : "");
        } else {
            selectedCustomerId = null;
            txtCustomerName.setText("");
            txtContactInfo.setText("");
        }
    }

    private void processTransaction() {
        // get session first
        String uid = null;
        try {
            uid = new sessionManager().getUserId();
        } catch (Exception e) { /* log it if needed */ }

        if (uid == null || uid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Session expired. Please login again.");
            return;
        }

        if (selectedCustomerId == null) {
            JOptionPane.showMessageDialog(this, "Please select a customer.");
            return;
        }

        // validation check
        String error = new handleValidateFields().validateFields(txtCustomerName, txtContactInfo, detailsModel, cmbPayment);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        // get data from the table
        List<TransactionDetails> items = new ArrayList<>();
        double grandTotal = 0.0;
        for (int i = 0; i < detailsModel.getRowCount(); i++) {
            String id = detailsModel.getValueAt(i, 0).toString();
            int q = Integer.parseInt(detailsModel.getValueAt(i, 2).toString());
            double pr = Double.parseDouble(detailsModel.getValueAt(i, 3).toString());

            items.add(new TransactionDetails(id, q, pr));
            grandTotal += (q * pr);
        }

        String now = LocalDateTime.now().toString();
        String method = cmbPayment.getSelectedItem().toString();

        Transaction trans = new Transaction(selectedCustomerId, now, grandTotal, 0.0, 0.0, method, now, items);
        String result = transactionController.createTransaction(trans);

        if ("200".equals(result)) {
            JOptionPane.showMessageDialog(this, "Transaction Saved Successfully!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Something went wrong. Check DB logs.");
        }
    }
}