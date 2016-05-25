package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

import java.text.ParseException;

/**
 * Created by Johannes on 17.05.2016.
 */
public class SchoolTestTest extends TestCase {

    public void testSchoolTest(){
        SchoolTest schoolTest = new SchoolTest();
    }

    public void testSchoolTest2(){
        SchoolClass schoolClass = new SchoolClass("1Stern", "Peter Lustig", "der wird niemals alt");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject mathematic = new Subject("Mathematic", schoolClass);
        String date = "16.02.2016";
        try {
            SchoolTest schoolTest = new SchoolTest(date, peter ,50, mathematic, "Hat geschummelt");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void testGetter(){
        SchoolClass schoolClass = new SchoolClass("1Stern", "Peter Lustig", "der wird niemals alt");
        Student peter = new Student("WWEQEWQ!", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject mathematic = new Subject("Mathematic", schoolClass);
        String date = "16.02.2016";
        SchoolTest schoolTest = null;
        try {
            schoolTest = new SchoolTest(date, peter ,50, mathematic, "Hat geschummelt");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(schoolTest.getTestDate(),"16.02.2016");
        assertEquals(schoolTest.getStudent().getName(),"WWEQEWQ!");
        assertEquals(schoolTest.getRating(), 50);
        assertEquals(schoolTest.getSubject().getName(), "Mathematic");
        assertEquals(schoolTest.getNote(),"Hat geschummelt");
    }
    public void testSetter(){
        SchoolTest schoolTest = new SchoolTest(50);
        SchoolClass to_db = new SchoolClass("TestTest0", "Igor", "NEINNEIN");
        Subject subject_for_test = new Subject("Test suib", to_db);
        try {
            schoolTest.setTestDate("1.1.2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student peter = new Student("eqkljhkweqhkeqw", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", to_db);
        schoolTest.setStudent(peter);
        schoolTest.setRating(49);
        schoolTest.setSubject(subject_for_test);
        schoolTest.setNote("Bonusaufgabe gerechnet");

        assertEquals(schoolTest.getTestDate(), "01.01.2016");
        assertEquals(schoolTest.getStudent().getName(), "eqkljhkweqhkeqw");
        assertEquals(schoolTest.getRating(), 49);
        assertEquals(schoolTest.getSubject().getName(), "Test suib");
        assertEquals(schoolTest.getNote(),"Bonusaufgabe gerechnet");
    }

}
