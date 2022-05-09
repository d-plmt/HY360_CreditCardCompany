package view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OverviewView extends JPanel {

    private JLabel ovoerviewMessageLabel;
    private JButton homeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public OverviewView(GUI gui) {
        this.setBackground(new Color(248,248,255));
        this.setLayout(null);
        this.cardPanel  = gui.getCardPanel();
        this.cardLayout = gui.getCardLayout();

        initComponents();
    }

    private void initComponents() {
        ovoerviewMessageLabel = new JLabel("Thank you!!! :)");
        ovoerviewMessageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ovoerviewMessageLabel.setBounds(330,50,500,100);

        this.add(ovoerviewMessageLabel);

        homeButton = new JButton("Home");
        homeButton.setBounds(340,450,180,40);
        homeButton.setBackground(new Color(248,248,255));
        homeButton.setForeground(new Color(40,5,150));
        homeButton.setFocusPainted(false);
        homeButton.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        homeButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(200, 200,255);
            }
        });
        homeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                homeButton.setBackground(new Color(230, 230,250));
            }
            public void mouseExited(MouseEvent evt) {
                homeButton.setBackground(new Color(248,248,255));
            }
        });
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "home"));

        this.add(homeButton);
    }
}