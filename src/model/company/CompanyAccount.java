package model.company;

public class CompanyAccount {
    private String iban;
    private String expDate;

    public CompanyAccount(String iban, String expDate){
        this.iban    = iban;
        this.expDate = expDate;
    }

    public String getIban() {
        return iban;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
