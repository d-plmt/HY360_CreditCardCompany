package controller;

import view.GUI;
import model.Generator;

public class ControllerContainer {

    private CompanyRegistrationController companyRegistrationController;
    private PrivateCitizenRegistrationController privateCitizenRegistrationController;
    private SupplierRegistrationController supplierRegistrationController;
    private CccController cccController;
    private UserLoginController userLoginController;

    public ControllerContainer(GUI gui) {
        companyRegistrationController        = new CompanyRegistrationController(gui.getCompanyRegistrationPanel());
        privateCitizenRegistrationController = new PrivateCitizenRegistrationController(gui.getPrivateCitizenRegistrationPanel());
        supplierRegistrationController       = new SupplierRegistrationController(gui.getSupplierRegistrationPanel());
        cccController                        = new CccController(gui.getCccView());
        userLoginController                  = new UserLoginController(gui.getUserLoginView(), gui);

    }
}
