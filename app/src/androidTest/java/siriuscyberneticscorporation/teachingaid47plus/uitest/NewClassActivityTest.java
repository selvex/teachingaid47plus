package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentsActivity;
import siriuscyberneticscorporation.teachingaid47plus.AddSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.NewClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;

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
        EditText className = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        EditText classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.enterText(className, "3e");
        mySolo.enterText(classTeacher, "Gunther Peichl");
        mySolo.enterText(note, "Böse Klasse");
        mySolo.clickOnButton("done");
        mySolo.assertCurrentActivity("wrong activity", AddStudentsActivity.class);
        mySolo.goBack();
    }

    public void testInput() {
        EditText className = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        EditText classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.enterText(className, "3e");
        mySolo.enterText(classTeacher, "Gunther Peichl");
        mySolo.enterText(note, "Böse Klasse");
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        List<SchoolClass> from_db = SchoolClass.find(SchoolClass.class, "name = ?", "3e");
        assertEquals(from_db.get(0).getNote(), "Böse Klasse");
        mySolo.assertCurrentActivity("wrong activity", AddStudentsActivity.class);
        mySolo.goBack();
    }

    public void testNoInput() {
        EditText className = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        EditText classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        assertEquals(true, note.getText().toString().isEmpty());
        assertEquals(true, className.getText().toString().isEmpty());
        assertEquals(true, classTeacher.getText().toString().isEmpty());
        mySolo.clickOnButton("done");
        mySolo.assertCurrentActivity("wrong activity", NewClassActivity.class);
    }

    public void testZKeepDbClean()
    {
        getActivity().getBaseContext().deleteDatabase("sugar_example_1.db");
    }

}