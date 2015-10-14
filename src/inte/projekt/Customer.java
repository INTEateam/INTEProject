package inte.projekt;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Customer {
    String personnr;
    String name;
    String adress;
    String gatunummer;
    Boolean member;

    public Customer(String personnr, String name, String adress, String gatunummer, Boolean member) throws IllegalArgumentException{
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
            this.personnr = personnr;
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
}
