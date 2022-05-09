package model.company;

import model.Address;

public class Company {
    private String companyName;
    private String ceoName;
    private String email;
    private String numOfEmployees;
    private Address address;
    private CompanyAccount companyAccount;

    public Company() {}

    public String getCompanyName() {
        return companyName;
    }

    public String getCeoName() {
        return ceoName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumOfEmployees() {
        return numOfEmployees;
    }

    public Address getAddress() {
        return address;
    }

    public CompanyAccount getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyName(String companyName) {
        StringBuilder sqlStringCompanyName = new StringBuilder();

        sqlStringCompanyName.append("'");
        sqlStringCompanyName.append(companyName);
        sqlStringCompanyName.append("'");
        this.companyName = sqlStringCompanyName.toString();
    }

    public void setCeoName(String ceoName) {
        StringBuilder sqlStringCeoName = new StringBuilder();

        sqlStringCeoName.append("'");
        sqlStringCeoName.append(ceoName);
        sqlStringCeoName.append("'");
        this.ceoName = sqlStringCeoName.toString();
    }

    public void setEmail(String email) {
        StringBuilder sqlStringEmail = new StringBuilder();

        sqlStringEmail.append("'");
        sqlStringEmail.append(email);
        sqlStringEmail.append("'");
        this.email = sqlStringEmail.toString();
    }

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCompanyAccount(CompanyAccount companyAccount) {
        this.companyAccount = companyAccount;
    }
}


