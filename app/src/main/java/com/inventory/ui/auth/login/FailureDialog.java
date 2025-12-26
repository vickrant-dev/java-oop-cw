package com.inventory.ui.auth.login;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FailureDialog extends JFrame{
    private JOptionPane failureDialog;

    public void setFailureDialog(String text) {
        this.failureDialog.showMessageDialog(FailureDialog.this, text);
    }
}
