package view.userLogin.company;

import view.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyMakeTransactionView extends JPanel {

    private JLabel transactionLabel = new JLabel("Transaction");
    private JLabel availableSuppliersLabel = new JLabel("Select Supplier");
    private JLabel availableEmployees = new JLabel("Select Employee");
    private JComboBox availableSuppliersComboBox = new JComboBox();
    private JTextField paymentAmounTextField = new JTextField();
    private JComboBox availableEmployeesComboBox = new JComboBox();
    private JButton buyButton = new JButton("BUY");
    private JButton homeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public CompanyMakeTransactionView(GUI gui) {
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();

        transactionLabel.setBounds(330,20,200,20);
        transactionLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        this.add(transactionLabel);

        availableSuppliersLabel.setBounds(180,150,150,20);
        availableSuppliersLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.add(availableSuppliersLabel);

        availableSuppliersComboBox.setBounds(330, 150, 100, 30);
        this.add(availableSuppliersComboBox);

        paymentAmounTextField.setBounds(435, 150, 40, 30);
        paymentAmounTextField.setBackground(new Color(248,248,255));
        this.add(paymentAmounTextField);

        availableEmployees.setBounds(180, 200, 150,20);
        availableEmployees.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.add(availableEmployees);

        availableEmployeesComboBox.setBounds(330,200, 100,30);
        this.add(availableEmployeesComboBox);

        personalizeButton(buyButton);
        buyButton.setBounds(480, 150, 50, 30);
        this.add(buyButton);

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

    public void setSupplierIdsData(String[] data) {
        availableSuppliersComboBox.removeAllItems();
        for (String datum : data) {
            availableSuppliersComboBox.addItem(datum);
        }
    }

    public void setEmployeeAfmData(String[] data) {
        availableSuppliersComboBox.removeAllItems();
        for (String datum : data) {
            availableEmployeesComboBox.addItem(datum);
        }
    }

    public Object getSupplierValue() {
        return availableSuppliersComboBox.getSelectedItem();
    }

    public Object getEmployeeValue() {
        return availableEmployeesComboBox.getSelectedItem();
    }

    public void addBuyButtonActionListener(ActionListener actionListener) {
        buyButton.addActionListener(actionListener);
    }

    public void notEnoughMoney() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Not enough Money...");
    }

    public String getPrice() { return paymentAmounTextField.getText(); }
}

