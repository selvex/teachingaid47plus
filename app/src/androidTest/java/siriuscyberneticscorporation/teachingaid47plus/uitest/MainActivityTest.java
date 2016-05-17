package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentsActivity;
import siriuscyberneticscorporation.teachingaid47plus.SettingsActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.Subject;


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

    public void testButtons() {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_settings));
        mySolo.assertCurrentActivity("wrong activity", SettingsActivity.class);
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
        SchoolClass.findById(SchoolClass.class, (long) 1);
        Subject.findById(Subject.class, (long) 1);
        Student.findById(Student.class, (long) 1);
        SchoolClass schoolClass = new SchoolClass("4a", "Huber", "Databasssssssseeeeeee");
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student josef = new Student("josef", "Bettina", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);
        Student michael = new Student("michael", "Kevin", "0123", "asdf@jklö.com", "Graz", "Hallo", schoolClass);

        long returned_id = schoolClass.save();
        peter.save();
        josef.save();
        long returned_student_id = michael.save();
        SchoolClass from_db = SchoolClass.findById(SchoolClass.class, returned_id);
        assertEquals(from_db.getNote(), "Databasssssssseeeeeee");
        assertEquals(Student.findById(Student.class, returned_student_id).getName(), "michael");

        List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(from_db.getId()));
        for (Student s : students)
        {
            System.out.println("Student name: " + s.getName());
        }

        peter.delete();
        josef.delete();
        michael.delete();
        schoolClass.delete();
    }

    public void testDropDowns()
    {
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        View dropdown_subject = mySolo.getView(Spinner.class, 1);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));
        mySolo.clickOnView(dropdown_subject);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));
    }

    public void testZKeepDbClean()
    {
        getActivity().getBaseContext().deleteDatabase("sugar_db_54.db");
    }
}