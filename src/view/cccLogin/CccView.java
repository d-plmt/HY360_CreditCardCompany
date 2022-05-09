package view.cccLogin;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CccView extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTable goodUsersTable;
    private DefaultTableModel goodUsersTableModel;
    private JTable badUsersTable;
    private DefaultTableModel badUsersTableModel;
    private JTable mostActiveEmployeeTable;
    private DefaultTableModel mostActiveEmployeeTableModel;

    private JButton goodUsersButton;
    private JButton badUsersButton;
    private JButton mostActiveEmployeeButton;
    private JButton deleteAccountButton;
    private JButton clearTableButton;

    private CardLayout tablesCardLayout;
    private JPanel tablesPanel;
    private DeleteAccountView deleteAccountView;

    public CccView(GUI gui) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();

        initComponents();
    }

    private void initComponents() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        buttonsPanel.setBackground(new Color(255,252,252));
        this.add(buttonsPanel, BorderLayout.NORTH);

        tablesCardLayout = new CardLayout();
        tablesPanel      = new JPanel();

        tablesPanel.setLayout(tablesCardLayout);

        initGoodUsersTable();
        initBadUsersTable();
        initMostActiveEmployeeTable();

        this.add(tablesPanel, BorderLayout.CENTER);

        goodUsersButton = new JButton("Good Users");
        personalizeButton(goodUsersButton);
        buttonsPanel.add(goodUsersButton);

        badUsersButton = new JButton("Bad Users");
        personalizeButton(badUsersButton);
        buttonsPanel.add(badUsersButton);

        mostActiveEmployeeButton = new JButton("Most Active Employee");
        personalizeButton(mostActiveEmployeeButton);
        buttonsPanel.add(mostActiveEmployeeButton);

        deleteAccountButton = new JButton("Delete Account");
        personalizeButton(deleteAccountButton);
        buttonsPanel.add(deleteAccountButton);
        deleteAccountView = new DeleteAccountView();
        tablesPanel.add(deleteAccountView, "deleteAccount");
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablesCardLayout.show(tablesPanel, "deleteAccount");
            }
        });

        clearTableButton = new JButton("Clear Table");
        personalizeButton(clearTableButton);
        buttonsPanel.add(clearTableButton);
        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanGoodUsersTableModel();
                cleanBadUsersTableModel();
                cleanMostActiveEmployeeTableModel();
            }
        });

        JButton homeButton = new JButton("Home");
        personalizeButton(homeButton);

        homeButton.addActionListener(e -> {
            cleanGoodUsersTableModel();
            cleanBadUsersTableModel();
            cardLayout.show(cardPanel, "home");
        });
        this.add(homeButton, BorderLayout.SOUTH);
    }

    public CardLayout getTablesCardLayout() { return tablesCardLayout; }

    public JPanel getTablesPanel() { return tablesPanel; }

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

    private void initGoodUsersTable() {
        goodUsersTableModel = new DefaultTableModel();
        goodUsersTable = new JTable(goodUsersTableModel);
        goodUsersTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        goodUsersTableModel.addColumn("ID");
        goodUsersTableModel.addColumn("Email");
        goodUsersTableModel.addColumn("Iban");

        goodUsersTable.setCellSelectionEnabled(false);
        goodUsersTable.setShowVerticalLines(true);
        goodUsersTable.setDragEnabled(false);
        goodUsersTable.getTableHeader().setReorderingAllowed(false);
        goodUsersTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        goodUsersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        goodUsersTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane jScrollPaneG = new JScrollPane(goodUsersTable);
        tablesPanel.add(jScrollPaneG,"goodUsersTable");
    }

    private void initBadUsersTable() {
        badUsersTableModel = new DefaultTableModel();
        badUsersTable = new JTable(badUsersTableModel);
        badUsersTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        badUsersTableModel.addColumn("ID");
        badUsersTableModel.addColumn("Email");
        badUsersTableModel.addColumn("Iban");

        badUsersTable.setCellSelectionEnabled(false);
        badUsersTable.setShowVerticalLines(true);
        badUsersTable.setDragEnabled(false);
        badUsersTable.getTableHeader().setReorderingAllowed(false);
        badUsersTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        badUsersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        badUsersTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane jScrollPaneB = new JScrollPane(badUsersTable);
        tablesPanel.add(jScrollPaneB,"badUsersTable");
    }

    private void initMostActiveEmployeeTable() {
        mostActiveEmployeeTableModel = new DefaultTableModel();
        mostActiveEmployeeTable = new JTable(mostActiveEmployeeTableModel);
        mostActiveEmployeeTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        mostActiveEmployeeTableModel.addColumn("Supplier ID");
        mostActiveEmployeeTableModel.addColumn("Transactions");

        mostActiveEmployeeTable.setCellSelectionEnabled(false);
        mostActiveEmployeeTable.setShowVerticalLines(true);
        mostActiveEmployeeTable.setDragEnabled(false);
        mostActiveEmployeeTable.getTableHeader().setReorderingAllowed(false);
        mostActiveEmployeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        mostActiveEmployeeTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        JScrollPane jScrollPaneB = new JScrollPane(mostActiveEmployeeTable);
        tablesPanel.add(jScrollPaneB,"mostActiveEmployeeTable");
    }

    public void addDeleteButtonActionListener(ActionListener deleteButtonActionListener) {
        deleteAccountButton.addActionListener(deleteButtonActionListener);
    }

    public void cleanGoodUsersTableModel() {
        goodUsersTableModel.setRowCount(0);
    }

    public void cleanBadUsersTableModel() {
        badUsersTableModel.setRowCount(0);
    }

    public void cleanMostActiveEmployeeTableModel() {
        mostActiveEmployeeTableModel.setRowCount(0);
    }

    public void setGoodUsersData(String[][] data) {
        for (String[] datum : data) {
            goodUsersTableModel.addRow(datum);
        }
    }

    public void setBadUsersData(String[][] data) {
        for (String[] datum : data) {
            badUsersTableModel.addRow(datum);
        }
    }

    public void setMostActiveEmployeeData(String[][] data) {
        for (String[] datum : data) {
            mostActiveEmployeeTableModel.addRow(datum);
        }
    }

    public void addGoodUsersButtonListener(ActionListener listenerForGoodUsersButton) {
        goodUsersButton.addActionListener(listenerForGoodUsersButton);
    }

    public void addBadUsersButtonListener(ActionListener listenerForBadUsersButton) {
        badUsersButton.addActionListener(listenerForBadUsersButton);
    }

    public void addMostActiveEmployeeButtonListener(ActionListener mostActiveEmployee) {
        mostActiveEmployeeButton.addActionListener(mostActiveEmployee);
    }

    public DeleteAccountView getDeleteAccountView() {
        return deleteAccountView;
    }

}