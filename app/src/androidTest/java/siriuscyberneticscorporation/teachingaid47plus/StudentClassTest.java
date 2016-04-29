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
        peter.get_name();
        peter.get_contactPersonName();
        peter.get_contactPersonTelNumber();
        peter.get_contactPersonEMail();
        peter.get_address();
        peter.get_note();
    }

    public void testSetter(){
        Student peter = new Student();
        peter.set_name("peter");
        peter.set_contactPersonName("Adi");
        peter.set_contactPersonTelNumber("123");
        peter.set_contactPersonEMail("asdf@jklö.at");
        peter.set_address("Graz");
        peter.set_note("Hallo");
    }
}