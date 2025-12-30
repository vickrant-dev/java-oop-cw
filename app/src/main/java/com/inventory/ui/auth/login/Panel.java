package com.inventory.ui.auth.login;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Panel extends JPanel {

    private Image image;

     public Panel ()
     {
         URL imageUrl = getClass().getResource("/electronix.png");

         if (imageUrl == null) {
             throw new RuntimeException("Image not found!");
         }

         image = new ImageIcon(imageUrl).getImage();


     }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0,500,500, this);
    }

     

}
