package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

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

        Homework homework = new Homework(date, gustav, mathematic, 3, "Hallo");
    }

    public void testGetter(){
        SchoolClass schoolClass = new SchoolClass("2Stern", "Peter Lustig", "der wird niemals alt");
        Student gustav = new Student("Gustav", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject chinesisch = new Subject("Chinesisch", schoolClass);
        String date = "22.22.2022";
        Homework homework = new Homework(date, gustav, chinesisch, EXCELLENT, "Hallo");

        assertEquals(homework.getDate(),"22.22.2022" );
        assertEquals(homework.getStudent().getName(), "Gustav");
        assertEquals(homework.getSubject().getName(), "Chinesisch");
        assertEquals(homework.getTag(), EXCELENT);
    }

    public void testSetter(){
        Homework homework = new Homework();
        SchoolClass schoolClass = new SchoolClass("2Stern", "Peter Lustig", "der wird niemals alt");
        Student michael = new Student("michael", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Subject chinesisch = new Subject("Chinesisch", schoolClass);
        homework.setDate("11.11.1111");
        homework.setStudent(michael);
        homework.setSubject(chinesisch);
        homework.setTag(Tags.EXCELLENT);
    }
}