package controller;

import model.*;
import model.privateCitizen.PrivateCitizen;
import model.privateCitizen.PrivateCitizenAccount;
import model.privateCitizen.PrivateCitizenQueryHandler;
import view.registration.PrivateCitizenRegistrationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PrivateCitizenRegistrationController {

    private PrivateCitizenRegistrationView theView;
    private Generator theModel;

    public PrivateCitizenRegistrationController(PrivateCitizenRegistrationView theView) {

        this.theView  = theView;
        this.theView.addDoneButtonListener(new DoneButtonListener() );
    }

    class DoneButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            PrivateCitizen privateCitizen = new PrivateCitizen();
            privateCitizen.setFirstName(theView.getFirstName());
            privateCitizen.setLastName(theView.getLastName());
            privateCitizen.setEmail(theView.getEmail());
            privateCitizen.setSex(theView.getSex());

            Address address = new Address();
            address.setCountry(theView.getCountry());
            address.setCity(theView.getCity());
            address.setStreet(theView.getStreet());
            address.setNumber(theView.getNumber());
            address.setPostalCode(theView.getPostalCode());
            privateCitizen.setAddress(address);

            PrivateCitizenAccount privateCitizenAccount = new PrivateCitizenAccount(Generator.ibanGenerate(), Generator.expDateGenerate());
            privateCitizen.setPrivateCitizenAccount(privateCitizenAccount);

            theView.clearFields();
            try {
                PrivateCitizenQueryHandler.registerPrivateCitizen(privateCitizen);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


