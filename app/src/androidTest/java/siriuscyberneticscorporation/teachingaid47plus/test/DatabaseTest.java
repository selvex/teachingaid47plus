package siriuscyberneticscorporation.teachingaid47plus.test;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.db_tables.DatabaseHelper;

/**
 * Created by Johannes on 29.04.2016.
 */
public class DatabaseTest extends TestCase {


    public void testDatabaseBasic()
    {
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student michael = new Student("michael", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.createStudent(michael);

    }

}