package view.userLogin;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoViewer extends JPanel {

    private DefaultTableModel transactionTableModel;
    private JTable transactionTable;
    private JLabel transactionHistory = new JLabel("Transaction History");
    private JLabel fromLabel = new JLabel("from");
    private JLabel toLabel = new JLabel("to");
    private JLabel dateFormat = new JLabel("date format: yyyy-mm-dd");
    private JTextField dateFrom = new JTextField();
    private JTextField dateTo = new JTextField();
    private JButton chechButton = new JButton("check");
    private JButton homeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public InfoViewer(GUI gui) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();

        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        topPanel.setBackground(new Color(250,250,255));

        transactionHistory.setFont(new Font("Times New Roman", Font.BOLD, 16));
        fromLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        toLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        dateFrom.setColumns(8);
        dateTo.setColumns(8);
        dateFormat.setFont(new Font("Times New Roman", Font.ITALIC, 12));

        topPanel.add(transactionHistory);
        topPanel.add(Box.createHorizontalStrut(70));
        topPanel.add(fromLabel);
        topPanel.add(dateFrom);
        topPanel.add(toLabel);
        topPanel.add(dateTo);

        personalizeButton(chechButton);
        chechButton.setPreferredSize(new Dimension(70,19));
        topPanel.add(chechButton);

        topPanel.add(Box.createHorizontalStrut(70));
        topPanel.add(dateFormat);

        this.add(topPanel, BorderLayout.NORTH);

        homeButton = new JButton("Home");
        personalizeButton(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "home");
            }
        });
        this.add(homeButton, BorderLayout.SOUTH);

        initTransactionTable();
    }

    private void initTransactionTable() {
        transactionTableModel = new DefaultTableModel();
        transactionTable      = new JTable(transactionTableModel);
        transactionTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        transactionTableModel.addColumn("ID");
        transactionTableModel.addColumn("Client Name");
        transactionTableModel.addColumn("Supplier Name");
        transactionTableModel.addColumn("Type");
        transactionTableModel.addColumn("Cost");
        transactionTableModel.addColumn("Date");

        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        transactionTable.setCellSelectionEnabled(false);
        transactionTable.setShowVerticalLines(true);
        transactionTable.setDragEnabled(false);
        transactionTable.getTableHeader().setReorderingAllowed(false);
        transactionTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane jScrollPaneG = new JScrollPane(transactionTable);
        this.add(jScrollPaneG, BorderLayout.CENTER);
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

    public void addCheckButtonActionListener(ActionListener actionListener) {
        chechButton.addActionListener(actionListener);
    }

    public void setTransactionHistory(String[][] transactionHistory) {
        for (String[] datum : transactionHistory) {
            transactionTableModel.addRow(datum);
        }
    }

    public void cleanTransactionHistoryTable() { transactionTableModel.setRowCount(0); }

    public String getDateFrom() { return dateFrom.getText(); }

    public String getDateTo() { return dateTo.getText(); }
}
