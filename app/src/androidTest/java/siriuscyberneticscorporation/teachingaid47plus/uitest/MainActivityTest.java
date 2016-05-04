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
import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.db_tables.DatabaseHelper;


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

    public void testDatabaseBasic()
    {
        Context context = getActivity().getBaseContext();
        DatabaseHelper db = new DatabaseHelper(context);
        Student peter = new Student("peter", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        Student michael = new Student("michael", "Adi", "0123456789", "asdf@jklö.com", "Graz", "Hallo");
        long id_1 = db.createStudent(michael);
        long id_2 = db.createStudent(peter);
        System.out.println("Michaels new id: " + Long.toString(id_1));
    }

    public void testDatabaseEntries()
    {
        Context context = getActivity().getBaseContext();
        DatabaseHelper db = new DatabaseHelper(context);
        Student michael = db.getStudentById(1);
        assertEquals("michael", michael.getName());
    }
}