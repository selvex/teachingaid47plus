package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
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
    private EditText className;
    private EditText classTeacher;
    private EditText studentName;
    private EditText addSubject;
    private EditText date;
    private EditText rating;
    private EditText cpemail;
    private TextView plus;
    private TextView minus;
    private TextView average;


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
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.sleep(200);

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
        getActivity().getBaseContext().deleteDatabase("sugar_db_55.db");

    }

    public void testParticipation(){
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.clickOnButton("new class");
        mySolo.sleep(200);
        className  = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(className, "7U");
        mySolo.enterText(classTeacher, "Schulte");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.enterText(studentName, "Lisa");
        mySolo.sleep(200);
        mySolo.clickOnButton("Add Student");
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        addSubject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.enterText(addSubject, "DG");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));
        mySolo.sleep(200);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "12.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("~");
        mySolo.sleep(200);
        View dropdownRating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdownRating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 5));
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(1000);
    }

    public void testTest(){

        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.clickOnButton("new class");
        mySolo.sleep(200);
        className  = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(className, "8U");
        mySolo.enterText(classTeacher, "Schulte");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.sleep(1000);
        mySolo.enterText(studentName, "Lisa");
        mySolo.sleep(200);
        mySolo.clickOnButton("Add Student");
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        addSubject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.sleep(1000);
        mySolo.enterText(addSubject, "DG");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));
        mySolo.sleep(200);
        mySolo.clickOnButton("Test");
        mySolo.sleep(200);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "12.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("0");
        mySolo.sleep(200);
        rating = (EditText) mySolo.getView(R.id.test_rating_edit);
        mySolo.enterText(rating, "7");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(1000);
    }

    public void testHomework() {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.clickOnButton("new class");
        mySolo.sleep(200);
        className  = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.sleep(1000);
        mySolo.enterText(className, "9U");
        mySolo.sleep(1000);
        mySolo.enterText(classTeacher, "Warga");

        mySolo.sleep(1000);
        mySolo.enterText(classTeacher, "Schulte");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.sleep(1000);
        mySolo.enterText(studentName, "Lisa");
        mySolo.sleep(200);
        mySolo.clickOnButton("Add Student");
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        addSubject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.sleep(1000);
        mySolo.enterText(addSubject, "DG");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));
        mySolo.sleep(200);
        mySolo.clickOnButton("Homework");
        mySolo.sleep(200);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "12.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("NONE");
        mySolo.sleep(200);
        View dropdownRating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdownRating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 3));
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(1000);
    }

    public void testStudentInfoActivity(){
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.clickOnButton("new class");
        mySolo.sleep(200);
        className  = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(className, "666");
        mySolo.enterText(classTeacher, "Devil");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(1000);
        studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.enterText(studentName, "Lilith");
        mySolo.sleep(200);
        mySolo.clickOnButton("Add Student");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        addSubject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.enterText(addSubject, "DG");
        mySolo.sleep(500);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));
        mySolo.sleep(200);

        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "12.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("~");
        mySolo.sleep(200);
        View dropdownRating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdownRating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 5));
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(500);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "13.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("~");
        mySolo.sleep(200);
        View dropdownRating2 = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdownRating2);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 4));
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(500);

        mySolo.clickOnButton("Test");
        mySolo.sleep(200);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "14.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("0");
        mySolo.sleep(200);
        rating = (EditText) mySolo.getView(R.id.test_rating_edit);
        mySolo.enterText(rating, "7");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(500);
        mySolo.clickOnButton("+");
        date  = (EditText) mySolo.getView(R.id.date_editText);
        mySolo.sleep(200);
        mySolo.enterText(date, "15.08.2015");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.clickOnButton("0");
        mySolo.sleep(200);
        rating = (EditText) mySolo.getView(R.id.test_rating_edit);
        mySolo.enterText(rating, "9");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(500);

        mySolo.clickOnText("Lilith");
        mySolo.sleep(1000);
        cpemail = (EditText) mySolo.getCurrentActivity().findViewById(R.id.cpemail_edittext);
        mySolo.sleep(1000);
        mySolo.enterText(cpemail, "BeyondTheMirror");
        mySolo.sleep(1000);
        mySolo.clickOnButton("save");
        mySolo.sleep(200);

        Student student = Student.find(Student.class, "name = ?", "Lilith").get(0);
        mySolo.sleep(500);
        assertEquals(student.getContactPersonEMail(), "BeyondTheMirror");

        dropdown_class = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));
        mySolo.sleep(200);

        mySolo.clickOnText("Lilith");
        mySolo.sleep(1000);
        plus = (TextView) mySolo.getCurrentActivity().findViewById(R.id.plus_textview);
        minus = (TextView) mySolo.getCurrentActivity().findViewById(R.id.minus_textview);
        average = (TextView) mySolo.getCurrentActivity().findViewById(R.id.test_average_textview);
        mySolo.sleep(1000);

        assertEquals(plus.getText().toString(), "Plus: 3");
        assertEquals(minus.getText().toString(),"Minus: 0");
        assertEquals(average.getText().toString(),"Testpoints average: 8");

        mySolo.clickOnButton("delete student");
        mySolo.sleep(1000);

        List<Student> students = Student.find(Student.class, "name = ?", "Lilith");
        assertEquals(students.size(),0);
    }

    public void testDeleteStudent(){
        mySolo.clickOnView(getActivity().findViewById(R.id.action_add_class));
        mySolo.clickOnButton("new class");
        mySolo.sleep(1000);
        className  = (EditText) mySolo.getCurrentActivity().findViewById(R.id.class_edittext);
        classTeacher = (EditText) mySolo.getCurrentActivity().findViewById(R.id.teacher_edittext);
        mySolo.enterText(className, "90U");
        mySolo.sleep(1000);
        mySolo.enterText(classTeacher, "Schulte");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(1000);
        studentName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.enterText(studentName, "Lisa");
        mySolo.sleep(1000);
        mySolo.clickOnButton("Add Student");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(1000);

        SchoolClass class_db = SchoolClass.find(SchoolClass.class, "name = ?", "90U").get(0);
        List<Student> students_db = Student.find(Student.class, "school_class = ?", String.valueOf(class_db.getId()));
        assertEquals(students_db.size(), 1);

        addSubject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.enterText(addSubject, "DG");
        mySolo.sleep(1000);
        mySolo.clickOnButton("done");
        mySolo.sleep(1000);
        mySolo.clickOnView(getActivity().findViewById(R.id.action_settings));
        mySolo.sleep(1000);
        mySolo.clickOnButton("Edit class");
        mySolo.sleep(1000);
        mySolo.clickOnText("90U");
        mySolo.sleep(1000);
        mySolo.clickOnButton("-");
        mySolo.sleep(1000);
        mySolo.goBack();
        mySolo.sleep(1000);
        mySolo.goBack();
        mySolo.sleep(1000);
        mySolo.goBack();
        mySolo.sleep(1000);
        mySolo.goBack();
        mySolo.sleep(1000);

        List<Student> students_db_new = Student.find(Student.class, "school_class = ?", String.valueOf(class_db.getId()));
        assertEquals(students_db_new.size(), 0);
    }
}