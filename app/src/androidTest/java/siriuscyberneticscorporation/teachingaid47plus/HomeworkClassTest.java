package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

import java.text.ParseException;

/**
 * Created by Johannes on 29.04.2016.
 */
public class HomeworkClassTest extends TestCase {


    public void testHomeworkClass(){
        Homework homework = new Homework();
    }

    public void testHomeworkClass2(){

        SchoolClass schoolClass = new SchoolClass("2Stern", "Peter Lustig", "der wird niemals alt");
        Student gustav = new Student("Gustav", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject mathematic = new Subject("Chinesisch", schoolClass);
        String date = "22.22.2022";

        try {
            Homework homework = new Homework(gustav, mathematic, date, Homework.Tags.NONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testGetter(){
        SchoolClass schoolClass = new SchoolClass("2Stern", "Peter Lustig", "der wird niemals alt");
        Student gustav = new Student("Gustav", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject chinesisch = new Subject("Chinesisch", schoolClass);
        String date = "22.12.2022";
        Homework homework = null;
        try {
            homework = new Homework(gustav, chinesisch, date, Homework.Tags.EXCELLENT);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("22.12.2022", homework.getDate());
        assertEquals("Gustav", homework.getStudent().getName());
        assertEquals("Chinesisch", homework.getSubject().getName());
        assertEquals(Homework.Tags.EXCELLENT, homework.getTag());
    }

    public void testSetter(){
        Homework homework = new Homework(Homework.Tags.NONE);
        SchoolClass schoolClass = new SchoolClass("2Stern", "Peter Lustig", "der wird niemals alt");
        Student michael = new Student("michael", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject chinesisch = new Subject("Chinesisch", schoolClass);
        try {
            homework.setDate("11.11.1111");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        homework.setStudent(michael);
        homework.setSubject(chinesisch);
        homework.setTag(1);

        assertEquals("11.11.1111", homework.getDate());
        assertEquals("michael", homework.getStudent().getName());
        assertEquals("Chinesisch", homework.getSubject().getName());
        assertEquals(Homework.Tags.EXCELLENT, homework.getTag());
    }
}