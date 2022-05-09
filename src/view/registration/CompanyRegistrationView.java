package view.registration;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompanyRegistrationView extends JPanel {

    private final JLabel nameLabel               = new JLabel("Company's name");
    private final JLabel ceoNameLabel            = new JLabel("Ceo's name");
    private final JLabel emailLabel              = new JLabel("Email");
    private final JLabel addressLabel            = new JLabel("Address");
    private final JLabel numOfEmp                = new JLabel("Num of Employees");

    private final JTextField nameField           = new JTextField();
    private final JTextField ceoField            = new JTextField();
    private final JTextField emailField          = new JTextField();
    private final JTextField countryField        = new JTextField("Country");
    private final JTextField cityField           = new JTextField("City");
    private final JTextField streetField         = new JTextField("Street");
    private final JTextField numberField         = new JTextField("9");
    private final JTextField postalCodeField     = new JTextField("73100");
    private final JTextField numOfEmployeesField = new JTextField("10");

    private final JButton doneButton             = new JButton("Done");

    private final CardLayout cardLayout;
    private final JPanel     cardPanel;

    public CompanyRegistrationView(GUI gui) {
        this.setBackground(new Color(248,248,255));
        this.setLayout(null);
        this.cardPanel  = gui.getCardPanel();
        this.cardLayout = gui.getCardLayout();

        initComponents();
    }

    private void initComponents() {
        nameLabel.setBounds(50,50,200,40);
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        ceoNameLabel.setBounds(50,100,200,40);
        ceoNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        emailLabel.setBounds(50,150,200,40);
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        addressLabel.setBounds(50,200,200,40);
        addressLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        numOfEmp.setBounds(50,250,200,40);
        numOfEmp.setFont(new Font("Times New Roman", Font.BOLD, 14));

        this.add(nameLabel);
        this.add(ceoNameLabel);
        this.add(emailLabel);
        this.add(addressLabel);
        this.add(numOfEmp);

        nameField.setBounds(200,60,200,30);
        nameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        nameField.setBackground(Color.WHITE);

        ceoField.setBounds(200,110,200,30);
        ceoField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        ceoField.setBackground(Color.WHITE);

        emailField.setBounds(200,160,200,30);
        emailField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        emailField.setBackground(Color.WHITE);

        countryField.setBounds(200,210,100,30);
        countryField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        countryField.setBackground(Color.WHITE);

        cityField.setBounds(305,210,100,30);
        cityField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        cityField.setBackground(Color.WHITE);

        streetField.setBounds(410,210,100,30);
        streetField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        streetField.setBackground(Color.WHITE);

        numberField.setBounds(515,210,100,30);
        numberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        numberField.setBackground(Color.WHITE);

        postalCodeField.setBounds(620,210,100,30);
        postalCodeField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        postalCodeField.setBackground(Color.WHITE);

        numOfEmployeesField.setBounds(200,260,50,30);
        numOfEmployeesField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        numOfEmployeesField.setBackground(Color.WHITE);

        this.add(nameField);
        this.add(ceoField);
        this.add(emailField);
        this.add(countryField);
        this.add(cityField);
        this.add(streetField);
        this.add(numberField);
        this.add(postalCodeField);
        this.add(numOfEmployeesField);

        doneButton.setBounds(340,450,180,40);
        doneButton.setBackground(new Color(248,248,255));
        doneButton.setForeground(new Color(40,5,150));
        doneButton.setFocusPainted(false);
        doneButton.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        doneButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(200, 200,255);
            }
        });
        doneButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                doneButton.setBackground(new Color(230, 230,250));
            }
            public void mouseExited(MouseEvent evt) {
                doneButton.setBackground(new Color(248,248,255));
            }
        });
        doneButton.addActionListener(e -> cardLayout.show(cardPanel, "overview"));

        this.add(doneButton);
    }

    public void addDoneButtonListener(ActionListener listenerForDoneButton) {
        doneButton.addActionListener(listenerForDoneButton);
    }

    private void invalidDataMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void clearFields() {
        nameField.setText("");
        ceoField.setText("");
        emailField.setText("");
        countryField.setText("Country");
        cityField.setText("City");
        streetField.setText("Street");
        numberField.setText("9");
        postalCodeField.setText("73100");
        numOfEmployeesField.setText("10");
    }

    public String getCompanyName() { return nameField.getText(); }
    public String getCeoName() { return ceoField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getCountry() { return countryField.getText(); }
    public String getCity() { return cityField.getText(); }
    public String getStreet() { return streetField.getText(); }
    public String getNumber() { return numberField.getText(); }
    public String getPostalCode() { return postalCodeField.getText(); }
    public String getNumOfEmployees() { return numOfEmployeesField.getText(); }
}