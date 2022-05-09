package model;

public class Address {
    private String country;
    private String city;
    private String street;
    private String number;
    private String postalCode;

    public Address() {

    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCountry(String country) {
        StringBuilder sqlStringCountry= new StringBuilder();

        sqlStringCountry.append("'");
        sqlStringCountry.append(country);
        sqlStringCountry.append("'");
        this.country = sqlStringCountry.toString();
    }

    public void setCity(String city) {
        StringBuilder sqlStringCity = new StringBuilder();

        sqlStringCity.append("'");
        sqlStringCity.append(city);
        sqlStringCity.append("'");
        this.city = sqlStringCity.toString();
    }

    public void setStreet(String street) {
        StringBuilder sqlStringStreet = new StringBuilder();

        sqlStringStreet.append("'");
        sqlStringStreet.append(street);
        sqlStringStreet.append("'");
        this.street = sqlStringStreet.toString();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
