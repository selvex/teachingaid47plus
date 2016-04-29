package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Johannes on 29.04.2016.
 */
public class StudentClassTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

   public void testClass(){
        Student peter = new Student();
    }

    public void testClass2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
    }

    public void testGetter(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        peter.getName();
        peter.getContactPersonName();
        peter.getContactPersonTelNumber();
        peter.getContactPersonEMail();
        peter.getAddress();
        peter.getNote();
    }

    public void testSetter(){
        Student peter = new Student();
        peter.setName("peter");
        peter.setContactPersonName("Adi");
        peter.setContactPersonTelNumber("123");
        peter.setContactPersonEMail("asdf@jklö.at");
        peter.setAddress("Graz");
        peter.setNote("Hallo");
    }
}