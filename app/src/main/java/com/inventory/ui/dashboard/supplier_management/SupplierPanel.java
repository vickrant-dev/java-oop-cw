package com.inventory.ui.dashboard.supplier_management;

import com.inventory.ui.dashboard.product_management.ManageProducts;
import com.inventory.ui.dashboard.product_management.PopupMenu;
import com.inventory.ui.dashboard.product_management.UpdateProduct;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SupplierPanel extends JPanel {

    JTable table;
    DefaultTableModel model;


    public SupplierPanel() {

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Supplier Management");

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 12, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // set column names
        String[] columns = {"ID", "Name", "Email", "Phone"};

        Object[][] data = {
                {1, "Juman", "juman@gmail.com", "222333111"},
                {2, "Dias", "dias@gmail.com", "222211111111"},
                {3, "Vicki", "Vivki@gmail.com", "2233422222"}
        };


        model = new DefaultTableModel(data, columns);
        model = new DefaultTableModel(data, columns) {
            // prevents the cell entering to the typing mode
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        // change the appearance of the table
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // adding scroll support to the table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // created button panel to the right aligned
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // create two buttons
        JButton addButton = new JButton("Add new Supplier");
        JButton refreshButton = new JButton("Refresh");

        // Align buttons to the right
        addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        refreshButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //spacing from the top
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(addButton);// add button to the panel
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(refreshButton);//add button to the panel

        // add panel to the right side
        add(buttonPanel, BorderLayout.EAST);

        // add action to the add button
        addButton.addActionListener(e -> {

            new AddNewSupplier();
        });
        // add action to the refresh button
        refreshButton.addActionListener(e -> {
            System.out.println("Refresh clicked");
        });

        // Popup menu
        PopupMenu popup = new PopupMenu();
        JPopupMenu popupMenu = popup.getPopupMenu();
        table.setComponentPopupMenu(popupMenu);

        // UPDATE menu item (index 0)
        JMenuItem updateItem = (JMenuItem) popupMenu.getComponent(0);

        updateItem.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row >=0);
            // assign column values to the variables
            String id       = table.getValueAt(row, 0).toString();
            String name     = table.getValueAt(row, 1).toString();
            String email = table.getValueAt(row, 2).toString();
            String phone    = table.getValueAt(row, 3).toString();

            // sending the vales as a parameters
            new UpdateSupplier(id, name, email, phone);
        });

        // add mouse listener to the table
        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detect Double Click
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        // Open the form
                        new ProductofSupplier();
                    }
                }
            }



            public void mousePressed(MouseEvent e) { selectRow(e); }

            public void mouseReleased(MouseEvent e) { selectRow(e); }

            private void selectRow(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                }
            }


        });


    }
}

// JFrame -> main application window. contains title, menu, buttons and other components
// JDialog -> temporary window. pop-up window basically
// JWindow -> window without borders or title bar. often used in splash screens.

// JPanel -> container that is used to group components
// JScrollPane -> adds scrollbar to another component
// JSplitPane -> Splits an area into two resizable sections
// JTabbedPane -> displays components with tabs
// JLayeredPane -> allow components to overlap each other
// JDesktopPane -> holds internal frames (we rarely use this)
// JInternalPane -> a window inside a window