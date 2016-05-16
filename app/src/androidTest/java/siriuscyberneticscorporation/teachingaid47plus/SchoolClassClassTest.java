package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SchoolClassClassTest extends TestCase {


    public void testSchoolClass(){
        SchoolClass schoolClass = new SchoolClass();
    }

    public void testSchoolClass2(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
    }
    public void testGetter(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);

        assertEquals(josef.getSchoolClass().getName(), "4a");
        assertEquals(schoolClass.getClassTeacher(), "Huber");
        assertEquals(schoolClass.getNote(), "Note of Class");


    }

    public void testSetter(){
        SchoolClass schoolClass = new SchoolClass();
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);


        schoolClass.setName("4a");
        schoolClass.setClassTeacher("Hoellerbauer");
        schoolClass.setNote("New Note of Class");

        assertEquals(schoolClass.getName(), "4a");
        assertEquals(josef.getSchoolClass().getClassTeacher(), "Hoellerbauer");
        assertEquals(schoolClass.getNote(), "New Note of Class");

    }

    public void testAddStudent(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        /* function to test removed, build this into db test */
    }

    public void testAddStudent2(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        /* function to test removed, build this into db test */
    }

    public void testDeleteStudent(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);

        /*
        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);
        success = schoolClass.addStudent(josef);
        assertEquals(success, true);
        success = schoolClass.deleteStudent(peter);
        assertEquals(success, true);
        */
    }

    public void testDeleteStudent2(){
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);

        /*
        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);
        success = schoolClass.addStudent(josef);
        assertEquals(success, true);
        success = schoolClass.deleteStudent(michael);
        assertEquals(success, false);
        */
    }
}