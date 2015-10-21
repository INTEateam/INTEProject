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
    private final static int PERSONNR_LENGTH_LONG = 12;
    private final static int PERSONNR_LENGTH_SHORT = 10;
    private String personnr;
    private String name;
    private String surname;
    private String address;
    private String streetnumber;
    private String zipcode;
    private Boolean member;

    public Customer(String personnr, String name, String surname, String address, String streetnumber, String zipcode, Boolean member){
        setPersonnr(personnr);
        setName(name);
        setSurname(surname);
        setAddress(address);
        setStreetnumber(streetnumber);
        setZipcode(zipcode);
        this.member = member;
    }

    public Customer(String personnr, String name, String surname, Boolean member){
        setPersonnr(personnr);
        setName(name);
        setSurname(surname);
        this.member = member;
    }

    public String getPersonnr() {
        return personnr;
    }

    private void setPersonnr(String personnr){
        if(personnr.length() == PERSONNR_LENGTH_SHORT){
            String datepart = personnr.substring(0,6);
            if(validDate(datepart, PERSONNR_LENGTH_SHORT) && validChecksum(personnr)){
                this.personnr = personnr;
            }else{
                throw new IllegalArgumentException("Invalid personnr.");
            }
        }else if(personnr.length() == PERSONNR_LENGTH_LONG){
                String datepart = personnr.substring(0,8);
                if(validDate(datepart,PERSONNR_LENGTH_LONG) && validChecksum(personnr)){
                    this.personnr = personnr;
                }else{
                    throw new IllegalArgumentException("Invalid personnr.");
                }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String s = name.replace(" ","");
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
        String s = surname.replace(" ","");
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String s = address.trim();
        Pattern pattern = Pattern.compile("(.)*([^\\w\\s]|[\\d])(.)*");
        if(s.equals("")){
            throw new IllegalArgumentException("Address can't be empty.");
        }else if(pattern.matcher(s).matches()){
            throw new IllegalArgumentException("Address can't contain digits or special characters.");
        }else{
            this.address = s;
        }
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        String s = streetnumber.trim();
        Pattern pattern = Pattern.compile("(.)*([\\W])(.)*");
        if(pattern.matcher(s).matches()){
            throw new IllegalArgumentException("Address can't contain special characters.");
        }else{
            this.streetnumber = s;
        }
    }

    public String getFullAddress(){
        return getAddress() + " " + getStreetnumber();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        String s = zipcode.replace(" ","");
        Pattern pattern = Pattern.compile("(.)*([\\D])(.)*");
        if(s.equals("") || s.length() != 5){
            throw new IllegalArgumentException("Zipcode must be 5 digits long.");
        }else if(pattern.matcher(s).matches()){
            throw new IllegalArgumentException("Zipcode can't contain non-digits.");
        }else{
            this.zipcode = s;
        }
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
                sdf.parse(dateToCheck);
            }catch (ParseException e){
                return false;
            }
        }else if(length == 12){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false);
            Date today = new Date();
            try{
                Date date = sdf.parse(dateToCheck);
                if(date.after(today)){
                    throw new IllegalArgumentException("Date of birth cannot specify a future date.");
                }
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
