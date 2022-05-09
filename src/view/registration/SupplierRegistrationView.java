package view.registration;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SupplierRegistrationView extends JPanel {

    private final DefaultComboBoxModel sexTypes       = new DefaultComboBoxModel();
    private final JComboBox  sexComboBox              = new JComboBox();

    private final JLabel firstNameLabel               = new JLabel("First Name");
    private final JLabel lastNameLabel                = new JLabel("Last Name");
    private final JLabel emailLabel                   = new JLabel("Email");
    private final JLabel addressLabel                 = new JLabel("Address");
    private final JLabel     sexLabel           = new JLabel("Sex");

    private final JTextField firstNameField           = new JTextField();
    private final JTextField lastNameField            = new JTextField();
    private final JTextField emailField               = new JTextField();
    private final JTextField countryField             = new JTextField("Country");
    private final JTextField cityField                = new JTextField("City");
    private final JTextField streetField              = new JTextField("Street");
    private final JTextField numberField              = new JTextField("9");
    private final JTextField postalCodeField          = new JTextField("73100");

    private final JButton doneButton                  = new JButton("Done");

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public SupplierRegistrationView(GUI gui) {
        this.setBackground(new Color(248,248,255));
        this.cardPanel  = gui.getCardPanel();
        this.cardLayout = gui.getCardLayout();

        doneButton.addActionListener(e -> cardLayout.show(cardPanel, "overview"));
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        firstNameLabel.setBounds(50,50,200,40);
        firstNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lastNameLabel.setBounds(50,100,200,40);
        lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        emailLabel.setBounds(50,150,200,40);
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        addressLabel.setBounds(50,200,200,40);
        addressLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        sexLabel.setBounds(50,250,200,40);
        sexLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        this.add(firstNameLabel);
        this.add(lastNameLabel);
        this.add(emailLabel);
        this.add(addressLabel);
        this.add(sexLabel);

        firstNameField.setBounds(200,60,200,30);
        firstNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        firstNameField.setBackground(Color.WHITE);

        lastNameField.setBounds(200,110,200,30);
        lastNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(230,230,230)));
        lastNameField.setBackground(Color.WHITE);

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

        this.add(firstNameField);
        this.add(lastNameField);
        this.add(emailField);
        this.add(countryField);
        this.add(cityField);
        this.add(streetField);
        this.add(numberField);
        this.add(postalCodeField);

        sexTypes.addElement("Female");
        sexTypes.addElement("Male");
        sexComboBox.setModel(sexTypes);
        sexComboBox.setBounds(200,260,100,30);

        this.add(sexComboBox);

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

        this.add(doneButton);
    }

    public void addDoneButtonListener(ActionListener listenerForDoneButton) {
        doneButton.addActionListener(listenerForDoneButton);
    }

    public void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        countryField.setText("Country");
        cityField.setText("City");
        streetField.setText("Street");
        numberField.setText("9");
        postalCodeField.setText("73100");
    }

    private void invalidDataMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public String getFirstName() { return firstNameField.getText(); }
    public String getLastName() { return lastNameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getCountry() { return countryField.getText(); }
    public String getCity() { return cityField.getText(); }
    public String getStreet() { return streetField.getText(); }
    public String getNumber() { return numberField.getText(); }
    public String getPostalCode() { return postalCodeField.getText(); }
    public String getSex() { return (String)sexComboBox.getSelectedItem();}
}