package model.supplier;

public class SupplierAccount {

    private String iban;
    private String expDate;
    private String netIncome;
    private String owedToCCC;
    private String commission;

    public SupplierAccount(String iban, String expDate) {
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

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

    public void setOwedToCCC(String owedToCCC) {
        this.owedToCCC = owedToCCC;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public String getOwedToCCC() {
        return owedToCCC;
    }

    public String getCommission() {
        return commission;
    }
}
