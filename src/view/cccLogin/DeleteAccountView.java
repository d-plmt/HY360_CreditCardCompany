package view.cccLogin;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteAccountView extends JPanel {
    private JComboBox usersComboBox;
    private JButton deleteUserButton;

    public DeleteAccountView() {
        this.setLayout(null);
        this.setBackground(Color.white);

        usersComboBox = new JComboBox();
        usersComboBox.setBounds(275,200,100,25);
        this.add(usersComboBox);

        deleteUserButton = new JButton("Delete Account");
        personalizeButton(deleteUserButton);
        deleteUserButton.setBounds(380,200,120,25);
        this.add(deleteUserButton);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(150,30));
        button.setBackground(new Color(255,248,248));
        button.setForeground(new Color(150,5,40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(160,35,35)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(250, 230,230);
            }
        });
    }

    public void setAccountIdsData(String[] data) {
        usersComboBox.removeAllItems();
        for(int i = 0; i < data.length; i++) {
            usersComboBox.addItem(data[i]);
        }
    }

    public Object getComboBoxValue() {
        return usersComboBox.getSelectedItem();
    }

    public void addDeleteButtonActionListener(ActionListener actionListener) {
        deleteUserButton.addActionListener(actionListener);
    }

    public void removeItem(Object o) {
        usersComboBox.removeItem(o);
    }
}
