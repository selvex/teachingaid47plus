package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AddTestActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.Participation;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
import siriuscyberneticscorporation.teachingaid47plus.SchoolTest;
import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.Subject;

/**
 * Created by Philipp on 24.05.2016.
 */
public class AddTestActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddTestActivityTest() { super(AddTestActivity.class); }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public  void testWalkTrough() {
        Button done = (Button) mySolo.getCurrentActivity().findViewById(R.id.test_done_button);
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.test_note_edit);
        EditText rating = (EditText) mySolo.getCurrentActivity().findViewById(R.id.test_rating_edit);
        EditText date = (EditText) mySolo.getCurrentActivity().findViewById(R.id.test_date_edit);

        mySolo.enterText(date, "01.05.2000");
        mySolo.enterText(rating, "100");
        mySolo.enterText(note, "Volle Punktzahl");
        mySolo.sleep(300);
        mySolo.clickOnButton("done");
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    public void testDBFunctionality1() {
        SchoolClass schoolClass = new SchoolClass("Untertertia","Augustus","");
        Student student = new Student("Caesar", "","","","","mag keine Gallier",schoolClass);
        Subject subject = new Subject("Latein 2.0",schoolClass);
        String date = "12.11.2010";
        SchoolTest test = null;
        try {
            test = new SchoolTest(date, student, subject);
        } catch (ParseException e){
            e.printStackTrace();
        }
        schoolClass.save();
        student.save();
        subject.save();
        test.save();
        mySolo.sleep(10000);
    }

    public void testDBFunctionality2(){

                Student student = Student.find(Student.class, "name = ?", "Caesar").get(0);
        Subject subject = Subject.find(Subject.class, "name = ?", "Latein 2.0").get(0);
        List<SchoolTest> tests = SchoolTest.find(SchoolTest.class, "subject = ? and student = ?", String.valueOf(subject.getId()), String.valueOf(student.getId()));

        int counter = 0;
        for (SchoolTest p : tests)
        {
            if (counter > 0)
            {
                System.out.println("More than 1 test found");
                break;
            }
            assertEquals("Caesar", p.getStudent().getName());
            assertEquals("Latein 2.0", p.getSubject().getName());
            p.delete();
            counter++;
        }
        subject.getSchoolClass().delete();
        student.delete();
        subject.delete();

    }
}