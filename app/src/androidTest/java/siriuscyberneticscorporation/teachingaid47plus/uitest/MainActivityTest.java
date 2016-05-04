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
        Student paul = new Student("paul", "Nein", "0123", "asdf@jklö.com", "Graz", "Hallo");
        long id_saved_to = paul.save();
        Student from_db = Student.findById(Student.class, id_saved_to);
        assertEquals("paul", from_db.getName());

    }
}