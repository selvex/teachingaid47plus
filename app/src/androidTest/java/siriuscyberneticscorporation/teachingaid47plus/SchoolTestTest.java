package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

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
        schoolClass.save();
        peter.save();
        mathematic.save();

        SchoolTest schoolTest = new SchoolTest(date, peter ,50, mathematic);

    }
    public void testGetter(){
        SchoolClass schoolClass = new SchoolClass("1Stern", "Peter Lustig", "der wird niemals alt");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject mathematic = new Subject("Mathematic", schoolClass);
        String date = "16.02.2016";
        schoolClass.save();
        peter.save();
        mathematic.save();

        SchoolTest schoolTest = new SchoolTest(date, peter ,50, mathematic);

        assertEquals(schoolTest.getDate(),"16.02.2016");
        assertEquals(schoolTest.getStudent(),"Huber");
        assertEquals(schoolTest.getRating(), 50);
        assertEquals(schoolTest.getSubject(), "Mathematik");
    }
    public void testSetter(){
        SchoolTest schoolTest = new SchoolTest();
        schoolTest.setDate("1.1.2016");
        schoolTest.setStudent("Michael");
        schoolTest.setRating(49);
        schoolTest.setSubject("Deutsch");

        assertEquals(schoolTest.getDate(), "1.1.2016");
        assertEquals(schoolTest.getStudent(), "Michael");
        assertEquals(schoolTest.getRating(), 49);
        assertEquals(schoolTest.getSubject(), "Deutsch");
    }

}
