package inte.projekt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test?
 */
public class TestCustomer {
    @Test
    public void testPersonnr(){
        Customer c = new Customer("198001013310","Namn", "Efternamn", "Gata", "nr1", true);
        assertEquals("198001013310",c.getPersonnr());
    }

    @Test
    public void testPersonnrTen(){
        Customer c = new Customer("9001013318","Namn", "Efternamn", "Gata", "nr2", true);
        assertEquals("9001013318",c.getPersonnr());
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidDay(){
        new Customer("9503359995","Namn", "Efternamn","Gata","nr3",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidDay12Digits(){
        new Customer("199503359995","Namn", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidMonth(){
        new Customer("9214259997","Namn", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidMonth12Digits(){
        new Customer("199214259997","Namn", "Efternamn","Gata","nr",true);
    }

    @Test
    public void testPersonnrDateValidityLeapYear(){
        new Customer("8402299997","Namn", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityNotLeapYear(){
        new Customer("8302299998","Namn", "Efternamn","Gata","nr",true);
    }

    @Test
    public void testPersonnrDateValidityLeapYear12Digits(){
        new Customer("198402299997","Namn", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityNotLeapYear12Digits(){
        new Customer("198302299998","Namn", "Efternamn","Gata","nr",true);
    }

    @Test // Currently creates a customer, even with invalid checksum.
    public void testPersonnrChecksumValid(){
        Customer c = new Customer("8112189876","Namn", "Efternamn", "Gata", "nr", true);
        assertEquals(true,c.validChecksum(c.getPersonnr()));
    }

    @Test
    public void testPersonnrChecksumInvalid(){
        Customer c = new Customer("8112189877","Namn", "Efternamn", "Gata", "nr", true);
        assertEquals(false,c.validChecksum(c.getPersonnr()));
    }

    @Test
    public void testPersonnrChecksumValid12digits(){
        Customer c = new Customer("198112189876","Namn", "Efternamn", "Gata", "nr", true);
        assertEquals(true,c.validChecksum(c.getPersonnr()));
    }

    @Test
    public void testPersonnrChecksumInvalid12digits(){
        Customer c = new Customer("198112189877","Namn", "Efternamn", "Gata", "nr", true);
        assertEquals(false,c.validChecksum(c.getPersonnr()));
    }

    @Test (expected = Exception.class)
    public void testNameBlankName(){
        new Customer("8112189876","","Efternamn","Gata","nr",true);
    }

    @Test
    public void testName1Character(){
        Customer c = new Customer("8112189876","A","Efternamn","Gata","nr",true);
        assertEquals("A",c.getName());
    }

    @Test (expected = Exception.class)
    public void testNameContainsDigits(){
        new Customer("8112189876","Namn 123", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testNameContainsSpecialCharacters(){
        new Customer("8112189876","Kurt %#¤%&}][€", "Efternamn","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testSurnameBlankSurname(){
        Customer c = new Customer("8112189876","Namn","","Gata","nr",true);
    }

    @Test
    public void testSurname1Character(){
        Customer c = new Customer("8112189876","Namn","B","Gata","nr",true);
        assertEquals("B",c.getSurname());
    }

    @Test (expected = Exception.class)
    public void testSurnameContainsDigits(){
        new Customer("8112189876","Namn", "Efternamn 456","Gata","nr",true);
    }

    @Test (expected = Exception.class)
    public void testSurnameContainsSpecialCharacters(){
        new Customer("8112189876","Namn", "Bergstedt $€{[]}","Gata","nr",true);
    }

    @Test
    public void testGetFullName(){
        Customer c = new Customer("8112189876","Bilbo", "Baggins","Gata","nr",true);
        Customer c2 = new Customer("8112189876","Frodo", "Baggins","Gata","nr",true);
        assertEquals("Bilbo Baggins",c.getFullName());
        assertEquals("Frodo Baggins",c2.getFullName());
    }

    @Test
    public void testNameRemoveWhitespace(){
        Customer c = new Customer("8112189876"," Namn ", "Efternamn","Gata","nr",true);
        assertEquals("Namn",c.getName());
    }

    @Test
    public void testSurnameRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn", " Efternamn ","Gata","nr",true);
        assertEquals("Efternamn",c.getSurname());
    }

    @Test (expected = Exception.class)
    public void testAdressBlankAdress(){
        new Customer("8112189876","Namn", "Efternamn","","nr",true);
    }

    @Test
    public void testAdress1Character(){
        new Customer("8112189876","Namn", "Efternamn","G","nr",true);
    }

    @Test (expected = Exception.class)
    public void testAdressContainsDigits(){
        new Customer("8112189876","Namn", "Efternamn","Gata 123","nr",true);
    }

    @Test (expected = Exception.class)
    public void testAdressContainsSpecialCharacters(){
        new Customer("8112189876","Namn", "Efternamn","#¤%","nr",true);
    }

    @Test
    public void testAdressRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn", "Efternamn"," Ovre Bergsgatan ","nr",true);
        assertEquals("Ovre Bergsgatan",c.getAdress());
    }

}
