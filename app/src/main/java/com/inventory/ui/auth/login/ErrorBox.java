package com.inventory.ui.auth.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorBox {

    public static void errorshw(String message){

        //Dialog box
        JDialog Dialogbox = new JDialog();
        Dialogbox.setUndecorated(true);
        Dialogbox.setModal(true);
        Dialogbox.setAlwaysOnTop(true);

        //Dialog box
        JPanel ErrorPanel = new JPanel(new BorderLayout(10, 10));
        ErrorPanel.setBackground(new Color(245,245,245));
        ErrorPanel.setBorder(BorderFactory.createLineBorder(new Color(245,245,245),0));
        ErrorPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        //Warning Sign
        JLabel warnsign = new JLabel("⚠");
        warnsign.setFont(new Font("Arial",Font.BOLD,24));
        warnsign.setForeground(new Color(200,225,0));

        //Error Label (Words)
        JLabel ErrorLabel = new JLabel(message);
        //ErrorLabel.setText(message);
        ErrorLabel.setForeground(new Color(0,0,0));
        ErrorLabel.setFont(new Font("Arial", Font.BOLD,15));
        //ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Message + Warning sign
        JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        content.setBackground(new Color(245,245,245));
        content.add(warnsign);
        content.add(ErrorLabel);

        ErrorPanel.add(content, BorderLayout.CENTER);
        //JOptionPane.showMessageDialog(null,ErrorPanel,"Message", JOptionPane.PLAIN_MESSAGE);

        //Custom "OK" Button to the error box
        JButton okbtn = new JButton("Ok");
        okbtn.setFocusPainted(false);
        okbtn.setBorderPainted(false);
        okbtn.setOpaque(true);
        okbtn.setBackground(new Color(211,211,211));
        okbtn.setForeground(Color.BLACK);
        okbtn.setFont(new Font("Arial", Font.BOLD,14));
        okbtn.setPreferredSize(new Dimension(100,35));

        //hover to the OK button
        Color dftcolor = new Color(211,211,211);
        Color hvcolor = new Color(169,169,169);
        okbtn.addMouseListener(new MouseAdapter() {
            @Override
            public  void mouseEntered(MouseEvent e){
                okbtn.setBackground(hvcolor);
                okbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                okbtn.setBackground(dftcolor);
                okbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        okbtn.addActionListener(e -> Dialogbox.dispose());

        JPanel btnpanel = new JPanel();
        btnpanel.setBackground(new Color(245,245,245));
        btnpanel.add(okbtn);

        ErrorPanel.add(btnpanel, BorderLayout.SOUTH);

        Dialogbox.add(ErrorPanel);
        Dialogbox.pack();
        Dialogbox.setLocationRelativeTo(null);
        Dialogbox.setVisible(true);
    }
}
