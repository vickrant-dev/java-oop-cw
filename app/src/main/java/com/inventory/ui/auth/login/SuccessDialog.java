package com.inventory.ui.auth.login;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SuccessDialog extends JFrame {
    
    private JOptionPane successDialog;

    public void setSuccessDialog(String text) {
        this.successDialog.showMessageDialog(
                SuccessDialog.this, text);
    }

}
