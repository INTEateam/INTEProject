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
        Customer c = new Customer("198001013310","Namn", "Efternamn", "Gata", "nr1", "12345", true);
        assertEquals("198001013310",c.getPersonnr());
    }

    @Test
    public void testPersonnrTen(){
        Customer c = new Customer("9001013318","Namn", "Efternamn", "Gata", "nr2", "12345",true);
        assertEquals("9001013318",c.getPersonnr());
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidDay(){
        new Customer("9503359995","Namn", "Efternamn","Gata","nr3","12345",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidDay12Digits(){
        new Customer("199503359995","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidMonth(){
        new Customer("9214259997","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityInvalidMonth12Digits(){
        new Customer("199214259997","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test
    public void testPersonnrDateValidityLeapYear(){
        new Customer("8402299997","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidityNotLeapYear(){
        new Customer("8302299998","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test
    public void testPersonnrDateValidityLeapYear12Digits(){
        new Customer("198402299997","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPersonnrDateValidityNotLeapYear12Digits(){
        new Customer("198302299998","Namn", "Efternamn","Gata","nr","12345",true);
    }

    @Test
    public void testPersonnrChecksumValid(){
        Customer c = new Customer("8112189876","Namn", "Efternamn", "Gata", "nr", "12345",true);
        assertEquals(true,c.validChecksum(c.getPersonnr()));
    }

    @Test (expected = Exception.class)
    public void testPersonnrChecksumInvalid(){
        new Customer("8112189877","Namn", "Efternamn", "Gata", "nr", "12345",true);
    }

    @Test
    public void testPersonnrChecksumValid12digits(){
        Customer c = new Customer("198112189876","Namn", "Efternamn", "Gata", "nr", "12345",true);
        assertEquals(true,c.validChecksum(c.getPersonnr()));
    }

    @Test (expected = Exception.class)
    public void testPersonnrChecksumInvalid12digits(){
        new Customer("198112189877","Namn", "Efternamn", "Gata", "nr", "12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNameBlankName(){
        new Customer("8112189876","","Efternamn","Gata","nr","12345",true);
    }

    @Test
    public void testName1Character(){
        Customer c = new Customer("8112189876","A","Efternamn","Gata","nr","12345",true);
        assertEquals("A",c.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNameContainsDigits(){
        new Customer("8112189876","Namn 123", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNameContainsSpecialCharacters(){
        new Customer("8112189876","Kurt %#¤%&}][€", "Efternamn","Gata","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSurnameBlankSurname(){
        new Customer("8112189876","Namn","","Gata","nr","12345",true);
    }

    @Test
    public void testSurname1Character(){
        Customer c = new Customer("8112189876","Namn","B","Gata","nr","12345",true);
        assertEquals("B",c.getSurname());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSurnameContainsDigits(){
        new Customer("8112189876","Namn", "Efternamn 456","Gata","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSurnameContainsSpecialCharacters(){
        new Customer("8112189876","Namn", "Bergstedt $€{[]}","Gata","nr","12345",true);
    }

    @Test
    public void testGetFullName(){
        Customer c = new Customer("8112189876","Bilbo", "Baggins","Gata","nr","12345",true);
        Customer c2 = new Customer("8112189876","Frodo", "Baggins","Gata","nr","12345",true);
        assertEquals("Bilbo Baggins",c.getFullName());
        assertEquals("Frodo Baggins",c2.getFullName());
    }

    @Test
    public void testNameRemoveWhitespace(){
        Customer c = new Customer("8112189876"," Namn ", "Efternamn","Gata","nr","12345",true);
        assertEquals("Namn",c.getName());
    }

    @Test
    public void testSurnameRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn", " Efternamn ","Gata","nr","12345",true);
        assertEquals("Efternamn",c.getSurname());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddressBlankAddress(){
        new Customer("8112189876","Namn", "Efternamn","","nr","12345",true);
    }

    @Test
    public void testAddress1Character(){
        new Customer("8112189876","Namn", "Efternamn","G","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddressContainsDigits(){
        new Customer("8112189876","Namn", "Efternamn","Gata 123","nr","12345",true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddressContainsSpecialCharacters(){
        new Customer("8112189876","Namn", "Efternamn","#¤%","nr","12345",true);
    }

    @Test
    public void testAddressRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn", "Efternamn"," Ovre Bergsgatan ","nr","12345",true);
        assertEquals("Ovre Bergsgatan",c.getAddress());
    }

    @Test
    public void testStreetNumber1Digit(){
        Customer c = new Customer("8112189876","Namn","Efternamn","Gata","1","12345",true);
        assertEquals("1",c.getStreetnumber());
    }

    @Test (expected = Exception.class)
    public void testStreetNumberContainsSpecialCharacters(){
        new Customer("8112189876","Namn","Efternamn","Gata","#%&/)(=","12345",true);
    }

    @Test
    public void testStreetNumberRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn","Efternamn","Gata"," 123B ","12345",true);
        assertEquals("123B",c.getStreetnumber());
    }

    @Test
    public void testAddressGetFullAddress(){
        Customer c = new Customer("8112189876","Namn","Efternamn","Stortorget","2","12345",true);
        assertEquals("Stortorget 2",c.getFullAddress());
    }

    @Test (expected = Exception.class)
    public void testZipcodeBlank(){
        new Customer("8112189876","Namn","Efternamn","Gata","nr","",true);
    }

    @Test (expected = Exception.class)
    public void testZipcodeLessThan5Digits(){
        new Customer("8112189876","Namn","Efternamn","Gata","nr","5",true);
    }

    @Test (expected = Exception.class)
    public void testZipcodeMoreThan5Digits(){
        new Customer("8112189876","Namn","Efternamn","Gata","nr","123456",true);
    }

    @Test (expected = Exception.class)
    public void testZipcodeContainsNonDigits(){
        new Customer("8112189876","Namn","Efternamn","Gata","nr","ABCDE",true);
    }

    @Test
    public void testZipcodeRemoveWhitespace(){
        Customer c = new Customer("8112189876","Namn","Efternamn","Gata","nr"," 12 3   6 8 ",true);
        assertEquals("12368",c.getZipcode());
    }

    @Test
    public void testCustomerWithoutAddressNotMember(){
        Customer c = new Customer("8112189876","Namn","Efternamn",false);
        assertEquals(false,c.getMember());
    }

    @Test
    public void testCustomerWithoutAddressMemberNotMember12Digits(){
        Customer c = new Customer("198112189876","Namn","Efternamn",true);
        assertEquals(true,c.getMember());
        c.setMember(false);
        assertEquals(false,c.getMember());
    }

    @Test (expected = Exception.class)
    public void testCustomerWithFuturePersonnummer12Digits(){
        new Customer("208112189876","Namn","Efternamn",true);
    }

    @Test
    public void testToString(){
        Customer c = new Customer("8112189876","Namn","Efternamn","Gata","nr","12368",true);
        assertEquals("\nNamn Efternamn\nGata nr\n12368\n",c.toString());
    }

}
