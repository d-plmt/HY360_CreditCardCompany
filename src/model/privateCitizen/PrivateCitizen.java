package model.privateCitizen;

import model.Address;

public class PrivateCitizen {

    private String firstName;
    private String lastName;
    private String email;
    private String sex;
    private Address address;
    private PrivateCitizenAccount privateCitizenAccount;

    public PrivateCitizen() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public Address getAddress() {
        return address;
    }

    public PrivateCitizenAccount getPrivateCitizenAccount() {
        return privateCitizenAccount;
    }

    public void setFirstName(String firstName) {
        StringBuilder sqlStringFirstName = new StringBuilder();

        sqlStringFirstName.append("'");
        sqlStringFirstName.append(firstName);
        sqlStringFirstName.append("'");
        this.firstName = sqlStringFirstName.toString();
    }

    public void setLastName(String lastName) {
        StringBuilder sqlStringLastName = new StringBuilder();

        sqlStringLastName.append("'");
        sqlStringLastName.append(lastName);
        sqlStringLastName.append("'");
        this.lastName = sqlStringLastName.toString();
    }

    public void setEmail(String email) {
        StringBuilder sqlStringEmail = new StringBuilder();

        sqlStringEmail.append("'");
        sqlStringEmail.append(email);
        sqlStringEmail.append("'");
        this.email = sqlStringEmail.toString();
    }

    public void setSex(String sex) {
        StringBuilder sqlStringSex = new StringBuilder();

        sqlStringSex.append("'");
        sqlStringSex.append(sex);
        sqlStringSex.append("'");
        this.sex = sqlStringSex.toString();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPrivateCitizenAccount(PrivateCitizenAccount privateCitizenAccount) {
        this.privateCitizenAccount = privateCitizenAccount;
    }
}
