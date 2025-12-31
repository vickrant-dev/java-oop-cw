package com.inventory.ui.dashboard.product_management;


import javax.swing.*;


public class PopupMenu  {

    private JPopupMenu popupMenu;

  public PopupMenu()
  {
    popupMenu = new JPopupMenu();

    JMenuItem Update =new JMenuItem("Update");
    JMenuItem Delete =new JMenuItem("Delete");
    JMenuItem Cancel =new JMenuItem("Cancel");

    popupMenu.add(Update);
    popupMenu.add(Delete);
    popupMenu.add(Cancel);


    // when the update button press new frame open

      Update.addActionListener(e ->  {
        popupMenu.setVisible(false);


      });
    // when the delete click message will appear
    Delete.addActionListener(e ->  {
        popupMenu.setVisible(false);
        System.out.println("Delete clicked");
    }) ;

    // when the cancel click popup meu close
      Cancel.addActionListener(e ->  {
          popupMenu.setVisible(false);
      });

  }

  // return the popup menu

    public JPopupMenu getPopupMenu() {
      return popupMenu;
    }


}
