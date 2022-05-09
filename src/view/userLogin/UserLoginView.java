package view.userLogin;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginView extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox usersComboBox;
    private JButton loginButton;
    private JButton homeButton;


    public UserLoginView(GUI gui) {
        this.setLayout(null);
        this.setBackground(new Color(248,248,255));


        this.cardPanel  = gui.getCardPanel();
        this.cardLayout = gui.getCardLayout();

        usersComboBox = new JComboBox();
        usersComboBox.setBounds(275,200,150,25);
        this.add(usersComboBox);
        loginButton = new JButton("Login");
        personalizeButton(loginButton);
        loginButton.setBounds(430,200,80,25);
        this.add(loginButton);

        homeButton = new JButton("Home");
        personalizeButton(homeButton);
        homeButton.setBounds(360, 470,100,40);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "home");
            }
        });
        this.add(homeButton);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(150,30));
        button.setBackground(new Color(248,248,255));
        button.setForeground(new Color(40,5,150));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(230, 230,250);
            }
        });
    }

    public void setAccountIdsData(String[] data) {
        usersComboBox.removeAllItems();
        for(int i = 0; i < data.length; i++) {
            usersComboBox.addItem(data[i]);
        }
    }

    public void addLoginButtonActionListener(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public Object getComboBoxValue() {
        return usersComboBox.getSelectedItem();
    }

    public void setPanelOf(String userType) {

        switch (userType) {
            case "private_citizen" -> cardLayout.show(cardPanel, "private_user_login");
            case "supplier" -> cardLayout.show(cardPanel, "supplier_user_login");
            case "company" -> cardLayout.show(cardPanel, "company_user_login");
        }
    }

}
