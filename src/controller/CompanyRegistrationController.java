package controller;

import model.*;
import model.company.Company;
import model.company.CompanyAccount;
import model.company.CompanyQueryHandler;
import view.registration.CompanyRegistrationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CompanyRegistrationController {

    private CompanyRegistrationView theView;

    public CompanyRegistrationController(CompanyRegistrationView theView) {

        this.theView  = theView;
        this.theView.addDoneButtonListener(new DoneButtonListener() );
    }

    class DoneButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Company company = new Company();
            company.setCompanyName(theView.getCompanyName());
            company.setCeoName(theView.getCeoName());
            company.setEmail(theView.getEmail());
            company.setNumOfEmployees(theView.getNumOfEmployees());

            Address address = new Address();
            address.setCountry(theView.getCountry());
            address.setCity(theView.getCity());
            address.setStreet(theView.getStreet());
            address.setNumber(theView.getNumber());
            address.setPostalCode(theView.getPostalCode());
            company.setAddress(address);

            CompanyAccount companyAccount = new CompanyAccount(Generator.ibanGenerate(), Generator.expDateGenerate());
            company.setCompanyAccount(companyAccount);

            theView.clearFields();
            try {
                CompanyQueryHandler.registerCompany(company);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

