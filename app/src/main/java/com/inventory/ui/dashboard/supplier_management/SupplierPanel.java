package com.inventory.ui.dashboard.supplier_management;

import javax.swing.*;
import java.awt.*;

public class SupplierPanel extends JPanel {
    public SupplierPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Supplier Management", SwingConstants.CENTER), BorderLayout.CENTER);
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