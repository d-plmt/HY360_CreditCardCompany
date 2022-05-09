package view.userLogin;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayDebtView extends JPanel {

    private JLabel payDeptLabel         = new JLabel("Pay DEBT");
    private JLabel remainingLabel       = new JLabel("Remaining: ");
    private JTextField payDeptTextField = new JTextField();
    private JButton payDebtButton       = new JButton("PAY");
    private JButton homeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public PayDebtView(GUI gui) {
        this.setLayout(null);
        this.setBackground(Color.white);

        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();

        initComponents();
    }

    private void initComponents() {
        payDeptLabel.setBounds(330,20,200,35);
        payDeptLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        this.add(payDeptLabel);

        remainingLabel.setBounds(180,150,200,20);
        remainingLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.add(remainingLabel);

        payDeptTextField.setBounds(180, 180, 70, 25);
        payDeptTextField.setFont(new Font("Times New Roman", Font.BOLD, 10));
        this.add(payDeptTextField);

        personalizeButton(payDebtButton);
        payDebtButton.setBounds(260, 180, 50,25);
        this.add(payDebtButton);

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

    public void setRemainingLabel(String remaining) {
        String s = "Owed To CCC: ";
        s+= remaining;
        remainingLabel.setText(s);
    }

    public void addPayButtonActionListener(ActionListener actionListener) {
        payDebtButton.addActionListener(actionListener);
    }

    public String getDesiredDebtAmount() {
        return payDeptTextField.getText();
    }

    public void invalidPayDebtAmountMessage() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Oops! Not valid Amount");
    }
}
