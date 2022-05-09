package view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeView extends JPanel {

    private final JButton loginAsCccButton   = new JButton("login as CCC");
    private final JButton loginAsUserButton  = new JButton("login");
    private final JButton register2CccButton = new JButton("register");
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public HomeView(GUI gui) {
        this.setBackground(new Color(248,248,255));
        this.setLayout(new GridBagLayout());

        this.cardPanel  = gui.getCardPanel();
        this.cardLayout = gui.getCardLayout();

        initComponents();
    }

    private void initComponents() {
        loginAsCccButton.setPreferredSize(new Dimension(180,40));
        loginAsCccButton.setBackground(new Color(255,248,248));
        loginAsCccButton.setForeground(new Color(150,5,40));
        loginAsCccButton.setFocusPainted(false);
        loginAsCccButton.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(160,35,35)));
        loginAsCccButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(255, 200,200);
            }
        });
        loginAsCccButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginAsCccButton.setBackground(new Color(250, 230,230));
            }
            public void mouseExited(MouseEvent evt) {
                loginAsCccButton.setBackground(new Color(255,248,248));
            }
        });
        loginAsCccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ccc");
            }
        });

        personalizeButton(loginAsUserButton);
        loginAsUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "userLogin");
            }
        });

        personalizeButton(register2CccButton);
        register2CccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "user_type");
            }
        });

        this.add(loginAsCccButton);
        this.add(Box.createHorizontalStrut(70));
        this.add(loginAsUserButton);
        this.add(Box.createHorizontalStrut(10));
        this.add(register2CccButton);
    }

    public void addLoginAsUserButtonActionListener(ActionListener actionListener) {
        loginAsUserButton.addActionListener(actionListener);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(180,40));
        button.setBackground(new Color(248,248,255));
        button.setForeground(new Color(40,5,150));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(200, 200,255);
            }
        });
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(230, 230,250));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(248,248,255));
            }
        });
    }
}
