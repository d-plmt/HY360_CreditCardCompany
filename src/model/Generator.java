package model;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Generator {

    public static String ibanGenerate(){
        StringBuilder iban = new StringBuilder();

        iban.append("'");
        for(int i = 0; i < 16; i++) iban.append((int)(Math.random() * 10));
        iban.append("'");

        return iban.toString();
    }

    public static String expDateGenerate() {
        StringBuilder expDate = new StringBuilder();
        int year, month, day;

        expDate.append("'");
        year = (int) ((Math.random() * ((2027-2013)+1))+2013);
        expDate.append(year);
        expDate.append('-');

        month = (int) ((Math.random() * ((12-1)+1))+1);
        if(month < 10) expDate.append('0');
        expDate.append(month);
        expDate.append('-');

        day = (int) ((Math.random() * ((30-1)+1))+1);
        if(day < 10) expDate.append('0');
        expDate.append(day);

        expDate.append("'");
        return expDate.toString();
    }

    public static String commissionGenerate() {
        StringBuilder commission = new StringBuilder();
        int number;
        double com;

        number = (int) ((Math.random() * ((10-5)+1))+5);
        com = number / 100.0;

        return String.valueOf(com);
    }

    public static String getCurrentDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return date.format(now);
    }
}
