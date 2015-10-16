package inte.projekt;

import freemarker.template.SimpleDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Customer {
    String personnr;
    String name;
    String adress;
    String gatunummer;
    Boolean member;

    public Customer(String personnr, String name, String adress, String gatunummer, Boolean member){
        setPersonnr(personnr);
        this.name = name;
        this.adress = adress;
        this.gatunummer = gatunummer;
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
            if(validDate(datepart,10)){
                this.personnr = personnr;
            }else{
                throw new IllegalArgumentException("Invalid personnr.");
            }
        }else if(personnr.length() == 12){
            if(personnr.charAt(0) == '1' && personnr.charAt(1) == '9'){
                this.personnr = personnr;
            }
        }else {
            throw new IllegalArgumentException("Invalid personnr");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
}
