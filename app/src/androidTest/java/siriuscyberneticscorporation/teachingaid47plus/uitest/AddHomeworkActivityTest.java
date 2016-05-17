package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.text.ParseException;
import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AddClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.AddHomeworkActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.Homework;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
import siriuscyberneticscorporation.teachingaid47plus.Student;
import siriuscyberneticscorporation.teachingaid47plus.Subject;

/**
 * Created by Johannes on 17.05.2016.
 */
public class AddHomeworkActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddHomeworkActivityTest()
    {
        super(AddHomeworkActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWalkTrough() {

        View dropdown_rating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_rating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 2));
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.homework_note_edit);
        EditText date = (EditText) mySolo.getCurrentActivity().findViewById(R.id.homework_date_edit);
        mySolo.enterText(date, "11.11.2011");
        mySolo.enterText(note, "Sweet");
        mySolo.clickOnButton("done");
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    public void testDropdown() {
        View dropdown_rating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_rating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));
    }

    public void testHDate(){
        EditText date = (EditText) mySolo.getCurrentActivity().findViewById(R.id.homework_date_edit);
        mySolo.enterText(date, "11.11.2011");
        assertEquals("11.11.2011", date.getText().toString());
    }

    public void testDBFunctionality1() {
        SchoolClass schoolClass = new SchoolClass("Bienenstock","Tom","");
        Student student = new Student("Biene Maja", "","","","","mag Blumen",schoolClass);
        Subject subject = new Subject("Honig",schoolClass);
        String date = "12.12.2012";
        Homework test = null;
        try {
            test = new Homework(student, subject, date, Homework.Tags.NONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        schoolClass.save();
        student.save();
        subject.save();
        test.save();
    }

    public void testDBFunctionality2(){
        Student student = Student.find(Student.class, "name = ?", "Biene Maja").get(0);
        Subject subject = Subject.find(Subject.class, "name = ?", "Honig").get(0);
        List<Homework> homeworks = Homework.find(Homework.class, "subject = ? and student = ?", String.valueOf(subject.getId()), String.valueOf(student.getId()));

        int counter = 0;
        for (Homework p : homeworks)
        {
            if (counter > 0)
            {
                System.out.println("More than 1 homework found");
                break;
            }
            assertEquals("Biene Maja", p.getStudent().getName());
            assertEquals("Honig", p.getSubject().getName());
            p.delete();
            counter++;
        }
        subject.getSchoolClass().delete();
        student.delete();
        subject.delete();

    }
}