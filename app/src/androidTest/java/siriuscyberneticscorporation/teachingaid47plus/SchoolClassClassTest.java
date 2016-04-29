package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SchoolClassClassTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testSchoolClass(){
        SchoolClass schoolClass = new SchoolClass();
    }

    public void testSchoolClass2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");
    }
    public void testGetter(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");

        schoolClass.getName();
        schoolClass.getClassTeacher();
        schoolClass.getStudents();
        schoolClass.getNote();

    }

    public void testSetter(){
        SchoolClass schoolClass = new SchoolClass();
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);

        schoolClass.setName("4a");
        schoolClass.setClassTeacher("Hoellerbauer");
        schoolClass.setStudents(students);
        schoolClass.setNote("New Note of Class");

    }

    public void testAddStudent(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");

        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo");
        boolean sucess = schoolClass.addStudent(michael);
        assertEquals(sucess, true);
    }

    public void testAddStudent2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");


        boolean sucess = schoolClass.addStudent(peter);
        assertEquals(sucess, false);
    }

    public void testDeleteStudent(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");

        boolean success = schoolClass.deleteStudent(peter);
        assertEquals(success, true);
    }

    public void testDeleteStudent2(){
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo");
        List<Student> students = new ArrayList<Student>();
        students.add(peter);
        students.add(josef);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", students, "Note of Class");

        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo");
        boolean success = schoolClass.deleteStudent(michael);
        assertEquals(success, false);
    }
}