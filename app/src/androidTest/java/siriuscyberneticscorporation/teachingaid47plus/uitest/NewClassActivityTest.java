package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentsActivity;
import siriuscyberneticscorporation.teachingaid47plus.AddSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.NewClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;

/**
 * Created by Bettina on 04.05.2016.
 */
public class NewClassActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public NewClassActivityTest()
    {
        super(NewClassActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWalkTrough() {
        mySolo.clickOnButton("done");
        mySolo.assertCurrentActivity("wrong activity", AddStudentsActivity.class);
        mySolo.goBack();
        EditText className = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        mySolo.enterText(className, "3e");
        EditText classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(classTeacher, "Gunther Peichl");
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.enterText(note, "Böse Klasse");
        mySolo.clickOnButton("done");
    }

}