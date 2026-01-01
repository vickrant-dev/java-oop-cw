package com.inventory;

import com.inventory.ui.auth.login.*;
//import com.inventory.ui.dashboard.product_management.AddNewProductDialog;
import com.inventory.ui.dashboard.customer_management.CustomerPanel;
import com.inventory.ui.dashboard.product_management.ProductPanel;
import com.inventory.ui.dashboard.supplier_management.SupplierPanel;

import javax.swing.*;

public class main {
    public static void main (String[] args) {
       // Login login = new Login();
      //  login.setVisible(true);

       JFrame frame = new JFrame("Supplier Management");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(900,500);
       frame.setLocationRelativeTo(null);

      frame.add(new SupplierPanel());
         frame.setVisible(true);
    }
}
