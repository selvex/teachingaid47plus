package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Johannes on 29.04.2016.
 */
public class StudentClassTest extends TestCase {

    public void testClass2()
    {
        SchoolClass to_enter = new SchoolClass("1A", "Peter Lustig", "ziemlich lustig");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", to_enter);
    }

    public void testGetter() {
        SchoolClass to_enter = new SchoolClass("1B", "Peter Lustig", "ziemlich lustig");

        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", to_enter);
        assertEquals("peter", peter.getName());
        assertEquals("Adi", peter.getContactPersonName());
        assertEquals("0123456789", peter.getContactPersonTelNumber());
        assertEquals("asdf@jklö.com", peter.getContactPersonEMail());
        assertEquals("Graz", peter.getAddress());
        assertEquals("Hallo", peter.getNote());
        assertEquals(to_enter, peter.getSchoolClass());
    }

    public void testSetter() {
        Student peter = new Student();
        peter.setName("peter");
        peter.setContactPersonName("Adi");
        peter.setContactPersonTelNumber("0123456789");
        peter.setContactPersonEMail("asdf@jklö.com");
        peter.setAddress("Graz");
        peter.setNote("Hallo");
        SchoolClass to_enter = new SchoolClass("1C", "Peter Lustig", "ziemlich lustig");
        peter.setSchoolClass(to_enter);

        assertEquals("peter", peter.getName());
        assertEquals("Adi", peter.getContactPersonName());
        assertEquals("0123456789", peter.getContactPersonTelNumber());
        assertEquals("asdf@jklö.com", peter.getContactPersonEMail());
        assertEquals("Graz", peter.getAddress());
        assertEquals("Hallo", peter.getNote());
        assertEquals(to_enter, peter.getSchoolClass());
    }
}