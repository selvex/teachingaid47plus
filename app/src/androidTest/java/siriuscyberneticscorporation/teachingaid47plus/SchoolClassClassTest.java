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
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        schoolClass.addStudent(peter);
        schoolClass.addStudent(josef);
    }
    public void testGetter(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        schoolClass.addStudent(peter);
        schoolClass.addStudent(josef);

        assertEquals(schoolClass.getName(), "4a");
        assertEquals(schoolClass.getClassTeacher(), "Huber");
        Student[] array = new Student[] {peter, josef};
        int i = 0;
        for(Student s : schoolClass.getStudents()){
            assertEquals(s, array[i]);
            i = i + 1;
        }
        assertEquals(schoolClass.getNote(), "Note of Class");


    }

    public void testSetter(){
        SchoolClass schoolClass = new SchoolClass();
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");


        schoolClass.setName("4a");
        schoolClass.setClassTeacher("Hoellerbauer");
        schoolClass.setNote("New Note of Class");

        assertEquals(schoolClass.getName(), "4a");
        assertEquals(schoolClass.getClassTeacher(), "Hoellerbauer");
        assertEquals(schoolClass.getNote(), "New Note of Class");

    }

    public void testAddStudent(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo");

        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");

        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);
        success = schoolClass.addStudent(josef);
        assertEquals(success, true);
        success = schoolClass.addStudent(michael);
        assertEquals(success, true);
    }

    public void testAddStudent2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");


        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);

        success = schoolClass.addStudent(peter);
        assertEquals(success, false);
    }

    public void testDeleteStudent(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");

        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");

        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);
        success = schoolClass.addStudent(josef);
        assertEquals(success, true);
        success = schoolClass.deleteStudent(peter);
        assertEquals(success, true);
    }

    public void testDeleteStudent2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo");
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Note of Class");
        
        boolean success = schoolClass.addStudent(peter);
        assertEquals(success, true);
        success = schoolClass.addStudent(josef);
        assertEquals(success, true);
        success = schoolClass.deleteStudent(michael);
        assertEquals(success, false);
    }
}