package com.inventory;

import com.inventory.ui.auth.login.*;
import com.inventory.ui.dashboard.customer_management.CustomerPanel;

import javax.swing.*;

public class main {
    public static void main (String[] args) {
        /*System.out.println("Main");
        Login login = new Login();
        login.setVisible(true);*/

        JFrame frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,500);
        frame.setLocationRelativeTo(null);

        frame.add(new CustomerPanel());
        frame.setVisible(true);
    }
}
