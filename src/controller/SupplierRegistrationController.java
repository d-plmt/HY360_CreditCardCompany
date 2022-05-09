package controller;

import model.*;
import model.supplier.Supplier;
import model.supplier.SupplierAccount;
import model.supplier.SupplierQueryHandler;
import view.registration.SupplierRegistrationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SupplierRegistrationController {

    private SupplierRegistrationView theView;
    private Generator theModel;

    public SupplierRegistrationController(SupplierRegistrationView theView) {

        this.theView = theView;
        this.theView.addDoneButtonListener(new DoneButtonListener() );
    }

    class DoneButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Supplier supplier = new Supplier();
            supplier.setFirstName(theView.getFirstName());
            supplier.setLastName(theView.getLastName());
            supplier.setEmail(theView.getEmail());
            supplier.setSex(theView.getSex());

            Address address = new Address();
            address.setCountry(theView.getCountry());
            address.setCity(theView.getCity());
            address.setStreet(theView.getStreet());
            address.setNumber(theView.getNumber());
            address.setPostalCode(theView.getPostalCode());
            supplier.setAddress(address);

            SupplierAccount supplierAccount = new SupplierAccount(Generator.ibanGenerate(), Generator.expDateGenerate());
            supplierAccount.setCommission(Generator.commissionGenerate());
            supplierAccount.setNetIncome("0");
            supplierAccount.setOwedToCCC("0");
            supplier.setSupplierAccount(supplierAccount);

            theView.clearFields();
            try {
                SupplierQueryHandler.registerSupplier(supplier);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


