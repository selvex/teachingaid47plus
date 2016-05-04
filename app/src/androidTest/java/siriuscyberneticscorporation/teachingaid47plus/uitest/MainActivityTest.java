package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;


import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
import siriuscyberneticscorporation.teachingaid47plus.Student;


/**
 * Created by SeLveX on 22.04.2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public MainActivityTest()
    {
        super(MainActivity.class);

    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());


    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testButtons()
    {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_student));
        mySolo.assertCurrentActivity("wrong activity", AddStudentActivity.class);
        mySolo.goBack();
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.goBack();
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_subject));
        mySolo.goBack();

    }

    public void testSugarDB()
    {
        SchoolClass sc_class = new SchoolClass("1A", "Peter Lustig", "sehr lustig");
        Student paul = new Student("paul", "Nein", "0123", "asdf@jklö.com", "Graz", "Hallo", sc_class);
        long id_class_saved_to = sc_class.save();
        long id_saved_to = paul.save();
        Student from_db = Student.findById(Student.class, id_saved_to);
        assertEquals("paul", from_db.getName());

    }

    public void testDatabaseBasic()
    {

        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Databasssssssseeeeeee");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);

        peter.save();
        josef.save();
        long returned_student_id = michael.save();
        long returned_id = schoolClass.save();
        SchoolClass from_db = SchoolClass.findById(SchoolClass.class, returned_id);
        assertEquals(from_db.getNote(), "Databasssssssseeeeeee");
        assertEquals(Student.findById(Student.class, returned_student_id).getName(), "michael");

    }

    public void testZKeepDbClean()
    {
        getActivity().getBaseContext().deleteDatabase("sugar_example_1.db");
    }
}