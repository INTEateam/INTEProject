package inte.projekt;

//import freemarker.template.SimpleDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Customer {
    String personnr;
    String name;
    String surname;
    String adress;
    String streetnumber;
    Boolean member;

    public Customer(String personnr, String name, String surname, String adress, String gatunummer, Boolean member){
        setPersonnr(personnr);
        setName(name);
        setSurname(surname);
        setAdress(adress);
        this.streetnumber = gatunummer;
        this.member = member;
    }

    public Customer(String personnr, String name, Boolean member){
        this.personnr = personnr;
        this.name = name;
        this.member = member;
    }

    public String getPersonnr() {
        return personnr;
    }

    private void setPersonnr(String personnr){
        if(personnr.length() == 10){
            String datepart = personnr.substring(0,6);
            if(validDate(datepart, 10)){
                this.personnr = personnr;
            }else{
                throw new IllegalArgumentException("Invalid personnr.");
            }
        }else if(personnr.length() == 12){
            if(personnr.charAt(0) == '1' && personnr.charAt(1) == '9'){
                String datepart = personnr.substring(0,8);
                if(validDate(datepart,12)){
                    this.personnr = personnr;
                }else{
                    throw new IllegalArgumentException("Invalid personnr.");
                }
            }
        }else {
            throw new IllegalArgumentException("Invalid personnr");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String s = name.replaceAll("\\s","");
        Pattern pattern = Pattern.compile("(.)*([\\W]|[\\d])(.)*");
        if(s.equals("")){
            throw new IllegalArgumentException("Name can't be empty.");
        }else if(pattern.matcher(s).matches()){ // Pattern checks if string contains any digits or special characters.
            throw new IllegalArgumentException("Name can't contain digits or special characters.");
        }else{
            this.name = s;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String s = surname.replaceAll("\\s","");
        Pattern pattern = Pattern.compile("(.)*([\\W]|[\\d])(.)*");
        if(s.equals("")){
            throw new IllegalArgumentException("Surname can't be empty.");
        }else if(pattern.matcher(s).matches()){
            throw new IllegalArgumentException("Surname can't contain digits or special characters.");
        }else{
            this.surname = s;
        }
    }

    public String getFullName() {
        return getName() + " " + getSurname();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        String s = adress.trim();
        Pattern pattern = Pattern.compile("(.)*([^\\w\\s]|[\\d])(.)*");
        if(s.equals("")){
            throw new IllegalArgumentException("Adress can't be empty.");
        }else if(pattern.matcher(s).matches()){
            throw new IllegalArgumentException("Adress can't contain digits or special characters.");
        }else{
            this.adress = s;
        }
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public boolean validDate(String dateToCheck,int length){
        if(length == 10){
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);
            try{
                Date date = sdf.parse(dateToCheck);
            }catch (ParseException e){
                return false;
            }
        }else if(length == 12){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false);
            try{
                Date date = sdf.parse(dateToCheck);
            }catch (ParseException e){
                return false;
            }
        }
        return true;
    }

    // Luhn-Algorithm, 10 digit check
    public boolean validChecksum(String personnr) {
        String tocheck = personnr;
        if(tocheck.length() > 10){
            tocheck = tocheck.substring(2);
        }
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(tocheck).reverse().toString();
        for(int i = 0; i < reverse.length(); i++){
            int digit = Character.digit(reverse.charAt(i),10);
            if(i % 2 == 0){
                s1 += digit;
            }else{
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

}
