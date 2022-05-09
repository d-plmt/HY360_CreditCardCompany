package view;

import view.cccLogin.CccView;
import view.registration.CompanyRegistrationView;
import view.registration.PrivateCitizenRegistrationView;
import view.registration.SupplierRegistrationView;
import view.userLogin.*;
import view.userLogin.company.CompanyInfoView;
import view.userLogin.company.CompanyMakeTransactionView;
import view.userLogin.company.CompanyUserView;
import view.userLogin.privateCitizen.PrivateCitizenUserView;
import view.userLogin.supplier.SupplierUserView;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class GUI extends JFrame{

    private JPanel                         cardPanel;
    private HomeView                       homePanel;
    private UserTypeView                   userTypePanel;
    private CompanyRegistrationView        companyRegistrationPanel;
    private PrivateCitizenRegistrationView privateCitizenRegistrationPanel;
    private SupplierRegistrationView       supplierRegistrationPanel;
    private CccView                        cccView;
    private UserLoginView                  userLoginView;
    private PrivateCitizenUserView         privateCitizenUserView;
    private CompanyUserView                companyUserView;
    private SupplierUserView               supplierUserView;
    private OverviewView                   overviewPanel;
    private MakeTransactionView            makeTransactionView;
    private CardLayout                     cardLayout;
    private ReturnView                     returnView;
    private PayDebtView                    payDebtView;
    private InfoViewer                     infoViewer;
    private CompanyInfoView                companyInfoView;
    private CompanyMakeTransactionView     companyMakeTransactionView;

    public GUI() {
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(220,220,240)));

        this.setTitle("Credit Card Company");
        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(825, 550));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel  = new JPanel();

        cardPanel.setLayout(cardLayout);
        this.setLayout(cardLayout);

        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
         homePanel                       = new HomeView(this);
         userTypePanel                   = new UserTypeView(this);
         companyRegistrationPanel        = new CompanyRegistrationView(this);
         privateCitizenRegistrationPanel = new PrivateCitizenRegistrationView(this);
         supplierRegistrationPanel       = new SupplierRegistrationView(this);
         overviewPanel                   = new OverviewView(this);
         cccView                         = new CccView(this);
         userLoginView                   = new UserLoginView(this);
         privateCitizenUserView          = new PrivateCitizenUserView(this);
         companyUserView                 = new CompanyUserView(this);
         supplierUserView                = new SupplierUserView(this);
         makeTransactionView             = new MakeTransactionView(this);
         returnView                      = new ReturnView(this);
         payDebtView                     = new PayDebtView(this);
         infoViewer                      = new InfoViewer(this);
         companyInfoView                 = new CompanyInfoView(this);
         companyMakeTransactionView      = new CompanyMakeTransactionView(this);

         cardPanel.add(homePanel, "home");
         cardPanel.add(userTypePanel,"user_type");
         cardPanel.add(companyRegistrationPanel, "companyReg");
         cardPanel.add(privateCitizenRegistrationPanel, "privateCitizenReg");
         cardPanel.add(supplierRegistrationPanel, "supplierReg");
         cardPanel.add(overviewPanel, "overview");
         cardPanel.add(cccView, "ccc");
         cardPanel.add(userLoginView, "userLogin");
         cardPanel.add(privateCitizenUserView, "private_user_login");
         cardPanel.add(companyUserView, "company_user_login");
         cardPanel.add(supplierUserView, "supplier_user_login");
         cardPanel.add(makeTransactionView, "make_transaction");
         cardPanel.add(returnView, "return");
         cardPanel.add(payDebtView, "payDebt");
         cardPanel.add(infoViewer, "info");
         cardPanel.add(companyInfoView, "companyInfo");
         cardPanel.add(companyMakeTransactionView, "companyTransactions");

         this.add(cardPanel);
    }

    public CardLayout getCardLayout() { return cardLayout; }

    public JPanel getCardPanel() { return cardPanel; }

    public HomeView getHomePanel() {return homePanel; }

    public CompanyRegistrationView getCompanyRegistrationPanel() {
        return companyRegistrationPanel;
    }

    public PrivateCitizenRegistrationView getPrivateCitizenRegistrationPanel() { return privateCitizenRegistrationPanel; }

    public SupplierRegistrationView getSupplierRegistrationPanel() {
        return supplierRegistrationPanel;
    }

    public CccView getCccView() { return cccView; }

    public UserLoginView getUserLoginView() { return userLoginView; }

    public MakeTransactionView getMakeTransactionView() { return makeTransactionView; }

    public CompanyMakeTransactionView getCompanyMakeTransactionView() { return companyMakeTransactionView; }

    public ReturnView getReturnView() {return returnView; }

    public PrivateCitizenUserView getPrivateCitizenUserView() {return privateCitizenUserView; }

    public SupplierUserView getSupplierUserView() {return supplierUserView; }

    public CompanyUserView getCompanyUserView() {return companyUserView; }

    public PayDebtView getPayDebtView() { return payDebtView; }

    public InfoViewer getInfoViewer() {return infoViewer; }

    public CompanyInfoView getCompanyInfoView() { return companyInfoView; }
}