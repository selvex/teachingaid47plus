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
import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.Subject;

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

    public void testExtendedWalkTrough() {
        getActivity().getBaseContext().deleteDatabase("sugar_example_11.db");

        EditText className = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        EditText classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(className, "25e");
        mySolo.enterText(classTeacher, "P");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        EditText studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        EditText studentNote = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.sleep(200);
        mySolo.enterText(0,"");

        mySolo.enterText(0,"Hippi");
        mySolo.enterText(studentNote, "Ist Vegetarier");
        mySolo.sleep(200);
        mySolo.clickOnButton("Add Student");
        mySolo.sleep(200);
        mySolo.clickOnButton("Done");
        EditText subject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subject_edittext);
        mySolo.enterText(0,"");
        mySolo.enterText(0, "Informatik");
        mySolo.sleep(200);
        mySolo.clickOnButton("Done");
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);

        Student student_db = Student.find(Student.class,"name = ?","Hippi").get(0);
        SchoolClass class_db = SchoolClass.find(SchoolClass.class,"name = ?","25e").get(0);
        Subject subject_db = Subject.find(Subject.class,"name = ?","Informatik").get(0);

        assertEquals(student_db.getNote(),"Ist Vegetarier");
        assertEquals(class_db.getClassTeacher(),"P");
        assertEquals(subject_db.getSchoolClass().getName(),"25e");

        mySolo.goBack();
        mySolo.goBack();
        mySolo.goBack();
        mySolo.sleep(200);
    }

}